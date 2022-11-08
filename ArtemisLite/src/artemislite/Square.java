/**
 * 
 */
package artemislite;

/**
 * This class represents a square the player may land on. Currently the
 * superclass of ElementSquare, ResourceSquare and NothingSquare.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
public abstract class Square implements IDisplay {
	// constants
	private static final int MINIMUM_NAME_LENGTH = 3, MAXIMUM_NAME_LENGTH = 25;
	private static final int MINIMUM_POSITION_ON_BOARD = 0, MAXIMUM_POSITION_ON_BOARD = 11;

	// Instance variables.
	private String squareName;
	private int positionOnBoard;
	private boolean isPurchasable;

	/**
	 * Default constructor for Square
	 */
	public Square() {

	}

	/**
	 * Constructor with arguments for Square.
	 * 
	 * @param squareName      The name of the square. Valid length :
	 *                        {@value #MINIMUM_NAME_LENGTH} to
	 *                        {@value #MAXIMUM_NAME_LENGTH}
	 * @param positionOnBoard Players' position on the game board.
	 * @param isPurchasable   Checks if the square is available to be purchase.
	 * 
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Square(String squareName, int positionOnBoard, boolean isPurchasable)
			throws IllegalArgumentException, NullPointerException {

		this.setSquareName(squareName);
		this.setPositionOnBoard(positionOnBoard);
		this.isPurchasable = isPurchasable;
	}

	/**
	 * Get method that returns the name of the Square.
	 * 
	 * @return The name of this instance of the Square.
	 */
	public String getSquareName() {
		return squareName;

	}

	/**
	 * Method that sets the name of the Square.
	 * 
	 * @param squareName Square name valid length : {@value #MINIMUM_NAME_LENGTH} to
	 *                   {@value #MAXIMUM_NAME_LENGTH}
	 * @throws NullPointerException     if the name entered is null
	 * @throws IllegalArgumentException if outside valid length
	 */
	public void setSquareName(String squareName) throws NullPointerException, IllegalArgumentException {
		if (squareName == null) {
			throw new NullPointerException("Square name is null.");
		} else if (squareName.length() >= MINIMUM_NAME_LENGTH && squareName.length() <= MAXIMUM_NAME_LENGTH) {
			this.squareName = squareName;
		} else {
			throw new IllegalArgumentException("Invalid Square Name Length.");
		}
	}

	/**
	 * Get method that returns the square position on the board of the player.
	 * 
	 * @return The position on board of this instance of the square for the player.
	 */
	public int getPositionOnBoard() {
		return positionOnBoard;
	}

	/**
	 * Method that sets the square position of the player on the board.
	 * 
	 * @param positionOnBoard where the square will be placed on the board. Index 0.
	 * @throws IllegalArgumentException if outside
	 *                                  {@value #MINIMUM_POSITION_ON_BOARD} and
	 *                                  {@value #MAXIMUM_POSITION_ON_BOARD}
	 */
	public void setPositionOnBoard(int positionOnBoard) throws IllegalArgumentException {
		if (positionOnBoard >= MINIMUM_POSITION_ON_BOARD && positionOnBoard <= MAXIMUM_POSITION_ON_BOARD) {

			this.positionOnBoard = positionOnBoard;

		} else {
			throw new IllegalArgumentException("Position on board out of bounds.");
		}
	}

	/**
	 * Get method that returns true or false whether a Square is available to be
	 * purchase or not.
	 * 
	 * @return The isPurchasable of this instance of the square.
	 */
	public boolean isPurchasable() {
		return isPurchasable;
	}

	/**
	 * Method that sets the square to be purchasable or not.
	 * 
	 * @param The isPurchasable to be set of the square
	 */
	public void setPurchasable(boolean isPurchasable) {
		this.isPurchasable = isPurchasable;
	}

	/**
	 * Abstract override toString method
	 * 
	 */

	@Override
	public abstract String toString();

	/**
	 * Abstract method that represents which square a player landed on the board.
	 */
	public abstract String landedOn(Player currentPlayer);

}
