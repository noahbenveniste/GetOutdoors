package edu.ncsu.csc216.get_outdoors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.Park;
import edu.ncsu.csc216.get_outdoors.model.ParkList;
import edu.ncsu.csc216.get_outdoors.model.TrailList;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Unit tests for GetOutdoorsManager
 * 
 * @author Noah Benveniste
 */
public class GetOutdoorsManagerTest {

	/** Manager object to be used throughout testing */
	private GetOutdoorsManager manager;
	/** Name of valid file to be used */
	private static final String VALID_FILE = "test-files/NCSU.md";
	
	/**
	 * Sets up manager object for tests
	 */
	@Before
	public void setUp() {
		manager = new GetOutdoorsManager();
	}
	
	/**
	 * Tests openDataFile()
	 */
	@Test
	public void testOpenDataFile() {
		//Read in valid data file
		manager.openDataFile(VALID_FILE);
		
		//Check the contents of the manager
		assertEquals(3, manager.getActivities().size());
		assertEquals(2, manager.getParks().size());
		assertEquals(2, manager.getNumTrailLists());
		
		//Loading an invalid data files
		manager = null;
		manager = new GetOutdoorsManager();
		try {
			manager.openDataFile("test-files/Invalid.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("File must start with the ACTIVITY information.", e.getMessage());
		}
		
		try {
			manager.openDataFile("test-files/Invalid.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("File must start with the ACTIVITY information.", e.getMessage());
		}
	}
	
	/**
	 * Tests setFilename()
	 */
	@Test
	public void testSetFilename() {
		try {
			manager.setFilename("");
		} catch (IllegalArgumentException e) {
			assertNull(manager.getFilename());
		}
		manager.setFilename("file.md");
		assertEquals("file.md", manager.getFilename());
	}
	
	/**
	 * Tests getParks()
	 */
	@Test
	public void testGetParks() {
		manager.openDataFile(VALID_FILE);
		ParkList exp = new ParkList();
		exp.addPark("Main Campus", "Lovely park on Main Campus", 0.0);
		exp.addPark("Centennial Campus", "Park contained on Engineering Campus", 1.0);
		for (int i = 0; i < exp.size(); i++) {
			assertEquals(0, exp.getParkAt(i).compareTo(manager.getParks().getParkAt(i)));
		}
	}
	
	/**
	 * Tests saveDataFile()
	 */
	@Test
	public void testSaveDataFile() {
		//Read in valid data file
		manager.openDataFile(VALID_FILE);
		//Add an activity for all parks to use
		manager.getActivities().addActivity("Fish", "Freshwater fishing", false, 1);
		assertEquals(4, manager.getActivities().size());
		//Add a park; the manager will create an empty trail list for it
		manager.getParks().addPark("Pullen Park", "Located on the northern end of campus", 0.0);
		assertEquals(3, manager.getParks().size());
		assertEquals(3, manager.getNumTrailLists());
		//Add a trail to the new park's trail list with its own list of activities, taken from manager.getActivities()
		SortedArrayList<Activity> a = new SortedArrayList<Activity>();
		a.add(manager.getActivities().getActivityAt(0));
		manager.getTrailList(2).addTrail("Main trail", a, true, 0.0, 1.0, Difficulty.MODERATE);
		//Save the current manager contents to a data file
		manager.saveDataFile("test-files/Actual_TempExportedManager.md");
		
		//Test that the exported file can be read back in
		manager = null;
		manager = new GetOutdoorsManager();
		manager.openDataFile("test-files/Actual_TempExportedManager.md");
		assertEquals(4, manager.getActivities().size());
		assertEquals(3, manager.getParks().size());
		assertEquals(3, manager.getNumTrailLists());
		Activity exp = new Activity("act-2", "Run", "Running", false, 5);
		assertEquals(exp, manager.getTrailList(0).getTrailAt(0).getActivities().get(0));
		manager.saveDataFile("test-files/Actual_FinalExportedManager.md");
		//fail("Not yet implemented");
	}
	
	/**
	 * Tests addGetTrails()
	 */
	@Test
    public void testAddGetTrails() {
        setUp();
        Park park3 = new Park("park-3", "Park 3", "Park 3 is for threes");
        Park park4 = new Park("park-4", "Park 4", "Park 4 is for threes");
        Park park5 = new Park("park-5", "Park 5", "Park 5 is for threes");
        Park park6 = new Park("park-6", "Park 6", "Park 6 is for threes");
        TrailList trailList3 = new TrailList(park3);
        TrailList trailList4 = new TrailList(park4);
        TrailList trailList5 = new TrailList(park5);
        TrailList trailList6 = new TrailList(park6);
        
        // Tests that negative index throws IAE.
        TrailList shouldBeNull = null;
        try {
        	shouldBeNull = manager.getTrailList(-1);
        	fail("Should have thrown IOOBE.");
        } catch (IndexOutOfBoundsException e) {
        	assertNull(shouldBeNull);
        }
        
        // Tests that index equal to "trailLists" size throws IAE.
        try {
        	shouldBeNull = manager.getTrailList(manager.getNumTrailLists());
        	fail("Should have thrown IOOBE.");
        } catch (IndexOutOfBoundsException e) {
        	assertNull(shouldBeNull);
        }
        
        assertEquals(-1 , manager.addTrailList(null));
        assertEquals(0, manager.addTrailList(park3));
        assertEquals(1, manager.addTrailList(park4));
        assertEquals(2, manager.addTrailList(park5));
        assertEquals(3, manager.addTrailList(park6));
        assertEquals(-1 , manager.addTrailList(null));
        assertEquals(4, manager.getNumTrailLists());
        
        assertEquals(trailList3, manager.getTrailList(0));
        assertEquals(trailList4, manager.getTrailList(1));
        assertEquals(trailList5, manager.getTrailList(2));
        assertEquals(trailList6, manager.getTrailList(3));
        
        // Tests that negative index throws IAE.
        shouldBeNull = null;
        try {
        	shouldBeNull = manager.getTrailList(-1);
        	fail("Should have thrown IOOBE.");
        } catch (IndexOutOfBoundsException e) {
        	assertNull(shouldBeNull);
        }
        
        // Tests that index equal to "trailLists" size throws IAE.
        try {
        	shouldBeNull = manager.getTrailList(manager.getNumTrailLists());
        	fail("Should have thrown IOOBE.");
        } catch (IndexOutOfBoundsException e) {
        	assertNull(shouldBeNull);
        }
        
        // Testing getTrailLists().
        TrailList[] trailListArray = manager.getTrailLists();
        assertEquals(trailList3, trailListArray[0]);
        assertEquals(trailList4, trailListArray[1]);
        assertEquals(trailList5, trailListArray[2]);
        assertEquals(trailList6, trailListArray[3]);
    }
	
}
