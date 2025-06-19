package com.ms.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.user.models.UserModel;

public interface UserReposiroty extends JpaRepository<UserModel, UUID> {
  
}
