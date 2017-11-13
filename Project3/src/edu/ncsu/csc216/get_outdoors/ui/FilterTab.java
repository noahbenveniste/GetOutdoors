package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Represents the Trail Filter tab the Get Outdoors! application.
 * 
 * @author Jessica Young Schmidt
 */
public class FilterTab extends JPanel implements ActionListener {
    /** Serial version UID */
    private static final long serialVersionUID = -785292681662594377L;
    /** ListPane for filtering */
    private FilterListPane listPane;
    /** All TrailLists */
    private TrailList[] trails;
    /** Available activities */
    private ActivityList activities;
    /** Number of parks */
    private int numParks;
    /** Radio buttons for parks */
    private JRadioButton[] parkButtons;
    /** Radio buttons for activities */
    private JRadioButton[] activityButtons;
    /** Group of activity buttons */
    private ButtonGroup activityGroup;
    /** Group of park buttons */
    private ButtonGroup parkGroup;

    /**
     * Construct FilterTab with parameters
     * 
     * @param activities list of activities
     * @param trailLists array of TrailLists
     * @param numParks number of parks
     */
    public FilterTab(ActivityList activities, TrailList[] trailLists, int numParks) {
        super();
        this.trails = trailLists;
        this.numParks = numParks;
        this.activities = activities;
        if (numParks == 0) {
            // No trails to display
            this.add(new JLabel("No Trails to Filter"));
        } else {
            initActivityButtons();
            initParkButtons();
            this.setListPane(new FilterListPane(getSelectedPark()));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(this.getListPane());
            add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }

    /**
     * initialize the activity buttons based on the activity list
     */
    private void initActivityButtons() {
        activityButtons = new JRadioButton[activities.size() + 1];
        activityGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Select maintenance/activity: "));
        // Add any option
        activityButtons[0] = new JRadioButton("Not closed for maintenance");
        activityGroup.add(activityButtons[0]);
        panel.add(activityButtons[0]);
        activityButtons[0].setSelected(true);
        activityButtons[0].addActionListener(this);
        activityButtons[0].setActionCommand("" + 0);
        for (int i = 1; i < activities.size() + 1; i++) {
            String actName = activities.getActivityAt(i - 1).getName();
            activityButtons[i] = new JRadioButton(actName);
            activityGroup.add(activityButtons[i]);
            panel.add(activityButtons[i]);
            activityButtons[i].addActionListener(this);
            activityButtons[i].setActionCommand("" + i);
        }
        this.add(panel);
    }

    /**
     * initialize the park buttons based on the park list
     */
    private void initParkButtons() {
        parkButtons = new JRadioButton[trails.length];
        parkGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Select park/trail list: "));
        for (int i = 0; i < numParks; i++) {
            String parkName = trails[i].getParkName();
            parkButtons[i] = new JRadioButton(parkName);
            parkGroup.add(parkButtons[i]);
            panel.add(parkButtons[i]);
            parkButtons[i].addActionListener(this);
            parkButtons[i].setActionCommand("" + i);
        }
        parkButtons[0].setSelected(true);
        this.add(panel);
    }

    /**
     * @return the listPane
     */
    protected ListPane getListPane() {
        return listPane;
    }

    /**
     * @param listPane the listPane to set
     */
    protected void setListPane(FilterListPane listPane) {
        this.listPane = listPane;
    }

    /**
     * Returns selected activity or null if maintenance is selected
     * 
     * @return selected activity. null if maintenance is selected
     */
    private Activity getSelectedActivity() {
        String command = activityGroup.getSelection().getActionCommand();
        if (command.equals("0")) {
            return null;
        }
        return activities.getActivityAt(Integer.parseInt(command) - 1);
    }

    /**
     * Returns selected park
     * 
     * @return selected park
     */
    private TrailList getSelectedPark() {
        String command = parkGroup.getSelection().getActionCommand();
        return trails[Integer.parseInt(command)];
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
        TrailList selectedPark = getSelectedPark();
        Activity selectedActivity = getSelectedActivity();

        if (selectedActivity == null) {
            listPane.setData(selectedPark);
        } else {
            listPane.setData(selectedPark, selectedActivity);
        }

        this.revalidate();
        this.repaint();
    }
}
