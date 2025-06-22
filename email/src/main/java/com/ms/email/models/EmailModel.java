package com.ms.email.models;

import com.ms.email.enums.EmailStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_EMAILS")
public class EmailModel implements Serializable {
  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID emailId;
  private UUID userId;
  private String emailFrom;
  private String emailTo;
  private String subject;
  @Column(columnDefinition = "TEXT")
  private String text;
  private LocalDateTime sendDateEmail;
  private EmailStatus EmailStatus;
  
}
