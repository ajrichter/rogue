import java.util.Random;

public class Room {
	private int maxHeight;
	private int maxWidth;
	private int height;
	private int width;
	private String[][] room;
	private boolean isDark;
	private int doors;

	//type of room, co = corner, ce = center, ed = edge
	private String type;

	//there are DARK and LIT room

	/**
	 * Each level has dimensions of 24x80 and can contain up to 9 rooms
	 * Subsequently, each room can have a maximum dimension of 8x26 (divided by 3)
	 * Minimum Height and Width of room is 4x4 (room with 4 block to walk on)
	 *	 	 _ _ _ _
	 * 		 | . . +
	 * 		 | . . |
	 *		 _ _ _ _
	 *
	 *	| & _ = wall, . = floor, + = door
	 *
	 * @param type: n = north, s = south, e = east, w = west, c = center
	 * 				e.x. ne = north east, 2 doors max
	 * 				corner rooms can have up to 2 doors
	 * 				edge rooms   "			  " 3 doors
	 * 				center room  "			  " 4 doors
	 *
	 */
	public Room(String type) {
		this.maxHeight = 24/3;	// = 8
		this.maxWidth = 80/3;	// = 26.66.. = 26

		//icky but user does not use this so it should be fine
		this.type = type;

		//Randomize height and width
		Random r = new Random();
		this.height = r.nextInt(this.maxHeight) + 4;
		this.width = r.nextInt(this.maxWidth) + 4;

		//create room
		//height = # rows, width = # cols
		room = new String[this.height][this.width];

		//Randomize dark room chance
		int chance = r.nextInt(10) + 1;	//random from 1-10
		if(chance == 1) {
			this.isDark = true;
		} else {
			this.isDark = false;
		}
	}

	public void makeRoom() {
		drawRoom();
	}

	/**
	 * NOTE: Method currently NOT used, code implemented in constructor
	 */
	public void randomize() {
		//randomizes height and width of room
		//could've been done in constructor (might do that instead)
		Random r = new Random();
		this.height = r.nextInt(this.maxHeight) + 4;
		this.width = r.nextInt(this.maxWidth) + 4;
	}

	public void drawRoom() {
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				if(i == 0 || i == (this.height - 1)) {
					room[i][j] = "=";
				} else if (j == 0 || j == (this.width -1)) {
					room[i][j] = "|";	//should be "-"
				} else {
					room[i][j] = ".";
				}
			}
		}
	}

	/**
	 * gonna create a makeDoor() class which basically just makes the door
	 * by rewriting over the walls of the dungeon
	 */
	public void numDoors() {
		//corner, edge, and center room
		Random r = new Random();

		if((this.type).equals("ce")) {
			this.doors = r.nextInt(4) + 1;	//4 doors max
		} else if ((this.type).equals("co")) {
			this.doors = r.nextInt(2) + 1;	//2 doors max
		} else if ((this.type).equals("ed")) {
			this.doors = r.nextInt(3) + 1;	//3 doors max
		}
		//probably need some kind of error catching
	}

	public void makeDoors() {
		numDoors();
		/**
		 * in hindsight this is rather tricky, need to know the position of each room
		 * to determine where the doors are (south edge room will not have doors
		 * to the south of the room)
		 *
		 * might require a 3x3 array for level or so
		 *
		 * also need to determine the connections of the hallway and how all the rooms
		 * will connect to each other (so you don't have some wonkey leve design
		 */
	}

	/**
	 * NOTE: Method currently NOT used, code implemented in constructor
	 */
	public void randomDark() {
		//make it 1/10 chance for now
		Random r = new Random();
		int chance = r.nextInt(10) + 1;	//random from 1-10
		if(chance == 1) {
			this.isDark = true;
		}
	}
}
