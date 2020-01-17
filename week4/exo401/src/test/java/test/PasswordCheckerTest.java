package test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import fr.eservices.drive.util.HmacChecker;
import fr.eservices.drive.util.MD5Checker;
import fr.eservices.drive.util.PasswordChecker;

public class PasswordCheckerTest {
	
	@Test
	public void testMD5Checker() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		PasswordChecker checker = new MD5Checker();
		String encoded = checker.encode("dufrene", "eservices");
		
		Assert.assertEquals("jIl4RROUPnEjMNyndVFLEg==", encoded);
	}
	
	@Test
	public void testHmacChecker() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		PasswordChecker checker = new HmacChecker();
		String encoded = checker.encode("dufrene", "eservices");
		
		Assert.assertEquals("rs5jfYR3vJdNH5mX4X9APEuP1QM=", encoded);
	}

}
