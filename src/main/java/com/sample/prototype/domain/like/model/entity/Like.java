package com.sample.prototype.domain.like.model.entity;

import com.sample.prototype.common.model.entity.BaseEntity;
import com.sample.prototype.domain.board.model.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_like")
@Getter
@NoArgsConstructor
public class Like extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	public Like(String username, Board board) {
		this.username = username;
		this.board = board;
	}
}
