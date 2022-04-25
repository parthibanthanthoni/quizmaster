package com.quizmaster.jwt.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.quizmaster.jwt.api.entity.AuthRequest;
import com.quizmaster.jwt.api.entity.User;
import com.quizmaster.jwt.api.service.UserService;
import com.quizmaster.jwt.api.util.JwtUtil;


@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService service;

	@GetMapping("/signin")
	public String welcome() {
		return "hello";
	}

	@PostMapping("/signup")
	public ResponseEntity<String> register(@RequestBody User user) {
		return service.registration(user);
	}

	

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
}
