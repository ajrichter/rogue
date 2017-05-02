

public class Armor extends Item {


	public Armor () {
		this.boardName = ']';		
		this.getArmor();
	}

	//Note: The names and armor protections are correct based on this source:
	//https://strategywiki.org/wiki/Rogue/Items
	public void getArmor() {
		DiceRoller d= new DiceRoller();
		switch (d.rollDie(8)) { 
		
		case 1: this.name = "Leather";
			this.armorProtection = 4;
		break;

		case 2: this.name = "Studded Leather";
			this.armorProtection = 4;
			break;
		
		case 3: this.name = "Ring Mail";
			this.armorProtection = 4;
		break;
			
			
		case 4: this.name =  "Scale Mail";	 
			this.armorProtection = 5;
			break;
		
		case 5: this.name = "Chain Mail";
			this.armorProtection = 6;
			break;
		
		case 6: this.name = "Banded Mail"; 
			this.armorProtection = 7;
			break;
		case 7: this.name = "Splint Mail";	
			this.armorProtection = 7;
			break;
		
		case 8: this.name = "Plate Mail";
			this.armorProtection = 8;
			break;
		
		default:
			this.name = "NOP";
			break;
		}
	}
	
		




}