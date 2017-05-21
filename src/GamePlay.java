import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GamePlay {
	private boolean gameOver = false;
	protected boolean hasAmulet = false;
	Player play;
	ArrayList<Level> dungeon;
	Level level;
	private String[] lines = new String[10];
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
			int x = level.moveUnit(level.play, direction);
			if (x == 6) {
				if (hasAmulet) {
					if (level.numLevel < level.numDungeons) {
						System.out.println("Reached!");
						update = 14;
					}
					ascend();
				}

				else {
					if (!hasAmulet && level.numLevel < level.numDungeons) {
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
			} else if (x == 3) {
				update = 3;
				narration = level.narration;
			} else if (x == 9) {
				update = 9;
				narration = level.narration;
			} else if (x == 12) {
				update = 12;
				gameOver = true;
			} else if (x == 26) {
				update = 26;
				narration = level.narration;
				hasAmulet = true;
			}
			level.moveEnemy(direction);
		}

		return update;
	}

	/*
	 * Player needs to get spawned And enemies recreated
	 */
	public void ascend() {
		if (level.numLevel > 0) {
			this.level = dungeon.get(level.numLevel--);

		}
	}

	/* Can't go down any further than L25 */
	public void descend() {
		if (level.numLevel < 25) {
			System.out.println("Going down " + level.numLevel);
			Level l = new Level(level.numLevel + 1, play);
			dungeon.add(l);
			this.level = l;
		}
	}

	public void saveGame(String[] test) {
		lines[0] += "i am testing";
		lines[5] += "all of this a test";
		List<String> thingy = Arrays.asList("Testing testing", "one two three");
		Charset utf8 = StandardCharsets.UTF_8;

		try {
			Files.write(Paths.get("saveGame.txt"), thingy, utf8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}