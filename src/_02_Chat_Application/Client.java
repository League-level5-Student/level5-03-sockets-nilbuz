package _02_Chat_Application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client implements KeyListener {

	String ip;
	int port;
	Socket socket;
	DataOutputStream clientOS;
	DataInputStream clientIS;

	JPanel panel;
	JFrame frame;
	JButton send;
	JLabel label;
	JTextArea chatfield;
	JTextArea inputfield;

	public Client(String ip, int port) {

		this.ip = ip;
		this.port = port;

	}

	public void start() {

		frame = new JFrame();
		panel = new JPanel();
		send = new JButton();
		label = new JLabel();
		chatfield = new JTextArea(300, 300);
		inputfield = new JTextArea(300, 100);

		frame.setTitle("Client");
		send.setText("Send Message");

		send.addActionListener((ActionEvent e) -> {
			String msg = JOptionPane.showInputDialog("Type your message here.");
			sendMessage(msg);
		});

		chatfield.setEditable(false);
		frame.setTitle("Client");
		send.setText("Send Message");
		inputfield.setEditable(true);
		panel.setLayout(new GridLayout(3, 1));

		panel.add(chatfield);
//		panel.add(label);
		panel.add(inputfield);
		frame.add(panel);
		panel.add(send);
		panel.addKeyListener(this);
		frame.setBounds(100, 500, 300, 400);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

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
				chatfield.setText(chatfield.getText() + "\n" + readMessage());
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

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key presed");

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("enter pressed");
			sendMessage(inputfield.getText());
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

}
