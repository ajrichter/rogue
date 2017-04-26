import java.util.ArrayList;

public class Dungeon {
	protected ArrayList<Level> lvl;
	protected Player play;
	
	//probably would want 1 instance of player or so
	public Dungeon(Player p) {
		play = p;
		
		//26 levels for now
		for(int i = 1; i <= 26; i++) {
			Level l = new Level(i + 1, play);
			
			lvl.add(l);
		}
	}
}
