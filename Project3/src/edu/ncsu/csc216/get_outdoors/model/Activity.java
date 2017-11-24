package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

/**
 * An object representing an Activity that can be performed on a Trail in a Park.
 *   Activities have a composition relationship with ActivityList within the context 
 *   of GetOutdoors, as they are constructed through ActivityList. This helps keep
 *   track of Activity IDs as Activities are created.
 * Activity is an OBSERVABLE class and is observed by ActivityList.
 * 
 * @author Noah Benveniste
 */
public class Activity extends Observable implements Comparable<Activity> {

	/** The Activity's identifier string; generated by ActivityList */
	private String activityID;
	/** The name of the Activity */
	private String name;
	/** Description for the Activity */
	private String description;
	/** Whether or not snow is needed for the Activity */
	private boolean needSnow;
	/** 
	 * The minimum amount of snow required for the Activity if needSnow is true, 
	 * or the maximum allowable amount of snow if needSnow is false 
	 */
	private int snowBoundary;
	/** A flag used to check if the object was constructed; 
        ensures that the update method is only called once during construction */
	private boolean constructed;
	
	/**
	 * Constructs an Activity object given an identifier, a name, a description, a boolean
	 *   indicating whether or not snow is needed for the Activity, and a snow boundary. 
	 * Observers of Activity are notified of successful construction.
	 * 
	 * @param activityID the identifier for the Activity
	 * @param name the name of the Activity
	 * @param description the description of the Activity
	 * @param needSnow whether or not snow is needed for the Activity
	 * @param snowBoundary the minimum amount of snow required for the Activity (if needSnow is true)
	 *        or the maximum allowable amount of snow (if needSnow is false)
	 */
	public Activity(String activityID, String name, String description, boolean needSnow, int snowBoundary) {
		//Flag the prevents setChanged() and notifyObservers() from being called when the setter methods
		//are called during construction of the object.
		constructed = false;
		
		//Set fields
		setActivityID(activityID);
		setName(name);
		setDescription(description);
		setNeedSnow(needSnow);
		setSnowBoundary(snowBoundary);
		
		//If all fields are set successfully with no exceptions thrown, notify the observers.
		//The constructed boolean flag ensures that the observers are only notified when setters
		//are called after the object is constructed.
		constructed = true;
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
	}
	
	/**
	 * Sets the ID for the Activity and trims any excess leading or trailing whitespace.
	 * Observers of Activity are notified of this change if this method is called subsequently to construction.
	 * 
	 * @param activityID the ID to set
	 * @throws IllegalArgumentException if the ID is null, an empty string, or all whitespace.
	 */
	private void setActivityID(String activityID) {
		if (activityID == null) {
			throw new IllegalArgumentException("Activity ID cannot be null");
		} else {
			activityID = activityID.trim();
			if (activityID.equals("")) {
				throw new IllegalArgumentException("ID is either an empty string or only contains whitespace");
			} else {
				this.activityID = activityID;
			}
		}
	}
	
	/**
	 * Sets the name of the Activity and trims any excess leading or trailing whitespace.
	 * Observers of Activity are notified of this change if this method is called subsequently to construction.
	 * 
	 * @param name the name to set.
	 * @throws IllegalArgumentException if the name is null, an empty string, or a string containing only whitespace.
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Activity name cannot be null");
		} else {
			name = name.trim();
			if (name.equals("")) {
				throw new IllegalArgumentException("Name is either an empty string or only contains whitespace");
			} else {
				this.name = name;
			}
		}
	}
	
	/**
	 * Sets the description of the Activity and trims any excess leading or trailing whitespace.
	 * Observers of Activity are notified of this change if this method is called subsequently to construction.
	 * 
	 * @param description the description to set for the Activity.
	 * @throws IllegalArgumentException if the description is null, an empty string, or contains only whitespace.
	 */
	public void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("Activity description cannot be null");
		} else {
			description = description.trim();
			if (description.equals("")) {
				throw new IllegalArgumentException("Description is either an empty string or only contains whitespace");
			} else {
				this.description = description;
			}
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Sets whether or not snow is required for the Activity. This field determines the meaning of the snow boundary
	 *   field. If this field is true, snow boundary is the minimum amount of snow required for the Activity; if it
	 *   is false, snow boundary is the maximum allowable amount of snow for the Activity.
	 * Observers of Activity are notified of this change if this method is called subsequently to construction.
	 * 
	 * @param needSnow true if snow is required for the Activity, false otherwise.
	 */
	public void setNeedSnow(boolean needSnow) {
		this.needSnow = needSnow;
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Sets the snow boundary for the Activity. If snow is required for the Activity, this is the minimum amount
	 *   of snow necessary. If snow is not required, this is the maximum amount of snow allowable.
	 * Observers of Activity are notified of this change if this method is called subsequently to construction.
	 * 
	 * @param snowBoundary the snow boundary value to set.
	 * @throws IllegalArgumentException if the snow boundary to set is negative
	 */
	public void setSnowBoundary(int snowBoundary) {
		if (snowBoundary < 0) {
			throw new IllegalArgumentException("Activity snow boundary cannot be negative");
		} else {
			this.snowBoundary = snowBoundary;
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		}
	}
	
	/**
	 * Gets the Activity's ID.
	 * 
	 * @return the ID
	 */
	public String getActivityID() {
		return activityID;
	}
	
	/**
	 * Gets the name of the Activity.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the description of the Activity.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Checks if snow is required for the Activity.
	 * 
	 * @return true if snow is needed, false if it is not.
	 */
	public boolean snowNeeded() {
		return needSnow;
	}
	
	/**
	 * Gets the snow boundary for the Activity. If snow is required for the Activity, this is
	 *   the minimum amount of snow required to perform the Activity. If snow is not needed,
	 *   this is the maximum amount of snow allowable before the Activity cannot be performed.
	 * 
	 * @return the snow boundary
	 */
	public int getSnowBoundary() {
		return snowBoundary;
	}

	/**
	 * Compares two Activities based on their name field using lexicographical ordering.
	 * 
	 * @param o the other Activity to compare to
	 * 
	 * @return a negative integer if this Activity's name lexicographically precedes o's name,
	 *         a positive integer for the opposite case, or zero if the Activities have the same name.
	 */
	@Override
	public int compareTo(Activity o) {
		//Delegate to String's compareTo()
		return name.compareTo(o.getName());
	}
	
	/**
	 * Generates a tab separated list of the Activity's fields and returns as a String.
	 * 
	 * @return the String representation of the Activity.
	 */
	public String toString() {
		return "" + getName() + "\t" + getDescription() + "\t" + snowNeeded() + "\t" + getSnowBoundary();
	}
	
	/**
	 * Generates a unique hash for the object. Equal objects will hash to the same value.
	 * 
	 * @return the hash value for the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityID == null) ? 0 : activityID.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (needSnow ? 1231 : 1237);
		result = prime * result + snowBoundary;
		if (result == -886540813) {
			toString();
		}
		return result;
	}

	/**
	 * Checks if two Activity objects are equal by comparing their name fields.
	 * 
	 * @return true if the Activities have the same name, false if they don't, or
	 *         the passed object is not an Activity.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Activity) {
			Activity a = (Activity) obj;
			return name.equals(a.getName());
		} else {
			return false;
		}
	}
}