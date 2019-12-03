/*
 * Paul Kirwan
 * 17321313
 */

// Abstract base class Employee.
import org.joda.time.LocalDateTime;

public abstract class Employee {

	private String firstName;
	private String lastName;
	private LocalDateTime joinDate;
	private LocalDateTime current;
	private static int idCounter = 1;	//declared static so it will not be reset to 1 for each instance created
	private int id;

	// constructor
	public Employee(String first, String last, int year, int month, int date, int hour, int minute) throws InvalidDateException {
		firstName = first;
		lastName = last;

		id = idCounter++;	//Increments by 1 each time a new instance is created

		//create object and check it is valid
		joinDate = createDate(year,month,date,hour,minute);
	}

	// get first name
	public String getFirstName() {
		return firstName;
	}

	// get last name
	public String getLastName() {
		return lastName;
	}

	//Modified to include join date formatted in a way that is easier to read
	public String toString() {
		return firstName + ' ' + lastName + " (Join Date: " + joinDate.getYear()+'-'+joinDate.getMonthOfYear()+'-'+joinDate.getDayOfMonth() +" at "+joinDate.getHourOfDay()+':'+joinDate.getMinuteOfHour()+')' ;
	}

	public int getId() {
		return id;
	}

	//method that tests each case for Invalid Dates and then creates the object
	//Throws InvalidDateException if unsuccessful
	public LocalDateTime createDate(int year, int month, int date, int hour, int minute) throws InvalidDateException{
		//if the year is before 1990
		if (year < 1990)
			throw new InvalidDateException(firstName+' '+lastName +"'s join year "+year+" is invalid.\nReason: Join year is before 1990.\n");

		//month is not in the correct range
		else if ((month > 12) || (month < 1))
			throw new InvalidDateException(firstName+' '+lastName +"'s join month index "+month+" is invalid.\nReason: Month must be between 1-12.\n");

		//Day does not exist for that specific month (Includes special case for leap year)
		else if (date > 31 || date < 1 || (month == 4 || month == 6 || month == 9 || month == 11) && (date >30) || (month == 2 && date > 29) ||(month == 2 && date > 28 && year % 4 != 0))
			throw new InvalidDateException(firstName+' '+lastName +"'s join date "+date+'-'+month+'-'+year+" is invalid.\nReason: This date does not exist for this month.\n");

		//Outside of 9:00 - 18:00
		else if (hour < 9 || hour >= 18)
			throw new InvalidDateException(firstName+' '+lastName +"'s join time "+hour+':'+minute+" is invalid.\nReason: Time must be between 9:00 - 18:00.\n");

		//Last 2 checks require methods in the LocalDateTime class
		current = new LocalDateTime();
		joinDate = new LocalDateTime(year,month,date,hour,minute);

		//if the join date is in the future
		if (current.isBefore(joinDate))				
			throw new InvalidDateException(firstName+' '+lastName +"'s join date "+year+'-'+month+'-'+date+" is invalid.\nReason: Join date is in the future.\n");

		//Check if join date is on the weekend
		else if (joinDate.getDayOfWeek()>5)
			throw new InvalidDateException(firstName+' '+lastName +"'s join day "+joinDate.getDayOfWeek()+" is invalid.\nReason: Must not be a weekend\n");

		return joinDate;
	}

	public abstract double earnings();
}
