package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

/**
 * A Park in the GetOutdoors system represents a collection of trails for a
 *   single park in the system. This is modelled by having each TrailList 
 *   maintain the Park at which the Trails are located. Each Park maintains 
 *   the change in snow depth, in inches, from the most recent snowfall of melt.
 * Park is an Observable type, and notifies its oberservers on construction 
 *   and when a new snowfall or melt is recorded via setSnowChange().
 *   
 * @author demills
 */
public class Park extends Observable {

	/** The Park's unique, auto-generated ID. */
	private String parkID;
	/** The Park's name. */
	private String name;
	/** A brief description of the Park. */
	private String description;
	/** The number of inches of snow that has most recently fallen or melted. */
	private double snowChange;
	
	/**
	 * Constructs a Park with the passed id, name, and description. The parameters 
	 *   must not be null, empty, or contain only whitespace. Construction is 
	 *   delegated to the Park(String,String,String,double) constructor.
	 *   Any leading or trailing whitespace is trimmed from valid Strings.
	 * Observers are notified of the change at the end of construction.
	 * 
	 * @param parkID the park's ID. 
	 * @param name the park's name.
	 * @param description a brief description of the park.
	 */
	public Park(String parkID, String name, String description) {
		this(parkID, name, description, 0);
	}

	/**
	 * Constructs a Park with the passed id, name, description, and snowfall delta. 
	 *   The parameters must not be null, empty, or contain whitespace. Construction 
	 *   is delegated to the Park(String,String,String,double) constructor.
	 *   Any leading or trailing whitespace is trimmed from valid Strings.
	 * Observers of Park are notified at the end of construction.
	 * 
	 * @param parkID the park's ID. 
	 * @param name the park's name.
	 * @param description a brief description of the park.
	 * @param snowChange the value, in inches, of the most recent snowfall or melt.
	 * @throws IllegalArgumentException if any of the String parameters are 
	 *                                  null, empty, or contain whitespace.
	 */
	public Park(String parkID, String name, String description, double snowChange) {
		if (parkID == null) {
			throw new IllegalArgumentException("Park ID cannot be null, empty, or contain whitespace.");
		} else {
			parkID = parkID.trim();
			if (parkID.equals("")) {
				throw new IllegalArgumentException("Park ID cannot be null, empty, or contain whitespace.");
			} else {
				this.parkID = parkID;
			}
		}
		if (name == null) {
			throw new IllegalArgumentException("Park ID cannot be null, empty, or contain whitespace.");
		} else {
			name = name.trim();
			if (name.equals("")) {
				throw new IllegalArgumentException("Park ID cannot be null, empty, or contain whitespace.");
			} else {
				this.name = name;
			}
		}
		if (description == null) {
			throw new IllegalArgumentException("Park ID cannot be null, empty, or contain whitespace.");
		} else {
			description = description.trim();
			if (description.equals("")) {
				throw new IllegalArgumentException("Park ID cannot be null, empty, or contain whitespace.");
			} else {
				this.description = description;
			}
		}

		// The Observers are notified within setSnowChange().
		setSnowChange(snowChange);
	}
	
	/**
	 * Returns the Park's name.
	 * 
	 * @return returns the Park's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the Park's ID.
	 * 
	 * @return returns the Park's ID.
	 */
	public String getParkID() {
		return parkID;
	}
	
	/**
	 * Returns the Park's description.
	 * 
	 * @return returns the Park's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the value of the most recent snowfall or melt in inches. A 
	 *   negative value indicates that snow has melted and a positive 
	 *   value indicates that snow has fallen.
	 * 
	 * @return the most recent change in snow depth, in inches.
	 */
	public double getSnowChanged() {
		return snowChange;
	}
	
	/**
	 * Sets the value of the most recent snowfall or melt in inches. A 
	 *   negative value should be passed if snow has melted and a 
	 *   positive value if snow has fallen.
	 *   
	 * @param snowChange the number of inches of snow that have fallen or melted.
	 */
	public void setSnowChange(double snowChange) {
		this.snowChange = snowChange;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Compares this Park to another based on names. The actual comparison is 
	 *   delegated to String.compareTo(). A negative value indicates that this
	 *   Park precedes the passed Park in the natural Park ordering, and vice
	 *   versa. A value of 0 indicates that these Park's have identical names.
	 *   
	 * @param otherPark the Park to compare this Park to.
	 * @return returns a negative int if this Park precedes the passed Park,
	 *         a positive int if this Park succeeds the passed Park, and
	 *         zero if this Park occupies the same position as the passed.
	 */
	public int compareTo(Park otherPark) {
		return name.compareTo(otherPark.getName());
	}
	
	/**
	 * Returns a String representation of the Park formatted as a tab-delimited
	 *   String of the Park's name, description, and snowChange.
	 *   
	 * For example, for a Park with the following fields, 
	 *    - name = "Pullen Park"
	 *    - description = "A historic, pleasant local park."
	 *    - snowChange = 0
	 *    
	 *   toString() would return a String formatted as: 
	 *   "Pullen Park\tA historic, pleasant local park.\t0"
	 *   
	 * @return returns a tab-delimited String of the Park's name, description, and snowchange.
	 */
	public String toString() {
		return name + "\t" + description + "\t" + snowChange;
	}
}