package Item;
import java.util.LinkedList;
import java.util.HashMap;

public class Inventory {
	
	private LinkedList<Item> inventory;
	private LinkedList<Character> invChar;
	//private HashMap<Character, Item> invChar;
	private int maxFood = 5; 
	private int maxWand = 1;
	private int maxArmor = 1;
	private int maxWeapon = 1;
	private int maxScrolls = 10;
	private int maxPotions = 5;
	private int maxRings = 1;
	
	public Inventory() {
		this.inventory = new LinkedList<Item>();
		this.invChar = new LinkedList<Character>();
	//	this.invChar = new HashMap<Character, Item>();
	}
	
	public void removeItem(Item toRemove) {
		inventory.remove(toRemove);
	}
	
	public boolean addItem(Item toAdd) { 
		/**
		 * Items are sorted by type. Food goes first. If an item is not in the inventory, it goes first, after food.
		 */
		// TODO add cases for other item types.

		if (toAdd.typeItem.equalsIgnoreCase("Weapon") && maxWeapon != 0)
		{
			maxWeapon--;
			inventory.add(toAdd);
			return true;
		}
		else if (toAdd.typeItem.equalsIgnoreCase("Armor") && maxArmor != 0)
		{
			maxArmor--;
			inventory.add(toAdd);
			return true;
		}
		else if (toAdd.typeItem.equalsIgnoreCase("Wand") && maxWand != 0)
		{
			maxWand--;
			inventory.add(toAdd);
			return true;
		}
		else if (toAdd.typeItem.equalsIgnoreCase("Food") && maxFood != 0)
		{
			maxFood--;
			inventory.add(toAdd);
			return true;
		}
		else if (toAdd.typeItem.equalsIgnoreCase("Scrolls") && maxScrolls != 0)
		{
			maxScrolls--;
			inventory.add(toAdd);
			return true;
		}
		else if (toAdd.typeItem.equalsIgnoreCase("Potions") && maxPotions != 0)
		{
			maxPotions--;
			inventory.add(toAdd);
			return true;
		}
		else if (toAdd.typeItem.equalsIgnoreCase("Ring") && maxRings != 0)
		{
			maxRings--;
			inventory.add(toAdd);
			return true;
		}
		else
		{
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