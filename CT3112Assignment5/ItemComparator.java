import java.util.Comparator;

/*
 * 	Paul Kirwan
 *	17321313
 */

//Compares the name of two Inventory objects
public class ItemComparator implements Comparator<Inventory> {
	
	@Override
	public int compare(Inventory i1, Inventory i2) {
		
		return(i1.getName().compareTo(i2.getName()));
	}
}
