package edu.ncsu.csc216.get_outdoors.ui;

/**
 * Simple class for transferring data within the GUI via events.
 * 
 * @author Jessica Young Schmidt
 */
public class ParkData implements Data {
    /** Park's ID */
    private String parkID;
    /** Park's name */
    private String name;
    /** Park's description */
    private String description;
    /** Snow change in park */
    private double snowChange;

    /**
     * Creates a ParkData object from all fields.
     * 
     * @param parkID park's ID
     * @param name park's name
     * @param description park's description
     * @param snowChange snow change in park
     */
    public ParkData(String parkID, String name, String description, double snowChange) {
        super();
        this.parkID = parkID;
        this.name = name;
        this.description = description;
        this.snowChange = snowChange;
    }

    /**
     * Returns park's ID
     * 
     * @return the parkID
     */
    public String getParkID() {
        return parkID;
    }

    /**
     * Returns park's name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns park's description
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns change in snow in park
     * 
     * @return the snowChange
     */
    public double getSnowChange() {
        return snowChange;
    }

    /**
     * Returns the Data as an Object array for use in the GUI.
     * 
     * @return the Data as an Object array.
     */
    @Override
    public Object[] getDataArray() {

        Object[] data = new Object[4];
        data[0] = parkID;
        data[1] = name;
        data[2] = description;
        data[3] = snowChange;
        return data;
    }

}
