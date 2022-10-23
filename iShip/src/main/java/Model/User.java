package Model;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	private int userId;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private long phoneNumber;
	private String email;
	private Date registrationDate;
	private String type;
	
	
	
	public User(String firstName, String lastName, int age,long phoneNumber, String email, String password) {
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		this.phoneNumber = phoneNumber;
		this.email=email;
		this.registrationDate = Date.valueOf(LocalDate.now());
		this.type = "User";
	}

	public String getName() {
		return this.firstName+" "+this.lastName;
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