package edu.ncsu.csc216.get_outdoors.ui;

import javax.swing.table.AbstractTableModel;

/**
 * TableModel is a wrapper for the information in List that can be used by a
 * JTable.
 * 
 * @author David R. Wright
 * @author Sarah Heckman
 * @author Jessica Young Schmidt
 */
public abstract class TableModel extends AbstractTableModel {
    /** Serial version UID */
    private static final long serialVersionUID = 542998100968273770L;
    /** Array of data information */
    protected Object[][] data;

    /**
     * Creates the model from the given data.
     * 
     * @param data the data to populate the TableModel
     */
    public TableModel(Object[][] data) {
        super();
        this.data = data;
    }

    /**
     * Returns the number of rows in the data.
     * 
     * @return the number of rows in the data
     */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the data.
     * 
     * @return the number of columns in the data
     */
    public abstract int getColumnCount();

    /**
     * Returns the column name at the given index.
     * 
     * @param col the index for finding the column name
     * @return the column name at the given index
     */
    public abstract String getColumnName(int col);

    /**
     * Returns the value at the given cell in the TableModel.
     * 
     * @param row the index for the row
     * @param col the index for the column
     * @return the value in the data at the given row and col
     */
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /**
     * Sets the value to the given cell in the TableModel.
     * 
     * @param value the value to set
     * @param row the index for the row
     * @param col the index for the column
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    /**
     * Returns the Data object associated with the given row in the TableModel.
     * 
     * @param row the Data to return
     * @return the Data for the given row
     */
    public abstract Data getRowData(int row);

    /**
     * Sets the given row with the provided Data.
     * 
     * @param d Data to set in the row
     * @param row the row to set
     */
    public abstract void setData(Data d, int row);

}
