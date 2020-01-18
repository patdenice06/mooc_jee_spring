package fr.eservices.drive.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public interface PasswordChecker {

	String encode(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException;

}
