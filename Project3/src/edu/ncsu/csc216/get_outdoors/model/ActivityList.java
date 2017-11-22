package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * 
 * ActivityList is both OBSERVABLE by GetOutdoorsManager and an OBSERVER of Activity.
 * ActivityList also implements the Tabular interface to output its data as an array
 *   for easy display via the UI classes.
 * 
 * @author Noah Benveniste
 */
public class ActivityList extends Observable implements Observer, Tabular {
	
	/** The name of the list */
	private String name;
	/** The number of activities in the list */
	private int numActivities;
	/** The underlying data structure that the list class utilizes */
	private SortedArrayList<Activity> activities;
	
	/**
	 * Constructs an empty ActivityList with default name "Activities".
	 * Observers of ActivityList are notified of construction.
	 */
	public ActivityList() {
		name = "Activities";
		activities = new SortedArrayList<Activity>();
		numActivities = 0;
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
	}
	
	/**
	 * Gets the name of the ActivityList.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds an Activity to the list if it is not already on the list. Auto generates a unique ID for the Activity.
	 * Observers of ActivityList are notified of the change
	 * 
	 * @param name the name of the Activity
	 * @param description the description of the Activity
	 * @param needSnow whether or not snow is needed for the activity
	 * @param snowBoundary the snow boundary for the Activity
	 * @return true if the Activity was added successfully, false otherwise i.e. the Activity was already on the list
	 */
	public boolean addActivity(String name, String description, boolean needSnow, int snowBoundary) {
		String id = "act-" + numActivities;
		//Throws an exception if any of the inputs are invalid
		Activity a = new Activity(id, name, description, needSnow, snowBoundary);
		//Only increment the activity id counter if the activity is successfully added to the list
		//i.e. it is not a duplicate of one in the list i.e. it doesn't have the same name as one 
		//already in the list
		if (activities.add(a)) {
			//Increment the number of activities
			numActivities++;
			
			//Marks the Observable as changed
			setChanged(); 
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
			
			//Add this object to the created activity as an observer
			a.addObserver(this);
			return true;
		} else {
			//Marks the Observable as changed
			setChanged(); 
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
			
			//Return false if the activity is already in the list
			return false;
		}
	}
	
	/**
	 * Gets an Activity at a specified index in the list
	 * 
	 * @param index the index of the Activity to get
	 * @return the Activity at the specified index
	 * @throws IndexOutOfBoundsException if the specified index is not between 0 and size - 1
	 */
	public Activity getActivityAt(int index) {
		if (index < 0 || index >= activities.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} else {
			return activities.get(index);
		}
	}

	/**
	 * Gets the number of Activities in the list
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return activities.size();
	}
	
	/**
	 * Checks if the list is empty i.e. there are no Activities in the list
	 * 
	 * @return true if there are no Activities in the list, false otherwise
	 */
	public boolean isEmpty() {
		return activities.isEmpty();
	}
	
	/**
	 * Generates a 2D array containing the data for all Activities in the list.
	 * Each row corresponds to an individual Activity. The first column corresponds to ID,
	 * the second name, the third description, the fourth whether or not snow is needed, and
	 * the fifth the snow boundary.
	 * 
	 * @return a 2D object array containing the data for all Activities in the list
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] arr = new Object[activities.size()][5];
		for (int i = 0; i < activities.size(); i++) {
			Activity current = activities.get(i);
			arr[i][0] = current.getActivityID();
			arr[i][1] = current.getName();
			arr[i][2] = current.getDescription();
			arr[i][3] = current.snowNeeded();
			arr[i][4] = current.getSnowBoundary();
		}
		return arr;
	}

	/**
	 * Gets the index of an Activity specified by its ID in the list.
	 * 
	 * @param id the ID of the Activity to find
	 * @return the index if an Activity with the ID is found, or -1 if no Activity
	 * 		   with the specified ID exists
	 */
	public int indexOfID(String id) {
		int index = -1;
		for (int i = 0; i < activities.size(); i++) {
			if ((activities.get(i).getActivityID()).equals(id)) {
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Notifies observers of ActivityList when a change is made
	 * 
	 * @param o an observable, in this case an Activity
	 * @param arg an observer, in this case GetOutdoorsManager
	 */
	@Override
	public void update(Observable o, Object arg) {
		//If the passed activity (observable o) is contained in the activities list, notify observers
		if (o instanceof Activity && activities.contains((Activity) o)) {
			setChanged();
			notifyObservers(arg);
		}
	}
	
}
