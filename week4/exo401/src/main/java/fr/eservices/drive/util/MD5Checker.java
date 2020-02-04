package fr.eservices.drive.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
//@Qualifier("md5")
public class MD5Checker implements PasswordChecker {

	@Override
	public String encode(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Digest login + password
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		
		byte[] data1 = login.getBytes("UTF-8");
		byte[] data2 = password.getBytes("UTF-8");
		messageDigest.update(data1);
		messageDigest.update(data2);
		byte[] digest = messageDigest.digest();

		// Encoding in Base64
		String encodedString = Base64.getEncoder().encodeToString( digest );
		
		return encodedString;
	}
	
}
