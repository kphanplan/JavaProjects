package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiPractice implements ActionListener {
	
	int counter = 0; 
	private JLabel label; 
	private JFrame frame;
	private JPanel panel;
	//constructor
	public GuiPractice() {
		
		//this is how you set up a window
		JFrame frame = new JFrame();
		
		JButton button = new JButton("HEY, CLICK ON ME!");
		button.addActionListener(this);
		
		label = new JLabel("Number of clicks: ");
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI PRACTICE");
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GuiPractice ();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		counter +=1; 
		label.setText("Number of Clicks: " + counter);
	}

}
