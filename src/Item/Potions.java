package Item;
public class Potions extends Item {
		private int damage = 0;
		private String name; 
		private String typeItem;
		
		
		public Potions(String name1, String typeItem1) {
			super(name1, typeItem1);
			this.name = name1;
			this.typeItem = typeItem1;
			this.boardName = "!";
			
		}
		
	
		public void getEffectFromPotionColors(String name)
		{
			if (name.equalsIgnoreCase("Confuse"))
			{
				
			}
			else if (name.equalsIgnoreCase("Poison"))
			{
				
			}
			else if (name.equalsIgnoreCase("Healing"))
			{
				
			}
			else if (name.equalsIgnoreCase("Strength"))
			{
				
			}
			else if (name.equalsIgnoreCase("MFIND"))
			{
			
			}
			else if (name.equalsIgnoreCase("TFIND"))
			{
				
			}
			else if (name.equalsIgnoreCase("Paralyze"))
			{
				
			}
			else if (name.equalsIgnoreCase("SeeInvisable"))
			{
			
			}
			else if (name.equalsIgnoreCase("Raise"))
			{
				
			}
			else if (name.equalsIgnoreCase("XHEAL"))
			{
				
			}
			else if (name.equalsIgnoreCase("Haste"))
			{
				
			}
			else if (name.equalsIgnoreCase("Restore"))
			{
				message = "Hey, this tastes great.  It make you feel warm all over.";	
			}
			else if (name.equalsIgnoreCase("Blind"))
			{
				message = "A cloak of darkness falls around you.";	
			}
			else if (name.equalsIgnoreCase("NOP"))
			{
				message = "This potion tastes extremely dull.";
			}
			else
			{
				message = "What an odd tasting potion!";
			}
		
		
		}	
		
		
}
