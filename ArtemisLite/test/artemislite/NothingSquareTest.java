/**
 * 
 */
package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for NothingSquare
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
class NothingSquareTest {

	// objects
	NothingSquare nothingSquare;
	Player player;

	// name
	String validNameLower, validNameMid, validNameUpper;
	String invalidNameNull, invalidNameLower, invalidNameUpper;

	// superclass exception messages
	String nullSquareNameExceptionMessage, invalidSquareNameExceptionMessage, invalidPositionOnBoardExceptionMessage;

	// messages
	String messageFirst, messageMid, messageLast;
	String messageExceptionMessage;

	// people
	String personFirst, personMid, personLast;
	String personExceptionMessage;

	// LandedOn variables
	String nothingSquareLandedOnMessage;
	String playerName;

	// toString
	String toStringReturn;

	// position on board
	int validPositionLower, validPositionMid, validPositionUpper;
	int invalidPositionLower, invalidPositionUpper;

	// messages indices
	int messagesMinIndex, messagesMidIndex, messagesMaxIndex;
	int messagesInvalidIndexLower, messagesInvalidIndexUpper;

	// people indices
	int peopleMinIndex, peopleMidIndex, peopleMaxIndex;
	int peopleInvalidIndexLower, peopleInvalidIndexUpper;

	// doActionReturn
	int nothingSquareInt;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		// name
		validNameLower = "Lor";
		validNameMid = "Lorem ipsum do";
		validNameUpper = "Lorem ipsum dolor sit ame";
		invalidNameNull = null;
		invalidNameLower = "Lo";
		invalidNameUpper = "Lorem ipsum dolor sit amet";
		nullSquareNameExceptionMessage = "Square name is null.";
		invalidSquareNameExceptionMessage = "Invalid Square Name Length.";

		// position on board
		validPositionLower = 0;
		validPositionMid = 5;
		validPositionUpper = 11;
		invalidPositionLower = -1;
		invalidPositionUpper = 12;
		invalidPositionOnBoardExceptionMessage = "Position on board out of bounds.";

		// messages
		messagesMinIndex = 0;
		messagesMidIndex = 10;
		messagesMaxIndex = 19;

		messagesInvalidIndexLower = -1;
		messagesInvalidIndexUpper = 21;

		messageFirst = "Just letting you know we have sold your drum kit \nwhile you are away, hope that won't be a problem....";
		messageMid = "Since we decided a few weeks ago to adopt the leaf \nas legal tender, we have, of course, all become immensely rich.";
		messageLast = "I got your distress call and I came as soon as I wanted to.";

		messageExceptionMessage = "Index out of bounds for messages";

		// people
		peopleMinIndex = 0;
		peopleMidIndex = 5;
		peopleMaxIndex = 10;

		peopleInvalidIndexLower = -1;
		peopleInvalidIndexUpper = 11;

		personFirst = "Mum";
		personMid = "Granny";
		personLast = "Arch Nemesis";

		personExceptionMessage = "Index out of bounds for people";

		// toString
		playerName = "testPlayer";
		nothingSquareLandedOnMessage = playerName + " has landed on " + validNameLower;

		// doAction
		nothingSquareInt = 0;
	}

	/**
	 * Test method for default constructor
	 * 
	 * Test method for {@link artemislite.NothingSquare#NothingSquare()}.
	 */
	@Test
	void testNothingSquare() {
		nothingSquare = new NothingSquare();
		assertNotNull(nothingSquare);
	}

	/**
	 * Test method for
	 * {@link artemislite.NothingSquare#NothingSquare(java.lang.String, int, boolean)}.
	 * 
	 * Test for NothingSquare with valid arguments
	 */
	@Test
	void testNothingSquareStringIntBooleanValid() {

		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
		assertEquals(validNameLower, nothingSquare.getSquareName());
		assertEquals(validPositionLower, nothingSquare.getPositionOnBoard());
		assertEquals(false, nothingSquare.isPurchasable());

		assertEquals(messageFirst, nothingSquare.getMessage(messagesMinIndex));
		assertEquals(messageMid, nothingSquare.getMessage(messagesMidIndex));
		assertEquals(messageLast, nothingSquare.getMessage(messagesMaxIndex));

		assertEquals(personFirst, nothingSquare.getPerson(peopleMinIndex));
		assertEquals(personMid, nothingSquare.getPerson(peopleMidIndex));
		assertEquals(personLast, nothingSquare.getPerson(peopleMaxIndex));

		nothingSquare = new NothingSquare(validNameMid, validPositionMid, true);
		assertEquals(validNameMid, nothingSquare.getSquareName());
		assertEquals(validPositionMid, nothingSquare.getPositionOnBoard());
		assertEquals(true, nothingSquare.isPurchasable());

		assertEquals(messageFirst, nothingSquare.getMessage(messagesMinIndex));
		assertEquals(messageMid, nothingSquare.getMessage(messagesMidIndex));
		assertEquals(messageLast, nothingSquare.getMessage(messagesMaxIndex));

		assertEquals(personFirst, nothingSquare.getPerson(peopleMinIndex));
		assertEquals(personMid, nothingSquare.getPerson(peopleMidIndex));
		assertEquals(personLast, nothingSquare.getPerson(peopleMaxIndex));

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);
		assertEquals(validNameUpper, nothingSquare.getSquareName());
		assertEquals(validPositionUpper, nothingSquare.getPositionOnBoard());
		assertEquals(false, nothingSquare.isPurchasable());

		assertEquals(messageFirst, nothingSquare.getMessage(messagesMinIndex));
		assertEquals(messageMid, nothingSquare.getMessage(messagesMidIndex));
		assertEquals(messageLast, nothingSquare.getMessage(messagesMaxIndex));

		assertEquals(personFirst, nothingSquare.getPerson(peopleMinIndex));
		assertEquals(personMid, nothingSquare.getPerson(peopleMidIndex));
		assertEquals(personLast, nothingSquare.getPerson(peopleMaxIndex));
	}

	/**
	 * Test method for
	 * {@link artemislite.NothingSquare#NothingSquare(java.lang.String, int, boolean)}.
	 */
	@Test
	void testNothingSquareStringIntBooleanInvalid() throws IllegalArgumentException, NullPointerException {
		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare(invalidNameLower, validPositionLower, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare(invalidNameUpper, validPositionLower, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			nothingSquare = new NothingSquare(invalidNameNull, validPositionLower, false);
		});
		assertEquals(nullSquareNameExceptionMessage, invalidNameNullException.getMessage());

		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare(validNameLower, invalidPositionLower, false);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare(validNameMid, invalidPositionUpper, true);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionUpperException.getMessage());
	}

	/**
	 * Test method for {@link artemislite.NothingSquare#getPerson(int)}.
	 * 
	 * Tests that person can be retrieved from array successfully.As the array is
	 * private the test is implemented by checking values from the start middle and
	 * end
	 */
	@Test
	void testGetPersonValid() {
		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
		assertEquals(personFirst, nothingSquare.getPerson(peopleMinIndex));
		assertEquals(personMid, nothingSquare.getPerson(peopleMidIndex));
		assertEquals(personLast, nothingSquare.getPerson(peopleMaxIndex));

		nothingSquare = new NothingSquare(validNameMid, validPositionMid, true);
		assertEquals(personFirst, nothingSquare.getPerson(peopleMinIndex));
		assertEquals(personMid, nothingSquare.getPerson(peopleMidIndex));
		assertEquals(personLast, nothingSquare.getPerson(peopleMaxIndex));

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);
		assertEquals(personFirst, nothingSquare.getPerson(peopleMinIndex));
		assertEquals(personMid, nothingSquare.getPerson(peopleMidIndex));
		assertEquals(personLast, nothingSquare.getPerson(peopleMaxIndex));
	}

	/**
	 * Test method for {@link artemislite.NothingSquare#getPerson(int)}.
	 * 
	 * Test that numbers outside of the valid range of the array throw appropriate
	 * Exception
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	void testGetPersonInvalid() throws IndexOutOfBoundsException {
		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
		Exception personOutOfBoundsLower = assertThrows(IndexOutOfBoundsException.class, () -> {
			nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
			nothingSquare.getPerson(invalidPositionLower);
		});

		assertEquals(personExceptionMessage, personOutOfBoundsLower.getMessage());

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);
		Exception personOutOfBoundsUpper = assertThrows(IndexOutOfBoundsException.class, () -> {
			nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
			nothingSquare.getPerson(invalidPositionUpper);
		});

		assertEquals(personExceptionMessage, personOutOfBoundsUpper.getMessage());
	}

	/**
	 * Test method for {@link artemislite.NothingSquare#getMessage(int)}.
	 * 
	 * Tests that message can be retrieved from array successfully. As the array is
	 * private the test is implemented by checking values from the start middle and
	 * end
	 */
	@Test
	void testGetMessageValid() {
		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
		assertEquals(messageFirst, nothingSquare.getMessage(messagesMinIndex));
		assertEquals(messageMid, nothingSquare.getMessage(messagesMidIndex));
		assertEquals(messageLast, nothingSquare.getMessage(messagesMaxIndex));

		nothingSquare = new NothingSquare(validNameMid, validPositionMid, true);
		assertEquals(messageFirst, nothingSquare.getMessage(messagesMinIndex));
		assertEquals(messageMid, nothingSquare.getMessage(messagesMidIndex));
		assertEquals(messageLast, nothingSquare.getMessage(messagesMaxIndex));

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);
		assertEquals(messageFirst, nothingSquare.getMessage(messagesMinIndex));
		assertEquals(messageMid, nothingSquare.getMessage(messagesMidIndex));
		assertEquals(messageLast, nothingSquare.getMessage(messagesMaxIndex));
	}

	/**
	 * Test method for {@link artemislite.NothingSquare#getMessage(int)}.
	 * 
	 * Test that numbers outside of the valid range of the array throw appropriate
	 * Exception
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	@Test
	void testGetMessageInvalid() throws IndexOutOfBoundsException {

		Exception messageOutOfBoundsLower = assertThrows(IndexOutOfBoundsException.class, () -> {
			nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
			nothingSquare.getMessage(messagesInvalidIndexLower);
		});

		assertEquals(messageExceptionMessage, messageOutOfBoundsLower.getMessage());

		Exception personOutOfBoundsUpper = assertThrows(IndexOutOfBoundsException.class, () -> {
			nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
			nothingSquare.getMessage(messagesInvalidIndexUpper);
		});

		assertEquals(messageExceptionMessage, personOutOfBoundsUpper.getMessage());
	}

	/**
	 * Test method for {@link artemislite.Square#getSquareName()}. and
	 * {@link artemislite.Square#setSquareName(java.lang.String)}.
	 * 
	 * Test SquareName getter and setter with valid values
	 */
	@Test
	void testGetSetSquareNameValid() {
		nothingSquare = new NothingSquare();
		nothingSquare.setSquareName(validNameLower);
		assertEquals(validNameLower, nothingSquare.getSquareName());

		nothingSquare = new NothingSquare();
		nothingSquare.setSquareName(validNameMid);
		assertEquals(validNameMid, nothingSquare.getSquareName());

		nothingSquare = new NothingSquare();
		nothingSquare.setSquareName(validNameUpper);
		assertEquals(validNameUpper, nothingSquare.getSquareName());
	}

	/**
	 * Test method for {@link artemislite.Square#getSquareName()}. and
	 * {@link artemislite.Square#setSquareName(java.lang.String)}.
	 * 
	 * Test SquareName getter and setter with invalid values
	 */
	@Test
	void testGetSquareNameInvalid() throws IllegalArgumentException, NullPointerException {
		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare();
			nothingSquare.setSquareName(invalidNameLower);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare();
			nothingSquare.setSquareName(invalidNameUpper);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			nothingSquare = new NothingSquare();
			nothingSquare.setSquareName(invalidNameNull);
		});
		assertEquals(nullSquareNameExceptionMessage, invalidNameNullException.getMessage());

	}

	/**
	 * Test method for {@link artemislite.Square#getPositionOnBoard()}. and
	 * {@link artemislite.Square#setPositionOnBoard(int)}.
	 * 
	 * Test getter and setter for setting the squares position on the board with
	 * valid values
	 */

	@Test
	void testGetSetPositionOnBoardValid() {
		nothingSquare = new NothingSquare();
		nothingSquare.setPositionOnBoard(validPositionLower);
		assertEquals(validPositionLower, nothingSquare.getPositionOnBoard());

		nothingSquare = new NothingSquare();
		nothingSquare.setPositionOnBoard(validPositionMid);
		assertEquals(validPositionMid, nothingSquare.getPositionOnBoard());

		nothingSquare = new NothingSquare();
		nothingSquare.setPositionOnBoard(validPositionUpper);
		assertEquals(validPositionUpper, nothingSquare.getPositionOnBoard());
	}

	/**
	 * Test method for {@link artemislite.Square#getPositionOnBoard()}. and
	 * {@link artemislite.Square#setPositionOnBoard(int)}.
	 * 
	 * Test getter and setter for setting the squares position on the board with
	 * invalid values
	 */

	@Test
	void testGetSetPositionOnBoardInvalid() {
		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare();
			nothingSquare.setPositionOnBoard(invalidPositionLower);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			nothingSquare = new NothingSquare();
			nothingSquare.setPositionOnBoard(invalidPositionUpper);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionUpperException.getMessage());

	}

	/**
	 * Test method for {@link artemislite.Square#isPurchasable()}. and
	 * {@link artemislite.Square#setPurchasable(boolean)}
	 * 
	 * testing getter and setter of boolean condition isPurchasable
	 */
	@Test
	void testIsSetPurchasable() {
		nothingSquare = new NothingSquare();
		nothingSquare.setPurchasable(false);
		assertEquals(false, nothingSquare.isPurchasable());
		nothingSquare.setPurchasable(true);
		assertEquals(true, nothingSquare.isPurchasable());

	}

	/**
	 * Test method for {@link artemislite.NothingSquare#toString()}.
	 */
	@Test
	void testToString() {
		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);

		toStringReturn = "NothingSquare [getSquareName()=" + validNameLower + ", getPositionOnBoard()="
				+ validPositionLower + ", isPurchasable()=" + false + "]";

		assertEquals(toStringReturn, nothingSquare.toString());

		nothingSquare = new NothingSquare(validNameMid, validPositionMid, true);

		toStringReturn = "NothingSquare [getSquareName()=" + validNameMid + ", getPositionOnBoard()=" + validPositionMid
				+ ", isPurchasable()=" + true + "]";

		assertEquals(toStringReturn, nothingSquare.toString());

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);

		toStringReturn = "NothingSquare [getSquareName()=" + validNameUpper + ", getPositionOnBoard()="
				+ validPositionUpper + ", isPurchasable()=" + false + "]";

		assertEquals(toStringReturn, nothingSquare.toString());

	}

	/**
	 * Test method for
	 * {@link artemislite.NothingSquare#landedOn(artemislite.Player)}.
	 */
	@Test
	void testLandedOn() {
		player = new Player();
		player.setPlayerName(playerName);

		nothingSquare = new NothingSquare();
		nothingSquare.setSquareName(validNameLower);
		assertEquals(nothingSquareLandedOnMessage, nothingSquare.landedOn(player));

	}

	/**
	 * Test method for {@link artemislite.NothingSquare#doAction()}.
	 */
	@Test
	void testDoAction() {
		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
		nothingSquare.doAction();
		assertEquals(nothingSquareInt, nothingSquare.doAction());

		nothingSquare = new NothingSquare(validNameMid, validPositionMid, true);
		assertEquals(nothingSquareInt, nothingSquare.doAction());

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);
		assertEquals(nothingSquareInt, nothingSquare.doAction());
	}

	/**
	 * Test method for {@link artemislite.NothingSquare#displayAll()}.
	 */
	@Test
	void testDisplayAll() {

		nothingSquare = new NothingSquare(validNameLower, validPositionLower, false);
		nothingSquare.displayAll();

		nothingSquare = new NothingSquare(validNameMid, validPositionMid, true);
		nothingSquare.displayAll();

		nothingSquare = new NothingSquare(validNameUpper, validPositionUpper, false);
		nothingSquare.displayAll();
	}

}
