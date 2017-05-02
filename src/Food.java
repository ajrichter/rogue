

import java.util.Random;

public class Food extends Item {
	
	
	public Food() {
		this.boardName = ':';
		this.getFood();
	}
	
	
	
	public void getFood()
	{
	
		DiceRoller d= new DiceRoller();
		switch (d.rollDie(8)) { 
		case 1: this.name = "Kale";
		this.playerHunger = 40;
		break;
		
		case 2: this.name = "Lettuce";
		this.playerHunger = 50;
		break;
		
		case 3: this.name = "Brussel Sprouts";
		this.playerHunger = 60;
		break;
		
		case 4: this.name = "Prune Juice";
		this.playerHunger = 10;
		break;
		
		case 5: this.name = "Chicken";
		this.playerHunger = 70;
		break;
		
		case 6: this.name = "Waffles";
		this.playerHunger = 20;
		break;
		
		case 7: this.name = "Steak";
		this.playerHunger= 80; 
		break;		
		
		case 8: this.name = "Spaghetti";		
		this.playerHunger = 30;		
		break;
		
		case 9: this.name = "Lobster";
		this.playerHunger = 90;
		break;
		
		
		default:
			this.name= "This food has an unusual taste!";
			break;
		}
		
		
		
		
	
	}	
	
	
}


