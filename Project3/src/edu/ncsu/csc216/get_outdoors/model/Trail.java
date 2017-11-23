package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * An object representing a Trail contained by a Park. Trails have a composition
 *   relationship with TrailList i.e. in the context of the GetOutdoors application,
 *   Trail objects are only constructed through the TrailList class. This helps to
 *   keep track of Trail IDs upon Trail construction. 
 * Trail is an OBSERVABLE class and is observed by TrailList. Trails are also 
 *    comparable by their name field.
 * 
 * @author Noah Benveniste
 */
public class Trail extends Observable implements Comparable<Trail> {
	
	/** The Trail's ID; set by TrailList */
	private String trailID;
	/** The Trail's name */
	private String trailName;
	/** The Trail's maintenance status */
	private boolean closedForMaintenance;
	/** The amount of snow on the Trail */
	private double snow;
	/** The length of the Trail/distance it covers */
	private double distance;
	/** The available activities that can be performed on the Trail */
	private SortedArrayList<Activity> activities;
	/** An enumeration representing the level of difficulty of the Trail */
	private Difficulty difficulty;
	/** A flag used to check if the object was constructed; 
	    ensures that the update method is only called once during construction */
	private boolean constructed;
	
	/**
	 * Constructs a Trail object given an identifier, a name, a list of available activities for that Trail,
	 *   the Trail's maintenance status (true if the Trail is closed for maintenance), the amount of snow on
	 *   the Trail, the length of the Trail, and the Trail's difficulty (see edu.ncsu.csc216.get_outdoors.enums.Difficulty)
	 * Observers of Trail are notified of construction.
	 * 
	 * @param id the Trail's unique identifer. Within the context of GetOutdoors, this field is generated by TrailList
	 * @param name the Trail's name; used to sort/compare Trails
	 * @param activities a SortedArrayList of available activities for the Trail
	 * @param closedForMaintenance the Trail's maintenance status (true if closed, false if open)
	 * @param snow the amount of snow on the Trail
	 * @param distance the length of the Trail
	 * @param diff the Trail's level of difficulty
	 */
	public Trail(String id, String name, SortedArrayList<Activity> activities, boolean closedForMaintenance, 
			     double snow, double distance, Difficulty diff) {
		//Indicates
		constructed = false;

		//Checking "id" param and setting Trail ID if valid.
		if (id == null) {
			throw new IllegalArgumentException("Trail ID cannot be null");
		} else {
			id = id.trim();
			if (id.equals("")) {
				throw new IllegalArgumentException("Trail ID is either empty or all whitespace");
			} else {
				this.trailID = id;
			}
		}

		//Checking "name" param and setting Trail ID if valid.
		if (name == null) {
			throw new IllegalArgumentException("Trail name cannot be null");
		} else {
			name = name.trim();
			if (name.equals("")) {
				throw new IllegalArgumentException("Trail name is either empty or all whitespace");
			} else {
				this.trailName = name;
			}
		}

		//Set fields; boolean flag "constructed" ensures that setChanged() and notifyObservers() are only called at
		//the end of successful construction
		setActivities(activities);
		setTrailMaintenance(closedForMaintenance);
		setSnow(snow);
		setDistance(distance);

		//Check for a null difficulty input
		if (diff == null) {
			throw new IllegalArgumentException("Difficulty cannot be null");
		} else {
			this.difficulty = diff;
		}

		//Indicates that the object has been constructed; allows the below methods to be called upon subsequent calls to setter methods
		constructed = true;
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
	}
	
	/**
	 * Sets the Activities available to be performed on the Trail.
	 * Observers of Trail are notified of this change if the Trail has been constructed.
	 * 
	 * @param activities a SortedArrayList of Activities available to be performed on the Trail
	 * @throws IllegalArgumentException if activities is null
	 */
	public void setActivities(SortedArrayList<Activity> activities) {
		//Check for null input
		if (activities == null) {
			throw new IllegalArgumentException("List of activities cannot be null");
		} else {
			this.activities = activities;
		}
		
		//Ensures that these methods are only called if setActivities() is called after construction
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Sets the Trail's maintenance status.
	 * Observers of Trail are notified of the change if this method is called subsequently to construction.
	 * 
	 * @param closedForMaintenance true if the Trail is closed for maintenance, false otherwise
	 */
	public void setTrailMaintenance(boolean closedForMaintenance) {
		//Set the field
		this.closedForMaintenance = closedForMaintenance;
		
		//Ensures that these methods are only called if this is called after construction
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Sets the amount of snow on the Trail. If the passed argument is negative, the amount is set to 0.
	 * Observers of Trail are notified of the change if this method is called subsequently to construction.
	 * 
	 * @param snow the amount of snow on the Trail.
	 */
	public void setSnow(double snow) {
		//Set the value for the snow field. Set to 0 if the passed argument is negative.
		if (snow < 0) {
			this.snow = 0;
		} else {
			this.snow = snow;
		}
		
		//Ensures that these methods are only called if this is called after construction
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Adds a specified amount of snow to the current level of snow on the Trail. If the sum would result
	 * in a negative value, the amount is set to 0.
	 * Observers of Trail are notified of the change if this method is called subsequently to construction.
	 * 
	 * @param snow the amount of snow to add to the Trail
	 * @return the new total amount of snow on the Trail
	 */
	public double addSnow(double snow) {
		//Generate the new amount of snow
		double sum = this.snow + snow;
		
		//If the added snowfall would result in a negative sum i.e. snow loss is greater
		//than the current amount of snow, set the snow amount to zero
		if (sum < 0) {
			sum = 0;
		}
		this.snow = sum;
		
		//Ensures that these methods are only called if this is called after construction
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
		
		//Return the updated snow amount
		return this.snow;
	}
	
	/**
	 * Sets the length of the Trail.
	 * Observers of Trail are notified of the change if this method is called subsequently to construction.
	 * 
	 * @param distance the length of the Trail
	 * @throws IllegalArgumentException if the passed argument is negative.
	 */
	public void setDistance(double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException("Trail distance cannot be negative");
		} else {
			this.distance = distance;
		}
		
		//Ensures that these methods are only called if this is called after construction
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Gets the Trail's identifier.
	 * 
	 * @return the Trail ID
	 */
	public String getTrailID() {
		return this.trailID;
	}
	
	/**
	 * Gets the Trail's name
	 * 
	 * @return the Trail name
	 */
	public String getTrailName() {
		return this.trailName;
	}
	
	/**
	 * Gets the Activities available to perform on the Trail
	 * 
	 * @return a SortedArrayList containing the Activities available to perform on the Trail
	 */
	public SortedArrayList<Activity> getActivities() {
		return this.activities;
	}
	
	/**
	 * Checks if the Trail is closed for maintenance.
	 * 
	 * @return true if the Trail is closed, false otherwise.
	 */
	public boolean closedForMaintenance() {
		return this.closedForMaintenance;
	}
	
	/**
	 * Gets the current amount of snow on the Trail.
	 * 
	 * @return the amount of snow on the Trail.
	 */
	public double getSnow() {
		return this.snow;
	}
	
	/**
	 * Gets the length of the Trail.
	 * 
	 * @return the length of the Trail.
	 */
	public double getDistance() {
		return this.distance;
	}
	
	/**
	 * Gets the Trail's difficulty enumeration.
	 * 
	 * @return the Trail's difficulty.
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 * Checks if the Trail is open for a specified Activity, based on maintenance
	 *   status, whether or not snow is needed for the Activity, and the amount
	 *   of snow currently on the Trail.
	 * 
	 * @param a the Activity to check for
	 * @return false if the Trail is closed, the Activity is not allowed, or there is
	 *         not enough/too much snow on the Trail for the Activity (depending on 
	 *         whether or not snow is required for the Activity). Returns true otherwise.
	 */
	public boolean trailOpen(Activity a) {
		//Check if the Trail is open
		if (closedForMaintenance) {
			return false;
		//Check if the Activity is allowed on the Trail
		} else if (!allow(a)) {
			return false;
		} else {
			//Check that there is an appropriate amount of snow for the Activity
			int snowBoundary = a.getSnowBoundary();
			if (a.snowNeeded()) {
				return (snowBoundary <= this.getSnow());
			} else {
				return (snowBoundary >= this.getSnow());
			}
		}
	}

	/**
	 * Checks if a specified Activity is on the list of allowable activities for the Trail.
	 * 
	 * @param a the Activity to check for.
	 * @return true if the Activity is allowed on the Trail, false otherwise.
	 */
	public boolean allow(Activity a) {
		return activities.contains(a);
	}

	/**
	 * Compares two Trails based on their name fields using lexicographical ordering.
	 * 
	 * @param t the Trail to compare to.
	 * @return a negative integer if this Trail lexicographically precedes the passed Trail,
	 *         a positive integer if this Trail lexicographically follows the passed Trail,
	 *         or 0 if the passed Trail has the same name as this Trail.
	 */
	public int compareTo(Trail t) {
		return this.getTrailName().compareTo(t.getTrailName());
	}

	/**
	 * Generates a unique hash value for the Trail object.
	 * 
	 * @return the hash value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activities == null) ? 0 : activities.hashCode());
		result = prime * result + (closedForMaintenance ? 1231 : 1237);
		result = prime * result + (constructed ? 1231 : 1237);
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(snow);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((trailID == null) ? 0 : trailID.hashCode());
		result = prime * result + ((trailName == null) ? 0 : trailName.hashCode());
		return result;
	}

	/**
	 * Checks if two Trail objects are equal. Compares the two Trails' name fields
	 * using lexicographical ordering.
	 * 
	 * @param obj the object to compare to
	 * @return true if the Trails have the same name, false if they have different names
	 * 		   or the input is not of type Trail.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Trail) {
			Trail t = (Trail) obj;
			return this.trailName.equals(t.getTrailName());
		} else {
			return false;
		}
	}

	/**
	 * Generates a tab separated list of the Trail's fields and returns as a String.
	 * 
	 * @return the String representation of the Trail.
	 */
	@Override
	public String toString() {
		String activitiesString = activities.get(0).getName();
		for (int i = 1; i < activities.size(); i++) {
			activitiesString += "\t" + activities.get(i).getName();
		}
		return getTrailName() + "\t" + closedForMaintenance() + "\t" + getSnow() + "\t" + 
		       getDistance() + "\t" + getDifficulty().toString() + "\t" + activitiesString;
	}

}
