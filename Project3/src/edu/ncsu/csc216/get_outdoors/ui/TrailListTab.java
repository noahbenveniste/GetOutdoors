package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.model.Trail;
import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * A TrailListTab for the GUI
 * 
 * @author Noah Benveniste
 * @author Daniel Mills
 */
public class TrailListTab extends Tab {

	/** Default serial version ID */
	private static final long serialVersionUID = 1L;
	/** */
	private TrailList trails;

	/**
	 * Creates a TrailListTab
	 * 
	 * @param t the TrailList
	 * @param activities the ActivityList with available Activities
	 */
	public TrailListTab(TrailList trails, ActivityList activities) {
		super();
		this.trails = trails;
		this.setListPane(new TrailListPane(this.trails));
		// Add a ListSelectionListener to the listPane so that ParkTab
        // can respond to selection events.
        this.getListPane().getTable().getSelectionModel().addListSelectionListener(this);
        this.setEditPane(new TrailEditPane());
        // Adds a FieldListener to the editPane so that ParkTab can
        // respond
        // to events in fields that are part of the ParkPane.
        this.getEditPane().addFieldListener(this);
        // Sets the layout for the tab and adds the element.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.getListPane());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(this.getEditPane());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(this.getButtons());
	}

	/**
	 * Deletes a TrailList
	 */
	@Override
	public void delete() {
		TrailData d = (TrailData) getEditPane().getFields();
		this.trails.removeTrail(d.getTrailID());
		getEditPane().clearFields();
        if (addMode) {
            enableAdd(false);
            getEditPane().disableAdd();
        }
        if (editMode) {
            getListPane().clearSelection();
            enableSave(false);
            getEditPane().disableEdit();
        }
	}

	/**
	 * Notifies an action listener when an action is performed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (addMode && e.getActionCommand().equals("add")) {
            try {
                TrailData d = (TrailData) getEditPane().getFields();
                if (d.getTrailName() == null || d.getTrailName().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Trail name must be non-whitespace",
                            "Trail Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    getEditPane().setData(d);
                    trails.addTrail(
                    		d.getTrailName(), 
                    		d.getActivities(), 
                    		d.isClosedForMaintenance(), 
                    		d.getSnow(),
                    		d.getDistance(), 
                    		d.getDifficulty());
                    enableAdd(false);
                    getEditPane().disableAdd();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Snowfall must be a double.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, iae.getMessage(), "Trail Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (editMode && e.getActionCommand().equals("save")) {
            try {
                TrailData d = (TrailData) getEditPane().getFields();
                if (d.getTrailName() == null || d.getTrailName().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Trail name must be non-whitespace",
                            "Trail Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    getEditPane().setData(d);
                    Trail trail = trails.getTrailAt(trails.indexOfID(d.getTrailID()));
                    trail.setSnow(d.getSnow());
                    trail.setTrailMaintenance(d.isClosedForMaintenance());
                    trail.setActivities(d.getActivities());
                    trail.setDistance(d.getDistance());
                    getListPane().clearSelection();
                    enableSave(false);
                    getEditPane().disableEdit();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Snowfall must be a double.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, iae.getMessage(), "Trail Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("cancel")) {
            getEditPane().clearFields();
            if (addMode) {
                enableAdd(false);
                getEditPane().disableAdd();
            }
            if (editMode) {
                getListPane().clearSelection();
                enableSave(false);
                getEditPane().disableEdit();
            }
        }
	}

}
