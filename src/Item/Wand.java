package Item;
public class Wand extends Item {
	private String name;
	private String typeItem;
	public Wand(String name1, String typeItem1) {
			super(name1, typeItem1);
			name = name1;
			typeItem = typeItem1;
		}

		public void getEffectFromRings(String name)
		{
			if (name.equalsIgnoreCase("Light"))
			{
				
			}
			else if (name.equalsIgnoreCase("Drain"))
			{
				
			}
			else if (name.equalsIgnoreCase("Missle"))
			{
				
			}
			else if (name.equalsIgnoreCase("Hit"))
			{
				
			}
			else if (name.equalsIgnoreCase("Haste"))
			{
			
			}
			else if (name.equalsIgnoreCase("Elect"))
			{
				
			}
			else
			{
			
			}
		
		
		}	
		
		
}
