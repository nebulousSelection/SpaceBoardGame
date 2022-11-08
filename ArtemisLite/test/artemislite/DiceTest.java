/**
 * 
 */
package artemislite;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Dice Class
 *
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */

class DiceTest {

	// test data
	Random random;
	Dice dice;
	DiceTester diceTester;
	int validRollLower, validRollMid, validRollUpper, invalidRollLower, invalidRollUpper;
	int validDieRollLower, validDieRollMid, validDieRollUpper, invalidDieRollLower, invalidDieRollUpper;
	int dieOne, dieTwo, totalRoll;
	String dieOneExceptionMessage, dieTwoExceptionMessage, lastRolledExceptionMessage;

	@BeforeEach
	void setUp() throws Exception {

		dice = new Dice();
		diceTester = new DiceTester();

		// valid ranges for dice total
		validRollLower = 2;
		validRollMid = 7;
		validRollUpper = 12;

		// Invalid ranges for dice total
		invalidRollLower = 1;
		invalidRollUpper = 13;

		// valid single die
		validDieRollLower = 1;
		validDieRollMid = 3;
		validDieRollUpper = 6;

		// invalid single die
		invalidDieRollLower = 0;
		invalidDieRollUpper = 7;

		// Exception messages that are thrown by components of the dice class
		dieOneExceptionMessage = "Dice one rolled a number outside its range.";
		dieTwoExceptionMessage = "Dice Two rolled a number outside its range.";
		lastRolledExceptionMessage = "Dice roll out of valid range.";

	}

	/**
	 * test default constructor for dice
	 */

	@Test
	void testDice() {
		assertNotNull(dice);
	}

	/**
	 * test valid getter and setter for diceOne
	 */

	@Test
	void testGetSetDieOneValid() {
		// Valid lower
		dice.setDieOne(validDieRollLower);
		assertEquals(validDieRollLower, dice.getDieOne());
		// Valid Mid
		dice.setDieOne(validDieRollMid);
		assertEquals(validDieRollMid, dice.getDieOne());
		// Valid Upper
		dice.setDieOne(validDieRollUpper);
		assertEquals(validDieRollUpper, dice.getDieOne());

	}

	/**
	 * Test setting the value of diceOne with invalid values
	 * 
	 * @throws IllegalArgumentException
	 */

	@Test
	void testGetSetDieOneInvalid() throws IllegalArgumentException {
		IllegalArgumentException illegalArgumentExceptionLower = assertThrows(IllegalArgumentException.class, () -> {
			dice.setDieOne(invalidDieRollLower);

		});

		assertEquals(dieOneExceptionMessage, illegalArgumentExceptionLower.getLocalizedMessage());

		IllegalArgumentException illegalArgumentExceptionUpper = assertThrows(IllegalArgumentException.class, () -> {
			dice.setDieOne(invalidDieRollUpper);

		});

		assertEquals(dieOneExceptionMessage, illegalArgumentExceptionUpper.getLocalizedMessage());
	}

	/**
	 * test valid getter and setter for diceTwo
	 */

	@Test
	void testGetSetDieTwoValid() {
		// Valid lower
		dice.setDieTwo(validDieRollLower);
		assertEquals(validDieRollLower, dice.getDieTwo());
		// Valid Mid
		dice.setDieTwo(validDieRollMid);
		assertEquals(validDieRollMid, dice.getDieTwo());
		// Valid Upper
		dice.setDieTwo(validDieRollUpper);
		assertEquals(validDieRollUpper, dice.getDieTwo());

	}

	/**
	 * Test setting the value of diceTwo with invalid values
	 * 
	 * @throws IllegalArgumentException
	 */

	@Test
	void testGetSetDieTwoInvalid() throws IllegalArgumentException {
		IllegalArgumentException illegalArgumentExceptionLower = assertThrows(IllegalArgumentException.class, () -> {
			dice.setDieTwo(invalidDieRollLower);

		});

		assertEquals(dieTwoExceptionMessage, illegalArgumentExceptionLower.getLocalizedMessage());

		IllegalArgumentException illegalArgumentExceptionUpper = assertThrows(IllegalArgumentException.class, () -> {
			dice.setDieTwo(invalidDieRollUpper);

		});

		assertEquals(dieTwoExceptionMessage, illegalArgumentExceptionUpper.getLocalizedMessage());
	}

	/**
	 * test valid getter and setter for diceTwo
	 */

	@Test
	void testGetSetLastRolledValid() {
		diceTester.setLastRolled(validRollLower);
		assertEquals(validRollLower, diceTester.getLastRolled());

		diceTester.setLastRolled(validRollMid);
		assertEquals(validRollMid, diceTester.getLastRolled());

		diceTester.setLastRolled(validRollUpper);
		assertEquals(validRollUpper, diceTester.getLastRolled());
	}

	/**
	 * Test setting the value of diceTwo with invalid values
	 * 
	 * @throws IllegalArgumentException
	 */

	@Test
	void testGetSetLastRolledInvalid() throws IllegalArgumentException {

		IllegalArgumentException illegalArgumentExceptionLower = assertThrows(IllegalArgumentException.class, () -> {
			diceTester.setLastRolled(invalidRollLower);

		});

		assertEquals(lastRolledExceptionMessage, illegalArgumentExceptionLower.getLocalizedMessage());

		IllegalArgumentException illegalArgumentExceptionUpper = assertThrows(IllegalArgumentException.class, () -> {
			diceTester.setLastRolled(invalidRollLower);

		});

		assertEquals(lastRolledExceptionMessage, illegalArgumentExceptionUpper.getLocalizedMessage());
	}

	/**
	 * Tests boolean getter and setter for Dice rolled
	 */
	@Test
	void testSetIsDiceRolled() {
		dice.setDiceRolled(true);
		assertEquals(true, dice.isDiceRolled());

		dice.setDiceRolled(false);
		assertEquals(false, dice.isDiceRolled());

		dice.setDiceRolled(true);
		assertEquals(true, dice.isDiceRolled());
	}

	/**
	 * tests for valid roll as this is a random number the scope of this test is
	 * limit this is why the other areas of the dice class are checked more
	 * thoroughly
	 */

	@Test
	void testRollDice() {
		dice.rollDice();
		totalRoll = dice.getLastRolled();
		assertTrue((validRollLower <= totalRoll) && (totalRoll <= validRollUpper));

	}

}

class DiceTester extends Dice {

	@Override
	public int getDieOne() {
		// TODO Auto-generated method stub
		return super.getDieOne();
	}

	@Override
	public void setDieOne(int dieOne) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		super.setDieOne(dieOne);
	}

	@Override
	public int getDieTwo() {
		// TODO Auto-generated method stub
		return super.getDieTwo();
	}

	@Override
	public void setDieTwo(int dieTwo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		super.setDieTwo(dieTwo);
	}

	@Override
	public boolean isDiceRolled() {
		// TODO Auto-generated method stub
		return super.isDiceRolled();
	}

	@Override
	public void setDiceRolled(boolean diceRolled) {
		// TODO Auto-generated method stub
		super.setDiceRolled(diceRolled);
	}

	@Override
	public int getLastRolled() {
		// TODO Auto-generated method stub
		return super.getLastRolled();
	}

	@Override
	protected void setLastRolled(int lastRolled) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		super.setLastRolled(lastRolled);
	}

	@Override
	public void rollDice() {
		// TODO Auto-generated method stub
		super.rollDice();
	}

}
