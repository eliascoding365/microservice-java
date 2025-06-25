package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRecordDto  (
  @NotBlank(message = "Nome não pode estar vazio!")
  @Size(min = 3, max = 100, message = "Nome deve ter entre 3 a 100 caracteres")
  String name,

  @NotBlank(message = "Email não pode estar vazio")
  //@Email(message = "Este email não é válido")
  String email,

  @NotBlank(message = "Senha não pode estar vazio")
  @Size(min = 4, message = "Senha tem que ter no mínimo 4 caracteres")
  String password
  
  ) {}
