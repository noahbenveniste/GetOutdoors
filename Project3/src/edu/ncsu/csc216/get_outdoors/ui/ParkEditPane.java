package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

/**
 * Panel for editing Park
 * 
 * @author Jessica Young Schmidt
 */
public class ParkEditPane extends EditPane {

    /** Serial version UID */
    private static final long serialVersionUID = 4001387904803487644L;
    /** Data */
    private ParkData data;
    /** Various text fields used throughout */
    private JTextField txtParkID, txtParkName, txtSnowChange;
    /** Text input area */
    private JTextArea txtParkDescription;

    /**
     * Creates a new edit pane with null ParkData
     */
    public ParkEditPane() {
        this(null);
    }

    /**
     * Returns text field for ID
     * 
     * @return the txtParkID
     */
    JTextField getTxtParkID() {
        if (null == txtParkID) {
            txtParkID = new JTextField();
            txtParkID.setColumns(5);
            txtParkID.setEditable(false);
            txtParkID.setVisible(true);
            txtParkID.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtParkID;
    }

    /**
     * Returns text field for name
     * 
     * @return the txtParkName
     */
    JTextField getTxtParkName() {
        if (null == txtParkName) {
            txtParkName = new JTextField();
            txtParkName.setColumns(20);
            txtParkName.setEditable(false);
            txtParkName.setVisible(true);
            txtParkName.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtParkName;
    }

    /**
     * Returns text field for snow change
     * 
     * @return the txtSnowChange
     */
    JTextField getTxtSnowChange() {
        if (null == txtSnowChange) {
            txtSnowChange = new JTextField();
            txtSnowChange.setColumns(5);
            txtSnowChange.setEditable(false);
            txtSnowChange.setVisible(true);
            txtSnowChange.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtSnowChange;
    }

    /**
     * Returns text area for description
     * 
     * @return the txtParkDescription
     */
    JTextArea getTxtParkDescription() {
        if (null == txtParkDescription) {
            txtParkDescription = new JTextArea(7, 80);
            txtParkDescription.setEditable(false);
            txtParkDescription.setVisible(true);
            txtParkDescription.setLineWrap(true);
            txtParkDescription.setAutoscrolls(true);
        }
        return txtParkDescription;
    }

    /**
     * Creates a new edit pane with the given ParkData.
     * 
     * @param data information to populate the pane with
     */
    public ParkEditPane(ParkData data) {
        super();
        this.data = data;
        init();
        disableEdit();
    }

    /**
     * Initialize view
     */
    @Override
    protected void initView() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Park ID: ", SwingConstants.LEFT));
        p.add(this.getTxtParkID());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Park Name: ", SwingConstants.LEFT));
        p.add(this.getTxtParkName());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Park Description: ", SwingConstants.LEFT));
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(this.getTxtParkDescription());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Latest Snowfall (snow change): ", SwingConstants.LEFT));
        p.add(this.getTxtSnowChange());
        this.add(p);
    }

    /**
     * Sets data to d
     * 
     * @param d data to set field to
     */
    @Override
    void setData(Data d) {
        data = (ParkData) d;
        fillFields();
    }

    /**
     * Enables edit mode and disables add.
     * 
     * @param d Data to populate the edit area with
     */
    @Override
    void enableEdit(Data d) {
        if (!edit) {
            edit = true;
            data = (ParkData) d;
            fillFields();
        }
    }

    /**
     * Clears the fields by setting data to null.
     */
    @Override
    void clearFields() {
        data = null;
        fillFields();
    }

    /**
     * Adds the given DocumentListener to the text fields.
     * 
     * @param l DocumentListener to add to text fields
     */
    @Override
    void addFieldListener(DocumentListener l) {
        getTxtParkName().getDocument().addDocumentListener(l);
        getTxtParkDescription().getDocument().addDocumentListener(l);
        getTxtSnowChange().getDocument().addDocumentListener(l);
    }

    /**
     * Fills the fields with the appropriate text from the Data field.
     */
    @Override
    void fillFields() {
        if (null == data) {
            txtParkID.setText("");
            txtParkName.setText("");
            txtParkDescription.setText("");
            txtSnowChange.setText("");

            txtParkID.setEditable(false);
            txtParkName.setEditable(false);
            txtParkDescription.setEditable(false);
            txtSnowChange.setEditable(false);
        } else {
            txtParkID.setText(data.getParkID());
            txtParkName.setText(data.getName());
            txtParkDescription.setText(data.getDescription());
            txtSnowChange.setText("" + data.getSnowChange());
        }
        if (add) {
            txtParkName.setEditable(true);
            txtParkDescription.setEditable(true);
        }
        if (edit || add) {
            txtSnowChange.setEditable(true);
        }
    }

    /**
     * Checks that certain fields are not empty
     */
    @Override
    boolean fieldsNotEmpty() {

        return getTxtParkName().getDocument().getLength() != 0
                && getTxtParkDescription().getDocument().getLength() != 0
                && getTxtSnowChange().getDocument().getLength() != 0;
    }

    /**
     * Returns the fields as a Data object.
     * 
     * @return the fields as a Data object
     */
    @Override
    Data getFields() {

        return new ParkData(this.getTxtParkID().getText(), this.getTxtParkName().getText(),
                this.getTxtParkDescription().getText(),
                Double.parseDouble(this.getTxtSnowChange().getText()));
    }

}
