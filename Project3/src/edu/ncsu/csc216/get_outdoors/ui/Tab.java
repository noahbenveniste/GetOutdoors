package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Represents the tab the Get Outdoors! application.
 * 
 * @author Jessica Young Schmidt
 */
public abstract class Tab extends JPanel
        implements ActionListener, ListSelectionListener, DocumentListener, ChangeListener {
    /** Serial version UID */
    private static final long serialVersionUID = -5033744302753009357L;
    /** ListPane in the tab (upper half) */
    private ListPane listPane;
    /** EditPane in the tab (lower half) */
    private EditPane editPane;
    /** EditButtonPanel that holds all actions for the tab */
    private EditButtonPanel editButtonPanel;
    /** Flag if in add mode */
    protected boolean addMode;
    /** Flag is in edit mode */
    protected boolean editMode;

    /**
     * Constructs a Tab
     */
    public Tab() {
        super();

        // Initially there is no add/edit mode because the user hasn't selected
        // an action
        addMode = false;
        editMode = false;
    }

    /**
     * Gets the ListPane
     * 
     * @return the listPane
     */
    protected ListPane getListPane() {
        return listPane;
    }

    /**
     * Sets the ListPane
     * 
     * @param listPane the listPane to set
     */
    protected void setListPane(ListPane listPane) {
        this.listPane = listPane;
    }

    /**
     * Gets the EditPane
     * 
     * @return the editPane
     */
    protected EditPane getEditPane() {
        return editPane;
    }

    /**
     * Sets the EditPane
     * 
     * @param editPane the editPane to set
     */
    protected void setEditPane(EditPane editPane) {
        this.editPane = editPane;
    }

    /**
     * Gets the EditButtonPanel
     * 
     * @return the editButtonPanel
     */
    protected EditButtonPanel getEditButtonPanel() {
        return editButtonPanel;
    }

    /**
     * Sets the EditButtonPanel
     * 
     * @param editButtonPanel the editButtonPanel to set
     */
    protected void setEditButtonPanel(EditButtonPanel editButtonPanel) {
        this.editButtonPanel = editButtonPanel;
    }

    /**
     * Gets the AddMode
     * 
     * @return the addMode
     */
    protected boolean isAddMode() {
        return addMode;
    }

    /**
     * Sets the AddMode
     * 
     * @param addMode the addMode to set
     */
    protected void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    /**
     * Gets the EditMode
     * 
     * @return the editMode
     */
    protected boolean isEditMode() {
        return editMode;
    }

    /**
     * Sets the EditMode
     * 
     * @param editMode the editMode to set
     */
    protected void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    /**
     * Called when the user selects the option to add a new item. Any previously
     * selected item is cleared, addMode is set to true, and the editPane fields are
     * set to enabled, which allows a user to enter text.
     */
    public void add() {
        listPane.clearSelection();
        addMode = true;
        editPane.enableAdd();
    }

    /**
     * Called when the user selects the option to delete a selected item. If there
     * is no item selected, a pop-up is displayed. If an item is selected, the item
     * is removed from the List, and the remaining fields are reset to default
     * (non-editing) states.
     */
    public abstract void delete();

    /**
     * Returns the EditButtonPanel. If the EditButtonPanel hasn't been created
     * before, The current object is added as an ActionListener to each button.
     * 
     * @return a panel of buttons for interacting with the Tab.
     */
    protected EditButtonPanel getButtons() {
        if (null == editButtonPanel) {
            editButtonPanel = new EditButtonPanel();
            editButtonPanel.getAddButton().addActionListener(this);
            editButtonPanel.getSaveButton().addActionListener(this);
            editButtonPanel.getCancelButton().addActionListener(this);
            editButtonPanel.setVisible(true);
        }
        return editButtonPanel;
    }

    /**
     * Disables all buttons so that the user cannot select them. Prevents users from
     * completing inappropriate actions.
     */
    private void disableButtons() {
        editButtonPanel.getAddButton().setEnabled(false);
        editButtonPanel.getSaveButton().setEnabled(false);
        editButtonPanel.getCancelButton().setEnabled(false);
    }

    /**
     * Enables the Tab for adding a new item if the flag is true. Otherwise, the Tab
     * is disabled for adding a new item.
     * 
     * @param flag true if enabling for add
     */
    protected void enableAdd(boolean flag) {
        if (flag) {
            editButtonPanel.getAddButton().setEnabled(true);
            editButtonPanel.getSaveButton().setEnabled(false);
            editButtonPanel.getCancelButton().setEnabled(true);
            editPane.enableAdd();
            listPane.clearSelection();
        } else {
            addMode = false;
            disableButtons();
            editPane.disableAdd();
        }
    }

    /**
     * Enables the Tab for saving an edited item if the flag is true. Otherwise, the
     * Tab is disabled for saving an edited item.
     * 
     * @param flag true if enabling for save
     */
    protected void enableSave(boolean flag) {
        if (flag) {
            editButtonPanel.getAddButton().setEnabled(false);
            editButtonPanel.getSaveButton().setEnabled(true);
            editButtonPanel.getCancelButton().setEnabled(true);
        } else {
            editMode = false;
            disableButtons();
            editPane.disableEdit();
        }
    }

    /**
     * A DocumentEvent happens when a user starts typing in the text fields. If a
     * DocumentEvent occurs, this method will enable or disable the appropriate
     * buttons depending on the mode.
     * 
     * @param e DocuementEvent from the user typing in a field in the Tab
     */
    private void handleDocEvent(DocumentEvent e) {
        if (editPane.fieldsNotEmpty()) {
            if (addMode) {
                enableAdd(true);
            } else if (editMode) {
                enableSave(true);
            }
        } else {
            disableButtons();
        }
    }

    /**
     * Method inherited from DocumentListener. If there's an insert,
     * handleDocEvent() is called.
     * 
     * @param e DocuementEvent from the user editing a field in Tab
     */
    public void insertUpdate(DocumentEvent e) {
        handleDocEvent(e);
    }

    /**
     * Method inherited from DocumentListener. If there's a remove, handleDocEvent()
     * is called.
     * 
     * @param e DocuementEvent from the user editing a field in Tab
     */
    public void removeUpdate(DocumentEvent e) {
        handleDocEvent(e);
    }

    /**
     * Method inherited from DocumentListener. If there's a change, handleDocEvent()
     * is called.
     * 
     * @param e DocuementEvent from the user editing a field in Tab
     */
    public void changedUpdate(DocumentEvent e) {
        handleDocEvent(e);
    }

    /**
     * Method inherited from ActionListener. If there is an add, save, or cancel
     * action (which correspond to the three buttons), this method is called. The
     * model is updated (or not) depending on the action.
     * 
     * @param e ActionEvent that represents the user's interaction with the GUI.
     */
    public abstract void actionPerformed(ActionEvent e);

    /**
     * Method inherited from ListSelectionListener. Anytime the user interacts with
     * the JTable that contains the List (as represented by the TableModel), this
     * method will be called. The method will populate the EditPane fields with the
     * information.
     * 
     * @param e ListSelectionEvent of a user selecting a row in the table
     */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        int row = listPane.getTable().getSelectedRow();
        if (row >= 0) {
            Data d = listPane.getTableModel().getRowData(row);
            editPane.setData(d);
            editMode = true;
            editPane.enableEdit(d);
        } else {
            editMode = false;
        }
    }

    /**
     * Sets the state as changed for the ChangeListener.
     * 
     * @param e ChangeEvent
     */
    public void stateChanged(ChangeEvent e) {
        // Not used
    }
}
