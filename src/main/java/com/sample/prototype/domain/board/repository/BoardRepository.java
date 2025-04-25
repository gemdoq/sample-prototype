package com.sample.prototype.domain.board.repository;

import com.sample.prototype.domain.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
