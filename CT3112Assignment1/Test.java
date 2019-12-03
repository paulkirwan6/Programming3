/*
 * Paul Kirwan
 * 17321313
 */

// Driver for Employee hierarchy

// Java core packages
import java.text.DecimalFormat;

// Java extension packages
import javax.swing.JOptionPane;
import org.joda.time.LocalDate;

public class Test {

    // test Employee hierarchy
    public static void main(String args[]) {
        String output = "";
        
        //Get bonus cut-off date; 5 years ago from today's date
        LocalDate current = new LocalDate();
        LocalDate bonusCutOff = current.minusYears(5);
        int bonus;
        
        Boss boss = new Boss("John", "Smith", 800.0,"2010-08-23");
        
        CommissionWorker commissionWorker = new CommissionWorker("Sue", "Jones", 400.0, 3.0, 150,"2015-06-14");
        
        PieceWorker pieceWorker = new PieceWorker("Bob", "Lewis", 2.5, 100,"2016-07-10"); //Changed to be lower than min wage
        
        HourlyWorker hourlyWorker = new HourlyWorker("Karen", "Price", 9.5, 40,"2018-01-04"); //Changed to be lower than min wage
        
        DecimalFormat precision2 = new DecimalFormat("0.00");
        
        //Create Array containing the employees
        Employee[] employees = new Employee[4];
        employees[0] = boss;
        employees[1] = commissionWorker;
        employees[2] = pieceWorker;
        employees[3] = hourlyWorker;
        	
        //Calculate payroll in loop
        for (Employee e : employees) {
        	
        	//Add bonus to earnings if employee's joinDate is before the 5 year cut-off date
        	if (e.getJoinDate().isBefore(bonusCutOff)) {
        		bonus = 50;
        		output += "(Bonus Included) ";
        	}
        	else
        		bonus = 0;
        	//Calculate Payroll if above minimum wage, else exception is thrown and caught
        	try {
        		output += e.toString() + " earned $" + precision2.format(e.earnings() + bonus) + "\n";
        	}
        	catch (MinimumWageException exception) {
        		output += exception.toString();
        	}
        }
        //Display Payroll
        JOptionPane.showMessageDialog(null, output, "Payroll:", JOptionPane.INFORMATION_MESSAGE);
        
    }
} // end class Test
