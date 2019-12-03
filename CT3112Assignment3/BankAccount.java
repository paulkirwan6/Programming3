import java.io.Serializable;

/*
 * 	Paul Kirwan
 * 	17321313
 */

public class BankAccount implements Serializable {

	private String name;
	private int accNum;
	private double balance;
	private double overdraft = 0;
	private Transaction transaction;
	private String transactions = "";
	private static int bankCount = 1001; //declared static so it will not be reset to 1 for each instance created

	public BankAccount(String name, String date, double amount) {
		this.name = name;
		this.balance = amount;
		accNum = bankCount++;
		transaction = new Transaction(date, "Open Account", amount);	//"Open Account" Transaction object
		transactions += transaction;
	}

	//Create new Transaction object and increase the balance
	public void deposit(String date, double amount) {
		balance += amount;
		transaction = new Transaction(date, "Deposit", amount);
		transactions += transaction;
	}

	public void withdraw(String date, double amount) {
		//Do not allow a withdrawal if the amount exceeds the overdraft limit (0 in this case)
		if (amount > balance + overdraft) {
			System.out.println("Insufficient funds to withdraw " + amount + '\n');
		}
		//Create new Transaction object and decrease the balance
		else {
			balance -= amount;
			transaction = new Transaction(date, "Withdraw", amount);
			transactions += transaction;
		}
	}

	//Return current transaction
	public String getTransactionDetail() {
		return transaction.toString();
	}

	//Returns String of BankAccount variables and transactions for this account
	@Override
	public String toString() {
		return "Account Number: " + accNum + "\tName: " + name + "\tBalance: " + balance + '\n' + transactions;
	}
}
