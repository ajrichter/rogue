package Item;

public class Armor extends Item {


	public Armor () {
		this.boardName = "]";		
		this.getArmor();
	}

	public void getArmor() {
		DiceRollerItem d= new DiceRollerItem();
		switch (d.rollDie(6)) { 
		case 1: this.armorProtection = 2;
		this.name = "Leather armor";
		break;

		case 2: this.armorProtection = 4;
		this.name = "Studded Leather / Ring mail";
		break;
		case 3: this.armorProtection = 8;
		this.name =  "Scale Mail";	
		break;
		case 4: this.armorProtection = 16;
		this.name = "Chain Mail";
		break;
		case 5: this.armorProtection = 32;
		this.name = "Branded mail / Splint mail";
		break;
		case 6: this.armorProtection = 64;
		this.name = "Plate mail";
		break;
		default:
			this.name = "NOP";
			break;
		}
	}
	
		



}