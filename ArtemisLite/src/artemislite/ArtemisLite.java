package artemislite;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains the main method and is the starting point for the
 * application.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
public class ArtemisLite {

	public static void main(String[] args) {

		// declare variables
		boolean startGame;
		int userNum;

		// instantiate objects
		Scanner scanner = new Scanner(System.in);
		Game game = new Game();

		// initialise variable
		startGame = false;

		// displays an interactive start menu for the game, and loops until exited by
		// user input
		do {
			startGame = menuOptions(scanner, game);
			game.setGameStart(startGame);

			if (game.isGameStart() == false) {
				scanner.nextLine();
				if (checkQuit(scanner) == true) {
					game.setGameQuit(true);
					System.out.println();
					quitGame();
				}
			}
			// checks if "Start Game" or "Quit Game" has been selected
		} while (game.isGameStart() == false && game.isGameQuit() == false);

		if (game.isGameStart() == true) {

			// creates an ArrayList to hold Player objects, and calls playerSetUp() method
			// to populate ArrayList
			ArrayList<Player> players = new ArrayList<Player>();
			players = playerSetUp(scanner);

			// creates an instance of the Game class
			game = new Game(players, startGame);

			// creates an instance of the Dice class, and sets default values
			Dice dice = game.getDice();
			dice.setDiceRolled(false);

			// identifies the first player in the play sequence and starts the game on their
			// turn
			Player currentPlayer = players.get(0);
			game.gameIntro(currentPlayer);

			// game loop begins
			while (game.isGameStart() == true && game.isGameQuit() == false && game.isGameWon() == false
					&& currentPlayer.isBankrupt() == false) {

				// print to console who's turn it is
				System.out.println();
				System.out.println(currentPlayer.getPlayerName() + "'s turn");

				// print game menu
				DisplayTurnOptions();

				// takes user input and allows user to choose from options on what to do on
				// their turn
				userNum = userNumberResponse(scanner);
				scanner.nextLine();

				// switch statement performs the actions described in the game menu
				switch (userNum) {
				case 1:
					if (dice.isDiceRolled() == false) {
						dice.setDiceRolled(true);

						Square currentSquare;
						dice.rollDice();
						// moves the player's location
						currentPlayer.movePosition(dice.getLastRolled());

						// update local location variable to player's new location
						currentSquare = game.playerNewSquare(currentPlayer.getPlayerLocation());

						// prints information to console about the square the player has landed on
						currentSquare.landedOn(currentPlayer);

						// if the square landed is purchasable
						if (currentSquare.isPurchasable()) {

							// cast to element square
							ElementSquare currentElementSquare = (ElementSquare) currentSquare;

							// get owner if there is one, object is NULL if the property is not owned.
							Player owner = currentElementSquare.getOwner();

							// if property is not owned, offer an opportunity to buy

							if (owner == null) {
								Player purchaser;
								purchaser = game.offerToBuy(currentPlayer, scanner);

								if (purchaser != null) {
									game.buyElementSquare(purchaser, currentElementSquare);
									if (game.checkDevelopmentUgradable(purchaser, currentElementSquare) == true) {
										game.setSystemUpgradable(currentElementSquare, purchaser);
									}
								} else {
									System.out.println("No one has bought this square");
								}
								// if property is owned
							} else {

								// do nothing
								if (owner.equals(currentPlayer)) {
									System.out.println("Thanks for visiting!");

									// charge rent
								} else {
									System.out.println(owner.getPlayerName() + " would you like to charge "
											+ currentPlayer.getPlayerName() + " rent?");
									System.out.println("The rent for " + currentElementSquare.getSquareName() + " is : "
											+ currentElementSquare.getRentPrice());
									if (userYesNoResponse(scanner) == true) {
										game.payRent(currentPlayer, currentElementSquare);
									} else {
										System.out.println(owner.getPlayerName() + " did not charge "
												+ currentPlayer.getPlayerName() + " rent.");
									}
								}
							}
							// if the square landed is a "utility" square
						} else {
							IUtility utility = (IUtility) currentSquare;
							game.utilityAction(utility, currentPlayer);
						}
						// if player has already used this action this turn
					} else {
						System.out.println();
						System.out.println("You have already rolled on this turn!");
					}
					break;
				case 2:
					// display owned property details
					System.out.println("Selected : Display owned squares");
					if (currentPlayer.getPlayerOwnedSquares().isEmpty()) {
						System.out.println("You have no properties to display");
					} else {

					}
					game.displayElementSquares(currentPlayer.getPlayerOwnedSquares());
					break;
				case 3:
					// develop owned properties
					System.out.println("You selected : Develop properties");
					if (currentPlayer.getDevelopableProperties().isEmpty()) {
						System.out.println("You have no properties that can be developed");
					} else {
						game.developSquare(currentPlayer, currentPlayer.getDevelopableProperties(), scanner);
					}
					break;
				case 4:
					// display game rules
					game.displayGameRules();
					break;
				case 5:
					// end turn
					System.out.println("End turn");
					System.out.println();

					// cannot end turn without rolling dice
					if (dice.isDiceRolled() == false) {
						System.out.println();
						System.err.println("You must roll the dice on your turn!");
						// perform "end of turn" chores
					} else {
						System.out.println();
						System.out.println("UPDATE");
						currentPlayer.playerSummary();
						currentPlayer.playerSnapShot();
						System.out.println();
						currentPlayer = game.nextPlayer(currentPlayer);
						dice.setDiceRolled(false);
					}
					break;
				case 6:
					// quit game
					if (checkQuit(scanner) == true) {
						game.setGameQuit(true);
						game.quitGame(currentPlayer);
					} else {
						System.out.println("Returning to game");
					}
					break;
				default:
					System.out.println("Invalid number input - please enter a number in digits between 1 and 6");
				}
				
				game.checkWin();

			}

			if (currentPlayer.isBankrupt()) {
				game.lostGame(currentPlayer);
			} else if (game.isGameWon()) {
				game.winGame();
			} 

		} else {
			System.out.println("Thanks for playing!");
		}
		scanner.close();
	}

	/**
	 * Displays the Start Menu for the game. Prints a list of options to screen.
	 */
	public static void displayMenu() {
		System.out.println("\nGame Menu");
		System.out.println("1. Start Game");
		System.out.println("2. Display Game Rules");
		System.out.println("3. Quit");
		System.out.println("\nEnter a number and select return:");
	}

	/**
	 * Displays the current player's owned properties.
	 * 
	 * @param listTitle      - String containing the name for "properties" under the
	 *                       current game rules
	 * @param elementSquares - List containing the player's owned properties
	 */
	public static void displayElementSquares(String listTitle, List<ElementSquare> elementSquares) {
		if (elementSquares.isEmpty()) {
			System.out.println("You currently have no " + listTitle);
		} else {

			for (int index = 0; index < elementSquares.size(); index++) {
				System.out.println("-----------------------[" + (index + 1) + "]-----------------------");
				elementSquares.get(index).displayAll();
				System.out.println("-----------------------------------------------");
				System.out.println();
			}
		}
	}

	/**
	 * Performs the actions described in the start menu.
	 * 
	 * @param scanner
	 * @param game
	 * @return startGame - boolean indicating the user has chose to start the game
	 */
	public static boolean menuOptions(Scanner scanner, Game game) {

		// declare variables
		boolean startGame, quitGame;

		// initialise variables
		startGame = false;
		quitGame = false;

		do {
			try {

				int userOption;

				// prints start menu to console
				displayMenu();

				// fetch user input
				userOption = scanner.nextInt();

				// switch statement performs the actions described in the start menu
				switch (userOption) {
				case 1:
					System.out.println("Selected : Start Game");
					startGame = true;
					break;
				case 2:
					System.out.println("Selected : Display Game Rules");
					game.displayGameRules();
					break;
				case 3:
					System.out.println("Selected : Quit Game");
					quitGame = true;
					break;
				default:
					System.out.println("Invalid input - please enter a number between 1-3");
				}
			} catch (InputMismatchException e) {
				System.out.println("Try again - please enter a number");

				scanner.next();
			}
		} while (startGame != true && quitGame != true);

		return startGame;
	}

	/**
	 * Displays a message indicating that the user has chose to quit the game.
	 */
	public static void quitGame() {
		System.out.println("You have quit the game!");
	}

	/**
	 * Ensures the user wants to quit before doing so.
	 * 
	 * @param scanner
	 * @return quit - Boolean indicating whether the user still wants to quit
	 */
	public static boolean checkQuit(Scanner scanner) {
		boolean quit;
		System.out.println("Are you sure you would like to quit the game? (Y/N) ");
		quit = userYesNoResponse(scanner);
		return quit;
	}

	/**
	 * Dynamically creates Player objects.
	 * 
	 * @param scanner
	 * @return players - ArrayList of Player objects
	 */
	public static ArrayList<Player> playerSetUp(Scanner scanner) {

		// create ArrayList to hold output
		ArrayList<Player> players = new ArrayList<Player>();

		// declare and initialise variable by calling method which prompts user for
		// total number of players
		int playerCount = playerCountInput(scanner);

		// call method to create specified number of default Player objects
		createPlayerObjects(players, playerCount);

		// call method prompt players for their (organisation) names
		namePlayerObjects(scanner, players, playerCount);

		// return completed ArrayList of Player objects
		return players;
	}

	/**
	 * Prompts the user for the total number of participating players, and provides
	 * error handling until an input is provided which is allowed under game rules.
	 * 
	 * @param scanner
	 * @return playerCount - Total number of participating players
	 */
	public static int playerCountInput(Scanner scanner) {

		// declare variable
		int playerCount = 0;

		// declare and initialise constants
		final int playerCountLowerBound = 2;
		final int playerCountUpperBound = 4;

		// prompt use for input
		System.out.println("Please enter the total number of players in the range 2-4.");

		// input error handling
		while (true) {
			String inputString = scanner.next();

			// ensure input is an integer
			try {
				playerCount = Integer.parseInt(inputString);

				// ensure play count is within the accepted range
				if (playerCount >= playerCountLowerBound && playerCount <= playerCountUpperBound) {
					break;
				} else {
					System.err.println("There must be 2-4 players. Please try again.");
				}
			} catch (NumberFormatException exception) {
				System.err.println("Please enter a valid number");
			}
		}
		// advance scanner to next line
		scanner.nextLine();

		return playerCount;
	}

	/**
	 * Creates a loop which dynamically creates the number of Player objects
	 * previously specified by the user
	 * 
	 * @param players     - ArrayList of Player objects
	 * @param playerCount - Total number of participating players
	 * @return players - ArrayList of Player objects
	 */
	public static ArrayList<Player> createPlayerObjects(ArrayList<Player> players, int playerCount) {

		// declare and initialise constants
		final int startingLocation = 0;
		final int startingResourceBalance = 8500;

		// create player object, assign starting values, append to ArrayList, repeat
		for (int loop = 0; loop < playerCount; loop++) {
			Player player = new Player();
			player.setResourceBalance(startingResourceBalance);
			player.setPlayerLocation(startingLocation);
			player.playerSnapShot();
			players.add(player);
		}
		return players;
	}

	/**
	 * Prompts each use in turn for their (organisation) name and invokes the
	 * appropriate setter, while providing user-friendly error handling.
	 * 
	 * @param scanner
	 * @param players     - ArrayList of Player objects
	 * @param playerCount - Total number of participating players
	 * @return players - ArrayList of Player objects
	 */
	public static ArrayList<Player> namePlayerObjects(Scanner scanner, ArrayList<Player> players, int playerCount) {

		// declare variables
		String playerName;

		// loop prompts all players to input their (organisation) name
		for (int loop = 0; loop < playerCount; loop++) {
			do {
				// prompt user input
				System.out.printf("Player %d, what is the name of your organisation?\n", loop + 1);
				playerName = scanner.nextLine();
				playerName.trim();
				// continue loop until user input name is unique and of an acceptable length
			} while (!checkPlayerNameUnique(players, playerName) || !checkPlayerNameLength(playerName));

			System.out.println("Your name has been saved.");

			// indexes into ArrayList to invoke setter
			players.get(loop).setPlayerName(playerName);
		}
		return players;
	}

	/**
	 * Checks if a player's (organisation) name is unique. Prints a message to
	 * screen and returns a boolean accordingly.
	 * 
	 * @param players     - ArrayList of Player objects
	 * @param currentName - Name to be checked for uniqueness
	 * @return boolean - Indicates whether the name is unique
	 */
	public static boolean checkPlayerNameUnique(ArrayList<Player> players, String currentName) {
		for (Player p : players) {
			if (p.getPlayerName() != null) {
				if (p.getPlayerName().equalsIgnoreCase(currentName)) {
					System.err.println("Your name is identical to " + p.getPlayerName() + " please set again");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks the length of the player's (organisation) name is within the accepted
	 * range. Prints a message to screen and returns a boolean accordingly.
	 * 
	 * @param playerName - String
	 * @return boolean
	 */
	public static boolean checkPlayerNameLength(String playerName) {

		// declare and initialise constants
		final int playerNameLengthLowerBound = 3;
		final int playerNameLengthUpperBound = 20;

		// provides user friendly error messages until user enters a valid input
		if (playerName.length() < playerNameLengthLowerBound || playerName.length() > playerNameLengthUpperBound) {
			System.err.println("Your organisation name must be 3-20 characters long. Please try again.");
			return false;
		}
		return true;
	}

	/**
	 * Displays a list of options a user can choose from on their turn.
	 */
	public static void DisplayTurnOptions() {
		System.out.println();
		System.out.println("+ + + + + + + + + + + + + + + + + + + + + ");
		System.out.println("1. Roll Dice and move");
		System.out.println("2. Show properties");
		System.out.println("3. Develop Elements");
		System.out.println("4. Display Game Rules");
		System.out.println("5. End Turn");
		System.out.println("6. Quit");
		System.out.println("Please select an option and press enter");
		System.out.println("+ + + + + + + + + + + + + + + + + + + + + ");
		System.out.println();
	}

	/**
	 * Pauses program until user hits ENTER key.
	 * 
	 * @param scanner
	 */
	public static void promptEnterKey(Scanner scanner) {
		System.out.println("Press \"ENTER\" to continue...");
		scanner.nextLine();
	}

	/**
	 * Prompts the user to make a choice by inputting y/n.
	 * 
	 * @param scanner
	 * @return
	 */
	public static boolean userYesNoResponse(Scanner scanner) {

		// declare variables
		boolean response;
		String userInput;

		// initialise variable
		response = false;

		// loop repeats until the user enters y/n
		do {
			userInput = scanner.nextLine();
			userInput.toLowerCase().trim();
			if (userInput.equals("y")) {
				response = true;
				System.out.println("Selected : Yes");
			} else if (userInput.equals("n")) {
				response = false;
				System.out.println("Selected : No");
			} else {
				System.out.println("Invalid input , please select either y or n. ");
			}
		} while ((!userInput.equals("y")) && (!userInput.equals("n")));

		return response;
	}

	/**
	 * Provides error handling when the user is asked to make a selection by
	 * inputting a number.
	 * 
	 * Throws InputMismatchException if the user enters anything other than an int.
	 * 
	 * @param scanner
	 * @return userNumber -
	 * @throws InputMismatchException
	 */
	public static int userNumberResponse(Scanner scanner) throws InputMismatchException {

		// declare variables
		int userNumber;
		boolean error;

		// initialise variables
		error = false;
		userNumber = 0;

		// loop continues until user provides valid input
		do {
			try {
				userNumber = scanner.nextInt();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number between 1 - 6 ");
				error = true;
				scanner.next();
			}
		} while (error == true);
		return userNumber;
	}
	
	/**
	 * Thread created to add delay to output. 
	 * 
	 * @param delayAmount
	 */
	public static void delay(int delayAmount) {
		// maybe should be moved to main and called from there???
		try {
			Thread.sleep(delayAmount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
