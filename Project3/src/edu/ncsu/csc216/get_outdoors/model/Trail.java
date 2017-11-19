package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * 
 * Trail is an OBSERVABLE class and is observed by TrailList.
 * 
 * @author Noah Benveniste
 */
public class Trail extends Observable implements Comparable<Trail> {
	
	/** */
	private String trailID;
	/** */
	private String trailName;
	/** */
	private boolean closedForMaintenance;
	/** */
	private double snow;
	/** */
	private double distance;
	/** */
	private SortedArrayList<Activity> activities;
	/** */
	private Difficulty difficulty;
	/** */
	private boolean constructed;
	
	/**
	 * 
	 * 
	 * @param id
	 * @param name
	 * @param activities
	 * @param maintenance
	 * @param snow
	 * @param distance
	 * @param d
	 */
	public Trail(String id, String name, SortedArrayList<Activity> activities, boolean closedForMaintenance, double snow, double distance, Difficulty d) {
		constructed = false;
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
		setActivities(activities);
		setTrailMaintenance(closedForMaintenance);
		setSnow(snow);
		setDistance(distance);
		if (d == null) {
			throw new IllegalArgumentException("Difficulty cannot be null");
		} else {
			this.difficulty = d;
		}
		constructed = true;
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
	}
	
	/**
	 * 
	 * 
	 * @param activities
	 * @throws
	 */
	public void setActivities(SortedArrayList<Activity> activities) {
		if (activities == null) {
			throw new IllegalArgumentException("List of activities cannot be null");
		} else {
			this.activities = activities;
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * @param closedForMaintenance
	 */
	public void setTrailMaintenance(boolean closedForMaintenance) {
		this.closedForMaintenance = closedForMaintenance;
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * 
	 * @param snow
	 */
	public void setSnow(double snow) {
		if (snow < 0) {
			this.snow = 0;
		} else {
			this.snow = snow;
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * @param snow
	 * @return
	 */
	public double addSnow(double snow) {
		double sum = this.snow + snow;
		//If the added snowfall would result in a negative sum i.e. snow loss is greater
		//than the current amount of snow, set the snow amount to zero
		if (sum < 0) {
			sum = 0;
		}
		this.snow = sum;
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
		//Return the updated snow amount
		return this.snow;
	}
	
	/**
	 * 
	 * @param distance
	 */
	public void setDistance(double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException("Trail distance cannot be negative");
		} else {
			this.distance = distance;
		}
		if (constructed) {
			setChanged(); //Marks the Observable as changed
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTrailID() {
		return this.trailID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTrailName() {
		return this.trailName;
	}
	
	/**
	 * 
	 * @return
	 */
	public SortedArrayList<Activity> getActivities() {
		return this.activities;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean closedForMaintenance() {
		return this.closedForMaintenance;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getSnow() {
		return this.snow;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getDistance() {
		return this.distance;
	}
	
	/**
	 * 
	 * @return
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public boolean trailOpen(Activity a) {
		if (closedForMaintenance) {
			return false;
		} else {
			int snowBoundary = a.getSnowBoundary();
			if (a.snowNeeded()) {
				return (snowBoundary >= this.getSnow());
			} else {
				return (snowBoundary <= this.getSnow());
			}
		}
	}

	/**
	 * Checks if a specified activity is on the list of allowable activities.
	 * 
	 * @param a
	 * @return
	 */
	public boolean allow(Activity a) {
		return activities.contains(a);
	}

	/**
	 * 
	 * 
	 * @param t
	 * @return
	 */
	public int compareTo(Trail t) {
		return this.getTrailName().compareTo(t.getTrailName());
	}

	/**
	 * 
	 * 
	 * @return
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
	 * 
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Trail) {
			Trail t = (Trail) obj;
			if (this.trailName.equals(t.getTrailName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		String a = activities.get(0).toString();
		for (int i = 1; i < activities.size(); i++) {
			a += "\t" + activities.get(i).toString();
		}
		return "" + getTrailName() + "\t" + closedForMaintenance() + "\t" + getSnow() + "\t" + getDistance() + "\t" + getDifficulty().toString() + a;
	}

}
