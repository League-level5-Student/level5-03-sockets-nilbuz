package _02_Chat_Application;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import _01_Intro_To_Sockets.server.ServerGreeter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {

	Server server;
	Client client;
	int port;

	public ChatApp(int port) {

		this.port = port;
		int asdf = JOptionPane.showConfirmDialog(null, "Do you want to host a connection?", "Discord 2",
				JOptionPane.YES_NO_OPTION);

		if (asdf == JOptionPane.YES_OPTION) {

			server = new Server(21710);
			server.start();

		} else {

			Client client = new Client("localhost", 21710);
			client.start();

		}
	}

	public static void initializeGUI() {

	}

//	public static void initializeNetStuff() {
//		
//		Server server = new Server(21710);
//		Client client = new Client("localhost", 21710);
//
//		System.out.println("asdf");
//		
//		Thread serverThread = new Thread(()-> server.start());
//		serverThread.start();
//		System.out.println("asdf");
//		client.start();
//		System.out.println("asdf");
//		
//	}

	public static void main(String[] args) {

		ChatApp chatApp = new ChatApp(21710);
//		initializeNetStuff();

	}

}
