package com.ms.user.mappers;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "userId", ignore = true)
  UserModel toModel(UserRecordDto dto);
  
}