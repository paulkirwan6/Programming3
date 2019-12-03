/*
 * Paul Kirwan
 * 17321313
 */

// Abstract base class Employee.
import org.joda.time.LocalDate;

public abstract class Employee {

    private String firstName;
    private String lastName;
    private LocalDate joinDate;
    private static int idCounter = 1;	//declared static so it will not be reset to 1 for each instance created
    private int id;

    // constructor
    public Employee(String first, String last, String date) {
        firstName = first;
        lastName = last;
        joinDate = new LocalDate(date);
        id = idCounter++;						//Increments by 1 each time a new instance is created
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }

    // get last name
    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return firstName + ' ' + lastName + " (ID: " + id + ')';
    }
    
    public int getId() {
    	return id;
    }
    
    public LocalDate getJoinDate() {
    	return joinDate;
    }
    
    public void setJoinDate(String date) {
    	joinDate = new LocalDate(date);
    }

    public abstract double earnings() throws MinimumWageException;
}
