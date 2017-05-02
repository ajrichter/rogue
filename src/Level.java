

import java.util.concurrent.ThreadLocalRandom;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*TODO
 * Player needs to be created in GamePlay. For name purposes
 * Enemies
 * Items: Should be moved into default package
 * And then  
 * Stairs
 * store the last point for going down a level
 * need a point for where player is
 * isLit
 */
public class Level {
	/* Definitions */
	protected final int MAXROOMS = 9;

	/* Class Variables */
	protected int numLevel;
	/* Making rooms */
	// Number of Rooms
	protected int numR;
	/*Tells you what to put down after you leave a space */
	protected char last;
	protected boolean inside;
	protected boolean door;
	// The full floor
	protected char[][] floor;
	// Visible part of the floor
	protected boolean[][] isSeen;
	// Array of Rooms Existence
	protected boolean[] rb;
	// Array of Rooms
	protected Rm[] rs;
	
	protected String itemNames;
	// Array of item displays
	
	protected int itemX = 0;
	protected int itemY = 0;
	
	// position of stair, 2 elements, stair[0] = x, stair[1] = y
	//protected int[] stair;
	protected Stairs stair;
	 
	//gold
	//protected Gold[] gold;

	/* Spawning Enemies and Items */
	// List of enemies
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> items;
	private Map <Character, Item> itemPos; //Maps each item to an item Position
	private int p; //armor protection for player
	private int h; //hunger for player


	
	
	private Inventory inventory;
	
	protected Player play;
	protected int hits;
	protected String narration;

	/* Room */
	public class Rm {
		int x1, x2, y1, y2, w, h, goldVal;
		boolean isDark;

		public Rm() {
			w = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			h = ThreadLocalRandom.current().nextInt(4, 6 + 1);
			
			int darkChance = ThreadLocalRandom.current().nextInt(1, 100 + 1);
			if(darkChance < 10) {	//currently 20% chance, KEEP IT HERE FOR DEMO ON FRIDAY
				isDark = true;
			} else {
				isDark = false;
			}
		}

		private void set(int x, int y) {
			x1 = x;
			y1 = y;
			x2 = x1 + w - 1;
			y2 = y1 + h - 1;
		}
	}
	
	public class Stairs {
		int x, y;
		char sym;
	 
	    public Stairs() {
	    	this.x = this.y = 0;
	    	this.sym = '%';
	    }
	}

	public Level(int nL, Player pp) {
		numLevel = nL;
		numR = ThreadLocalRandom.current().nextInt(5, 8 + 1);
		isSeen = new boolean[24][80];
		floor = new char[24][80];
		rb = new boolean[9];
		rs = new Rm[9];
		stair = new Stairs();

		/* set everything to 0, which means empty */
		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				floor[y][x] = ' ';

		makeRooms();
		doors();
		placeGold();
		
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		inventory = new Inventory();
		itemPos = new HashMap <Character, Item>();

		for (int i = 0; i < 6; i++) {
			makeItem();
			makeEnemy();
		}
		
		//initialize to false
		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				isSeen[y][x] = false;

		/*
		 * Spawn Player and Light up its room. Use %|/ 3 to find the room
		 */
		play = pp;
		spawnP();
		last  = '.';
		hits = 0;
		
		//Spawn stair for current level
		spawnStair();
		
		narration = "";

		System.out.println("Level Constructor Finished.");
	}

	private void doors() {
		for (int i = 0; i < MAXROOMS - 1; i++) {
			if (i%3 < 2 && rb[i] && rb[i+1]) {
				conn(i, i+1);
			}
			if (i/3 < 2 && rb[i] && rb[i+3]) {
				conn(i, i+3);
			}
		}
	}
	/*
	 * The "Working Class" Method
	 * All the work drawing a hall done in here
	 */
	private void  conn(int r1, int r2){
		/* d is down, r is right. Stolen from C code*/
		char direc = 'd';
		if (r1 + 1 == r2)
		    direc = 'r';
		int turn;
		
		if(direc == 'r'){
			/* the x coords are already set by the room of course! */ 
			/* And d means door */
			int d1y = ThreadLocalRandom.current().nextInt(rs[r1].y1+1, rs[r1].y2);
			int d2y = ThreadLocalRandom.current().nextInt(rs[r2].y1+1, rs[r2].y2);
			turn = ((rs[r2].x1 + rs[r1].x2 + 2) / 2);
			// turn  = ThreadLocalRandom.current().nextInt(rs[r2].x2+1, rs[r2].x1);
			
			floor[d1y][rs[r1].x2] = '+';
			floor[d2y][rs[r2].x1] = '+';
			/* Left Side */
			for(int x = rs[r1].x2 + 1; x <= turn; x++){
				floor[d1y][x] = '#';
			}
			/* Find top door */
			int top = d1y;
			int bottom = d2y;
			if(d1y < d2y){
				top = d2y;
				bottom = d1y;
			}
			/* Move Up */
			for(int up = bottom; up <= top; up++){
				floor[up][turn] = '#';
			}
			/* Right Side */
			for(int x = turn; x < rs[r2].x1; x++){
				floor[d2y][x] = '#';
			}
		} else if(direc =='d'){
			int d1x = ThreadLocalRandom.current().nextInt(rs[r1].x1+1, rs[r1].x2);
			int d2x = ThreadLocalRandom.current().nextInt(rs[r2].x1+1, rs[r2].x2);
			turn = ((rs[r2].y1 - rs[r1].y2) / 2) + rs[r1].y2;
			// turn  = ThreadLocalRandom.current().nextInt(rs[r2].x2+1, rs[r2].x1);
			
			/* Doors */
			floor[rs[r1].y2][d1x] = '+';
			floor[rs[r2].y1][d2x] = '+';
			/* Top */
			for(int y = rs[r1].y2 + 1; y <= turn; y++){
				floor[y][d1x] = '#';
			}
			/* Find Right Door */
			int left = d1x;
			int right = d2x;
			if(d1x > d2x){
				left= d2x;
				right = d1x;
			}
			/* Move Right*/
			for(int x = left; x <= right; x++){
				floor[turn][x] = '#';
			}
			/* Bottom */
			for(int y = turn; y < rs[r2].y1; y++){
				floor[y][d2x] = '#';
			}
		} else {
			System.out.println("Oops! There is an error in conn().");
		}
	}
	
	/**
	 * Place gold in each room
	 */
	private void placeGold() {
		Point temp = new Point();
		
		//50% chance of spawning
		for(int i = 0; i < rs.length; i++) {
			if(ThreadLocalRandom.current().nextInt(0, 1 +1) == 0 && rb[i]) {
				//random gold value
				rs[i].goldVal = ThreadLocalRandom.current().nextInt(1, 50 + 1);
				
				char c = '0';
				
				while (c != '.') {
					temp.x = ThreadLocalRandom.current().nextInt(rs[i].x1 + 1, rs[i].x2);
					temp.y = ThreadLocalRandom.current().nextInt(rs[i].y1 + 1, rs[i].y2);
					c = floor[temp.y][temp.x];
					
					floor[temp.y][temp.x] = '*';
				}
			}
		}
	}
	
	private void spawnP() {
		/*
		 * I don't think the code commented here is used at all (moved to method findS()
		Point rd = new Point();
		int roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
		while (!rb[roomN])
			roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);

		Rm r = rs[roomN];
		char c = '0';
		while (c != '.') {
			rd.x = ThreadLocalRandom.current().nextInt(r.x1 + 1, r.x2);
			rd.y = ThreadLocalRandom.current().nextInt(r.y1 + 1, r.y2);
			c = floor[rd.y][rd.x];
		}
		*/

		play.p = findS();
		floor[play.p.y][play.p.x] = play.val;
		inside = true;

		//assuming you can get spawned inside a dark room, if yes use this
		//if not then change up the spawn room selection above to include
		//rs[roomN].isDark in the statement
		if(!getCurRoom(play).isDark) {
			seeRm(getCurRoom(play));
		} else {
			//simply draw the outline of the room
			//shows squares around player
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					isSeen[(play.p.y) + i - 1][(play.p.x) + j - 1] = true;
				}
			}
			
		}
	}

	/*
	 * Finds a random empty square inside a room
	 */
	private Point findS() {
		Point rd = new Point();
		/* Find a room that exists */
		int roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
		while (!rb[roomN])
			roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);

		Rm r = rs[roomN];
		char c = '0';
		while (c != '.') {
			rd.x = ThreadLocalRandom.current().nextInt(r.x1 + 1, r.x2);
			rd.y = ThreadLocalRandom.current().nextInt(r.y1 + 1, r.y2);
			c = floor[rd.y][rd.x];
		}
		return rd;
	}

	/**
	 * Spawn stair
	 */
	private void spawnStair() {
		Point pt = findS();
		this.stair.x = pt.x;
		this.stair.y = pt.y;
	 
	    floor[stair.y][stair.x] = stair.sym;
	}
	
	/*
	 * Need to store the Item Point Public not protected
	 */
	private void makeItem() {
		Item i = new Item();
		Point spot = findS();
		
		i.generateItem();
		itemY = spot.y;
		itemX = spot.x;
		
		items.add(i);
		floor[itemY][itemX] = i.getBoardName();
		char c = floor[itemY][itemX];
		
		itemNames = i.getPickUpMessage();
		itemPos.put(c, i);
	}
	
	
	
	
	
	
	
	private void makeEnemy() {
		Enemy e = new Enemy();
		Point spot = findS();
		e.p = spot;
		enemies.add(e);
		floor[spot.y][spot.x] = e.val;
	}

	/* I loosely implemented this in move when an Enemy is killed.*/
	public void removeUnit(Unit u) {
	}
	
	/*
	 * grid: x: 0__25#27__52#54__79 y: 0__6#8__14#16__22#
	 */
	private void makeRooms() {
		for (int i = 0; i < numR; i++) {
			int roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
			while (rb[roomN])
				roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
			
			Rm r = new Rm();
			int x = ThreadLocalRandom.current().nextInt((roomN % 3) * 27, ((roomN % 3) * 27) + 25 - r.w + 1);
			int y = ThreadLocalRandom.current().nextInt((roomN / 3) * 8, ((roomN / 3) * 8) + 6- r.h + 1);
			r.set(x, y);
			
			rs[roomN] = drawRoom(r);
			rb[roomN] = true;
		}
	}

	private Rm drawRoom(Rm r) {
		// Top and Bottom
		for (int w = r.x1; w <= r.x2; w++) {
			floor[r.y1][w] = '-';
			floor[r.y2][w] = '-';
		}
		// Inside and Sides
		for (int h = r.y1 + 1; h < r.y2; h++) {
			floor[h][r.x1] = '|';
			for (int w = r.x1 + 1; w < r.x2; w++) {
				floor[h][w] = '.';
			}
			floor[h][r.x2] = '|';
		}
		return r;
	}

	/**
	 * returns isSeen
	 */
	public boolean[][] getSeen() {
		return isSeen;
	}

	/**
	 * returns board as viewed by player
	 */
	public char[][] getFloor() {
		char[][] pfloor = new char[24][80];
		for (int y = 0; y < floor.length; y++) {
			for (int x = 0; x < floor[y].length; x++) {
				if(isSeen[y][x]) {
					pfloor[y][x] = floor[y][x];
				}
			}
		}

		return pfloor;
	}

	
	//Updates the player's stats after equiping or consuming an item
	public void updatePlayerStatsAfterEquip(Item i) {
			System.out.println(i);
			play.equiptOrConsumeItem(i);
			p = play.armor;	
			h = play.hunger;
			System.out.println("You have a hunger of: " + h);
			
	}
	
	/*
	 * Should return a String to narrate for you
	 * 2 Strings for Monster and Player Attack
	 * 
	 * Returns: 0 = moved 1 = fighting 2 = cant move
	 * Fight Monsters
	 * Get Items
	 * 
	 * Assumes Unit is Player
	 * Need to fix isSeen array so it changes for player
	 * if #/ first + then make all squares around @ seen if
	 * second + then make room seen if . then nothing
	 * 
	 * add to the bottom of this a random move for all enemies
	 * Iterate over the arraylist and then do a random
	 * move between 1 and 0 in each direction.
	 * Simple enough!
	 * Call new method moveAllMonsters();
	 */
	public int moveUnit(Player u, int[] dir) {
		Point a = u.p;
		/* Bounds check*/
		if ((a.x + dir[0]) < 0 || (a.y + dir[1]) < 0 || (a.y + dir[1]) > 23 || (a.x + dir[0]) > 79) {
			System.out.println("You are trying to move out of bounds. Very Bad!");
			return 2;
		}
		char c = floor[a.y + dir[1]][a.x + dir[0]];

		/* Implementing a pseudo-fight method for testing/getting Monsters out of the way! 
		 * However, the enemy should also be removed from enemies, the list
		 * */
		if (Character.isUpperCase(c) && c != '%') {
			for(Enemy e: enemies) {
				if(e.p.x == a.x+dir[0] && e.p.y == a.y+dir[1]) {
					narration = "You hit the " + e.getName() + "!";
				}
			}
			hits++;
			if(hits == 3){
				floor[a.y + dir[1]][a.x + dir[0]] = '.';
				hits = 0;
				return 4;
			}
			
			System.out.println("Good Hit on the " + c + "!");
			//narration = "You hit the " + c + "!";
			return 1;
		} else if (validMove(c)) {
			if (isItem(c)) {
				narration = play.name + " " + itemPos.get(c).getPickUpMessage();
				updatePlayerStatsAfterEquip(itemPos.get(c)); //Test Equip
				inventory.addItem(itemPos.get(c));
				floor[a.y + dir[1]][a.x + dir[0]] = '.';
				return 3;
			}
			
			
			floor[a.y][a.x] = last;
			last = c;
			
			a.setLocation(a.x + dir[0], a.y + dir[1]);
			floor[a.y][a.x] = '@';
				
			
			//shows everything if room is not dark, shows surrounding area if it is dark
			if(isInRoom(u) && !(getCurRoom(u).isDark)) {
				seeRm(getCurRoom(u));
				
			} else {//otherwise room is dark
				makeDark(getCurRoom(u));
			}
			//quick fix, make every dark room dark again instead of the one you've just left
			for(int i = 0; i < rs.length; i++) {
				if(rb[i] && rs[i].isDark) {
					makeDark(rs[i]);
				}
			}
		
			//shows squares around player
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					isSeen[a.y + i - 1][a.x + j - 1] = true;
				}
			}
			
			//isInRoom should be remove later in case gold can spawn in hall
			//Picking up gold
			//last one checks if gold has already been picked up - in case enemy can drop gold (instead of transfer)
			if(c == '*' && isInRoom(u) && getCurRoom(u).goldVal != 0) {	
				u.gold += getCurRoom(u).goldVal;
				narration = "You've picked up " + getCurRoom(u).goldVal + " gold";
				getCurRoom(u).goldVal = 0;
				last = '.';
				
				System.out.println("Moved Successfully and Picked Up Some Gold");
				return 3;
			}
			
			System.out.println("Moved Successfully");
			return 0;
		}
		if(c == stair.sym) {
			return 6;
		}
		
		System.out.println("No Move");
		return 2;
	}

	private boolean validMove(char c) {
		return (c == ':' || c == '.' || c == '+' || c == '#' || c == '!' || c == '/' || c == ')' || c == ']' || c == '=' || c == '?' || c == '*'); //The player can step on any item, even if the player's inventory is full 
	}
	
	private boolean isItem(char c) {
		return (c == ':' || c == '!' || c == '/' || c == ']' || c == '?' || c == ')' || c == '=');
	}
	
	
	
	/**
	 * checks if unit is currently in a room
	 */
	private boolean isInRoom(Unit u) {
		Point a = u.p;
		for(int i = 0; i < rs.length; i++) {
			if(rb[i]) {
				for(int scanX = rs[i].x1; scanX <= rs[i].x2; scanX++) {
					for(int scanY = rs[i].y1; scanY <= rs[i].y2; scanY++) {
						if(rb[i] && a.x == scanX && a.y == scanY) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * check which room the unit is in
	 */
	private Rm getCurRoom(Unit u) {
		Point a = u.p;
		for(int i = 0; i < rs.length; i++) {
			if(rb[i]) {
				for(int scanX = rs[i].x1; scanX <= rs[i].x2; scanX++) {
					for(int scanY = rs[i].y1; scanY <= rs[i].y2; scanY++) {
						if(rb[i] && a.x == scanX && a.y == scanY) {
							return rs[i];
						}
					}
				}
			}
		}
		
		//never used, need it here to prevent compilation err
		Rm room = new Rm();
		return room;
	}
	
	/**
	 * there might be a problem with this if it erases items and monster (and maybe player)
	 * in the room to make it dark again, shouldn't happen since you're using boolean and not
	 * directly changing the board itself
	 */
	private void makeDark(Rm curRoom) {
		for(int x = (curRoom.x1 + 1); x < curRoom.x2; x++) {
			for(int y = (curRoom.y1 + 1); y < curRoom.y2;y++) {
				isSeen[y][x] = false;
			}
		}
	}

	/*
	 * 0 1 2
	 * 3 @ 5
	 * 6 7 8
	 * When move to a #: show 1, 3, 5, 7
	 * When move to a +: show the room you entered
	 * Simple enough!
	 */
	private void seeRm(Rm r) {
		for (int y = r.y1; y <= r.y2; y++) {
			for (int x = r.x1; x <= r.x2; x++) {
				isSeen[y][x] = true;
			}
		}
	}
	/*
	public static void main(String[] args){
		Level l = new Level(1, new Player());
		
		for(int y = 0; y < l.floor.length; y++){
			for(int x = 0; x < l.floor[y].length; x++){
				System.out.print(l.floor[y][x]);
			}
			System.out.println("");
		}
	}
	*/
		//change this to "last" later b/c you'll have to be on top of the stair
}