import Item.Item;

/*
	Create the different enemies
	Start with 5 enemies.
	addMakeEnemy() Method which randomly generates an Enemy

*/

public class Enemy extends Unit{

	//special ability
	//boolean[] flags
	private int armorClass;
	private double treasureChance;
	private int expGained;
	private int dmg;

	public Enemy() {
		//values for prototype
		this.val="E";
		this.name="Slime";
		this.armor=10;
		this.hp=20;
	}
	/*
Aquator	0	M	20	5d8	2	0d0/0d0		Rusts armor
Bat	0	F	1	1d8	3	1d2		Flies randomly
Centaur	15		17	4d8	4	1d2/1d5/1d5
Dragon	100	M	5000	10d8	-1	1d8/1d8/3d10		Ranged 6d6 flame attack
Emu	0	M	2	1d8	7	1d2
	*/
	private void genMon(){

		switch (month) {
		   case 1: this.val = "A"
		   			this.name = "Aquator";
					
					break;
		   case 2:  monthString = "February";
					break;
		   case 3:  monthString = "March";
					break;
		   case 4:  monthString = "April";
					break;
		   case 5:  monthString = "May";
					break;
		   default: monthString = "Invalid month";
					break;
	   }
	}

	// decides to drop an item and returns a random item
	// Should drop treasure
	public Item dropTreasure() {
		Item i= new Item("Amulet of Yendor",null);
		return i;
	}

}
