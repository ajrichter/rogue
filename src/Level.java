import java.util.concurrent.ThreadLocalRandom;
import Item.Item;
import java.awt.Point;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.ArrayList;
import java.util.Collections;

/*TODO
 * Player needs to be created in GamePlay. For name purposes
 * Enemies
 * Items
 * Stairs
 * store the last point for going down a level
 * need a point for where player is
 * isLit
 */
public class Level {
	private int numLevel;
	// Number of Rooms
	private int numR;
	// The full floor
	private char[][] floor;
	// Visible part of the floor
	private boolean[][] isSeen;
	// Array of Rooms Existence
	private boolean[] rb;
	// array of rooms connected all together
	private boolean[] conn;
	// Array of Rooms
	private Rm[] rs;
	// List of enemies
	private ArrayList<Enemy> enList;
	private ArrayList<Item> items;
	
	//list of available enemies
	private String[] allEnemy;
	//position of stair, 2 elements, stair[0] = x, stair[1] = y
	protected int[] stair;

	protected Player p;
	
	// Rooms
	class Rm {
		private int x1, x2,  y1, y2, w, h, numDoors;

		public Rm() {
			w = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			h = ThreadLocalRandom.current().nextInt(4, 6 + 1);
			numDoors = 0;
		}
		private void set(int x, int y){
			x1 = x;
			y1 = y;
			x2 = x1 + w - 1;
			y2 = y1 + h - 1;
		}
	}

	public Level(int nL) {
		numLevel = nL;
		numR = ThreadLocalRandom.current().nextInt(5, 8 + 1);
		isSeen = new boolean[24][80];
		floor = new char[24][80];
		rb = new boolean[9];
		rs = new Rm[9];
		conn = new boolean[9];
		/* set everything to 0, which means empty */
		for(int y = 0; y < 24; y++)
			for(int x = 0; x < 80; x++)
				floor[y][x] = ' ';
		
		makeRooms();
		makeDoors();
		p = new Player();
		spawnPlayer(p);
		
		enList = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		
		
		// Great I just do not understand this part
		allEnemy = new String[] {"A","B","C","D","E","F"};
		
		makeEnemyList();
		spawnEnemy(enList);
		
		// I dont know what most of this random shit does
		
		
		/*
		 * We are gonna make the damn corridors by
		 * creating a new boolean array conn
		 * And fixing that wtf door code
		 * 
		 * We are gonna make a findS(quare)
		 * method to place items and enemies
		 * We are gonna leave them stationary for now...
		 * But we will store both in arraylists
		 */
	}
	
	
	/**
	 * Don't repeat yourself? Gonna be choosing random room a lot so 
	 * might as well create a method that does that
	 */
	private int randomRoom() {
		int ranRm = ThreadLocalRandom.current().nextInt(0, 8 + 1);
		while(!rb[ranRm])
			ranRm = ThreadLocalRandom.current().nextInt(0, 8 + 1);
		
		return ranRm;
		
		/*
		boolean validRm = false;
		int index = -1;
		while(!validRm) { 	//if fail use validRm == false
			int ranRm = ThreadLocalRandom.current().nextInt(0, 8 + 1);
			validRm = this.rb[ranRm];
			index = ranRm;
		}
		*/
		
		//return this.rs[index];
	}
	
	/*
	 * Similar to validMove
	 * Finds a random square to place an Item/Enemy
	 */
	private Point findS(){
		Point rd = new Point();
		
		
		
		return rd;
	}
	
	private void placeItem(int x, int y){
		Item i = new Item();
		Point spot = new Point();
		// i.point = p
		
		
		
		items.add(i);
		floor[y][x] = i.getBoardName();
	}
	/**
	 * Might need to use if-else instead of just if
	 */
	private void randomNumDoors(Rm[] roomArr, boolean[] roomBoo) {
		for(int i = 0; i < roomArr.length; i++) {
			//corner rooms
			if(i%2 == 0 && i != 4 && roomBoo[i]) {	
				roomArr[i].numDoors = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			}
			//edge room
			if (i%2 == 1 && i != 4 && roomBoo[i]) {	
				roomArr[i].numDoors = ThreadLocalRandom.current().nextInt(1, 3 + 1);
			} 
			//center room
			if (i == 4 && roomBoo[i]) {	
				roomArr[i].numDoors = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			}
		}
	}
	
	/**
	 * This does randomly generate doors at different place
	 * 
	 * I still think there's a problem with generating doors this way
	 *  in that doors don't connect to other doors that aren't directly opposite to it
	 *  
	 *  e.x. connections may happen like this
	 *  
	 *       - - - -        - - - -
	 * 		 | . . + ####   | . . |
	 * 		 | . . |	###	+ . . |
	 *		 - - - -		- - - -
	 *
	 *		 but not like this
	 *
	 *       - - - -        - - - -
	 *      | . . + ####   | . . |
	 * 	 | . . |	#	          | . . |
	 *		  - - - -	#	-         + - -
	 *					#######
	 *
	 *	 instead, the left room's door should lead to a dead-end and the right room should connect
	 *	 to some other room. But then how are you supposed to enter the left room? So that becomes
	 *	 a problem
	 *
	 *	Watch this video and notice how the rooms are connected to each other
	 *	https://www.youtube.com/watch?v=zUB1KovxOY4
	 */
	private void makeDoors(){
		// no I made these class variables so you do not need to pass them
		// since they are objects they change in all places
		randomNumDoors(rs, rb);
		
		for(int i = 0; i < rs.length; i++) {
			ArrayList<Integer> side = new ArrayList<Integer>(4);
			
			//side of the room, 0 = left, 1 = top, 2 = right, 3 = bot
			for(int j = 0; j < 4; j++) {	
				side.add(j);
			}
			
			//numDoors initialize to 0 so loop doesn't run for rb[i] = false
			if(rb[i]) {
				for(int j = 0; j < rs[i].numDoors; j++) {
					//top left room
					if(i == 0) {
						if(side.contains(0) && side.contains(1)) {
							side.remove(side.indexOf(0));
							side.remove(side.indexOf(1));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//top room
					if(i == 1) {
						if(side.contains(1)) {
							side.remove(side.indexOf(1));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//top right room
					if(i == 2) {
						if(side.contains(1) && side.contains(2)) {
							side.remove(side.indexOf(1));
							side.remove(side.indexOf(2));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//left room
					if(i == 3) {
						if(side.contains(0)) {
							side.remove(side.indexOf(0));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//center room
					if(i == 4) {
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//right room
					if(i == 5) {
						if(side.contains(2)) {
							side.remove(side.indexOf(2));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//bottom left room
					if(i == 6) {
						if(side.contains(0) && side.contains(3)) {
							side.remove(side.indexOf(0));
							side.remove(side.indexOf(3));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//bottom room
					if(i == 7) {
						if(side.contains(3)) {
							side.remove(side.indexOf(3));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					//bottom right room
					if(i == 8) {
						if(side.contains(2) && side.contains(3)) {
							side.remove(side.indexOf(2));
							side.remove(side.indexOf(3));
						}
						
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
				}
			}
		}
		
	}
	
	/**
	 * Used in makeDoors
	 */
	private int shuffle(ArrayList<Integer> list) {
		Collections.shuffle(list);
		int temp = list.get(0);
		list.remove(0);
		return temp;
	}
	
	private void placeDoor(int dSide, Rm curRm) {
		int tempX = ThreadLocalRandom.current().nextInt(curRm.x1 + 1, curRm.x2);
		int tempY = ThreadLocalRandom.current().nextInt(curRm.y1 + 1, curRm.y2);
		
		if(dSide == 0) {
			floor[tempY][curRm.x1] = '+';
		} else if(dSide == 1) {
			floor[curRm.y1][tempX] = '+';
		} else if(dSide == 2) {
			floor[tempY][curRm.x2] = '+';
		} else if(dSide == 3) {
			floor[curRm.y2][tempX] = '+';
		}
	}
	
	private void makeRooms(){
		for(int i = 0; i < numR; i++){
				addR();
		}
	}
	
	private void addR(){
		int roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
		while(rb[roomN])
			roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);

		Rm r = new Rm();
		r = placeR(r, roomN);

		rs[roomN] = r;
		rb[roomN] = true;
	}

	/* x: 0__25#27__52#54__79
	 * y: 0__6#8__14#16__22#
	 */
	private Rm placeR(Rm r, int num){
		int min = 0, max = 25;
		if(num%3 == 1){
			min = 27;
			max = 52;
		} else if(num%3 == 2){
			min = 54;
			max = 79;
		}
		int x  =ThreadLocalRandom.current().nextInt(min, max -r.w+1);
		
		min = 0;
		max = 6;
		if(num / 3 == 1){
			min = 8;
			max = 14;
		} else if(num / 3 == 2){
			min = 16;
			max = 22;
		}
		int y = ThreadLocalRandom.current().nextInt(min, max -r.h +1) ;
		
		r.set(x, y);
		
		return writeR(r);
	}
	
	private Rm writeR(Rm r){
		// I made a small change to this loop
		// w not 0->w but x1->x2
		// make top and part of a room
		for(int w = r.x1; w <= r.x2; w++){
			floor[r.y1][w] = '-';
			floor[r.y2][w] ='-';
		}
		// make inner room and side walls
		for(int h = r.y1+1; h < r.y2; h++){
			floor[h][r.x1] = '|';
			for(int w = r.x1+1; w < r.x2; w++){
				floor[h][w] = '.';
			}
			floor[h][r.x2] ='|';
		}
		
		return r;
	}

	public boolean[][] getSeen(){
		return isSeen;
	}

	public char[][] getFloor() {
		char[][] pfloor = new char[24][80];
		for (int y = 0; y< floor.length; y++){
			for(int x =0; x < floor[y].length; x++){
				//if(isSeen[y][x]){
					pfloor[y][x] = floor[y][x];
				//} else {
					//pfloor[y][x] = ' ';
				//}
			}
		}
			
		return pfloor;
	}

	public boolean moveUnit(Unit u, int[] dir){
		Point a = p.getP();
		if(validMove(u, dir)){
			// only if it is not a door
			floor[a.y][a.x] = '.';
			p.setP(a.x + dir[0], a.y + dir[1]);
			a.translate(dir[0], dir[1]);
			floor[a.y][a.x] = '@';
			
			System.out.println("Valid");
			
			return true;
		}
		System.out.println("Not Valid");
		return true;
	}
	
	public boolean validMove(Unit u, int[] dir){
		// inside a room || door || hall
		// && No out of bounds check!
		// Else no move
		
		Point a = p.getP();
		if(floor[a.y + dir[1]][a.y + dir[0]]  == '.'){
			return true;
		}
		return false;
	}

	// remove enemy
	public void removeUnit(Unit u){
	}

	//picks up an item
	public void pickUp(Unit u, int[] direction){
	}

	/**
	 * Fills up the empty enList with random enemies
	 * Currently this list will just fill up with any random enemies
	 * 	but eventually we'll have different enemies spawn on each level
	 * 
	 * Only 6 monsters currently (6 symbols) so 
	 */
	public void makeEnemyList() {
		int numEnemy = ThreadLocalRandom.current().nextInt(1, 9 + 1);	//may be changed later
		Enemy e = new Enemy();
		
		//remember that val = symbol of the monster (STRING)
		for(int i = 0; i < numEnemy; i++) {
			//Goes to allEnemy.legnth to avoid out of bounds ERR
			int chooseEnemy = ThreadLocalRandom.current().nextInt(0, this.allEnemy.length);
			e.val = allEnemy[chooseEnemy];
			(this.enList).add(e);
		}
	}
	
	/**
	 * 
	 */
	public void spawnPlayer(Player p) {
		// Level num is irrelevant
		//if(this.numLevel == 1 && !(p.hasA)) {
			Rm temp = randomRoom();	//can probably go outside
			int xPos = ThreadLocalRandom.current().nextInt(temp.x1 + 1, temp.x2);
			int yPos = ThreadLocalRandom.current().nextInt(temp.y1 + 1, temp.y2);
			p.setP(xPos, yPos);
			
			floor[yPos][xPos] = '@';
			seeRm(temp);
	}
	
	private void seeRm(Rm r){
		for(int y = r.y1; y <= r.y2; y++){
			for(int x = r.x1; x <= r.x2; x++){
				isSeen[y][x] = true;
			}
		}
	}
	
	
	/**
	 * I think this works
	 * Lol wtf bro
	 */
	public void spawnEnemy(ArrayList<Enemy> eList) {
		Rm temp;
		
		//place all enemy in list to random rooms
		for(int i = 0; i < eList.size(); i++) {
			temp = randomRoom();
			int xPos = ThreadLocalRandom.current().nextInt(temp.x1 + 1, temp.x2);
			int yPos = ThreadLocalRandom.current().nextInt(temp.y1 + 1, temp.y2);
			(eList.get(i)).setP(xPos, yPos);
			
			/* fail attempt to add enemy
			Enemy e = eList.get(i);
			char eBoard = e.val.charAt(0);
			
			floor[xPos][yPos] = eBoard;
			*/
		}
	}	
}