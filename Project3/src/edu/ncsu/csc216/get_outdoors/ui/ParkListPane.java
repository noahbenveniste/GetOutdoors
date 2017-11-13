package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.get_outdoors.model.ParkList;

/**
 * Maintains the list of parks.
 * 
 * @author Jessica Young Schmidt
 */
public class ParkListPane extends ListPane {
    /** Serial version UID */
    private static final long serialVersionUID = -7283113847070434860L;
    /** List of parks */
    private ParkList parks;
    /** ParkTableModel which displays the list of parks */
    private ParkTableModel ptm;
    /** Widths of columns */
    private int[] colWidths = { 50, 125, 300, 75 };

    /**
     * Constructs list of parks
     * 
     * @param parks list of parks
     */
    public ParkListPane(ParkList parks) {
        super();
        this.parks = parks;
        parks.addObserver(this);
        ptm = new ParkTableModel(parks.get2DArray());
        initView();
    }

    /**
     * Initialize view
     */
    private void initView() {
        table = new JTable(ptm);
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
     * This method is called by the observed object, whenever the observed object is
     * changed.
     * 
     * @param o the observable object
     * @param arg any additional information needed about the change.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (parks.equals(o)) {
            // If there is a different number of rows, create a show new
            // TableModel.
            if (parks.size() != ptm.getRowCount()) {
                ptm = new ParkTableModel(parks.get2DArray());
                table.setModel(ptm);
            } else {
                // Otherwise, just update the values directly.
                Object[][] arr = parks.get2DArray();
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < ptm.getColumnCount(); j++) {
                        ptm.setValueAt(arr[i][j], i, j);
                    }
                }
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
        return ptm;
    }

}
