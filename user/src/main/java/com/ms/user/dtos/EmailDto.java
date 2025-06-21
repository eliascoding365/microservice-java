package com.ms.user.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class EmailDto {
    private UUID userId;
    private String emailTo;
    private String subject;
    private String text;

}
