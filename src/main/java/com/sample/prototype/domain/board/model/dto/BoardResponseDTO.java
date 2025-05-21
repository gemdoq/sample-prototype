package com.sample.prototype.domain.board.model.dto;

import com.sample.prototype.domain.comment.model.dto.CommentResponseDTO;
import com.sample.prototype.domain.like.model.dto.LikeResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardResponseDTO {
	private Long id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdDttm;
	private LocalDateTime updatedDttm;
	private int likeCount;
	private List<CommentResponseDTO> comments;
	private List<LikeResponseDTO> likes;
}
