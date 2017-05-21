import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Controller extends JFrame implements KeyListener {
	private GamePlay game;
	private RougeView view;
	public static JFrame frame;
	public boolean narration;
	private boolean equipItem = false;
	private boolean dropItem = false;
	private boolean nextNarration; // if there is a second narration
	public boolean ascend = false;

	public Controller(String s) {
		super(s);

		narration = false;

		frame = new JFrame();
		frame.setSize(1000, 500);
		frame.setMinimumSize(new Dimension(1000, 400));
		frame.addKeyListener(this);
		frame.setVisible(true);

		// initialize variables
		this.game = new GamePlay();
		this.view = new RougeView(frame);		
		view.updateBoard(game.level.getFloor());
		view.nextTurn();
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			if (nextNarration == true) {
				if (!game.narration2.equals("")) {
					view.updateNaration(game.narration2);
					narration = false;
				} else {
					narration = false;
					view.updateNaration("");
				}
				nextNarration = false;
			} else {
				narration = false;
				view.updateNaration("");
			}
			frame.repaint();
		}

		if (narration == false) {
			System.out.println("Trying to move");
			
			if (key == KeyEvent.VK_H || key == KeyEvent.VK_LEFT) {
				int[] left = { -1, 0 };
				int update = game.move(left);
				updateView(update);
			} else if (key == KeyEvent.VK_L || key == KeyEvent.VK_RIGHT) {
				int[] right = { 1, 0 };
				int update = game.move(right);
				updateView(update);
			} else if (key == KeyEvent.VK_J || key == KeyEvent.VK_DOWN) {
				int[] down = { 0, 1 };
				int update = game.move(down);
				updateView(update);
			} else if (key == KeyEvent.VK_K || key == KeyEvent.VK_UP) {
				int[] up = { 0, -1 };
				int update = game.move(up);
				updateView(update);
			} else if (key == KeyEvent.VK_Y) {
				// moves diagonally left and up
				int[] leftup = { -1, 1 };
				int update = game.move(leftup);
				updateView(update);
			} else if (key == KeyEvent.VK_U) {
				// moves diagonally right and up
				int[] rightup = { 1, 1 };
				int update = game.move(rightup);
				updateView(update);
			} else if (key == KeyEvent.VK_B) {
				// moves diagonally left and down
				int[] leftdown = { -1, -1 };
				int update = game.move(leftdown);
				updateView(update);
			} else if (key == KeyEvent.VK_N) {
				// moves diagonally right and down
				int[] rightdown = { 1, -1 };
				int update = game.move(rightdown);
				updateView(update);
			} else if (key == KeyEvent.VK_PERIOD) {
				/* Rest */
				view.nextTurn();
			} else if (key == KeyEvent.VK_I) {
				int update = game.printInventory();
				updateView(update);
			} else if (key == KeyEvent.VK_E) {
				equipItem = true;
			} else if (key == KeyEvent.VK_D) {
				dropItem = true;
			}

			if (equipItem == true) {
				if (key == KeyEvent.VK_0) {
					int update = game.equipOrConsumeItem(0);
					updateView(update);
				} else if (key == KeyEvent.VK_1) {
					int update = game.equipOrConsumeItem(1);
					updateView(update);
				} else if (key == KeyEvent.VK_2) {
					int update = game.equipOrConsumeItem(2);
					updateView(update);
				} else if (key == KeyEvent.VK_3) {
					int update = game.equipOrConsumeItem(3);
					updateView(update);
				} else if (key == KeyEvent.VK_4) {
					int update = game.equipOrConsumeItem(4);
					updateView(update);
				}
			}

			if (dropItem == true) {
				if (key == KeyEvent.VK_0) {
					int update = game.throwItem(0);
					updateView(update);
				} else if (key == KeyEvent.VK_1) {
					int update = game.throwItem(1);
					updateView(update);
				} else if (key == KeyEvent.VK_2) {
					int update = game.throwItem(2);
					updateView(update);
				} else if (key == KeyEvent.VK_3) {
					int update = game.throwItem(3);
					updateView(update);
				} else if (key == KeyEvent.VK_4) {
					int update = game.throwItem(4);
					updateView(update);
				}
			}
		}
		/*
		 * This is a perfect example of the M-V-C framework It is *concise* and
		 * the Controller moderates between the View and Model.
		 */
		view.updateStats(game.level.stats());

		System.out.println("KeyPress Finished");
	}

	public void keyTyped(KeyEvent e) {
	}

	public void updateView(int update) {
		if (update == 14) {
			game.dungeon.get(game.level.numDungeons - 1).spawnP();

		}

		if (update == 12) {
			view.lose();
		}
		if (game.level.numLevel == 0 && game.hasAmulet) {
			view.win();
		}
		if (update == 9 || update == 1 || update == 3 || update == 4 || update == 5 || update == 6 || update == 7
				|| update == 10 || update == 26) {
			view.updateNaration(game.narration);
			this.dropItem = false;
			this.equipItem = false;

			if (update == 1 || update == 5 || update == 6 || update == 7 || update == 9 || update == 10
					|| update == 26) {
				narration = true;
			}

			if (update == 1) {
				nextNarration = true;
			}
		} else {
			view.updateNaration("");
		}
		view.updateBoard(game.level.getFloor());
		view.nextTurn();
		System.out.println("View updated");
	}

	public static void main(String[] args) {
		Controller control = new Controller("Rogue");
		control.view.updateBoard(control.game.level.getFloor());
		control.view.updateStats(control.game.level.stats());
		control.view.nextTurn();
	}
}