public class Unit {
	//  representation on the board
	protected String val;
	// This might be the same as val?
	protected String boardName;
	// Statistics for the Unit
	protected int level;
	protected int strength;
	protected int hp;
	protected int maxHP;
	protected int armor;
	public boolean dead;

/*
	Defines a Units stats and health.
*/
	public Unit() {
		hp = 100;
		armor = 1;
		level = 1;
		strength = 1;
		val = "N";
		dead = false;
	}
/*
	Creates a Unit with specified armor, strength
	and with a value to represent it.
*/
	public Unit(int a, int s, String k) {
		val = k;
		hp = 100;
		level = 1;
		armor = a;
		strength = s;
		dead = false;
	}

	// This gives the
	public String toString(){
		return boardName;
	}

	// Returns the unit's value
	public String getVal(){
		return val;
	}

	//returns armor value
	public int getArmor(){
		return this.armor;
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


	public void takeDamage(int damage){
		this.hp-=damage;
		if(this.hp<=0){
			isDead=true;
		}
	}

	public void move(int[] dir) {
		// if level.validMove(dir)
		// then makeMove

		// TODO figure out this direction thing
		//Call level.move(Player, Direction)?
	}

	public boolean isDead() {
		return isDead;
	}
}
