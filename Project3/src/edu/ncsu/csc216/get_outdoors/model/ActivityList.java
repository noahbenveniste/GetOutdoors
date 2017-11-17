package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * 
 * @author Noah Benveniste
 */
public class ActivityList extends Observable implements Observer, Tabular {
	
	/** */
	private String name;
	/** */
	private int numActivities;
	/** */
	private SortedArrayList<Activity> activities;
	
	/**
	 * 
	 */
	public ActivityList() {
		this.name = "Activities";
		activities = new SortedArrayList<Activity>();
		numActivities = 0;
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
		// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @param b
	 * @param n
	 * @return
	 */
	public boolean addActivity(String name, String description, boolean needSnow, int snowBoundary) {
		String ID = "act-" + numActivities;
		//Throws an exception if any of the inputs are invalid
		Activity a = new Activity(ID, name, description, needSnow, snowBoundary);
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
			//Return false if the activity is already in the list
			return false;
		}
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public Activity getActivityAt(int index) {
		if (index < 0 || index >= activities.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} else {
			return this.activities.get(index);
		}
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return this.activities.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.activities.isEmpty();
	}
	
	/**
	 * 
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] arr = new Object[this.activities.size()][5];
		for (int i = 0; i < this.activities.size(); i++) {
			Activity current = this.activities.get(i);
			arr[i][0] = current.getActivityID();
			arr[i][1] = current.getName();
			arr[i][2] = current.getDescription();
			arr[i][3] = current.snowNeeded();
			arr[i][4] = current.getSnowBoundary();
		}
		return arr;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int indexOfID(String id) {
		int index = -1;
		for (int i = 0; i < this.activities.size(); i++) {
			if ((activities.get(i).getActivityID()).equals(id)) {
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * 
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		//If the passed activity (observable o) is contained in the activities list, notify observers
		Activity a = (Activity) o;
		if (this.activities.contains(a)) {
			notifyObservers(arg);
		}
	}
	
}
