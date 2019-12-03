import java.util.*;

/*
 * 	Paul Kirwan
 *	17321313
 */

public class Inventory {
	private String sku;
	private String itemName;
	private double price;
	private int quantity;
	
	private static List<Inventory> items = new ArrayList<Inventory>();
	
	//Main constructor to create individual Inventory objects
	public Inventory(String sku, String itemName, int quantity, double price) {
		this.sku = sku;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	//Constructor used to pass the list from main to the Inventory class
	public Inventory(List<Inventory> list) {
		items = list;
	}
	
	//Getter methods
	public String getSku() {
		return sku;
	}
	
	public String getName() {
		return itemName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	//Update quantity when adding/removing Inventory objects from the Shopping Cart
	public void setQuantity(int amount) {
		this.quantity = amount;
	}
	
	//Same method as searchCartInventory, but searches through the static list
	public static Inventory searchInventory(String itemName) {
		//Create "blank" Inventory object with just name
		Inventory i = new Inventory("0000", itemName, 0, 0);

		//Sorts list before BinarySearch
		Collections.sort(items, new ItemComparator());

		//Compare by itemName
		int index = Collections.binarySearch(items , i, new ItemComparator()); 
		return items.get(index);
	}
	
	@Override
	public String toString() {
		return sku + "\t" + itemName + "\t" + quantity + "\t" + price;
	}
}