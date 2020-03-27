package _02_Chat_Application;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {

	public static void initializeGUI() {
		
		JFrame frame = new JFrame();
//		JPanel panel = new JPanel();
		JButton send = new JButton();
		
		
		frame.setTitle("poop");
		send.setText("asdf");
		
//		panel.add(send);
//		frame.add(panel);
		frame.add(send);
		frame.pack();
		frame.setVisible(true);
		
		send.addActionListener((ActionEvent e)-> {
			
			System.out.println("asdf");
			
		});
		
	}
	

	
	public static void initializeNetStuff() {
		
		Server server = new Server(21710);
		Client client = new Client("localhost", 21710);

		System.out.println("asdf");
		
		Thread serverThread = new Thread(()-> server.start());
		serverThread.start();
		System.out.println("asdf");
		client.start();
		System.out.println("asdf");
		
	}
	
	public static void main(String[] args) {

		initializeGUI();
		
		
	}

}
