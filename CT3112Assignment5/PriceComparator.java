import java.util.Comparator;

/*
 * 	Paul Kirwan
 *	17321313
 */

//Compares the price of two Inventory objects
public class PriceComparator implements Comparator<Inventory> {
	
	@Override
	public int compare(Inventory i1, Inventory i2) {
		
		if (i1.getPrice()*i1.getQuantity() > i2.getPrice()*i2.getQuantity())
			return 1;
		if (i1.getPrice()*i2.getQuantity() == i2.getPrice()*i2.getQuantity())
			return 0;
		//else
		return -1;
	}
}