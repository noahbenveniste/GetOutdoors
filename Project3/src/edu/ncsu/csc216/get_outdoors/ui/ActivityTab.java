package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;

/**
 * Represents the Activity tab the Get Outdoors! application.
 * 
 * @author Jessica Young Schmidt
 */
public class ActivityTab extends Tab {
    /** Serial version UID */
    private static final long serialVersionUID = -9176580776697888645L;
    private ActivityList activities;

    /**
     * Constructs the ActivityTab with the given ActivityList.
     * 
     * @param activities list of activities to display
     */
    public ActivityTab(ActivityList activities) {
        super();
        this.activities = activities;

        // Create the ActivityListPane (table)
        setListPane(new ActivityListPane(this.activities));
        // Add a ListSelectionListener to the listPane so that ActivityTab
        // can respond to selection events.
        getListPane().getTable().getSelectionModel().addListSelectionListener(this);

        // Creates the ActivityPane (form)
        setEditPane(new ActivityEditPane());
        // Adds a FieldListener to the editPane so that ActivityTab can
        // respond
        // to events in fields that are part of the ActivityPane.
        getEditPane().addFieldListener(this);

        // Sets the layout for the tab and adds the element.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(getListPane());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(getEditPane());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(getButtons());
    }

    /**
     * Called when the user selects the option to delete a selected item. If there
     * is no item selected, a pop-up is displayed. If an item is selected, the item
     * is removed from the List, and the remaining fields are reset to default
     * (non-editing) states.
     */
    @Override
    public void delete() {
        // Cannot delete activity
        throw new UnsupportedOperationException("Cannot delete activity");
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
                ActivityData d = (ActivityData) getEditPane().getFields();
                if (d.getName() == null || d.getName().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Activity name must be non-whitespace",
                            "Activity Error", JOptionPane.ERROR_MESSAGE);
                } else if (d.getDescription() == null || d.getDescription().trim().equals("")) {

                    JOptionPane.showMessageDialog(this,
                            "Activity description must be non-whitespace", "Activity Error",
                            JOptionPane.ERROR_MESSAGE);
                } else

                if (d.getSnowBoundary() < 0) {
                    JOptionPane.showMessageDialog(this, "Snow boundary must be non-negative",
                            "Activity Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    getEditPane().setData(d);
                    activities.addActivity(d.getName(), d.getDescription(), d.snowNeeded(),
                            d.getSnowBoundary());
                    enableAdd(false);
                    getEditPane().disableAdd();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Snow boundary must be a non-negative integer.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, "Activity name must be unique.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (editMode && e.getActionCommand().equals("save")) {
            ActivityData d = (ActivityData) getEditPane().getFields();
            if (d.getName() == null || d.getName().trim().equals("")) {

                JOptionPane.showMessageDialog(this, "Activity name must be non-whitespace",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            }
            if (d.getDescription() == null || d.getDescription().trim().equals("")) {

                JOptionPane.showMessageDialog(this, "Activity description must be non-whitespace",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            } else

            if (d.getSnowBoundary() < 0) {
                JOptionPane.showMessageDialog(this, "Snow boundary must be non-negative",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);

            } else {
                getEditPane().setData(d);
                Activity activity = activities
                        .getActivityAt(activities.indexOfID(d.getActivityID()));
                activity.setDescription(d.getDescription());
                activity.setSnowBoundary(d.getSnowBoundary());
                activity.setNeedSnow(d.snowNeeded());
                getListPane().clearSelection();
                enableSave(false);
                getEditPane().disableEdit();
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
