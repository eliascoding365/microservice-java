package com.ms.user.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.LoginDto;
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

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
    
    try {
      UserModel userLoginRequest = userService.login(loginDto);
      return ResponseEntity.ok(userLoginRequest);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } 
  }
}
