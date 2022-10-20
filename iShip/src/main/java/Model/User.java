package Model;

import java.sql.Date;
import java.time.LocalDate;

public class User {
	
	private int userId;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private long phoneNumber;
	private String email;
	private Date registrationDate;
	
	
	public User(String firstName, String lastName, int age,long phoneNumber, String email, String password) {
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		this.phoneNumber = phoneNumber;
		this.email=email;
		this.registrationDate = Date.valueOf(LocalDate.now());
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getId() {
		return userId;
	}
	public void setId(int id) {
		this.userId = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean checkIfValidPass() {
		int countUpperCase = 0;
		int countNumber = 0;
		char[] passwordArray = this.password.toCharArray();
		if(passwordArray.length >= 8) {
			for(int i = 0; i < passwordArray.length;i++) {
				if(Character.isUpperCase(passwordArray[i])) 
					countUpperCase++;
				if(Character.isDigit(passwordArray[i]))
					countNumber++;
			}
			if(countNumber > 0 && countUpperCase >0) {
				return true;
			}
		}
		
		return false;
	}
}

