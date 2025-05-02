package com.sample.prototype.global.configuration;

import com.sample.prototype.domain.user.UserRepository;
import com.sample.prototype.domain.user.model.entity.User;
import com.sample.prototype.domain.user.model.enums.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			User admin = new User(
					"admin",
					"admin@email.com",
					passwordEncoder.encode("password"),
					Role.ADMIN
			);
			userRepository.save(admin);

			User user = new User(
					"user",
					"user@email.com",
					passwordEncoder.encode("password"),
					Role.USER
			);
			userRepository.save(user);
		}
	}
}
