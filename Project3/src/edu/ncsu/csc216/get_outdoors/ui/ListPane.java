package edu.ncsu.csc216.get_outdoors.ui;

import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Maintains the list of objects.
 * 
 * @author Jessica Young Schmidt
 */
public abstract class ListPane extends JScrollPane implements Observer {
    /** Serial version UID */
    private static final long serialVersionUID = 1L;
    /** displays the list of objects */
    protected JTable table;

    /**
     * Clears the selection.
     */
    public void clearSelection() {
        table.clearSelection();
    }

    /**
     * Returns the JTable.
     * 
     * @return the JTable
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Returns the TableModel.
     * 
     * @return the TableModel
     */
    public abstract TableModel getTableModel();

}
