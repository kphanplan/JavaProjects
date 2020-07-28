package email;

import java.util.Scanner;

public class Email {

	// ATTRIBUTES//
	String firstName;
	String lastName;
	String password;
	String department;
	String email;
	String company = "kpcompany.com";
	private int mailboxCap = 500;
	private int passwordLength = 10;
	String altEmail;

	// constructor for first and last name
	public Email(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = setDepartment();
		this.password = randomPassword(passwordLength);
		this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + company;
	}

	// METHODS//

	// Ask for department
	private String setDepartment() {
		System.out.println("DEPARTMENT CODES: \n1 for Sales\n2 For Development\n3 for Accounting\n0 for none");
		System.out.println("SELECT YOUR DEPARTMENT: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int dept = input.nextInt();
		if (dept == 1) {
			return "Sales";
		}
		if (dept == 2) {
			return "Development";
		}
		if (dept == 3) {
			return "Accounting";
		} else {
			return "";
		}
	}

	// Generate random password
	private String randomPassword(int length) {
		// creates the set of characters it can pick from
		String passwordSet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$%";
		// collect characters into array
		char[] password = new char[length];
		// loop thru length amount of times and generate random number from set
		// remember, math.random generates a decimal < 1
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
		}
//	!!!YOU CAN ACTUALLY GENERATE A STRING FROM CHAR LIKE THIS!!!
		return new String(password);
	}

	// Set mailbox capacity
	public void setMailboxCapacity(int capacity) {
		this.mailboxCap = capacity;
	}

	// Set alternate email
	public void setAlternateEmail(String altEmail) {
		this.altEmail = altEmail;
	}

	// Change password
	public void changePassword(String password) {
		this.password = password;
	}

	// GETTERS

	public int getMailboxCapacity() {
		return mailboxCap;
	}
	public String getAlternateEmail() {
		return altEmail;
	}
	public String getPassword() {
		return password;
	}
	
	public String showInfo() {
		return "DISPLAY NAME: " + firstName + " " + lastName + 
				"\rCOMPANY EMAIL: " + email + 
				"\rMAILBOX CAPACITY: " + mailboxCap + "mb" +
				"\rPASSWORD: " + password + 
				"\rWELCOME TO THE " + company + " FAMILY! :]";
	}

}
