/*
	TODO:
	Move
	isDead
	How are stats used?
*/
public class Unit {
	//  representation on the board
	protected String val;
	// Statistics for the Unit
	protected int level;
	protected int strength;
	protected int hp;
	protected int maxHP;
	protected int armor;
	public boolean dead;

/*
	Defines stats and health for the Unit.
*/
	public Unit() {
		this(1, 1,"N");
	}

	// Creates a Unit with values
	public Unit(int a, int s, String k) {
		val = k;
		hp = 10;
		maxHP = 10;
		level = 1;
		armor = a;
		strength = s;
		dead = false;
	}

	// Returns the Unit's character representation
	public String toString(){
		return val;
	}

	// Returns the armor value.
	public int getArmor(){
		return this.armor;
	}

	// Lowers a Unit's HP from a fight.
	public void takeDamage(int damage){
		this.hp-=damage;
		if(this.hp<=0){
			isDead=true;
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

	// this is not needed since movement occurs in Level or GamePlay
	public void move(int[] dir) {
		// if level.validMove(dir)
		// then makeMove

		// TODO figure out this direction thing
		//Call level.move(Player, Direction)?
	}

	/* Returns true if alive
		False if dead.
		isDead is changes after each fight.
	*/
	public boolean isDead() {
		return isDead;
	}
}
