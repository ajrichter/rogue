package Item;

public class Weapon extends Item {
		public Weapon(String name, String typeItem) {
			super(name, typeItem);

		}
		
		
		
		
		
		public void getDamageFromWeapon(String name)
		{
			
			
			
			/** The weapon damage are temporary values! **/
			if (name.equalsIgnoreCase("Mace"))
			{
				damage = 1;
			}
			else if (name.equalsIgnoreCase("Long Sword"))
			{
				damage = 4;
			}
			else if (name.equalsIgnoreCase("Short Bow"))
			{
				damage = 8;
			}
			else if (name.equalsIgnoreCase("Arrow"))
			{
				damage = 16;
			}
			else if (name.equalsIgnoreCase("Dagger"))
			{
				damage = 32;
			}
			else if (name.equalsIgnoreCase("Rock"))
			{
				damage = 5000000;
			}
			else if (name.equalsIgnoreCase("Two-Handed Sword"))
			{
				damage = 5000000;
			}
			else if (name.equalsIgnoreCase("Sling"))
			{
				damage = 5000000;
			}
			else if (name.equalsIgnoreCase("Dart"))
			{
				damage = 5000000;
			}
			else if (name.equalsIgnoreCase("Crossbow"))
			{
				damage = 5000000;
			}
			else if (name.equalsIgnoreCase("Crossbow bolt"))
			{
				damage = 5000000;
			}
			else if (name.equalsIgnoreCase("Spear"))
			{
				
			}
			else
			{
				damage = 0;
			}
	
		}	
		
		public int getDamage() {
			return damage;
		}
		
		
		
		
		
	}
















