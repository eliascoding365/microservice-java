package com.ms.user.services;

import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserReposiroty;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    UserReposiroty userRepository;
    UserProducer userProducer;

    public UserService(UserReposiroty userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer; 
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        {
            userModel = userRepository.save(userModel);
            userProducer.publishMessageEmail(userModel);
            return userRepository.save(userModel); 
        }
    }
}