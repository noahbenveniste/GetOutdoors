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
		
		constructed = true;
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
	}
	
	/**
	 * 
	 * 
	 * @param activities
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
	 * @param snow
	 */
	public void setSnow(double snow) {
		
	}
	
	/**
	 * 
	 * @param snow
	 * @return
	 */
	public double addSnow(double snow) {
		return 0.0;
	}
	
	/**
	 * 
	 * @param distance
	 */
	public void setDistance(double distance) {
		
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
		return false;
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public boolean allow(Activity a) {
		return false;
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	public int compareTo(Trail t) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trail other = (Trail) obj;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		if (closedForMaintenance != other.closedForMaintenance)
			return false;
		if (constructed != other.constructed)
			return false;
		if (difficulty != other.difficulty)
			return false;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (Double.doubleToLongBits(snow) != Double.doubleToLongBits(other.snow))
			return false;
		if (trailID == null) {
			if (other.trailID != null)
				return false;
		} else if (!trailID.equals(other.trailID))
			return false;
		if (trailName == null) {
			if (other.trailName != null)
				return false;
		} else if (!trailName.equals(other.trailName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trail [trailID=" + trailID + ", trailName=" + trailName + ", closedForMaintenance="
				+ closedForMaintenance + ", snow=" + snow + ", distance=" + distance + ", activities=" + activities
				+ ", difficulty=" + difficulty + ", constructed=" + constructed + "]";
	}

}
