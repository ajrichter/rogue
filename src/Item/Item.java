
package Item;

/**
 * 
 * @author Harry
 *
 */

public class Item {
	protected Character boardName;
	protected String name; //name of item
	protected String typeItem; //armor,equipment,ring,food,
	protected int damage; 
	protected int armorProtection;
	protected String potionMessage;
	protected String scrollMessage;
	protected int foodStrength;
	protected String finalMessage;
	private GenItem generate;
	
	public Item() {
	generate = new GenItem();
		
	}


	public GenItem generateItem() {
		generate.genItem();
		this.typeItem = generate.getTypeItem();
		this.name = generate.getName();
		this.boardName = generate.getBoardName();
		this.finalMessage = generate.getPickUpMessage();
		if (this.typeItem.equals("Food")) {
			this.foodStrength = generate.getFoodStrength();
		}
		else if (this.typeItem.equals("Weapon"))
		{
			this.damage = generate.getWeaponDamage();
		}
		
		return generate;
	}

	public char getBoardName() {
		return this.boardName;
	}
	
	public String getPrintMessage() {
		return finalMessage;
	}
	
	public String getItemName() {
		return name;
	}
	
	public String getItemType() {
		return typeItem;
	}

	





}