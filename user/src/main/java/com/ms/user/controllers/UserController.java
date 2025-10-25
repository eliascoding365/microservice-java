package com.ms.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ms.user.dtos.LoginDto;
import com.ms.user.dtos.LoginResponseDto;
import com.ms.user.dtos.UserRecordDto;
import com.ms.user.mappers.UserMapper;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping("/users")
  public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
    logger.info(">>>>>>>>>> REQUEST RECEIVED TO CREATE USER! <<<<<<<<<<");
    UserModel userModel = userMapper.toModel(userRecordDto);
    UserModel savedUser = userService.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }
}
