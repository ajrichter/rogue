
import java.util.LinkedList;

public class Inventory {

	private LinkedList<Item> inventory;
	private final int maxInventorySize = 5;
	private int itemsInInventory = 0;

	public Inventory() {
		this.inventory = new LinkedList<Item>();
	}

	public void removeItem(Item toRemove) {
		if (itemsInInventory != 0) {
			itemsInInventory--;
			inventory.remove(toRemove);
		}
	}

	public int getInventorySpace() {
		return itemsInInventory;
	}

	public boolean addItem(Item toAdd) {
		if (itemsInInventory != maxInventorySize) {
			inventory.add(toAdd);
			itemsInInventory++;
			return true;
		} else {
			return false;
		}
	}
}