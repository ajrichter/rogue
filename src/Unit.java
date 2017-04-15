import java.awt.Point;

/*
	TODO:
	Move
	isDead
	How are stats used?
*/
public class Unit {
	//  representation on the board
	protected String val, name;
	// Statistics for the Unit
	protected int level, xp, strength, hp, maxHP, armor;
	protected boolean dead;
	private Point p;

	// All done in Subclasses
	// Clearly not dead as it has just been constructed!
	public Unit() {
		this.dead = false;
		p = new Point();
	}

	public boolean isEnemy(){
		return !this.val.equals("@");
	}
	
	public void setP(int x, int y){
		p.setLocation(x,  y);
	}
	
	public Point getP(){
		return p;
	}

	// Returns the Unit's character representation
	public String toString(){
		return val;
	}

	public String getName(){
		return name;
	}

	// Returns the armor value.
	public int getArmor(){
		return armor;
	}

	// Lowers a Unit's HP from a fight.
	public void takeDamage(int damage){
		this.hp-=damage;
		if(this.hp<=0){
			dead=true;
		}
	}

	//returns hit value and damage
	public int[] fight() {
		DiceRoller d= new DiceRoller();
		int[] attack =new int[2];
		attack[0]= d.rollDie(20)+1;
		attack[1]=d.rollDie(6)+1+((this.strength-10)/2);
		if(attack[1]<0){
			attack[1]=0;
		}
		return attack;
	}

	public boolean isDead() {
		return dead;
	}
}
