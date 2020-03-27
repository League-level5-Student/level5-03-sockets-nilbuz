package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {

	int port;
	ServerSocket serverSocket;
	Socket clientSocket;
	boolean running;
	DataOutputStream serverOS;
	DataInputStream serverIS;

	public Server(int port) {

		this.port = port;
		running = true;

	}

	public void start() {
		
		while (running) {

			try {
				
				serverSocket = new ServerSocket(port);
				serverOS = new DataOutputStream(clientSocket.getOutputStream());
				serverIS = new DataInputStream(clientSocket.getInputStream());
				clientSocket = serverSocket.accept();
				serverOS.writeUTF("hi client");

//				clientSocket.close();

			} catch (SocketTimeoutException e) {
				e.printStackTrace();
				System.out.println("timed out");
				running = false;

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("IO exception");
				running = false;

			}
		}
	}
	
	public void sendMessage(String message) {
		
		try {
			serverOS.writeUTF(message);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
