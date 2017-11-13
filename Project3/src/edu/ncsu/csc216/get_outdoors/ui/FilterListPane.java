package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Maintains the list of trails for filtering.
 * 
 * @author Jessica Young Schmidt
 */
public class FilterListPane extends ListPane {

    /** Serial version UID */
    private static final long serialVersionUID = -3998419560246767436L;
    /** List of trails */
    private TrailList trailList;
    /** TrailTableModel which displays the list of trails */
    private TrailTableModel ttm;
    /** Widths of columns */
    private int[] colWidths = { 50, 125, 300, 75, 75 };

    /**
     * Constructs list of trail for filtering
     * 
     * @param trailList list of trails
     */
    public FilterListPane(TrailList trailList) {
        super();
        this.trailList = trailList;
        this.trailList.addObserver(this);
        // only gets those not closed for maintenance
        ttm = new TrailTableModel(trailList.get2DArrayNoMaintenance());
        initView();
    }

    /**
     * Sets TrailTableModel to parameter's trails that are not closed for
     * maintenance
     * 
     * @param trailList trails to examine
     */
    public void setData(TrailList trailList) {
        ttm = new TrailTableModel(trailList.get2DArrayNoMaintenance());
        this.trailList = trailList;
        this.trailList.addObserver(this);
        table.setModel(ttm);
    }

    /**
     * Sets TrailTableModel to parameter's trails that are not closed for
     * maintenance and currently open for activity
     * 
     * @param trailList trails to examine
     * @param activity activity to find trails that are currently open to
     */
    public void setData(TrailList trailList, Activity activity) {
        ttm = new TrailTableModel(trailList.get2DArray(activity));
        this.trailList = trailList;
        this.trailList.addObserver(this);
        table.setModel(ttm);
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
        if (o instanceof TrailList) {
            TrailList trails = (TrailList) o;
            if (trails.equals(trailList)) {
                ttm = new TrailTableModel(trailList.get2DArrayNoMaintenance());
                initView();
            }
        }
    }

    /**
     * Returns the TableModel.
     * 
     * @return the TableModel
     */
    @Override
    public TableModel getTableModel() {
        return ttm;
    }

    /**
     * Initialize view
     */
    private void initView() {
        table = new JTable(ttm);
        for (int i = 0; i < colWidths.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(colWidths[i]);
        }
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        setViewportView(table);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

}
