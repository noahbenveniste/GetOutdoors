package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;
import edu.ncsu.csc216.get_outdoors.model.Activity;

/**
 * A Pane belonging to TrailListTab that defines the part of the
 *   window for editing individual Trails in a Park. This class
 *   extends EditPane and implements the Observer interface, so
 *   it can observe the system's general ActivityList.
 * 
 * @author demills
 */
public class TrailEditPane extends EditPane implements Observer {

	/** Constant used for serialization. */
	private static final long serialVersionUID = 1L;
	/** The TrailData object holding values of the Trail's fields. */
	private TrailData data;
	/** The ActivityList of the Trail being edited. */	private ActivityList activityList;
	/** Text field for the edited Trail's ID. */	private JTextField txtTrailID;
	/** Text field for the edited Trail's name. */
	private JTextField txtTrailName;	/** Text field for the edited Trail's snow depth. */
	private JTextField txtSnow;	/** Text field for the edited Trail's length (distance). */
	private JTextField txtDistance;
	/** Check box for whether or not the Trail is closed for maintenance. */	private JCheckBox txtClosedForMaintenance;
	/** 
	 * A combo box (dropdown list) for the available Difficulty values 
	 *  that can be assigned to a Trail. 
	 */ 	private JComboBox<Difficulty> tcDifficulty;
	/** An array of check boxes for selecting which Activities are permitted on a Trail. */	private JCheckBox[] tcActivities;
	
	/**
	 * Constructs a TrailEditPane, delegating to the constructor, 
	 *   TrailEditPane(TrailData, ActivityList), passing null for the
	 *   TrailData parameter.
	 *   
	 * @param actList the Trail's list of permitted Activities.
	 */
	public TrailEditPane(ActivityList actList) {
		this(null, actList);
	}
	/**
	 * Constructs a TrailEditPane. Construction is partially delegated
	 *   to TrailEditPane's parent class, EditPane. The "activityList"
	 *   instance variable is intialized to the passed ActivityList, the 
	 *   "data" instance variable is initialized to the passed TrailData. 
	 *   
	 * @param data the edited Trail's data, in a TrailData object.
	 * @param actList the edited Trail's ActivityList.
	 */
	public TrailEditPane(TrailData data, ActivityList actList) {
		super();
		this.data = data;
		this.activityList = actList;
		activityList.addObserver(this);
		init();
		disableEdit();
	}
	
	
	/**
	 * Returns the ActivityList of the Trail selected for editing.
	 * 
	 * @return return's the ActivityList of the Trail being edited.
	 */	ActivityList getActivityList() {
		return activityList;
	}

	JTextField getTxtTrailID() {
        if (null == txtTrailID) {
            txtTrailID = new JTextField();
            txtTrailID.setColumns(10);
            txtTrailID.setEditable(false);
            txtTrailID.setVisible(true);
            txtTrailID.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtTrailID;
	}

	/**
	 * Returns a JTextField of the edited Trail's name. If a JTextField hasn't 
	 *   been created for this field yet, one is instantiated with a width of 20,
	 *   then made visible. By default, this field is not editable.
	 * 
	 * @return return's text field for name of the Trail being edited.
	 */	JTextField getTxtTrailName() {
        if (null == txtTrailName) {
            txtTrailName = new JTextField();
            txtTrailName.setColumns(20);
            txtTrailName.setEditable(false);
            txtTrailName.setVisible(true);
            txtTrailName.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtTrailName;
	}
	/**
	 * Returns a JTextField for editing a Trail's snow depth. If a JTextField hasn't 
	 *   been created for this field yet, one is instantiated with a width of 5,
	 *   then made visible. By default, this field is not editable.
	 * 
	 * @return return's text field for snow depth of the Trail being edited.
	 */
	JTextField getTxtSnow() {
        if (null == txtSnow) {
            txtSnow = new JTextField();
            txtSnow.setColumns(5);
            txtSnow.setEditable(false);
            txtSnow.setVisible(true);
            txtSnow.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtSnow;
	}

	/**
	 * Returns a JTextField for editing a Trail's distance (length). If a JTextField 
	 *   hasn't been created for this field yet, one is instantiated with a width of 
	 *   5, then made visible. By default, this field is not editable.
	 * 
	 * @return return's text field for the distance of the Trail being edited.
	 */
	JTextField getTxtDistance() {
        if (null == txtDistance) {
            txtDistance = new JTextField();
            txtDistance.setColumns(5);
            txtDistance.setEditable(false);
            txtDistance.setVisible(true);
            txtDistance.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtDistance;
	}
	/**
	 * Returns a JCheckBox for toggling the Trail's boolean value, "closedForMaintenance". 
	 *   If a check box hasn't been created yet, one is instantiated and made visible.
	 * 
	 * @return return's a JCheckBox for toggling a Trail's boolean, "closedForMaintenance".
	 */
	JCheckBox getTxtClosedForMaintenance() {
        if (null == txtClosedForMaintenance) {
            txtClosedForMaintenance = new JCheckBox();
            txtClosedForMaintenance.setVisible(true);
        }
        return txtClosedForMaintenance;
	}

	/**
	 * Returns JCheckBox array for toggling the Activities in a Trail's ActivityList.
	 *   One CheckBox is included in the array for each activity in the system's 
	 *   ActivityList. If this array hasn't been created yet, one is instantiated, with
	 *   the text for each JCheckBox set to the name of the Activity it will toggle. 
	 *   Each JCheckBox is individually made visible after construction.
	 *   
	 * @return return's a JCheckBox array for toggling a Trail's ActivityList values.
	 */	JCheckBox[] getTcActivities() {
		if (tcActivities == null) {
			tcActivities = new JCheckBox[activityList.size()];
			for (int i = 0; i < activityList.size(); i++) {
				tcActivities[i] = new JCheckBox();
				tcActivities[i].setHorizontalAlignment(SwingConstants.LEFT);
				tcActivities[i].setText(activityList.getActivityAt(i).getName());
				tcActivities[i].setVisible(true); 
			}
		}
		return tcActivities;
	}

	/**
	 * Returns a JComboBox (dropdown menu) for selecting a Trail's Difficulty from 
	 *   the values listed in the Difficulty enumeration. Each Difficulty value 
	 *   is added the the combo box, after which it is set to visible. The JComboBox
	 *   is set to being uneditable after construction.
	 *   
	 * @return returns a JComboBox for selecting a Trail's Difficulty.
	 */
	JComboBox<Difficulty> getTcDifficulty() {
		if (tcDifficulty == null) {
			tcDifficulty = new JComboBox<Difficulty>();
			for (int i = 0; i < Difficulty.values().length; i++) {
				tcDifficulty.addItem(Difficulty.values()[i]);
			}
			tcDifficulty.setVisible(true);
			tcDifficulty.setEditable(false);
		}
		return tcDifficulty;
	}

	/**
	 * Each component of the TrailEditPane is added to its own temporary JPanel, with 
	 *   a FlowLayout, along with an appropriate JLabel to identify the component's
	 *   purpose. The components are obtained from the getText*() and getTc*() 
	 *   methods above. After each temporary JPanel is created and populated,
	 *   it is added to the TrailEditPane.
	 */
	@Override	protected void initView() {
		// Adding ID components - 1st row.
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel.add(new JLabel("Trail ID:", SwingConstants.LEFT));
		panel.add(getTxtTrailID());
		this.add(panel);

		// Adding Name components - 2nd row.
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel.add(new JLabel("Trail Name:", SwingConstants.LEFT));
		panel.add(getTxtTrailName());
		this.add(panel);

		// Adding Snow and Distance components - 3rd row.
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel.add(new JLabel("Snow:", SwingConstants.LEFT));
		panel.add(getTxtSnow());
		panel.add(new JLabel("Distance:", SwingConstants.LEFT));
		panel.add(getTxtDistance());
		this.add(panel);
		
		// Adding ClosedForMaintenance components - 4th row.
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel.add(new JLabel("Closed for Maintenance:", SwingConstants.LEFT));
		panel.add(getTxtClosedForMaintenance());
		this.add(panel);

		// Adding Difficulty components - 5th row.
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel.add(new JLabel("Difficulty:", SwingConstants.LEFT));
		panel.add(getTcDifficulty());
		this.add(panel);

		// Adding Activities components - 6th row.
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel.add(new JLabel("Activities:", SwingConstants.LEFT));
		JCheckBox[] checkBoxes = getTcActivities();
		for (int i = 0; i < getTcActivities().length; i++) {
			panel.add(checkBoxes[i]);
		}
		this.add(panel);
	}
	/**
	 * Sets the TrailEditPane's Data instance to the passed parameter, after
	 *   which fillFields() is called to update the GUI for the new values.
	 *   Unlike enableEdit(), this TrailEditPane's editing state, represented
	 *   by the boolean "edit", is not altered.
	 * 
	 * @param data the Data instance to set for this TrailEditPane.
	 */
	@Override
	void setData(Data data) {
		this.data = (TrailData) data;
		fillFields();
	}

	/**
	 * If this TrailEditPane is not in its editing state, it then is set to 
	 *   be. This TrailEditPane's Data instance is then set to the passed
	 *   parameter, after which fillFields() is called to update the GUI
	 *   for the new values. If this TrailEditPane is in the editing state,
	 *   nothing is done.
	 * 
	 * @param data the Data to set for this TrailEditPane.
	 */
	@Override
	void enableEdit(Data data) {
        if (!edit) {
            edit = true;
            this.data = (TrailData) data;
            fillFields();
        }
	}

	/**
     * Clears the fields by setting data to null, then calls fillFields()
     *   to update the GUI. 
     */
	@Override
	void clearFields() {
		data = null;
		fillFields();
	}
    /**
     * Adds the passed DocumentListener to each of the text field components.
     * 
     * @param docListener DocumentListener to add to text fields.
     */
	@Override
	void addFieldListener(DocumentListener docListener) {
    	getTxtTrailID().getDocument().addDocumentListener(docListener);
    	getTxtTrailName().getDocument().addDocumentListener(docListener);
    	getTxtSnow().getDocument().addDocumentListener(docListener);
    	getTxtDistance().getDocument().addDocumentListener(docListener);
	}

    /**
     * Fills the fields with the appropriate text from the Data field.
     * If "data" is null, the fields are set to empty strings, and the 
     *   TrailEditPane's components are set to their uneditable state.
     *   All check boxes are set as unselected and the Difficulty 
     *   combo box is set to its default value, "Difficult.EASY".
     *   This is for when no row is selected in the TrailListPane.
     * If "data" is not null, the components are set to the values
     *   returned from their associated getter methods. For each
     *   Activity contained in the Trail's ActivityList, the 
     *   associated JCheckBox (from "tcActivities[]") is set to 
     *   its selected state. 
     * Depending on whether this instance is in the "edit" or "add"
     *   state, components are set as editable or enabled. "tcTrailID"
     *   is ignored, and if in the add mode, "tcTrailName" is set
     *   as editable. If in the add or edit mode, all other components
     *   besides the components for the Trail's name, Difficulty, and 
     *   ID are set as enabled or editable, as appropriate.
     */
	@Override
	void fillFields() {
        if (null == data) {
            txtTrailID.setText("");
            txtTrailName.setText("");
            txtSnow.setText("");
            txtDistance.setText("");
            txtClosedForMaintenance.setSelected(false);
            tcDifficulty.setSelectedItem(Difficulty.EASY);
            for (int i = 0; i < tcActivities.length; i++) {
            	tcActivities[i].setSelected(false);
            	tcActivities[i].setEnabled(false);
            }

            txtTrailID.setEditable(false);
            txtTrailName.setEditable(false);
            txtSnow.setEditable(false);
            txtDistance.setEditable(false);
            txtClosedForMaintenance.setEnabled(false);
        } else {
            txtTrailID.setText(data.getTrailID());
            txtTrailName.setText(data.getTrailName());
            txtSnow.setText(data.getSnow() + "");
            txtDistance.setText(data.getDistance() + "");
            txtClosedForMaintenance.setSelected(data.isClosedForMaintenance());
            tcDifficulty.setSelectedItem(data.getDifficulty());
            for (int i = 0; i < activityList.size(); i++) {
            	Activity currActivity = activityList.getActivityAt(i);
            	if (data.getActivities().contains(currActivity)) {
            		tcActivities[i].setSelected(true);
            	} else {
            		tcActivities[i].setSelected(false);
            	}
            }
        }
        

        if (add) {
            txtTrailName.setEditable(true);
        }

        if (add || edit) {
            txtSnow.setEditable(true);
            txtDistance.setEditable(true);
            txtClosedForMaintenance.setEnabled(true);
            for (int i = 0; i < tcActivities.length; i++) {
            	tcActivities[i].setEnabled(true);
            }
        }
	}

    /**
     * If the TrailEditPane is in add mode, returns true if the three
     *   editable fields (trailName, snow, and distance) are all
     *   non-empty.
     * If the TrailEditPane is in edit mode, returns true if the two
     *   editable fields (snow and distance) are both non-empty. Since
     *   a Trail cannot have an empty name, it will always be filled 
     *   when in edit mode.
     * Otherwise, the method returns false.
     * This method is called in Tab.handleDocEvent(), through TrailListTab, 
     *   to determine whether the necessary fields have been filled to enable 
     *   the "Save" or "Add" buttons.
     *   
     * @return returns true if the name, snow, and distance fields are all non-empty.
     */
	@Override
	boolean fieldsNotEmpty() {
		return getTxtTrailName().getDocument().getLength() != 0 &&
		   getTxtSnow().getDocument().getLength() != 0 &&
		   getTxtDistance().getDocument().getLength() != 0;
	}

	/**
	 * Returns a Data object constructed with the Trail's current values.
	 *   The Trail values are obtained from the components associated
	 *   with each value, which are themselves obtained from their 
	 *   associated getter methods.
	 * 
	 * @return returns a Data object of the Trail's current values.
	 */
	@Override
	Data getFields() {
		TrailData trailData = new TrailData(getTxtTrailID().getText(), getTxtTrailName().getText(), 
							 getTxtClosedForMaintenance().isSelected(), Double.parseDouble(getTxtSnow().getText()),
							 Double.parseDouble(getTxtDistance().getText()), 
							 (Difficulty) getTcDifficulty().getSelectedItem(), getSelectedActivities());
		return trailData;
	}

	/**
	 * Returns a SortedArrayList of Activities of the currently selected
	 *   Activities from the JCheckBox array "tcActivities". 
	 *   
	 * @return returns the list of Activities selected for the Trail.
	 */	private SortedArrayList<Activity> getSelectedActivities() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		for (int i = 0; i < getTcActivities().length; i++) {
			if (tcActivities[i].isSelected()) {
				activities.add(activityList.getActivityAt(i));
			}
		}
		return activities;
	}

	/**
	 * If the system's ActivityList has been updated, this method 
	 *   is called. It sets the TrailEditPane's ActivityList to
	 *   null, removes all components from the TrailEditPane, and 
	 *   then calls init(), through which initView() is called.
	 *   initView() will then reinitialize the TrailEditPane by
	 *   adding back the removed componenets.
	 */	public void update(Observable obs, Object obj) {
		if (obs instanceof ActivityList) {
			tcActivities = null;
			this.removeAll();
			init();
		}
	}
}