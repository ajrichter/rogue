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
	/* Definitions */
	protected final int MAXROOMS = 9;

	/* Class Variables */
	private int numLevel;
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
	
	// position of stair, 2 elements, stair[0] = x, stair[1] = y
	protected int[] stair;

	/* Spawning Enemies and Items */
	// List of enemies
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> items;

	protected Player play;
	protected int hits;

	/* Room */
	public class Rm {
		int x1, x2, y1, y2, w, h, numDoors;

		public Rm() {
			w = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			h = ThreadLocalRandom.current().nextInt(4, 6 + 1);
			numDoors = 0;
		}

		private void set(int x, int y) {
			x1 = x;
			y1 = y;
			x2 = x1 + w - 1;
			y2 = y1 + h - 1;
		}
	}

	public Level(int nL, Player pp) {
		numLevel = nL;
		numR = ThreadLocalRandom.current().nextInt(5, 8 + 1);
		isSeen = new boolean[24][80];
		floor = new char[24][80];
		rb = new boolean[9];
		rs = new Rm[9];

		/* set everything to 0, which means empty */
		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				floor[y][x] = ' ';

		makeRooms();
		doors();
		
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();

		for (int i = 0; i < 5; i++) {
			// makeItem();
			makeEnemy();
		}

		/*
		 * Spawn Player and Light up its room. Use %|/ 3 to find the room
		 */
		play = pp;
		spawnP();
		last  = '.';
		hits = 0;
		
		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				isSeen[y][x] = true;

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
	
	private void spawnP() {
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

		play.p = findS();
		floor[play.p.y][play.p.x] = play.val;
		inside = true;

		seeRm(rs[roomN]);
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

	/*
	 * Need to store the Item Point Public not protected
	 */
	private void makeItem() {
		Item i = new Item();
		Point spot = findS();
		// i.p = spot
		// Diff Package so different visibility
		
		i.generateItem();
		
		items.add(i);
		floor[spot.y][spot.x] = i.getBoardName();
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

	public boolean[][] getSeen() {
		return isSeen;
	}

	public char[][] getFloor() {
		char[][] pfloor = new char[24][80];
		for (int y = 0; y < floor.length; y++) {
			for (int x = 0; x < floor[y].length; x++) {
				pfloor[y][x] = floor[y][x];
			}
		}

		return pfloor;
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
	public int moveUnit(Unit u, int[] dir) {
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
		if (Character.isUpperCase(c)) {
			hits++;
			if(hits == 3){
				floor[a.y + dir[1]][a.x + dir[0]] = '.';
				hits = 0;
			}
			
			System.out.println("Good Hit on the " + c + "!");
			return 1;
		} else if (validMove(c)) {
			floor[a.y][a.x] = last;
			last = c;
			
			a.setLocation(a.x + dir[0], a.y + dir[1]);
			floor[a.y][a.x] = '@';
			
			/*
			 * This is where isSeen needs to be changed
			 * Not enough to just do the square 
			 */
			isSeen[a.y][a.x] = true;

			System.out.println("Moved Successfully");
			return 0;
		}
		System.out.println("No Move");
		return 2;
	}

	private boolean validMove(char c) {
		return (c == '.' || c == '+' || c == '#');
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
}