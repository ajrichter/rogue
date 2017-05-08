

import java.util.LinkedList;
import java.util.HashMap;

public class Inventory {
	
	private LinkedList<Item> inventory;
	private LinkedList<Character> invChar;
	private final int maxInventorySize = 5;
	private int inventorySize = 5;
	private int itemsInInventory = 0;
	
	public Inventory() {
		this.inventory = new LinkedList<Item>();
		this.invChar = new LinkedList<Character>();
	}
	
	public void removeItem(Item toRemove) {
		if (inventorySize != maxInventorySize) {
		inventorySize++;
		itemsInInventory--;
		inventory.remove(toRemove);
		}
	}
	
	public int getInventorySpace() {
		return itemsInInventory;
	}
	
	
	
	public boolean addItem(Item toAdd) { 
		if (inventorySize != 0) { 
			inventory.add(toAdd);
			//invChar.add(toAdd.boardName);
			inventorySize--;
			itemsInInventory++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void inventoryChars() { // Find out how items are represented as characters. I'm using a hashmap for this.
		Character curChar = 'a';
		for (int i = 0; i < inventory.size(); i++) {
			invChar.add(curChar);
			curChar++;
		}
	}
	
	public void listInventory() {
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(invChar.get(i) + ": " + inventory.get(i).name);
		}
	}
}