package _02_Chat_Application;

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

	public Client(String ip, int port) {

		this.ip = ip;
		this.port = port;

	}

	public void start() {

		panel = new JPanel();
		frame = new JFrame();
		send = new JButton();
		label = new JLabel();

		frame.setTitle("Client");
		send.setText("Send Message");

		send.addActionListener((ActionEvent e) -> {
			String msg = JOptionPane.showInputDialog("Type your message here.");

		});

		panel.add(send);
		frame.add(panel);
		frame.setBounds(300, 500, 300, 100);
		frame.setVisible(true);
		run();
	}

	public void run() {

		try {
			socket = new Socket(ip, port);
			clientOS = new DataOutputStream(socket.getOutputStream());
			clientIS = new DataInputStream(socket.getInputStream());

			clientOS.writeUTF("connected");
			System.out.println(clientIS.readUTF());

			while (socket.isConnected()) {

				label.setText(readMessage());
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
