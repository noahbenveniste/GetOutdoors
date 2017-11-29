package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * A TrailList maintains a collection of Trails in the system, providing
 *   functionality adding and removing trails, notifying Trails when a 
 *   Park's snowChange field is updated, and returning display arrays 
 *   for the Trails in a Park. TrailList has a 1-1 correspondence with
 *   the Park class, as every list of Trails must have a Park in which
 *   they're located.
 * TrailList is both an Observer and an Observable type. The TrailList's
 *   Park and Trails are observed, and GetOutdoorsManager observers TrailList.
 *   
 * @author demills
 */
public class TrailList extends Observable implements Observer, Tabular {

	/** The TrailList's ID, equivalent to its Park's ID. */
	private String trailListID;
	/** The number of trails added, used to assign the next Trail's ID. */
	private int numTrails;
	/** The observed Park in which the Trails are located. */
	private Park park;
	/** The collection of observed Trails in the Park. */
	private SortedArrayList<Trail> trails;
	
	/**
	 * Constructs a TraiList, taking the Park in which they are located as
	 *   a parameter. The fields are initialized, with the TrailList's 
	 *   ID set to the Park's ID. Observers are then notified.
	 *   
	 * @param park the Park in which the Trails belong.
	 * @throws IllegalArgumentException if the Park parameter is null.
	 */
	public TrailList(Park park) {
		if (park == null) {
			throw new IllegalArgumentException("Park cannot be null");
		}

		trailListID = park.getParkID();
		trails = new SortedArrayList<Trail>();
		numTrails = 0;

		// Adds this TrailList as an Observer of the Park.
		this.park = park;
		park.addObserver(this);


		// Notifies TrailList's Observer, GetOutDoorsManager, of the change.
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Returns the name of the TrailList's Park.
	 * 
	 * @return returns the TrailList's Park's name.
	 */
	public String getParkName() {
		return park.getName();
	}
	
	/**
	 * Adds a new Trail to the TrailList, taking all parameters required for
	 *   creating a Trail, minus the Trail's ID, which is generated as the
	 *   Park's ID, appended by "-<numTrails>". For example the second Trail
	 *   added to a list with Park ID, "park-5", would be given the ID, "park-5-1".
	 *   
	 * @param name the Trail's name
	 * @param activities a list of Activities permitted on the Trail.
	 * @param maintenance whether the Trail is closed for maintenance; true if so.
	 * @param snow the current depth of snow on the trail.
	 * @param distance the length of the Trail.
	 * @param difficulty the Difficulty value associated with a Trail.
	 * @throws IllegalArgumentException if a Trail couldn't be constructed from the parameters.
	 * @return true if the Trail addition was succesful; false otherwise.
	 */
	public boolean addTrail(String name, SortedArrayList<Activity> activities, boolean maintenance, 
							double snow, double distance, Difficulty difficulty) 
	{
		// The new Trail's ID.
		String id = trailListID + "-" + numTrails;

		Trail newTrail; 
		try {
			newTrail = new Trail(id, name, activities, maintenance, snow, distance, difficulty);
		} catch (IllegalArgumentException e) {
			return false;
		}

		// Adds this TrailList as the Trail's Observer.
		newTrail.addObserver(this);

		// Adds the Trail to the list and incremenets the numTrails.
		trails.add(newTrail);
		numTrails++;

		// Notifies Traillist's Observer, GetOutDoorsManager, of change.
		setChanged();
		notifyObservers(this);

		return true;
	}
	
	/**
	 * Returns the Trail at the specified index in the list.
	 * 
	 * @param index the index at which to return a Trail.
	 * @return returns the Trail at the passed index.
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   or equal to the size of the list.
	 */
	public Trail getTrailAt(int index) {
		if (index < 0 || index >= trails.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} 
		return trails.get(index);
	}
	
	/**
	 * Removes and returns the Trail at the specified index.
	 * 
	 * @param index the index at which to remove a Trail.
	 * @return returns the Trail at the passed index.
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   or equal to the size of the list.
	 */
	public Trail removeTrail(int index) {
		if (index < 0 || index >= trails.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} 

		Trail removedTrail = trails.remove(index);
		setChanged();
		notifyObservers(this);
		return removedTrail;
	}
	
	/**
	 * Removes and returns the Trail matching the passed Trail ID, if one exists.
	 * 
	 * @param id the ID of the Trail to remove.
	 * @return returns the Trail matching the passed ID.
	 * @throws IndexOutOfBoundsException if no Trail matches the passed ID.
	 */
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

	/**
	 * Returns the number of Trails in the list.
	 * 
	 * @return returns the TrailList's size.
	 */
	public int size() {
		return trails.size();
	}
	
	/**
	 * Returns whether the TrailList is empty.
	 * 
	 * @return returns true if TrailList is empty; false if not.
	 */
	public boolean isEmpty() {
		return trails.isEmpty();
	}
	
	/**
	 * Returns an integer hash of the TrailList's "numTrails", "park", 
	 *   "trailListID" and "trails" fields.
	 *   
	 * @return returns a hash of the TrailList's fields.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((park == null) ? 0 : park.hashCode());

		return result;
	}

	/**
	 * Returns whether an Object equals this TrailList. An Object is considered 
	 *   equal if it is a TrailList with identically named Parks.
	 * 
	 * @return returns true if the parameter equals this TraiList, false otherwise.
	 * @param object the Object to compare for equality to this one.
	 */
	public boolean equals(Object object) {
		if (object == null || !(object instanceof TrailList)) {
			return false;
		}

		TrailList otherTrailList = (TrailList) object;
		if (otherTrailList.hashCode() == this.hashCode()) {
			return true;
		}

		return false;
	}


	/**
	 * Returns a 2D array of Objects where each row represents a Trail in the
	 *   list. The columns of each row are the fields of a Trail as such:
	 *   
	 *     0 = String - Trail ID
	 *     1 = String - Trail name
	 *     2 = boolean - "closedForMaintenance"
	 *     3 = double - depth of snow on Trail.
	 *     4 = double - length of trail ("distance")
	 *     5 = Difficulty - difficulty of Trail
	 *     6 = SortedArrayList<Activity> - Activities permitted on Trail.
	 * 
	 * @return returns a 2D array representing the Trails in the list.
	 */
	@Override
	public Object[][] get2DArray() {

		// Populating the 2D array with Trail info.
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
	
	/**
	 * Returns a 2D array of Objects where each row represents a Trail in the
	 *   list that permits and is open for the specified Activity. The columns 
	 *   of each row are the fields of a Trail as such:
	 *   
	 *     0 = String - Trail ID
	 *     1 = String - Trail name
	 *     2 = boolean - "closedForMaintenance"
	 *     3 = double - depth of snow on Trail.
	 *     4 = double - length of trail ("distance")
	 *     5 = Difficulty - difficulty of Trail
	 *     6 = SortedArrayList<Activity> - Activities permitted on Trail.
	 * 
	 * @return returns a 2D array representing the Trails in the list that 
	 *         allow the specified Activity.
	 * @throws IllegalArgumentException if the parameter is null.
	 * @param activity the Activity by which to filter the TrailList. 
	 */
	public Object[][] get2DArray(Activity activity) {
		if (activity == null) {
			throw new IllegalArgumentException("Activity cannot be null");
		}
		
		// Counting number of open trails to intialize trailListArray's length.
		int openCount = 0;
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (current.trailOpen(activity)) {
				openCount++;
			}
		}

		// Populating the 2D array with Trail info.
		Object[][] trailListArray = new Object[openCount][7];
		int index = 0;
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (current.trailOpen(activity)) {
				trailListArray[index][0] = current.getTrailID();
				trailListArray[index][1] = current.getTrailName();
				trailListArray[index][2] = current.closedForMaintenance();
				trailListArray[index][3] = current.getSnow();
				trailListArray[index][4] = current.getDistance();
				trailListArray[index][5] = current.getDifficulty();
				trailListArray[index][6] = current.getActivities();
				index++;
			}
		}

		return trailListArray;
	}

	/**
	 * Returns a 2D array of Objects where each row represents a Trail in the
	 *   list that is not closed for maintenance. The columns of each row are 
	 *   the fields of a Trail as such:
	 *   
	 *     0 = String - Trail ID
	 *     1 = String - Trail name
	 *     2 = boolean - "closedForMaintenance"
	 *     3 = double - depth of snow on Trail.
	 *     4 = double - length of trail ("distance")
	 *     5 = Difficulty - difficulty of Trail
	 *     6 = SortedArrayList<Activity> - Activities permitted on Trail.
	 * 
	 * @return returns a 2D array representing the Trails not closed for maintenance. 
	 */
	public Object[][] get2DArrayNoMaintenance() {
		// Counting number of open trails to intialize trailListArray's length.
		int openCount = 0;
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (!current.closedForMaintenance()) {
				openCount++;
			}
		}

		// Populating 2D array with Trail info.
		Object[][] trailListArray = new Object[openCount][7];
		int index = 0;
		for (int i = 0; i < trails.size(); i++) {
			Trail current = trails.get(i);
			if (!current.closedForMaintenance()) {
				trailListArray[index][0] = current.getTrailID();
				trailListArray[index][1] = current.getTrailName();
				trailListArray[index][2] = current.closedForMaintenance();
				trailListArray[index][3] = current.getSnow();
				trailListArray[index][4] = current.getDistance();
				trailListArray[index][5] = current.getDifficulty();
				trailListArray[index][6] = current.getActivities();
				index++;
			}
		}

		return trailListArray;
	}
	
	/**
	 * Returns the index of the Trail in the list matching the specified 
	 *   Trail ID. If no such Trail exists, -1 is returned.
	 * 
	 * @param trailID ID of the Trail to search for.
	 * @return returns the index of the specified Trail; -1 if it doesn't exist.
	 */
	public int indexOfID(String trailID) {
		int index = -1;
		for (int i = 0; i < trails.size(); i++) {
			if (trails.get(i).getTrailID().equals(trailID)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * If one of TrailList's Observed objects, Trail or Park, is updated, this 
	 *   method is called. 
	 * For an updated Trail, TrailList's Observer, GetOutdoorsManager, is notified 
	 *   if the passed Observable Trail ("obs") is contained in the list.
	 * For an updated Park (i.e. one with a fresh snowfall or melt), each Trail 
	 *   in this TrailList is passed the latest Park's "snowChange" value. Then
	 *   TrailList's Observer, GetOutdoorsManager, is notified of the change.
	 *   
	 * @param obs the Observable Object, either a Park or Trail.
	 * @param arg the Observer of this TrailList
	 */
	@Override
	public void update(Observable obs, Object arg) {
		//If the passed park (observable o) is contained in the activities list, notify observers
		if (obs instanceof Trail) {
			Trail observedTrail = (Trail) obs;
			if (trails.contains(observedTrail)) {
				setChanged();
				notifyObservers(arg);
			}
		}
		if (obs instanceof Park) {
			Park observedPark = (Park) obs;
			//Check if the Park is actually associated with this TrailList
			if (observedPark.equals(this.park)) {
				for (int i = 0; i < trails.size(); i++) {
					trails.get(i).addSnow(observedPark.getSnowChange());
				}
				setChanged();
				notifyObservers(arg);
			}
		}
	}
	
}