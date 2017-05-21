
import java.util.concurrent.ThreadLocalRandom;

public class DiceRoller {
	public DiceRoller() {}
	/*
		Java1.8 API Proper way of generating random numbers.
		public int nextInt(int origin, int bound)
			Returns a pseudorandom int value between the specified origin (inclusive)
			and the specified bound (exclusive).
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
	 */
	public int rollDie(int sides) {
		return ThreadLocalRandom.current().nextInt(1, sides + 1);
	}

	public int roll(int numD, int numS) {
		int sum = 0;
		for(int i = 0; i < numD; i++){
			sum += rollDie(numS);
		}
		return sum;
	}
}
