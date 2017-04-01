package Item;
public class UnitForItem {

	protected int statusTime;
	protected int level;
	protected int strength;
	protected int hp;
	protected int maxHP;
	protected int armor;
	public boolean isDead=false;
	protected String name;
	protected String boardName;
	
	public UnitForItem() {
		// TODO constructor and stuff
	}
	
	public String toString(){
		return boardName;
	}
	
	public String getName(){
		return name;
	}
	
	//returns armor
	public int getAC(){
		return this.armor;
	}
	
	
	
	public void move(int[] dir) {
		// TODO figure out this direction thing
		//Call level.move(Player, Direction)?
	}
	
	public boolean isDead() {
		// TODO write the code and maybe add in an attribute
		
		return false;
	}
}