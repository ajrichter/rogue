<<<<<<< HEAD
package Item;

/**
 * 
 * @author Harry
 *
 */

public class Item {
	protected String boardName;
	protected String name; //name of item
	protected String typeItem; //armor,equipment,ring,food,
	protected int damage; 
	protected int armorProtection;
	protected String potionMessage;
	protected String scrollMessage;
	protected int foodStrength;
	protected String finalMessage;
	
	public Item() {
		
		
	}


	public void generateItem() {
		GenItem g = new GenItem();
		g.genItem();
		this.typeItem = g.getTypeItem();
		this.name = g.getName();
		this.boardName = g.getBoardName();
		this.finalMessage = g.getPickUpMessage();
		if (this.typeItem.equals("Food")) {
			this.foodStrength = g.getFoodStrength();
		}
		else if (this.typeItem.equals("Weapon"))
		{
			this.damage = g.getWeaponDamage();
		}
		
		
		
		
	}

	public String getBoardName() {
		return this.boardName;
	}
	
	public String getPrintMessage() {
		return finalMessage;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	
	
	
//	public int getDamageFromW() {
//		Weapon w = new Weapon(name, typeItem);
//		w.getDamageFromWeapon(w);
//		return w.getDamage();
//	}
//	
//	public int getProtectionFromArmor() {
//		Armor a = new Armor(name, typeItem);
//		a.getArmorProtection(a);
//		return a.getArmorProtection();
//	}
//	
//	public int getStrengthFromFood() {
//		Food f = new Food();
//		f.getStrengthFromFood(f);
//		return f.getFoodStrength();
//	}
//	
//	public String getScrollMessage() {
//		Scrolls s = new Scrolls(name, typeItem);
//		s.getEffectsFromScrolls(s);
//		return s.getMessage();
//	}


=======
package Item;

import java.util.HashMap;
/**
 * 
 * @author Harry
 *
 */

public class Item {
	protected String boardName;
	protected String name; //name of item
	protected String typeItem; //armor,equipment,ring,food,
	protected int damage; 
	protected int armorProtection;
	protected String potionMessage;
	protected String scrollMessage;
	protected int foodStrength;
	protected String finalMessage;
	
	public Item() {
		
		
	}


	public void generateItem() {
		GenItem g = new GenItem();
		g.genItem();
		this.typeItem = g.getTypeItem();
		this.name = g.getName();
		this.boardName = g.getBoardName();
		this.finalMessage = g.getPickUpMessage();
		if (this.typeItem.equals("Food")) {
			this.foodStrength = g.getFoodStrength();
		}
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
//	public int getDamageFromW() {
//		Weapon w = new Weapon(name, typeItem);
//		w.getDamageFromWeapon(w);
//		return w.getDamage();
//	}
//	
//	public int getProtectionFromArmor() {
//		Armor a = new Armor(name, typeItem);
//		a.getArmorProtection(a);
//		return a.getArmorProtection();
//	}
//	
//	public int getStrengthFromFood() {
//		Food f = new Food();
//		f.getStrengthFromFood(f);
//		return f.getFoodStrength();
//	}
//	
//	public String getScrollMessage() {
//		Scrolls s = new Scrolls(name, typeItem);
//		s.getEffectsFromScrolls(s);
//		return s.getMessage();
//	}


>>>>>>> master
}