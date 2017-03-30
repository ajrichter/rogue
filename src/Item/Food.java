package Item;

public class Food extends Item {
	
	public Food(String name, String typeItem) {
		super(name, typeItem);

	}
	
	
	
	
	
	public void getStrengthFromFood(String name)
	{
		if (name.equalsIgnoreCase("Fruit Snacks"))
		{
			strength = 1;
		}
		else if (name.equalsIgnoreCase("Banana"))
		{
			strength = 2;
		}
		else if (name.equalsIgnoreCase("Oatmeal"))
		{
			strength = 4;
		}
		else if (name.equalsIgnoreCase("Eggs"))
		{
			strength = 8;
		}
		else if (name.equalsIgnoreCase("Steak"))
		{
			strength = 16;
		}
		else if (name.equalsIgnoreCase("Trump Steaks"))
		{
			strength = 32;
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
