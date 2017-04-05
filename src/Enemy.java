import Item.Item;

/*
	Create the different enemies
	Start with a list  of 5 or so?
	addMakeEnemy() Method which randomly generates an Enemy

*/

public class Enemy extends Unit{

	//special ability
	//boolean[] flags
	private int armorClass;
	private double treasureChance;
	private int expGained;
	private int dmg;

	public Enemy() {
		//values for prototype
		this.val="E";
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
