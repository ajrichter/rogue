
package Item;

import java.awt.Point;

/**
 * 
 * @author Harry
 *
 */

public class Item {
	protected Point p;
	protected Character boardName;
	protected String name; //name of item
	protected String typeItem; //armor,equipment,ring,food,
	protected int damage; 
	protected int armorProtection;
	protected int playerStrength;
	protected int playerHealth;
	protected int playerHP;
	protected String finalMessage;
	private GenItem generate;
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
		this.finalMessage = generate.getPickUpMessage();
		if (this.typeItem.equals("Food")) {
			this.playerStrength = generate.getFoodStrength();
		}
		else if (this.typeItem.equals("Weapon"))
		{
			this.damage = generate.getWeaponDamage();
		}
		else if (this.typeItem.equals("Armor"))
		{
			this.armorProtection = generate.getArmorProtection();
		}
		
		return generate;
	}

	public char getBoardName() {
		return this.boardName;
	}
	
	public String getPrintMessage() {
		return finalMessage;
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
	
	public int getStrengthFromFood() {
		return playerStrength;
	}

	



}