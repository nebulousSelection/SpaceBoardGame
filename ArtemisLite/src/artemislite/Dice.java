/**
 *
 * 
 */
package artemislite;

import java.util.Random;

/**
 * This class allows the creation of a dice object to allow a player to roll a
 * dice in the game. It also keeps track of the values from the last time the
 * dice were rolled and if the player has rolled them on a given turn.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */

public class Dice {

	// Constants declaration
	private static final int DIE_MINIMUM = 1, DIE_MAXIMUM = 6;
	private static final int MINIMUM_ROLL = 2, MAXIMUM_ROLL = 12;
	private static final int MAXIMUM_RETRY = 3;
	private static final int SHORT_DELAY = 250, LONG_DELAY = 400;

	// Declare instance variables
	private int dieOne;
	private int dieTwo;
	private boolean diceRolled;
	private int lastRolled;

	/**
	 * Default constructor for dice class
	 */

	public Dice() {

	}

	/**
	 * Returns int value of die one's rolled amount
	 * 
	 * @return the dieOne
	 */
	public int getDieOne() {
		return dieOne;
	}

	/**
	 * Sets the roll amount for die one
	 * 
	 * @param dieOne rolled amount
	 * @throws IllegalArgumentException if dieOne is outside of range
	 */
	public void setDieOne(int dieOne) throws IllegalArgumentException {
		if (dieOne <= DIE_MAXIMUM && dieOne >= DIE_MINIMUM) {
			this.dieOne = dieOne;
		} else {
			throw new IllegalArgumentException("Dice one rolled a number outside its range.");
		}
	}

	/**
	 * Returns int value of die two's rolled amount
	 * 
	 * @return the dieTwo
	 */
	public int getDieTwo() {
		return dieTwo;
	}

	/**
	 * Sets the roll amount for die two
	 * 
	 * @param dieTwo rolled amount
	 * @throws IllegalArgumentException if dieTwo is outside of range
	 */
	public void setDieTwo(int dieTwo) throws IllegalArgumentException {
		if (dieTwo <= DIE_MAXIMUM && dieTwo >= DIE_MINIMUM) {
			this.dieTwo = dieTwo;
		} else {
			throw new IllegalArgumentException("Dice Two rolled a number outside its range.");
		}
	}

	/**
	 * A boolean to check if the dice has been rolled yet
	 * 
	 * @return the diceRolled
	 */
	public boolean isDiceRolled() {
		return diceRolled;
	}

	/**
	 * Sets the diceRolled for a particular turn
	 * 
	 * @param diceRolled
	 */
	public void setDiceRolled(boolean diceRolled) {
		this.diceRolled = diceRolled;
	}

	/**
	 * Returns the total roll of dieOne and dieTwo from the last time the roll dice
	 * method was called
	 * 
	 * @return the lastRolled
	 */
	public int getLastRolled() {
		return lastRolled;
	}

	/**
	 * Sets the value from the last time the dice was rolled
	 * 
	 * @param lastRolled total of dieOne and dieTwo
	 * @throws IllegalArgumentException if the total of dieOne and dieTwo is outside
	 *                                  the expected range
	 */
	protected void setLastRolled(int lastRolled) throws IllegalArgumentException {
		if (lastRolled >= MINIMUM_ROLL && lastRolled <= MAXIMUM_ROLL) {
			this.lastRolled = lastRolled;
		} else {
			throw new IllegalArgumentException("Dice roll out of valid range.");
		}
	}

	/**
	 * This method adds two randomly generated numbers together representing each
	 * die side and outputs to screen a user's total roll
	 * 
	 * 
	 * @throws Exception
	 */
	public void rollDice() {
		// Variable declared for total dice roll for player
		int totalRoll, retry;
		boolean validRoll;
		validRoll = false;

		retry = 0;
		// Creates a new random number generator
		Random random = new Random();
		try {
			// Random number generated and 1 added to get the dice side total
			do {

				try {

					if (retry == MAXIMUM_RETRY) {
						throw new Exception("Dice roll not in valid range");
					}

					setDieOne(random.nextInt(DIE_MAXIMUM) + DIE_MINIMUM);
					setDieTwo(random.nextInt(DIE_MAXIMUM) + DIE_MINIMUM);
					totalRoll = this.dieOne + this.dieTwo;
					if (totalRoll >=MINIMUM_ROLL && totalRoll <= MAXIMUM_ROLL) {

						this.setLastRolled(totalRoll);
						rollMessage();
						validRoll = true;
					} else {
						validRoll = false;
						retry++;
					}

				} catch (IllegalArgumentException illegalArgumentException) {
					System.out.println("Error with dice, rerolling");
					validRoll = false;
					retry++;
				}

			} while (validRoll == false);
		} catch (Exception e) {
			// needs to throw this error to the main have updated to for testing purposes
			System.out.println("Error with dice, retried : " + MAXIMUM_RETRY + " times ");

		}
	}

	/**
	 * Outputs the screen the total of each dice roll and the final sum as the total
	 * roll. Formatting added to output box around dice. Thread implemented to add a
	 * delay between the rolls
	 */

	private void rollMessage() {

		System.out.println("% % % % % % % % % % % % % % % % % % % % % % % %");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(" Dice one rolls");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(".");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(".");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(". ");
		ArtemisLite.delay(LONG_DELAY);
		System.out.print(dieOne + "\t");

		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(" Dice two rolls");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(".");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(".");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(". ");
		ArtemisLite.delay(LONG_DELAY);
		System.out.print(dieTwo + "\n");

		System.out.println();

		ArtemisLite.delay(SHORT_DELAY);
		System.out.print(" Roll total: ");
		ArtemisLite.delay(LONG_DELAY);
		System.out.print(lastRolled + "\n");
		ArtemisLite.delay(LONG_DELAY);
		System.out.println("% % % % % % % % % % % % % % % % % % % % % % % %\n");
	}


}