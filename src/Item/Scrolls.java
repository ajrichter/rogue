package Item;
public class Scrolls extends Item {


	public Scrolls() {
		this.boardName = "?";
		this.getScrolls();
	}


	public void getScrolls()
	{
		DiceRollerItem d= new DiceRollerItem();

		switch (d.rollDie(16))
		{
		case 1: this.name = "Consfuse";
		this.scrollMessage = "Your hands begin to glow red";
		break;

		case 2: this.name = "Light";
		this.scrollMessage = "The corridor glows and then fades";
		break;

		case 3: this.name = "Armor";
		this.scrollMessage = "Your armor glows faintly for a moment";
		break;

		case 4: this.name = "Hold";
		this.scrollMessage = "You stay!";
		break;

		case 5: this.name = "Sleep";
		this.scrollMessage = "You fall asleep";
		break;

		case 6: this.name = "Create";
		this.scrollMessage = "You hear a faint cry of anguish in the distance.";
		break;

		case 7: this.name = "Ident";
		this.scrollMessage = "This scroll is an identify scroll";
		break;

		case 8: this.name = "Map";
		this.scrollMessage = "Oh, now this scroll has a map on it.";
		break;

		case 9: this.name = "GFIND";
		this.scrollMessage = "You begin to feel greedy and you sense gold.";
		break;

		case 10: this.name = "Teleport";
		this.scrollMessage = "You are transported";
		break;

		case 11: this.name = "Ench";
		this.scrollMessage = "Your weapon is enchanted!";
		break;

		case 12: this.name = "Scare";
		this.scrollMessage = "You hear maniacal laughter in the distance.";
		break;

		case 13: this.name = "Remove";
		this.scrollMessage = "You feel as if somebody is watching over you.";
		break;

		case 14: this.name = "Agr";
		this.scrollMessage = "You hear a high pitched humming noise.";
		break;

		case 15: this.name = "NOP";
		this.scrollMessage = "This scroll seems to be blank";
		break;

		case 16: this.name = "Genocide";
		this.scrollMessage = "You have been granted the boon of genocide";	
		break;

		default:
			this.name = "cryptography";
			this.potionMessage = "What a puzzling scroll!";
			break;
		}

	}	

}


