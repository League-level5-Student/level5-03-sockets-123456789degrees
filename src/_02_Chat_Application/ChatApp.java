package _02_Chat_Application;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _00_Click_Chat.gui.ButtonClicker;


/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JTextField message = new JTextField(25);
	static JTextArea text = new JTextArea();
	JButton button = new JButton("Send");
	Server server;
	Client client; 
	public static void main(String[] args) {
		new ChatApp();
	}
	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "ChatApp", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			frame.setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				text.append("\nServer: " + message.getText());
				server.sendMessage(message.getText());
				message.setText("");
			});
			panel.setLayout(null);
			panel.add(text);
			panel.add(message);
			panel.add(button);
			message.setBounds(new Rectangle (0, 340, 420, 20));
			text.setBounds(new Rectangle(0, 0, 500, 330));
			button.setBounds(new Rectangle(430, 340, 60, 20));
			frame.add(panel);
			frame.setVisible(true);
			frame.setSize(500, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
		}
		else{
			frame.setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			button.addActionListener((e)->{
				text.append("\nClient: " + message.getText());
				client.sendMessage(message.getText());
				message.setText("");
			});
			panel.setLayout(null);
			panel.add(text);
			panel.add(message);
			panel.add(button);
			message.setBounds(new Rectangle (0, 340, 420, 20));
			text.setBounds(new Rectangle(0, 0, 500, 330));
			button.setBounds(new Rectangle(430, 340, 60, 20));
			frame.add(panel);
			frame.setVisible(true);
			frame.setSize(500, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}