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
	ArrayList<Level> dungeon;
	Level level;
	private String[] lines = new String[10];
	int currlevel;
	String narration;
	
	public GamePlay() {
        System.out.println("Gameplay Constructor called");
		play = new Player();
		level = new Level(1, play);
		dungeon = new ArrayList<Level>();
		dungeon.add(level);
		System.out.println("Gameplay Constructor called");
		currlevel=0;
		narration = "";
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
		int x=level.moveUnit(level.play, direction);
		if(x==6){
			Level l = new Level(1,play);
			dungeon.add(l);
			this.currlevel++;
			this.level = l;
		}
		if(x==1){
			update=1;
			narration=level.narration;
		}
		if(x == 3) {
			update = 3;
			narration = level.narration;
		}
		return update;
		
		//Add randomly move enemy
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