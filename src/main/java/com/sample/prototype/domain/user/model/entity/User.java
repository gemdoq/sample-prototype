package com.sample.prototype.domain.user.model.entity;

import com.sample.prototype.common.model.entity.BaseEntity;
import com.sample.prototype.domain.user.model.enums.Role;
import com.sample.prototype.global.validation.ValidEmailDomain;
import com.sample.prototype.global.validation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "사용자 이름은 필수입니다.")
	@ValidUsername
	@Column(nullable = false, unique = true)
	private String username;

	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "유효한 이메일 형식이어야 합니다.")
	@ValidEmailDomain
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "비밀번호는 필수입니다.")
	@Size(min = 6, max = 100, message = "비밀번호는 6~100자여야 합니다.")
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public void update(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
