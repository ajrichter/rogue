package Item;

import java.util.HashMap;
/**
 * 
 * @author Harry
 *
 */

public class Item {
	protected int armorProtection = 0;
	protected int strength = 0;
	protected int damage = 0;
	protected String carnation; //Cursed, blessed, or normal
	protected String message;
	protected String weaponName;
	protected String boardName;
	protected HashMap <String, String> item;
	private static String name; //name of item
	private static String typeItem; //armor,equipment,ring,food,

		
	public Item(String name, String typeItem){
		Item.setName(name);
		Item.setTypeItem(typeItem);
	}
	

	public void addItem(Item i) {
		item = new HashMap <String, String>();
		item.put(i.getName(), i.typeItem());
	}
	
	public String getItem(String key) 
	{
		return item.get(key);
	}
	
	
	
	//takes up time to use items
	protected void wasteTime(){
		//Todo
	}
	
	//returns type item
	protected String typeItem(){
		return getTypeItem();
	}

	public static String getTypeItem() {
		return typeItem;
	}


	

	
	public static void setTypeItem(String typeItem) {
		Item.typeItem = typeItem;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return boardName;
	}
	
	
	public static void setName(String name) {
		Item.name = name;
	}
}