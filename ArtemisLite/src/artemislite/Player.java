package artemislite;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Instances of this class are used to represent players and their respective
 * game metrics.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
public class Player implements IDisplay {

	// declare variables
	private String playerName;
	private int playerLocation, resourceBalance, prevResourceBalance, prevNumberofProperties;
	private ArrayList<ElementSquare> playerOwnedSquares = new ArrayList<ElementSquare>();

	// declare constants
	private static final int MIN_PLAYER_NAME_LENGTH = 3;
	private static final int MAX_PLAYER_NAME_LENGTH = 20;
	private static final int FINAL_SQUARE_INDEX = 11;
	private static final int MINIMUM_RESOURCES = 0;

	/**
	 * Default constructor.
	 */
	public Player() {

	}

	/**
	 * Argument based constructor.
	 * 
	 * @param playerName         - String between 3 and 20 characters long inclusive
	 * @param resourceBalance    - Integer
	 * @param playerLocation     - Integer
	 * @param playerOwnedSquares - ArrayList of integers
	 * @throws IllegalArgumentException
	 */
	public Player(String playerName, int resourceBalance, int playerLocation,
			ArrayList<ElementSquare> playerOwnedSquares) throws IllegalArgumentException {
		this.setPlayerName(playerName);
		this.resourceBalance = resourceBalance;
		this.playerLocation = playerLocation;
		this.playerOwnedSquares = playerOwnedSquares;
	}

	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName - Must be between 3 and 20 characters long inclusive
	 * @throws IllegalArgumentException
	 */
	public void setPlayerName(String playerName) throws IllegalArgumentException {
		if (playerName.length() >= MIN_PLAYER_NAME_LENGTH && playerName.length() <= MAX_PLAYER_NAME_LENGTH) {
			this.playerName = playerName;
		} else {
			throw new IllegalArgumentException("Player name must be between 3 and 20 characters long inclusive");
		}
	}

	public int getResourceBalance() {
		return resourceBalance;
	}

	public void setResourceBalance(int resourceBalance) {
		if (resourceBalance < MINIMUM_RESOURCES) {
			this.resourceBalance = MINIMUM_RESOURCES;
		} else {

			this.resourceBalance = resourceBalance;
		}

	}

	public int getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(int playerLocation) {
		this.playerLocation = playerLocation;
	}

	public ArrayList<ElementSquare> getPlayerOwnedSquares() {
		return playerOwnedSquares;
	}

	public void setPlayerOwnedSquares(ArrayList<ElementSquare> playerOwnedSquares) {
		this.playerOwnedSquares = playerOwnedSquares;
	}

	public int getPrevResourceBalance() {
		return prevResourceBalance;
	}

	public void setPrevResourceBalance(int prevResourceBalance) {
		this.prevResourceBalance = prevResourceBalance;
	}

	public int getPrevNumberofProperties() {
		return prevNumberofProperties;
	}

	public void setPrevNumberofProperties(int prevNumberofProperties) {
		this.prevNumberofProperties = prevNumberofProperties;
	}

	public void addPlayerOwnedSquare(ElementSquare purchasedSquare) {
		playerOwnedSquares.add(purchasedSquare);
	}

	/**
	 * Moves the player location ahead the specified number of squares.
	 * 
	 * @param squaresToMove - Direct output from rollDice() here
	 */
	public void movePosition(int squaresToMove) {

		if (this.playerLocation + squaresToMove > FINAL_SQUARE_INDEX) {
			this.playerLocation = ((this.playerLocation + squaresToMove) - FINAL_SQUARE_INDEX - 1);
		} else {
			this.playerLocation += squaresToMove;
		}

	}

	/**
	 * Adds to the player resource balance, and prints a relevant user message to
	 * the console.
	 * 
	 * @param resourceAmount - the value to be added to the player resource balance
	 */
	public void collectResources(int resourceAmount) {
		this.resourceBalance += resourceAmount;
		System.out.println(this.getPlayerName() + "'s balance has been increased by : " + resourceAmount);
		System.out.println(this.getPlayerName() + "'s new balance is: " + this.resourceBalance);
	}

	/**
	 * Implemented method from IDisplay.
	 */
	@Override
	public void displayAll() {
		System.out.printf("Organisation Name:   %-25sResource Balance:   %-25dLocation:   %s\n", this.playerName,
				this.resourceBalance, this.playerLocation);
	}

	/**
	 * Custom display method for player rankings displayed at end of game. Prints
	 * user-friendly info to console.
	 */
	public void displayAllFinalRanking() {
		System.out.format("  %-25s", this.playerName);
		System.out.format("|  %-23d", this.getPlayerOwnedSquares().size());
		System.out.format("|  %d", this.getResourceBalance());
		System.out.println();
	}

	/**
	 * Invokes decreaseResources() method to reduce the player's balance by the cost
	 * of the square.
	 * 
	 * @param elementSquareCost cost to buy a particular element square
	 */
	public void purchaseSquare(int elementSquareCost) {
		decreaseResources(elementSquareCost);
	}

	/**
	 * Invokes decreaseResources() method to reduce the player's balance by the cost
	 * of the rent.
	 * 
	 * @param rentAmount
	 */
	public void payRent(int rentAmount) {
		decreaseResources(rentAmount);
	}

	/**
	 * Reduces the player's balance by the specified amount.
	 * 
	 * @param amountToDecrease
	 */
	public void decreaseResources(int amountToDecrease) {
		this.setResourceBalance(this.getResourceBalance() - amountToDecrease);
		System.out.println(this.getPlayerName() + "'s new balance is: " + this.resourceBalance);
		System.out.println();
	}

	/**
	 * Checks if the player's resource balance has fallen to or below zero,
	 * returning a boolean indicating yes or no.
	 * 
	 * @return playerIsBankrupt - boolean indicating whether the current player is
	 *         bankrupt
	 */
	public boolean isBankrupt() {
		boolean playerIsBankrupt;
		if (this.getResourceBalance() <= 0) {
			playerIsBankrupt = true;
		} else {
			playerIsBankrupt = false;
		}
		return playerIsBankrupt;
	}

	/**
	 * Method prompts user(s) for input (y/n) on whether or not they wish to
	 * purchase a property. If the first user declines, each subsequent user is
	 * offered a chance to buy according to their play order until either the
	 * property is purchased or all players decline.
	 * 
	 * Basic user input error handling is included.
	 * 
	 * @param scanner
	 * @return
	 */
	public Player offerToPurchase(Scanner scanner) {

		// declare variables
		String userInput;
		Player purchaser;

		// initialise Player object
		purchaser = null;

		// loop continues until acceptable input is provided
		do {
			System.out.println(this.playerName + " your current balance is " + this.resourceBalance);
			System.out.println(this.playerName + " would you like to buy this Square? (y/n)");
			userInput = scanner.nextLine();
			userInput.toLowerCase();

			// prompts user for input while providing input error handling
			if (userInput.equalsIgnoreCase("y")) {
				purchaser = this;
			} else if (userInput.equalsIgnoreCase("n")) {
				purchaser = null;
			} else {
				System.out.println("Invalid input, please choose either y or n and press enter");
			}
		} while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n"));

		return purchaser;
	}

	/**
	 * Provides user with further information before asking them to confirm their
	 * decision to purchase property.
	 * 
	 * @param scanner
	 * @param developmentCost
	 * @return response - boolean indicating
	 */
	public boolean checkDevelopmentWanted(Scanner scanner, int developmentCost) {

		// declare variables
		String userInput;
		boolean response;

		// initialise variable
		response = false;

		// loop continues until acceptable input is provided
		do {
			System.out.println(this.playerName + " your current balance is : " + this.resourceBalance);
			System.out.println(this.playerName + " this development will cost you " + developmentCost
					+ " are you sure you want to go ahead? (y/n)\n");

			userInput = scanner.nextLine();
			userInput.toLowerCase();

			// prompts user for input while providing input error handling
			if (userInput.equalsIgnoreCase("y")) {
				response = true;
			} else if (userInput.equalsIgnoreCase("n")) {
				response = false;
			} else {
				System.out.println("Invalid input, please choose either y or n and press enter");
			}
		} while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n"));

		return response;
	}

	/**
	 * Executes purchase transaction by adding property to player's owned property
	 * list and deducting the cost from their resource balance.
	 * 
	 * @param costToBuy
	 * @param purchased - property which is to be purchased
	 */
	public void purchaseElementSquare(int costToBuy, ElementSquare purchased) {
		this.addPlayerOwnedSquare(purchased);
		this.decreaseResources(costToBuy);
	}

	/**
	 * Fetches player owned properties which can be developed.
	 * 
	 * @return developableSquares - contains player owned properties which can be
	 *         developed
	 */
	public ArrayList<ElementSquare> getDevelopableProperties() {
		List<ElementSquare> ownedSquares = this.playerOwnedSquares;
		ArrayList<ElementSquare> developableSquares = new ArrayList<ElementSquare>();

		// check if the player owns properties
		if (!ownedSquares.isEmpty()) {
			for (ElementSquare square : ownedSquares) {
				// check if the owned properties can be developed
				if (square.isDevelopable()) {
					// if yes, add them to the ArrayList to be returned
					developableSquares.add(square);
				}
			}
		}
		return developableSquares;
	}

	/**
	 * Prompts the user to select the property they would like to develop. Provides
	 * user-friendly input error handling via do-while loop.
	 * 
	 * @param scanner
	 * @return ElementSquare - returns the property selected by the user
	 */
	public ElementSquare selectDevelopmentOption(Scanner scanner) {
		int userInput = 0, userInputDisplay;
		boolean validInput;
		ArrayList<ElementSquare> developableSquares = this.getPlayerOwnedSquares();
		do {

			System.out.println("Please enter the number above the Element Square you would like to develop");
			try {
				userInput = scanner.nextInt();
				userInput -= 1;
				if (userInput >= 0 && userInput < developableSquares.size()) {
					userInputDisplay = userInput + 1;
					System.out.println("You selected : " + (userInputDisplay));
					validInput = true;

				} else {
					throw new IndexOutOfBoundsException("Property selected not available, please try again.");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
				validInput = false;
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a the number as a digit ");
				validInput = false;
				scanner.nextLine();
			}
		} while (validInput == false);
		// to make the difference between the actual index and what is displayed

		return developableSquares.get(userInput);

	}

	/**
	 * This method takes a "snapshot" of certain game-play metrics so they can be
	 * referenced in the player's next turn to show their progress.
	 */
	public void playerSnapShot() {
		this.setPrevResourceBalance(this.getResourceBalance());
		this.setPrevNumberofProperties(this.getPlayerOwnedSquares().size());
	}

	/**
	 * Provides a dynamic update on changes to the player's statistics since their
	 * previous turn, using the metrics stored by the "player snapshot".
	 * 
	 * @param Player
	 */
	public void playerSummary() {
		System.out.println("~~~ END OF TURN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// resource balance update
		if (this.getResourceBalance() > this.getPrevResourceBalance()) {
			System.out.println(
					this.getPlayerName() + " earned " + (this.getResourceBalance() - this.getPrevResourceBalance())
							+ " in the last round bringing their balance to " + this.getResourceBalance() + ".");
		} else if (this.getResourceBalance() == this.getPrevResourceBalance()) {
			System.out.println(this.getPlayerName() + " broke even in the last round, keeping their balance at "
					+ this.getResourceBalance() + ".");
		} else if (this.getResourceBalance() < this.getPrevResourceBalance()) {
			System.out.println(
					this.getPlayerName() + " spent " + (this.getPrevResourceBalance() - this.getResourceBalance())
							+ " in the last round bringing their balance to " + this.getResourceBalance() + ".");
		}
		// property ownership update
		if (this.getPlayerOwnedSquares().size() > this.getPrevNumberofProperties()) {
			System.out.println("They purchased "
					+ (this.getPlayerOwnedSquares().get(this.getPlayerOwnedSquares().size() - 1)).getSquareName()
					+ ", bringing their total number of properties to " + this.getPlayerOwnedSquares().size() + ".");
		} else {
			System.out.println("They own " + this.getPlayerOwnedSquares().size() + " properties.");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}
