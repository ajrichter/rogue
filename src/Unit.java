public class Unit {

	protected int statusTime;
	protected int level;
	protected int strength;
	protected int hp;
	protected int maxHP;
	protected int armor;
	public boolean isDead=false;
	protected String name;
	protected String boardName;
	
	public Unit() {
		// TODO constructor and stuff
	}
	
	public String toString(){
		return boardName;
	}
	
	public String getName(){
		return name;
	}
	
	//returns armor
	public int getAC(){
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
		// TODO figure out this direction thing
		//Call level.move(Player, Direction)?
	}
	
	public boolean isDead() {
		// TODO write the code and maybe add in an attribute
		
		return false;
	}
}