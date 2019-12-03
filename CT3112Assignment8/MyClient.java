import java.io.*;
import java.net.*;

/*
 * Paul Kirwan
 * 17321313
 */

public class MyClient {

	//Start Client
	public static void main(String args[]) {

		//Ensure command has been entered correctly
		if (args.length != 4) {
			System.out.println("Invalid arguments.\nEnter using the format: (java MyClient <host> <port> <-u / -d> <file-name>)"
					+ "\nExample: (java MyClient 127.0.0.1 4400 -d data.txt) is valid.");
			System.exit(0);
		}

		//Get fields from terminal 
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String command = args[2];
		String fileName = (args[3]);
		System.out.println("Command entered: java MyClient "+ host +' '+ port+ ' ' + command +' '+ fileName);

		Socket s;
		try {
			// establish the connection with server
			System.out.println("Connecting to server...");
			s = new Socket(host, port); 

			// obtaining input and out streams 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

			// Exchange all information between client and client handler
			System.out.println("Connected.");

			//Write -u/-d and the file name to dos
			dos.writeUTF(command);
			dos.writeUTF(fileName);

			if (command.equals("-u")) {
				System.out.println("Uploading " + fileName +" to server...");

				//Send file name to server
				//Send file to server
				File myFile = new File(fileName);
				byte[] mybytearray = new byte[(int) myFile.length()];

				try {
					FileInputStream fis = new FileInputStream(myFile);
					BufferedInputStream bis = new BufferedInputStream(fis);
					bis.read(mybytearray, 0, mybytearray.length);
					OutputStream os = s.getOutputStream();
					os.write(mybytearray, 0, mybytearray.length);

					//Close handles
					os.flush();
					bis.close();

					System.out.println("File upload successful!");
				}
				//Error handling
				catch (IOException e) {
					System.out.println("File "+ fileName + " not found.");
				}
			}

			else if (command.equals("-d")) {

				System.out.println("Downloading " + fileName + " from server...");

				InputStream in = s.getInputStream();

				// Writing the file to disk
				// Instantiating a new output stream object
				FileOutputStream output = new FileOutputStream(fileName);
				byte[] buffer = new byte[1024];
				try {
					int bytesRead;
					while ((bytesRead = in.read(buffer)) != -1) {
						output.write(buffer, 0, bytesRead);
					}
				}
				catch (SocketException e) {
					//If connection is lost, allow the program to finish so another client can be connected and the server stays up
				}

				// Closing the FileOutputStream handle
				output.close();

				System.out.println("File download successful!");
			}
			//Does not accept a command other than <-u> or <-d>
			else {
				System.out.println("Invalid command. Use <-d> to download or <-u> to upload.");
			}

		}
		catch(Exception e){
			e.printStackTrace(); 
		}

	}
}
