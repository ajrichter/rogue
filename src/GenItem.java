



import java.util.Random;

public class GenItem {
private String typeItem;
private String itemName;	
private int protection = 0;
private int foodStrength = 0;
private int hunger = 0;
private Character boardName;
private int weaponDamage;
private String useMessage = "";
private String dropMessage = "";
private String pickUpMessage = "";
private String takeOffMessage = "";

public GenItem () {
	
}


	public int getProbabilityOfItem() {
		int num = 0;
		Random rnd = new Random(); 
		num = rnd.nextInt(100);	
		if (num < 5) 
		{
			return 1;
		}
		else if (num < 10 && num >= 5)
		{
			return 2;
		}
		else if (num < 50 && num >= 10)
		{
			return 3;
		}
		else if (num < 60 && num >= 50)
		{
			return 4;
		}
		else if (num < 70 && num >= 60)
		{
			return 5;
		}
		else if (num < 80 && num >= 70)
		{
			return 6;
		}
		else if (num < 100 && num >= 80)
		{
			return 7;
		}
		else {
			return -1;
		}
		
	}
	








	public void genItem()
	{
		int num = getProbabilityOfItem();
		switch (4) { 
		case 1: this.typeItem = "Armor";
		Armor a = new Armor();
		a.getArmor();
		this.protection = a.armorProtection;
		this.boardName = a.boardName;
		this.itemName = a.name;
		this.pickUpMessage = " picked up " + this.itemName + " Armor with a protection of " + this.protection + " points.";
		this.useMessage = " equpted " + this.itemName + " Armor with " + this.protection + " protection!";
		this .dropMessage = " dropped the " + this.itemName + " on the floor.";
		this.takeOffMessage = " took off the " + this.itemName + " and put it back into your inventory";
		break;
		
		case 2: this.typeItem = "Weapon";
		Weapon w = new Weapon();
		w.getWeapon();
		this.weaponDamage = w.damage;
		this.itemName = w.name;
		this.boardName = w.boardName;
		this.useMessage = " equipted the " + this.itemName + " with a damage of " + this.weaponDamage;
		this .dropMessage = " dropped the " + this.itemName + " on the floor.";
		this.takeOffMessage = " took off the " + this.itemName + " " + this.typeItem + " and put it back into your inventory";
		this.pickUpMessage = " picked up " + this.itemName + " with a damage of " + this.weaponDamage + " points.";
		break;
	
		case 3: this.typeItem = "Food";
			Food food = new Food();
			food.getFood();
			this.hunger = food.playerHunger;
			this.boardName = food.boardName;
			this.itemName = food.name;
			this.pickUpMessage = " picked up " + this.itemName + " It contains " + this.hunger + " calories.";
			this.useMessage = " ate a " + food.name;
			this .dropMessage = " dropped the " + this.itemName + " on the floor.";
			break;
		

		case 4: this.typeItem = "Ring";
			Ring ring = new Ring();
			ring.getRing();
			this.boardName = ring.boardName;
			this.itemName = ring.name;
			this.useMessage = " put the " + this.itemName + " ring on his finger."; 
			this .dropMessage = " dropped the " + this.itemName + " on the floor.";
			this.takeOffMessage = " took off the " + this.itemName + " and put it back into his inventory";
			this.pickUpMessage = " picked up " + this.itemName + " Ring.";
			break;
			
		case 5: this.typeItem = "Scrolls";
			Scrolls scroll = new Scrolls();
			scroll.getScrolls();
			this.boardName = scroll.boardName;
			this.itemName = scroll.name;
			this.useMessage = " read the " + this.itemName + " Scroll.";
			this .dropMessage = " dropped the " + this.itemName + " on the floor.";
			this.pickUpMessage = " picked up " + this.itemName + " Scroll";
			break;
			
		case 6: this.typeItem = "Wand";
			Wand wand = new Wand();
			wand.getWand();
			this.boardName = wand.boardName;
			this.itemName = wand.name;
			this.useMessage = " equiped the " + this.itemName + " Wand.";
			this .dropMessage = " dropped the " + this.itemName + " on the floor.";
			this.takeOffMessage = " took off the " + this.itemName + " and put it back into his inventory";
			this.pickUpMessage = " picked up " + this.itemName + " Wand.";
			break;

		case 7: this.typeItem = "Potions";
			Potions p = new Potions();
			p.getPotion();
			this.boardName = p.boardName;
			this.itemName = p.name;
			this.useMessage = " drank the " + this.itemName + " potion.";
			this .dropMessage = " dropped the " + this.itemName + " on the floor.";
			this.pickUpMessage = " picked up " + this.itemName + " Potions.";
			break;
		default:
			this.typeItem = "NOP";
		break;	


		}
	}
	
	public String getTypeItem() {
		return this.typeItem;
	}

	public String getName() {
		return this.itemName;
	}

	public Character getBoardName() {
		return this.boardName;
	}

	public int getHunger() {
		return this.hunger;
	}

	public String getEquiptOrUseMessage() {
		return this.useMessage;
	}

	public String getDropMessage() {
		return this.dropMessage;
	}
	
	public String getTakeOffMessage() {
		return this.takeOffMessage;
	}
	
	public String getPickUpMessage() {
		return this.pickUpMessage;
	}
	
	
	public int getWeaponDamage() {
		return this.weaponDamage;
	}
	
	public int getArmorProtection() {
		return this.protection;
	}



}



