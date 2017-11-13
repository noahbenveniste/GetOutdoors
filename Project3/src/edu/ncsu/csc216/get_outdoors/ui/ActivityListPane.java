package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.get_outdoors.model.ActivityList;

/**
 * Maintains the list of activities.
 * 
 * @author Jessica Young Schmidt
 */
public class ActivityListPane extends ListPane {
    /** Serial version UID */
    private static final long serialVersionUID = 1176490008221464860L;
    /** List of activities */
    private ActivityList activities;
    /** ActivityTableModel which displays the list of activities */
    private ActivityTableModel atm;
    /** Widths of columns */
    private int[] colWidths = { 50, 125, 300, 75, 75 };

    /**
     * Constructs list of activities
     * 
     * @param activities list of activities
     */
    public ActivityListPane(ActivityList activities) {
        super();
        this.activities = activities;
        activities.addObserver(this);
        atm = new ActivityTableModel(activities.get2DArray());
        initView();
    }

    /**
     * Initialize view
     */
    private void initView() {
        table = new JTable(atm);
        for (int i = 0; i < colWidths.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(colWidths[i]);
        }
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        setViewportView(table);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Returns the TableModel.
     * 
     * @return the TableModel
     */
    @Override
    public TableModel getTableModel() {
        return atm;
    }

    /**
     * This method is called by the observed object, whenever the observed object is
     * changed.
     * 
     * @param o the observable object
     * @param arg any additional information needed about the change.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (activities.equals(o)) {
            // If there is a different number of rows, create a show new
            // ActivityTableModel.
            if (activities.size() != atm.getRowCount()) {
                atm = new ActivityTableModel(activities.get2DArray());
                table.setModel(atm);
            } else {
                // Otherwise, just update the values directly.
                Object[][] arr = activities.get2DArray();
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < atm.getColumnCount(); j++) {
                        atm.setValueAt(arr[i][j], i, j);
                    }
                }
            }
        }
    }
}
