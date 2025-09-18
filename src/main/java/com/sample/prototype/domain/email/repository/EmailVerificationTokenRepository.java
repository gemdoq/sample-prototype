package com.sample.prototype.domain.email.repository;

import com.sample.prototype.domain.email.model.entity.EmailVerificationToken;
import com.sample.prototype.domain.email.model.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 이메일 인증 토큰 레포지토리
 */
@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

	/**
	 * 토큰 값으로 조회
	 */
	Optional<EmailVerificationToken> findByToken(String token);

	/**
	 * 이메일과 토큰 타입으로 조회 (가장 최근 것)
	 */
	Optional<EmailVerificationToken> findTopByEmailAndTokenTypeOrderByCreatedDttmDesc(String email, TokenType tokenType);

	/**
	 * 이메일과 토큰 타입으로 미사용 토큰 조회
	 */
	Optional<EmailVerificationToken> findByEmailAndTokenTypeAndUsedFalse(String email, TokenType tokenType);

	/**
	 * 이메일로 기존 토큰 삭제 (새 토큰 발급 전)
	 */
	void deleteByEmailAndTokenType(String email, TokenType tokenType);
}
