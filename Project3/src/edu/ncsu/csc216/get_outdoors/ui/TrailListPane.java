package edu.ncsu.csc216.get_outdoors.ui;

import java.util.Observable;

/**
 * Panel for GUI components used for the Trail editing menu.
 * 
 * @author demills
 */
public class TrailListPane extends ListPane {

	/** Unique ID for serialization.  */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new TrailListPane().
	 */
	public TrailListPane() {
		// Empty for now.
	}

	/**
	 * Called when Observed objects have been modified.
	 * 
	 * @param obs the Observed object that's calling this method.
	 * @param arg the Observed object that's calling this method.
	 */
	@Override
	public void update(Observable obs, Object arg) {
		// Empty for now.
	}

	/**
	 * Returns the TableModel that this TrailListPane maintains.
	 * 
	 * @return returns this TrailListPane's TableModel. 
	 */
	@Override
	public TableModel getTableModel() {
		return null;
	}
}