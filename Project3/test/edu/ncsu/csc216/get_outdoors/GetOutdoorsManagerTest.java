package edu.ncsu.csc216.get_outdoors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		//fail("Not yet implemented");
	}
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
}
