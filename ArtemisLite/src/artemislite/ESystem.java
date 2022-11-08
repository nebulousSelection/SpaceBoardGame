/**
 * 
 */
package artemislite;

/**
 * 
 * This sets up each system with a system name and a multiplier which is used to
 * calculate the rent and cost to buy.
 * 
 * @author Adam Sloan
 * @author Anna Graham
 * @author Charmange Casimero
 * @author Jonathan Foster
 */

public enum ESystem {

	SYSTEM_ONE("Orion", 1.0, 2), SYSTEM_TWO("Space Launch System", 1.25, 3), SYSTEM_THREE("Lunar Gateway", 1.6, 3),
	SYSTEM_FOUR("Human Landing System", 2.0, 2);

	private final String systemName;

	private final double SystemMultiplier;
	private final int elementSquaresInSystem;

	ESystem(String systemName, double systemMultiplier, int elementSquaresInSystem) {
		this.systemName = systemName;
		this.SystemMultiplier = systemMultiplier;
		this.elementSquaresInSystem = elementSquaresInSystem;

	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @return the systemMultiplier
	 */
	public double getSystemMultiplier() {
		return SystemMultiplier;
	}

	/**
	 * @return the elementSquaresInSystem
	 */
	public int getElementSquaresInSystem() {
		return elementSquaresInSystem;
	}

}
