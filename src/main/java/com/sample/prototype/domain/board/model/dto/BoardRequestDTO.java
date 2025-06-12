package com.sample.prototype.domain.board.model.dto;

import com.sample.prototype.global.validation.RestrictedString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDTO {
	@NotBlank(message = "제목은 필수입니다.")
	@Size(max = 100, message = "제목은 100자 이내여야 합니다.")
	@RestrictedString
	private String title;

	@NotBlank(message = "내용은 필수입니다.")
	private String content;
}
