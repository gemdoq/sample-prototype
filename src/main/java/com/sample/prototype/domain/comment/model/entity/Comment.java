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

	private String content;

	private String author;

	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;

	public Comment(String content, String author, Board board) {
		this.content = content;
		this.author = author;
		this.board = board;
	}
}
