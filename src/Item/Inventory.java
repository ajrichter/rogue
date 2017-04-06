package Item;
import java.util.LinkedList;
import java.util.HashMap;

public class Inventory {
	
	private LinkedList<Item> inventory;
	private LinkedList<Character> invChar;
	//private HashMap<Character, Item> invChar;
	
	public Inventory() {
		this.inventory = new LinkedList<Item>();
		this.invChar = new LinkedList<Character>();
	//	this.invChar = new HashMap<Character, Item>();
	}
	
	public void removeItem(Item toRemove) {
		inventory.remove(toRemove);
	}
	
	public void addItem(Item toAdd) { 
		/**
		 * Items are sorted by type. Food goes first. If an item is not in the inventory, it goes first, after food.
		 */
		// TODO add cases for other item types.
		int maxFood = -1; // Figure out the index of the last piece of food
		int maxWand = -1;
		int maxArmor = -1;
		int maxWeapon = -1;
		int maxScrolls = -1;
		int maxPotions = -1;
		int maxRings = -1;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getTypeItem().equals("Food")) {
				maxFood = i;
			}
			else if (inventory.get(i).getTypeItem().equals("Wand")) {
				maxWand = i;
			}
			else if (inventory.get(i).getTypeItem().equals("Armor")) {
				maxArmor = i;
			}
			else if (inventory.get(i).getTypeItem().equals("Weapon"))
			{
				maxWeapon = i;
			}
			else if (inventory.get(i).getTypeItem().equals("Scrolls"))
			{
				maxScrolls = i;
			}
			else if (inventory.get(i).getTypeItem().equals("Potions"))
			{
				maxPotions = i;
			}
			else if (inventory.get(i).getTypeItem().equals("Rings"))
			{
				maxRings = i;
			}
			
			if (inventory.get(i).getTypeItem().equals("Food")) {
				inventory.add(i, toAdd);
			}
			// else if (other item type)
			else if (inventory.get(i).getTypeItem().equals("Potions")) {
				inventory.add(i, toAdd);
			}
			
			
			
		}
	
		inventory.add(maxFood + 1, toAdd); // If the inventory doesn't contain any other items of toAdd's type, put it first, after food.
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
			System.out.println(invChar.get(i) + ": " + inventory.get(i).getName());
		}
	}
}