package com.example.buensaborback.bussines.service;

public interface IEmailService {
    void sendEmailWithAttachment(String to, String subject, String body, byte[] attachment, String attachmentName);
}