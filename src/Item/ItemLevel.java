package Item;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;



public class ItemLevel {

	private String[][] floorSeen;
	private UnitForItem[][] floorUnits;
	private Item[][] floorItems;
	private String[][] floorTraps;

	public ItemLevel(UnitForItem player, Item [] items){
		makeLevel();

		floorUnits= new UnitForItem[20][20];
		floorItems= new Item[20][20];
		int [] loc1 = new int [] {10, 8};
		int [] loc2 = new int [] {12, 5};
		int [] loc3 = new int [] {5, 12};
		int [] loc4 = new int [] {11, 6};
		int [] loc5 = new int [] {6, 15};
		int [] loc6 = new int [] {10, 6};
		int [] loc7 = new int [] {6, 10};
		int [] loc8 = new int [] {5, 9};
		int [] loc9 = new int [] {9, 5};
		int [] loc10 = new int [] {7, 8};
		int [] loc11 = new int [] {4, 5};
		int [] loc12 = new int [] {5, 4};
		
		
		addItem(items[0], loc1);
		addItem(items[1], loc2);
		addItem(items[2], loc3);
		
		addItem(items[3], loc4);
		addItem(items[4], loc5);
		addItem(items[5], loc6);
		
		addItem(items[6], loc7);
		addItem(items[7], loc8);
		addItem(items[8], loc9);
		
		addItem(items[9], loc10);
		addItem(items[10], loc11);
		addItem(items[11], loc12);
		addUnit(player);
	}

	public String[][] getSeenFloor(){
		return floorSeen;
	}

	//Small for prototype
	public void makeLevel(){

		this.floorSeen= new String[20][20];
		for(int i=0; i<20;i++){
			for(int k=0; k<20;k++){
				if(i==0 || i==19){
					this.floorSeen[i][k]= "=";
				}else if(k==0 ||k==19){
					this.floorSeen[i][k]="|";
				}
				else{
					this.floorSeen[i][k]=".";
				}
			}
		}
	}

	//makes one room
	private void makeRoom(){
		//TODO
	}

	//Not implemented in prototype
	private void makeHallway(){	
		//TODO
	}

	//adds a unit to the level
	private void addUnit(UnitForItem u){

		Random rand = new Random();
		int  tempX = rand.nextInt(this.floorSeen.length);
		int tempY = rand.nextInt(this.floorSeen[0].length);

		//look for empty space on a floor
		while(this.floorSeen[tempX][tempY]!="."){
			tempX = rand.nextInt(this.floorSeen.length);
			tempY = rand.nextInt(this.floorSeen[0].length);
		}
		this.floorUnits[tempX][tempY]=u;
		this.floorSeen[tempX][tempY]=u.toString();
	}

	//checks if a move is valid or not, this method is working
	public boolean validMove(UnitForItem u, int[] dir){

		int[] location= unitLocation(u);

		//if out of bound
		if(location[0]+dir[0]<0 || 
				location[0]+dir[0]>(floorUnits.length-1)||
				location[1]+dir[1]<0 ||
				location[1]+dir[1]>(floorUnits[0].length-1)){
			return false;
		}else{
			//check if piece of floor
			String loc = floorSeen[location[1]+dir[1]][location[0]+dir[0]];
			if(loc.equals(".")||  loc.equals(")") || loc.equals("!") || loc.equals(":") || loc.equals("/") ||  loc.equals("]") ||  loc.equals("?")){
						return true;
					}
		}

		return false;
	}



	//if player hits enemy
	public Item isItem(UnitForItem u, int[] dir){
		int[] location= unitLocation(u);

		//if out of bound
		if(location[0]+dir[0]<0 || 
				location[0]+dir[0]>(floorUnits.length-1)||
				location[1]+dir[1]<0 ||
				location[1]+dir[1]>(floorUnits[0].length-1)){
			return null;
		}else{
			//check if piece of floor
			String loc = floorSeen[location[1]+dir[1]][location[0]+dir[0]];
			if(loc.equals(".")||loc.equals(")") || loc.equals("!") || loc.equals(":") || loc.equals("/") || loc.equals("=") || loc.equals("]") || loc.equals("?")  ){
				return (Item) floorItems[location[1]+dir[1]][location[0]+dir[0]];
			}
		}

		return null;
	}

	public int[] unitLocation(UnitForItem u){	
		int[] temp= new int[2];

		for(int i=0; i<floorUnits.length;i++){
			for(int k=0; k<floorUnits.length;k++){
				if(u.equals(floorUnits[i][k])){
					temp[0]=k;
					temp[1]=i;
				}
			}
		}	
		return temp;
	}

	//moves the player in a certain direction
	public boolean moveUnit(UnitForItem u, int[] dir){
		int[] location= unitLocation(u);

		if(validMove(u, dir)){
			//make previous location clear
			if(floorItems[location[1]][location[0]]==null){
				floorSeen[location[1]][location[0]]= ".";
			}else{
				floorSeen[location[1]][location[0]]=floorItems[location[1]][location[0]].toString();
			}
			floorUnits[location[1]][location[0]]= null;
			//add units to current location
			floorSeen[(location[1]+dir[1])][(location[0]+dir[0])]=u.toString();
			floorUnits[(location[1]+dir[1])][(location[0]+dir[0])]= u;
			return true;
		}

		return false;
	}

	//called by enemy who dies
	public void removeUnit(UnitForItem u){
		int[] loc= unitLocation(u);
		this.floorUnits[loc[1]][loc[0]]=null;
		if(this.floorItems[loc[1]][loc[0]]==null){
			this.floorSeen[loc[1]][loc[0]]=".";
		}
	}

	//picks up an item
	public void pickUp(UnitForItem u, int[] direction){
		int[] loc= unitLocation(u);
		this.floorItems[loc[1]+direction[1]][loc[0]+direction[0]]=null;
		this.floorSeen[loc[1]+direction[1]][loc[0]+direction[0]]=".";
	}

	//checks if player can see trap
	public void seeTrap(){
		//TODO
	}

	//checks if player can disarm a trap
	public void disarmTrap(){
		//TODO
	}

	public void addItem(Item item, int[] location){
		floorItems[location[1]][location[0]]=item;
		floorSeen[location[1]][location[0]]=item.toString();
	}

}
