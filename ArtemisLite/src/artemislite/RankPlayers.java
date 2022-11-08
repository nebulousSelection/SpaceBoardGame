/**
 * 
 */
package artemislite;

import java.util.Comparator;

/**
 * Custom comparator ranks players based on the number of properties they own.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */
public class RankPlayers implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		return o2.getPlayerOwnedSquares().size() - o1.getPlayerOwnedSquares().size();
	}

}
