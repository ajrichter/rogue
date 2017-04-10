package Item;

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



	public void genItem()
	{
		DiceRollerItem d= new DiceRollerItem();
		switch (d.rollDie(3)) { 
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
		
		default:
			this.typeItem = "NOP";
		break;
		
		
		
		
////		case 4: this.typeItem = "Ring";
////			Ring ring = new Ring();
////			this.boardName = ring.boardName;
////			
////		break;
////		case 5: this.typeItem = "Scrolls";
////			Scrolls scroll = new Scrolls();
////			this.boardName = scroll.boardName;
////
////		case 6: this.typeItem = "Wand";
////			Wand wand = new Wand();
////			this.boardName = wand.boardName;
////			
////		break;
////
////		case 7: this.typeItem = "Potions";
////			Potions p = new Potions();
////			this.boardName = p.boardName;
//
//		break;



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
