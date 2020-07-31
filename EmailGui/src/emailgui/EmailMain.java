package emailgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailMain implements ActionListener {

	// ATTRIBUTES
	JButton submit;
	JTextArea results;
	JLabel title, l1, l2, l3;
	JTextField t1, t2, t3;

	// GENERATE A RANDOM PASSWORD
	static String password;

	private static String randomPassword(int length) {
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

	public EmailMain() {

		// Building the GUI
		JFrame f = new JFrame("KP Bored'n Chafed Bank");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		title = new JLabel("GENERATE EMAIL & PASSWORD");
		title.setBounds(40, 20, 200, 30);

		l1 = new JLabel("First Name: ");
		l1.setBounds(40, 50, 100, 30);
		t1 = new JTextField();
		t1.setBounds(40, 75, 200, 30);

		l2 = new JLabel("Last Name: ");
		l2.setBounds(40, 100, 100, 30);
		t2 = new JTextField();
		t2.setBounds(40, 125, 200, 30);

		l3 = new JLabel("Department: ");
		l3.setBounds(40, 150, 100, 30);
		t3 = new JTextField();
		t3.setBounds(40, 175, 200, 30);

		submit = new JButton("Submit");
		submit.setBounds(40, 225, 100, 30);

		results = new JTextArea("Enter your credentials above");
		results.setEditable(false);
		results.setBounds(40, 275, 300, 150);

		f.add(title);

		f.add(l1);
		f.add(l2);
		f.add(l3);

		f.add(t1);
		f.add(t2);
		f.add(t3);

		submit.addActionListener(this);
		f.add(submit);
		f.add(results);

		f.setSize(400, 600);
		f.setLayout(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new EmailMain();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s1 = t1.getText();
		String s2 = t2.getText();
		String s3 = t3.getText();

		String email = s1.toLowerCase().trim() + "." + s2.toLowerCase().trim() + "@" + s3.toLowerCase().trim() + ".kpbank.com";
		String em = email.replaceAll("\\s+","");
		
		if (e.getSource() == submit) {		
			String holder = randomPassword(10);
		password = holder;
			results.setText("WELCOME TO KPBANCK\r\n" + "\r\nFirst Name:  " + s1 + "\r\nLast Name:  " + s2
					+ "\r\nDepartment:  " + s3 + "\r\n" + "\r\nCompany Email: \r\n" + em + "\r\nPassword:  " + password);
		}
	}
}
