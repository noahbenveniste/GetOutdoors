package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

/**
 * 
 * @author Noah Benveniste
 */
public class Activity extends Observable implements Comparable<Activity> {

	/** */
	private String activityID;
	/** */
	private String name;
	/** */
	private String description;
	/** */
	private boolean needSnow;
	/** */
	private int snowBoundary;
	/** */
	private boolean constructed;
	
	/**
	 * 
	 * 
	 * @param activityID
	 * @param name
	 * @param description
	 * @param needSnow
	 * @param snowBoundary
	 */
	public Activity(String activityID, String name, String description, boolean needSnow, int snowBoundary) {
		constructed = false;
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
		// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
	}
	
	/**
	 * 
	 * @param id
	 */
	private void setActivityID(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Activity ID cannot be null");
		} else {
			id = id.trim();
			if (id.equals("")) {
				throw new IllegalArgumentException("ID is either an empty string or only contains whitespace");
			} else {
				this.activityID = id;
			}
		}
	}
	
	/**
	 * 
	 * @param name
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
	 * 
	 * @param d
	 */
	public void setDescription(String d) {
		if (d == null) {
			throw new IllegalArgumentException("Activity description cannot be null");
		} else {
			d = d.trim();
			if (d.equals("")) {
				throw new IllegalArgumentException("Description is either an empty string or only contains whitespace");
			} else {
				this.description = d;
			}
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setNeedSnow(boolean b) {
		this.needSnow = b;
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * @param s
	 */
	public void setSnowBoundary(int s) {
		if (s < 0) {
			throw new IllegalArgumentException("Activity snow boundary cannot be negative");
		} else {
			this.snowBoundary = s;
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public String getActivityID() {
		return activityID;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean snowNeeded() {
		return needSnow;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public int getSnowBoundary() {
		return snowBoundary;
	}

	/**
	 * Compares two Activities based on their name field
	 * 
	 * @param o the other Activity to compare to
	 * 
	 * @return a negative integer if this Activity's name lexicographically precedes o's name,
	 * a positive integer for the opposite case, or zero if the Activities have the same name.
	 */
	@Override
	public int compareTo(Activity o) {
		//Delegate to String's compareTo()
		return (this.getName().compareTo(o.getName()));
	}
	
	/**
	 * 
	 * 
	 * @return
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
		result = prime * result + (constructed ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (needSnow ? 1231 : 1237);
		result = prime * result + snowBoundary;
		return result;
	}

	/**
	 * Checks if two Activity objects are equal by comparing their name fields.
	 * 
	 * @return true if the Activities have the same name, false if they don't, or
	 * the passed object is not an Activity.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Activity) {
			Activity a = (Activity) obj;
			if (this.name.equals(a.getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
