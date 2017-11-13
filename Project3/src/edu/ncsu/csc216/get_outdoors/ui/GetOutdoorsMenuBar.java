package edu.ncsu.csc216.get_outdoors.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * The GetOutdoorsMenuBar bar that holds all major GetOutdoors operations.
 * 
 * @author David R. Wright
 * 
 *         Early versions for other Menu Bars
 * 
 * @author Jessica Young Schmidt
 */
public class GetOutdoorsMenuBar extends JMenuBar {

    /** Serial version UID */
    private static final long serialVersionUID = -494084892866088182L;
    /** Names for the File menu items */
    private static final String[] FILE_MENU_NAMES = { "Open File", "Save File", "Exit" };
    /** Keyboard shortcuts for the File menu items */
    private static final int[] FILE_MENU_KEYS = { KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_X };
    /** ActionCommand for File menu items */
    private static final String[] FILE_MENU_COMMANDS = { "11", "12", "13" };
    /** Names for the Park menu items */
    private static final String[] PARK_MENU_NAMES = { "New Park" };
    /** Keyboard shortcuts for the Park menu items */
    private static final int[] PARK_MENU_KEYS = { KeyEvent.VK_N };
    /** ActionCommands for Park menu items */
    private static final String[] PARK_MENU_COMMANDS = { "21" };
    /** Names for the Trail menu items */
    private static final String[] TRAIL_MENU_NAMES = { "Add New Trail", "Delete Trail" };
    /** Keyboard shortcuts for the Trail menu items */
    private static final int[] TRAIL_MENU_KEYS = { KeyEvent.VK_A, KeyEvent.VK_D };
    /** ActionCommands for Trail menu items */
    private static final String[] TRAIL_MENU_COMMANDS = { "31", "32" };
    /** Names for the Activity menu items */
    private static final String[] ACTIVITY_MENU_NAMES = { "Add Activity" };
    /** Keyboard shortcuts for the Activity menu items */
    private static final int[] ACTIVITY_MENU_KEYS = { KeyEvent.VK_A };
    /** ActionCommands for the Activity menu items */
    private static final String[] ACTIVITY_MENU_COMMANDS = { "41" };
    /** Reference to GetOutdoorsGUI */
    private GetOutdoorsGUI parent;

    /**
     * Creates the GetOutdoorsMenuBar for the GetOutdoorsGUI.
     * 
     * @param parent the GetOutdoorsGUI that created the GetOutdoorsMenuBar
     */
    public GetOutdoorsMenuBar(GetOutdoorsGUI parent) {
        super();
        this.parent = parent;
        initMenu();
    }

    /**
     * Initializes the Menu bar by adding each of the Menus.
     */
    private void initMenu() {
        this.add(fileMenu());
        this.add(parkMenu());
        this.add(trailMenu());
        this.add(activityMenu());
    }

    /**
     * Creates the File menu.
     * 
     * @return the File menu
     */
    private JMenu fileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        addMenuItems(menu, FILE_MENU_NAMES, FILE_MENU_KEYS, FILE_MENU_COMMANDS);
        return menu;
    }

    /**
     * Creates the Park menu
     * 
     * @return the Park menu
     */
    private JMenu parkMenu() {
        JMenu menu = new JMenu("Parks");
        menu.setMnemonic(KeyEvent.VK_L);
        addMenuItems(menu, PARK_MENU_NAMES, PARK_MENU_KEYS, PARK_MENU_COMMANDS);
        return menu;
    }

    /**
     * Creates the Trail menu
     * 
     * @return the Trail menu
     */
    private JMenu trailMenu() {
        JMenu menu = new JMenu("Trails");
        menu.setMnemonic(KeyEvent.VK_T);
        addMenuItems(menu, TRAIL_MENU_NAMES, TRAIL_MENU_KEYS, TRAIL_MENU_COMMANDS);
        return menu;
    }

    /**
     * Creates the Activity menu
     * 
     * @return the Activity menu
     */
    private JMenu activityMenu() {
        JMenu menu = new JMenu("Activities");
        menu.setMnemonic(KeyEvent.VK_C);
        addMenuItems(menu, ACTIVITY_MENU_NAMES, ACTIVITY_MENU_KEYS, ACTIVITY_MENU_COMMANDS);
        return menu;
    }

    /**
     * Creates MenuItems with the given names, hot keys, and command values and adds
     * them to the given Menu.
     * 
     * @param menu JMenu to add MenuItems to
     * @param itemNames name for the MenuItems
     * @param hotkeys keyboard shortcut for the MenuItems
     * @param cmds command strings for the MenuItems
     */
    private void addMenuItems(JMenu menu, String[] itemNames, int[] hotkeys, String[] cmds) {
        for (int i = 0; i < itemNames.length; i++) {
            JMenuItem item = new JMenuItem(itemNames[i], hotkeys[i]);
            item.setActionCommand(cmds[i]);
            item.addActionListener(parent);
            menu.add(item);
        }
    }
}
