package edu.ncsu.csc216.get_outdoors.ui;

/**
 * Simple class for transferring data within the GUI via events.
 * 
 * @author Jessica Young Schmidt
 */
public class ActivityData implements Data {
    /** Activity's id */
    private String activityID;
    /** Activity's name */
    private String name;
    /** Activity's description */
    private String description;
    /** Is snow needed for the activity? */
    private boolean needSnow;
    /**
     * Boundary for when is possible or not possible for activity. If needSnow is
     * true, it is the minimum number of inches needed in order to do activity. If
     * needSnow is false, it is the maximum number of inches of snow before no
     * longer able to do activity.
     */
    private int snowBoundary;

    /**
     * Creates a ActivityData object from all fields.
     * 
     * @param activityID activity's ID
     * @param name activity's name
     * @param description activity's description
     * @param needSnow activity need snow?
     * @param snowBoundary activity snow boundary
     */
    public ActivityData(String activityID, String name, String description, boolean needSnow,
            int snowBoundary) {
        this.activityID = activityID;
        this.name = name;
        this.description = description;
        this.needSnow = needSnow;
        this.snowBoundary = snowBoundary;
    }

    /**
     * Returns activity ID
     * 
     * @return the activityID
     */
    public String getActivityID() {
        return activityID;
    }

    /**
     * Returns the activity name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns activity description
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether snow is needed
     * 
     * @return the needSnow
     */
    public boolean snowNeeded() {
        return needSnow;
    }

    /**
     * Returns the snow boundary
     * 
     * @return the snowBoundary
     */
    public int getSnowBoundary() {
        return snowBoundary;
    }

    /**
     * Returns the Data as an Object array for use in the GUI.
     * 
     * @return the Data as an Object array.
     */
    @Override
    public Object[] getDataArray() {
        Object[] data = new Object[5];
        data[0] = activityID;
        data[1] = name;
        data[2] = description;
        data[3] = needSnow;
        data[4] = snowBoundary;
        return data;
    }

}
