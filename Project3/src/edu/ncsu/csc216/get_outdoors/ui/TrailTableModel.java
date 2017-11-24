package edu.ncsu.csc216.get_outdoors.ui;

/**
 * Handles interaction between model and GUI for displaying Trail data
 * 
 * @author Noah Benveniste
 * @author Daniel Mills
 */
public class TrailTableModel extends TableModel {

    /** Default serial version ID */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a TrailTableModel
	 * 
	 * @param data an array containing Trail data from the TrailList
	 */
	public TrailTableModel(Object[][] data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the number of columns
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Gets the name for a column
	 */
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the data for a row
	 */
	@Override
	public Data getRowData(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Sets the data for a row
	 */
	@Override
	public void setData(Data d, int row) {
		// TODO Auto-generated method stub

	}

}
