package com.sample.prototype.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 로그인 페이지 뷰 컨트롤러
 * 실제 로그인 처리는 AuthRestController에서 담당
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping
	public String loginForm() {
		log.debug("GET /login 요청, 로그인 폼 렌더링");
		return "common/login";
	}
}
