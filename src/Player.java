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
	
	protected Inventory inventory;
	private String narrationMessage = "";

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
		this.maxHunger = 2000;
		this.hunger = 200;
		DiceRoller d= new DiceRoller();
		this.strength = d.roll(3, 6);
		this.maxHP = this.hp = d.roll(4, 6);
		this.name=s;
		inventory = new Inventory();
		items = new ArrayList <Item>();
	
	}

	
	public void useItem(Item item) {
		switch (item.getItemType()) {
		case "Weapon":
			inventory.removeItem(item);
		
		case "Armor":
			this.armor += item.getArmorProtection();
			//narrationMessage = play.name + item.getUseMessage();
			inventory.removeItem(item);
			break;
		case "Food":
			this.hunger += item.getPlayerHunger();

			//narrationMessage = play.name + " ate " + this.name;
			//System.out.println("Player strength: " + strength);
			inventory.removeItem(item);
			break;
		case "Ring":
			//narrationMessage = play.name + item.getUseMessage();
			this.strength += item.getPlayerStrength();
			inventory.removeItem(item);
			break;
		case "Potions":
			//narrationMessage = play.name + item.getUseMessage();
			this.strength += item.getPlayerStrength();
			inventory.removeItem(item);
			break;
		case "Scrolls":
			//narrationMessage = play.name + item.getUseMessage();
			inventory.removeItem(item);
			break;
		case "Wand":
			inventory.removeItem(item);
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
		" Hunger: "+this.hunger+ " (" + this.maxHunger + ") Armor: "+ this.armor+
		 " Gold: "+ this.gold + " Exp: " + this.xp + " Str:" + this.strength + " (" + this.strength + ")";
		return temp;
	}

	/*
		Regenerate HP
		Check LevelUp
		Check Hunger
		Stats decrease
	*/
	public String move(){
		narrationMessage = "";
		if (!narrationMessage.equals("You have fainted.")) {
		steps++;
		if(steps >= 10) {
			steps = 0;
			if(hp < maxHP)
				hp++;
		}

		if(xp == nexp)
			levelUp();

		hunger--;
		}
		
		if(hunger == 50)
			narrationMessage = "You should probably stop by the Gizmo.";
		else if(hunger == 25)
			narrationMessage = "You are starving!";
		else if(hunger == 0)
			narrationMessage = "You have fainted. Game over!";
		

	return narrationMessage;
	
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

}
