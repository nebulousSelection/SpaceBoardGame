package artemislite;




import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for artemis lite class
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */

class ArtemisLiteTest {

	ByteArrayInputStream inputStream;
	ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	ArrayList<Player> players = new ArrayList<Player>();
	int playerCount;
	int startingLocation;
	int startingResourceBalance;
	int expectedUserNumberReturn;
	int expectedPlayerCountOutput;
	boolean expectedResult, actualResult; 
	String playerCountInputValid;
	String userNameInputStreamUnique, expectedPlayerNameOne, expectedPlayerNameTwo, repeatingPlayerName;
	String playerNameLengthValid, playerNameLengthInvalidLower, playerNameLengthInvalidUpper;
	String userResponseYes, userResponseNo;
	String userNumberInputValid;

	@BeforeEach
	void setUp() throws Exception {

		System.setOut(new PrintStream(outputStreamCaptor));

		expectedResult = true;

		playerCount = 2;
		startingLocation = 0;
		startingResourceBalance = 8500;

		userNameInputStreamUnique = String.format("Name 1%sName 2", System.lineSeparator());
		expectedPlayerNameOne = "Name 1";
		expectedPlayerNameTwo = "Name 2";
		repeatingPlayerName = "Name";

		playerNameLengthValid = "validPlayerName";
		playerNameLengthInvalidLower = " ";
		playerNameLengthInvalidUpper = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

		userResponseYes = "y";
		userResponseNo = "n";

		playerCountInputValid = "2\n";
		expectedPlayerCountOutput = 2;

		userNumberInputValid = "1";
		expectedUserNumberReturn = 1;
	}

	/**
	 * This test is not particularly useful, but uses an interesting method 
	 * to capture the output stream. 
	 */
	@Test
	final void testDisplayMenu() {
		String expectedOutput  = String.format("Game Menu%s1. Start Game%s2. Display Game Rules%s3. Quit%s\nEnter a number and select return:", 
				System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
		ArtemisLite.displayMenu();
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	final void testCheckQuitYes() {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(userResponseYes.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		assertTrue(ArtemisLite.checkQuit(scanner));
	}
	
	@Test
	final void testCheckQuitNo() {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(userResponseNo.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		assertFalse(ArtemisLite.checkQuit(scanner));
	}

	@Test
	final void testPlayerCountInput() {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(playerCountInputValid.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		int actual = ArtemisLite.playerCountInput(scanner);

		assertEquals(expectedPlayerCountOutput, actual);
	}

	@Test
	final void testCreatePlayerObjects() {
		ArtemisLite.createPlayerObjects(players, playerCount);
		assertEquals(playerCount, players.size());
		for (int loop = 0; loop < playerCount; loop++) {
			assertNull(players.get(loop).getPlayerName());
			assertEquals(0, players.get(loop).getPlayerOwnedSquares().size());
			assertEquals(startingLocation, players.get(loop).getPlayerLocation());
			assertEquals(startingResourceBalance, players.get(loop).getResourceBalance());
		}
	}

	@Test
	final void testNamePlayerObjects() {
		ArtemisLite.createPlayerObjects(players, playerCount);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(userNameInputStreamUnique.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		ArtemisLite.namePlayerObjects(scanner, players, playerCount);

		assertEquals(expectedPlayerNameOne, players.get(0).getPlayerName().trim());
		assertEquals(expectedPlayerNameTwo, players.get(1).getPlayerName().trim());
	}

	@Test
	final void testCheckPlayerNameUnique() {
		ArtemisLite.createPlayerObjects(players, playerCount);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(userNameInputStreamUnique.getBytes());
		System.setIn(inputStream);

		players.get(0).setPlayerName(repeatingPlayerName);

		assertFalse(ArtemisLite.checkPlayerNameUnique(players, repeatingPlayerName));
	}

	@Test
	final void testCheckPlayerNameLengthValid() {
		actualResult = ArtemisLite.checkPlayerNameLength(playerNameLengthValid);
		assertTrue(actualResult);
	}

	@Test
	final void testCheckPlayerNameLengthInvalidLower() {
		actualResult = ArtemisLite.checkPlayerNameLength(playerNameLengthInvalidLower);
		assertFalse(actualResult);
	}

	@Test
	final void testCheckPlayerNameLengthInvalidUpper() {
		actualResult = ArtemisLite.checkPlayerNameLength(playerNameLengthInvalidUpper);
		assertFalse(actualResult);
	}

	@Test
	final void testUserYesResponse() {

		inputStream = new ByteArrayInputStream(userResponseYes.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		assertTrue(ArtemisLite.userYesNoResponse(scanner));
	}

	@Test
	final void testUserNoResponse() {

		inputStream = new ByteArrayInputStream(userResponseNo.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		assertFalse(ArtemisLite.userYesNoResponse(scanner));
	}

	@Test
	final void testUserNumberResponseValid() {

		inputStream = new ByteArrayInputStream(userNumberInputValid.getBytes());
		System.setIn(inputStream);

		Scanner scanner = new Scanner(System.in);

		int actual = ArtemisLite.userNumberResponse(scanner);

		assertEquals(expectedUserNumberReturn, actual);
	}
}
