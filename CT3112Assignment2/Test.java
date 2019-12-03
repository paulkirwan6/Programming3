/*
 * Paul Kirwan
 * 17321313
 */

// Driver for Employee hierarchy

// Java core packages
import java.text.DecimalFormat;

// Java extension packages
import javax.swing.JOptionPane;

public class Test {

	// test Employee hierarchy
	public static void main(String args[]) {
		String output = "";

		//Create array of employees
		Employee[] employees = new Employee[4];

		try {
			employees[0] = new Boss("John", "Smith", 800.0, 1985, 6, 30, 13, 12 );
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {																//year, month, day, hour, minute
			employees[1] = new CommissionWorker("Sue", "Jones", 400.0, 3.0, 150, 2020, 8, 4, 10, 23);
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		//This is the only time an exception isn't thrown, as it is a valid date/time (leap year occured in 1996)
		try {													//year, month, day, hour, minute
			employees[2] = new PieceWorker("Bob", "Lewis", 2.5, 200, 1996, 2, 29, 9, 35 );
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {														//year, month, day, hour, minute
			employees[3] = new HourlyWorker("Karen", "Price", 11.5, 40, 2010, 13, 9, 14, 45);
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {											//year, month, day, hour, minute
			employees[4] = new Boss("James", "Smyth", 800.0, 2008, 10, 27, 8, 33 );
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {																//year, month, day, hour, minute
			employees[5] = new CommissionWorker("Sarah", "Jones", 400.0, 3.0, 150, 2003, 1, 18, 11, 14 );
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {													//year, month, day, hour, minute
			employees[6] = new PieceWorker("Bill", "Lewis", 2.5, 200, 1999, 2, 30, 16, 11 );
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {														//year, month, day, hour, minute
			employees[7] = new HourlyWorker("Ciara", "Price", 11.5, 40, 2015, 7, 8, 18, 37 );
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {											//year, month, day, hour, minute
			employees[8] = new Boss("Derek", "Smith", 800.0, 1989, 12, 12, 19, 25);
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		try {																//year, month, day, hour, minute
			employees[9] = new CommissionWorker("Susan", "Johnson", 400.0, 3.0, 150, 2019, 2, 29, 3, 40);
		} catch (InvalidDateException e) {
			output += e.toString() + '\n';
		}

		DecimalFormat precision2 = new DecimalFormat("0.00");

		//Calculate payroll in loop
		for (Employee e : employees) {
			if (e != null)
				output += e.toString() + " earned $" + precision2.format(e.earnings()) + "\n";
		}
		//Display Payroll
		JOptionPane.showMessageDialog(null, output, "Payroll:", JOptionPane.INFORMATION_MESSAGE);

	}
} // end class Test
