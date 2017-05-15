
import java.awt.Point;

/**
 * 
 * @author Harry Carpenter, hhcarpenter@knox.edu
 *
 */

public class Item {

	//Scroll Variables:
	protected boolean identifyWandOrRing = false;
	protected boolean identifyPotion = false;
	protected boolean identifyWeapon = false;
	protected boolean foodDetection = false;
	protected boolean identifyArmor = false;
	protected boolean identifyScroll = false;
	protected boolean magicDetection = false;

	//Potion Variables:
	protected boolean hallucination = false;
	protected boolean levitation = false; 
	protected boolean seeInvisable = false; 
	protected boolean restoreStrength = false;
	protected boolean extraHealing = false;
	protected boolean healing = false;
	protected boolean blindness = false;
	protected boolean hasteSelf = false;
	protected boolean monsterDetection = false; 

	//Wand Variables:
	protected boolean light = false;
	protected boolean monsterHaste = false;
	protected boolean monsterInvisible = false; 

	//Food Variables:
	protected int playerHunger;


	//Armor Variables:
	protected int armorProtection;

	//Ring Variables:
	protected boolean sustainStrength = false; 
	protected boolean aggregateMonster = false;
	protected boolean maintainArmor = false; 
	protected int gold = 0; 




	//Item Variables:
	public Point p;
	protected char boardName;
	protected String name; 
	protected String typeItem;
	protected int strengthFromWeapons;
	protected String eMessage; 
	protected String dMessage;
	protected String pMessage; 
	protected int playerStrength;
	protected int playerHealth;
	protected int playerHP;
	protected int playerXP;
	protected GenItem generate;









	public Item() {
		generate = new GenItem();

	}

	public GenItem generateItem() {
		generate.genItem();

		//Item Variable declarations:
		this.typeItem = generate.getTypeItem();
		this.name = generate.getName();
		this.boardName = generate.getBoardName();
		this.eMessage = generate.getEquiptOrUseMessage();
		this.pMessage = generate.getPickUpMessage();
		this.dMessage = generate.getDropMessage();
		this.playerHP = generate.getPlayerHP();
		this.playerStrength = generate.getPlayerStrength();
		this.playerXP = generate.getPlayerXP();

		//Food Variable declarations:
		this.playerHunger = generate.getHunger();


		//Weapon Variable declarations:
		this.playerStrength = generate.getWeaponStrength();

		//Armor Variable declarations:
		this.armorProtection = generate.getArmorProtection();

		//Ring Variable declarions:
		this.gold = generate.getGold(); 

		//Potion Variable Declarations:
		this.restoreStrength = generate.isRestoreStrength();
		this.extraHealing = generate.getExtraHealing();
		this.healing = generate.getHealing();
		this.hallucination = generate.getHallucination();
		this.blindness = generate.getInvisible();
		this.hasteSelf = generate.getHasteSelf();
		this.levitation = generate.getLevitation();

		//Wand Varaible Declarations:
		this.light = generate.getLight();
		this.monsterInvisible = generate.monsterInvisible();

		//Scroll Variable Declarations:
		this.identifyScroll = generate.identifyScrolls();
		this.identifyArmor = generate.identifyArmor();
		this.identifyPotion = generate.identifyPotions();
		this.identifyWandOrRing = generate.identifyWand() || generate.identifyRing();
		this.foodDetection = generate.identifyFood();
		this.identifyWeapon = generate.identifyWeapon();


		return generate;
	}

	public boolean monsterDetection() {
		return this.monsterDetection;
	}


	public boolean magicDetection() {
		return this.magicDetection;
	}


	public boolean identifyScoll() {
		return this.identifyScroll;
	}

	public boolean identifyWeapon() {
		return this.identifyWeapon;
	}

	public boolean idenitfyWandOrRing() {
		return this.identifyWandOrRing;
	}

	public boolean identifyFood() {
		return this.foodDetection;
	}

	public boolean identifyPotions() {
		return this.identifyPotion;
	}

	public boolean identifyArmor() {
		return this.identifyArmor;
	}


	public boolean monsterInvisible() {
		return monsterInvisible;
	}


	public boolean getLight() {
		return light;
	}

	public boolean getLevitation() {
		return levitation;
	}

	public boolean getHasteSelf() {
		return hasteSelf;
	}

	public boolean getBlindness() {
		return blindness;
	}

	public boolean getHallucination() {
		return hallucination;
	}

	public boolean getHealing() {
		return healing;
	}

	public boolean getExtraHealing() {
		return extraHealing;
	}

	public int getPlayerXP() {
		return playerXP;
	}

	public boolean restoreStrength() {
		return restoreStrength;
	}

	public String getUseMessage() {
		return eMessage;
	}

	public String getDropMessage() {
		return dMessage;
	}

	public String getPickUpMessage() {
		return pMessage;
	}

	public String getItemType() {
		return typeItem;
	}

	public char getBoardName() {
		return this.boardName;
	}

	public String getItemName() {
		return name;
	}

	public int getArmorProtection() {
		return armorProtection;
	}

	public int getWeaponStrength() {
		return strengthFromWeapons;
	}

	public int getPlayerStrength() {
		return playerStrength;
	}

	public int getPlayerHunger() {
		return playerHunger;
	}

	public int getPlayerHP() {
		return playerHP;
	}

}