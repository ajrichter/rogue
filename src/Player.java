import java.util.ArrayList;
import java.util.Random;

public class Player extends Unit {
	protected boolean hasA, hasW, hallucination, blindness, hasteSelf, levitation, light, monsterInvisible,
			monsterDetection, magicDetection = false;
	protected boolean identifyWandRing, identifyPotion, idenitfyWeapon, identifyArmor, foodDetection,
			identifyScroll = false;
	protected int gold, nexp, steps;
	protected ArrayList<Item> items;
	protected Inventory inventory;
	private String narrationMessage = "";
	private DiceRoller d;

	public Player(String s) {
		super();
		/* C stats */
		this.val = '@';
		this.name = s;
		this.level = 1;
		this.xp = 0;
		this.nexp = 10;
		this.maxHP = this.hp = 12;
		this.maxStrength = this.strength = 20;
		this.gold = 0;
		this.armor = 2;
		/* Our Stats */
		this.maxArmor = 20;
		this.steps = 0;
		this.hunger = this.maxHunger = 1000;
		inventory = new Inventory();
		items = new ArrayList<Item>();
		d = new DiceRoller();
	}

	/*
	 * Edit this to include armor etc.
	 */
	public int attack() {
		Random r = new Random();
		if (this.strength - 1 >= 1)
		{
		this.strength--;
		}
		else {
		this.strength = 1;
		}
		return r.nextInt(this.strength);
	}

	public void useItem(Item item) {

		switch (item.getItemType()) {
		case "Armor":
			if (this.armor + item.getArmorProtection() < this.maxArmor) {
				this.armor += item.getArmorProtection();
			} else {
				this.maxArmor++;
				this.armor = this.maxArmor;
			}
			break;
		case "Food":
			if (this.hunger + item.getPlayerHunger() < this.maxHunger) {
				this.hunger += item.getPlayerHunger();
			} else {
				this.hunger = this.maxHunger;
			}
			break;
		case "Weapon":
			if (this.strength + item.getWeaponStrength() < this.maxStrength) {
				this.strength += item.getWeaponStrength();
			} else {
				this.maxStrength++;
				this.strength = this.maxStrength;
			}

			break;
		case "Ring":
			if (this.strength + item.getRingStrength() < this.maxStrength) {
				this.strength += item.getRingStrength();
			} else {
				this.maxStrength++;
				this.strength = this.maxStrength;
			}
			this.gold += item.gold;
			this.hp += item.getHPFromRing();
			break;
		case "Potions":
			if (item.getExtraHealing() || item.getHealing()) {
				if (this.hp + item.getHPFromPotion() < this.maxHP) {
					this.hp += item.getHPFromPotion();
				} else {
					if (item.getHealing()) {
						this.maxHP++;
					}
					if (item.getHealing()) {
						DiceRoller dice = new DiceRoller();
						this.maxHP += dice.rollDie(2);
					}

					this.hp = this.maxHP;
				}
			}

			this.blindness = item.getBlindness();
			this.hasteSelf = item.getHasteSelf();
			this.levitation = item.getLevitation();
			this.monsterDetection = item.monsterDetection();
			this.magicDetection = item.magicDetection();

			if (item.restoreStrength()) {
				this.strength = this.maxStrength;
			}

			this.hallucination = item.getHallucination();

			if (this.strength + item.getPotionStrength() < this.maxStrength) {
				this.strength += item.getPotionStrength();
			} else {
				this.maxStrength += 1;
				this.strength = this.maxStrength;
			}
			this.xp += item.getPlayerXP();

			break;
		case "Scroll":
			this.idenitfyWeapon = item.identifyWeapon();
			this.identifyArmor = item.identifyArmor();
			this.identifyPotion = item.identifyPotions();
			this.identifyScroll = item.identifyScoll();
			this.identifyWandRing = item.idenitfyWandOrRing();
			this.foodDetection = item.identifyFood();

			break;
		case "Wand":
			this.light = item.getLight();
			if (this.strength + item.getWandStrength() < this.maxStrength) {
				this.strength += item.getWandStrength();
			} else {
				this.maxStrength++;
				this.strength = this.maxStrength;

			}
			this.monsterInvisible = item.monsterInvisible();

			break;

		}
	}

	public void dropItem(Item item) {
		inventory.removeItem(item);
		narrationMessage = this.name + item.getDropMessage();

	}

	public String getNarrationMessage() {
		return narrationMessage;
	}

	/*  Level: 1  Gold: 0      Hp: 12(12)  Str: 16(16)  Arm: 4   Exp: 1/0  */
	public String pStats() {
		return " Gold: " + this.gold +  " HP: " + this.hp + " (" + this.maxHP + ") " + " Str:" + this.strength + " (" + this.maxStrength + ")" + " Armor: " + this.armor + " Exp: " + this.level + "/" + this.xp;
	}
	

	/*
	 * Hunger goes down too fast: this needs to be fixed
	 * 
	 * Regenerate HP Check LevelUp Check Hunger Stats decrease
	 */
	public String move() {
		narrationMessage = "";
		if (!narrationMessage.equals("You have fainted.")) {
			steps++;
			if (steps >= 20) {
				steps = 0;
				if (hp < maxHP)
					hp++;
			}

			if (xp >= nexp)
				levelUp();
			hunger--;
		}

		if (hunger == 100)
			narrationMessage = "You should probably stop by the Gizmo.";
		else if (hunger == 50)
			narrationMessage = "You are starving!";
		else if (hunger == 0)
			narrationMessage = "You have fainted.";

		return narrationMessage;

	}

	/*
	 * This should be sent to the narration "Welcome to Level X!"
	 */
	public void levelUp() {
		nexp = nexp * 2;
		level++;
	}

	public int getGold() {
		return this.gold;
	}
}