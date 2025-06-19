package com.ms.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserReposiroty;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired
  UserReposiroty userRepository;

  public UserService(UserReposiroty userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public UserModel save(UserModel userModel) {
    {
      return userRepository.save(userModel);
    }
  }
}
