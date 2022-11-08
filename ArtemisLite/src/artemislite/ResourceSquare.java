/**
 * 
 */
package artemislite;

/**
 * This class represents the occurrence on the Resource Square "Base Camp
 * Square". This subclass of square has a collectible resource amount variable.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
public class ResourceSquare extends Square implements IDisplay, IUtility {

	// Invalid resource amount as IUtility uses the resource amount to parse between
	// ResourceSquare and NothingSquare
	private static final int INVALID_RESOURCE_AMOUNT = 0;
	

	// Instance variables.
	private int resourceAdjustment;

	/**
	 * Default constructor for Resource Square.
	 */
	public ResourceSquare() {

	}

	/**
	 * Constructor with arguments for Resource Square.
	 * 
	 * @param squareName         The name of the square
	 * @param positionOnBoard    Players' position on the game board.
	 * @param resourceAdjustment Adjusting the players resources.
	 * @param isPurchasable      Checks if the square is available to be purchase.
	 * @throws IllegalArgumentException if the resource amount is equal to zero
	 */
	public ResourceSquare(String squareName, int positionOnBoard, int resourceAdjustment, boolean isPurchaseable)
			throws IllegalArgumentException {
		super(squareName, positionOnBoard, isPurchaseable);
		setResourceAdjustment(resourceAdjustment);
	}

	/**
	 * Get method that returns the amount of resource player can collect.
	 * 
	 * @return The resource amount of this instance of resource square
	 */
	public int getResourceAdjustment() {
		return resourceAdjustment;
	}

	/**
	 * 
	 * Method that sets the resource amount of the player can collect.
	 * 
	 * @param The resource amount to be set for collection when a player lands on
	 *            Resource Square.
	 * @throws IllegalArgumentException if the resource adjustment amount is zero
	 */
	public void setResourceAdjustment(int resourceAdjustment) throws IllegalArgumentException {
		if (resourceAdjustment == INVALID_RESOURCE_AMOUNT) {
			throw new IllegalArgumentException("Resource adjustment level cannot be zero.");
		} else {
			this.resourceAdjustment = resourceAdjustment;
		}

	}

	/**
	 * Overrides and implements method from IDisplay class. Outputs to screen the
	 * name of the square, players' position on board as well as the collected
	 * resources.
	 */
	@Override
	public void displayAll() {
		System.out.println("Name : " + super.getSquareName());
		System.out.println("------------------------------------------------------");
		System.out.println("Position on the board : " + (super.getPositionOnBoard() + 1));
		System.out.println("Resources collected if you land on this square : " + this.getResourceAdjustment());

	}

	/**
	 * Override toString method. Method that returns the name of the square,
	 * players' position on board as well as resources received.
	 */

	@Override
	public String toString() {
		return "ResourceSquare [getSquareName()=" + getSquareName() + ", getPositionOnBoard()=" + getPositionOnBoard()
				+ ", isPurchasable()=" + isPurchasable() + "getresourceAdjustment()=" + this.resourceAdjustment + "]";
	}

	/**
	 * Overrides and implements method from Square class. Method that represents
	 * which square a player landed on the board. Outputs to screen the name of the
	 * square and the amount of resources a player collects.
	 * 
	 * @return
	 */
	@Override
	public String landedOn(Player currentPlayer) {
		String message;
		message = currentPlayer.getPlayerName() + " has landed on " + this.getSquareName();
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(message);
		System.out.println("Available resources to collect at this square : " + this.resourceAdjustment);
		return message;

	}

	/**
	 * Overrides and implements the method from IUtility class. Method returns the
	 * amount of a player recieve when passing the base camp.
	 */
	@Override
	public int doAction() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println();
		return this.resourceAdjustment;
	}

}
