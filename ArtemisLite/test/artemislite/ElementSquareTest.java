/**
 * 
 */
package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for ElementSquareClass
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 *
 */
class ElementSquareTest {

	// objects
	ElementSquare elementSquareOne;
	ElementSquare elementSquareTwo;
	ElementSquare elementSquareThree;
	ElementSquare elementSquareFour;
	Player player;
	ByteArrayInputStream inputStream;
	ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	Scanner scanner;

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

	// cost to buy

	int baseCost, elementSquareOneCost, elementSquareTwoCost, elementSquareThreeCost, elementSquareFourCost;
	int invalidCost;
	String invalidPurchaseCostExceptionMessage;
	// rent

	int baseRent, elementSquareOneRent, elementSquareTwoRent, elementSquareThreeRent, elementSquareFourRent;
	String invalidRentExceptionMessage;
	int invalidRent;

	// rent with developments
	int elementSquareOneRentTwoDev, elementSquareOneRentFourDev;
	int elementSquareThreeRentTwoDev, elementSquareThreeRentFourDev;

	// development level

	int validDevLevelLower, validDevLevelMid, validDevLevelUpper;
	int invalidDevLevelLower, invalidDevLevelUpper;

	String invalidDevLevelLowerExceptionMessage, invalidDevLevelUpperExceptionMessage;

	// base development cost

	int baseDevelopmentCost, elementSquareOneDevCost, elementSquareTwoDevCost, elementSquareThreeDevCost,
			elementSquareFourDevCost;
	int invalidDevelopmentCost;

	String invalidDevelopmentExceptionMessage;

	// development upgrade cost

	int noUpgrade, elementSquareTwoTwoDevsUpgradeCost, elementSquareTwoFourDevsUpgradeCost,
			elementSquareFourTwoDevsUpgradeCost, elementSquareFourFourDevsUpgradeCost;

	// system
	String invalidSystemExceptionMessage;

	// userInputs
	String validStringInput, invalidStringInput;
	String validIntInputLower, validIntInputMid, validIntInputUpper;
	int userDevelopmentAmountLower, userDevelopmentAmountMid, userDevelopmentAmountUpper;

	String invalidIntInputLower, invalidIntInputUpper;

	// short development level messages
	String noDevelopmentMessage, twoDevelopmentsMessage, fourDevelopmentsMessage;

	// toSting
	String toStringMessage;

	// landedOn
	String LandedOnMessage;

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

		// Player Name
		playerName = "Lorem ipsum do";

		// system
		invalidSystemExceptionMessage = "No system assigned to ElementSquare";

		// cost to buy
		baseCost = 50;

		elementSquareOneCost = baseCost;
		elementSquareTwoCost = 312;
		elementSquareThreeCost = 630;
		elementSquareFourCost = 350;
		invalidCost = -1;
		invalidPurchaseCostExceptionMessage = "Cost to buy can't be less than zero";

		// rent
		baseRent = 5;

		elementSquareOneRent = baseRent;
		elementSquareTwoRent = 68;
		elementSquareThreeRent = 184;
		elementSquareFourRent = 110;

		invalidRent = -1;
		invalidRentExceptionMessage = "Rent Price can't be less than zero";

		// rent with developments

		elementSquareOneRentTwoDev = 7;
		elementSquareOneRentFourDev = 12;
		elementSquareThreeRentTwoDev = 264;
		elementSquareThreeRentFourDev = 476;

		// DevelopmentCost
		baseDevelopmentCost = 80;

		elementSquareOneDevCost = baseDevelopmentCost;
		elementSquareTwoDevCost = 150;
		elementSquareThreeDevCost = 238;
		elementSquareFourDevCost = 210;

		// developmentLevel

		validDevLevelLower = 0;
		validDevLevelMid = 2;
		validDevLevelUpper = 4;

		invalidDevLevelLower = -1;
		invalidDevLevelUpper = 5;

		invalidDevLevelLowerExceptionMessage = "Exceeded maximum development level";
		invalidDevLevelUpperExceptionMessage = "Below minimum development level";

		// development costs
		noUpgrade = 0;
		elementSquareTwoTwoDevsUpgradeCost = 300;
		elementSquareTwoFourDevsUpgradeCost = 675;
		elementSquareFourTwoDevsUpgradeCost = 420;
		elementSquareFourFourDevsUpgradeCost = 945;

		invalidDevelopmentExceptionMessage = "Development Level Cost can't be less than zero";

		// short development level messages

		noDevelopmentMessage = "Not developed";
		twoDevelopmentsMessage = "Minor development level 2 : Re-purposed Lunar Rover Shelter";
		fourDevelopmentsMessage = "Major development : Earth View Luxury Apartment";

		// userInputs
		validStringInput = "q";

		validIntInputLower = "0\nq";
		validIntInputMid = "2";
		validIntInputUpper = "4";

		invalidStringInput = "\nt\nq";

		invalidIntInputLower = "0\nq";
		invalidIntInputUpper = "5\nq";

		// userDevelopmentAmountReturnValues

		userDevelopmentAmountLower = 0;
		userDevelopmentAmountMid = 2;
		userDevelopmentAmountUpper = 4;

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#ElementSquare()}.
	 */
	@Test
	void testElementSquare() {
		elementSquareOne = new ElementSquare();
		assertNotNull(elementSquareOne);
	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#ElementSquare(java.lang.String, int, artemislite.ESystem, boolean)}.
	 */
	@Test
	void testElementSquareStringIntESystemBooleanValid() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);

		assertEquals(validNameLower, elementSquareOne.getSquareName());
		assertEquals(validPositionLower, elementSquareOne.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_ONE, elementSquareOne.getSystem());
		assertEquals(true, elementSquareOne.isPurchasable());
		assertEquals(elementSquareOneCost, elementSquareOne.getCostToBuy());
		assertEquals(elementSquareOneRent, elementSquareOne.getRentPrice());
		assertEquals(validDevLevelLower, elementSquareOne.getDevelopmentLevel());
		assertEquals(elementSquareOneDevCost, elementSquareOne.getDevelopmentUpgradeCost());
		assertNull(elementSquareOne.getOwner());
		assertEquals(false, elementSquareOne.isDevelopable());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);

		assertEquals(validNameMid, elementSquareTwo.getSquareName());
		assertEquals(validPositionMid, elementSquareTwo.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_TWO, elementSquareTwo.getSystem());
		assertEquals(false, elementSquareTwo.isPurchasable());
		assertEquals(elementSquareTwoCost, elementSquareTwo.getCostToBuy());
		assertEquals(elementSquareTwoRent, elementSquareTwo.getRentPrice());
		assertEquals(validDevLevelLower, elementSquareTwo.getDevelopmentLevel());
		assertEquals(elementSquareTwoDevCost, elementSquareTwo.getDevelopmentUpgradeCost());
		assertNull(elementSquareTwo.getOwner());
		assertEquals(false, elementSquareTwo.isDevelopable());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		assertEquals(validNameUpper, elementSquareThree.getSquareName());
		assertEquals(validPositionUpper, elementSquareThree.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_THREE, elementSquareThree.getSystem());
		assertEquals(true, elementSquareThree.isPurchasable());
		assertEquals(elementSquareThreeCost, elementSquareThree.getCostToBuy());
		assertEquals(elementSquareThreeRent, elementSquareThree.getRentPrice());
		assertEquals(validDevLevelLower, elementSquareThree.getDevelopmentLevel());
		assertEquals(elementSquareThreeDevCost, elementSquareThree.getDevelopmentUpgradeCost());
		assertNull(elementSquareThree.getOwner());
		assertEquals(false, elementSquareThree.isDevelopable());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		assertEquals(validNameLower, elementSquareFour.getSquareName());
		assertEquals(validPositionMid, elementSquareFour.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_FOUR, elementSquareFour.getSystem());
		assertEquals(false, elementSquareFour.isPurchasable());
		assertEquals(elementSquareFourCost, elementSquareFour.getCostToBuy());
		assertEquals(elementSquareFourRent, elementSquareFour.getRentPrice());
		assertEquals(validDevLevelLower, elementSquareFour.getDevelopmentLevel());
		assertEquals(elementSquareFourDevCost, elementSquareFour.getDevelopmentUpgradeCost());
		assertNull(elementSquareFour.getOwner());
		assertEquals(false, elementSquareFour.isDevelopable());
	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#ElementSquare(java.lang.String, int, artemislite.ESystem, boolean)}.
	 */
	@Test
	void testElementSquareStringIntESystemBooleanInvalid() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			elementSquareOne = new ElementSquare(invalidNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			elementSquareOne = new ElementSquare(invalidNameUpper, validPositionLower, ESystem.SYSTEM_ONE, true);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {
			elementSquareOne = new ElementSquare(invalidNameNull, validPositionLower, ESystem.SYSTEM_ONE, true);
		});
		assertEquals(nullSquareNameExceptionMessage, invalidNameNullException.getMessage());

		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {
			elementSquareOne = new ElementSquare(validNameLower, invalidPositionLower, ESystem.SYSTEM_ONE, true);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {
			elementSquareOne = new ElementSquare(validNameLower, invalidPositionUpper, ESystem.SYSTEM_ONE, true);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionUpperException.getMessage());

		Exception invalidSystemException = assertThrows(NullPointerException.class, () -> {
			elementSquareOne = new ElementSquare(validNameLower, validPositionUpper, null, true);
		});
		assertEquals(invalidSystemExceptionMessage, invalidSystemException.getMessage());

	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#ElementSquare(java.lang.String, int, boolean, int, int, int, int, artemislite.Player, artemislite.ESystem, boolean)}.
	 * 
	 *
	 */
	@Test
	void testElementSquareStringIntBooleanIntIntIntIntPlayerESystemBooleanValid() {

		player = new Player();

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
				elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);

		assertEquals(validNameLower, elementSquareOne.getSquareName());
		assertEquals(validPositionLower, elementSquareOne.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_ONE, elementSquareOne.getSystem());
		assertEquals(true, elementSquareOne.isPurchasable());
		assertEquals(elementSquareOneCost, elementSquareOne.getCostToBuy());
		assertEquals(elementSquareOneRent, elementSquareOne.getRentPrice());
		assertEquals(validDevLevelLower, elementSquareOne.getDevelopmentLevel());
		assertEquals(elementSquareOneDevCost, elementSquareOne.getDevelopmentUpgradeCost());
		assertNull(elementSquareOne.getOwner());
		assertEquals(false, elementSquareOne.isDevelopable());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, false, elementSquareTwoCost,
				elementSquareTwoRent, validDevLevelMid, elementSquareTwoDevCost, player, ESystem.SYSTEM_TWO, true);

		assertEquals(validNameMid, elementSquareTwo.getSquareName());
		assertEquals(validPositionMid, elementSquareTwo.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_TWO, elementSquareTwo.getSystem());
		assertEquals(false, elementSquareTwo.isPurchasable());
		assertEquals(elementSquareTwoCost, elementSquareTwo.getCostToBuy());
		assertEquals(elementSquareTwoRent, elementSquareTwo.getRentPrice());
		assertEquals(validDevLevelMid, elementSquareTwo.getDevelopmentLevel());
		assertEquals(elementSquareTwoDevCost, elementSquareTwo.getDevelopmentUpgradeCost());
		assertNotNull(elementSquareTwo.getOwner());
		assertEquals(true, elementSquareTwo.isDevelopable());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, true, elementSquareThreeCost,
				elementSquareThreeRent, validDevLevelUpper, elementSquareThreeDevCost, null, ESystem.SYSTEM_THREE,
				false);

		assertEquals(validNameUpper, elementSquareThree.getSquareName());
		assertEquals(validPositionUpper, elementSquareThree.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_THREE, elementSquareThree.getSystem());
		assertEquals(true, elementSquareThree.isPurchasable());
		assertEquals(elementSquareThreeCost, elementSquareThree.getCostToBuy());
		assertEquals(elementSquareThreeRent, elementSquareThree.getRentPrice());
		assertEquals(validDevLevelUpper, elementSquareThree.getDevelopmentLevel());
		assertEquals(elementSquareThreeDevCost, elementSquareThree.getDevelopmentUpgradeCost());
		assertNull(elementSquareThree.getOwner());
		assertEquals(false, elementSquareThree.isDevelopable());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, false, elementSquareFourCost,
				elementSquareFourRent, validDevLevelLower, elementSquareFourDevCost, player, ESystem.SYSTEM_FOUR,
				false);

		assertEquals(validNameLower, elementSquareFour.getSquareName());
		assertEquals(validPositionMid, elementSquareFour.getPositionOnBoard());
		assertEquals(ESystem.SYSTEM_FOUR, elementSquareFour.getSystem());
		assertEquals(false, elementSquareFour.isPurchasable());
		assertEquals(elementSquareFourCost, elementSquareFour.getCostToBuy());
		assertEquals(elementSquareFourRent, elementSquareFour.getRentPrice());
		assertEquals(validDevLevelLower, elementSquareFour.getDevelopmentLevel());
		assertEquals(elementSquareFourDevCost, elementSquareFour.getDevelopmentUpgradeCost());
		assertNotNull(elementSquareTwo.getOwner());
		assertEquals(false, elementSquareFour.isDevelopable());
	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#ElementSquare(java.lang.String, int, boolean, int, int, int, int, artemislite.Player, artemislite.ESystem, boolean)}.
	 * 
	 *
	 */
	@Test
	void testElementSquareStringIntBooleanIntIntIntIntPlayerESystemBooleanInvalid() {

		Exception invalidNameLowerException = assertThrows(IllegalArgumentException.class, () -> {
			elementSquareOne = new ElementSquare(invalidNameLower, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameLowerException.getMessage());

		Exception invalidNameUpperException = assertThrows(IllegalArgumentException.class, () -> {
			elementSquareOne = new ElementSquare(invalidNameUpper, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);
		});
		assertEquals(invalidSquareNameExceptionMessage, invalidNameUpperException.getMessage());

		Exception invalidNameNullException = assertThrows(NullPointerException.class, () -> {

			elementSquareOne = new ElementSquare(invalidNameNull, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);
		});
		assertEquals(nullSquareNameExceptionMessage, invalidNameNullException.getMessage());

		Exception invalidPositionLowerException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, invalidPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionLowerException.getMessage());

		Exception invalidPositionUpperException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, invalidPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);
		});

		assertEquals(invalidPositionOnBoardExceptionMessage, invalidPositionUpperException.getMessage());

		Exception invalidSystemException = assertThrows(NullPointerException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, null, false);
		});
		assertEquals(invalidSystemExceptionMessage, invalidSystemException.getMessage());

		Exception invalidCostException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, invalidCost,
					elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, null, false);
		});
		assertEquals(invalidPurchaseCostExceptionMessage, invalidCostException.getMessage());

		Exception invalidRentException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
					invalidRent, validDevLevelLower, elementSquareOneDevCost, null, null, false);
		});
		assertEquals(invalidRentExceptionMessage, invalidRentException.getMessage());

		Exception invalidDevelopmentCostException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, validDevLevelLower, invalidDevelopmentCost, null, null, false);
		});
		assertEquals(invalidDevelopmentExceptionMessage, invalidDevelopmentCostException.getMessage());

		Exception invalidDevLevelLowerException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, invalidDevLevelLower, elementSquareOneDevCost, null, null, false);
		});
		assertEquals(invalidDevLevelLowerExceptionMessage, invalidDevLevelLowerException.getMessage());

		Exception invalidDevLevelUpperException = assertThrows(IllegalArgumentException.class, () -> {

			elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
					elementSquareOneRent, invalidDevLevelUpper, elementSquareOneDevCost, null, null, false);
		});
		assertEquals(invalidDevLevelUpperExceptionMessage, invalidDevLevelUpperException.getMessage());

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#getCostToBuy()}.
	 */
	@Test
	void testGetCostToBuyValid() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);

		assertEquals(elementSquareOneCost, elementSquareOne.getCostToBuy());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);

		assertEquals(elementSquareTwoCost, elementSquareTwo.getCostToBuy());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		assertEquals(elementSquareThreeCost, elementSquareThree.getCostToBuy());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		assertEquals(elementSquareFourCost, elementSquareFour.getCostToBuy());

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#getRentPrice()}.
	 */
	@Test
	void testGetRentPrice() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);

		assertEquals(elementSquareOneRent, elementSquareOne.getRentPrice());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);

		assertEquals(elementSquareTwoRent, elementSquareTwo.getRentPrice());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		assertEquals(elementSquareThreeRent, elementSquareThree.getRentPrice());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		assertEquals(elementSquareFourRent, elementSquareFour.getRentPrice());
	}

	/**
	 * Test method for {@link artemislite.ElementSquare#getDevelopmentLevel()}.
	 */
	@Test
	void testGetDevelopmentLevel() {

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
				elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);

		assertEquals(validDevLevelLower, elementSquareOne.getDevelopmentLevel());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, false, elementSquareTwoCost,
				elementSquareTwoRent, validDevLevelMid, elementSquareTwoDevCost, player, ESystem.SYSTEM_TWO, true);

		assertEquals(validDevLevelMid, elementSquareTwo.getDevelopmentLevel());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, true, elementSquareThreeCost,
				elementSquareThreeRent, validDevLevelUpper, elementSquareThreeDevCost, null, ESystem.SYSTEM_THREE,
				false);
		assertEquals(validDevLevelUpper, elementSquareThree.getDevelopmentLevel());

	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#getDevelopmentUpgradeCost()}.
	 */
	@Test
	void testGetDevelopmentUpgradeCost() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);

		assertEquals(elementSquareOneDevCost, elementSquareOne.getDevelopmentUpgradeCost());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);

		assertEquals(elementSquareTwoDevCost, elementSquareTwo.getDevelopmentUpgradeCost());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		assertEquals(elementSquareThreeDevCost, elementSquareThree.getDevelopmentUpgradeCost());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		assertEquals(elementSquareFourDevCost, elementSquareFour.getDevelopmentUpgradeCost());
	}

	/**
	 * Test method for {@link artemislite.ElementSquare#getOwner()}.
	 */
	@Test
	void testGetSetOwner() {
		player = new Player();

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		assertNull(elementSquareOne.getOwner());

		elementSquareOne.setOwner(player);
		assertEquals(player, elementSquareOne.getOwner());
	}

	/**
	 * Test method for {@link artemislite.ElementSquare#getSystem()}.
	 */
	@Test
	void testGetSetSystemValid() {
		elementSquareOne = new ElementSquare();
		elementSquareOne.setSystem(ESystem.SYSTEM_ONE);
		assertEquals(ESystem.SYSTEM_ONE, elementSquareOne.getSystem());

		elementSquareOne.setSystem(ESystem.SYSTEM_TWO);
		assertEquals(ESystem.SYSTEM_TWO, elementSquareOne.getSystem());

		elementSquareOne.setSystem(ESystem.SYSTEM_THREE);
		assertEquals(ESystem.SYSTEM_THREE, elementSquareOne.getSystem());

		elementSquareOne.setSystem(ESystem.SYSTEM_FOUR);
		assertEquals(ESystem.SYSTEM_FOUR, elementSquareOne.getSystem());

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#isDevelopable()}.
	 */
	@Test
	void testIsDevelopable() {
		elementSquareOne = new ElementSquare();

		elementSquareOne.setDevelopable(false);
		assertFalse(elementSquareOne.isDevelopable());

		elementSquareOne.setDevelopable(true);
		assertTrue(elementSquareOne.isDevelopable());
	}

	/**
	 * Test method for {@link artemislite.ElementSquare#addDevelopment(int)}.
	 */
	@Test
	void testAddDevelopment() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		elementSquareOne.addDevelopment(validDevLevelLower);
		assertEquals(validDevLevelLower, elementSquareOne.getDevelopmentLevel());
		assertEquals(elementSquareOneRent, elementSquareOne.getRentPrice());

		// add two developments
		elementSquareOne.addDevelopment(validDevLevelMid);
		assertEquals(validDevLevelMid, elementSquareOne.getDevelopmentLevel());
		assertEquals(elementSquareOneRentTwoDev, elementSquareOne.getRentPrice());

		// add two more developments
		elementSquareOne.addDevelopment(validDevLevelMid);
		assertEquals(validDevLevelUpper, elementSquareOne.getDevelopmentLevel());
		assertEquals(elementSquareOneRentFourDev, elementSquareOne.getRentPrice());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);
		// add two developments
		elementSquareThree.addDevelopment(validDevLevelMid);
		assertEquals(validDevLevelMid, elementSquareThree.getDevelopmentLevel());
		assertEquals(elementSquareThreeRentTwoDev, elementSquareThree.getRentPrice());

		// add two more developments
		elementSquareThree.addDevelopment(validDevLevelMid);
		assertEquals(validDevLevelUpper, elementSquareThree.getDevelopmentLevel());
		assertEquals(elementSquareThreeRentFourDev, elementSquareThree.getRentPrice());

	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#newDevelopmentUpgradeCost(int)}.
	 * 
	 * 
	 */
	@Test
	void testNewDevelopmentUpgradeCost() {

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);
		assertEquals(noUpgrade, elementSquareTwo.newDevelopmentUpgradeCost(validDevLevelLower));
		assertEquals(elementSquareTwoTwoDevsUpgradeCost, elementSquareTwo.newDevelopmentUpgradeCost(validDevLevelMid));
		assertEquals(elementSquareTwoFourDevsUpgradeCost,
				elementSquareTwo.newDevelopmentUpgradeCost(validDevLevelUpper));

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);
		assertEquals(noUpgrade, elementSquareTwo.newDevelopmentUpgradeCost(validDevLevelLower));
		assertEquals(elementSquareFourTwoDevsUpgradeCost,
				elementSquareFour.newDevelopmentUpgradeCost(validDevLevelMid));
		assertEquals(elementSquareFourFourDevsUpgradeCost,
				elementSquareFour.newDevelopmentUpgradeCost(validDevLevelUpper));
	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#userDevelopmentAmount(java.util.Scanner)}.
	 * 
	 * 
	 */

	@Test
	void testUserDevelopmentAmountValid() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);

		try {
			System.setIn(new ByteArrayInputStream(validStringInput.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountLower, elementSquareOne.userDevelopmentAmount(scanner));

			System.setIn(new ByteArrayInputStream(validIntInputLower.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountLower, elementSquareOne.userDevelopmentAmount(scanner));

			System.setIn(new ByteArrayInputStream(validIntInputMid.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountMid, elementSquareOne.userDevelopmentAmount(scanner));

			System.setIn(new ByteArrayInputStream(validIntInputUpper.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountUpper, elementSquareOne.userDevelopmentAmount(scanner));

			elementSquareOne.addDevelopment(userDevelopmentAmountMid);

			System.setIn(new ByteArrayInputStream(validIntInputMid.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountMid, elementSquareOne.userDevelopmentAmount(scanner));

			elementSquareOne.addDevelopment(userDevelopmentAmountMid);

		} finally {
			System.setIn(System.in);
			scanner.close();
		}

	}

	@Test
	void testUserDevelopmentAmountinvalid() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);

		try {
			System.setIn(new ByteArrayInputStream(invalidStringInput.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountLower, elementSquareOne.userDevelopmentAmount(scanner));

			System.setIn(new ByteArrayInputStream(invalidIntInputLower.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountLower, elementSquareOne.userDevelopmentAmount(scanner));

			System.setIn(new ByteArrayInputStream(invalidIntInputUpper.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountLower, elementSquareOne.userDevelopmentAmount(scanner));

			elementSquareOne.addDevelopment(validDevLevelUpper);

			System.setIn(new ByteArrayInputStream(invalidIntInputUpper.getBytes()));
			scanner = new Scanner(System.in);
			assertEquals(userDevelopmentAmountLower, elementSquareOne.userDevelopmentAmount(scanner));

		} finally {
			System.setIn(System.in);
			scanner.close();
		}

	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#displayDevelopmentLevel(int)}.
	 */
	@Test
	void testDisplayDevelopmentLevel() {
		elementSquareOne = new ElementSquare();
		elementSquareOne.displayDevelopmentLevel(validDevLevelLower);
		elementSquareOne.displayDevelopmentLevel(validDevLevelMid);
		elementSquareOne.displayDevelopmentLevel(validDevLevelUpper);

	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#displayShortDevelopmentLevel(int)}.
	 */
	@Test
	void testDisplayShortDevelopmentLevel() {
		elementSquareFour = new ElementSquare();

		assertEquals(noDevelopmentMessage, elementSquareFour.displayShortDevelopmentLevel(validDevLevelLower));
		assertEquals(twoDevelopmentsMessage, elementSquareFour.displayShortDevelopmentLevel(validDevLevelMid));
		assertEquals(fourDevelopmentsMessage, elementSquareFour.displayShortDevelopmentLevel(validDevLevelUpper));
	}

	/**
	 * Test method for {@link artemislite.ElementSquare#fullyDeveloped()}.
	 */
	@Test
	void testFullyDeveloped() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		elementSquareOne.addDevelopment(validDevLevelLower);
		assertFalse(elementSquareOne.fullyDeveloped());

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		elementSquareOne.addDevelopment(validDevLevelMid);
		assertFalse(elementSquareOne.fullyDeveloped());

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		elementSquareOne.addDevelopment(validDevLevelUpper);
		assertTrue(elementSquareOne.fullyDeveloped());
	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#purchaseElementSquare(artemislite.Player)}.
	 */
	@Test
	void testPurchaseElementSquare() {
		int costToBuy;
		player = new Player();

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		costToBuy = elementSquareOne.purchaseElementSquare(player);
		assertEquals(costToBuy, elementSquareOne.getCostToBuy());
		assertEquals(player, elementSquareOne.getOwner());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);
		costToBuy = elementSquareTwo.purchaseElementSquare(player);
		assertEquals(costToBuy, elementSquareTwo.getCostToBuy());
		assertEquals(player, elementSquareTwo.getOwner());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		costToBuy = elementSquareThree.purchaseElementSquare(player);
		assertEquals(costToBuy, elementSquareThree.getCostToBuy());
		assertEquals(player, elementSquareOne.getOwner());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		costToBuy = elementSquareFour.purchaseElementSquare(player);
		assertEquals(costToBuy, elementSquareFour.getCostToBuy());
		assertEquals(player, elementSquareFour.getOwner());

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#calculateRent()}.
	 */
	@Test
	void testCalculateRent() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		elementSquareOne.addDevelopment(validDevLevelLower);
		assertEquals(elementSquareOneRent, elementSquareOne.getRentPrice());

		// add two developments
		elementSquareOne.addDevelopment(validDevLevelMid);
		assertEquals(elementSquareOneRentTwoDev, elementSquareOne.getRentPrice());

		// add two more developments
		elementSquareOne.addDevelopment(validDevLevelMid);
		assertEquals(elementSquareOneRentFourDev, elementSquareOne.getRentPrice());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);
		// add two developments
		elementSquareThree.addDevelopment(validDevLevelMid);
		assertEquals(elementSquareThreeRentTwoDev, elementSquareThree.getRentPrice());

		// add two more developments
		elementSquareThree.addDevelopment(validDevLevelMid);
		assertEquals(validDevLevelUpper, elementSquareThree.getDevelopmentLevel());
		assertEquals(elementSquareThreeRentFourDev, elementSquareThree.getRentPrice());
	}

	/**
	 * Test method for {@link artemislite.ElementSquare#calculateCostToBuy()}.
	 */
	@Test
	void testCalculateCostToBuy() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		assertEquals(elementSquareOneCost, elementSquareOne.calculateCostToBuy());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);

		assertEquals(elementSquareTwoCost, elementSquareTwo.calculateCostToBuy());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		assertEquals(elementSquareThreeCost, elementSquareThree.calculateCostToBuy());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		assertEquals(elementSquareFourCost, elementSquareFour.calculateCostToBuy());
	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#calculateDevelopmentUpgradeCost()}.
	 */
	@Test
	void testCalculateDevelopmentUpgradeCost() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		assertEquals(elementSquareOneDevCost, elementSquareOne.calculateDevelopmentUpgradeCost());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);

		assertEquals(elementSquareTwoDevCost, elementSquareTwo.calculateDevelopmentUpgradeCost());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);

		assertEquals(elementSquareThreeDevCost, elementSquareThree.calculateDevelopmentUpgradeCost());

		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		assertEquals(elementSquareFourDevCost, elementSquareFour.calculateDevelopmentUpgradeCost());

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#displayAll()}.
	 * 
	 * revisit
	 */
	@Test
	void testDisplayAll() {
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, ESystem.SYSTEM_ONE, true);
		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, ESystem.SYSTEM_TWO, false);
		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, ESystem.SYSTEM_THREE, true);
		elementSquareFour = new ElementSquare(validNameLower, validPositionMid, ESystem.SYSTEM_FOUR, false);

		outputStreamCaptor = new ByteArrayOutputStream();

		try {

			System.setOut(new PrintStream(outputStreamCaptor));
			elementSquareOne.displayAll();

			if (outputStreamCaptor.toString().contains(validNameLower)
					&& outputStreamCaptor.toString().contains(ESystem.SYSTEM_ONE.getSystemName())) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

			outputStreamCaptor.flush();

			elementSquareTwo.displayAll();

			if (outputStreamCaptor.toString().contains(validNameMid)
					&& outputStreamCaptor.toString().contains(ESystem.SYSTEM_TWO.getSystemName())) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

			elementSquareThree.displayAll();

			if (outputStreamCaptor.toString().contains(validNameUpper)
					&& outputStreamCaptor.toString().contains(ESystem.SYSTEM_THREE.getSystemName())) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

			elementSquareFour.displayAll();

			if (outputStreamCaptor.toString().contains(validNameLower)
					&& outputStreamCaptor.toString().contains(ESystem.SYSTEM_FOUR.getSystemName())) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

		} catch (IOException e) {
			System.out.println("Error flushing output capture");
			e.printStackTrace();
		} finally {
			System.setOut(System.out);
		}

	}

	/**
	 * Test method for {@link artemislite.ElementSquare#toString()}.
	 */
	@Test
	void testToString() {
		player = new Player();

		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
				elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, null, ESystem.SYSTEM_ONE, false);

		toStringMessage = "ElementSquare [getSquareName()=" + validNameLower + ", getPositionOnBoard()="
				+ validPositionLower + ", isPurchasable()=" + true + "costToBuy=" + elementSquareOneCost
				+ ", rentPrice=" + elementSquareOneRent + ", developmentLevel=" + validDevLevelLower
				+ ", developmentUpgradeCost=" + elementSquareOneDevCost + ", owner=" + null + ", system="
				+ ESystem.SYSTEM_ONE + ", isDevelopable=" + false + "]";

		assertEquals(toStringMessage, elementSquareOne.toString());

		elementSquareTwo = new ElementSquare(validNameMid, validPositionMid, false, elementSquareTwoCost,
				elementSquareTwoRent, validDevLevelMid, elementSquareTwoDevCost, player, ESystem.SYSTEM_TWO, true);

		toStringMessage = "ElementSquare [getSquareName()=" + validNameMid + ", getPositionOnBoard()="
				+ validPositionMid + ", isPurchasable()=" + false + "costToBuy=" + elementSquareTwoCost + ", rentPrice="
				+ elementSquareTwoRent + ", developmentLevel=" + validDevLevelMid + ", developmentUpgradeCost="
				+ elementSquareTwoDevCost + ", owner=" + player + ", system=" + ESystem.SYSTEM_TWO + ", isDevelopable="
				+ true + "]";

		assertEquals(toStringMessage, elementSquareTwo.toString());

		elementSquareThree = new ElementSquare(validNameUpper, validPositionUpper, true, elementSquareThreeCost,
				elementSquareThreeRent, validDevLevelUpper, elementSquareThreeDevCost, null, ESystem.SYSTEM_THREE,
				false);

		toStringMessage = "ElementSquare [getSquareName()=" + validNameUpper + ", getPositionOnBoard()="
				+ validPositionUpper + ", isPurchasable()=" + true + "costToBuy=" + elementSquareThreeCost
				+ ", rentPrice=" + elementSquareThreeRent + ", developmentLevel=" + validDevLevelUpper
				+ ", developmentUpgradeCost=" + elementSquareThreeDevCost + ", owner=" + null + ", system="
				+ ESystem.SYSTEM_THREE + ", isDevelopable=" + false + "]";

		assertEquals(toStringMessage, elementSquareThree.toString());

	}

	/**
	 * Test method for
	 * {@link artemislite.ElementSquare#landedOn(artemislite.Player)}.
	 */
	@Test
	void testLandedOn() {
		player = new Player();
		player.setPlayerName(playerName);
		elementSquareOne = new ElementSquare(validNameLower, validPositionLower, true, elementSquareOneCost,
				elementSquareOneRent, validDevLevelLower, elementSquareOneDevCost, player, ESystem.SYSTEM_ONE, false);

		LandedOnMessage = playerName + " has landed on " + validNameLower;

		assertEquals(LandedOnMessage, elementSquareOne.landedOn(player));

	}

}
