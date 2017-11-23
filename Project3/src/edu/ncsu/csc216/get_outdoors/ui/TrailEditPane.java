package edu.ncsu.csc216.get_outdoors.ui;

import javax.swing.event.DocumentListener;

/**
 * A Pane belonging to TrailListTab that represents the part 
 *   of the window for editing individual Trails in a Park.
 * 
 * @author demills
 */
public class TrailEditPane extends EditPane {

	/** Constant used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new TrailEditPane.
	 */
	public TrailEditPane() {

	}

	/**
	 * Initializes the TrailEditPane.
	 */
	@Override
	protected void initView() {

	}

	/**
	 * Sets the TrailEditsPane data.
	 * 
	 * @param d the Data to set for this TrailEditPane.
	 */
	@Override
	void setData(Data d) {
	}

	/**
	 * Enables editing and disables add.
	 * 
	 * @param d the Data to set for This TrailEditPane.
	 */
	@Override
	void enableEdit(Data d) {

	}

	/**
     * Clears the fields by setting data to null.
     */
	@Override
	void clearFields() {

	}

    /**
     * Adds the given DocumentListener to the text fields.
     * 
     * @param docListener DocumentListener to add to text fields
     */
	@Override
	void addFieldListener(DocumentListener docListener) {

	}


    /**
     * Fills the fields with the appropriate text from the Data field.
     */
	@Override
	void fillFields() {

	}

    /**
     * Checks that certain fields are not empty
     * 
     * @returns returns true if the fields are empty; false otherwise.
     */
	@Override
	boolean fieldsNotEmpty() {
		return false;
	}

	/**
	 * Returns a Data object representing the Pane's fields.
	 */
	@Override
	Data getFields() {
		return null;
	}
}