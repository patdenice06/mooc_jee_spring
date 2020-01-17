package fr.eservices.drive.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacChecker implements PasswordChecker {
	
	@Override
	public String encode(String login, String password) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		// create a Message Authentication Code (MAC) from binary data and encoded it into Base64 string
		// On utilisera le login comme clé de hash à appliquer sur le mot de passe
		
		Mac mac = Mac.getInstance("HmacSHA1");
		
		byte[] keyBytes   = login.getBytes("UTF-8");
		String algorithm  = "RawBytes";
		SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);

		mac.init(key);
		
		byte[] data  = password.getBytes("UTF-8");
//		byte[] data2 = "0123456789".getBytes("UTF-8");

		mac.update(data);
//		mac.update(data2);

		byte[] macBytes = mac.doFinal();		
		
		// Encoding in Base64
		String encodedString = Base64.getEncoder().encodeToString( macBytes );

		
		return encodedString;
	}

}
