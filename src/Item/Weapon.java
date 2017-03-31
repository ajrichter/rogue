package Item;

public class Weapon extends Item {
	DiceRollerItem dice = new DiceRollerItem();
	
	private String name;
	private String typeItem;
	public Weapon (String name, String typeItem) { 
		super(name, typeItem);
		
		this.boardName= ")";
		this.name = name;
		this.typeItem = typeItem;
	}

	
	public void getDamageFromWeapon()
		{
			if (name.equalsIgnoreCase("Mace"))
			{
				
				damage = dice.rollDie(4) + dice.rollDie(4); //2d4 damage 
			}
			else if (name.equalsIgnoreCase("Long Sword"))
			{
				damage = dice.rollDie(10); //1d10 damage
			}
			else if (name.equalsIgnoreCase("Short Bow"))
			{
				damage = 1; //1d1 damage 
			}
			else if (name.equalsIgnoreCase("Arrow"))
			{
				damage = 1; //1d1 damage
			}
			else if (name.equalsIgnoreCase("Dagger"))
			{
				damage = dice.rollDie(6); //1d6 damage  
			}
			else if (name.equalsIgnoreCase("Rock"))
			{
				damage = dice.rollDie(2); 
			}
			else if (name.equalsIgnoreCase("Two-Handed Sword"))
			{
				damage = dice.rollDie(6) + dice.rollDie(6) + dice.rollDie(6); 
			}
			else if (name.equalsIgnoreCase("Sling"))
			{
				damage = 0;
			}
			else if (name.equalsIgnoreCase("Dart"))
			{
				damage = 1; 
			}
			else if (name.equalsIgnoreCase("Crossbow"))
			{
				damage = 1;
			}
			else if (name.equalsIgnoreCase("Crossbow bolt"))
			{
				damage = dice.rollDie(2);
			}
			else if (name.equalsIgnoreCase("Spear"))
			{
				damage = dice.rollDie(8);
			}
	
		}	
		
		public int getDamage() {
			return damage;
		}
		
	
		
		
	}














