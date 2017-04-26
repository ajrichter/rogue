import java.util.ArrayList;

public class Dungeon {
	protected ArrayList<Level> lvl;
	
	//probably would want 1 instance of player or so
	//don't add in all 26 levels at once, have that be done in gameplay
	//	where you keep passing the player on and just do dungeon.lvl.add(level)
	//	or something along those lines so that we have only 1 instances of player
	public Dungeon() {
		//Player tempP = new Player();
		
		//then just keep adding stuff through gameplay
		//ex. dungeon.lvl.add(i, play) where i = loop variable
		lvl = new ArrayList<Level>();
		
		
		//26 levels for now\
		/*
		for(int i = 0; i < 26; i++) {
			Level l = new Level(i, tempP);
			
			lvl.add(l);
		}
		*/
	}
}
