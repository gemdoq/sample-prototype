package com.sample.prototype.domain.comment.service;

import com.sample.prototype.domain.board.model.entity.Board;
import com.sample.prototype.domain.board.repository.BoardRepository;
import com.sample.prototype.domain.comment.model.dto.CommentRequestDTO;
import com.sample.prototype.domain.comment.model.dto.CommentResponseDTO;
import com.sample.prototype.domain.comment.model.entity.Comment;
import com.sample.prototype.domain.comment.repository.CommentRepository;
import com.sample.prototype.global.exception.AppException;
import com.sample.prototype.global.exception.Domain;
import com.sample.prototype.global.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

	private static final Logger log = LoggerFactory.getLogger(CommentService.class);
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	// 생성자를 통한 의존성 주입
	public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
		this.commentRepository = commentRepository;
		this.boardRepository = boardRepository;
	}

	@Transactional(readOnly = true)
	public List<CommentResponseDTO> getAllComments() {
		log.debug("전체 댓글 조회 요청");
		// DB에서 댓글 전체 조회
		List<Comment> comments = commentRepository.findAll();
		// Entity 리스트를 DTO 리스트로 변환
		return comments.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Transactional
	public void addComment(Long boardId, CommentRequestDTO requestDTO, String author) {
		log.debug("댓글 추가 요청: boardId={}, author={}", boardId, author);
		// 게시글 존재 확인
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new AppException(Domain.BOARD, ErrorCode.BOARD_NOT_FOUND));
		// 댓글 엔티티 생성 및 저장
		Comment comment = new Comment(requestDTO.getContent(), author, board);
		commentRepository.save(comment);
		log.debug("댓글 추가 완료: boardId={}, commentId={}", boardId, comment.getId());
	}

	@Transactional
	public void deleteComments(List<Long> ids) {
		log.debug("댓글 다중 삭제 요청: ids={}", ids);
		// ID 목록 유효성 확인
		if (ids == null || ids.isEmpty()) {
			log.warn("삭제할 댓글 ID 없음");
			throw new AppException(Domain.COMMENT, ErrorCode.INVALID_REQUEST, "삭제할 댓글을 선택하세요.");
		}
		// 일괄 삭제
		commentRepository.deleteAllByIdInBatch(ids);
		log.debug("댓글 다중 삭제 완료: count={}", ids.size());
	}

	@Transactional
	public void deleteComment(Long id) {
		log.debug("댓글 개별 삭제 요청: id={}", id);
		// 댓글 존재 확인
		if (!commentRepository.existsById(id)) {
			log.warn("삭제 대상 댓글 없음: id={}", id);
			throw new AppException(Domain.COMMENT, ErrorCode.COMMENT_NOT_FOUND);
		}
		// 댓글 삭제
		commentRepository.deleteById(id);
		log.debug("댓글 개별 삭제 완료: id={}", id);
	}

	// Comment 엔티티를 CommentResponseDTO로 변환
	private CommentResponseDTO toResponseDTO(Comment comment) {
		CommentResponseDTO dto = new CommentResponseDTO();
		dto.setId(comment.getId());
		dto.setContent(comment.getContent());
		dto.setAuthor(comment.getAuthor());
		dto.setCreatedDttm(comment.getCreatedDttm());
		dto.setBoardId(comment.getBoard().getId());
		return dto;
	}
}
