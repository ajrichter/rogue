package Item;

public class Armor extends Item {

	
	public Armor(String name, String typeItem) {
		super(name, typeItem);
		this.boardName = "]";
		this.name = name;
		this.typeItem = typeItem;
	}
	
	public void getArmorProtection(Item a) {
	
	if (a.name.equalsIgnoreCase("Leather armor"))
	{
		carnation = "Cursed";
		armorProtection = 2;
	}
	else if (a.name.equalsIgnoreCase("Studded Leather / Ring mail"))
	{
		carnation = "Normal";
		armorProtection = 3;
	}
	else if (a.name.equalsIgnoreCase("Scale Mail"))
	{
		carnation = "Enchanted";
		armorProtection =  4;
	}
	else if (a.name.equalsIgnoreCase("Chain Mail"))
	{
		carnation = "Cursed";
		armorProtection =  5;
	}
	else if (a.name.equalsIgnoreCase("Branded mail / Splint mail"))
	{
		carnation = "Normal";
		armorProtection = 6;
	}
	else if (a.name.equalsIgnoreCase("Plate mail"))
	{
		carnation = "Enchanted";
		armorProtection = 7;
	}
	else {	
	armorProtection = 0;
	}
	
}
	
	public int getArmorProtection() {
		return armorProtection;
	}

	
}
