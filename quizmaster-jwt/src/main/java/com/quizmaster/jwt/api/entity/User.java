package com.quizmaster.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true, length = 10)
    private String userName;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false,length = 12)
    private String sex;
    @Column(nullable = false,length = 12)
    private long aadhaarNumber;
    @Column(nullable = false,length = 10)
    private String panNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String state;
    
}
