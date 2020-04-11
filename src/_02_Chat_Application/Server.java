package _02_Chat_Application;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;

public class Server implements KeyListener {

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
	JTextField chatfield;
	JTextField inputfield;

	public Server(int port) {

		this.port = port;
		running = true;

	}

	public void start() {

		frame = new JFrame();
		panel = new JPanel();
		send = new JButton();
		label = new JLabel();
		chatfield = new JTextField(200);
		inputfield = new JTextField(200);
		
//		BorderLayout layout = new BorderLayout();
//		panel.setLayout(layout);
		

		send.addActionListener((ActionEvent e) -> {
			String msg = JOptionPane.showInputDialog("Type your message here.");
			sendMessage(msg);
		});

		chatfield.setEditable(false);
		frame.setTitle("Server");
		send.setText("Send Message");
		inputfield.setEditable(true);
		panel.setLayout(new GridLayout(4,1));
		
		
		panel.add(chatfield);
//		panel.add(label);
		panel.add(inputfield);
		frame.add(panel);
		panel.add(send);
		panel.addKeyListener(this);
		frame.setBounds(100, 300, 300, 150);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		run();
	}

	public void run() {

		try {
			System.out.println("1");

			serverSocket = new ServerSocket(port);
			System.out.println("2");
			connection = serverSocket.accept();
			System.out.println("3");
			serverOS = new DataOutputStream(connection.getOutputStream());
			System.out.println("4");
			serverIS = new DataInputStream(connection.getInputStream());
			System.out.println("5");
		
			System.out.println(serverIS.readUTF());
			
			while (running) {

				System.out.println("server: msg recieved");
//				label.setText(readMessage());
				
				chatfield.setText(label.getText() + "\n" + readMessage());
				

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
