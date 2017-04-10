package Item;
public class Potions extends Item {
		private int damage = 0;
		private String name; 
		private String typeItem;
		

		public Potions() {
			this.boardName = "!";
				
		}
		
		
	
		public void getPotion()
		{
			DiceRollerItem d= new DiceRollerItem();
			
			switch (d.rollDie(13))
			{
			case 1: this.name = "Confuse";
			break;
			
			case 2: this.name = "Poision";
			break;
			
			case 3: this.name = "Healing";
			break;
			
			case 4: this.name = "MFIND";
			break;
			
			case 5: this.name = "TFIND";
			break;
			
			case 6: this.name = "Paralyze";
			break;
			
			case 7: this.name = "SeeInvisiable";
			break;
			
			case 8: this.name = "Raise";
			break;
			
			case 9: this.name = "XHeal";
			break;
			
			case 10: this.name = "Haste";
			break;
			
			case 11: this.name = "Restore";
			this.potionMessage = "Hey, this tastes great.  It make you feel warm all over.";
			break;
			
			case 12: this.name = "Blind";
			this.potionMessage = "A cloak of darkness falls around you.";
			break;
			
			case 13: this.name = "Mystery";
			this.potionMessage =  "What an odd tasting potion!";
			break;
			
			default: this.name = "NOP";
			this.potionMessage = "This potion tastes extremely dull.";
			break;	
			
			}
		}	
	}










