package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	String ip;
	int port;
	Socket socket;
	DataOutputStream clientOS;
	DataInputStream clientIS;

	public Client(String ip, int port) {

		this.ip = ip;
		this.port = port;

	}

	public void start() {

		try {
			socket = new Socket(ip, port);
			clientOS = new DataOutputStream(socket.getOutputStream());
			clientIS = new DataInputStream(socket.getInputStream());
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {

			System.out.println(clientIS.readUTF());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void sendMessage(String message) {

		try {
			clientOS.writeUTF(message);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
