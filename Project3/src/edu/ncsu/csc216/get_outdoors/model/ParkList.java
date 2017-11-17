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
	private String name;
	/** */
	private int numParks;
	/** */
	private SortedLinkedList<Park> parks;
	
	/**
	 * 
	 */
	public ParkList() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param snowChange
	 * @return
	 */
	public boolean addPark(String name, String description, double snowChange) {
		return false;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public Park getParkAt(int index) {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return this.parks.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.parks.isEmpty();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int indexOfID(String id) {
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public Object[][] get2DArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

}
