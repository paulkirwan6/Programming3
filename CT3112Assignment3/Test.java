import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

/*
 * 	Paul Kirwan
 * 	17321313
 */

public class Test {

	//Serialize and save to file
	public static void serialize(Object obj, String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			fos.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	//Deserialize the file and store as Object in memory
	public static Object deserialize(String fileName) {
		Object obj = new Object();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close();
			fis.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
		return obj;
	}


	//Write to text file using RandomAccessFile
	public static void writeText(File f, long position, String text) {
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(f, "rw");
			raf.setLength(position);
			raf.seek(position);
			raf.writeBytes(text);
			raf.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Read from text file using RandomAccessFile
	public static String readText(File f) {
		RandomAccessFile raf;
		String output = "";
		try {
			raf = new RandomAccessFile(f, "rw");
			raf.seek(0);
			output = raf.readLine();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static void main(String[] args) {

		//Start of task 1
		System.out.println("\nTask 1:\n");
		
		//Create array of transactions
		Transaction[] transactions = new Transaction[3];
		
		transactions[0] = new Transaction("22/08/2019", "Withdraw", 200);
		transactions[1] = new Transaction("23/08/2019", "Deposit", 100);
		transactions[2] = new Transaction("01/09/2019", "Withdraw", 50);

		//serialize the array and save it to a file named “transactions.bin”
		serialize(transactions, "transactions.bin");

		//deserialize the file and add the contents to the an array
		Transaction[] importedTransactions = new Transaction[3];
		importedTransactions = (Transaction[]) deserialize("transactions.bin");

		//Print the imported Transaction Array
		for (Transaction transaction: importedTransactions) {
			System.out.println(transaction);
		}

		//Start of task 2
		System.out.println("\nTask 2:\n");
		
		//2. Create Bank Account
		BankAccount account = new BankAccount("Lucas Ness", "16/08/2017", 100);
		account.withdraw("22/08/2019", 200);
		account.deposit("22/08/2019", 100);
		account.withdraw("22/08/2019", 50);

		//serialize the array and save it to a file named “accountdetails.bin”
		serialize(account, "accountdetails.bin");

		//deserialize the file and print to console
		BankAccount importedAccount = (BankAccount) deserialize("accountdetails.bin");
		System.out.println(importedAccount);

		//Start of task 3
		System.out.println("\nTask 3:\n");
		
		//Write the text file
		File f = new File("overdraft.txt");
		writeText(f, 0, "Would you like to increase your overdraft? Please type Yes/No at the end of the line.");

		//Read the new file
		System.out.println(readText(f));

		//Read user input using Scanner 
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();

		if (userInput.toLowerCase().equals("yes") || (userInput.toLowerCase().equals("no"))){
			//Write to the text file
			writeText(f, f.length(), userInput);
			
			//Read the appended file
			System.out.println("Appended file: " + readText(f));
		}
		else { //Incorrect user input
			System.out.println("Incorrect input. Please enter 'yes' or 'no'.");
		}
	}
}