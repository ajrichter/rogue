



import java.util.Random;

public class GenItem {
	private String typeItem;
	private String itemName;	
	private int protection = 0;
	private int hunger = 0;
	private Character boardName;
	private int weaponDamage;
	private String useMessage = "";
	private String dropMessage = "";
	private String pickUpMessage = "";
	private String takeOffMessage = "";
	private int playerStrength = 0;
	private int playerHP = 0;
	private int maxPlayerHP = 0; 
	private int playerXP = 0;
	private boolean restoreStrength = false;
	private boolean healing = false;
	private boolean extraHealing = false;
	private boolean hallucination = false;
	private boolean invisible = false;
	private boolean hasteSelf = false;
	private boolean levitation = false;
	private boolean light = false;
	
	public GenItem () {

	}


	public int getProbabilityOfItem() {
		int num = 0;
		Random rnd = new Random(); 
		num = rnd.nextInt(100);	
		if (num < 10) 
		{
			return 1;
		}
		else if (num < 20 && num >= 10)
		{
			return 2;
		}
		else if (num < 60 && num >= 20)
		{
			return 3;
		}
		else if (num < 70 && num >= 60)
		{
			return 4;
		}
		else if (num < 80 && num >= 70)
		{
			return 5;
		}
		else if (num < 90 && num >= 80)
		{
			return 6;
		}
		else if (num < 100 && num >= 90)
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
		switch (7) { 
		case 1: this.typeItem = "Armor";
		Armor a = new Armor();
		a.getArmor();
		this.protection = a.armorProtection;
		this.boardName = a.boardName;
		this.itemName = a.name;
		this.pickUpMessage = " picked up the " + this.itemName + " Armor with a protection of " + this.protection + " points.";
		this.useMessage = " equpted " + this.itemName + " Armor with " + this.protection + " protection!";
		this.dropMessage = " dropped the " + this.itemName + " " + this.typeItem + " on the floor.";
		break;

		case 2: this.typeItem = "Weapon";
		Weapon w = new Weapon();
		w.getWeapon();
		this.weaponDamage = w.damage;
		this.itemName = w.name;
		this.boardName = w.boardName;
		this.useMessage = " equipted the " + this.itemName + " with a damage of " + this.weaponDamage;
		this.dropMessage = " dropped the " + this.itemName + " " + this.typeItem + " on the floor.";
		this.pickUpMessage = " picked up the " + this.itemName + " with a damage of " + this.weaponDamage + " points.";
		break;

		case 3: this.typeItem = "Food";
		Food food = new Food();
		food.getFood();
		this.hunger = food.playerHunger;
		this.boardName = food.boardName;
		this.itemName = food.name;
		this.pickUpMessage = " picked up " + this.itemName + ". It contains " + this.hunger + " calories.";
		this.useMessage = " ate a " + food.name;
		this .dropMessage = " dropped the " + this.itemName + " on the floor.";
		break;


		case 4: this.typeItem = "Ring";
		Ring ring = new Ring();
		ring.getRing();
		this.boardName = ring.boardName;
		this.itemName = ring.name;
		this.useMessage = " put the " + this.itemName + " ring on your finger."; 
		this .dropMessage = " dropped the " + this.itemName + " " + this.typeItem + " on the floor.";
		this.pickUpMessage = " picked up " + this.itemName + " Ring.";
		break;

		case 5: this.typeItem = "Scroll";
		Scrolls scroll = new Scrolls();
		scroll.getScrolls();
		this.boardName = scroll.boardName;
		this.itemName = scroll.name;
		this.useMessage = " read the " + this.itemName + " Scroll.";
		this .dropMessage = " dropped the " + this.itemName + " " + this.typeItem + " on the floor.";
		this.pickUpMessage = " picked up the " + this.itemName + " Scroll";
		break;

		case 6: this.typeItem = "Wand";
		Wand wand = new Wand();
		wand.getWand();
		this.boardName = wand.boardName;
		this.itemName = wand.name;
		this.useMessage = " equiped the " + this.itemName + " Wand.";
		this .dropMessage = " dropped the " + this.itemName + " " + this.typeItem + " on the floor.";
		this.pickUpMessage = " picked up the " + this.itemName + " Wand.";
		this.light = wand.light;
		break;

		case 7: this.typeItem = "Potion";
		Potions p = new Potions();
		p.getPotion();
		
		this.boardName = p.boardName;
		this.itemName = p.name;
		this.playerStrength = p.playerStrength;
		this.playerHP = p.playerHP;
		this.maxPlayerHP = p.maxPlayerHP;
		this.playerXP = p.playerXP;
		this.restoreStrength = p.restoreStrength;
		this.extraHealing = p.extraHealing;
		this.healing = p.healing;
		this.hallucination = p.hallucination;
		this.invisible = p.blindness;
		this.useMessage = " drank the " + this.itemName + " potion." + p.eMessage;
		this .dropMessage = " dropped the " + this.itemName + " " + this.typeItem + " on the floor.";
		this.pickUpMessage = " picked up the " + this.itemName + " Potion.";
		this.hasteSelf = p.hasteSelf;
		this.levitation = p.levitation;
		break;
		default:
			this.typeItem = "NOP";
			break;	


		}
	}

	public boolean getLight() {
		return this.light;
	}
	
	
	public boolean getLevitation() {
		return this.levitation;
	}
	
	public boolean getHasteSelf() {
		return this.hasteSelf;
	}
	
	public boolean getInvisible() {
		return this.invisible;
	}
	
	
	public boolean getHallucination() {
		return this.hallucination;
	}
	
	
	public boolean getHealing() {
		return this.healing;
	}
	
	public boolean getExtraHealing() {
		return this.extraHealing;
	}
	
	public boolean isRestoreStrength() {
		return this.restoreStrength;
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

	public int getMaxPlayerHP() {
		return this.maxPlayerHP;
	}
	
	public int getPlayerHP() {
		return this.playerHP;
	}
	
	public int getPlayerStrength() {
		return this.playerStrength;
	}
	
	public int getPlayerXP() {
		return this.playerXP;
	}
	

}



