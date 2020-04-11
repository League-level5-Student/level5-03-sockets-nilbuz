package _02_Chat_Application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {

	String ip;
	int port;
	Socket socket;
	DataOutputStream clientOS;
	DataInputStream clientIS;

	JPanel panel;
	JFrame frame;
	JButton send;
	JLabel label;
	JTextField chatfield;
	JTextField inputfield;
	
	public Client(String ip, int port) {

		this.ip = ip;
		this.port = port;

	}

	public void start() {

		panel = new JPanel();
		frame = new JFrame();
		send = new JButton();
		label = new JLabel();
		chatfield = new JTextField(200);
		inputfield = new JTextField(200);
		
		frame.setTitle("Client");
		send.setText("Send Message");

		send.addActionListener((ActionEvent e) -> {
			String msg = JOptionPane.showInputDialog("Type your message here.");
			sendMessage(msg);
		});

		panel.add(send);
//		panel.add(label);
		panel.add(chatfield);
		panel.add(inputfield);
		frame.add(panel);
		frame.setBounds(100, 600, 300, 150);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		
		run();
	}

	public void run() {

		try {
			socket = new Socket(ip, port);
			clientOS = new DataOutputStream(socket.getOutputStream());
			clientIS = new DataInputStream(socket.getInputStream());

			clientOS.writeUTF("client connected");
			System.out.println(clientIS.readUTF());

			System.out.println("asdfasdfasdfasdfdsddfsfadasfdsfd");

			while (socket.isConnected()) {
				System.out.println("client: msg recieved");
				chatfield.setText(label.getText() + "\n" + readMessage());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void sendMessage(String message) {

		try {
			clientOS.writeUTF(message);
			clientOS.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String readMessage() {

		try {
			return clientIS.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "no messages";
	}

}
