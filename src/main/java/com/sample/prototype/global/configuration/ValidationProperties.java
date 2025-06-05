package com.sample.prototype.global.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// 유효성 검사에서 설정값을 바인딩하는 Configuration 클래스
@Configuration
@ConfigurationProperties(prefix = "app.validation")
public class ValidationProperties {

	private List<String> allowedEmailDomains;

	public List<String> getAllowedEmailDomains() {
		return allowedEmailDomains;
	}

	public void setAllowedEmailDomains(List<String> allowedEmailDomains) {
		this.allowedEmailDomains = allowedEmailDomains;
	}
}