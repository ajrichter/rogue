import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
	Has timers for different things
	Health goes up with each Move
	Move method is for health increase and stats decrease
	Level calls player.Move
 */

public class Player extends Unit{
	// Haha what the hell is this
	protected boolean hasA;
	private boolean hasW;
	protected int gold, nexp, steps, hunger;	//changed to protected to allow access to gold
	private static Player play;
	protected ArrayList <Item> items;
	
	private Inventory inventory;
	private String narrationMessage;

	public Player(String s) {
		super();
		System.out.println("Player Constructor called");
		// the player-character is an @-symbol
		this.val = '@';
		this.name=s;
		this.gold=0;
		this.xp=0;
		this.level=1;
		this.armor=3;
		this.nexp = 20;
		this.steps = 0;
		this.hunger = 150;
		DiceRoller d= new DiceRoller();
		this.strength = d.roll(3, 6);
		this.maxHP = this.hp = d.roll(4, 6);
		this.name=s;
		inventory = new Inventory();
		items = new ArrayList <Item>();
	
	}

	/*
	 * Just name this useItem(i)?
	 */
	public void equiptOrConsumeItem(Item item) {
		switch (item.getItemType()) {
		case "Armor":
			this.armor += item.getArmorProtection();
			//narrationMessage = play.name + item.getUseMessage();
			break;
		case "Food":
			this.hunger += item.getPlayerHunger();
			this.name += item.getItemName();
			//narrationMessage = play.name + " ate " + this.name;
			//System.out.println("Player strength: " + strength);
			inventory.removeItem(item);
			break;
		case "Ring":
			//narrationMessage = play.name + item.getUseMessage();
			this.strength += item.getPlayerStrength();
			break;
		case "Potions":
			//narrationMessage = play.name + item.getUseMessage();
			inventory.removeItem(item);
			break;
		case "Scrolls":
			//narrationMessage = play.name + item.getUseMessage();
			inventory.removeItem(item);
			break;
		case "Wand":
			//narrationMessage = play.name + item.getUseMessage();
			break;
		
		
		}
	}		

	public void unequipItem(Item item) {
		switch (item.getItemType()) {
		case "Armor":
			this.armor -= item.getArmorProtection();
			narrationMessage = play.name + item.getTakeOffMessage();
			break;
		case "Ring":
			narrationMessage = play.name + item.getTakeOffMessage();
			this.strength -= item.getPlayerStrength();
			break;
		case "Wand":	
			narrationMessage = play.name + item.getTakeOffMessage();
			break;
		
		
		}
		
}

	public void dropItem(Item item) {
		inventory.removeItem(item);
		narrationMessage = play.name + item.getDropMessage();
		
	}
		
	public String getNarrationMessage() {
		return narrationMessage;
	}
	
	public void inventoryString() {
		inventory.listInventory();
	}

	public String[] playerStats(){
		String[] temp= new String[2];
		temp[0]= "Level: " + this.level + " HP: " +this.hp+ " ("+this.maxHP+") "+
		" Str: "+this.strength+ " (" + this.strength + ") Armor: "+ this.armor+
		 " Gold: "+ this.gold + " Exp: " + this.xp;
		return temp;
	}

	/*
		Regenerate HP
		Check LevelUp
		Check Hunger
		Stats decrease
	*/
	public void move(){
		steps++;
		if(steps >= 10) {
			steps = 0;
			if(hp < maxHP)
				hp++;
		}

		if(xp == nexp)
			levelUp();

		hunger--;
		chkHu();
	}

	/*
	 * This should be sent to the narration
	 */
	public void levelUp() {
		nexp = nexp * 2;
		level++;
	}
	
	public int getGold() {
		return this.gold;
	}

	public void chkHu() {
		if(hunger <= 50)
			System.out.println("You should probably stop by the Gizmo.");
		else if(hunger <= 25)
			System.out.println("You are starving!");
		else if(hunger <= 0)
			System.out.println("You have fainted.");
	}
}
