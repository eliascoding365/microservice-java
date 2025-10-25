package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;

import org.springframework.beans.factory.annotation.Value;


@Component
public class UserProducer {

  final RabbitTemplate rabbitTemplate;

  public UserProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${broker.queue.email.name}")
  private String routingKey;

  public void publishMessageEmail(UserModel userModel){
    var emailDto = new EmailDto();
    emailDto.setUserId(userModel.getUserId());
    emailDto.setEmailTo(userModel.getEmail());
    emailDto.setSubject("Cadastro realizado com sucesso!");
    emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \nEstamos felizes em te receber em nosso sistema!");

    rabbitTemplate.convertAndSend("", routingKey , emailDto );
  }
}
