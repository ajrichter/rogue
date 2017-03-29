public class Item {

	private static String name; //name of item
	private static String typeItem; //armor,equipment,ring,food,
	
	public Item(String name, String typeItem){
		this.setName(name);
		this.setTypeItem(typeItem);
	}
	
	public String toString(){
		return "A";
	}
	
	//returns name of the item
	protected String showName(){
		return getName();
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

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Item.name = name;
	}
}