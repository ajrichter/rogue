import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import Item.Item;

public class Level {
	private String[][] floorSeen;

	/*
   *	     ---+----
   * 		 | . . +
   * 		 | . . |
   *		 --------
	*/
	private Unit[][] floorUnits;
	private Item[][] floorItems;
	private String[][] floorTraps;
	private Room[] rooms;

	// Andrew's  Instance Variables
	private int numLevel;
	private int numR;
	private char[][] floor;
	private boolean[][] isSeen;

	/*	The maximum room size is
		25 wide * 7 high
		--------------------+-
		|.......................|
		|.......................|
		+.......................|
		|.......................+
		|.......................|
		----------------------
	*/

	// Maximum Room dimensions are 8*26, h*w
	// Maximum Room dimensions are 4*4, with walls and 4 squares to move in.
	// x = w, y = h
	// Size needs to be a DiceRoller beacuse it should be different Probabilities
	class Rm {
		private int x1, x2,  y1, y2, w, h;

		public Rm() {
			w = ThreadLocalRandom.current().nextInt(4, 26 + 1);
			h = ThreadLocalRandom.current().nextInt(4, 8 + 1);
		}

		private void set(int x, int y){
			x1 = x;
			y1 = y;
			x2 = x1 + w - 1;
			y2 = y1 + h - 1;
		}
	}

	// Andrew's Level Generator
	/* Generate a number of rooms
		? should I generate the size of the room as a factor of the number of rooms
		? yes because this is more dynamic and makes it more interesting
		Using inner Room class, Generate a random room
		First room is placed randomly
		Second and following rooms are placed after checking to see if there square is open
		Try to fit it in the array
		If it doesn't fit, make a new room and repeat
		Repeat for the number of rooms
	*/
	public Level(int nL) {
		numLevel = nL;
		numR = ThreadLocalRandom.current().nextInt(4, 8 + 1);

		isSeen = new boolean[24][80];
		floor = new char[24][80];
		// set everything to 0, which means empty
		for(int y = 0; y < 24; y++){
			for(int x = 0; x < 80; x++){
				floor[y][x] = '0';
			}
		}
		makeRooms();
	}

	public Level(Unit player){
		makeLevel();

		floorUnits= new Unit[24][80];
		floorItems= new Item[24][80];

		rooms = new Room[9];	//9 possible rooms

		Enemy e= new Enemy();
		addUnit(player);
		addUnit(e);
	}

	public void makeRooms(){
		Rm r1 = new Rm();
		r1.set(ThreadLocalRandom.current().nextInt(0, 79 - r1.w + 1), ThreadLocalRandom.current().nextInt(0, 23 - r1.h + 1))
		insertRoom(r1);

		// now make new rooms and check if they fit
		// Add a new method in here to make the room find a spot that fits
		// should all rooms just be within their 1/9th of the grid
		// Yes. This makes much more sense
		// So put it in a random spot in its 1/9th, not on the whoel grid
		// Move through the 9 and random boolean concerning if it isor is not a room
		// Then do doors/hallways
		for(int r = 0; r < numR -1; r++){
			Rm rx = new Rm();
			rx.set(ThreadLocalRandom.current().nextInt(0, 79 - r1.w + 1), ThreadLocalRandom.current().nextInt(0, 23 - r1.h + 1))
			while(!fits(rx)){

			}
			insertRoom(rx);
		}
	}

	private boolean fits(Rm r){
		// Ensure there is one extra space around the room for hallways
		for (int y = r.y1-1; y < r.y2+2; y++){
			for(int x = r.x1-1; x < r.x2 + 2; x++){
				if(floor[y][x] != '0')
					return false;
			}
		}
		return true;
	}
	private void insertRoom(Rm r){
		// make top and part of a room
		for(int w = 0; w < r.w; w++){
			floor[r.y1][w] = '-';
			floor[r.y2][w] ='-';
		}
		// make inner room and sidewalls
		for(int h = r.y1+1; h < r.y2; h++){
			floor[h][r.x1] = '|';
			for(int w = r.x1+1; w < r.x2; w++){
				floor[h][w] = '.';
			}
			floor[h][x2] ='|';
		}
	}

	//Small for prototype
	// Yes, I can tell
	public void makeLevel(){
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
	}

	public char[][] showL(){
		// make the floor dynamically here and return one array
		// combine all of the arrays
		return floor;
	}

	public boolean[][] getSeen(){
		return isSeen;
	}

	public char[][] getFloor() {
		return floor;
	}

	public String[][] getSeenFloor(){
		return floorSeen;
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
			if(Character.isLetter(floorSeen[location[1]+dir[1]][location[0]+dir[0]].charAt(0))){
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
