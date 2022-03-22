package com.quizmaster.jwt.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizmaster.jwt.api.entity.AuthRequest;
import com.quizmaster.jwt.api.entity.User;
import com.quizmaster.jwt.api.repository.UserRepository;
import com.quizmaster.jwt.api.util.JwtUtil;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signin")
    public String welcome() {
        return "hello";
    }
    @PostMapping("/signup")
    public String register(@RequestBody User user){
    	if(userRepository.findByUserName(user.getUserName())== null){
    		userRepository.save(user);
    		return "Succesfully Registered";	
    	}
    	else
    		return "Username not available";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
