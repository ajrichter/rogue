
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import Item.Item;

public class mainForTestingItem {
	
	private boolean hasAmulet=false;
	private Player play; 
	private Level level;
	private static RougeView view;
	private String[] lines = new String[10];
	
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
	

	
	public void help() {
		//View.printControlMenu();
	}
	
	public void option() {
		//View.printOptionMenu();
	}
	
	public void move(int[] direction) {
			//picking up item
			if(level.isItem(this.play, direction)!=null){
				level.pickUp(this.play, direction);
				view.updateBoard(level.getSeenFloor());
				view.updateNaration(play.getName()+ " has found the Amulet of Yendor and won the game!!!");
			}
			//moves to new space
			level.moveUnit(this.play,direction);
		
		
		view.nextTurn();
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
	
	
	public static void main(String[] args) {
		
		mainForTestingItem game = new mainForTestingItem();
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
		ControllerForTestingItem c= new ControllerForTestingItem("cat");
		c.sendInfo(game,view);
	}
}