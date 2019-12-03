import java.io.Serializable;

/*
 * 	Paul Kirwan
 * 	17321313
 */

public class Transaction implements Serializable {

	private static int transactionCount = 1; //declared static so it will not be reset to 1 for each instance created
	private int transactionNum = 0;
	private String date;
	private String type;
	private double amount;

	public Transaction(String date, String type, double amount) {
		this.date = date;
		this.type = type;
		this.amount = amount;
		transactionNum = transactionCount++;
	}

	//Returns String of Transaction details
	@Override
	public String toString() {
		return transactionNum + ") " + date + ' ' + type + ' ' + amount +'\n';
	}
}
