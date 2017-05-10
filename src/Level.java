
/*
 * Needs to get refactored a lot!
 * Working on move still...
 * Enemy/Player are ready for fight 
 */

import java.util.concurrent.ThreadLocalRandom;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Level {
	protected final int MAXROOMS = 9;
	protected int numLevel;
	protected int numR;
	/* Tells you what to put down after you leave a space */
	protected char last;
	protected boolean inside;
	protected boolean door;
	protected char[][] floor;
	protected boolean[][] isSeen;
	protected boolean[] rb;
	protected Rm[] rs;
	protected String itemNames;
	protected int itemX = 0;
	protected int itemY = 0;
	protected Point stairs;
	private final char STAIR = '%';
	protected Point amulet;

	private ArrayList<Enemy> enemies;

	private ArrayList<Item> items;
	private Map<Character, Item> itemPos; // Maps each item to an item Position
	private int p; // armor protection for player
	private int h; // hunger for player

	protected Player play;
	protected int hits;
	protected String narration;
	protected String narration2;

	protected boolean onStairs;

	/* Room */
	public class Rm {
		int x1, x2, y1, y2, w, h, goldVal;
		boolean isDark;

		public Rm() {
			w = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			h = ThreadLocalRandom.current().nextInt(4, 6 + 1);
			isDark = false;

			if (ThreadLocalRandom.current().nextInt(1, 100 + 1) < 5)
				isDark = true;
		}

		private void set(int x, int y) {
			x1 = x;
			y1 = y;
			x2 = x1 + w - 1;
			y2 = y1 + h - 1;
		}
	}

	public Level(int nL, Player pp) {
		// starts at 0 so at 25 should find the amulet
		numLevel = nL;
		numR = ThreadLocalRandom.current().nextInt(5, 8 + 1);
		isSeen = new boolean[24][80];
		floor = new char[24][80];
		rb = new boolean[9];
		rs = new Rm[9];
		play = pp;

		/* set everything to 0, which means empty */
		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				floor[y][x] = ' ';

		makeRooms();
		doors();
		placeGold();

		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();

		itemPos = new HashMap<Character, Item>();

		for (int i = 0; i < 6; i++) {
			makeItem();
			makeEnemy();
		}

		// initialize to false
		for (int y = 0; y < 24; y++)
			for (int x = 0; x < 80; x++)
				isSeen[y][x] = false;

		/*
		 * Spawn Player and Light up its room. Use %|/ 3 to find the room
		 */
		spawnP();
		last = '.';
		hits = 0;

		/*
		 * Spawn stair for current level Should be above player It would be
		 * place everything. Then fit player
		 */
		this.stairs = findS();
		floor[stairs.y][stairs.x] = STAIR;

		narration = "";
		narration2 = "";

		onStairs = false;

		/* What we have been waiting for! */
		if (numLevel == 25) {
			amulet = findS();
			floor[amulet.y][amulet.x] = ',';
		}
	}

	/*
	 * Connects all rooms with doors and passages.
	 */
	private void doors() {
		for (int i = 0; i < MAXROOMS - 1; i++) {
			if (i % 3 < 2 && rb[i] && rb[i + 1]) {
				conn(i, i + 1, 'r');
			} else if (i % 3 == 0 && rb[i] && rb[i + 2]) {
				conn(i, i + 2, 'r');
			}
			if (i / 3 < 2 && rb[i] && rb[i + 3]) {
				conn(i, i + 3, 'd');
			} else if (i / 3 == 0 && rb[i] && rb[i + 6]) {
				conn(i, i + 6, 'd');
			}
		}
	}

	/*
	 * Connects two rooms
	 */
	private void conn(int r1, int r2, char direc) {
		int turn;

		if (direc == 'r') {
			/* the x coords are already set by the room of course! */
			/* And d means door */
			int d1y = ThreadLocalRandom.current().nextInt(rs[r1].y1 + 1, rs[r1].y2);
			int d2y = ThreadLocalRandom.current().nextInt(rs[r2].y1 + 1, rs[r2].y2);
			turn = ((rs[r2].x1 + rs[r1].x2 + 2) / 2);
			// turn = ThreadLocalRandom.current().nextInt(rs[r2].x2+1,
			// rs[r2].x1);

			floor[d1y][rs[r1].x2] = '+';
			floor[d2y][rs[r2].x1] = '+';
			/* Left Side */
			for (int x = rs[r1].x2 + 1; x <= turn; x++) {
				floor[d1y][x] = '#';
			}
			/* Find top door */
			int top = d1y;
			int bottom = d2y;
			if (d1y < d2y) {
				top = d2y;
				bottom = d1y;
			}
			/* Move Up */
			for (int up = bottom; up <= top; up++) {
				floor[up][turn] = '#';
			}
			/* Right Side */
			for (int x = turn; x < rs[r2].x1; x++) {
				floor[d2y][x] = '#';
			}
		} else if (direc == 'd') {
			int d1x = ThreadLocalRandom.current().nextInt(rs[r1].x1 + 1, rs[r1].x2);
			int d2x = ThreadLocalRandom.current().nextInt(rs[r2].x1 + 1, rs[r2].x2);
			turn = ((rs[r2].y1 - rs[r1].y2) / 2) + rs[r1].y2;
			// turn = ThreadLocalRandom.current().nextInt(rs[r2].x2+1,
			// rs[r2].x1);

			/* Doors */
			floor[rs[r1].y2][d1x] = '+';
			floor[rs[r2].y1][d2x] = '+';
			/* Top */
			for (int y = rs[r1].y2 + 1; y <= turn; y++) {
				floor[y][d1x] = '#';
			}
			/* Find Right Door */
			int left = d1x;
			int right = d2x;
			if (d1x > d2x) {
				left = d2x;
				right = d1x;
			}
			/* Move Right */
			for (int x = left; x <= right; x++) {
				floor[turn][x] = '#';
			}
			/* Bottom */
			for (int y = turn; y < rs[r2].y1; y++) {
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

		// 50% chance of spawning
		for (int i = 0; i < rs.length; i++) {
			if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 0 && rb[i]) {
				// random gold value
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
		 * I don't think the code commented here is used at all (moved to method
		 * findS() Point rd = new Point(); int roomN =
		 * ThreadLocalRandom.current().nextInt(0, 8 + 1); while (!rb[roomN])
		 * roomN = ThreadLocalRandom.current().nextInt(0, 8 + 1);
		 * 
		 * Rm r = rs[roomN]; char c = '0'; while (c != '.') { rd.x =
		 * ThreadLocalRandom.current().nextInt(r.x1 + 1, r.x2); rd.y =
		 * ThreadLocalRandom.current().nextInt(r.y1 + 1, r.y2); c =
		 * floor[rd.y][rd.x]; }
		 */

		play.p = findS();
		floor[play.p.y][play.p.x] = play.val;
		inside = true;

		// assuming you can get spawned inside a dark room, if yes use this
		// if not then change up the spawn room selection above to include
		// rs[roomN].isDark in the statement
		if (!getCurRoom(play).isDark) {
			seeRm(getCurRoom(play));
		} else {
			// simply draw the outline of the room
			// shows squares around player
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
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
		Enemy e = new Enemy(numLevel, play.level);
		e.p = findS();
		enemies.add(e);
		floor[e.p.y][e.p.x] = e.val;
	}

	/* I loosely implemented this in move when an Enemy is killed. */
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
			int y = ThreadLocalRandom.current().nextInt((roomN / 3) * 8, ((roomN / 3) * 8) + 6 - r.h + 1);
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
	 * returns board as viewed by player
	 */
	public char[][] getFloor() {
		char[][] pfloor = new char[24][80];
		for (int y = 0; y < floor.length; y++) {
			for (int x = 0; x < floor[y].length; x++) {
				if (isSeen[y][x]) {
					pfloor[y][x] = floor[y][x];
				}
				hideEnemy(pfloor, x, y);
			}
		}

		return pfloor;
	}

	public int throwItem(int itemNum, Player u, int[] dir) {
		narration = "";
		Point a = u.p;
		if (play.inventory.getInventorySpace() > itemNum) {
			if (floor[a.y + dir[1]][a.x + dir[0]] != '.') {
				narration = "Something is in the way. You need to choose a different spot for throwing.";
			} else {
				Item item = play.items.get(itemNum);
				narration = "You" + item.getDropMessage();
				floor[a.y + dir[1]][a.x + dir[0]] = item.boardName;
				play.items.remove(item);
				play.inventory.removeItem(item);
			}
		} else {
			narration = "The index is too large for the inventory's size.";
		}
		return 7;
	}

	// Updates the er's stats after equiping or consuming an item
	public int updatePlayerStatsAfterEquip(int itemNum) {
		narration = "";
		if (play.inventory.getInventorySpace() > itemNum) {
			Item item = play.items.get(itemNum);
			narration = "You" + item.getUseMessage();

			play.useItem(item);

			p += play.armor;
			h += play.hunger;
			play.items.remove(item);
			play.inventory.removeItem(item);
		} else {
			narration = "The index is too large for the inventory's size.";
		}

		// inventory.removeItem(play.items.get(0)); //removes the first item
		// from list
		return 6;
	}

	public int printInventory() {
		narration = "";
		if (play.items.size() != 0) {
			narration += "Current Inventory: ";
		} else {
			narration = "Nothing stored in Inventory";
		}

		int count = 0;
		for (Item i : play.items) {
			if (!i.typeItem.equalsIgnoreCase("Food")) {
				narration += count + " ";
				narration += i.name + " " + i.typeItem;
			} else {
				narration += count + " ";
				narration += i.name;
			}
			count++;
			if (count != play.items.size())
				narration += ", ";
		}

		return 5;
	}

	/*
	 * Return Narration String Returns: 0 = moved 1 = fighting 2 = cant move
	 * Call new method moveAllMonsters();
	 */
	public int moveUnit(Player u, int[] dir) {
		onStairs = false;
		Point a = u.p;
		/* Bounds check */
		if ((a.x + dir[0]) < 0 || (a.y + dir[1]) < 0 || (a.y + dir[1]) > 23 || (a.x + dir[0]) > 79) {
			return 2;
		}
		char c = floor[a.y + dir[1]][a.x + dir[0]];

		if (Character.isUpperCase(c) && c != STAIR) {
			for (Enemy e : enemies) {
				if (e.p.x == a.x + dir[0] && e.p.y == a.y + dir[1]) {
					fight(e);
					return 1;
				}
			}
			hits++;
			if (hits == 3) {
				floor[a.y + dir[1]][a.x + dir[0]] = '.';
				hits = 0;
				System.out.println(narration);
				return 1;
			}
			return 1;
		} else if (validMove(c)) {
			if (isItem(c)) {
				if (play.inventory.addItem(itemPos.get(c)) == false) {
					narration = "Can't add item. Inventory is full.";
				} else {
					narration = "You" + " " + itemPos.get(c).getPickUpMessage();
					play.items.add(itemPos.get(c));
					// inventory.addItem(itemPos.get(c));
					floor[a.y + dir[1]][a.x + dir[0]] = '.';
				}
				return 3;
			}

			floor[a.y][a.x] = last;
			last = c;

			a.setLocation(a.x + dir[0], a.y + dir[1]);
			floor[a.y][a.x] = '@';

			// shows everything if room is not dark, shows surrounding area if
			// it is dark
			if (isInRoom(u) && !(getCurRoom(u).isDark)) {
				seeRm(getCurRoom(u));

			} else {// otherwise room is dark
				makeDark(getCurRoom(u));
			}
			// quick fix, make every dark room dark again instead of the one
			// you've just left
			for (int i = 0; i < rs.length; i++) {
				if (rb[i] && rs[i].isDark) {
					makeDark(rs[i]);
				}
			}

			// shows squares around player
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					isSeen[a.y + i - 1][a.x + j - 1] = true;
				}
			}

			// isInRoom should be remove later in case gold can spawn in hall
			// Picking up gold
			// last one checks if gold has already been picked up - in case
			// enemy can drop gold (instead of transfer)
			if (c == '*' && isInRoom(u) && getCurRoom(u).goldVal != 0) {
				u.gold += getCurRoom(u).goldVal;
				narration = "You've picked up " + getCurRoom(u).goldVal + " gold";
				getCurRoom(u).goldVal = 0;
				last = '.';

				System.out.println("Moved Successfully and Picked Up Some Gold");
				return 3;
			}
			narration = "";
			narration = play.move();
			if (narration.equalsIgnoreCase("You should probably stop by the Gizmo.")
					|| narration.equals("You are starving!")) {
				return 9;
			}
			if (narration.equalsIgnoreCase("You have fainted. Game over!")) {
				floor[a.y][a.x] = '.';
				return 10;
			}

			else {
				System.out.println("Moved Successfully");
				return 0;
			}
		}
		if (c == STAIR) {
			onStairs = true;
			return 6;
		}

		System.out.println("No Move");
		return 2;
	}

	/*
	 * Error in GamePlay: need to have a String array
	 * So that we can have two narrations to space through 
	 */
	public String fight(Enemy e){
		/*
		 * Player attacks
		 * Then Enemy
		 * Then update narration?
		 * Then remove enemy from enemies or DIE!
		 * check if enemy is in Room or not
		 */
		String n = "";
		int patk = play.attack();
		e.hp -= patk;
		narration = "You hit the " + e.name + " for " + patk + " damage!";
		if(e.hp <= 0){
			floor[e.p.y][e.p.x] = '.';
			System.out.println("adding xp me " + play.xp + " enemy " + e.xp);
			play.xp += e.xp;
			narration = "You defeated the " + e.name + "!";
			narration2 = "";
			enemies.remove(e);
		} else {
			/* fight back */
			int eatk  = e.getDMG();
			play.hp -= eatk;
			narration2 = "The " + e.name + " attacked you for " + eatk + " damage!";
			
			if(play.hp <= 0)
				narration2 = "Sorry. You died!";
		}
		return n;
	}
	
	private boolean validMove(char c) {
		/*
		 * switch (c) { case ' ': case '|': case '-': return false; default:
		 * return !(Character.isUpperCase(c)); }
		 */
		return (c == ':' || c == '.' || c == '+' || c == '#' || c == '!' || c == '/' || c == ')' || c == ']' || c == '='
				|| c == '?' || c == '*');
	}

	private boolean isItem(char c) {
		return (c == ':' || c == '!' || c == '/' || c == ']' || c == '?' || c == ')' || c == '=');
	}

	/**
	 * checks if unit is currently in a room
	 */
	private boolean isInRoom(Unit u) {
		Point a = u.p;
		for (int i = 0; i < rs.length; i++) {
			if (rb[i]) {
				for (int scanX = rs[i].x1; scanX <= rs[i].x2; scanX++) {
					for (int scanY = rs[i].y1; scanY <= rs[i].y2; scanY++) {
						if (rb[i] && a.x == scanX && a.y == scanY) {
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
		for (int i = 0; i < rs.length; i++) {
			if (rb[i]) {
				for (int scanX = rs[i].x1; scanX <= rs[i].x2; scanX++) {
					for (int scanY = rs[i].y1; scanY <= rs[i].y2; scanY++) {
						if (rb[i] && a.x == scanX && a.y == scanY) {
							return rs[i];
						}
					}
				}
			}
		}

		// never used, need it here to prevent compilation err
		Rm room = new Rm();
		return room;
	}

	/**
	 * there might be a problem with this if it erases items and monster (and
	 * maybe player) in the room to make it dark again, shouldn't happen since
	 * you're using boolean and not directly changing the board itself
	 */
	private void makeDark(Rm curRoom) {
		for (int x = (curRoom.x1 + 1); x < curRoom.x2; x++) {
			for (int y = (curRoom.y1 + 1); y < curRoom.y2; y++) {
				isSeen[y][x] = false;
			}
		}
	}

	private void hideRoomEnemy(char[][] playerFloor) {
		for (int i = 0; i < rs.length; i++) {
			if (isInRoom(play)) {

			}
		}
	}

	private void hideEnemy(char[][] playerFloor, int xPos, int yPos) {
		if (Character.isUpperCase(playerFloor[yPos][xPos])) {
			if (isInRoom(play)) {
				// hides enemy in rooms that player aren't in
				Rm temp = getCurRoom(play);
				if (yPos > temp.y1 && yPos < temp.y2 && xPos > temp.x1 && xPos < temp.x2) {
					// do nothing
				} else {
					playerFloor[yPos][xPos] = '.';
				}
				// gonna need a way to hide enemy when they enter hallway too!
			} else {
				// it would probably look something like this
				boolean seePlayer = false;
				// hides everything when player not in room
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (playerFloor[yPos + i - 1][xPos + i - 1] == '@') {
							seePlayer = true;
						}
					}
				}
				if (seePlayer) {
					// do nothing
				} else {
					playerFloor[yPos][xPos] = '.';
				}
			}
		}
	}

	private void seeRm(Rm r) {
		for (int y = r.y1; y <= r.y2; y++) {
			for (int x = r.x1; x <= r.x2; x++) {
				isSeen[y][x] = true;
			}
		}
	}
}