package com.example.demo.services.security;

public interface EncryptionService {

	String encryptString(String input);

	boolean checkPassword(String plainPassword, String encryptPassword);
}
