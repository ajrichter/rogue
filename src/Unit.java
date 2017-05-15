import java.awt.Point;

public class Unit {
	protected String name;
	protected char val;
	protected int level, xp, hp, maxHP, armor, maxStrength, hunger, maxHunger, strength, maxArmor;
	protected boolean dead, fight;
	protected Point p;

	public Unit() {
		this.dead = false;
		p = new Point();
	}

	public boolean isEnemy() {
		return !(this.val == '@');
	}

	// Returns the Unit's character representation
	public String toString() {
		return " " + val;
	}

	public String getName() {
		return name;
	}

	// Lowers a Unit's HP from a fight.
	public void takeDamage(int damage) {
		this.hp -= damage;
		if (this.hp <= 0) {
			dead = true;
		}
	}

	// returns hit value and damage
	public int[] fight() {
		DiceRoller d = new DiceRoller();
		int[] attack = new int[2];
		attack[0] = d.rollDie(20) + 1;
		attack[1] = d.rollDie(6) + 1 + ((this.strength - 10) / 2);
		if (attack[1] < 0) {
			attack[1] = 0;
		}
		return attack;
	}
}
