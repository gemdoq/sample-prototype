package com.sample.prototype.domain.board.repository;

import com.sample.prototype.domain.board.model.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@EntityGraph(attributePaths = {"comments"})
	Optional<Board> findById(Long id);
}
