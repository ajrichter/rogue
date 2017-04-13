<<<<<<< HEAD
package Item;



import java.util.Random;

public class GenItem {
private String typeItem;
private String itemName;	
private int protection = 0;
private int foodStrength = 0;
private String boardName;
private int weaponDamage;
private String pickUpMessage = "";
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
		switch (num) { 
		case 1: this.typeItem = "Armor";
		Armor a = new Armor();
		a.getArmor();
		this.protection = a.armorProtection;
		this.boardName = a.boardName;
		this.itemName = a.name;
		System.out.println(this.typeItem);
		this.pickUpMessage = "You picked up " + this.itemName + " Armor with " + this.protection + " protection!";
		break;
		
		case 2: this.typeItem = "Weapon";
		Weapon w = new Weapon();
		w.getWeapon();
		this.weaponDamage = w.damage;
		this.itemName = w.name;
		this.boardName = w.boardName;
		System.out.println(this.typeItem);
		this.pickUpMessage = "You picked up " + this.itemName + " with a damage of " + this.weaponDamage;
		break;
	
		case 3: this.typeItem = "Food";
			Food food = new Food();
			food.getFood();
			this.boardName = food.boardName;
			this.itemName = food.name;
			this.foodStrength = food.foodStrength;
			System.out.println(this.typeItem);
			this.pickUpMessage = "picked up " + this.itemName + " with a food strength of " + this.foodStrength;
			break;
		

		case 4: this.typeItem = "Ring";
			Ring ring = new Ring();
			ring.getRing();
			this.boardName = ring.boardName;
			this.itemName = ring.name;
			System.out.println(this.typeItem);
			this.pickUpMessage = "picked up the " + this.itemName + " Ring."; 
			break;
			
		case 5: this.typeItem = "Scrolls";
			Scrolls scroll = new Scrolls();
			scroll.getScrolls();
			this.boardName = scroll.boardName;
			this.itemName = scroll.name;
			System.out.println(this.typeItem);
			this.pickUpMessage = "picked up the " + this.itemName + " Scroll.";
			break;
			
		case 6: this.typeItem = "Wand";
			Wand wand = new Wand();
			wand.getWand();
			this.boardName = wand.boardName;
			this.itemName = wand.name;
			System.out.println(this.typeItem);
			this.pickUpMessage = "picked up the " + this.itemName + " Wand.";
			break;

		case 7: this.typeItem = "Potions";
			Potions p = new Potions();
			p.getPotion();
			this.boardName = p.boardName;
			this.itemName = p.name;
			System.out.println(this.typeItem);
			this.pickUpMessage = "picked up the " + this.itemName + " Potion.";
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

	public String getBoardName() {
		return this.boardName;
	}

	public int getFoodStrength() {
		return this.foodStrength;
	}

	public String getPickUpMessage() {
		return this.pickUpMessage;
	}

	public int getWeaponDamage() {
		return this.weaponDamage;
	}




}
=======
package Item;

import java.util.Random;

public class GenItem {
private String typeItem;
private String itemName;	
private int protection = 0;
private int foodStrength = 0;
private String boardName;
private int weaponDamage;
private String pickUpMessage = "";
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
		switch (num) { 
		case 1: this.typeItem = "Armor";
		Armor a = new Armor();
		a.getArmor();
		this.protection = a.armorProtection;
		this.boardName = a.boardName;
		this.itemName = a.name;
		this.pickUpMessage = "You picked up " + this.itemName + " Armor with " + this.protection + " protection!";
		break;
		
		case 2: this.typeItem = "Weapon";
		Weapon w = new Weapon();
		w.getWeapon();
		this.weaponDamage = w.damage;
		this.itemName = w.name;
		this.boardName = w.boardName;
		this.pickUpMessage = "You picked up " + this.itemName + " with a damage of " + this.weaponDamage;
		break;
	
		case 3: this.typeItem = "Food";
			Food food = new Food();
			food.getFood();
			this.boardName = food.boardName;
			this.itemName = food.name;
			this.foodStrength = food.foodStrength;
			this.pickUpMessage = "You picked up " + this.itemName + " with a food strength of " + this.foodStrength;
			break;
		

		case 4: this.typeItem = "Ring";
			Ring ring = new Ring();
			ring.getRing();
			this.boardName = ring.boardName;
			this.itemName = ring.name;
			this.pickUpMessage = "You picked up the " + this.itemName + " Ring."; 
			break;
			
		case 5: this.typeItem = "Scrolls";
			Scrolls scroll = new Scrolls();
			scroll.getScrolls();
			this.boardName = scroll.boardName;
			this.itemName = scroll.name;
			this.pickUpMessage = "You picked up the " + this.itemName + " Scroll.";
			break;
			
		case 6: this.typeItem = "Wand";
			Wand wand = new Wand();
			wand.getWand();
			this.boardName = wand.boardName;
			this.itemName = wand.name;
			this.pickUpMessage = "You picked up the " + this.itemName + " Wand.";
			break;

		case 7: this.typeItem = "Potions";
			Potions p = new Potions();
			p.getPotion();
			this.boardName = p.boardName;
			this.itemName = p.name;
			this.pickUpMessage = "You picked up the " + this.itemName + " Potion.";
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

	public String getBoardName() {
		return this.boardName;
	}

	public int getFoodStrength() {
		return this.foodStrength;
	}

	public String getPickUpMessage() {
		return this.pickUpMessage;
	}






}
>>>>>>> master
