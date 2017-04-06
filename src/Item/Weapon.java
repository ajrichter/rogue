package Item;

public class Weapon extends Item {
	DiceRollerItem dice = new DiceRollerItem();

	public Weapon (String name, String typeItem) { 
		super(name, typeItem);
		
		this.boardName= ")";
		this.name = name;
		this.typeItem = typeItem;
	}

	
	public void getDamageFromWeapon(Item w)
		{
			if (w.name.equalsIgnoreCase("Mace"))
			{
				
				damage = dice.rollDie(4) + dice.rollDie(4); //2d4 damage 
			}
			else if (w.name.equalsIgnoreCase("Long Sword"))
			{
				damage = dice.rollDie(10); //1d10 damagvar=0;e
			}
			else if (w.name.equalsIgnoreCase("Short Bow"))
			{
				damage = 1; //1d1 damage 
			}
			else if (w.name.equalsIgnoreCase("Arrow"))
			{
				damage = 1; //1d1 damage
			}
			else if (w.name.equalsIgnoreCase("Dagger"))
			{
				damage = dice.rollDie(6); //1d6 damage  
			}
			else if (w.name.equalsIgnoreCase("Rock"))
			{
				damage = dice.rollDie(2); 
			}
			else if (w.name.equalsIgnoreCase("Two-Handed Sword"))
			{
				damage = dice.rollDie(6) + dice.rollDie(6) + dice.rollDie(6); 
			}
			else if (w.name.equalsIgnoreCase("Sling"))
			{
				damage = 0;
			}
			else if (w.name.equalsIgnoreCase("Dart"))
			{
				damage = 1; 
			}
			else if (w.name.equalsIgnoreCase("Crossbow"))
			{
				damage = 1;
			}
			else if (w.name.equalsIgnoreCase("Crossbow bolt"))
			{
				damage = dice.rollDie(2);
			}
			else if (w.name.equalsIgnoreCase("Spear"))
			{
				damage = dice.rollDie(8);
			}
	
		}	
		
		public int getDamage() {
			return damage;
		}
		
	
		
		
	}














