package com.example.demo.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	private StrongPasswordEncryptor strongEncryptor;

	@Autowired
	public void setStrongEncryptor(StrongPasswordEncryptor strongEncryptor) {
		this.strongEncryptor = strongEncryptor;
	}

	@Override
	public String encryptString(String input) {
		// TODO Auto-generated method stub
		return strongEncryptor.encryptPassword(input);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptPassword) {
		// TODO Auto-generated method stub
		return strongEncryptor.checkPassword(plainPassword, encryptPassword);
	}

}
