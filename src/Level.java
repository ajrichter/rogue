import java.util.concurrent.ThreadLocalRandom;
import Item.Item;
import java.awt.Point;
import java.util.ArrayList;

/*TODO
 * Enemies
 * Items
 * isLit
 * Stairs
 * 
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
	// Array of Rooms
	private Rm[] rs;
	// List of enemies
	private ArrayList<Enemy> enList;
	
	// Need something similar for Items?
	// Ink is right. Make a Room class. But simple!!
	// door, edges, items, 
	// How do I do hallways then?
	// Things to think about...
	
	//list of available enemies
	private String[] allEnemy;
	//position of stair, 2 elements, stair[0] = x, stair[1] = y
	protected int[] stair;
	
	// Player needs to be in GamePlay too though
	protected Player p;
	
	// An inner class used to define each room
	class Rm {
		private int x1, x2,  y1, y2, w, h;

		public Rm() {
			// These are both one shorter because random room placement confused me
			// I do not know why this is wrong
			w = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			h = ThreadLocalRandom.current().nextInt(4, 6 + 1);
		}

		private void set(int x, int y){
			x1 = x;
			y1 = y;
			x2 = x1 + w - 1;
			y2 = y1 + h - 1;
		}
	}

	// Andrew's Level Generator
	public Level(int nL) {
		numLevel = nL;
		// store the last point for going down a level
		// need a point for  where player is
		numR = ThreadLocalRandom.current().nextInt(4, 8 + 1);

		isSeen = new boolean[24][80];
		floor = new char[24][80];
		rb = new boolean[9];
		rs = new Rm[9];
		
		// set everything to 0, which means empty
		for(int y = 0; y < 24; y++){
			for(int x = 0; x < 80; x++){
				floor[y][x] = ' ';
			}
		}
		
		makeRooms();
		makeDoors();
		p = new Player();
		spawnPlayer(p);
		
		this.enList = new ArrayList<Enemy>();
		// Great I just do not understand this part
		this.allEnemy = new String[] {"A","B","C","D","E","F"};
	}
	
	private void placeItem(int x, int y){
		Item i = new Item();
		i.generateItem();
		// add to arraylist
		
		floor[y][x] = i.getBoardName().charAt(0);
	}
	
	/*
	 * Add 9 Randomly sized rooms
	 * Then fit Doors/Hallways
	 * The next part Ink will work on:
	 * Then places Item and Generates Enemies 
	 */
	private void makeDoors(){
		// rb, rs
		// door booleans
		boolean[] db = new boolean[9];
		// Right Side Doors
		for(int i = 0; i < 8; i++){
			if(rb[i] && ThreadLocalRandom.current().nextBoolean()){
				// find closest room
				Rm r1 = rs[i];
				if(rb[i+1]){
					int d1 = ThreadLocalRandom.current().nextInt(r1.y1+1, r1.y2);
					int d2 = ThreadLocalRandom.current().nextInt(rs[i+1].y1+1, rs[i+1].y2);
					// make doors
					floor[d1][r1.x2] = '+';
					floor[d2][rs[i+1].x1] = '+';
					// make halls
					// first half
					for(int a = r1.x2+1; a < (rs[i+1].x1 - r1.x2)/2; a++){
						floor[d1][a] = '#';
					}
					// up/down
					if(d1 > d2){
						for(int u = d2; u <= d1; u++)
							floor[u][(rs[i+1].x1 - r1.x2)/2] = '#';
					} else if(d2 < d1){
						for(int u = d1; u <= d2; u++)
							floor[u][(rs[i+1].x1 - r1.x2)/2] = '#';
					}
					// second half
					for(int b = (rs[i+1].x1 - r1.x2)/2; b < rs[i+1].x1; b++){
						floor[d2][b] = '#';
					}
				} else {
					// Find another room if no adjacent
				}
				db[i] = true;
			}
			if(i % 3 == 1)
				i++;	
		}
		// bottom doors are if not done || if true
		
		
		
		// Problem: Need a failsafe if a room has no door and exists so both booleans false
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

	/* Grid Layout
	 * Array Indices:
	 * #: Buffer, __: Range
	 * x: 0__25#27__52#54__79
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
		System.out.println(min + " "+ max + " " + (max -r.w+1) +" " + x);
		
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
		System.out.println(min + " "+ max + " " + (max -r.h+1) +" " + y);
		
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

	//checks if a move is valid or not, this method is working
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

	public boolean moveUnit(Unit u, int[] dir){
		Point a = p.getP();
		// if(validMove(...
			
		floor[(int) a.getY()][(int) a.getX()] = '.';
		p.setP((int) a.getX() + dir[0], (int) a.getY() + dir[1]);
		floor[(int) a.getY() + dir[1]][(int) a.getX() + dir[0]] = '@';
		
		return true;
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
		
		//remember that val = symbol of the monster
		for(int i = 0; i < numEnemy; i++) {
			int chooseEnemy = ThreadLocalRandom.current().nextInt(0, this.allEnemy.length + 1);
			e.val = allEnemy[chooseEnemy];
			(this.enList).add(e);
		}
	}
	
	/**
	 * 
	 */
	public void spawnPlayer(Player p) {
		// Level num is irrelevant
		//if(this.numLevel == 1 && !(p.hasA)) { //also need condition where player does not have amulet of yendor
			//TODO: place player anywhere on 1st floor
			Rm temp = randomRoom();	//can probably go outside
			int xPos = ThreadLocalRandom.current().nextInt(temp.x1 + 1, temp.x2);
			int yPos = ThreadLocalRandom.current().nextInt(temp.y1 + 1, temp.y2);
			p.setP(xPos, yPos);
			
			floor[yPos][xPos] = '@';
			seeRm(temp);
			
		//} else {
			//TODO: place player on stairs, FIND IT
		//}
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
	 */
	public void spawnEnemy(ArrayList<Enemy> eList) {
		Rm temp;
		
		//place all enemy in list to random rooms
		for(int i = 0; i < eList.size(); i++) {
			temp = randomRoom();
			int xPos = ThreadLocalRandom.current().nextInt(temp.x1 + 1, temp.x2);
			int yPos = ThreadLocalRandom.current().nextInt(temp.y1 + 1, temp.y2);
			(eList.get(i)).setP(xPos, yPos);
		}
	}
	
	/**
	 * Don't repeat yourself? Gonna be choosing random room a lot so 
	 * might as well create a method that does that
	 */
	public Rm randomRoom() {
		boolean validRm = false;
		int index = -1;
		while(!validRm) { 	//if fail use validRm == false
			int ranRm = ThreadLocalRandom.current().nextInt(0, 8 + 1);
			validRm = this.rb[ranRm];
			index = ranRm;
		}
		
		return this.rs[index];
	}
	
}
