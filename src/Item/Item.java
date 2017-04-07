package Item;

import java.util.HashMap;
/**
 * 
 * @author Harry
 *
 */

public class Item {
	protected int armorProtection = 0;
	protected int strength = 0;
	protected int damage = 0;
	protected String carnation; //Cursed, blessed, or normal
	protected String message;
	protected String weaponName;
	protected String boardName;
	protected String name; //name of item
	protected String typeItem; //armor,equipment,ring,food,
	protected int itemNumber; //id for item
		
	public Item(String name1, String typeItem1){
		name = name1; 
		typeItem = typeItem1;
	}
	
	public String toString() {
		return boardName;
	}

	
	
	
	//takes up time to use items
	protected void wasteTime(){
		//Todo
	}
	
	//returns type item
	protected String typeItem(){
		return getTypeItem();
	}

	public String getTypeItem() {
		return typeItem;
	}


	


	public String getName() {
		return name;
	}

	public int getItemNumber() {
		return itemNumber;
	}
	
	public int getDamageFromW() {
		Weapon w = new Weapon(name, typeItem);
		w.getDamageFromWeapon(w);
		return w.getDamage();
	}
	
	public int getProtectionFromArmor() {
		Armor a = new Armor(name, typeItem);
		a.getArmorProtection(a);
		return a.getArmorProtection();
	}
	
	public int getStrengthFromFood() {
		Food f = new Food(name, typeItem);
		f.getStrengthFromFood(f);
		return f.getFoodStrength();
	}
	
	public String getScrollMessage() {
		Scrolls s = new Scrolls(name, typeItem);
		s.getEffectsFromScrolls(s);
		return s.getMessage();
	}


}