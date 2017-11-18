package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * 
 * @author demills
 */
public class TrailList extends Observable implements Observer, Tabular {

	private String trailListID;
	private int numTrails;
	private Park park;
	private SortedArrayList<Trail> trails;
	
	public TrailList(Park park) {
		if (park == null) {
			throw new IllegalArgumentException();
		}
		this.park = park;

		// This should be able to be removed. It shouldn't be possible 
		// for a Park to ever return a null parkID value.
		if (park.getParkID() == null) {
			throw new IllegalArgumentException("Park parmeter has null ID.");
		}
		trailListID = park.getParkID();
		trails = new SortedArrayList<Trail>();
		numTrails = 0;

		setChanged();
		notifyObservers(this);
	}

	public String getParkName() {
		return park.getName();
	}
	

	public boolean addTrail(String name, SortedArrayList<Activity> activities, boolean maintenance, 
							double snow, double distance, Difficulty difficulty) 
	{
		String id = trailListID + "-" + numTrails;
		Trail newTrail; 
		try {
			newTrail = new Trail(id, name, activities, maintenance, snow, distance, difficulty);
		} catch (IllegalArgumentException e) {
			return false;
		}

		trails.add(newTrail);
		numTrails++;

		setChanged();
		notifyObservers(this);

		return true;
	}
	
	public Trail getTrailAt(int index) {
		if (index < 0 || index >= trails.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} 
		return trails.get(index);
	}
	
	public Trail removeTrail(int index) {
		if (index < 0 || index >= trails.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} 

		Trail removedTrail = trails.remove(index);
		setChanged();
		notifyObservers(this);
		return removedTrail;
	}
	
	public Trail removeTrail(String id) {
		String currentTrailID;
		for (int i = 0; i < trails.size(); i++) {
			currentTrailID = trails.get(i).getTrailID();
			if (currentTrailID.equals(id)) {
				return removeTrail(i);
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public int size() {
		return trails.size();
	}
	
	public boolean isEmpty() {
		return trails.isEmpty();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numTrails;
		result = prime * result + ((park == null) ? 0 : park.hashCode());
		result = prime * result + ((trailListID == null) ? 0 : trailListID.hashCode());
		result = prime * result + ((trails == null) ? 0 : trails.hashCode());
		return result;
	}

	public boolean equals(Object object) {
		if (object == null || !(object instanceof TrailList)) {
			return false;
		}

		TrailList otherTrailList = (TrailList) object;
		String otherParkName = otherTrailList.getParkName();

		if (otherParkName.equals(park.getName())) {
			return true;
		}

		return false;
	}


	/**
	 * 
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] trailListArray = new Object[trails.size()][7];
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			trailListArray[i][0] = current.getTrailID();
			trailListArray[i][1] = current.getTrailName();
			trailListArray[i][2] = current.closedForMaintenance();
			trailListArray[i][3] = current.getSnow();
			trailListArray[i][4] = current.getDistance();
			trailListArray[i][5] = current.getDifficulty();
			trailListArray[i][6] = current.getActivities();
		}
		return trailListArray;
	}
	
	public Object[][] get2DArray(Activity activity) {
		// This may not be needed, but Trail.trailOpen() allows for 
		// null activities, so a NullPointerException will be thrown
		// if this is removed without updating Trail.trailOpen().
		if (activity == null) {
			throw new IllegalArgumentException();
		}
		
		// Counting number of open trails to intialize trailListArray's length.
		int openCount = 0;
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (current.trailOpen(activity)) {
				openCount++;
			}
		}

		Object[][] trailListArray = new Object[openCount][7];
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (current.trailOpen(activity)) {
				trailListArray[i][0] = current.getTrailID();
				trailListArray[i][1] = current.getTrailName();
				trailListArray[i][2] = current.closedForMaintenance();
				trailListArray[i][3] = current.getSnow();
				trailListArray[i][4] = current.getDistance();
				trailListArray[i][5] = current.getDifficulty();
				trailListArray[i][6] = current.getActivities();
			}
		}
		return trailListArray;
	}

	public Object[][] get2DArrayNoMaintenance() {
		// Counting number of open trails to intialize trailListArray's length.
		int openCount = 0;
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (!current.closedForMaintenance()) {
				openCount++;
			}
		}

		Object[][] trailListArray = new Object[openCount][7];
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (!current.closedForMaintenance()) {
				trailListArray[i][0] = current.getTrailID();
				trailListArray[i][1] = current.getTrailName();
				trailListArray[i][2] = current.closedForMaintenance();
				trailListArray[i][3] = current.getSnow();
				trailListArray[i][4] = current.getDistance();
				trailListArray[i][5] = current.getDifficulty();
				trailListArray[i][6] = current.getActivities();
			}
		}
		return trailListArray;
	}

	
	public int indexOfID(String id) {
		int index = -1;
		for (int i = 0; i < trails.size(); i++) {
			if ((trails.get(i).getTrailID()).equals(id)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public void update(Observable obs, Object arg) {
		//If the passed park (observable o) is contained in the activities list, notify observers
		if (obs instanceof Trail) {
			Trail observedTrail = (Trail) obs;
			if (trails.contains(observedTrail)) {
				notifyObservers(arg);
			}
		}
		if (obs instanceof Park) {
			Park observedPark = (Park) obs;
			for (int i = 0; i < trails.size(); i++) {
				trails.get(i).addSnow(observedPark.getSnowChanged());
				notifyObservers(arg);
			}
		}
	}
}