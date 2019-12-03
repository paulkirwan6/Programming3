/*
 * Paul Kirwan
 * 17321313
 */

// PieceWorker class derived from Employee
public final class PieceWorker extends Employee {

    private double wagePerPiece; // wage per piece output
    private int quantity; // output for week

    // constructor for class PieceWorker
    public PieceWorker(String first, String last, double wage, int numberOfItems, String date) {
        super(first, last, date); // call superclass constructor
        setWage(wage);
        setQuantity(numberOfItems);
    }

    // set PieceWorker's wage
    public void setWage(double wage) {
        wagePerPiece = (wage > 0 ? wage : 0);
    }

    // set number of items output
    public void setQuantity(int numberOfItems) {
        quantity = (numberOfItems > 0 ? numberOfItems : 0);
    }

    // determine PieceWorker's earnings
    public double earnings() throws MinimumWageException{
    	if (quantity*wagePerPiece >= 40*10)
    		return quantity * wagePerPiece;
    	//else throw exception
    	throw new MinimumWageException(getFirstName() +' ' + getLastName() + " (ID: "+ getId() + ") is making less than minimum wage ($400 weekly)\n");
    }

    public String toString() {
        return "Piece worker: " + super.toString();
    }
}
