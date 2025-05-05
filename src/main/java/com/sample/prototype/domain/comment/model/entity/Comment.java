package com.sample.prototype.domain.comment.model.entity;

import com.sample.prototype.common.model.entity.BaseEntity;
import com.sample.prototype.domain.board.model.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_comment")
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	public Comment(String content, String author, Board board) {
		this.content = content;
		this.author = author;
		this.board = board;
	}
}
