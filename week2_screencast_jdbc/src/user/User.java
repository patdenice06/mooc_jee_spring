package user;

import java.time.*;
import java.util.Date;

public class User {

	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private Date birthday;
	

	// password should not be in this object ...
	// private String lastName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public Date getBirthday() { return birthday; }
	public void setBirthday(Date birthday) { this.birthday = birthday; }

	public int getAge() { 
		if ( birthday == null ) return -1;
		
		Date input = new Date();
		LocalDate today = 
			input.toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		
		LocalDate birthDate = 
//			birthday.toInstant()	// Remove because always throws UnsupportedOperationException
			new java.util.Date(birthday.getTime()).toInstant()	
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		
		return 
			Period.between( birthDate, today )
			.getYears();
	}
		
}