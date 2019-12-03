/*
 * Paul Kirwan
 * 17321313
 */

// Definition of class HourlyWorker

public final class HourlyWorker extends Employee {

    private double wage; // wage per hour
    private double hours; // hours worked for week

    // constructor for class HourlyWorker
    public HourlyWorker(String first, String last, double wagePerHour, double hoursWorked, String date) {
        super(first, last, date); // call superclass constructor
        setWage(wagePerHour);
        setHours(hoursWorked);
    }

    // Set the wage
    public void setWage(double wagePerHour) {
        wage = (wagePerHour > 0 ? wagePerHour : 0);
    }

    // Set the hours worked
    public void setHours(double hoursWorked) {
        hours = (hoursWorked >= 0 && hoursWorked < 168
                ? hoursWorked : 0);
    }

    // Get the HourlyWorker's pay
    public double earnings() throws MinimumWageException{
    	if (wage >= 10)
    		return wage * hours;
    	//else throw exception
    	throw new MinimumWageException(getFirstName() +' ' + getLastName() + " (ID: "+ getId() + ") is making less than minimum wage ($400 weekly)\n");
    }

    public String toString() {
        return "Hourly worker: " + super.toString();
    }
}
