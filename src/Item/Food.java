package Item;

public class Food extends Item {
	
	
	public Food() {
		this.boardName = ":";
		this.getFood();
	}
	
	
	
	public void getFood()
	{
		DiceRollerItem d= new DiceRollerItem();
		switch (d.rollDie(8)) { 
		case 1: this.name = "Pizza";
		this.foodStrength = 4;
		break;
		
		case 2: this.name = "Hot Dog";
		this.foodStrength = 5;
		break;
		
		case 3: this.name = "Hamburger";
		this.foodStrength = 6;
		break;
		
		case 4: this.name = "Pancakes";
		this.foodStrength = 1;
		break;
		
		case 5: this.name = "Chicken Wings";
		this.foodStrength = 7;
		break;
		
		case 6: this.name = "Waffles";
		this.foodStrength = 2;
		break;
		
		case 7: this.name = "Steak";
		this.foodStrength= 8; 
		break;		
		
		case 8: this.name = "Spaghetti";		
		this.foodStrength = 3;		
		break;
		
		default:
			this.name= "This food has an unusual taste!";
			break;
		}
		
		
		
		
	
	}	
	
	
}
