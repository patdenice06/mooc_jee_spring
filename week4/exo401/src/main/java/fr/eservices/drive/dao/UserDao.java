package fr.eservices.drive.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eservices.drive.model.User;
import fr.eservices.drive.util.PasswordChecker;

@Component
public abstract class UserDao implements IUserDao {
	
	@Autowired
	PasswordChecker pwdCheck;
	
	public void setPwdCheck(PasswordChecker pwdCheck) {
		this.pwdCheck = pwdCheck;
	}
	
	protected abstract void doChangePassword(String login, String password);
	
	@Override
	public void setPassword(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		doChangePassword( login, pwdCheck.encode(login, password) );
	}
	
	@Override
	public boolean tryLogin(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		User u = find(login);
		String pwd = u.getPassword();
		if ( pwd == null ) return false;
		return pwd.equals( pwdCheck.encode(login, password) );
	}
	

}
