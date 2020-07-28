package email;

import java.util.Scanner;

public class EmailMain {

//showInfo() : shows info of user
//setAlternateEmail : allows you to set a secondary email
//changePassword : allows you to change the password
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner userinput = new Scanner(System.in);	
		
		System.out.println("Enter your first name: ");
		String fName = userinput.nextLine(); 
		System.out.println("Enter your last name: ");
		String lName = userinput.nextLine(); 
		
		Email name1 = new Email(fName.trim(), lName.trim());
		System.out.println(name1.showInfo());
	}
}
