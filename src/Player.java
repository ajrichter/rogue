import Item.Inventory;
import Item.Item;

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
	private int gold, nexp, steps, hunger;
	private static Player play;
	private Inventory inventory;

	public Player(){
		this("John Dooley");
	}

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
	}

	public void pickUpAndStoreInI(Item item) {
		inventory.addItem(item);

		if (item.getItemType().equals("Armor"))
		{
			this.armor += item.getArmorProtection();
			System.out.println(play.name + item.getPickUpMesage());
		}
		else if (item.getItemType().equals("Food"))
		{
			this.strength += item.getPlayerStrength();
			System.out.println(play.name + item.getPickUpMesage());
		}
		else if (item.getItemType().equals("Ring"))
		{
			System.out.println(play.name + item.getPickUpMesage());
			this.strength += item.getPlayerStrength();

		}
		else if (item.getItemType().equals("Potions"))
		{
			System.out.println(play.name + item.getPickUpMesage());

		}

		else if (item.getItemType().equals("Scrolls"))
		{
			System.out.println(play.name + item.getPickUpMesage());
		}

		else if (item.getItemType().equals("Wand"))
		{
			System.out.println(play.name + item.getPickUpMesage());
		}	
	}



	public void equiptOrConsumeItem(Item item) {

		if (item.getItemType().equals("Armor"))
		{
			this.armor += item.getArmorProtection();
			System.out.println(play.name + item.getUseMessage());
		}
		else if (item.getItemType().equals("Food"))
		{
			System.out.println("Player strength: " + strength);
			this.strength += item.getPlayerStrength();
			System.out.println(play.name + item.getUseMessage());
			System.out.println("Player strength: " + strength);
			inventory.removeItem(item);
		}
		else if (item.getItemType().equals("Ring"))
		{
			System.out.println(play.name + item.getUseMessage());
			this.strength += item.getPlayerStrength();

		}
		else if (item.getItemType().equals("Potions"))
		{
			System.out.println(play.name + item.getUseMessage());
			inventory.removeItem(item);
		}

		else if (item.getItemType().equals("Scrolls"))
		{
			System.out.println(play.name + item.getUseMessage());
			inventory.removeItem(item);
		}

		else if (item.getItemType().equals("Wand"))
		{
			System.out.println(play.name + item.getUseMessage());
		}
	}		

	public void unequipItem(Item item) {

		if (item.getItemType().equals("Armor"))
		{
			this.armor -= item.getArmorProtection();
			System.out.println(play.name + item.getTakeOffMessage());
		}

		else if (item.getItemType().equals("Ring"))
		{
			System.out.println(play.name + item.getTakeOffMessage());
			this.strength -= item.getPlayerStrength();
		}

		else if (item.getItemType().equals("Wand"))
		{
			System.out.println(play.name + item.getTakeOffMessage());
		}	




	}






	public void dropItem(Item item) {
		
		
		
		if (item.getItemType().equals("Armor"))
		{
			this.armor -= item.getArmorProtection();
			System.out.println(play.name + item.getDropMessage());
			inventory.removeItem(item);
		}
		else if (item.getItemType().equals("Food"))
		{
			this.strength -= item.getPlayerStrength();
			System.out.println(play.name + item.getDropMessage());
			System.out.println("Player strength: " + strength);
			inventory.removeItem(item);
		}
		else if (item.getItemType().equals("Ring"))
		{
			System.out.println(play.name + item.getDropMessage());
			this.strength -= item.getPlayerStrength();
			inventory.removeItem(item);
		}
		else if (item.getItemType().equals("Potions"))
		{
			System.out.println(play.name + item.getDropMessage());
			inventory.removeItem(item);
		}

		else if (item.getItemType().equals("Scrolls"))
		{
			System.out.println(play.name + item.getDropMessage());
			inventory.removeItem(item);
		}

		else if (item.getItemType().equals("Wand"))
		{
			System.out.println(play.name + item.getDropMessage());
			inventory.removeItem(item);
		}	
	}

	public String[] playerStats(){
		String[] temp= new String[2];
		temp[0]= "Level: " + this.level + " HP: " +this.hp+ " ("+this.maxHP+") "+
		" Str: "+this.strength+ " (" + this.strength + ") Armor: "+ this.armor+
		 " Gold: "+ gold + " Exp: " + this.xp;
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

	public void levelUp() {
		nexp = nexp * 2;
		level++;
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
