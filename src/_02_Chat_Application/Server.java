package _02_Chat_Application;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Server {

	int port;
	ServerSocket serverSocket;
	Socket connection;
	boolean running;
	DataOutputStream serverOS;
	DataInputStream serverIS;

	JFrame frame;
	JPanel panel;
	JButton send;
	JLabel label;

	public Server(int port) {

		this.port = port;
		running = true;

	}

	public void start() {

		frame = new JFrame();
		panel = new JPanel();
		send = new JButton();
		label = new JLabel();

		frame.setTitle("Server");
		send.setText("Send Message");

		send.addActionListener((ActionEvent e) -> {
			String msg = JOptionPane.showInputDialog("Type your message here.");
			sendMessage(msg);
		});

		panel.add(label);
		panel.add(send);
		frame.add(panel);
		frame.setBounds(700, 500, 300, 100);
		frame.setVisible(true);
		run();
	}

	public void run() {

		try {
			System.out.println(port);

			serverSocket = new ServerSocket(port);
			System.out.println("2");
			connection = serverSocket.accept();
			System.out.println("3");
			serverOS = new DataOutputStream(connection.getOutputStream());
			System.out.println("4");
			serverIS = new DataInputStream(connection.getInputStream());
			System.out.println("5");
			serverOS.writeUTF("asdf");
			System.out.println(serverIS.readUTF());

			while (running) {

				label.setText(readMessage());

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void sendMessage(String message) {

		try {

			serverOS.writeUTF(message);
			serverOS.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String readMessage() {

		try {
			return serverIS.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "no messages";
	}
}
