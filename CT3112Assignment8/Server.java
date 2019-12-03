import java.io.*;
import java.net.*;

/*
 * Paul Kirwan
 * 17321313
 */

public class Server extends Thread {

	//Start server
	@SuppressWarnings("resource")
	public static void main(String args[]) {

		// server is listening on port 4400 
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(4400);
			ss.setSoTimeout(0);
		} catch (IOException e) {
			e.printStackTrace();
		} 

		//Run an infinite loop for getting client requests
		System.out.println("Waiting for client connection...");
		while (true)  
		{
			Socket s = null;
			try {
				// socket object to receive incoming client requests 
				s = ss.accept();

				System.out.println("A new client is connected : " + s); 

				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

				System.out.println("Assigning new thread for this client"); 

				//Create and run ClientHandler Thread 
				Thread t = new ClientHandler(s, dis, dos);
				t.start();
			} 
			catch (Exception e) {
				e.printStackTrace(); 
			} 
		}
	}
}