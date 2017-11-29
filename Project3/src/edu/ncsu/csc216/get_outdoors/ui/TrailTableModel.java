package edu.ncsu.csc216.get_outdoors.ui;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Handles interaction between model and GUI for displaying Trail data.
 * 
 * @author Noah Benveniste
 * @author Daniel Mills
 */
public class TrailTableModel extends TableModel {

    /** Default serial version ID */
	private static final long serialVersionUID = 1L;
	/** Names for each column of the table. */
    private String[] colNames = {"ID", "Trail Name", "Closed for Maintenance?", "Snow", "Distance", "Difficulty"};

	/**
	 * Constructs a TrailTableModel, passing the "data" parameter to
	 *   the constructor of TrailTabelModel's parent, TableModel.
	 * 
	 * @param data an array containing Trail data from the TrailList
	 */
	public TrailTableModel(Object[][] data) {
		super(data);
	}

	/**
	 * Return the number of columns of data available for use in a
	 *   Trail table.
	 * 
	 * @return number of columns for the Trail table.
	 */
	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	/**
	 * Gets the name for the column in the table at the specified index.
	 * 
	 * @param col the index of the column to return the name of.
	 * @return returns the name of the specified column.
	 */
	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	/**
	 * Returns the a TrailData object constructed from the Objects of the 
	 *   row at the specified index in the 2D Object array of data.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Data getRowData(int row) {
//	        String trailID = (String) data[row][0];
//	        String trailName = (String) data[row][1];
//	        boolean closedForMaintenance = (boolean) data[row][2];
//	        double snow = (double) data[row][3];
//	        double distance = (double) data[row][4];
//	        Difficulty difficulty = (Difficulty) data[row][5];
//	        @SuppressWarnings("unchecked")
//			SortedArrayList<Activity> activities = (SortedArrayList<Activity>) data[row][6];
//	        TrailData trailData = new TrailData(trailID, trailName, closedForMaintenance,
//	        		                        snow, distance, difficulty, activities);
	        return new TrailData(
	        		(String) data[row][0], //Trail ID
	        		(String) data[row][1], //Trail name
	        		(boolean) data[row][2], //Maintenance status
	        		(double) data[row][3], //Snow level
	        		(double) data[row][4], //Trail distance
	        		(Difficulty) data[row][5], //Trail difficulty
	        		(SortedArrayList<Activity>) data[row][6] //Available activities
	        		);
	}

	/**
	 * Sets the data for a row.
	 */
	@Override
	public void setData(Data data, int row) {
		if (data == null || row < 0 || row >= this.data.length) {
			throw new IllegalArgumentException("Invalid parameter for TrailTableModel.setData()");
		}
		TrailData trailData = (TrailData) data; 
		setValueAt(trailData.getTrailID(), row, 0);
		setValueAt(trailData.getTrailName(), row, 1);
		setValueAt(trailData.isClosedForMaintenance(), row, 2);
		setValueAt(trailData.getSnow(), row, 3);
		setValueAt(trailData.getDistance(), row, 4);
		setValueAt(trailData.getDifficulty(), row, 5);
		setValueAt(trailData.getActivities(), row, 6);
	}
}