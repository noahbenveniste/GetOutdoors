package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Panel for GUI components used for the Trail editing menu.
 * 
 * @author demills
 */
public class TrailListPane extends ListPane {

	/** Unique ID for serialization.  */
	private static final long serialVersionUID = 1L;
	/** The trails associated with the Park for this tab */
	private TrailList trails;
	/** The table model for the trail list */
	private TrailTableModel ttm;
	/** Widths of columns */
    private int[] colWidths = { 55, 110, 220, 55, 55, 55 };

	/**
	 * Constructs a new TrailListPane().
	 * 
	 * @param trails the trail list associated with the park for this pane/tab
	 */
	public TrailListPane(TrailList trails) {
		super();
		this.trails = trails;
		trails.addObserver(this);
		ttm = new TrailTableModel(trails.get2DArray());
		initView();
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

	/**
	 * Called when Observed objects have been modified.
	 * 
	 * @param obs the Observed object that's calling this method.
	 * @param arg the Observed object that's calling this method.
	 */
	@Override
	public void update(Observable obs, Object arg) {
		//If the TrailList associated with this observer has been updated
		if (obs.equals(trails)) {
			//If a Trail has been added or removed to/from the list
			if (trails.size() != ttm.getRowCount()) {
				ttm = new TrailTableModel(trails.get2DArray());
				table.setModel(ttm);
			} else {
				//Otherwise a Trail already in the list has been updated in some way
				Object[][] arr = trails.get2DArray();
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[i].length; j++) {
						ttm.setValueAt(arr[i][j], i, j);
					}
				}
			}
		}
	}

	/**
	 * Returns the TableModel that this TrailListPane maintains.
	 * 
	 * @return returns this TrailListPane's TableModel. 
	 */
	@Override
	public TableModel getTableModel() {
		return this.ttm;
	}
}