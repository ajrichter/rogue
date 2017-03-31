import Item.Item;

public class Enemy extends Unit{

	//special ability
	//boolean[] flags
	private int armorClass;
	private double treasureChance;
	private int expGained;
	private int dmg;
	
	public Enemy() {
		//values for prototype
		this.boardName="E";
		this.name="Slime";
		this.armor=10;
		this.hp=20;
	}
	
	public void chase() {
		
	}
	
	public void moveTo() {
		
	}
	
	public void moveRandom() {
		
	}
	
	public int attackPlayer() {
		return 0;
		
	}
	
	public void removeMonster() {
		
	}
	
	//decides to drop an item and returns a random item
	public Item dropTreasure() {
		Item i= new Item("Amulet of Yendor",null);
		return i;
	}

}
