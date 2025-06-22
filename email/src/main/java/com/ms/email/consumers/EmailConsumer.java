package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;

@Component
public class EmailConsumer {

  final EmailService emailService;

  public EmailConsumer(EmailService emailService){
    this.emailService = emailService;
  }

  @RabbitListener(queues = "${broker.queue.email.name}")
  public void listenEmailQueue(@Payload EmailRecordDto EmailRecordDto) {
    var emailModel = new EmailModel();
    BeanUtils.copyProperties(EmailRecordDto, emailModel);
    emailService.sendEmail(emailModel);
  }

}
