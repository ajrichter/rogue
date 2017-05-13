



import java.util.Random;

public class GenItem {
	private boolean identifyScroll = false;
	private boolean identifyWandOrRing = false;
	private boolean foodDetection = false;
	private boolean identifyPotion = false;
	private boolean identifyArmor = false;
	private boolean identifyWeapon =false;
	private boolean monsterDetection = false;
	private boolean magicDetection = false;
	
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
	private boolean blindness = false;
	private boolean hasteSelf = false;
	private boolean levitation = false;
	private boolean light = false;
	private boolean monsterInvisible = false;
	
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
		switch (6) { 
		case 1: this.typeItem = "Armor";
		Armor a = new Armor();
		a.getArmor();
		this.protection = a.armorProtection;
		this.boardName = a.boardName;
		this.itemName = a.name;
		this.pickUpMessage = " picked up armor.";
		this.useMessage = " use " + a.name;
		this.dropMessage = " dropped armor on the floor.";
		break;

		case 2: this.typeItem = "Weapon";
		Weapon w = new Weapon();
		w.getWeapon();
		this.weaponDamage = w.damage;
		this.itemName = w.name;
		this.boardName = w.boardName;
		this.pickUpMessage = " picked up weapon.";
		this.useMessage = " use " + w.name;
		this.dropMessage = " dropped weapon on the floor.";
		break;

		case 3: this.typeItem = "Food";
		Food food = new Food();
		food.getFood();
		this.hunger = food.playerHunger;
		this.boardName = food.boardName;
		this.itemName = food.name;
		this.pickUpMessage = " picked up food.";
		this.useMessage = " eat " + food.name;
		this.dropMessage = " dropped food on the floor.";
		break;


		case 4: this.typeItem = "Ring";
		Ring ring = new Ring();
		ring.getRing();
		this.boardName = ring.boardName;
		this.itemName = ring.name;
		this.pickUpMessage = " picked up ring.";
		this.useMessage = " use " + this.itemName;
		this.dropMessage = " dropped ring on the floor.";
		break;

		case 5: this.typeItem = "Scroll";
		Scrolls scroll = new Scrolls();
		scroll.getScrolls();
		this.boardName = scroll.boardName;
		this.itemName = scroll.name;
		this.pickUpMessage = " picked up scroll.";
		this.useMessage = " read " + scroll.name;
		this.dropMessage = " dropped scroll on the floor.";
		this.identifyPotion =scroll.identifyPotion;
		this.identifyArmor = scroll.identifyArmor;
		this.identifyWandOrRing = scroll.identifyWandOrRing;
		this.identifyScroll = scroll.identifyScroll;
		this.foodDetection = scroll.foodDetection;
		this.identifyWeapon = scroll.identifyWeapon;
		break;

		case 6: this.typeItem = "Wand";
		Wand wand = new Wand();
		wand.getWand();
		this.boardName = wand.boardName;
		this.itemName = wand.name;
		this.pickUpMessage = " picked up wand.";
		this.useMessage = " use " + this.itemName;
		this.dropMessage = " dropped wand on the floor.";
		this.weaponDamage = wand.damage;
		this.light = wand.light;
		this.monsterInvisible = wand.monsterInvisible;
		
		break;

		case 7: this.typeItem = "Potions";
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
		this.blindness = p.blindness;
		this.pickUpMessage = " picked up potion.";
		this.useMessage = " drank " + p.name;
		this.dropMessage = " dropped wand on the floor.";
		this.magicDetection = p.magicDetection;
		this.monsterDetection = p.monsterDetection;
		this.hasteSelf = p.hasteSelf;
		this.levitation = p.levitation;
		break;
		default:
			this.typeItem = "NOP";
			break;	


		}
	}

	public boolean monsterDetection() {
		return monsterDetection;
	}
	
	public boolean magicDetection() {
		return magicDetection;
	}
	
	public boolean identifyWeapon() {
		return this.identifyWeapon;
	}
	
	public boolean identifyArmor() {
		return this.identifyArmor;
	}
	
	public boolean identifyFood() {
		return this.foodDetection;
	}
	
	public boolean identifyWand() {
		return this.identifyWandOrRing;
	}
	public boolean identifyPotions() {
		return this.identifyPotion;
	}
	public boolean identifyRing() {
		return this.identifyWandOrRing;
	}
	public boolean identifyScrolls() {
		return this.identifyScroll;
	}
	
	
	public boolean monsterInvisible() {
		return this.monsterInvisible;
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
		return this.blindness;
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



