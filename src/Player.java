import java.util.Random;

public class Player extends Unit{
	
	//curArmor
	//curWeapon
	//curRings
	private int gold;
	private int exp;
	//Hunger
	//private Inventory inventory;

	
	public Player() {
		
		DiceRoller d6= new DiceRoller();
		
		//always set
		this.boardName="@";
		this.name="Madison";
		this.gold=0;
		this.exp=0;
		this.level=1;
		this.armor=3;
		
		//random
		this.strength= d6.rollDie(6)+d6.rollDie(6)+d6.rollDie(6);
		this.maxHP= d6.rollDie(6)+ d6.rollDie(6)+d6.rollDie(6)+d6.rollDie(6);
		this.hp=this.maxHP;
	}
	
	
	public String[] playerStats(){
		String[] temp= new String[2];
		temp[0]= "Level: " + this.level + " Hits: " +this.hp+ " ("+this.maxHP+") "+ " Str: "+this.strength+ " (" + this.strength + ") Armor: "+ this.armor+ " Gold: "+ gold + " Exp: " + this.exp;
		return temp;
	}
	
	public void checkLevelUp() {
		// TODO
	}
	
	public void checkHunger() {
		// TODO
	}
	
}
