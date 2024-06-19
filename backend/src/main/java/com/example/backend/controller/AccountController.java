package com.example.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AccountController {
  @Autowired
  MemberRepository memberRepository;
  
  @PostMapping("/api/account/login")
  public int login(@RequestBody Map<String, String> params) {
    Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
    if (member != null) {
      return member.getId(); 
    }
    return 0; 
  }
  
}
