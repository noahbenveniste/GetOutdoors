package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.DocumentListener;

/**
 * Panel for editing
 * 
 * @author Jessica Young Schmidt
 */
public abstract class EditPane extends JPanel {
    /** Serial version UID */
    private static final long serialVersionUID = 2282168724908674161L;
    /** Currently editing? */
    protected boolean add;
    /** Currently adding? */
    protected boolean edit;

    /**
     * Constructing EditPane with add and edit as false
     */
    public EditPane() {
        super();
        add = false;
        edit = false;
    }

    /**
     * Initialize panel
     */
    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        initView();
    }

    /**
     * Initialize view
     */
    protected abstract void initView();

    /**
     * Enable ability to add
     */
    void enableAdd() {
        if (!add) {
            add = true;
            edit = false;
            clearFields();
        }
    }

    /**
     * Disable ability to add
     */
    void disableAdd() {
        add = false;
        clearFields();
    }

    /**
     * Disable ability to edit
     */
    void disableEdit() {
        edit = false;
        clearFields();
    }

    /**
     * Sets data to d
     * 
     * @param d data to set field to
     */
    abstract void setData(Data d);

    /**
     * Enables edit mode and disables add.
     * 
     * @param d Data to populate the edit area with
     */
    abstract void enableEdit(Data d);

    /**
     * Clears the fields by setting data to null.
     */
    abstract void clearFields();

    /**
     * Adds the given DocumentListener to the text fields.
     * 
     * @param docListener DocumentListener to add to text fields
     */
    abstract void addFieldListener(DocumentListener l);

    /**
     * Fills the fields with the appropriate text from the Data field.
     */
    abstract void fillFields();

    /**
     * Checks that certain fields are not empty
     */
    abstract boolean fieldsNotEmpty();

    /**
     * Returns the fields as a Data object.
     * 
     * @return the fields as a Data object
     */
    abstract Data getFields();

}
