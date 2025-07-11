package com.ms.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.LoginDto;
import com.ms.user.dtos.LoginResponseDto;
import com.ms.user.dtos.UserRecordDto;
import com.ms.user.mappers.UserMapper;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping("/users")
  public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
    UserModel userModel = userMapper.toModel(userRecordDto);
    UserModel savedUser = userService.save(userModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }

  @GetMapping("/hello")
  public String hello() {
    return "Olá, mundo autenticado!";
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto) {

    try {
      String token = userService.login(loginDto);

      var loginResponse = new LoginResponseDto(token);

      return ResponseEntity.ok(loginResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
