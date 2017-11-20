package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.util.SortedLinkedList;

/**
 * 
 * @author Noah Benveniste
 */
public class ParkList extends Observable implements Observer, Tabular {

	/** */
	private String parkListName;
	/** */
	private int numParks;
	/** */
	private SortedLinkedList<Park> parks;
	
	/**
	 * 
	 */
	public ParkList() {
		parkListName = "Parks";
		numParks = 0;
		parks = new SortedLinkedList<Park>();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return parkListName;
	}
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param snowChange
	 * @return
	 */
	public boolean addPark(String name, String description, double snowChange) {
		String ID = "park-" + numParks;
		//Throws an exception if any of the inputs are invalid
		Park p = new Park(ID, name, description, snowChange);
		//Only increment the park id counter if the park is successfully added to the list
		//i.e. it is not a duplicate of one in the list i.e. it doesn't have the same name as one 
		//already in the list
		if (parks.add(p)) {
			//Increment the number of parks
			numParks++;
			
			//Marks the Observable as changed
			setChanged(); 
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			// The current instance is passed in except in specific instance listed in the detailed method descriptions, below.
			
			//Add this object to the created activity as an observer
			p.addObserver(this);
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
	public Park getParkAt(int index) {
		if (index < 0 || index >= parks.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} else {
			return parks.get(index);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return parks.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return parks.isEmpty();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int indexOfID(String id) {
		int index = -1;
		for (int i = 0; i < parks.size(); i++) {
			if ((parks.get(i).getParkID()).equals(id)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * 
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] arr = new Object[parks.size()][4];
		for (int i = 0; i < parks.size(); i++) {
			Park current = parks.get(i);
			arr[i][0] = current.getParkID();
			arr[i][1] = current.getName();
			arr[i][2] = current.getDescription();
			arr[i][3] = current.getSnowChange();
		}
		return arr;
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		//If the passed park (observable o) is contained in the activities list, notify observers
		Park p = (Park) o;
		if (parks.contains(p)) {
			notifyObservers(arg);
		}
	}
}