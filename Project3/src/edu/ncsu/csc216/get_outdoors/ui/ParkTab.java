package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import edu.ncsu.csc216.get_outdoors.model.Park;
import edu.ncsu.csc216.get_outdoors.model.ParkList;

/**
 * Represents the Park tab the Get Outdoors! application.
 * 
 * @author Jessica Young Schmidt
 */
public class ParkTab extends Tab {

    /** Serial version UID */
    private static final long serialVersionUID = -9183012178127901570L;
    /** ParkList for the tab */
    private ParkList parks;

    /**
     * Constructs the ParkTab with the given ParkList.
     * 
     * @param parks list of parks to display
     */
    public ParkTab(ParkList parks) {
        super();
        this.parks = parks;

        // Create the ParkListPane (table)
        this.setListPane(new ParkListPane(this.parks));
        // Add a ListSelectionListener to the listPane so that ParkTab
        // can respond to selection events.
        this.getListPane().getTable().getSelectionModel().addListSelectionListener(this);

        // Creates the ParkPane (form)
        this.setEditPane(new ParkEditPane());
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
     * Called when the user selects the option to delete a selected item. If there
     * is no item selected, a pop-up is displayed. If an item is selected, the item
     * is removed from the List, and the remaining fields are reset to default
     * (non-editing) states.
     */
    @Override
    public void delete() {
        // Cannot delete park
        throw new UnsupportedOperationException("Cannot delete park");
    }

    /**
     * Method inherited from ActionListener. If there is an add, save, or cancel
     * action (which correspond to the three buttons), this method is called. The
     * model is updated (or not) depending on the action.
     * 
     * @param e ActionEvent that represents the user's interaction with the GUI.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (addMode && e.getActionCommand().equals("add")) {
            try {
                ParkData d = (ParkData) getEditPane().getFields();
                if (d.getName() == null || d.getName().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Park name must be non-whitespace",
                            "Park Error", JOptionPane.ERROR_MESSAGE);
                }
                if (d.getDescription() == null || d.getDescription().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Park description must be non-whitespace",
                            "Park Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    getEditPane().setData(d);
                    parks.addPark(d.getName(), d.getDescription(), d.getSnowChange());
                    enableAdd(false);
                    getEditPane().disableAdd();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Snowfall/change must be a double.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, "Park name must be unique.", "Park Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (editMode && e.getActionCommand().equals("save")) {
            try {
                ParkData d = (ParkData) getEditPane().getFields();
                if (d.getName() == null || d.getName().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Park name must be non-whitespace",
                            "Park Error", JOptionPane.ERROR_MESSAGE);
                }
                if (d.getDescription() == null || d.getDescription().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Park description must be non-whitespace",
                            "Park Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    getEditPane().setData(d);
                    Park park = parks.getParkAt(parks.indexOfID(d.getParkID()));
                    park.setSnowChange(d.getSnowChange());
                    getListPane().clearSelection();
                    enableSave(false);
                    getEditPane().disableEdit();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Snowfall/change must be a double.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
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
