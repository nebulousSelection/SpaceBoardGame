/**
 * 
 */
package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for abstract Square class
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */

class SquareTest {

	// objects
	Square square;
	Player p;

	// name
	String validNameLower, validNameMid, validNameUpper;
	String invalidNameNull, invalidNameLower, invalidNameUpper;
	String nullSquareNameExceptionMessage, invalidSquareNameExceptionMessage, invalidPositionOnBoardExceptionMessage;

	// landedOn
	String nothingSquareLandedOnMessage, resourceSquareLandedOnMessage, elementSquareLandedOnMessage;

	// Player Name
	String playerName;

	// position on board
	int validPositionLower, validPositionMid, validPositionUpper;
	int invalidPositionLower, invalidPositionUpper;

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

		// landedon
		playerName = "testPlayer";

		// landed on
		nothingSquareLandedOnMessage = playerName + " has landed on " + validNameLower;
		resourceSquareLandedOnMessage = playerName + " has landed on " + validNameMid;
		elementSquareLandedOnMessage = playerName + " has landed on " + validNameUpper;
	}

	/**
	 * Test default constructor Square
	 * 
	 * Test method for {@link artemislite.Square#Square()}.
	 */
	@Test
	void testSquare() {
		square = new ElementSquare();
		assertNotNull(square);

		square = new NothingSquare();
		assertNotNull(square);

		square = new ResourceSquare();
		assertNotNull(square);

	}

	/**
	 *
	 * Test method for
	 * {@link artemislite.Square#Square(java.lang.String, int, boolean)}.
	 * 
	 * Testing Constructor with arguments with valid values
	 *
	 */
	@Test
	void testSquareStringIntBooleanValid() {

		square = new NothingSquare(validNameLower, validPositionLower, false);
		assertEquals(validNameLower, square.getSquareName());
		assertEquals(validPositionLower, square.getPositionOnBoard());
		assertEquals(false, square.isPurchasable());

		square = new NothingSquare(validNameMid, validPositionMid, true);
		assertEquals(validNameMid, square.getSquareName());
		assertEquals(validPositionMid, square.getPositionOnBoard());
		assertEquals(true, square.isPurchasable());

		square = new NothingSquare(validNameUpper, validPositionUpper, false);
		assertEquals(validNameUpper, square.getSquareName());
		assertEquals(validPositionUpper, square.getPositionOnBoard());
		assertEquals(false, square.isPurchasable());
	}

	/**
	 * Test method for
	 * {@link artemislite.Square#Square(java.lang.String, int, boolean)}.
	 * 
	 * Testing Constructor with arguments with invalid values
	 * 
	 * @throws IllegalArgumentException for invalid name length or invalid position
	 *                                  on board
	 * @throws NullPointerException     for null name
	 */

	@Test
	void testSquareStringIntBooleanInvalid() throws IllegalArgumentException, NullPointerException {

		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			square = new NothingSquare(invalidNameLower, validPositionLower, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			square = new NothingSquare(invalidNameUpper, validPositionLower, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			square = new NothingSquare(invalidNameNull, validPositionLower, false);
		});
		assertEquals(nullSquareNameExceptionMessage, invalidNameNullException.getMessage());

		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {
			square = new NothingSquare(validNameLower, invalidPositionLower, false);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			square = new NothingSquare(validNameMid, invalidPositionUpper, true);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionUpperException.getMessage());

	}

	/**
	 * Test method for {@link artemislite.Square#getSquareName()}. and
	 * {@link artemislite.Square#setSquareName(java.lang.String)}.
	 * 
	 * Test SquareName getter and setter with valid values
	 */
	@Test
	void testGetSetSquareNameValid() {
		square = new NothingSquare();
		square.setSquareName(validNameLower);
		assertEquals(validNameLower, square.getSquareName());

		square = new ElementSquare();
		square.setSquareName(validNameMid);
		assertEquals(validNameMid, square.getSquareName());

		square = new ResourceSquare();
		square.setSquareName(validNameUpper);
		assertEquals(validNameUpper, square.getSquareName());
	}

	/**
	 * Test method for {@link artemislite.Square#getSquareName()}. and
	 * {@link artemislite.Square#setSquareName(java.lang.String)}.
	 * 
	 * Test SquareName getter and setter with invalid values
	 * 
	 * @throws IllegalArgumentException for invalid name
	 * @throws NullPointerException     for null name
	 */
	@Test
	void testGetSquareNameInvalid() throws IllegalArgumentException, NullPointerException {
		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			square = new NothingSquare();
			square.setSquareName(invalidNameLower);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			square = new ElementSquare();
			square.setSquareName(invalidNameUpper);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			square = new ResourceSquare();
			square.setSquareName(invalidNameNull);
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
		square = new NothingSquare();
		square.setPositionOnBoard(validPositionLower);
		assertEquals(validPositionLower, square.getPositionOnBoard());

		square = new ElementSquare();
		square.setPositionOnBoard(validPositionMid);
		assertEquals(validPositionMid, square.getPositionOnBoard());

		square = new ResourceSquare();
		square.setPositionOnBoard(validPositionUpper);
		assertEquals(validPositionUpper, square.getPositionOnBoard());
	}

	/**
	 * * Test method for {@link artemislite.Square#getPositionOnBoard()}. and
	 * {@link artemislite.Square#setPositionOnBoard(int)}.
	 * 
	 * Test getter and setter for setting the squares position on the board with
	 * invalid values
	 * 
	 * @throws IllegalArgumentException for invalid position on board
	 */
	@Test
	void testGetSetPositionOnBoardInvalid() throws IllegalArgumentException {
		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {
			square = new NothingSquare();
			square.setPositionOnBoard(invalidPositionLower);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			square = new ResourceSquare();
			square.setPositionOnBoard(invalidPositionUpper);
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
		square = new NothingSquare();
		square.setPurchasable(false);
		assertEquals(false, square.isPurchasable());
		square.setPurchasable(true);
		assertEquals(true, square.isPurchasable());

		square = new ElementSquare();
		square.setPurchasable(false);
		assertEquals(false, square.isPurchasable());
		square.setPurchasable(true);
		assertEquals(true, square.isPurchasable());

		square = new ResourceSquare();
		square.setPurchasable(false);
		assertEquals(false, square.isPurchasable());
		square.setPurchasable(true);
		assertEquals(true, square.isPurchasable());
	}

	/**
	 * Test method for {@link artemislite.Square#toString()}. Testing toString
	 * method is not null, as this is an abstract method further testing will be
	 * done in the relevant sub classes
	 */
	@Test
	void testToString() {
		square = new ElementSquare();
		assertNotNull(square.toString());

		square = new NothingSquare();
		assertNotNull(square.toString());

		square = new ResourceSquare();
		assertNotNull(square.toString());
	}

	/**
	 * Test method for {@link artemislite.Square#landedOn(artemislite.Player)}.
	 * Testing method is not null, as this is an abstract method further testing
	 * will be done in the relevant sub classes
	 * 
	 * 
	 */
	@Test
	void testLandedOn() {
		Player p = new Player();
		p.setPlayerName(playerName);

		square = new NothingSquare();
		square.setSquareName(validNameLower);
		assertEquals(nothingSquareLandedOnMessage, square.landedOn(p));

		square = new ResourceSquare();
		square.setSquareName(validNameMid);
		assertEquals(resourceSquareLandedOnMessage, square.landedOn(p));

		square = new ElementSquare();
		square.setSquareName(validNameUpper);
		ElementSquare elementSquare = (ElementSquare) square;
		// avoids null pointer exception
		elementSquare.setSystem(ESystem.SYSTEM_ONE);
		assertEquals(elementSquareLandedOnMessage, square.landedOn(p));
	}
}
