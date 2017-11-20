package edu.ncsu.csc216.get_outdoors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.model.Park;
import edu.ncsu.csc216.get_outdoors.model.TrailList;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Unit tests for GetOutdoorsManager
 * 
 * @author Noah Benveniste
 */
public class GetOutdoorsManagerTest {

	/** */
	private GetOutdoorsManager manager;
	
	private static final String VALID_FILE= "test-files/NCSU.md";
	
	@Before
	public void setUp() {
		manager = new GetOutdoorsManager();
	}

	@Test
	public void testGetOutdoorsManager() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testOpenDataFile() {
		//Read in valid data file
		manager.openDataFile(VALID_FILE);
		
		//Check the contents of the manager
		assertEquals(3, manager.getActivities().size());
		assertEquals(2, manager.getParks().size());
		assertEquals(2, manager.getNumTrailLists());
	}

	@Test
	public void testIsChanged() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetChanged() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetFilename() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetFilename() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetNumTrailLists() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetTrailListInt() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetTrailLists() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetActivities() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddTrailListPark() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetParks() {
		fail("Not yet implemented");
	}
	
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
		manager.saveDataFile("test-files/expected.md");
		
		//Test that the exported file can be read back in
		manager = null;
		manager = new GetOutdoorsManager();
		manager.openDataFile("test-files/expected.md");
		assertEquals(4, manager.getActivities().size());
		assertEquals(3, manager.getParks().size());
		assertEquals(3, manager.getNumTrailLists());
		Activity exp = new Activity("act-2", "Run", "Running", false, 5);
		assertEquals(exp, manager.getTrailList(0).getTrailAt(0).getActivities().get(0));
		manager.saveDataFile("test-files/expected2.md");
		//fail("Not yet implemented");
	}
	
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
    }
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
}
