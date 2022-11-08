/**
 * 
 */
package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for ResourceSquare 
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
class ResourceSquareTest {

	// objects
	ResourceSquare resourceSquare;
	Player player;

	// name
	String validNameLower, validNameMid, validNameUpper;
	String invalidNameNull, invalidNameLower, invalidNameUpper;

	// superclass exception messages
	String nullSquareNameExceptionMessage, invalidSquareNameExceptionMessage, invalidPositionOnBoardExceptionMessage;

	// LandedOn variables
	String resourceSquareLandedOnMessage;
	String playerName;

	// toString
	String toStringReturn;

	String resourceAmountExceptionMessage;

	// position on board
	int validPositionLower, validPositionMid, validPositionUpper;
	int invalidPositionLower, invalidPositionUpper;

	// resource amounts
	int validResourceMin, validResourceMidNegative, validResourceMidPositive, validResourceMax;
	int invalidResourceAmount;

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

		// resource amounts
		validResourceMin = Integer.MIN_VALUE;
		validResourceMidNegative = Integer.MIN_VALUE / 2;
		validResourceMidPositive = Integer.MAX_VALUE / 2;
		validResourceMax = Integer.MAX_VALUE;
		invalidResourceAmount = 0;
		resourceAmountExceptionMessage = "Resource adjustment level cannot be zero.";

		// toString
		playerName = "testPlayer";
		resourceSquareLandedOnMessage = playerName + " has landed on " + validNameLower;
	}

	/**
	 * Test method for default constructor
	 * 
	 * Test method for {@link artemislite.ResourceSquare#ResourceSquare()}.
	 */
	@Test
	void testResourceSquare() {
		resourceSquare = new ResourceSquare();
		assertNotNull(resourceSquare);
	}

	/**
	 * Test method for
	 * {@link artemislite.ResourceSquare#ResourceSquare(String, int, int, boolean)
	 * 
	 * Test for ResourceSquare with valid arguments
	 */
	@Test
	void testResourceSquareStringintintbooleanValid() {

		resourceSquare = new ResourceSquare(validNameLower, validPositionLower, validResourceMin, false);
		assertEquals(validNameLower, resourceSquare.getSquareName());
		assertEquals(validPositionLower, resourceSquare.getPositionOnBoard());
		assertEquals(validResourceMin, resourceSquare.getResourceAdjustment());
		assertEquals(false, resourceSquare.isPurchasable());

		resourceSquare = new ResourceSquare(validNameMid, validPositionMid, validResourceMidNegative, true);
		assertEquals(validNameMid, resourceSquare.getSquareName());
		assertEquals(validPositionMid, resourceSquare.getPositionOnBoard());
		assertEquals(validResourceMidNegative, resourceSquare.getResourceAdjustment());
		assertEquals(true, resourceSquare.isPurchasable());

		resourceSquare = new ResourceSquare(validNameLower, validPositionUpper, validResourceMidPositive, false);
		assertEquals(validNameLower, resourceSquare.getSquareName());
		assertEquals(validPositionUpper, resourceSquare.getPositionOnBoard());
		assertEquals(validResourceMidPositive, resourceSquare.getResourceAdjustment());
		assertEquals(false, resourceSquare.isPurchasable());

		resourceSquare = new ResourceSquare(validNameUpper, validPositionUpper, validResourceMax, true);
		assertEquals(validNameUpper, resourceSquare.getSquareName());
		assertEquals(validPositionUpper, resourceSquare.getPositionOnBoard());
		assertEquals(validResourceMax, resourceSquare.getResourceAdjustment());
		assertEquals(true, resourceSquare.isPurchasable());

	}

	/**
	 * Test method for
	 * {@link artemislite.ResourceSquare#ResourceSquare(String, int, int, boolean)
	 * 
	 * Test for ResourceSquare with invalid arguments
	 */
	@Test
	void testResourceSquareStringintintbooleanInvalid() throws IllegalArgumentException, NullPointerException {
		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare(invalidNameLower, validPositionLower, validResourceMin, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare(invalidNameUpper, validPositionMid, validResourceMidPositive, true);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			resourceSquare = new ResourceSquare(invalidNameNull, validPositionUpper, validResourceMax, false);
		});
		assertEquals(nullSquareNameExceptionMessage, invalidNameNullException.getMessage());

		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare(validNameLower, invalidPositionLower, validResourceMin, true);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare(validNameUpper, invalidPositionUpper, validResourceMax, false);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionUpperException.getMessage());

		Exception invalidResourceException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare(validNameMid, validPositionMid, invalidResourceAmount, true);
		});
		assertEquals(resourceAmountExceptionMessage, invalidResourceException.getMessage());

	}

	/**
	 * Test method for {@link artemislite.Square#getSquareName()}. and
	 * {@link artemislite.Square#setSquareName(java.lang.String)}.
	 * 
	 * Test SquareName getter and setter with valid values
	 */
	@Test
	void testGetSetSquareNameValid() {
		resourceSquare = new ResourceSquare();
		resourceSquare.setSquareName(validNameLower);
		assertEquals(validNameLower, resourceSquare.getSquareName());

		resourceSquare = new ResourceSquare();
		resourceSquare.setSquareName(validNameMid);
		assertEquals(validNameMid, resourceSquare.getSquareName());

		resourceSquare = new ResourceSquare();
		resourceSquare.setSquareName(validNameUpper);
		assertEquals(validNameUpper, resourceSquare.getSquareName());
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
			resourceSquare = new ResourceSquare();
			resourceSquare.setSquareName(invalidNameLower);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare();
			resourceSquare.setSquareName(invalidNameUpper);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			resourceSquare = new ResourceSquare();
			resourceSquare.setSquareName(invalidNameNull);
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
		resourceSquare = new ResourceSquare();
		resourceSquare.setPositionOnBoard(validPositionLower);
		assertEquals(validPositionLower, resourceSquare.getPositionOnBoard());

		resourceSquare = new ResourceSquare();
		resourceSquare.setPositionOnBoard(validPositionMid);
		assertEquals(validPositionMid, resourceSquare.getPositionOnBoard());

		resourceSquare = new ResourceSquare();
		resourceSquare.setPositionOnBoard(validPositionUpper);
		assertEquals(validPositionUpper, resourceSquare.getPositionOnBoard());
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
			resourceSquare = new ResourceSquare();
			resourceSquare.setPositionOnBoard(invalidPositionLower);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			resourceSquare = new ResourceSquare();
			resourceSquare.setPositionOnBoard(invalidPositionUpper);
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
		resourceSquare = new ResourceSquare();
		resourceSquare.setPurchasable(false);
		assertEquals(false, resourceSquare.isPurchasable());
		resourceSquare.setPurchasable(true);
		assertEquals(true, resourceSquare.isPurchasable());

	}

	/**
	 * Test method for {@link artemislite.ResourceSquare#toString()}.
	 * 
	 * Test for the overridden toStringMethod
	 */
	@Test
	void testToString() {
		resourceSquare = new ResourceSquare(validNameLower, validPositionLower, validResourceMin, false);

		toStringReturn = "ResourceSquare [getSquareName()=" + validNameLower + ", getPositionOnBoard()="
				+ validPositionLower + ", isPurchasable()=" + false + "getresourceAdjustment()=" + validResourceMin
				+ "]";

		assertEquals(toStringReturn, resourceSquare.toString());

		resourceSquare = new ResourceSquare(validNameMid, validPositionMid, validResourceMidNegative, true);

		toStringReturn = "ResourceSquare [getSquareName()=" + validNameMid + ", getPositionOnBoard()="
				+ validPositionMid + ", isPurchasable()=" + true + "getresourceAdjustment()=" + validResourceMidNegative
				+ "]";

		assertEquals(toStringReturn, resourceSquare.toString());

		resourceSquare = new ResourceSquare(validNameLower, validPositionUpper, validResourceMidPositive, false);

		toStringReturn = "ResourceSquare [getSquareName()=" + validNameLower + ", getPositionOnBoard()="
				+ validPositionUpper + ", isPurchasable()=" + false + "getresourceAdjustment()="
				+ validResourceMidPositive + "]";

		assertEquals(toStringReturn, resourceSquare.toString());

		resourceSquare = new ResourceSquare(validNameUpper, validPositionUpper, validResourceMax, true);

		toStringReturn = "ResourceSquare [getSquareName()=" + validNameUpper + ", getPositionOnBoard()="
				+ validPositionUpper + ", isPurchasable()=" + true + "getresourceAdjustment()=" + validResourceMax
				+ "]";

		assertEquals(toStringReturn, resourceSquare.toString());

	}

	/**
	 * Test method for
	 * {@link artemislite.ResourceSquarelandedOn(artemislite.Player)}.
	 * 
	 * Simple test to check that the playername and the squareName are returned
	 * correctly in the landed on message
	 */
	@Test
	void testLandedOn() {
		player = new Player();
		player.setPlayerName(playerName);

		resourceSquare = new ResourceSquare();
		resourceSquare.setSquareName(validNameLower);

		assertEquals(resourceSquareLandedOnMessage, resourceSquare.landedOn(player));

	}

	/**
	 * Test method for {@link artemislite.ResourceSquare#doAction()}.
	 * 
	 * Checks that the return value is equal to the set resourceAmount
	 */
	@Test
	void testDoAction() {
		resourceSquare = new ResourceSquare(validNameLower, validPositionLower, validResourceMin, false);

		assertEquals(validResourceMin, resourceSquare.doAction());

		resourceSquare = new ResourceSquare(validNameMid, validPositionMid, validResourceMidNegative, true);
		assertEquals(validResourceMidNegative, resourceSquare.doAction());

		resourceSquare = new ResourceSquare(validNameLower, validPositionUpper, validResourceMidPositive, false);
		assertEquals(validResourceMidPositive, resourceSquare.doAction());

		resourceSquare = new ResourceSquare(validNameUpper, validPositionUpper, validResourceMax, true);
		assertEquals(validResourceMax, resourceSquare.doAction());

	}

	/**
	 * Test method for {@link artemislite.ResourceSquare#displayAll()}.
	 * 
	 * As this is a void method this will be checked visually.If all the getters and
	 * setters of the respective classes are tested there is very little room for
	 * error
	 */
	@Test
	void testDisplayAll() {

		resourceSquare = new ResourceSquare(validNameLower, validPositionLower, validResourceMin, false);
		resourceSquare.displayAll();

		resourceSquare = new ResourceSquare(validNameMid, validPositionMid, validResourceMidNegative, true);
		resourceSquare.displayAll();

		resourceSquare = new ResourceSquare(validNameLower, validPositionUpper, validResourceMidPositive, false);
		resourceSquare.displayAll();

		resourceSquare = new ResourceSquare(validNameUpper, validPositionUpper, validResourceMax, true);
		resourceSquare.displayAll();
	}

}
