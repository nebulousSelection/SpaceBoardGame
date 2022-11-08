package artemislite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Rank Player Class 
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */

class RankPlayersTest {

	Player p1, p2, p3, p4;
	ElementSquare e;
	List<Player> players;
	List<Player> expectedOrder;
	
	@BeforeEach
	void setUp() throws Exception {
		players = new ArrayList<Player>();
		expectedOrder = new ArrayList<Player>();
		
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		p4 = new Player();
		
		p1.addPlayerOwnedSquare(e);
		p1.addPlayerOwnedSquare(e);
		p1.addPlayerOwnedSquare(e);
		
		p2.addPlayerOwnedSquare(e);
		p2.addPlayerOwnedSquare(e);
		
		p3.addPlayerOwnedSquare(e);
		p3.addPlayerOwnedSquare(e);
		
		p4.addPlayerOwnedSquare(e);
	}

	@Test
	final void testEqual() {
		players.add(p2);
		players.add(p3);
		
		expectedOrder.add(p2);
		expectedOrder.add(p3);
		
		assertEquals(players, expectedOrder);
		
		Collections.sort(players, new RankPlayers());
		
		assertEquals(players, expectedOrder);
	}
	
	@Test
	final void testNotEqualUnsorted() {
		players.add(p2);
		players.add(p4);
		players.add(p1);
		players.add(p3);
		
		expectedOrder.add(p1);
		expectedOrder.add(p2);
		expectedOrder.add(p3);
		expectedOrder.add(p4);
		
		assertNotEquals(players, expectedOrder);
		
		Collections.sort(players, new RankPlayers());
		
		assertEquals(players, expectedOrder);	
	}
	
	@Test
	final void testNotEqualSorted() {
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		
		expectedOrder.add(p1);
		expectedOrder.add(p2);
		expectedOrder.add(p3);
		expectedOrder.add(p4);
		
		assertEquals(players, expectedOrder);
		
		Collections.sort(players, new RankPlayers());
		
		assertEquals(players, expectedOrder);	
	}
}
