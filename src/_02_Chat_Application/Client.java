package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				String message = (String) is.readObject();
				ChatApp.text.append("\nServer: " + message);
			//	JOptionPane.showMessageDialog(null, is.readObject());
			//	System.out.println(is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String message) {
		try {
			if (os != null) {
				os.writeObject(message);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
