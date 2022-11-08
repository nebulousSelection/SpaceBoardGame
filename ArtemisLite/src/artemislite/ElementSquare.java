/**
 * 
 */
package artemislite;

import java.util.Scanner;

/**
 * Element Square class represents a square that can be purchased, charge rent
 * and be developed by the user. This class has multiple methods that automate
 * setting up an ElementSquare based on their position or the system they are
 * in. These can be bypassed by using the full constructor with arguments.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
public class ElementSquare extends Square implements IDisplay {

	// constants

	// used to calculate the price of a development upgrade
	private static final double MINOR_DEVELOPMENT_MULTIPLIER = 1.2, MAJOR_DEVELOPMENT_MULTIPLIER = 1.5;

	// used to calculate initial rent, purchase cost and development costs
	private static final int BASE_RENT = 5, BASE_ELEMENT_COST = 50, BASE_DEV_COST = 80;
	private static final int POSITION_MODIFIER_RENT = 10, POSITION_MODIFIER_DEV_COST = 10,
			POSITION_MODIFIER_PURCHASE = 50;

	// Boundary values for instance vars
	private static final int MINIMUM_RENT_COST = 0, MINIMUM_DEVELOPMENT_COST = 0, MINIMUM_PURCHASE_COST = 0;
	protected static final int MIN_DEVELOPMENT_AMOUNT = 0, MAX_DEVELOPMENT_AMOUNT = 4;

	// instance vars
	private int costToBuy;
	private int rentPrice;
	private int developmentLevel;
	private int developmentUpgradeCost;
	private Player owner;
	private ESystem system;
	private boolean isDevelopable;

	/**
	 * Default constructor for ElementSquare
	 */
	public ElementSquare() {

	}

	/**
	 * Constructor with less arguments for square setup. The other values are set
	 * using methods allowing them to be more dynamically calculated based on the
	 * position of the board
	 * 
	 * @param squareName
	 * @param positionOnBoard
	 * @param owner
	 * @param system
	 * @param isPurchasable
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public ElementSquare(String squareName, int positionOnBoard, ESystem system, boolean isPurchasable)
			throws IllegalArgumentException, NullPointerException {
		super(squareName, positionOnBoard, isPurchasable);
		this.setSystem(system);
		this.setCostToBuy(this.calculateCostToBuy());
		this.setRentPrice(this.calculateRent());
		this.setDevelopmentUpgradeCost(this.calculateDevelopmentUpgradeCost());

	}

	/**
	 * Constructor with full arguments will allow someone else working on the system
	 * to set every value individually should they want to
	 * 
	 * @param squareName
	 * @param positionOnBoard
	 * @param isPurchasable
	 * @param costToBuy              cost of an element square
	 * @param rentPrice              cost of rent for element square
	 * @param developmentLevel       level of development min 0 max 4
	 * @param developmentUpgradeCost cost to develop an element
	 * @param owner                  current owner of square if null then no current
	 *                               owner
	 * @param system                 which grouping the element square is affects
	 *                               costs of element square
	 * @param isDevelopable          if this square is able to be developed , only
	 *                               available when all elementsquares in a system
	 *                               are purchased by one owner
	 * 
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public ElementSquare(String squareName, int positionOnBoard, boolean isPurchasable, int costToBuy, int rentPrice,
			int developmentLevel, int developmentUpgradeCost, Player owner, ESystem system, boolean isDevelopable)
			throws IllegalArgumentException, NullPointerException {
		super(squareName, positionOnBoard, isPurchasable);
		this.setCostToBuy(costToBuy);
		this.setRentPrice(rentPrice);
		this.setDevelopmentLevel(developmentLevel);
		this.setDevelopmentUpgradeCost(developmentUpgradeCost);
		this.owner = owner;
		this.setSystem(system);
		this.isDevelopable = isDevelopable;
	}

	/**
	 * @return the costToBuy
	 */
	public int getCostToBuy() {
		return costToBuy;
	}

	/**
	 * sets the cost to buy an elementSquare
	 * 
	 * @param costToBuy
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	private void setCostToBuy(int costToBuy) throws IllegalArgumentException {
		if (costToBuy > MINIMUM_PURCHASE_COST) {
			this.costToBuy = costToBuy;
		} else {
			throw new IllegalArgumentException("Cost to buy can't be less than zero");
		}
	}

	/**
	 * @return the rentPrice
	 */
	public int getRentPrice() {
		return rentPrice;

	}

	/**
	 * Uses private method to calculate rent price based on the current development
	 * level, the position of the square on the board and the system it is in
	 * 
	 * @param rentPrice the rentPrice to set
	 * @throws IllegalArgumentException
	 */
	private void setRentPrice(int rentPrice) throws IllegalArgumentException {
		if (rentPrice > MINIMUM_RENT_COST) {
			this.rentPrice = rentPrice;
		} else {
			throw new IllegalArgumentException("Rent Price can't be less than zero");
		}
	}

	/**
	 * @return the developmentLevel
	 */
	public int getDevelopmentLevel() {
		return developmentLevel;
	}

	/**
	 * sets development level
	 * 
	 * @param developmentLevel
	 * @throws IllegalArgumentException if greater than 4 or less than 0
	 */
	private void setDevelopmentLevel(int developmentLevel) throws IllegalArgumentException {
		if (developmentLevel <= MAX_DEVELOPMENT_AMOUNT && developmentLevel >= MIN_DEVELOPMENT_AMOUNT) {
			this.developmentLevel = developmentLevel;
		} else if (developmentLevel < MAX_DEVELOPMENT_AMOUNT) {
			throw new IllegalArgumentException("Exceeded maximum development level");
		} else if (developmentLevel > MIN_DEVELOPMENT_AMOUNT) {
			throw new IllegalArgumentException("Below minimum development level");
		}

	}

	/**
	 * @return the developmentUpgradeCost
	 */
	public int getDevelopmentUpgradeCost() {
		return developmentUpgradeCost;
	}

	/**
	 * the developmentUpgradeCost to set
	 * 
	 * @param developmentUpgradeCost
	 * @throws IllegalArgumentException if less that 0
	 */
	public void setDevelopmentUpgradeCost(int developmentUpgradeCost) throws IllegalArgumentException {
		if (developmentUpgradeCost > MINIMUM_DEVELOPMENT_COST) {
			this.developmentUpgradeCost = developmentUpgradeCost;
		} else {
			throw new IllegalArgumentException("Development Level Cost can't be less than zero");
		}
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @return the system
	 */
	public ESystem getSystem() {
		return system;
	}

	/**
	 * 
	 * @param system the system to set
	 * @throws NullPointerException if null
	 */
	public void setSystem(ESystem system) throws NullPointerException {
		if (system != null) {
			this.system = system;
		} else {
			throw new NullPointerException("No system assigned to ElementSquare");
		}
	}

	public boolean isDevelopable() {
		return isDevelopable;
	}

	public void setDevelopable(boolean isDevelopable) {
		this.isDevelopable = isDevelopable;
	}

	/**
	 * Once the amount of development to be done has been verified, this method then
	 * adds the
	 * 
	 * @param developmentAmount
	 * @return
	 */

	public void addDevelopment(int developmentAmount) {
		int newDevelopmentLevel;
		newDevelopmentLevel = this.developmentLevel + developmentAmount;
		this.setDevelopmentLevel(newDevelopmentLevel);
		this.setRentPrice(this.calculateRent());

	}

	/**
	 * calculates the cost of new developments
	 * 
	 * @param developmentAmount between 1-4 validated in userDevelopment amount
	 * @return
	 */

	public int newDevelopmentUpgradeCost(int developmentAmount) {
		int newDevelopmentLevel, developmentCost;

		newDevelopmentLevel = this.developmentLevel + developmentAmount;
		developmentCost = 0;
		if (developmentAmount != MIN_DEVELOPMENT_AMOUNT) {
			for (int index = this.developmentLevel; index <= newDevelopmentLevel; index++) {
				
				
				
				if (index == MIN_DEVELOPMENT_AMOUNT) {
					developmentCost = 0;
				} else if (index == this.developmentLevel +1) {
					developmentCost = this.developmentUpgradeCost;
				} else if (index > this.developmentLevel) {
					developmentCost += this.developmentUpgradeCost;
				}
				
				if (index == (MAX_DEVELOPMENT_AMOUNT - 1)) {
					this.setDevelopmentUpgradeCost(
							(int) (this.getDevelopmentUpgradeCost() * MAJOR_DEVELOPMENT_MULTIPLIER));
				}
				
			}
		}
		return developmentCost;

	}

	/**
	 * user inputs the amount they would like to develop an Element Square by, this
	 * method gives them information about the development level of the Element
	 * Square at present and validates the input so that it does not set the
	 * development level beyond its limits
	 * 
	 * @param scanner
	 * @return
	 */
	public int userDevelopmentAmount(Scanner scanner) {
		String quit = "q";
		int newDevelopmentLevel;
		Integer developmentAmount = null;
		String userIn = null;
		boolean validEntry = false;

		do {
			System.out.println("Please enter the amount you would like to develop this property? (number input)");
			System.out.println("Enter 'q' to return to the development menu");

			userIn = scanner.nextLine();
			// userIn.trim();

			if (userIn.equals(quit)) {
				System.out.println("Now returning to the development menu..\n");
				developmentAmount = 0;

			} else {
				try {

					developmentAmount = Integer.parseInt(userIn);

					newDevelopmentLevel = this.developmentLevel + developmentAmount;
					if (developmentAmount == MIN_DEVELOPMENT_AMOUNT) {
						System.out.println("\nYou entered : " + developmentAmount + ".");
						System.out.println("Nothing will change please try again");
						validEntry = false;

					} else if (newDevelopmentLevel > MAX_DEVELOPMENT_AMOUNT) {
						System.out.println(
								"\nMaximum development amount exceeded based on input - no change has been made");
						System.out.println("The Maximum amount " + this.getSquareName() + "can be upgrade by is : "
								+ (MAX_DEVELOPMENT_AMOUNT - this.developmentLevel) + "\n");
						newDevelopmentLevel = MIN_DEVELOPMENT_AMOUNT;
						validEntry = false;
					} else {
						System.out.println("\nThe current development level of this property is :"
								+ displayShortDevelopmentLevel(this.developmentLevel));
						System.out.println(
								"Preparing to upgrade to : " + displayShortDevelopmentLevel(newDevelopmentLevel));

						validEntry = true;

					}

				} catch (NumberFormatException e) {
					System.out.println("Invalid input:");
					scanner.nextLine();
					validEntry = false;

				}

			}

		} while (validEntry == false && !userIn.equalsIgnoreCase("q"));

		return developmentAmount;

	}

	/**
	 * Displays to String the state of the development of an Element square
	 * 
	 * @param developmentAmount between 1 and 4
	 */
	public void displayDevelopmentLevel(int developmentAmount) {
		switch (developmentAmount) {
		case 0:
			System.out.println("\n" + this.getSquareName()
					+ " has no developments , the next development level is : Space blanket ");
			System.out.println("It requires 4 more developments to reach maximum development level \n ");
			break;
		case 1:
			System.out.println("\n" + this.getSquareName()
					+ " is currently at development level :  minor dev level 1  , the next development level is : Re-purposed Lunar Rover Shelter ");
			System.out.println("It requires 3 more developments to reach maximum development level \n");
			break;
		case 2:
			System.out.println("\n" + this.getSquareName()
					+ " is currently at development level :  minor dev level 2 , the next development level is : Unfurnished Moon Shed");
			System.out.println("It requires 2 more developments to reach maximum development level \n");
			break;
		case 3:
			System.out.println("\n" + this.getSquareName()
					+ " is currently at development level :  minor dev level 3 , the next development level is : Earth View Luxury Apartment ");
			System.out.println("It requires 1 more developments to reach maximum development level \n");
			break;
		case 4:
			System.out.println(this.getSquareName() + " is fully developed! You have an Earth View Luxury Apartment here.");
			break;

		default:
			System.out.println("Error in Developments, number set beyond its limits");
			break;

		}

	}

	/**
	 * Displays to String the state of the development of an Element square
	 * 
	 * @param developmentAmount between 1 and 4
	 */
	public String displayShortDevelopmentLevel(int developmentAmount) {
		switch (developmentAmount) {
		case 0:
			return "Not developed";
		case 1:
			return "Minor development level 1 : Space blanket";
		case 2:
			return "Minor development level 2 : Re-purposed Lunar Rover Shelter";
		case 3:
			return "Minor development level 3 : Unfurnished Moon Shed";
		case 4:
			return "Major development : Earth View Luxury Apartment";
		default:
			return "Development level error";
		}

	}

	/**
	 * Utility method to show whether an ElementSquare is fully developed or not
	 * 
	 * @return true if element fully developed
	 */

	public boolean fullyDeveloped() {
		if (this.developmentLevel == MAX_DEVELOPMENT_AMOUNT) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * purchase
	 * 
	 * @param newOwner
	 * @return the cost to buy this ElementSquare
	 */
	public int purchaseElementSquare(Player newOwner) {
		this.setOwner(newOwner);
		return this.costToBuy;
	}

	/**
	 * This method calculates what the rent for each property square should be based
	 * on : where the square is on the board, which system it is in and what the
	 * current level of development is.
	 * 
	 * @return returns what the rent is being set to
	 */

	public int calculateRent() {

		int rentCost, positionAdjustment, elementBaseRent, currentDevelopmentLevel;
		double systemModifier;

		rentCost = BASE_RENT;

		positionAdjustment = this.getPositionOnBoard() * POSITION_MODIFIER_RENT;
		systemModifier = this.system.getSystemMultiplier();
		currentDevelopmentLevel = this.getDevelopmentLevel();

		elementBaseRent = (int) ((BASE_RENT + positionAdjustment) * systemModifier);

		switch (currentDevelopmentLevel) {
		case 0:
			rentCost = elementBaseRent;
			break;
		case 1:
			rentCost = (int) (elementBaseRent * MINOR_DEVELOPMENT_MULTIPLIER);
			break;
		case 2:
			rentCost = (int) (elementBaseRent * Math.pow(MINOR_DEVELOPMENT_MULTIPLIER, developmentLevel));
			break;
		case 3:
			rentCost = (int) (elementBaseRent * Math.pow(MINOR_DEVELOPMENT_MULTIPLIER, developmentLevel));
			break;
		case 4:
			rentCost = (int) (elementBaseRent * Math.pow(MINOR_DEVELOPMENT_MULTIPLIER, (developmentLevel - 1))
					* MAJOR_DEVELOPMENT_MULTIPLIER);
			break;

		default:
			System.out.println("Error setting rent");
			break;
		}
		return rentCost;
	}

	/**
	 * This method calculates the cost to buy an element at the start of the game
	 * dynamically based on the square's position on the board and which system it
	 * is in
	 * 
	 * @return
	 */

	public int calculateCostToBuy() {

		int elementCost, positionAdjustment;
		double systemModifier;

		elementCost = BASE_ELEMENT_COST;
		positionAdjustment = this.getPositionOnBoard() * POSITION_MODIFIER_PURCHASE;
		systemModifier = this.system.getSystemMultiplier();

		elementCost *= systemModifier;
		elementCost += positionAdjustment;

		return elementCost;
	}

	/**
	 * creates a base development level for this square, a major development at
	 * level 4 is increased using a constant multiplier when the user is being
	 * charged in
	 * 
	 * @return the base development cost of this square
	 */

	public int calculateDevelopmentUpgradeCost() {

		int devCost, positionAdjustment;
		double systemModifier;

		devCost = BASE_DEV_COST;

		positionAdjustment = this.getPositionOnBoard() * POSITION_MODIFIER_DEV_COST;
		systemModifier = this.system.getSystemMultiplier();

		devCost *= systemModifier;
		devCost += positionAdjustment;

		return devCost;
	}

	/**
	 * Override toString method
	 */

	@Override
	public String toString() {
		return "ElementSquare [getSquareName()=" + getSquareName() + ", getPositionOnBoard()=" + getPositionOnBoard()
				+ ", isPurchasable()=" + isPurchasable() + "costToBuy=" + costToBuy + ", rentPrice=" + rentPrice
				+ ", developmentLevel=" + developmentLevel + ", developmentUpgradeCost=" + developmentUpgradeCost
				+ ", owner=" + owner + ", system=" + system + ", isDevelopable=" + isDevelopable + "]";
	}

	/**
	 * Implemented method from IDisplay
	 */

	@Override
	public void displayAll() {

		System.out.println("Name : " + super.getSquareName());
		System.out.println("-------------------------------------------------");
		System.out.println("Position on the board : " + (super.getPositionOnBoard() + 1));
		System.out.println("System : " + this.system.getSystemName());
		System.out.println("Current rent : " + this.rentPrice);

		System.out.println();
		if (isDevelopable) {
			System.out.println("Current development level : " + displayShortDevelopmentLevel(this.developmentLevel));
			System.out.println("Cost for a single upgrade : " + this.getDevelopmentUpgradeCost());
		} else {
			System.out.println("Not yet able to be developed");
		}

		if (this.getOwner() != null) {
			System.out.print("Owner : " + this.owner.getPlayerName());
		}
		System.out.println();

	}

	@Override
	public String landedOn(Player currentPlayer) {
		String message;
		message = currentPlayer.getPlayerName() + " has landed on " + this.getSquareName();
		System.out.println("----------------------------------------------------------");
		System.out.println(message + " \tSystem : " + this.system.getSystemName());
		System.out.println("Postion on the board : " + (this.getPositionOnBoard() + 1) + "\n");
		if (owner == null) {
			System.out.println("This Square is available for purchase for : " + this.costToBuy);
		} else if (owner.equals(currentPlayer)) {
			System.out.println("You own this square");
			if (this.isDevelopable) {
				System.out.println("This Square can be developed! Its current development level is :"
						+ displayShortDevelopmentLevel(this.developmentLevel));
			} else {
				System.out.println("This Square can not yet be developed, you must acquire the others in this system");
			}

		} else {

			System.out.println("The owner of this square is : " + this.owner.getPlayerName());
			System.out.println("The current price of rent is : " + this.getRentPrice());
		}

		System.out.println("----------------------------------------------------------\n");
		return message;
	}

}
