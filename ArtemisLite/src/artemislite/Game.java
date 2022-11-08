/**
 * 
 */
package artemislite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains all information regarding the game, It processes whether
 * the game has been ended as well as creating the board , keeping track of non
 * purchased ElementSquares
 *
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 *
 */
public class Game implements IDisplay {

	protected static final int MAX_BOARD_SQUARES = 12, MAX_OWNABLE_SQUARES = 10, PASS_GO_RESOURCES = 300;
	private static final int FIRST_PLAYER_INDEX = 0, SHORT_DELAY = 250;

	private ArrayList<Square> board;
	private ArrayList<ElementSquare> ownableSquare;
	private ArrayList<Player> players;
	private Dice dice;
	private boolean gameStart;
	private boolean gameQuit;
	private boolean gameWon;
	private boolean gameLost;

	public Game() {

	}

	/**
	 * Smaller constructor to allow for auto-population of parts of the the game
	 * like the dice, the board and the ownable squares(ElementSquares)
	 * 
	 * Players and gamestart passed in via the main, other boolean types default to
	 * false
	 * 
	 * @param board         array list of abstract squares
	 * @param ownableSquare list of purchasable squares that are subtracted as the
	 *                      player
	 * @param players
	 */
	public Game(ArrayList<Player> players, boolean gameStart) {
		this.players = players;
		this.gameStart = gameStart;
		setBoard();
		setOwnableSquare();
		setDice();

	}

	/**
	 * Constructor with args useful should the someone update the code to set all
	 * fields more manually
	 * 
	 * 
	 * @param board
	 * @param ownableSquare
	 * @param players
	 * @param dice
	 * @param gameStart
	 * @param gameQuit
	 * @param gameWon
	 */
	public Game(ArrayList<Square> board, ArrayList<ElementSquare> ownableSquare, ArrayList<Player> players, Dice dice,
			boolean gameStart, boolean gameQuit, boolean gameWon, boolean gameLost) {
		super();
		this.board = board;
		this.ownableSquare = ownableSquare;
		this.players = players;
		this.dice = dice;
		this.gameStart = gameStart;
		this.gameQuit = gameQuit;
		this.gameWon = gameWon;
		this.gameLost = gameLost;
	}

	/**
	 * 
	 * @return
	 */

	public ArrayList<Square> getBoard() {
		return board;
	}

	/**
	 * Uses private methods to setup the board via the setter
	 * 
	 * @param board the board to set
	 */
	private void setBoard() {
		this.board = populateBoard(board);
	}

	public ArrayList<ElementSquare> getOwnableSquare() {
		return ownableSquare;
	}

	private void setOwnableSquare() {
		this.ownableSquare = addOwnableSquares();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/**
	 * @return the dice
	 */
	public Dice getDice() {
		return dice;
	}

	/**
	 * @param dice the dice to set
	 */
	private void setDice() {
		this.dice = createDice();
	}

	/**
	 * @return the gameStart
	 */
	public boolean isGameStart() {
		return gameStart;
	}

	/**
	 * @param gameStart the gameStart to set
	 */
	public void setGameStart(boolean gameStart) {
		this.gameStart = gameStart;
	}

	/**
	 * @return the gameQuit
	 */
	public boolean isGameQuit() {
		return gameQuit;
	}

	/**
	 * @param gameQuit the gameQuit to set
	 */
	public void setGameQuit(boolean gameQuit) {
		this.gameQuit = gameQuit;
	}

	/**
	 * @return the gameWon
	 */
	public boolean isGameWon() {
		return gameWon;
	}

	/**
	 * @param gameWon the gameWon to set
	 */
	private void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public boolean isGameLost() {
		return gameLost;
	}

	public void setGameLost(boolean gameLost) {
		this.gameLost = gameLost;
	}

	/**
	 * This method creates the square objects and then passes them into other
	 * methods to complete their entries on the board
	 * 
	 * @param board
	 * @return
	 */

	private Square boardSetup(int squareToAdd) {
		switch (squareToAdd) {
		case 0:
			Square U1 = new ResourceSquare("Base Camp", 0, PASS_GO_RESOURCES, false);
			return U1;
		case 1:
			Square E1 = new ElementSquare("Launch Abort System", 1, ESystem.SYSTEM_ONE, true);
			return E1;
		case 2:
			Square E2 = new ElementSquare("Heat Shield Resistant", 2, ESystem.SYSTEM_ONE, true);
			return E2;
		case 3:
			Square E3 = new ElementSquare("Propulsion Cooling System", 3, ESystem.SYSTEM_TWO, true);
			return E3;
		case 4:
			Square E4 = new ElementSquare("Launch Control Centre", 4, ESystem.SYSTEM_TWO, true);
			return E4;
		case 5:
			Square E5 = new ElementSquare("Fuel Tanks", 5, ESystem.SYSTEM_TWO, true);
			return E5;
		case 6:
			Square U2 = new NothingSquare("Phone Home", 6, false);
			return U2;
		case 7:
			Square E6 = new ElementSquare("H.L.C.S", 7, ESystem.SYSTEM_THREE, true);
			return E6;
		case 8:
			Square E7 = new ElementSquare("H.A.L.O", 8, ESystem.SYSTEM_THREE, true);
			return E7;
		case 9:
			Square E8 = new ElementSquare("E.S.P.R.I.T", 9, ESystem.SYSTEM_THREE, true);
			return E8;
		case 10:
			Square E9 = new ElementSquare("Raptor Engines", 10, ESystem.SYSTEM_FOUR, true);
			return E9;
		case 11:
			Square E10 = new ElementSquare("Reusable Boosters", 11, ESystem.SYSTEM_FOUR, true);
			return E10;
		default:
			System.out.println("Board overflow!");
			return null;
		}
	}

	/**
	 * Adds squares to an ArrayList of type Square
	 * 
	 * @param board
	 * @return
	 */

	private ArrayList<Square> populateBoard(ArrayList<Square> board) {
		board = new ArrayList<Square>();

		for (int index = 0; index < MAX_BOARD_SQUARES; index++) {
			if ((!boardSetup(index).equals(null))) {
				board.add(boardSetup(index));
			}

		}
		return board;

	}

	/**
	 * Adds ownable squares
	 * 
	 * @param ownableSquares
	 * @return
	 */

	private ArrayList<ElementSquare> addOwnableSquares() {
		ArrayList<Square> squares = this.board;
		ArrayList<ElementSquare> ownableSquares = new ArrayList<ElementSquare>();

		for (Square s : squares) {
			if (s.isPurchasable()) {
				ElementSquare ownableSquare = (ElementSquare) s;
				ownableSquares.add(ownableSquare);
			}
		}

		return ownableSquares;

	}

	private Dice createDice() {
		Dice dice = new Dice();
		return dice;
	}

	/**
	 * Displays the game rules
	 */

	public void displayGameRules() {
		File gameRules = new File("GameRules.txt");
		String line;

		try {
			FileReader fileReader = new FileReader(gameRules);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			line = bufferedReader.readLine();

			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
			fileReader.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
		} catch (IOException e) {
			System.err.println("Problem with reading the file!");
		} catch (Exception e) {
			System.err.println("Something went wrong...");
		}

	}

	/**
	 * Introduction to the game brief description of the aim of the game
	 * 
	 * 
	 * @param startPlayer used to get where all players start and their resources at
	 *                    the beginning of the game as all players start the game
	 *                    with these.
	 * 
	 */

	public void gameIntro(Player startPlayer) {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------");
		System.out.println("CAPCOM");
		System.out.println("------");
		System.out.println("Incoming message:\n");
		System.out.println("\t'Welcome to Artemis Lite " + printPlayerNames() + ".");
		System.out.println(
				"\tYour mission is to travel to, acquire and develop all elements of the Artemis Lite Project.\n");
		System.out.println(
				"\tYou are about to land at : " + this.board.get(startPlayer.getPlayerLocation()).getSquareName());
		System.out.println(
				"\tNASA have given you all : " + startPlayer.getPrevResourceBalance() + " resources to start with\n");
		System.out.println(
				"\tThis should be enough to complete your mission. It's grown quite quiet here in Mission Control. ");
		System.out
				.println("\tA few moments ago the flight director requested that everyone sit down and get prepared ");
		System.out.println("\tfor the events that are coming. He closed with a remark of good luck to all of you.'\n");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------");

		System.out.println(printPlayerNames() + " have landed ");
		System.out.println(startPlayer.getPlayerName() + " will begin");

	}

	/**
	 * utility method to print all the player names with some formatting
	 * 
	 * @return
	 */

	public String printPlayerNames() {
		int addComma, addAnd;
		addComma = 2;
		addAnd = 1;
		String playerNames;
		playerNames = "";
		ArrayList<Player> players = this.players;

		for (int index = 0; index < players.size(); index++) {
			playerNames += players.get(index).getPlayerName();
			if (index < players.size() - addComma) {
				playerNames += ", ";
			} else if (index < players.size() - addAnd) {
				playerNames += " and ";
			}

		}

		return playerNames;
	}

	/**
	 * controls turn management returns the player who will be going next when
	 * called
	 * 
	 * @param players
	 * @param currentPlayer
	 * @return
	 */

	public Player nextPlayer(Player currentPlayer) {
		ArrayList<Player> players = this.players;
		Player newPlayer;
		int playerIndex;
		playerIndex = players.indexOf(currentPlayer);
		if (playerIndex == players.size() - 1) {
			newPlayer = players.get(FIRST_PLAYER_INDEX);
		} else {
			newPlayer = players.get(playerIndex + 1);
		}
		return newPlayer;
	}

	/**
	 * Moves the player around the board sets the position within player based on
	 * the amount rolled by the dice
	 * 
	 * @param playerLocation index of the players location relative to the board
	 * @return where the players current position on the board is as type Square
	 */

	public Square playerNewSquare(int playerLocation) {
		List<Square> board = this.board;
		Square currentSquare = board.get(playerLocation);

		return currentSquare;

	}

	/**
	 * Offers players to buy the square that has been landed on starting with the
	 * player whose turn it is
	 * 
	 * @param currentPlayer
	 * @param scanner
	 * @return the person who has bought the square or null which can then be
	 *         processed
	 */

	public Player offerToBuy(Player currentPlayer, Scanner scanner) {
		ArrayList<Player> players = this.players;
		Player playerToAsk = currentPlayer;
		Player purchaser = null;
		int index = 0;
		do {
			if (playerToAsk.offerToPurchase(scanner) != null) {
				purchaser = playerToAsk;
			} else {
				++index;
				playerToAsk = this.nextPlayer(playerToAsk);
			}

		} while (index < players.size() && purchaser == null);

		return purchaser;

	}

	/**
	 * Prints a message regarding who has bought the property
	 * 
	 * 
	 * 
	 * @param player
	 * @param elementSquareForPurchase
	 */

	public void buyElementSquare(Player player, ElementSquare elementSquareForPurchase) {
		String playerName;
		int squareCost;
		playerName = player.getPlayerName();
		squareCost = elementSquareForPurchase.purchaseElementSquare(player);
		player.purchaseElementSquare(squareCost, elementSquareForPurchase);

		System.out.println(playerName + " has just bought : " + elementSquareForPurchase.getSquareName());
		System.out.println(squareCost + " has been removed from their balance");

		// removes from ownable list and adds to players list
		removeOwnableSquare(elementSquareForPurchase);

	}

	/**
	 * removes square from the list of ownable squares
	 * 
	 * @param squareToRemove
	 */

	private void removeOwnableSquare(ElementSquare squareToRemove) {
		this.ownableSquare.remove(squareToRemove);
	}

	/**
	 * Manages the process of a landing player paying rent and the owner receiving
	 * rent.
	 * 
	 * @param currentPlayer        player paying rent
	 * @param currentElementSquare used to get the rent price and the current owner
	 */
	public void payRent(Player currentPlayer, ElementSquare currentElementSquare) {

		int rentAmount;
		Player owner = currentElementSquare.getOwner();
		rentAmount = currentElementSquare.getRentPrice();
		currentPlayer.payRent(rentAmount);
		owner.collectResources(rentAmount);

	}

	/**
	 * 
	 * checks system is upgradeable based on the player and the square they have
	 * bought
	 * 
	 * @param purchaser
	 * @param purchasedElementSquare
	 * @return
	 */

	public boolean checkDevelopmentUgradable(Player purchaser, ElementSquare purchasedElementSquare) {
		ESystem checkSystem = purchasedElementSquare.getSystem();
		int playerOwnedInSystem, remainingOwnableInSystem, totalInSystem;

		playerOwnedInSystem = this.checkOwnedSystemSquares(purchaser, checkSystem);
		remainingOwnableInSystem = this.checkOwnableSystemSquares(checkSystem);

		// total amount in system
		totalInSystem = checkSystem.getElementSquaresInSystem();

		if (remainingOwnableInSystem > 0) {
			return false;
		} else if (playerOwnedInSystem == totalInSystem) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks the array of ownable systems for properties that match the system
	 * 
	 * @param system
	 * @return
	 */

	private int checkOwnableSystemSquares(ESystem system) {
		int amountRemainingInSystem;
		amountRemainingInSystem = 0;
		ArrayList<ElementSquare> ownable = this.ownableSquare;

		for (ElementSquare square : ownable) {
			if (square.getSystem() == system) {
				amountRemainingInSystem++;
			}
		}

		if (amountRemainingInSystem == 0) {

			System.out.println("All Element Squares in " + system.getSystemName() + " have now been purchased.");
		} else {
			System.out.println("There are " + amountRemainingInSystem + " Element Squares remaining in "
					+ system.getSystemName() + "\n");
		}

		return amountRemainingInSystem;

	}

	/**
	 * checks the owned system squares for a player
	 * 
	 * 
	 * @param purchaser
	 * @param system
	 * @return
	 */

	private int checkOwnedSystemSquares(Player purchaser, ESystem system) {
		ArrayList<ElementSquare> owned = purchaser.getPlayerOwnedSquares();
		int amountOwnedInSystem;
		amountOwnedInSystem = 0;

		for (ElementSquare square : owned) {
			if (square.getSystem() == system) {
				amountOwnedInSystem++;
			}
		}

		return amountOwnedInSystem;

	}

	/**
	 * sets all in squares in system to upgradeable
	 * 
	 * @param purchased
	 */

	public void setSystemUpgradable(ElementSquare purchased, Player purchaser) {
		ESystem upgradableSystem;
		boolean setUpgradable;
		upgradableSystem = purchased.getSystem();
		setUpgradable = true;
		ArrayList<ElementSquare> ownable = purchaser.getPlayerOwnedSquares();
		System.out.println(upgradableSystem.getSystemName() + " is now able to be developed!");
		for (ElementSquare square : ownable) {
			if (square.getSystem().equals(upgradableSystem)) {
				square.setDevelopable(setUpgradable);

			}

		}
	}

	/**
	 * displays a development menu
	 */

	public void displayDevelopmentMenu() {
		System.out.println("-------------Development Menu-------------------");
		System.out.println("1. View Developable Elements");
		System.out.println("2. Select Element to develop");
		System.out.println("3. Exit development");
		System.out.println("------------------------------------------------");
	}

	/**
	 * walks the current player through a series of step to develop an element
	 * 
	 * can display the players owned squares allow them to select an
	 * 
	 * 
	 * @param currentPlayer player developing
	 * @param devlopable    an array of developable properties
	 * @param scanner
	 */

	public void developSquare(Player currentPlayer, ArrayList<ElementSquare> devlopable, Scanner scanner) {
		ElementSquare toDevelop;

		int userInput, upgradeAmount, developmentCost;
		boolean quitDeveloping;

		quitDeveloping = false;
		upgradeAmount = 0;

		do {
			try {
				this.displayDevelopmentMenu();
				userInput = scanner.nextInt();
				switch (userInput) {
				case 1:
					System.out.println("Now Displaying elements that can be developed");
					displayElementSquares(devlopable);
					break;
				case 2:
					toDevelop = currentPlayer.selectDevelopmentOption(scanner);
					scanner.nextLine();
					toDevelop.displayDevelopmentLevel(toDevelop.getDevelopmentLevel());
					upgradeAmount = toDevelop.userDevelopmentAmount(scanner);
					if (upgradeAmount != 0) {
						developmentCost = toDevelop.newDevelopmentUpgradeCost(upgradeAmount);
						if (currentPlayer.checkDevelopmentWanted(scanner, developmentCost) == true) {
							toDevelop.addDevelopment(upgradeAmount);
							System.out.println(currentPlayer.getPlayerName() + " has just developed : "
									+ toDevelop.getSquareName());
							toDevelop.displayDevelopmentLevel(toDevelop.getDevelopmentLevel());
							currentPlayer.decreaseResources(developmentCost);
						} else {
							System.out.println("Development Cancelled");
						}
					} else {
						System.out.println("Nothing developed");
					}
					break;
				case 3:
					quitDeveloping = true;
					System.out.println("Now quitting development");
					break;

				default:
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter your choice in digits");
			}

		} while (quitDeveloping == false);
	}

	public void displayElementSquares(List<ElementSquare> elementSquares) {
		if (elementSquares.isEmpty()) {
			System.out.println("None to display");
		} else {

			for (int index = 0; index < elementSquares.size(); index++) {
				System.out.println("-----------------------[" + (index + 1) + "]-----------------------");
				elementSquares.get(index).displayAll();
				System.out.println("--------------------------------------------------");

			}
		}

	}

	/**
	 * Handles actions for utility squares like NothingSqaure and ResourcesSquare if
	 * the resources are not zero they are added to the players balance
	 * 
	 * @param utility
	 * @param currentPlayer
	 */

	public void utilityAction(IUtility utility, Player currentPlayer) {
		int resource;

		resource = utility.doAction();
		if (resource != 0) {
			currentPlayer.collectResources(resource);
		}
	}

	/**
	 * If a player quits the game it tells the users who has quit and then displays
	 * the end of game analysis
	 * 
	 * @param quittingPlayer
	 */

	public void quitGame(Player quittingPlayer) {
		System.out.println(quittingPlayer.getPlayerName() + " has quit the game");
		System.out.println("Now displaying game statistics : ");
		playerRankingTable();
	}

	/**
	 * If a player is bankrupt the game it tells the users who has quit and then
	 * displays the end of game analysis
	 * 
	 * @param bankruptPlayer
	 */

	public void lostGame(Player bankruptPlayer) {
		System.out.println(bankruptPlayer.getPlayerName() + " has ran out of resources, your mission has ended");
		System.out.println("Now displaying game statistics : ");
		playerRankingTable();

	}

	/**
	 * checks for winning conditions using private methods
	 */

	public void checkWin() {
		if (this.ownableSquare.isEmpty() && this.checkAllSquaresDeveloped() == true) {
			this.setGameWon(true);

		}

	}

	/**
	 * checks if all the squares owned by players have been fully developed.
	 * 
	 * @return if the game has ended true otherwise false
	 */

	private boolean checkAllSquaresDeveloped() {
		int amountFullyDeveloped;
		boolean fullyDeveloped;
		amountFullyDeveloped = 0;

		List<Player> players = this.players;
		for (Player p : players) {
			List<ElementSquare> playerSquares = p.getPlayerOwnedSquares();
			for (ElementSquare e : playerSquares) {

				if (e.fullyDeveloped() == true) {
					amountFullyDeveloped++;
				}
			}
		}

		if (MAX_OWNABLE_SQUARES == amountFullyDeveloped) {
			fullyDeveloped = true;
		} else {
			fullyDeveloped = false;
		}

		return fullyDeveloped;

	}

	/**
	 * method to display the win game message when all properties are developed
	 * 
	 * @param players
	 */
	public void winGame() {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int previousYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		int previousPreviousYear = Calendar.getInstance().get(Calendar.YEAR) - 2;

		System.out.println();
		System.out.println("ALL SYSTEMS ARE GO!!!");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.println("The project is on the path to ultimate success!");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.println();
		System.out.println(
				"It started as a dream in " + previousPreviousYear + " when the first mission was attempted...");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.println("In " + previousYear + " another attempt was made however failed...");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.println("Until finally a successful landing is achieved in " + currentYear);
		ArtemisLite.delay(SHORT_DELAY);
		System.out.println("Congrulations team - Artemis has successfully launched");
		ArtemisLite.delay(SHORT_DELAY);
		System.out.println("Here is how you made that possible...");
		ArtemisLite.delay(SHORT_DELAY);

		playerRankingTable();
	}

	private void rankPlayers() {
		List<Player> players = this.players;
		Collections.sort(players, new RankPlayers());

	}

	private void displayPlayerRankings() {
		List<Player> players = this.players;
		for (int index = 0; index < players.size(); index++) {
			System.out.format("%-10d|", (index + 1));
			players.get(index).displayAllFinalRanking();
		}
	}

	private void playerRankingTable() {
		// rank the players based on the number of properties owned
		rankPlayers();
		// display player ranking table
		System.out.println();
		System.out.format("%-10s|", "Ranking");
		System.out.format("  %-25s", "Organisation");

		if (isGameWon()) {
			System.out.format("|  %-23s", "Properties Developed");
		} else {
			System.out.format("|  %-23s", "Properties Purchased");
		}

		System.out.format("|  %s", "Resources");
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		displayPlayerRankings();
		System.out.println();

		System.out.println();
		System.out.println("Developers:");
		System.out.println("Adam Sloan");
		System.out.println("Charmagne Casimero");
		System.out.println("Jonathan Foster");
		System.out.println("Anna Graham");

	}

	/**
	 * this method overrides the ISquare method to display all
	 */

	@Override
	public void displayAll() {
		List<Square> board = this.getBoard();
		for (Square squares : board) {
			System.out.println(squares.toString());
		}
	}

}
