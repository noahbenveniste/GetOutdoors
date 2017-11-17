package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

/**
 * Trail is an OBSERVABLE class and is observed by TrailList.
 * @author Noah Benveniste
 */
public class Trail extends Observable {
	
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
	

	public Trail() {
		
	}

}
