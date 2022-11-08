package artemislite;

/**
 * This class represents the Nothing Square.
 * No action is taken by the player or the game on this square. 
 * It displays a randomly selected message and person who has sent the message when landed on.
 * This subclass of square has a collectible resource amount variable.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */


import java.util.Random;

public class NothingSquare extends Square implements IDisplay, IUtility {

	// constants

	/**
	 * Used to parse between NothingSquare and ResourceSquare by IUtility Resource
	 * Square returns a value that is not zero
	 */
	private static final int DO_ACTION_RETURN = 0;
	/**
	 * Predetermined array of messages that are randomly selected when a player
	 * lands on this square
	 */
	private static final String[] MESSAGES = {
			"Just letting you know we have sold your drum kit \nwhile you are away, hope that won't be a problem....",
			"You have won the lottery! You have 3 days to claim your reward , \nhow long does it take to get back from space ? ",
			"Did you leave the immersion on?",
			"We've been trying to reach you about your car's extended warranty.......",
			"You may have millions in unclaimed P.P.E .....  ", "To infinity and beyond!!!",
			"About that tenner you owe me ......", "Take your protein pills and put your helmet on.",
			"Again ,we will not be referring to you \nas 'The Space Cowboy' when you return...",
			"So long and thanks for all the fish",
			"Since we decided a few weeks ago to adopt the leaf \nas legal tender, we have, of course, all become immensely rich.",
			"Don’t Panic",
			"This planet has — or rather had — a problem, which was this: \nmost of the people living on it were unhappy for pretty much all of the time.",
			"Any man who can hitch the length and breadth of the galaxy, \nrough it, slum it, struggle against terrible odds, win through, \nand still know where his towel is is clearly a man to be reckoned with.",
			"Help me Obi Wan you're my only hope ... ",
			"Maybe we should finally tell them the big secret, \nthat all the chimps we sent into space came back super intelligent.",
			"When I found out about this, I went through a range of emotions. \nFirst I was nervous, then anxious, then wary, then apprehensive, then... kind of sleepy, \nthen worried, and then concerned. \nBut now I realize that being a spaceman is something you have to do.",
			"Space; It seems to go on and on forever...\nbut then you get to the end and a gorilla starts throwing barrels at you.",
			"Now, Now. There will be plenty of time to discuss your objections \nwhen and if you return.",
			"I got your distress call and I came as soon as I wanted to." };
	/**
	 * Predetermined array of people that are randomly selected when a player lands
	 * on this square
	 */
	private static final String[] PEOPLE = { "Mum", "Dad", "Mystery caller", "No Caller ID", "CAPCOM", "Granny",
			"Brother", "Best Friend", "Flight Controller", "Teammate ", "Arch Nemesis" };

	// Instance variables.

	private String[] people;
	private String[] messages;

	/**
	 * Default constructor for Nothing Square.
	 */
	public NothingSquare() {

	}

	/**
	 * Constructor with arguments from abstract superclass Square instance variables
	 * people and messages set here to constant arrays
	 * 
	 * @param squareName
	 * @param positionOnBoard
	 * @param isPurchasable
	 */
	public NothingSquare(String squareName, int positionOnBoard, boolean isPurchasable)
			throws IndexOutOfBoundsException {
		super(squareName, positionOnBoard, isPurchasable);
		this.people = PEOPLE;
		this.messages = MESSAGES;

	}

	/**
	 * Get method that returns the person sending a message when a player lands on
	 * the Nothing Square.
	 * 
	 * @param PersonIndex index of message array to be returned
	 * @return String person based on a random number
	 * @throws IndexOutOfBoundsException if the number generated is outside of the
	 *                                   bounds of the people array
	 */
	public String getPerson(int personIndex) throws IndexOutOfBoundsException {
		if (personIndex >= 0 && personIndex < people.length) {
			return people[personIndex];
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds for people");
		}
	}

	/**
	 * Get method that returns the message when a player lands on the Nothing
	 * Square.
	 * 
	 * @param MessageIndex index of message array to be returned
	 * @return String message based on a random number
	 * @throws IndexOutOfBoundsException if the number generated is outside of the
	 *                                   bounds of the messages array
	 */
	public String getMessage(int messageIndex) throws IndexOutOfBoundsException {
		if (messageIndex >= 0 && messageIndex < messages.length) {
			return messages[messageIndex];
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds for messages");
		}
	}

	/**
	 * Overrides and implements the method from Square class. The method outputs the
	 * square name where the player landed.
	 * 
	 * @return a message related to the square the player has landed on
	 */
	@Override
	public String landedOn(Player currentPlayer) {
		String message;
		message = currentPlayer.getPlayerName() + " has landed on " + this.getSquareName();
		System.out.println("*************************************************************");
		System.out.println(message);
		return message;
	}

	/**
	 * Overrides and implements the method from IUtility class. Outputs the message
	 * from home when a player lands on Nothing Square.
	 */
	@Override
	public int doAction() {
		Random random = new Random();
		System.out.println("Now communicating with your home planet");
		System.out.println("New message incoming from : " + getPerson(random.nextInt(people.length)));
		System.out.println("------ \n");
		System.out.println("Message : ");
		System.out.println(getMessage(random.nextInt(messages.length)));
		System.out.println("*************************************************************");
		return DO_ACTION_RETURN;
	}

	/**
	 * Override of the to String method
	 */

	@Override
	public String toString() {
		return "NothingSquare [getSquareName()=" + getSquareName() + ", getPositionOnBoard()=" + getPositionOnBoard()
				+ ", isPurchasable()=" + isPurchasable() + "]";
	}

	/**
	 * Overrides and implements the method from IDisplay interface.
	 */
	@Override
	public void displayAll() {
		System.out.println("Name : " + super.getSquareName());
		System.out.println("------------------------------------------------------");
		System.out.println("Position on the board : " + (super.getPositionOnBoard() + 1));

	}

}