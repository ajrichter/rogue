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
	
	public Room(String type) {
		this.maxHeight = 24/3;	// = 8
		this.maxWidth = 80/3;	// = 26.66.. = 26
		
		//icky but user does not use this so it should be fine
		this.type = type;
		
		//Randomize height and width
		Random r = new Random();
		this.height = r.nextInt(this.maxHeight) + 1;
		this.width = r.nextInt(this.maxWidth) + 1;
		
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
		this.height = r.nextInt(this.maxHeight) + 1;
		this.width = r.nextInt(this.maxWidth) + 1;
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
