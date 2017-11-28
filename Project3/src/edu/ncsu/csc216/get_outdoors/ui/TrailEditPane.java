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
 * A Pane belonging to TrailListTab that represents the part 
 *   of the window for editing individual Trails in a Park.
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
	
		ActivityList getActivityList() {
		return activityList;
	}

	JTextField getTxtTrailID() {
        if (null == txtTrailID) {
            txtTrailID = new JTextField();
            txtTrailID.setColumns(5);
            txtTrailID.setEditable(false);
            txtTrailID.setVisible(true);
            txtTrailID.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtTrailID;
	}
	JTextField getTxtTrailName() {
        if (null == txtTrailName) {
            txtTrailName = new JTextField();
            txtTrailName.setColumns(5);
            txtTrailName.setEditable(false);
            txtTrailName.setVisible(true);
            txtTrailName.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtTrailName;
	}
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
	JCheckBox getTxtClosedForMaintenance() {
        if (null == txtClosedForMaintenance) {
            txtClosedForMaintenance = new JCheckBox();
            txtClosedForMaintenance.setVisible(true);
        }
        return txtClosedForMaintenance;
	}
	JCheckBox[] getTcActivities() {
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
	 * Sets the TrailEditsPane data.
	 * 
	 * @param d the Data to set for this TrailEditPane.
	 */
	@Override
	void setData(Data data) {
		this.data = (TrailData) data;
	}

	/**
	 * Enables editing and disables add.
	 * 
	 * @param d the Data to set for This TrailEditPane.
	 */
	@Override
	void enableEdit(Data data) {
        if (!edit) {
            edit = true;
            data = (TrailData) data;
            fillFields();
        }
	}

	/**
     * Clears the fields by setting data to null.
     */
	@Override
	void clearFields() {
		data = null;
		fillFields();
	}
    /**
     * Adds the given DocumentListener to the text fields.
     * 
     * @param docListener DocumentListener to add to text fields
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
     */
	@Override
	void fillFields() {
        if (null == data) {
            txtTrailID.setText("");
            txtTrailName.setText("");
            txtSnow.setText("");
            txtDistance.setText("");
            txtClosedForMaintenance.setSelected(false);
            for (int i = 0; i < tcActivities.length; i++) {
            	tcActivities[i].setSelected(false);
            	tcActivities[i].setEnabled(false);
            }

            txtTrailID.setEditable(false);
            txtTrailName.setEditable(false);
            txtSnow.setEditable(false);
            txtDistance.setEditable(false);
            txtClosedForMaintenance.setEnabled(false);
            // TODO - Check that Difficulty combo box defaults to "EASY".

        } else {
            txtTrailID.setText(data.getTrailID());
            txtTrailName.setText(data.getTrailName());
            txtSnow.setText(data.getSnow() + "");
            txtDistance.setText(data.getDistance() + "");
            txtClosedForMaintenance.setSelected(data.isClosedForMaintenance());
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
     * Checks that certain fields are not empty
     * 
     * @returns returns true if the fields are empty; false otherwise.
     */
	@Override
	boolean fieldsNotEmpty() {
		return getTxtTrailName().getDocument().getLength() != 0 &&
			   getTxtSnow().getDocument().getLength() != 0 &&
			   getTxtDistance().getDocument().getLength() != 0;
	}

	/**
	 * Returns a Data object representing the Pane's fields.
	 */
	@Override
	Data getFields() {
		TrailData trailData = new TrailData(getTxtTrailID().getText(), getTxtTrailName().getText(), 
							 getTxtClosedForMaintenance().isSelected(), Double.parseDouble(getTxtSnow().getText()),
							 Double.parseDouble(getTxtDistance().getText()), 
							 (Difficulty) getTcDifficulty().getSelectedItem(), getSelectedActivities());
		return trailData;
	}
	private SortedArrayList<Activity> getSelectedActivities() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		for (int i = 0; i < getTcActivities().length; i++) {
			if (tcActivities[i].isSelected()) {
				activities.add(activityList.getActivityAt(i));
			}
		}
		return activities;
	}
	public void update(Observable obs, Object obj) {
		// TODO determine if this method is needed.
	}
}