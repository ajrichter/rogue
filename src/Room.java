import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Room {
	private int maxHeight;
	private int maxWidth;
	private int height;
	private int width;
	private String[][] room;
	private boolean isDark;
	private int doors;

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
		makeDoors();
	}
	
	public void drawRoom() {
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				if(i == 0 || i == (this.height - 1)) {
					room[i][j] = "=";		//should be "-"
				} else if (j == 0 || j == (this.width -1)) {
					room[i][j] = "|";	
				} else {
					room[i][j] = ".";
				}
			}
		}
	}

	/**
	 * gonna create a makeDoor() class which basically just makes the door
	 * by rewriting over the walls of the dungeon
	 * 
	 * there should be a string array in level with all the types of doors in it
	 * the level will random the number of rooms generated per level and will
	 * choose the type of rooms randomly from that array
	 */
	public void numDoors() {
		//corner, edge, and center room
		Random r = new Random();

		
		if((this.type).equals("c")) {
			this.doors = r.nextInt(4) + 1;	//4 doors max
		} else if ((this.type).equals("sw") || 
				   (this.type).equals("se") || 
				   (this.type).equals("nw") || 
				   (this.type).equals("ne")) {
			this.doors = r.nextInt(2) + 1;	//2 doors max
		} else if ((this.type).equals("n") || 
				   (this.type).equals("s") || 
				   (this.type).equals("e") || 
				   (this.type).equals("w")) {
			this.doors = r.nextInt(3) + 1;	//3 doors max
		}
		
		//probably need some kind of error catching
	}

	/**
	 * Done in the most inefficient way imaginable
	 * Revamp and cleanup would be appreciated
	 */
	public void makeDoors() {
		numDoors();
		
		Random r = new Random();
		
		//nextInt(max - min +1) + min
		//ex. max = 1, min = 0, nextInt(2) generates number from 0-1
		int doorSide;
		int doorRow;
		int doorCol;
		//int doorPos = ThreadLocalRandom.current().nextInt(0, 2);	//min - max(exclusive) gives 0 or 1
		
		//0 = left/east, 1 = top/north, 2 = right/west, 3 = bottom/south
		ArrayList<Integer> side = new ArrayList<Integer>(4);
		for(int i = 0; i < 4; i++) {
			side.add(i);
		}
		
		for(int i = 0; i < this.doors; i++) {
			//easy lazy way
			//help me implement this using switch?
			if ((this.type).equals("c")) {
				//shuffles instead of selecting random number
				Collections.shuffle(side);
				int curSide = side.get(0);
				side.remove(0);
				
				//0 = left, 1 = top, 2 = right, 3 = bottom
				if (curSide == 0) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][0] = "+";
				} else if (curSide == 1) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[0][doorCol] = "+";
				} else if (curSide == 2) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][this.width - 1] = "+";
				} else if (curSide == 3) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[this.height - 1][doorCol] = "+";
				}
			}
			if ((this.type).equals("n")) {
				if(side.contains(1)) {
					side.remove(side.get(1));	//hopefully this works? removes 1 = top
				}
					
				Collections.shuffle(side);
				int curSide = side.get(0);
				side.remove(0);
				
				if (curSide == 0) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][0] = "+";
				} else if (curSide == 2) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][this.width - 1] = "+";
				} else if (curSide == 3) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[this.height - 1][doorCol] = "+";
				}
			}
			if ((this.type).equals("s")) {
				if(side.contains(3)) {
					side.remove(side.get(3));
				}
				
				Collections.shuffle(side);
				int curSide = side.get(0);
				side.remove(0);
				
				if (curSide == 0) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][0] = "+";
				} else if (curSide == 1) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[0][doorCol] = "+";
				} else if (curSide == 2) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][this.width - 1] = "+";
				}
			}
			if ((this.type).equals("e")) {
				if(side.contains(0)) {
					side.remove(side.get(0));
				}
				
				Collections.shuffle(side);
				int curSide = side.get(0);
				side.remove(0);
				
				if (curSide == 1) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[0][doorCol] = "+";
				} else if (curSide == 2) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][this.width - 1] = "+";
				} else if (curSide == 3) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[this.height - 1][doorCol] = "+";
				}
			}
			if ((this.type).equals("w")) {
				if(side.contains(2)) {
					side.remove(side.get(2));
				}

				Collections.shuffle(side);
				int curSide = side.get(0);
				side.remove(0);
				
				if (curSide == 0) {
					doorRow = ThreadLocalRandom.current().nextInt(1, this.height - 1);
					room[doorRow][0] = "+";
				} else if (curSide == 1) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[0][doorCol] = "+";
				} else if (curSide == 3) {
					doorCol = ThreadLocalRandom.current().nextInt(1, this.width - 1);
					room[this.height - 1][doorCol] = "+";
				}
			}
			if ((this.type).equals("nw")) {
	
			}
			if ((this.type).equals("ne")) {
	
			}
			if ((this.type).equals("sw")) {
				
			}
			if ((this.type).equals("se")) {
	
			}
		}
	}
}
