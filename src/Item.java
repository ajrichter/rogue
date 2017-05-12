

import java.awt.Point;


/**
 * 
 * @author Harry
 *
 */

public class Item {
	
	protected int playerHunger;
	protected int ringProtection;
	public Point p;
	protected char boardName;
	protected String name; //name of item
	protected String typeItem; //armor,equipment,ring,food,
	protected int damage; 
	protected int armorProtection;
	protected int playerStrength;
	protected int playerHealth;
	protected int playerHP;
	protected int maxPlayerHP;
	protected int playerXP;
	protected String eMessage; //Message for using or equiping
	protected String dMessage; //Message for dropping
	protected String pMessage; //Message for picking up item
	protected String uMessage; //Message for taking off equiptment
	private GenItem generate;
	protected boolean restoreStrength = false;
	protected boolean extraHealing = false;
	protected boolean healing = false;
	protected boolean blindness = false;
	protected boolean hasteSelf = false;
	protected boolean light = false;
	protected boolean monsterHaste = false; //item from wand
	protected boolean monsterDetection = false; //item from potion
	protected boolean monsterInvisable = false; //item from wand
	protected boolean hallucination = false; //item from potion 
	protected boolean levitation = false;  //item from potion
	protected boolean seeInvisable = false; //item from potion
	protected boolean polyMorph = false;
	protected boolean slowMonster = false;
	protected boolean sustainStrength = false; //Item from ring
	protected boolean aggregateMonster = false; //Item from ring
	protected boolean maintainArmor = false; //Item from ring
	protected boolean isStealth = false; //Item from ring
	protected boolean cancellation = false; //Item from wand

	
	public Item() {
	generate = new GenItem();
		
	}


	public GenItem generateItem() {
		generate.genItem();
		this.typeItem = generate.getTypeItem();
		this.name = generate.getName();
		this.boardName = generate.getBoardName();
		this.eMessage = generate.getEquiptOrUseMessage();
		this.uMessage = generate.getTakeOffMessage();
		this.pMessage = generate.getPickUpMessage();
		this.dMessage = generate.getDropMessage();
		
		
		if (this.typeItem.equals("Food")) {
			this.playerHunger = generate.getHunger();
		}
		else if (this.typeItem.equals("Weapon"))
		{
			this.damage = generate.getWeaponDamage();

		}
		else if (this.typeItem.equals("Armor"))
		{
			this.armorProtection = generate.getArmorProtection();
		}
		
			this.maxPlayerHP = generate.getMaxPlayerHP();
			this.playerHP = generate.getPlayerHP();
			this.playerStrength = generate.getPlayerStrength();
			this.playerXP = generate.getPlayerXP();
			this.restoreStrength = generate.isRestoreStrength();
			this.extraHealing = generate.getExtraHealing();
			this.healing = generate.getHealing();
			this.hallucination = generate.getHallucination();
			this.blindness = generate.getInvisible();
			this.hasteSelf = generate.getHasteSelf();
			this.levitation = generate.getLevitation();
			this.light = generate.getLight();
		return generate;
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
	
	public String getTakeOffMessage() {
		return uMessage;
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
	
	public int getDamageFromWeapon() {
		return damage;
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