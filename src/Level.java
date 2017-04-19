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
	// Tells move what to replace the square with
	protected boolean inside;
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

	class Rconn {
		boolean[] conn, isconn;
		boolean ingraph;

		public Rconn() {
			conn = new boolean[MAXROOMS]; /* possible to connect to room i? */
			isconn = new boolean[MAXROOMS]; /*
											 * connection been made to room i?
											 */
			ingraph = false; /* this room in graph already? */
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

		for (int i = 0; i < 8; i++) {
			// Item is a Character not char?
			// makeItem();
			makeEnemy();
		}

		/*
		 * Spawn Player and Light up its room. Use %|/ 3 to find the room
		 */
		play = pp;
		spawnP();

		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				isSeen[y][x] = true;

		System.out.println("Level Constructor Finished.");

		// I dont know what most of this random shit does
		/*
		 * We are gonna make the damn corridors by creating a new boolean array
		 * conn And fixing that wtf door code
		 * 
		 * We are gonna leave enemies stationary for now... But we will store
		 * both in arraylists
		 */
	}

	private void doors() {
		Rconn[] rdes = new Rconn[MAXROOMS];
		/* Initialize Rooms able to Connect*/
		rdes[0].conn[1] = rdes[0].conn[3] = true;
		rdes[1].conn[0] = rdes[1].conn[2] = rdes[1].conn[4] = true; 
		rdes[2].conn[1] = rdes[2].conn[5] = true;
		rdes[3].conn[0] = rdes[3].conn[4] = rdes[3].conn[6] = true;
		rdes[4].conn[1] = rdes[4].conn[3] = rdes[4].conn[5] = rdes[4].conn[7] =  true;
		rdes[5].conn[2] = rdes[5].conn[4] = rdes[5].conn[8] = true;
		rdes[6].conn[3] = rdes[6].conn[7] = true;
		rdes[7].conn[4] = rdes[7].conn[6] = rdes[7].conn[8] = true;
		rdes[8].conn[5] = rdes[8].conn[7] = true;
		
		int roomcount = 1;
	    Rconn r1 = rdes[ThreadLocalRandom.current().nextInt(MAXROOMS)];
	    r1.ingraph = true;
	    do {
		int i;
		Rconn r2 = rdes[0];
		
		int j = 0;
		for (i = 0; i < MAXROOMS; i++)
		    if (r1.conn[i] && !rdes[i].ingraph && ThreadLocalRandom.current().nextInt(++j) == 0)
		    	r2 = rdes[i];
		if (j == 0)
		{
		    do
			r1 = rdes[ThreadLocalRandom.current().nextInt(MAXROOMS)];
		    while (!r1.ingraph);
		}
		else
		{
		    r2.ingraph = true;
		    /*
		     * i = r1 - rdes;
		     * j = r2 - rdes;
		     * conn(i, j);
		     */
		    r1.isconn[j] = true;
		    r2.isconn[i] = true;
		    roomcount++;
		}
	    } while (roomcount < MAXROOMS);
		
	    
	    
		boolean flip = false;
		for(int i = 0; i < MAXROOMS; i++){
			if(rb[i]){
				
			}
			if(flip){
				i++;
				flip = false;
			} else
				flip = true;
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

	public void removeUnit(Unit u) {
	}

	/**
	 * Might need to use if-else instead of just if
	 */
	private void randomNumDoors(Rm[] roomArr, boolean[] roomBoo) {
		for (int i = 0; i < roomArr.length; i++) {
			// corner rooms
			if (i % 2 == 0 && i != 4 && roomBoo[i]) {
				roomArr[i].numDoors = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			}
			// edge room
			if (i % 2 == 1 && i != 4 && roomBoo[i]) {
				roomArr[i].numDoors = ThreadLocalRandom.current().nextInt(1, 3 + 1);
			}
			// center room
			if (i == 4 && roomBoo[i]) {
				roomArr[i].numDoors = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			}
		}
	}

	/**
	 * This does randomly generate doors at different place
	 * 
	 * I still think there's a problem with generating doors this way in that
	 * doors don't connect to other doors that aren't directly opposite to it
	 * 
	 * e.x. connections may happen like this
	 * 
	 * - - - - - - - - | . . + #### | . . | | . . | ### + . . | - - - - - - - -
	 *
	 * but not like this
	 *
	 * - - - - - - - - | . . + #### | . . | | . . | # | . . | - - - - # - + - -
	 * #######
	 *
	 * instead, the left room's door should lead to a dead-end and the right
	 * room should connect to some other room. But then how are you supposed to
	 * enter the left room? So that becomes a problem
	 *
	 * Watch this video and notice how the rooms are connected to each other
	 * https://www.youtube.com/watch?v=zUB1KovxOY4
	 * 
	 * another possibility would be to just add in all the doors and then make
	 * it so that there's a chance that it won't generate then the corresponding
	 * door would just be a dead-end
	 */
	private void makeDoors() {
		// no I made these class variables so you do not need to pass them
		// since they are objects they change in all places
		randomNumDoors(rs, rb);

		for (int i = 0; i < rs.length; i++) {
			ArrayList<Integer> side = new ArrayList<Integer>(4);

			// side of the room, 0 = left, 1 = top, 2 = right, 3 = bot
			for (int j = 0; j < 4; j++) {
				side.add(j);
			}

			// numDoors initialize to 0 so loop doesn't run for rb[i] = false
			if (rb[i]) {
				for (int j = 0; j < rs[i].numDoors; j++) {
					// top left room
					if (i == 0) {
						if (side.contains(0) && side.contains(1)) {
							side.remove(side.indexOf(0));
							side.remove(side.indexOf(1));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// top room
					if (i == 1) {
						if (side.contains(1)) {
							side.remove(side.indexOf(1));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// top right room
					if (i == 2) {
						if (side.contains(1) && side.contains(2)) {
							side.remove(side.indexOf(1));
							side.remove(side.indexOf(2));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// left room
					if (i == 3) {
						if (side.contains(0)) {
							side.remove(side.indexOf(0));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// center room
					if (i == 4) {
						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// right room
					if (i == 5) {
						if (side.contains(2)) {
							side.remove(side.indexOf(2));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// bottom left room
					if (i == 6) {
						if (side.contains(0) && side.contains(3)) {
							side.remove(side.indexOf(0));
							side.remove(side.indexOf(3));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// bottom room
					if (i == 7) {
						if (side.contains(3)) {
							side.remove(side.indexOf(3));
						}

						int curSide = shuffle(side);
						placeDoor(curSide, rs[i]);
					}
					// bottom right room
					if (i == 8) {
						if (side.contains(2) && side.contains(3)) {
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

	private void makeHall() {
		for (int i = 0; i < rs.length; i++) {
			// if rightside has door, connect to leftside door of adjacent room
			if (rb[i] && i % 3 < 2 && isDoor(rs[i], "hori", rs[i].x2)) {
				// if adjacent room exists
				if (rb[i + 1] && (i + 1) % 3 > 0 && isDoor(rs[i + 1], "hori", rs[i + 1].x1)) {
					int hallLength = rs[i + 1].x1 - rs[i].x2;
					int door1Y = findDoor(rs[i], "hori", rs[i].x2);
					int door2Y = findDoor(rs[i + 1], "hori", rs[i + 1].x1);
					int height = Math.abs(door1Y - door2Y);
					int currY = door1Y;

					for (int j = 1; j < hallLength - 1; j++) {
						floor[rs[i].x1 + j][currY] = '#';
						if (j == hallLength - 1) { // reached halfway
							for (int k = 1; k < height; k++) {
								if (door1Y < door2Y) { // door 1 is HIGHER than
														// door 2
									floor[rs[i].x1 + j][door1Y + k] = '#'; // going
																			// down
								} else {
									floor[rs[i].x1 + j][door1Y - k] = '#'; // going
																			// up
								}
								// might need to change door1Y here for currY,
								// prolly doesn't matter
							}
							currY = door2Y;
						}
					}
				}
				// need a case for when adjacent room doesn't exist (goes to
				// next room,
				// and if that adjacent room doesn't exist then what? dead-end
				// or maze or long dead-end)
				// can probably collapse making of right and bottom door into 1
				// section
				// otherwise this code will get much more ugly than it is right
				// now
			}
		}
	}

	/**
	 * for curSide, x1 = left, y1 = top, x2, = right, y2 = bottom
	 */
	private boolean isDoor(Rm curRoom, String side, int curSide) {
		if (side.equals("hori")) {
			for (int i = curRoom.y1; i < curRoom.y2; i++) {
				if (floor[curSide][i] == '+') {
					return true;
				}
			}
		}
		if (side.equals("vert")) {
			for (int i = curRoom.x1; i < curRoom.x2; i++) {
				if (floor[i][curSide] == '+') {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * can probably just use 1 method and have it return the coordinate of the
	 * door
	 * 
	 * use the boolean above and pass in a variable to be changed
	 */
	private int findDoor(Rm curRoom, String side, int curSide) {
		if (side.equals("hori")) {
			for (int i = curRoom.y1; i < curRoom.y2; i++) {
				if (floor[curSide][i] == '+') {
					return i;
				}
			}
		}
		if (side.equals("vert")) {
			for (int i = curRoom.x1; i < curRoom.x2; i++) {
				if (floor[i][curSide] == '+') {
					return i;
				}
			}
		}

		return 0;
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

		if (dSide == 0) {
			floor[tempY][curRm.x1] = '+';
		} else if (dSide == 1) {
			floor[curRm.y1][tempX] = '+';
		} else if (dSide == 2) {
			floor[tempY][curRm.x2] = '+';
		} else if (dSide == 3) {
			floor[curRm.y2][tempX] = '+';
		}
	}

	/* Change this to make 9 rooms. And choose which to connect to 
	 * Draw all 9 rooms in order
	 * */
	private void makeRooms() {
		for (int i = 0; i < numR; i++) {
			int roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
			while (rb[roomN])
				roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
			rs[roomN] = placeR(roomN);
			rb[roomN] = true;
		}
	}

	/*
	 * This method contains useful math for understanding how the grid is
	 * aligned x: 0__25#27__52#54__79 y: 0__6#8__14#16__22#
	 */
	private Rm placeR(int num) {
		Rm r = new Rm();
		int min = (num % 3) * 27;
		int max = min + 25;
		int x = ThreadLocalRandom.current().nextInt(min, max - r.w + 1);
		
		min = (num/3)*8;
		max = min+6;
		int y = ThreadLocalRandom.current().nextInt(min, max - r.h + 1);

		r.set(x, y);

		return drawR(r);
	}

	private Rm drawR(Rm r) {
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
	 * Returns: 0 = successful move 1 = FIGHT! 2 = No Move occurred
	 * 
	 * Need to fix isSeen if #/ first + then make all squares around @ seen if
	 * second + then make room seen if . then nothing
	 */
	public int moveUnit(Unit u, int[] dir) {
		Point a = u.p;

		/* Out of Bounds */
		if ((a.x + dir[0]) < 0 || (a.y + dir[1]) < 0 || (a.y + dir[1]) > 23 || (a.x + dir[0]) > 79) {
			System.out.println("No Move");
			return 2;
		}
		char c = floor[a.y + dir[1]][a.x + dir[0]];

		if (Character.isUpperCase(c)) {
			System.out.println("FIGHT NIGHT!");
			return 1;
		} else if (validMove(c)) {
			floor[a.y][a.x] = '.';
			if (!inside) {
				floor[a.y][a.x] = '#';
			}

			a.setLocation(a.x + dir[0], a.y + dir[1]);
			floor[a.y][a.x] = '@';
			/*
			 * Not comprehensive enough At a minimum: all 8 surrounding squares
			 * are seen
			 */

			isSeen[a.y][a.x] = true;
			inside = (c == '.');

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
	 * Pass a point If its a + then see the Rm If its not then see the 8(9-self)
	 * surrounding squares
	 */
	private void seeRm(Rm r) {
		for (int y = r.y1; y <= r.y2; y++) {
			for (int x = r.x1; x <= r.x2; x++) {
				isSeen[y][x] = true;
			}
		}
	}

	public static void main(String[] args) {
		Level l = new Level(1, new Player());

		for (int y = 0; y < l.floor.length; y++) {
			for (int x = 0; x < l.floor[y].length; x++) {
				System.out.print(l.floor[y][x]);
			}
			System.out.println("");
		}
	}

}