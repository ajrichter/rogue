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
			if (currentItem.getTypeItem().equalsIgnoreCase("Weapon"))
			{
				if (i.addItem(currentItem))
				{
					level.pickUp(this.play, direction);
					view.updateBoard(level.getSeenFloor());
					view.updateNaration(play.getName()+ " aquired the " + currentItem.getName() + " " + currentItem.getTypeItem() + ". It has " + currentItem.getDamageFromW() + " damage.");
				}
				else
				{
					view.updateNaration("Can't add item. " + currentItem.getTypeItem() + " is full");
				}
				
			}
			else if (currentItem.getTypeItem().equalsIgnoreCase("Armor"))
			{
				if (i.addItem(currentItem))
				{
					level.pickUp(this.play, direction);
					view.updateBoard(level.getSeenFloor());
					view.updateNaration(play.getName()+ " aquired the " + currentItem.getName() + " " + currentItem.getTypeItem() + ". It has " + currentItem.getProtectionFromArmor() + " protection.");
				}
				else
				{
					view.updateNaration("Can't add item. " + currentItem.getTypeItem() + " is full");
				}
			}
			else if (currentItem.getTypeItem().equalsIgnoreCase("Food"))
			{
				if (i.addItem(currentItem))
				{
					level.pickUp(this.play, direction);
					view.updateBoard(level.getSeenFloor());
					view.updateNaration(play.getName()+ " aquired " + currentItem.getName() + " " + currentItem.getTypeItem() + ". It has " + currentItem.getStrengthFromFood() + " strength.");
				}
				else
				{
					view.updateNaration("Can't add item. " + currentItem.getTypeItem() + " is full");
				}
			}
			else if (currentItem.getTypeItem().equalsIgnoreCase("Scrolls"))
			{
				
				if (i.addItem(currentItem))
				{
					level.pickUp(this.play, direction);
					view.updateBoard(level.getSeenFloor());
					view.updateNaration(play.getName()+ " aquired " + currentItem.getName() + " " + currentItem.getTypeItem() + ". Resulting message:" + currentItem.getScrollMessage());
				}
				else
				{
					view.updateNaration("Can't add item. " + currentItem.getTypeItem() + " is full");
				}
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
		
		Weapon w = new Weapon("Two-Handed Sword", "Weapon");
		Weapon w2 = new Weapon("Dagger", "Weapon");
		Weapon w3 = new Weapon("Rock", "Weapon");
		
		
		Scrolls s = new Scrolls("Scare", "Scrolls");
		Scrolls s2 = new Scrolls("Light", "Scrolls");
		Scrolls s3 = new Scrolls("Confuse", "Scrolls");
		
		
		Potions p = new Potions("Restore", "Potions");
		Potions p2 = new Potions("Blind", "Potions");
		Potions p3 = new Potions("Pollyjuice Potion", "Potions");
		
		
		Armor a = new Armor("Plate mail", "Armor");
		Armor a2 = new Armor("Leather armor", "Armor");
		Armor a3 = new Armor("Dragonscales", "Armor");
		
		
		Food f = new Food("Trump Steaks", "Food");
		Food f2 = new Food("Fruit Snacks", "Food");
		Food f3 = new Food("Banana", "Food");

		items = new Item [] {w, w2, w3, s, s2, s3, a, a2, a3, f, f2, f3, p, p2, p3};
	

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