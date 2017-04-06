import java.util.Random;

import Item.Item;

public class Level {
	
	// Commit vs. push
	
	private String[][] floorSeen;
	private Unit[][] floorUnits;
	private Item[][] floorItems;
	private String[][] floorTraps;
	
	public Level(Unit player){
		makeLevel();
		
		floorUnits= new Unit[24][80];
		floorItems= new Item[24][80];
		
		Enemy e= new Enemy();
		addUnit(player);
		addUnit(e);
	}
	
	public String[][] getSeenFloor(){
		return floorSeen;
	}
	
	//Small for prototype
	//test comment
	//another test comment
	public void makeLevel(){
		this.floorSeen = new String[24][80];
		//should probably call makeroom()
		/*
		this.floorSeen= new String[20][20];
		for(int i=0; i<20;i++){
			for(int k=0; k<20;k++){
				if(i==0 || i==19){
					this.floorSeen[i][k]= "=";
				}else if(k==0 ||k==19){
					this.floorSeen[i][k]="|";
				}else{
					this.floorSeen[i][k]=".";
				}
			}
		}
		*/
	}
	
	//makes one room
	private void makeRoom(){
		//you would probably want this to make an object
		//there are DARK and LIT room
		//seems like the max size of a room is 7x25 (let's make upper limit 8x26
		
		Random rand = new Random();
		int heightRand = rand.nextInt(6) + 1;
		int widthRand = rand.nextInt(24) + 1;
		for(int i = 0; i < heightRand; i++) {
			for(int j = 0; j < widthRand; j++) {
				if(i == 0 || k == widthrand -)
			}
		}
		
		//TODO
	}
	
	//Not implemented in prototype
	private void makeHallway(){	
		//TODO
	}
	
	//adds a unit to the level
	private void addUnit(Unit u){
		
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
	public boolean validMove(Unit u, int[] dir){
		
		int[] location= unitLocation(u);
		
		//if out of bound
		if(location[0]+dir[0]<0 || 
		   location[0]+dir[0]>(floorUnits.length-1)||
		   location[1]+dir[1]<0 ||
		   location[1]+dir[1]>(floorUnits[0].length-1)){
			return false;
		}else{
			//check if piece of floor
			if(floorSeen[location[1]+dir[1]][location[0]+dir[0]].equals(".")|| 
			   floorSeen[location[1]+dir[1]][location[0]+dir[0]].equals("A")){
				return true;
			}
		}
		
		return false;
	}
	
	//if player hits enemy
	public Enemy isEnemy(Unit u, int[] dir){
		int[] location= unitLocation(u);
		
		//if out of bound
		if(location[0]+dir[0]<0 || 
		   location[0]+dir[0]>(floorUnits.length-1)||
		   location[1]+dir[1]<0 ||
		   location[1]+dir[1]>(floorUnits[0].length-1)){
			return null;
		}else{
			//check if piece of floor
			if(floorSeen[location[1]+dir[1]][location[0]+dir[0]].equals("E")){
				return (Enemy) floorUnits[location[1]+dir[1]][location[0]+dir[0]];
			}
		}
		
		return null;
	}
	
	//if player hits enemy
		public Item isItem(Unit u, int[] dir){
			int[] location= unitLocation(u);
			
			//if out of bound
			if(location[0]+dir[0]<0 || 
			   location[0]+dir[0]>(floorUnits.length-1)||
			   location[1]+dir[1]<0 ||
			   location[1]+dir[1]>(floorUnits[0].length-1)){
				return null;
			}else{
				//check if piece of floor
				if(floorSeen[location[1]+dir[1]][location[0]+dir[0]].equals("A")){
					return (Item) floorItems[location[1]+dir[1]][location[0]+dir[0]];
				}
			}
			
			return null;
		}
	
	public int[] unitLocation(Unit u){	
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
	public boolean moveUnit(Unit u, int[] dir){
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
	public void removeUnit(Unit u){
		int[] loc= unitLocation(u);
		this.floorUnits[loc[1]][loc[0]]=null;
		if(this.floorItems[loc[1]][loc[0]]==null){
			this.floorSeen[loc[1]][loc[0]]=".";
		}
	}
	
	//picks up an item
	public void pickUp(Unit u, int[] direction){
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
