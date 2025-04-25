package com.sample.prototype.domain.user.model.entity;

import com.sample.prototype.common.model.entity.BaseEntity;
import com.sample.prototype.domain.user.model.enums.Role;
import jakarta.persistence.*;
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

	private String username;

	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	public User(String username, String email, Role role) {
		this.username = username;
		this.email = email;
		this.role = role;
	}
}
