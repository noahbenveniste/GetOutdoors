package edu.ncsu.csc216.get_outdoors.ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.GetOutdoorsManager;
import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * GUI for the Get Outdoors! application.
 * 
 * @author Jessica Young Schmidt
 */
public class GetOutdoorsGUI extends JFrame implements ActionListener, WindowListener, Observer {
    /** Serial version UID */
    private static final long serialVersionUID = 4315834200123762940L;

    /** Default (starting) number of tabs */
    private static final int NUM_TAB_DEFAULT = 3;

    /** Reference to the GetOutdoorsManager model */
    private GetOutdoorsManager getOutdoors;

    /**
     * Reference to the TabbedPane that holds the ActivityTab, ParkTabs,
     * TrailListTab
     */
    private JTabbedPane tabbedPane;

    /** Boolean that tracks if we are opening a GetOutdoorsManager file */
    private boolean openFile;

    /**
     * Constructs the GetOutdoorsGUI.
     * 
     * @param b the GetOutdoorsManager model shown by the GUI.
     */
    public GetOutdoorsGUI(GetOutdoorsManager b) {
        super("GetOutdoorsManager");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getOutdoors = b;
        getOutdoors.addObserver(this);
        openFile = false;
        initGUI();
        this.addWindowListener(this);
    }

    /**
     * Returns the GetOutdoorsManager instance associated with the
     * GetOutdoorsManager GUI.
     * 
     * @return the GetOutdoorsManager instance associated with the GUI.
     */
    public GetOutdoorsManager getGetOutdoorsManager() {
        return getOutdoors;
    }

    /**
     * Initializes the GUI.
     */
    private void initGUI() {
        setPreferredSize(new Dimension(1000, 650));
        setJMenuBar(new GetOutdoorsMenuBar(this));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        initTabbedPane();
    }

    /**
     * Initializes the TabbedPane that contains the tabs for activities, parks,
     * trail lists, and filtering in the GetOutdoorsManager system.
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab(getOutdoors.getActivities().getName(),
                new ActivityTab(getOutdoors.getActivities()));
        tabbedPane.addTab(getOutdoors.getParks().getName(), new ParkTab(getOutdoors.getParks()));
        tabbedPane.addTab("Filter Trails", new FilterTab(getOutdoors.getActivities(),
                getOutdoors.getTrailLists(), getOutdoors.getNumTrailLists()));

        for (int i = 0; i < getOutdoors.getNumTrailLists(); i++) {
            TrailList t = getOutdoors.getTrailList(i);
            tabbedPane.addTab(t.getParkName(), new TrailListTab(t, getOutdoors.getActivities()));
        }

        tabbedPane.setSelectedIndex(0);
        if (openFile) {
            getContentPane().removeAll();
            openFile = false;
        }

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().validate();
    }

    /**
     * Each command in the JMenuItem is given a number. The number determines which
     * action is taken for the given command passed in through the ActionEvent.
     * 
     * @param e ActionEvent representing user's interaction with the GUI.
     */
    public void actionPerformed(ActionEvent e) {
        if (null != e.getSource() && e.getSource() instanceof JMenuItem) {
            JMenuItem source = (JMenuItem) (e.getSource());
            String cmd = source.getActionCommand();
            switch (getInt(cmd)) {
            case 11:
                doOpenFile();
                break;
            case 12:
                doSaveFile();
                break;
            case 13:
                doExit();
                break;
            case 21:
                doNewPark();
                break;
            case 31:
                doAddTrail();
                break;
            case 32:
                doDeleteTrail();
                break;
            case 41:
                doAddActivity();
                break;
            default:
                break;
            }
        }
    }

    /**
     * Provides a JFileChooser so a user can load a previous GetOutdoorsManager file
     * into the system.
     */
    private void doOpenFile() {
        try {
            JFileChooser chooser = new JFileChooser("./");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "GetOutdoorsManager files (md)", "md");
            chooser.setFileFilter(filter);
            chooser.setMultiSelectionEnabled(false);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                boolean open = getOutdoors
                        .openDataFile(chooser.getSelectedFile().getAbsolutePath());
                if (!open) {
                    JOptionPane.showMessageDialog(this, "Error opening file.", "Opening Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    openFile = true;
                    initTabbedPane();
                }
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error opening file.", "Opening Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Provides a JFileChooser so a user can select where to save their file.
     */
    private void doSaveFile() {
        JFileChooser chooser = new JFileChooser("./");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "GetOutdoorsManager files (md)", "md");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        if (getOutdoors.getFilename() != null) {
            chooser.setSelectedFile(new File(getOutdoors.getFilename()));
        }
        int returnVal = chooser.showSaveDialog(this);
        boolean saved = true;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            getOutdoors.setFilename(filename);
            saved = getOutdoors.saveDataFile(filename);
        } else {
            saved = false;
        }
        if (!saved) {
            JOptionPane.showMessageDialog(this, "File not saved.", "Saving Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * If the GetOutdoorsManager has changed since the last save, the user is
     * prompted to save the GetOutdoorsManager.
     */
    private void doExit() {
        if (getOutdoors.isChanged()) {
            doSaveFile();
        }
        // GetOutdoorsManager either has not changed or the file was saved properly in
        // the previous if block
        if (!getOutdoors.isChanged()) {
            System.exit(NORMAL);
        } else { // Did NOT save when prompted to save
            JOptionPane.showMessageDialog(this,
                    "GetOutdoorsManager changes have not been saved. "
                            + "You will not be able to exit until changes are saved.",
                    "Saving Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new Park to the system.
     */
    private void doNewPark() {
        tabbedPane.setSelectedIndex(1);
        ((ParkTab) tabbedPane.getSelectedComponent()).add();
    }

    /**
     * Handles adding a trail to the given TrailListTab. If there is no TrailList
     * selected, an error message is displayed.
     */
    private void doAddTrail() {
        int index = tabbedPane.getSelectedIndex();
        if (tabbedPane.getComponentAt(index) instanceof TrailListTab) {
            ((TrailListTab) tabbedPane.getSelectedComponent()).add();
        } else {
            JOptionPane.showMessageDialog(this, "Trail List not selected.", "Trail List Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Handles deleting the selected trail in the given TrailListTab. If there is no
     * TrailList selected, an error message is displayed.
     */
    private void doDeleteTrail() {
        int index = tabbedPane.getSelectedIndex();
        if (index > 2) {
            int optRes = JOptionPane.showConfirmDialog(this, "Confirm Trail Delete", null,
                    JOptionPane.YES_NO_OPTION);
            if (index > 2 && JOptionPane.YES_OPTION == optRes) {
                ((TrailListTab) tabbedPane.getSelectedComponent()).delete();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Trail not selected.", "Trail Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Handles the add command for a Activity.
     */
    private void doAddActivity() {
        tabbedPane.setSelectedIndex(0);
        ((ActivityTab) tabbedPane.getSelectedComponent()).add();
    }

    /**
     * Helper method for converting a string to an int. Since all ints in program
     * should be positive, the method returns -1 on error.
     * 
     * @param s String to convert
     * @return integer value
     */
    private int getInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Method inherited from WindowListener. Not used in this implementation.
     * 
     * @param e WindowEvent
     */
    public void windowOpened(WindowEvent e) {
        // not interested in this event
    }

    /**
     * Method inherited from WindowListener. When the window is closing, the
     * doExit() method will be called to handle the option for saving on exit. If
     * the cancel button is pressed in the FileChooser, the program will exit
     * without saving.
     * 
     * @param e WindowEvent
     */
    public void windowClosing(WindowEvent e) {
        doExit();
    }

    /**
     * Method inherited from WindowListener. Not used in this implementation.
     * 
     * @param e WindowEvent
     */
    public void windowClosed(WindowEvent e) {
        // not interested in this event
    }

    /**
     * Method inherited from WindowListener. Not used in this implementation.
     * 
     * @param e WindowEvent
     */
    public void windowIconified(WindowEvent e) {
        // not interested in this event
    }

    /**
     * Method inherited from WindowListener. Not used in this implementation.
     * 
     * @param e WindowEvent
     */
    public void windowDeiconified(WindowEvent e) {
        // not interested in this event
    }

    /**
     * Method inherited from WindowListener. Not used in this implementation.
     * 
     * @param e WindowEvent
     */
    public void windowActivated(WindowEvent e) {
        // not interested in this event
    }

    /**
     * Method inherited from WindowListener. Not used in this implementation.
     * 
     * @param e WindowEvent
     */
    public void windowDeactivated(WindowEvent e) {
        // not interested in this event
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
        if (o.equals(getOutdoors)) {

            int size = getOutdoors.getNumTrailLists();
            if (size > tabbedPane.getTabCount() - NUM_TAB_DEFAULT) {
                for (int i = 0; i < size; i++) {
                    TrailList tl = getOutdoors.getTrailList(i);
                    int ind = tabbedPane.indexOfTab(tl.getParkName());
                    if (ind == -1) {
                        tabbedPane.addTab(tl.getParkName(),
                                new TrailListTab(tl, getOutdoors.getActivities()));
                    }
                }
            }

            // any updates need to update the filter
            tabbedPane.setComponentAt(2, new FilterTab(getOutdoors.getActivities(),
                    getOutdoors.getTrailLists(), getOutdoors.getNumTrailLists()));

        }
    }

    /**
     * Starts the application.
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        GetOutdoorsManager go = new GetOutdoorsManager();
        GetOutdoorsGUI frame = new GetOutdoorsGUI(go);
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
