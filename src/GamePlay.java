import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GamePlay {
	private boolean gameOver = false;
	protected boolean hasAmulet = false;
	Player play;
	ArrayList<Level> dungeon;
	Level level;
	String narration;
	String narration2;

	public GamePlay() {
		String s = "John Dooley";
		switch (ThreadLocalRandom.current().nextInt(0, 4 + 1)) {
		case 0:
			s = "Myra";
			break;
		case 1:
			s = "Ink";
			break;
		case 2:
			s = "Harry";
			break;
		case 3:
			s = "Andrew";
			break;
		default:
			s = "John Dooley";
			break;
		}
		play = new Player(s);

		level = new Level(0, play);
		dungeon = new ArrayList<Level>();
		dungeon.add(level);
		narration = "";
		narration2 = "";
	}

	public boolean checkWin() {
		if (hasAmulet == true) {
			return true;
		}
		return false;
	}

	public int throwItem(int itemNum) {
		int[] dir = { 0, 1 };
		int update = level.throwItem(itemNum, play, dir);
		narration = level.narration;
		return update;
	}

	public int equipOrConsumeItem(int itemNum) {
		int update = level.updatePlayerStatsAfterEquip(itemNum);
		narration = level.narration;
		return update;
	}

	public int printInventory() {
		int update = level.printInventory();
		narration = level.narration;
		return update;
	}

	public int move(int[] direction) {
		int update = 0;

		if (!gameOver) {
			System.out.println("Player wont move");
			int x = level.moveUnit(level.play, direction);
			if (x == 6) {
				if (hasAmulet) {
					ascend();
				} else {
					if (level.numLevel < level.numDungeons) {
						descend();
					} else {
						narration = "You've reached the last floor! Find the Amulet!";
						update = 6;
					}
				}
			} else if (x == 1) {
				update = 1;
				narration = level.narration;
				narration2 = level.narration2;
			} else if (x == 3 || x == 9) {
				update = x;
				narration = level.narration;
			} else if (x == 12) {
				update = 12;
				gameOver = true;
			} else if (x == 26) {
				update = 26;
				narration = level.narration;
				hasAmulet = true;
			}
			System.out.println("Monsters arent moving");
			level.moveEnemy(direction);
			System.out.println("ok they are");
		}
		return update;
	}

	public void ascend() {
		if (level.numLevel > 0) {
			Level l = dungeon.get(level.numLevel - 1); 
			l.ascend(play);
			this.level = l;
		}
	}

	/* Can't go down any further than 25 */
	public void descend() {
		if (level.numLevel < 25) {
			System.out.println("Going down " + level.numLevel);
			Level l = new Level(level.numLevel + 1, play);
			dungeon.add(l);
			this.level = l;
		}
	}
}