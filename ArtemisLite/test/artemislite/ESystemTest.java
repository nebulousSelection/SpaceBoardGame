/**
 * 
 */
package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for ESystems
 * 
 * As this is an eNum class the testing is less extensive, the values are
 * constants so just checking the values can be retrieved for each system will
 * suffice.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
class ESystemTest {

	ESystem systemOne;
	ESystem systemTwo;
	ESystem systemThree;
	ESystem systemFour;

	String systemOneName, systemTwoName, systemThreeName, systemFourName;
	double systemOneMultiplier, systemTwoMultiplier, systemThreeMultiplier, systemFourMultiplier;
	int systemOneSquares, systemTwoSquares, systemThreeSquares, systemFourSquares;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		systemOne = ESystem.SYSTEM_ONE;
		systemTwo = ESystem.SYSTEM_TWO;
		systemThree = ESystem.SYSTEM_THREE;
		systemFour = ESystem.SYSTEM_FOUR;

		systemOneName = "Orion";
		systemTwoName = "Space Launch System";
		systemThreeName = "Lunar Gateway";
		systemFourName = "Human Landing System";

		systemOneMultiplier = 1.0;
		systemTwoMultiplier = 1.25;
		systemThreeMultiplier = 1.6;
		systemFourMultiplier = 2.0;

		systemOneSquares = 2;
		systemTwoSquares = 3;
		systemThreeSquares = 3;
		systemFourSquares = 2;

	}

	/**
	 * Test method for
	 * {@link artemislite.ESystem#ESystem(java.lang.String, java.lang.String, double, int)}.
	 */
	@Test
	void testESystem() {
		assertNotNull(ESystem.values());
	}

	/**
	 * Test method for {@link artemislite.ESystem#getSystemName()}.
	 */
	@Test
	void testGetSystemName() {
		assertEquals(systemOneName, systemOne.getSystemName());
		assertEquals(systemTwoName, systemTwo.getSystemName());
		assertEquals(systemThreeName, systemThree.getSystemName());
		assertEquals(systemFourName, systemFour.getSystemName());

	}

	/**
	 * Test method for {@link artemislite.ESystem#getSystemMultiplier()}.
	 */
	@Test
	void testGetSystemMultiplier() {
		assertEquals(systemOneMultiplier, systemOne.getSystemMultiplier());
		assertEquals(systemTwoMultiplier, systemTwo.getSystemMultiplier());
		assertEquals(systemThreeMultiplier, systemThree.getSystemMultiplier());
		assertEquals(systemFourMultiplier, systemFour.getSystemMultiplier());
	}

	/**
	 * Test method for {@link artemislite.ESystem#getElementSquaresInSystem()}.
	 */
	@Test
	void testGetElementSquaresInSystem() {
		assertEquals(systemOneSquares, systemOne.getElementSquaresInSystem());
		assertEquals(systemTwoSquares, systemTwo.getElementSquaresInSystem());
		assertEquals(systemThreeSquares, systemThree.getElementSquaresInSystem());
		assertEquals(systemFourSquares, systemFour.getElementSquaresInSystem());
	}

}
