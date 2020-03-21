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
	
	public Client(String ip, int port) {
		
		this.ip = ip;
		this.port = port;
		
	}
	
	public void start() {
		
		try {
			
			socket = new Socket(ip,port);
		
			DataOutputStream clientOutputStream = new DataOutputStream(socket.getOutputStream());
			DataInputStream clientInputStream = new DataInputStream(socket.getInputStream());
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
