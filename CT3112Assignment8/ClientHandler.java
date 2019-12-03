import java.net.*; 
import java.io.*; 

/*
 * Paul Kirwan
 * 17321313
 */

public class ClientHandler extends Thread
{ 
	private final DataInputStream dis; 
	private final DataOutputStream dos; 
	private final Socket s;
	private int bytesRead;

	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
	} 

	@Override
	public void run()  
	{
		try {
			// receive the command and file name from client 
			String received = dis.readUTF();
			String fileName = dis.readUTF();

			System.out.println("received: " + received);

			//download operation
			if (received.equals("-d")) {
				System.out.println("Sending file " + fileName + " to client...");
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

					System.out.println("File sent.");
				}
				//Error handling
				catch (IOException e) {
					System.out.println("Error. File " + fileName + " not found on server.");
				}
			}
			//Upload operation
			else if (received.equals("-u")) {
				System.out.println("Receiving file " + fileName + " from client...");
				InputStream in = s.getInputStream();

				// Writing the file to disk
				// Instantiating a new output stream object

				FileOutputStream output = new FileOutputStream(fileName);
				byte[] buffer = new byte[1024];

				try {
					while ((bytesRead = in.read(buffer)) != -1) {
						output.write(buffer, 0, bytesRead);
					}
				}
				catch (SocketException e) {
					//If connection is lost, allow the program to finish so the server stays active
				}

				// Closing the FileOutputStream handle
				output.close();

				System.out.println("File received.");
			}

			else {
				System.out.println("Invalid command entered.");
			}
			s.close();
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		} 

	} 
} 