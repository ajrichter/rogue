import java.util.Random;

public class Room {
	private int maxHeight;
	private int maxWidth;
	private String[][] room;
	boolean isDark;
	
	public Room() {
		this.maxHeight = 8;
		this.maxWidth = 26;
		room = new String[maxHeight][maxWidth];
		this.isDark = false;
	}
	
	public void makeRoom() {
		
	}
}
