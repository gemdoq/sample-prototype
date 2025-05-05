package com.sample.prototype.domain.like.repository;

import com.sample.prototype.domain.like.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
	boolean existsByBoardIdAndUsername(Long boardId, String username);
}
