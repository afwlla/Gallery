package com.example.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;
import com.example.backend.service.JwtService;
import com.example.backend.service.JwtServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AccountController {

  @Autowired
  MemberRepository memberRepository;
  
  @PostMapping("/api/account/login")
  public ResponseEntity<Void> login(@RequestBody Map<String, String> params, HttpServletResponse res) {
    Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

    if (member != null) {
      JwtService jwtService = new JwtServiceImpl();
      int id = member.getId();
      String token = jwtService.getToken("id", id);

      Cookie cookie = new Cookie("token", token);
      cookie.setHttpOnly(true);
      cookie.setPath("/");

      res.addCookie(cookie);
      
      return ResponseEntity.ok().build();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
  
}
