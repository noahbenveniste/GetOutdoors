package edu.ncsu.csc216.get_outdoors.ui;

import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Simple class for transferring data within the GUI via events.
 * 
 * @author Jessica Young Schmidt
 */
public class TrailData implements Data {
    /** Trail's ID */
    private String trailID;
    /** Trail's name */
    private String trailName;
    /** Maintenance currently being performed on trail? */
    private boolean closedForMaintenance;
    /** Snow currently on trail */
    private double snow;
    /** Trail distance */
    private double distance;
    /** Trail difficulty */
    private Difficulty difficulty;
    /** Trail activities */
    private SortedArrayList<Activity> activities;

    /**
     * Creates a TrailData object from all fields.
     * 
     * @param trailID the Trail's ID
     * @param trailName the Trail's name
     * @param closedForMaintenance whether or not the Trail is closed for maintenance
     * @param snow the amount of snow on the Trail
     * @param distance the length of the Trail
     * @param difficulty the Trail's difficulty level
     * @param activities the available activities for the Trail
     */
    public TrailData(String trailID, String trailName, boolean closedForMaintenance, double snow,
            double distance, Difficulty difficulty, SortedArrayList<Activity> activities) {
        super();
        this.trailID = trailID;
        this.trailName = trailName;
        this.closedForMaintenance = closedForMaintenance;
        this.snow = snow;
        this.distance = distance;
        this.difficulty = difficulty;
        this.activities = activities;
    }

    /**
     * Returns trail ID
     * 
     * @return the trailID
     */
    public String getTrailID() {
        return trailID;
    }

    /**
     * Returns trail's name
     * 
     * @return the trailName
     */
    public String getTrailName() {
        return trailName;
    }

    /**
     * Returns whether Trail is close for maintenance
     * 
     * @return the closedForMaintenance
     */
    public boolean isClosedForMaintenance() {
        return closedForMaintenance;
    }

    /**
     * Returns snow on trail
     * 
     * @return the snow
     */
    public double getSnow() {
        return snow;
    }

    /**
     * Returns trail distance
     * 
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns trail difficulty
     * 
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns trail activities
     * 
     * @return the activities
     */
    public SortedArrayList<Activity> getActivities() {
        return activities;
    }

    /**
     * Returns the Data as an Object array for use in the GUI.
     * 
     * @return the Data as an Object array.
     */
    @Override
    public Object[] getDataArray() {
        Object[] data = new Object[7];
        data[0] = this.trailID;
        data[1] = this.trailName;
        data[2] = this.closedForMaintenance;
        data[3] = this.snow;
        data[4] = this.distance;
        data[5] = this.difficulty;
        data[6] = this.activities;
        return data;
    }
}
