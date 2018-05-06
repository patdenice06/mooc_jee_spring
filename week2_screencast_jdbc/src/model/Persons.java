package model;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;

/**
 * Bean object for users.persons table
 */
public class Persons {

	private Long id;	// good practise for the use of wrapper Long in case of SQL NULL value.
	private Timestamp registerDate;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	

	// password should not be in this object ...
	// private String lastName;
	
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Timestamp getRegisterDate() {
		return registerDate;
	}	
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public LocalDate getBirthday() { return birthday; }
	public void setBirthday(LocalDate localDate) { this.birthday = localDate; }

	public int getAge() { 
		if ( birthday == null ) return -1;
		
		Date input = new Date();
		LocalDate today = 
			input.toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		
//		LocalDate birthDate = 
//			birthday.toInstant()	// Remove because always throws UnsupportedOperationException
//			new java.util.Date(birthday.getTime()).toInstant()	
//			.atZone(ZoneId.systemDefault())
//			.toLocalDate();
		
//		LocalDate birthDate =
//				birthday.at
		
			
		
		return 
			Period.between( birthday, today )
			.getYears();
	}
		
}