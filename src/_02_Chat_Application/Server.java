package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {

	int port;
	ServerSocket serverSocket;
	boolean running = true;

	public Server(int port) {

		this.port = port;

	}

	public void start() {

		while (running) {

			try {

				serverSocket = new ServerSocket(port);
				Socket clientSocket = serverSocket.accept();

				DataOutputStream clientOutputStream = new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream clientInputStream = new DataInputStream(clientSocket.getInputStream());

			} catch (SocketTimeoutException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
}
