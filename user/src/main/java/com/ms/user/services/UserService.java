package com.ms.user.services;

import com.ms.user.dtos.LoginDto;
import com.ms.user.exceptions.EmailAlreadyExistsException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository,
            UserProducer userProducer,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Transactional
    public UserModel save(UserModel userModel) {

        try {
            String encodedPassword = passwordEncoder.encode(userModel.getPassword());
            userModel.setPassword(encodedPassword);

            UserModel savedUser = userRepository.save(userModel);
            userProducer.publishMessageEmail(savedUser);
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage() != null && e.getMessage().contains("ConstraintViolationException")
                    && e.getMessage().toLowerCase().contains("email")) {
                throw new EmailAlreadyExistsException("Este email: " + userModel.getEmail() + " já existe");
            }
            throw e;
        }
    }

    public String login(LoginDto loginDto) {

        UserModel user = userRepository.findByEmail(loginDto.getEmail())
                .filter(u -> passwordEncoder.matches(loginDto.getPassword(), u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas. Verifique o email e a senha."));
        return tokenService.generateToken(user);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}