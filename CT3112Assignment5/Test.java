import java.util.*;

/*
 * 	Paul Kirwan
 *	17321313
 */

public class Test {

	public static void main(String[] args) {
		
		//Add each Inventory object to a list
		List<Inventory> list = new ArrayList<Inventory>();
		list.add(new Inventory("1000", "Apple\t", 30, 2.50));
		list.add(new Inventory("1001", "Orange\t", 40, 2));
		list.add(new Inventory("2001", "Milk\t", 10, 2.39));
		list.add(new Inventory("2002", "Orange Juice", 20, 1.99));
		list.add(new Inventory("3001", "Blue Cheese", 10, 2.25));
		list.add(new Inventory("3002", "Cheddar\t", 20, 2.79));
		list.add(new Inventory("4001", "Chocolate", 40, 2.99));
		list.add(new Inventory("4002", "Candy\t", 30, 0.99));
		list.add(new Inventory("5001", "Beef\t", 10, 5.00));
		list.add(new Inventory("5002", "Chicken\t", 10, 4.00));
		
		//Create static list of Inventory using the Inventory list constructor
		Inventory inventory =  new Inventory(list);

		//print the contents of each object in the list before adding to the cart
		System.out.println("Inventory List before adding to cart:");
		for (Inventory i : list)
			System.out.println(i.toString());
		System.out.println();
		
		//Test ShoppingCart class
		ShoppingCart cart1 = new ShoppingCart("John", "12/10/2017");

		//Cart1 operations and show output
		System.out.println("Cart 1:");
		cart1.addItem("Apple\t", 2);
		cart1.addItem("Orange\t", 5);
		cart1.addItem("Milk\t", 2);
		cart1.addItem("Blue Cheese", 4);
		cart1.addItem("Candy\t", 25);
		cart1.removeItem("Candy\t", 5);
		
		System.out.println(cart1.viewCart());
		
		//View inventory after adding to cart1
		System.out.println("Inventory List after adding to cart 1:");
		for (Inventory i : list)
			System.out.println(i.toString());
		System.out.println();
		
		//Cart2 operations and show output
		ShoppingCart cart2 = new ShoppingCart("Mark", "13/10/2017");
		
		System.out.println("Cart 2:");
		cart2.addItem("Apple\t", 2);
		cart2.addItem("Orange\t", 5);
		cart2.addItem("Milk\t", 2);
		cart2.addItem("Blue Cheese", 4);
		cart2.addItem("Cheddar\t", 3);
		cart2.addItem("Beef\t", 6);
		cart2.addItem("Candy\t", 20);
		cart2.addItem("Chocolate", 10);
		cart2.addItem("Chicken\t", 2);
		cart2.removeItem("Chocolate", 5);
		cart2.removeItem("Blue Cheese", 1);
		
		System.out.println(cart2.viewCart());
		
		//Sort cart2 by price in ascending order
		Collections.sort(cart2.getItems(), new PriceComparator());
		System.out.println("Cart 2 Sorted by price:" + cart2.viewCart());
		
		//View Inventory list after adding to both carts
		System.out.println("Inventory List after adding to cart 2:");
		for (Inventory i : list)
			System.out.println(i.toString());
	}
}