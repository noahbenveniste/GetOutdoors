package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.util.SortedLinkedList;

/**
 * A container object for Park objects. Uses a SortedLinkedList as an underlying data
 *   structure. Has a composition relationship with Park objects in the context of 
 *   GetOutdoors, as Park objects are only constructed through ParkList.addPark().
 * ParkList is both OBSERVABLE by GetOutdoorsManager and an OBSERVER of Park.
 * ParkList also implements the Tabular interface to output its data as an array
 *   for easy display via the UI classes.
 * 
 * @author Noah Benveniste
 */
public class ParkList extends Observable implements Observer, Tabular {

	/** The name of the ParkList */
	private String parkListName;
	/** The number of Parks in the ParkList */
	private int numParks;
	/** The underlying list data structure */
	private SortedLinkedList<Park> parks;
	
	/**
	 * Constructs a ParkList with a default name of "Parks". Initializes the numParks field
	 * and constructs an empty SortedLinkedList of Park objects for the underlying list data structure.
	 * Observers of ParkList are notified of construction.
	 */
	public ParkList() {
		parkListName = "Parks";
		numParks = 0;
		parks = new SortedLinkedList<Park>();
		setChanged(); //Marks the Observable as changed
		notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
	}
	
	/**
	 * Gets the name of the ParkList.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return parkListName;
	}
	
	/**
	 * Adds a Park to the ParkList if it is not already on the list. Auto generates a unique ID.
	 * Observers of ParkList are notified of the change.
	 * 
	 * @param name the name of the Park
	 * @param description the description for the Park
	 * @param snowChange the amount of snow that fell/melted
	 * @return true if the Park was added successfully, false otherwise i.e. it was already in the list
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
			
			//Add this object to the created activity as an observer
			p.addObserver(this);
			
			//Return true if the Park was successfully added
			return true;
		} else {
			
			//Marks the Observable as changed
			setChanged(); 
			notifyObservers(this); //Sends a message to any Observer classes that the object has changed.
			
			//Return false if the Park is already in the list
			return false;
		}
	}
	
	/**
	 * Gets a Park at a specified index
	 * 
	 * @param index the index of the Park to find in the ParkList
	 * @return the Park at the specified index
	 * @throws IndexOutOfBoundsException if the specified index is not between 0 and size - 1
	 */
	public Park getParkAt(int index) {
		if (index < 0 || index >= parks.size()) {
			throw new IndexOutOfBoundsException("Index is outside of the acceptable range");
		} else {
			return parks.get(index);
		}
	}
	
	/**
	 * Gets the number of Parks in the list
	 * 
	 * @return the size of the ParkList
	 */
	public int size() {
		return parks.size();
	}
	
	/**
	 * Checks if the ParkList is empty i.e. contains no Parks
	 * 
	 * @return true if the ParkList contains no Parks, false otherwise
	 */
	public boolean isEmpty() {
		return parks.isEmpty();
	}
	
	/**
	 * Finds the index of a Park by its ID
	 * 
	 * @param id the ID of the Park
	 * @return the index of the Park corresponding to the ID in the list, 
	 *         -1 if the list doesn't contain a Park corresponding to the ID
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
	 * Generates a 2D array containing the data for each Park in the list.
	 *   Each row in the array corresponds to an individual Park. The first
	 *   column stores the ID, the second the name, the third the description
	 *   and the fourth the snow change.
	 *
	 * @return a 2D object Array containing data for all Parks in the ParkList
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
	 * Notifies observers of ParkList when a change is made
	 * 
	 * @param o the observable object, in this case a Park
	 * @param arg, the observer, in this case GetOutdoorsManager
	 */
	@Override
	public void update(Observable o, Object arg) {
		//If the passed park (observable o) is contained in the activities list, notify observers
		if (o instanceof Park && parks.contains((Park) o)) {
			setChanged();
			notifyObservers(arg);
		}
	}
	
}