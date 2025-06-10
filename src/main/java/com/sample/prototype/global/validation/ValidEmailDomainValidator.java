package com.sample.prototype.global.validation;

import com.sample.prototype.global.configuration.ValidationProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ValidEmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

	private static final Logger log = LoggerFactory.getLogger(ValidEmailDomainValidator.class);
	private final List<Pattern> allowedDomainPatterns;

	public ValidEmailDomainValidator(ValidationProperties validationProperties) {
		// 설정된 도메인 리스트를 정규식 패턴 리스트로 변환
		this.allowedDomainPatterns = validationProperties.getAllowedEmailDomains().stream()
				.map(domain -> Pattern.compile("^" + Pattern.quote(domain) + "$", Pattern.CASE_INSENSITIVE))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;  // null은 @NotBlank로 처리
		}

		if (!value.contains("@")) {
			log.warn("잘못된 이메일 형식: value={}", value);
			return false;
		}

		String domain = value.substring(value.indexOf("@") + 1);

		boolean isValid = allowedDomainPatterns.stream()
				.anyMatch(pattern -> pattern.matcher(domain).matches());

		if (!isValid) {
			log.warn("허용되지 않은 이메일 도메인: value={}", value);
		}

		return isValid;
	}
}
