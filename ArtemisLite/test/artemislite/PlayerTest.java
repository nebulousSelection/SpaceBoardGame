package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Player Class
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 *
 */

class PlayerTest {

	ArrayList<ElementSquare> playerOwnedSquares;
	ElementSquare purchasedSquare;
	String playerNameValid, playerNameInvalidLower, playerNameInvalidUpper;
	int resourceBalance, negativeResourceBalance, prevNumberofProperties, playerLocation, squaresToMoveBy,
			resourcesToCollect, resourceCost;

	@BeforeEach
	void setUp() throws Exception {
		playerNameValid = "playerNameValid";
		playerNameInvalidLower = " ";
		playerNameInvalidUpper = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

		playerOwnedSquares = new ArrayList<ElementSquare>();
		purchasedSquare = new ElementSquare();

		resourceCost = 1;
		playerLocation = 1;
		squaresToMoveBy = 1;
		resourceBalance = 1;
		resourcesToCollect = 1;
		prevNumberofProperties = 1;
		negativeResourceBalance = -1;
	}

	@Test
	final void testConstructorNoArgs() {
		Player player = new Player();
		assertNotNull(player);
	}

	@Test
	final void testConstructorValidArgs() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		assertNotNull(player);
		assertEquals(playerNameValid, player.getPlayerName());
		assertEquals(resourceBalance, player.getResourceBalance());
		assertEquals(playerLocation, player.getPlayerLocation());
		assertEquals(playerOwnedSquares, player.getPlayerOwnedSquares());
	}

	@Test
	final void testConstructorInvalidArgs() {
		assertThrows(IllegalArgumentException.class, () -> {
			Player player = new Player(playerNameInvalidLower, resourceBalance, playerLocation, playerOwnedSquares);
		});
	}

	@Test
	final void testValidPlayerName() {
		Player player = new Player();
		player.setPlayerName(playerNameValid);
		assertEquals(playerNameValid, player.getPlayerName());
	}

	@Test
	final void testInvalidPlayerNameLower() {
		Player player = new Player();
		assertThrows(IllegalArgumentException.class, () -> {
			player.setPlayerName(playerNameInvalidLower);
		});
	}

	@Test
	final void testInvalidPlayerNameUpper() {
		Player player = new Player();
		assertThrows(IllegalArgumentException.class, () -> {
			player.setPlayerName(playerNameInvalidUpper);
		});
	}

	@Test
	final void testResourceBalance() {
		Player player = new Player();
		player.setResourceBalance(resourceBalance);
		assertEquals(resourceBalance, player.getResourceBalance());
	}

	@Test
	final void testPrevResourceBalance() {
		Player player = new Player();
		player.setPrevResourceBalance(resourceBalance);
		assertEquals(resourceBalance, player.getPrevResourceBalance());
	}

	@Test
	final void testPrevNumberOfProperties() {
		Player player = new Player();
		player.setPrevNumberofProperties(prevNumberofProperties);
		assertEquals(prevNumberofProperties, player.getPrevNumberofProperties());
	}

	@Test
	final void testPlayerLocation() {
		Player player = new Player();
		player.setPlayerLocation(playerLocation);
		assertEquals(playerLocation, player.getPlayerLocation());
	}

	@Test
	final void testPlayerOwnedSquares() {
		Player player = new Player();
		player.setPlayerOwnedSquares(playerOwnedSquares);
		assertEquals(playerOwnedSquares, player.getPlayerOwnedSquares());
	}

	@Test
	final void testAddPlayerOwnedSquare() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		player.addPlayerOwnedSquare(purchasedSquare);
		assertEquals(purchasedSquare, playerOwnedSquares.get(playerOwnedSquares.size() - 1));
	}

	@Test
	final void testMovePosition() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		player.movePosition(squaresToMoveBy);
		assertEquals(playerLocation + squaresToMoveBy, player.getPlayerLocation());
	}

	@Test
	final void testCollectResources() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		player.collectResources(resourcesToCollect);
		assertEquals(resourceBalance + resourcesToCollect, player.getResourceBalance());
	}

	@Test
	final void testPurchaseSquare() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		player.playerSnapShot();
		player.purchaseSquare(resourceCost);
		assertEquals((player.getPrevResourceBalance() - resourceCost), player.getResourceBalance());
	}

	@Test
	final void testPayRent() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		player.playerSnapShot();
		player.payRent(resourceCost);
		assertEquals((player.getPrevResourceBalance() - resourceCost), player.getResourceBalance());
	}

	@Test
	final void testDecreaseResources() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		player.playerSnapShot();
		player.decreaseResources(resourceCost);
		assertEquals((player.getPrevResourceBalance() - resourceCost), player.getResourceBalance());
	}

	@Test
	final void testIsBankrupt() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		assertFalse(player.isBankrupt());

		player.setResourceBalance(negativeResourceBalance);
		assertTrue(player.isBankrupt());
	}

	@Test
	final void testPurchaseElementSquare() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);

		player.playerSnapShot();

		player.purchaseElementSquare(resourceCost, purchasedSquare);

		assertEquals(purchasedSquare, playerOwnedSquares.get(playerOwnedSquares.size() - 1));
		assertEquals((player.getPrevResourceBalance() - resourceCost), player.getResourceBalance());
	}

	@Test
	final void testGetDevelopablePropertiesNothingToDevelop() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);
		assertTrue(player.getDevelopableProperties().size() == 0);
	}

	@Test
	final void testGetDevelopablePropertiesSquaresToDevelop() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);

		ElementSquare E1 = new ElementSquare("Launch Abort System", 1, ESystem.SYSTEM_ONE, true);
		ElementSquare E2 = new ElementSquare("Heat Shield Resistant", 2, ESystem.SYSTEM_ONE, true);

		E1.setDevelopable(true);
		E2.setDevelopable(true);

		player.addPlayerOwnedSquare(E1);
		player.addPlayerOwnedSquare(E2);

		assertTrue(player.getDevelopableProperties().size() == 2);
	}

	@Test
	final void testPlayerSnapshot() {
		Player player = new Player(playerNameValid, resourceBalance, playerLocation, playerOwnedSquares);

		player.playerSnapShot();

		player.collectResources(resourcesToCollect);
		player.addPlayerOwnedSquare(purchasedSquare);

		assertEquals(resourceBalance, player.getPrevResourceBalance());
		assertEquals(0, player.getPrevNumberofProperties());

		assertNotEquals(resourceBalance, player.getResourceBalance());
		assertNotEquals(0, player.getPlayerOwnedSquares().size());
	}

}
