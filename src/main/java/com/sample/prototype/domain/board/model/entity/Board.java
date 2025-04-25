package com.sample.prototype.domain.board.model.entity;

import com.sample.prototype.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_board")
@Getter
@NoArgsConstructor
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;

	private String author;

	public Board(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
}
