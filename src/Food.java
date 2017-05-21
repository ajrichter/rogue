



public class Food extends Item {


	public Food() {
		this.boardName = ':';
		this.getFood();
	}

	public void getFood()
	{

		DiceRoller d= new DiceRoller();
		switch (d.rollDie(9)) { 
		case 1: this.name = "Kale";
		this.playerHunger = 400;
		break;

		case 2: this.name = "Lettuce";
		this.playerHunger = 500;
		break;

		case 3: this.name = "Brussel Sprouts";
		this.playerHunger = 600;
		break;

		case 4: this.name = "Prune Juice";
		this.playerHunger = 100;
		break;

		case 5: this.name = "Chicken";
		this.playerHunger = 700;
		break;

		case 6: this.name = "Waffles";
		this.playerHunger = 200;
		break;

		case 7: this.name = "Lobster";
		this.playerHunger= 800; 
		break;		

		case 8: this.name = "Steak";		
		this.playerHunger = 1000;		
		break;

		case 9: this.name = "Slugs, Mold, and Rot";
		this.playerHunger = -200;
		break;


		default:
			this.name= "This food has an unusual taste!";
			break;
		}





	}	


}


