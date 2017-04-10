import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import Item.Item;

public class Level {
	// New Outline for Level
	private boolean[][] isSeen;
	/* We need a way to represent all the enemies such that
	they can be defeated or added each time you enter a new room
	*/
	// Can't use a queue because then can't access the enemies location when needed.
	private Queue<Enemy> enemies = new ArrayDeque<Enemy>();
	private int numEnemies;

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
	class Rm {
		private int x1, x2,  y1, y2, w, h;

		public Rm() {
			h = ThreadLocalRandom.current().nextInt(4, 8 + 1);
			w = ThreadLocalRandom.current().nextInt(4, 26 + 1);
		}

		private void set(int x, int y){
			x1 = x;
			y1 = y;
			x2 = x1 + w;
			y2 = y1 + h;
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

		Rm r1 = new Rm();
		int x = ThreadLocalRandom.current().nextInt(0, 79 + 1);
		int y = ThreadLocalRandom.current().nextInt(0, 23 + 1);

		for(int w = 0; w < r1.w; w++){
			floor[][]
			floor[][]
		}

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

	private void makeRooms() {

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

	//makes one room
	private void makeRoom(){
		//you would probably want this to make an object
		//there are DARK and LIT room
		//seems like the max size of a room is 7x25 (let's make upper limit 8x26

		//this is moved to the room class

		//TODO
	}

	//Not implemented in prototype
	private void makeHallway(){
		//TODO
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
