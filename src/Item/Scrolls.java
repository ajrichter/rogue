package Item;

public class Scrolls extends Item {
	
	public Scrolls(String name, String typeItem) {
		super(name, typeItem);

	}
	/** Here are the actual scrolls from the game: **/
	//Confuse
	//Light
	//Armor
	//Hold
	//Sleep
	//Create
	//Ident
	//Map
	//GFIND
	//TELEP - Teleport
	//ENCH 
	//Scare
	//Remove
	//Aggr
	//NOP
	//Genocide
	
	
	/**Values like Barrelene are only temporary values. **/
	public void getEffectsFromScrolls(String name)
	{
		if (name.equalsIgnoreCase("Barrelene"))
		{
			strength = -10;
			damage = 10;
		}
		else if (name.equalsIgnoreCase("Basketane"))
		{
			strength = 20;
			armorProtection = -5;
		}
		else if (name.equalsIgnoreCase("Cubane"))
		{
			
			
		}
		else if (name.equalsIgnoreCase("Olympiadane"))
		{
	
		}
		else if (name.equalsIgnoreCase("Penguinone"))
		{
			
		}
		else if (name.equalsIgnoreCase("Sulflower"))
		{
		
		}
		
	}	
	
}










