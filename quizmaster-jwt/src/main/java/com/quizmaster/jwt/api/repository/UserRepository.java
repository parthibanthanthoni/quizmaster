package com.quizmaster.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quizmaster.jwt.api.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	public User findByUserName(String userName);
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);


}
