package edu.ncsu.csc216.get_outdoors.ui;

/**
 * ParkTableModel is a wrapper for the information in List that can be used by a
 * JTable.
 * 
 * @author Jessica Young Schmidt
 */
public class ParkTableModel extends TableModel {
    /** Serial version UID */
    private static final long serialVersionUID = 989231662934085844L;
    /** Names for each of the columns in the TableModel */
    private String[] colNames = { "ID", "Park Name", "Park Description", "Latest Snowfall/Change" };

    /**
     * Creates the model from the given data.
     * 
     * @param data the data to populate the TableModel
     */
    public ParkTableModel(Object[][] data) {
        super(data);
    }

    /**
     * Returns the column name at the given index.
     * 
     * @param col the index for finding the column name
     * @return the column name at the given index
     */
    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    /**
     * Returns the Data object associated with the given row in the TableModel.
     * 
     * @param row the Data to return
     * @return the Data for the given row
     */
    @Override
    public ParkData getRowData(int row) {
        return new ParkData((String) data[row][0], (String) data[row][1], (String) data[row][2],
                (Double) data[row][3]);
    }

    /**
     * Sets the given row with the provided Data.
     * 
     * @param d Data to set in the row
     * @param row the row to set
     */
    @Override
    public void setData(Data d, int row) {
        ParkData act = (ParkData) d;
        setValueAt(act.getParkID(), row, 0);
        setValueAt(act.getName(), row, 1);
        setValueAt(act.getDescription(), row, 2);
        setValueAt(act.getSnowChange(), row, 3);
    }

    /**
     * Returns the number of columns in the data.
     * 
     * @return the number of columns in the data
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }
}
