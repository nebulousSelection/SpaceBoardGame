package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test for Game Class
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 *
 */

class GameTest {

	static final int MAX_BOARD_SQUARES = 12, MAX_OWNABLE_SQUARES = 10, PASS_GO_RESOURCES = 300, FIRST_PLAYER_INDEX = 0, SECOND_PLAYER_INDEX = 1;
	
	ArrayList<Square> board;
	ArrayList<ElementSquare> ownableSquare;
	ArrayList<Player> players;
	Dice dice;
	boolean gameStart;
	boolean gameQuit;
	boolean gameWon;
	boolean gameLost;
	
	int expectedResourceBalance, expectedResourceBalance1, expectedResourceBalance2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		players = new ArrayList<Player>();
		players = ArtemisLite.createPlayerObjects(players, 2);
		
		gameStart = true;
		gameQuit = true;
		gameWon = true;
		gameLost = true;
		
		expectedResourceBalance = 8800;
		expectedResourceBalance1 = 8485;
		expectedResourceBalance2 = 8415;
		
	}

	@Test
	final void testConstructorNoArgs() {
		Game game = new Game();
		assertNotNull(game);
	}

	@Test
	final void testConstructorWithArgs() {
		Game game = new Game(players, gameStart);
		assertNotNull(game);
		assertEquals(players, game.getPlayers());
		assertEquals(gameStart, game.isGameStart());
		assertEquals(MAX_BOARD_SQUARES, game.getBoard().size());
		assertEquals(MAX_OWNABLE_SQUARES, game.getOwnableSquare().size());
		assertNotNull(game.getDice());
	}

	@Test
	final void testGetBoard() {
		Game game = new Game(players, gameStart);
		assertEquals(MAX_BOARD_SQUARES, game.getBoard().size());
	}

	@Test
	final void testGetOwnableSquare() {
		Game game = new Game(players, gameStart);
		assertEquals(MAX_OWNABLE_SQUARES, game.getOwnableSquare().size());
	}

	@Test
	final void testGetPlayers() {
		Game game = new Game();
		game.setPlayers(players);
		assertEquals(players, game.getPlayers());
	}

	@Test
	final void testGetDice() {
		Game game = new Game(players, gameStart);
		assertNotNull(game.getDice());
	}

	@Test
	final void testIsGameStart() {
		Game game = new Game();
		assertFalse(game.isGameStart());
		game.setGameStart(gameStart);
		assertTrue(game.isGameStart());
	}

	@Test
	final void testIsGameQuit() {
		Game game = new Game();
		assertFalse(game.isGameQuit());
		game.setGameQuit(gameQuit);
		assertTrue(game.isGameQuit());
	}

	@Test
	final void testIsGameLost() {
		Game game = new Game();
		assertFalse(game.isGameLost());
		game.setGameLost(gameLost);
		assertTrue(game.isGameLost());
	}

	@Test
	final void testNextPlayer() {
		Game game = new Game(players, gameStart);
		Player currentPlayer = game.getPlayers().get(FIRST_PLAYER_INDEX);
		
		assertEquals(game.getPlayers().get(FIRST_PLAYER_INDEX), currentPlayer);
		assertNotEquals(game.getPlayers().get(SECOND_PLAYER_INDEX), currentPlayer);
		
		currentPlayer = game.nextPlayer(currentPlayer);
		
		assertNotEquals(game.getPlayers().get(FIRST_PLAYER_INDEX), currentPlayer);
		assertEquals(game.getPlayers().get(SECOND_PLAYER_INDEX), currentPlayer);
	}
	
	@Test
	final void testNextPlayerOverflow() {
		Game game = new Game(players, gameStart);
		Player currentPlayer = game.getPlayers().get(SECOND_PLAYER_INDEX);
		
		assertNotEquals(game.getPlayers().get(FIRST_PLAYER_INDEX), currentPlayer);
		assertEquals(game.getPlayers().get(SECOND_PLAYER_INDEX), currentPlayer);
		
		currentPlayer = game.nextPlayer(currentPlayer);
		
		assertEquals(game.getPlayers().get(FIRST_PLAYER_INDEX), currentPlayer);
		assertNotEquals(game.getPlayers().get(SECOND_PLAYER_INDEX), currentPlayer);
		

	}

	@Test
	final void testPayRent() {
		
		Game game = new Game(players, gameStart);
		
		game.buyElementSquare(players.get(SECOND_PLAYER_INDEX), (ElementSquare) game.getBoard().get(1));
		
		game.payRent(players.get(FIRST_PLAYER_INDEX), (ElementSquare) game.getBoard().get(1));

		assertEquals(expectedResourceBalance1, players.get(FIRST_PLAYER_INDEX).getResourceBalance());
		assertEquals(expectedResourceBalance2, players.get(SECOND_PLAYER_INDEX).getResourceBalance());
	}

	@Test
	final void testCheckDevelopmentUgradable() {
		Game game = new Game(players, gameStart);
		game.buyElementSquare(players.get(FIRST_PLAYER_INDEX), (ElementSquare) game.getBoard().get(1));
		assertFalse(game.checkDevelopmentUgradable(players.get(FIRST_PLAYER_INDEX), (ElementSquare) game.getBoard().get(1)));
		game.buyElementSquare(players.get(FIRST_PLAYER_INDEX), (ElementSquare) game.getBoard().get(2));
		assertTrue(game.checkDevelopmentUgradable(players.get(FIRST_PLAYER_INDEX), (ElementSquare) game.getBoard().get(1)));
	}

	@Test
	final void testUtilityAction() {
		Game game = new Game(players, gameStart);
		game.utilityAction((IUtility) game.getBoard().get(0), players.get(FIRST_PLAYER_INDEX));
		int actualResourceBalance = players.get(FIRST_PLAYER_INDEX).getResourceBalance();
		assertEquals(expectedResourceBalance, actualResourceBalance);
	}

}
