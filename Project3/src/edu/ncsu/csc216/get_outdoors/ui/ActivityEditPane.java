package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

/**
 * Panel for editing Activity
 * 
 * @author Jessica Young Schmidt
 */
public class ActivityEditPane extends EditPane {
    /** Serial version UID */
    private static final long serialVersionUID = -8412701545351090646L;
    /** Activity data */
    private ActivityData data;
    /** Text fields for ID, Name, and snow bounary */
    private JTextField txtActivityID, txtActivityName, txtSnowBoundary;
    /** Text area for activity description */
    private JTextArea txtActivityDescription;
    /** Checkbox for whether snow is needed */
    private JCheckBox txtNeedSnow;

    /**
     * Creates a new edit pane with null ActivityData.
     */
    public ActivityEditPane() {
        this(null);
    }

    /**
     * Creates a new edit pane with the given ActivityData.
     * 
     * @param data information to populate the pane with
     */
    public ActivityEditPane(ActivityData data) {
        super();
        this.data = data;
        init();
        disableEdit();
    }

    /**
     * Returns text field for ID
     * 
     * @return the txtActivityID
     */
    JTextField getTxtActivityID() {
        if (null == txtActivityID) {
            txtActivityID = new JTextField();
            txtActivityID.setColumns(5);
            txtActivityID.setEditable(false);
            txtActivityID.setVisible(true);
            txtActivityID.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtActivityID;
    }

    /**
     * Returns text field for name
     * 
     * @return the txtActivityName
     */
    JTextField getTxtActivityName() {
        if (null == txtActivityName) {
            txtActivityName = new JTextField();
            txtActivityName.setColumns(20);
            txtActivityName.setEditable(false);
            txtActivityName.setVisible(true);
            txtActivityName.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtActivityName;
    }

    /**
     * Returns text field for snow boundary
     * 
     * @return the snowBoundary
     */
    JTextField getSnowBoundary() {
        if (null == txtSnowBoundary) {
            txtSnowBoundary = new JTextField();
            txtSnowBoundary.setColumns(5);
            txtSnowBoundary.setEditable(false);
            txtSnowBoundary.setVisible(true);
            txtSnowBoundary.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtSnowBoundary;
    }

    /**
     * Returns text area for description
     * 
     * @return the txtActivityDescription
     */
    JTextArea getTxtActivityDescription() {
        if (null == txtActivityDescription) {
            txtActivityDescription = new JTextArea(7, 80);
            txtActivityDescription.setEditable(false);
            txtActivityDescription.setVisible(true);
            txtActivityDescription.setLineWrap(true);
            txtActivityDescription.setAutoscrolls(true);
        }
        return txtActivityDescription;
    }

    /**
     * Returns checkbox for whether snow is needed
     * 
     * @return the needSnow
     */
    JCheckBox getNeedSnow() {
        if (null == txtNeedSnow) {
            txtNeedSnow = new JCheckBox();
            txtNeedSnow.setVisible(true);
        }
        return txtNeedSnow;
    }

    /**
     * Adds the given DocumentListener to the text fields.
     * 
     * @param l DocumentListener to add to text fields
     */
    @Override
    void addFieldListener(DocumentListener l) {
        getTxtActivityName().getDocument().addDocumentListener(l);
        getTxtActivityDescription().getDocument().addDocumentListener(l);
    }

    /**
     * Fills the fields with the appropriate text from the Data field.
     */
    @Override
    void fillFields() {
        if (null == data) {
            txtActivityID.setText("");
            txtActivityName.setText("");
            txtActivityDescription.setText("");
            txtSnowBoundary.setText("");
            txtNeedSnow.setSelected(false);

            txtActivityID.setEditable(false);
            txtActivityName.setEditable(false);
            txtActivityDescription.setEditable(false);
            txtSnowBoundary.setEditable(false);
            txtNeedSnow.setEnabled(false);
        } else {
            txtActivityID.setText(data.getActivityID());
            txtActivityName.setText(data.getName());
            txtActivityDescription.setText(data.getDescription());
            txtSnowBoundary.setText("" + data.getSnowBoundary());
            txtNeedSnow.setSelected(data.snowNeeded());
        }
        if (add) {
            txtActivityName.setEditable(true);
        }
        if (edit || add) {
            // txtActivityID.setEditable(true);
            txtActivityDescription.setEditable(true);
            txtSnowBoundary.setEditable(true);
            txtNeedSnow.setEnabled(true);
        }
    }

    /**
     * Checks that certain fields are not empty
     */
    @Override
    boolean fieldsNotEmpty() {

        return getTxtActivityName().getDocument().getLength() != 0
                && getTxtActivityDescription().getDocument().getLength() != 0;
    }

    /**
     * Sets data to d
     * 
     * @param d data to set field to
     */
    @Override
    void setData(Data d) {
        data = (ActivityData) d;
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
            data = (ActivityData) d;
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
     * Initialize view
     */
    @Override
    protected void initView() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Activity ID: ", SwingConstants.LEFT));
        p.add(this.getTxtActivityID());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Activity Name: ", SwingConstants.LEFT));
        p.add(this.getTxtActivityName());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Activity Description: ", SwingConstants.LEFT));
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(this.getTxtActivityDescription());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Need Snow? ", SwingConstants.LEFT));
        p.add(this.getNeedSnow());
        p.add(new JLabel("Snow Boundary: ", SwingConstants.LEFT));
        p.add(this.getSnowBoundary());
        this.add(p);
    }

    /**
     * Returns the fields as a Data object.
     * 
     * @return the fields as a Data object
     */
    @Override
    Data getFields() {
        return new ActivityData(this.getTxtActivityID().getText(),
                this.getTxtActivityName().getText(), this.getTxtActivityDescription().getText(),
                this.getNeedSnow().isSelected(),
                Integer.parseInt(this.getSnowBoundary().getText()));
    }

}
