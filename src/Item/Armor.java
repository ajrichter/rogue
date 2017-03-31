package Item;

public class Armor extends Item {
	/**
	 * 
	 * @param name
	 * @param typeItem
	 */
	
	
	public Armor(String name, String typeItem) {
		super(name, typeItem);
		
	}
	
	public void getArmorProtection(String name) {
	
	if (name.equalsIgnoreCase("Leather armor"))
	{
		carnation = "Cursed";
		armorProtection = 2;
	}
	else if (name.equalsIgnoreCase("Studded Leather / Ring mail"))
	{
		carnation = "Normal";
		armorProtection = 3;
	}
	else if (name.equalsIgnoreCase("Scale Mail"))
	{
		carnation = "Enchanted";
		armorProtection =  4;
	}
	else if (name.equalsIgnoreCase("Chain Mail"))
	{
		carnation = "Cursed";
		armorProtection =  5;
	}
	else if (name.equalsIgnoreCase("Branded mail / Splint mail"))
	{
		carnation = "Normal";
		armorProtection = 6;
	}
	else if (name.equalsIgnoreCase("Plate mail"))
	{
		carnation = "Enchanted";
		armorProtection = 7;
	}
	else {	
	armorProtection = 0;
	}
	
}
	
public int getArmorProtection()
{
	return armorProtection;
}
	
public String getStatus()
{
	return carnation;
}

	
}
