package Item;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;



public class mainForTestingItem {

	private boolean hasAmulet=false;
	private PlayerForItem play; 
	private ItemLevel level;
	private static RougeViewItem view;
	private String[] lines = new String[10];
	private Inventory i = new Inventory();
	
	private static Item [] items;
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
		Item currentItem = level.isItem(this.play, direction);
		
		if(currentItem !=null){
				if (i.addItem(currentItem))
				{
					level.pickUp(this.play, direction);
					view.updateBoard(level.getSeenFloor());
					view.updateNaration(currentItem.finalMessage);
				}
				else
				{
					 view.updateNaration("Can't add item. " + currentItem.typeItem + " is full");
				}
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
		game.play = new PlayerForItem();
		game.view= new RougeViewItem();
		
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		Item item5 = new Item();
		Item item6 = new Item();
		Item item7 = new Item();
		Item item8 = new Item();
		Item item9 = new Item();
		Item item10 = new Item();
		Item item11 = new Item();
		Item item12 = new Item();
		item1.generateItem();
		item2.generateItem();
		item3.generateItem();
		item4.generateItem();
		item5.generateItem();
		item6.generateItem();
		item7.generateItem();
		item8.generateItem();
		item9.generateItem();
		item10.generateItem();
		item11.generateItem();
		item12.generateItem();
		
		
		items = new Item [] {item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12};
	

		game.level= new ItemLevel(game.play, game.items);



		//makes a board
		view.updateBoard(game.level.getSeenFloor());
		view.updateStats(game.play.playerStats());
		view.nextTurn();

		//makes controller
		ControllerForTestingItem c= new ControllerForTestingItem("cat");
		c.sendInfo(game,view);
	}
}