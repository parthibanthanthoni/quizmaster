package com.quizmaster.jwt.api.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quizmaster.jwt.api.entity.User;
import com.quizmaster.jwt.api.repository.UserRepository;
@SuppressWarnings("deprecation")
@Service
public class UserService{
	@Autowired
	public UserRepository userRepository;

	public ResponseEntity<String> registration(User user) {
		EmailValidator validator = EmailValidator.getInstance();
		if (validator.isValid(user.getEmail())&& isValidPassword(user.getPassword())) {
			if (userRepository.findByEmail(user.getEmail()) == null) {
				if (userRepository.findByUserName(user.getUserName()) == null) {
					userRepository.save(user);
					return new ResponseEntity<>("Successfully Registered",HttpStatus.OK);
				} else{
					return new ResponseEntity<>("Username not avaialable",HttpStatus.CONFLICT);
				}
			} else{
				return new ResponseEntity<>("There is an account with that email address: " + user.getEmail(),HttpStatus.CONFLICT);
			}
		} else{
			return new ResponseEntity<>("Invalid EmailId or password",HttpStatus.UNAUTHORIZED);
		}
	}

	public static boolean isValidPassword(String password) {

		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);
		if (password == null) {
			return false;
		}
		Matcher m = p.matcher(password);
		return m.matches();
	}
}