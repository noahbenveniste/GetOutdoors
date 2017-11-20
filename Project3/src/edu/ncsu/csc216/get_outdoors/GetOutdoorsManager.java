package edu.ncsu.csc216.get_outdoors;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.*;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * The main class for the GetOutdoors tools. Holds references to the top-level
 * data structures and acts as the controller between the model and the GUI
 * presentation view.
 * 
 * @author Jessica Young Schmidt
 * @author Your Name Here
 */
public class GetOutdoorsManager extends Observable implements Observer {
    /** Increment for increasing the capacity of the array of TrailList */
    private static final int RESIZE = 3;

    // States for file processing
    /** Start State */
    private final State START_STATE = new StartState();
    /** State when processing activities */
    private final State ACTIVITY_STATE = new ActivityState();
    /** State when processing parks */
    public final State PARK_STATE = new ParkState();
    /** State when processing trails */
    public final State TRAIL_STATE = new TrailState();
    /** Heading for activities in files */
    private static final String ACTIVITY_HEADER = "Activities";
    /** Heading for parks in files */
    private static final String PARK_HEADER = "Parks";
    /** Heading for trails in files */
    private static final String TRAIL_HEADER = "Trails";
    /** Delimiter in files */
    public static final String DELIMITER = "\t";

    /** State for reading from file */
    private State state;

    /** A collection of TrailList */
    private TrailList[] trailLists;
    /** Number of TrailList */
    private int numLists;
    /** A collection of Activities */
    private ActivityList activities;
    /** A collection of Parks */
    private ParkList parks;
    /** Filename for saving the information */
    private String filename;
    /** True if manager has changed since last save */
    private boolean changed;

    /**
     * Constructs the GetOutdoorsManager, performing the following actions: 
     * - A list of TraiLists is initialized,
     * - A single ActivityList is created with this instance as its Observer.
     * - A single ParkList is created with this instance as its Observer.
     * - The boolean "changed" is set to false, indicating no change since last save.
     */
    public GetOutdoorsManager() {
    	trailLists = new TrailList[3];
    	changed = false;
    	numLists = 0;

    	activities = new ActivityList();
    	parks = new ParkList();

    	activities.addObserver(this);
    	parks.addObserver(this);
    }
    
    /**
     * Returns whether the manager has changed since the last save.
     * True if it has, false if not.
     * 
     * @return returns true if manager has changed since last save; false otherwise.
     */
    public boolean isChanged() {
    	return changed;
    }
    
    /**
     * Sets the value of the "changed" field to the passed value, indicating
     *   whether or not the manager has changed since the last save.
     * 
     * @param changed whether the manager has changed since the last save.
     */
    public void setChanged(boolean changed) {
    	this.changed = changed;
    }
    
    /**
     * Returns the filename of the file that the manager is saving its data to.
     * 
     * @return filename of the manager's save file.
     */
    public String getFileName() {
    	return filename;
    }
    
    /**
     * Sets the save file's filename to the passed String, if valid.
     * A valid filename must be non-empty, non-null, and contain at 
     *   least one non-whitespace character. For valid Strings, leading 
     *   and trailing whitespace will be trimmed.
     * If the argument is invalid, an IllegalArgumentException is thrown.
     *   
     * @param filename the new save file's filename. 
     * @throws IllegalArgumentException if the filename is invalid.
     */
    public void setFileName(String filename) {
    	if (filename == null || filename.trim().equals("")) {
    		throw new IllegalArgumentException();
    	}
    	this.filename = filename.trim();
    }

    /**
     * Saves the GetOutdoorsManager to file.
     * 
     * @param filename filename to save GetOutdoorsManager information to.
     * @return true is saved successfully
     */
    public boolean saveDataFile(String filename) {
        if (filename == null || filename.trim().equals("")) {
            System.err.println("Invalid filename: " + filename);
            return false;
        } else {
            try {
                PrintStream out = new PrintStream(new File(filename.trim()));
                out.println("# Activities\n");
                for (int i = 0; i < activities.size(); i++) {
                    out.println("*\t" + clean(activities.getActivityAt(i).toString()));
                }
                out.println("\n# Parks\n");
                for (int i = 0; i < parks.size(); i++) {
                    out.println("*\t" + clean(parks.getParkAt(i).toString()));
                }
                out.println("\n# Trails\n");
                for (int i = 0; i < numLists; i++) {
                    TrailList trails = trailLists[i];
                    for (int j = 0; j < trails.size(); j++) {
                        out.println("*\t" + clean(trails.getParkName()) + "\t"
                                + clean(trails.getTrailAt(j).toString()));
                    }
                }
                changed = false;
                out.close();
                return true;
            } catch (IOException e) {
                System.err.println("An error occurred while saving file " + filename);
                e.printStackTrace(System.err);
                return false;
            }
        }
    }
    
    /**
     * Returns the number of TrailLists currently in the system.
     * 
     * @return returns the manager's number of TrailLists.
     */
    public int getNumTrailsLists() {
    	return numLists;
    }
    
    /**
     * Returns the TrailList at the specified index in the manager's 
     *   array of TrailLists.
     * 
     * @param index the index from which to return a TrailList.
     */
    public TrailList getTrailList(int index) {
    	if (index < 0 || index >= trailLists.length) {
    		throw new IndexOutOfBoundsException();
    	}
    	return trailLists[index];
    }
    
    /**
     * Returns an array of TrailLists in the system.
     * 
     * @return returns an array of TrailLists in the system.
     */
    public TrailList[] getTrailLists() {
    	return trailLists;
    }
    
    /**
     * Returns the system's ActivityList.
     * 
     * @return the system's ActivityList
     */
    public ActivityList getActivities() {
    	return activities;
    }
    
    /**
     * Returns the system's ParkList.
     * 
     * @return returns the system's ParkList.
     */
    public ParkList getParks() {
    	return parks;
    }

    /**
     * Adds the passed park's TrailList to the system's array of TrailLists.
     * This manager instance is added as an Observer of the added TrailList.
     * 
     * @param park 
     * @return
     */
    public int addTrailList(Park park) {

    	TrailList newList;
    	// Returns -1 if TrailList cannot be created.
    	try {
    		newList = new TrailList(park);
    	} catch (IllegalArgumentException e) {
    		return -1;
    	}

    	// Grows "trailLists" array if full.
    	if (trailLists.length == numLists) {
    		growTrailListArray();
    	}

    	newList.addObserver(this);
    	trailLists[numLists] = newList; 
    	numLists++;
    	return numLists - 1;
    }
    
    /**
     * Increases the size of the TrailList array when it is at capacity.
     */
    private void growTrailListArray() {
    	TrailList[] newTrailLists = new TrailList[trailLists.length * 2];
    	for (int i = 0; i < trailLists.length; i++) {
    		newTrailLists[i] = trailLists[i];
    	}
    	trailLists = newTrailLists;
    }

    /**
     * Opens a data file with the given name and creates the data structures from
     * the file.
     * 
     * @param fname filename to create GetOutdoorsManager information from.
     * @return true is opened successfully
     */
    public boolean openDataFile(String fname) {
        if (changed) {
            saveDataFile(this.filename);
        }
        try {
            state = START_STATE;
            Scanner fileIn = new Scanner(new File(fname));
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                processLine(line);
            }

            setChanged();
            notifyObservers(this);
            changed = false;
            fileIn.close();
            return true;
        } catch (IOException e) {
            System.err.println("An error occurred while reading file " + fname);
            e.printStackTrace(System.err);
            return false;
        }
    }

    /**
     * Cleans text input to remove line break character
     * 
     * @param text - the text to clean
     * @return the cleaned text
     */
    private String clean(String text) {
        return text.replace("\n", " ").trim();
    }

    /**
     * Processes a single line from the input text file
     * 
     * @param line the line being processed
     */
    private void processLine(String line) {
        try (Scanner lineScan = new Scanner(line)) {
            if (lineScan.hasNext()) {
                String token = lineScan.next();
                if (token.equals("#")) {
                    state.onHeader(lineScan.nextLine().trim());
                } else {
                    state.onContent(line);
                }
            }
        }
    }

    /**
     * Represents a State in the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private abstract class State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        public abstract void onHeader(String line);

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        public abstract void onContent(String line);

    }

    /**
     * Start State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class StartState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            if (line.equalsIgnoreCase(ACTIVITY_HEADER)) {
                state = ACTIVITY_STATE;
            } else {
                throw new IllegalArgumentException(
                        "File must start with the ACTIVITY information.");
            }

        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            throw new IllegalArgumentException("File must start with the ACTIVITY information.");
        }

    }

    /**
     * Activity State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class ActivityState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            if (line.equalsIgnoreCase(PARK_HEADER)) {
                state = PARK_STATE;
            } else {
                throw new IllegalArgumentException(
                        "File must define PARK after the ACTIVITY information.");
            }
        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            if (!line.trim().equals("")) { // Not a blank line
                try (Scanner lineScan = new Scanner(line)) {
                    lineScan.useDelimiter(DELIMITER);
                    lineScan.next(); // * at start
                    String activityName = lineScan.next();
                    String activityDesc = lineScan.next();
                    boolean needSnow = lineScan.nextBoolean();
                    int snowBoundary = lineScan.nextInt();
                    activities.addActivity(activityName, activityDesc, needSnow, snowBoundary);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Error loading activity information: " + e.getMessage());
                }
            }
        }

    }

    /**
     * Park State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class ParkState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            if (line.equalsIgnoreCase(TRAIL_HEADER)) {
                state = TRAIL_STATE;
            } else {
                throw new IllegalArgumentException(
                        "File must define TRAIL after the PARK information.");
            }
        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            if (!line.trim().equals("")) { // Not a blank line
                try (Scanner lineScan = new Scanner(line)) {
                    lineScan.useDelimiter(DELIMITER);
                    lineScan.next(); // * at start
                    String parkName = lineScan.next();
                    String parkDesc = lineScan.next();
                    double snow = lineScan.nextDouble();
                    parks.addPark(parkName, parkDesc, snow);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Error loading park information: " + e.getMessage());
                }
            }
        }

    }

    /**
     * Trail State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class TrailState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            throw new IllegalArgumentException(
                    "File must not define any other headers after TRAIL.");
        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            if (!line.trim().equals("")) { // Not a blank line
                try (Scanner lineScan = new Scanner(line)) {
                    lineScan.useDelimiter(DELIMITER);
                    lineScan.next(); // * at start
                    String parkName = lineScan.next();
                    for (int i = 0; i < numLists; i++) {
                        if (trailLists[i].getParkName().equals(parkName)) {
                            String trailName = lineScan.next();
                            boolean trailMaintenance = lineScan.nextBoolean();
                            double snow = lineScan.nextDouble();
                            double distance = lineScan.nextDouble();
                            Difficulty difficulty = Difficulty.valueOf(lineScan.next());

                            SortedArrayList<Activity> activitiesList = new SortedArrayList<Activity>();
                            if (lineScan.hasNextLine()) {
                                String actLine = lineScan.nextLine();
                                Scanner actScan = new Scanner(actLine);
                                actScan.useDelimiter(DELIMITER);
                                while (actScan.hasNext()) {
                                    String activityName = actScan.next();
                                    for (int j = 0; j < activities.size(); j++) {
                                        Activity activity = activities.getActivityAt(j);
                                        if (activity.getName().equals(activityName)) {
                                            activitiesList.add(activity);
                                        }
                                    }
                                }
                                actScan.close();
                            }
                            trailLists[i].addTrail(trailName, activitiesList, trailMaintenance,
                                    snow, distance, difficulty);
                        }
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Error loading trail information: " + e.getMessage());
                }
            }
        }
    }

    /**
     * If one of the manager's Observed objects (ActivityList, ParkList,
     *   or TrailList) changes, update() is called. 
     * If "obs" is the "parks" ParkList, the list is iterated through 
     *   to check that each Park has a TrailList in "trailLists. If a park
     *   has no associated TrailList there, one is added.
     * For each case, the manager notifies its observers of the change and sets 
     *   "changed" to true.
     *   
     * @param obs the Observed object, who has just called notifyObservers().
     */
	@Override
	public void update(Observable obs, Object arg) {
		if (parks.equals(obs)) { // Updated ParkList case
			// The updated "parks" ParkList is iterated through to see if any 
			// new Parks have been added. If one has, it will not have an associated
			// TrailList in "trailLists", so the new Park is passed to addTrailList().
			for (int i = 0; i < parks.size(); i++) {
				for (int j = 0; j < trailLists.length; j++) {
					String currentParkName = parks.getParkAt(i).getName();
					String currentTrailListName = trailLists[i].getParkName();
					if (!(currentParkName.equals(currentTrailListName))){
						addTrailList(parks.getParkAt(i));
					}
				} 
			} // for
		} // if
		notifyObservers(this);
	}
}