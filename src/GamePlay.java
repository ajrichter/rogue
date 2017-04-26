import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamePlay {
	
	private boolean hasAmulet=false;
	Player play;
	//ArrayList<Level> dungeon;
	Dungeon dungeon;
	Level currLevel;
	private String[] lines = new String[10];
	int currNumLevel;
	String narration;
	boolean goUp;
	
	public GamePlay() {
        System.out.println("Gameplay Constructor called");
		play = new Player();
		dungeon = new Dungeon();
		//NOTE that there will be a problem later on when climbing UP stairs
		//since you'd probably have 2 players, or maybe not
		//currLevel = dungeon.lvl.get(currNumLevel);
		currLevel = new Level(currNumLevel, play);
		//dungeon = new ArrayList<Level>();
		dungeon.lvl.add(currLevel);
		System.out.println("Gameplay Constructor called");
		currNumLevel=0;
		narration = "";
		goUp = true;
	}
	
	public void climbStairs() {
		if(hasAmulet==true) {
			
			//dungeon.currLevel--;
			//if (dungeon.currLevel==-1) {
				//View.printVictoryScreen();
			//}
		} else {
			//dungeon.currLevel++;
		}
	}
	
	public boolean checkWin() {
		
		if (hasAmulet==true /*&& dungeon.currLevel==-1*/) {
			return true;
		}
		return false;
	}
	
	//resolves attack. returns 0 if nobody died, 1 if defender died, 2 if attacker died
	/*public void unitAttack(Unit attacker, Unit defender) {
		
		String text= "";
		int[] attack = attacker.fight();
		
		if(attack[0]>=defender.getArmor()){
			defender.takeDamage(attack[1]);
			text= attacker.getName() + " hit " + defender.getName()+ " for " +attack[1]+ " damage";
			if(defender.isDead()){
				//Item i=((Enemy) defender).dropTreasure();
				//drop item
				//if(i!=null){
				//	level.addItem(i, this.level.unitLocation(defender));
			//	}
				text=attacker.getName() + " hit " + defender.getName()+ " for " +attack[1]+ " damage and defeated it!";
				this.level.removeUnit(defender);
			}
		}else{
			text= attacker.getName()+ " missed "+ defender.getName();
		}
		
		
		this.view.updateNaration(text);
	}*/
	
	public int move(int[] direction) {
		int update=0;
		
		/* 
		 * I changed this to work with Level
		 * Player needs to be shared between Level and GamePlay
		 */
		int x=currLevel.moveUnit(currLevel.play, direction);
		if(x==6){
			/*
			Level l = new Level(1,play);
			dungeon.add(l);
			this.currlevel++;
			this.level = l;
			*/
			
			/**
			this.currNumLevel++;
			Level tempLvl = new Level(currNumLevel, play);
			dungeon.lvl.add(tempLvl);
			
			this.currLevel = dungeon.lvl.get(currNumLevel);
			**/
			
			changeFloor(goUp);
		}
		if(x==1){
			update=1;
			narration=currLevel.narration;
		}
		return update;
		
		//Add randomly move enemy
	}
	
	public void changeFloor(boolean change) {
		if(!change /*&& this.currNumLevel >= 0*/) {
			this.currNumLevel--;
			//System.out.println("Can't go down!");
			//return;
			//TODO Consider floor -1 (can't go down) case - basically win condition with amulet
		} else {
			this.currNumLevel++;
		}
		
		Level tempLvl;
		
		//only dealing with going down dungeon right now
		if(this.currNumLevel >= dungeon.lvl.size()) {
			//add new floor
			tempLvl = new Level(currNumLevel, play);
			dungeon.lvl.add(tempLvl);
		} else {
			//go to existing floor
			//TODO fix this, might not work since play contains position of player of previous floor
			tempLvl = dungeon.lvl.get(currNumLevel);
			tempLvl.play = this.play;	//update player
		}
		
		this.currLevel = tempLvl;
	}
	
	public void saveGame(String[] test) {
		lines[0]+= "i am testing";
		lines [5] += "all of this a test";
		List<String> thingy = Arrays.asList("Testing testing", "one two three");
		Charset utf8 = StandardCharsets.UTF_8;

		try {
			Files.write(Paths.get("saveGame.txt"), thingy, utf8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*public static void main(String[] args) {
		
		GamePlay game = new GamePlay();
		String[] dyingnoises = new String[4];
		game.saveGame(dyingnoises); 
		
		//creates needed instances
		game.play = new Player();
		game.view= new RougeView();
		game.level= new Level(game.play);
		
		//makes a board
		view.updateBoard(game.level.getSeenFloor());
		view.updateStats(game.play.playerStats());
		view.nextTurn();
		
		//makes controller
		Controller c= new Controller("cat");
		c.sendInfo(game,view);
	}*/
}