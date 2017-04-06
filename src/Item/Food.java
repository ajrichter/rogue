package Item;

public class Food extends Item {
	public Food(String name, String typeItem) {
		super(name, typeItem);
		this.boardName = ":";
	}
	
	
	
	
	
	public void getStrengthFromFood(Item f)
	{
		if (f.name.equalsIgnoreCase("Fruit Snacks"))
		{
			strength = 1;
		}
		else if (f.name.equalsIgnoreCase("Banana"))
		{
			strength = 2;
		}
		else if (f.name.equalsIgnoreCase("Oatmeal"))
		{
			strength = 4;
		}
		else if (f.name.equalsIgnoreCase("Eggs"))
		{
			strength = 8;
		}
		else if (f.name.equalsIgnoreCase("Steak"))
		{
			strength = 16;
		}
		else if (f.name.equalsIgnoreCase("Trump Steaks"))
		{
			strength = 100;
		}
		else
		{
			strength = 0;
		}
	
	}	
	
	public int getFoodStrength()
	{
		return strength;
	}
	
}
