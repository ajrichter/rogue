package Item;
public class Scrolls extends Item {
	private String name;
	private String typeItem;
	public Scrolls(String name, String typeItem) {
		super(name, typeItem);
		this.boardName = "?";
		this.name = name; 
		this.typeItem = typeItem;
	}

	public void getEffectsFromScrolls(String name, Weapon weapon)
	{
		if (name.equalsIgnoreCase("Confuse"))
		{
	
			
		}
		else if (name.equalsIgnoreCase("Light"))
		{

		}
		else if (name.equalsIgnoreCase("Armor"))
		{
			
			
		}
		else if (name.equalsIgnoreCase("Hold"))
		{
	
		}
		else if (name.equalsIgnoreCase("Sleep"))
		{
			
		}
		else if (name.equalsIgnoreCase("Create"))
		{
		
		}
		else if (name.equalsIgnoreCase("Ident"))
		{
		
		}
		else if (name.equalsIgnoreCase("Map"))
		{
			
		}
		else if (name.equalsIgnoreCase("GFIND"))
		{
			
		}
		else if (name.equalsIgnoreCase("Teleport"))
		{
			

		}
		else if (name.equalsIgnoreCase("Ench"))
		{
			
			if (weapon == null)
			{
			message = "You feel a strange sense of loss.";
			}
			else
			{
			message = "Your " + weapon.weaponName + " glows blue for a moment";
			}
		}
		else if (name.equalsIgnoreCase("Scare"))
		{
			message = "You hear maniacal laughter in the distance.";
		}
		else if (name.equalsIgnoreCase("Remove"))
		{
			message = "You feel as if somebody is watching over you.";
		}
		else if (name.equalsIgnoreCase("Agr"))
		{
			message = "You hear a high pitched humming noise.";
		}
		else if (name.equalsIgnoreCase("NOP"))
		{
			message = "This scroll seems to be blank";
		}
		else if (name.equalsIgnoreCase("Genocide"))
		{
			message = "You have been granted the boon of genocide";	
		}
		else
		{
			message = "What a puzzling scroll!";
		}
		
	}	

	public String getMessage()
	{
		return message;
	}
	
	
	
	
	
}









