/*
 * Paul Kirwan
 * 17321313
 */

// Boss class derived from Employee.

public final class Boss extends Employee {

    private double weeklySalary;

    // constructor for class Boss
    public Boss(String first, String last, double salary, String date) {
        super(first, last, date); // call superclass constructor
        setWeeklySalary(salary);
    }

    // set Boss's salary
    public void setWeeklySalary(double salary) {
        weeklySalary = (salary > 0 ? salary : 0);
    }

    // get Boss's pay
    public double earnings() throws MinimumWageException {
    	if (weeklySalary >= 40*10)			//Check earnings is above minimum wage
    		return weeklySalary;
    	//else throw exception
    	throw new MinimumWageException(getFirstName() +' ' + getLastName() + " (ID: "+ getId() + ") is making less than the minimum wage of $400 weekly.\n");	//else throw exception
    }

    // get String representation of Boss's name
    public String toString() {
        return "Boss: " + super.toString();
    }
} // end class Boss
