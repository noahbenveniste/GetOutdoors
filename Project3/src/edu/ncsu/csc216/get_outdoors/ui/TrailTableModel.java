package edu.ncsu.csc216.get_outdoors.ui;

/**
 * Handles interaction between model and GUI for displaying Trail data
 * 
 * @author Noah Benveniste
 * @author Daniel Mills
 */
public class TrailTableModel extends TableModel {

    /** */
	private static final long serialVersionUID = 1L;

	public TrailTableModel(Object[][] data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data getRowData(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(Data d, int row) {
		// TODO Auto-generated method stub

	}

}
