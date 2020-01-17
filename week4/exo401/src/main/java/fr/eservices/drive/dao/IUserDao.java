package fr.eservices.drive.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import fr.eservices.drive.model.User;

public interface IUserDao {
	
	public User find( String login );
	
	public void save( User user );
	
	public boolean tryLogin( String login, String password ) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException;
	
	public void setPassword( String login, String password ) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException;

}
