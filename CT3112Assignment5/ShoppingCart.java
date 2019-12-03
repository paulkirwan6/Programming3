import java.text.DecimalFormat;
import java.util.*;

/*
 * 	Paul Kirwan
 *	17321313
 */

public class ShoppingCart {
	private String customerName;
	private String date;
	private List<Inventory> cart;
	private DecimalFormat precision2 = new DecimalFormat("0.00");	//Display to 2 decimal places

	public ShoppingCart(String customerName, String date) {
		this.customerName = customerName;
		this.date = date;
		cart = new ArrayList<Inventory>();
	}

	//Return list of items
	public List<Inventory> getItems() {
		return cart;
	}

	//Calculate total cost in the cart
	public double getTotal() {
		double total = 0;
		for (Inventory item : cart) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}

	//Add item from the inventory list to cart
	public void addItem(String itemName, int quantity) {

		//Make sure user input has a positive quantity
		if (quantity <= 0) {
			System.out.println("Must add a quantity greater than 0!");
			return;
		}

		//Find Inventory object with the same name
		Inventory itemInInventory = Inventory.searchInventory(itemName);
		int amountInInventory = itemInInventory.getQuantity();

		//Check if there is enough in inventory, if not, add the amount in the inventory and reduce the inventory quantity to 0.
		if (amountInInventory <= 0)
			System.out.println("There is no " + itemName + "left in inventory.");

		else if (quantity > amountInInventory) {
			System.out.println("Unable to add " + quantity + ' '+ itemName + "as there are only " + itemInInventory.getQuantity() + " left in inventory. Added " + itemInInventory.getQuantity() + " to cart.");
			Inventory item = new Inventory(itemInInventory.getSku(), itemInInventory.getName(), itemInInventory.getQuantity() , itemInInventory.getPrice());
			cart.add(item);
		}

		else {	//Add to quantity by the user's requested amount, then reduce the quantity in the list by this amount
			System.out.println("Add " + quantity + ' ' + itemName + " to cart.");
			Inventory item = new Inventory(itemInInventory.getSku(), itemInInventory.getName(), itemInInventory.getQuantity() , itemInInventory.getPrice());
			item.setQuantity(quantity);
			cart.add(item);
			itemInInventory.setQuantity(amountInInventory - quantity);
		}
	}

	//Reduces quantity for a valid Inventory
	public void removeItem(String itemName, int quantity) {

		//Make sure user input has a positive quantity
		if (quantity <= 0) {
			System.out.println("Must remove a quantity greater than 0!");
			return;
		}
		Inventory itemInCart = searchInventory(itemName); //Gets item in cart
		Inventory itemInInventory = Inventory.searchInventory(itemName); //Finds same item in Inventory

		//Only allow to remove the amount in the cart or less, otherwise empty the cart
		if (quantity < itemInCart.getQuantity()) {

			//Add quantity back to inventory list and remove quantity from cart
			System.out.println("Removed " + quantity + ' ' + itemName + " from cart.");
			itemInCart.setQuantity(itemInCart.getQuantity() - quantity);
			itemInInventory.setQuantity(itemInInventory.getQuantity() + quantity);
		}
		else {  //Remove all from the cart and add back to inventory
			System.out.println("Cannot remove " + quantity + " as there are only "+ itemInCart.getQuantity() + " Removed all of this item from cart.");
			itemInInventory.setQuantity(itemInInventory.getQuantity() + itemInCart.getQuantity());
			itemInCart.setQuantity(0);
		}

		if(itemInCart.getQuantity() <= 0) { //If the quantity of book is zero, remove from cart
			cart.remove(itemInCart);		
		}
	}

	//Search Inventory by name, uses BinarySearch
	public Inventory searchInventory(String itemName) {

		//Create "blank" Inventory object using just name as an argument
		Inventory i = new Inventory("0000", itemName, 0, 0);

		//Sorts list before BinarySearch
		Collections.sort(cart, new ItemComparator());

		//Compare by itemName
		int index = Collections.binarySearch(cart , i, new ItemComparator()); 
		return cart.get(index);
	}

	//View the date, customerName and contents of the ShoppingCart 
	public String viewCart() {
		String output = '\n' + date + "\tName: " + customerName + "\n";
		for (Inventory item : cart) {
			output += item.getQuantity() + "\t" + item.getName() + "\t€" + precision2.format(item.getPrice()*item.getQuantity()) + "\n";
		}
		output += "\t\tTotal: " + precision2.format(this.getTotal()) + '\n';
		return output;
	}
}