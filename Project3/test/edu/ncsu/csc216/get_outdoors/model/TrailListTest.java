package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Unit tests for TrailList
 * 
 * @author Noah Benveniste
 */
public class TrailListTest {

	/** TrailList reference to be used by test methods */
	private TrailList t;
	/** Park reference used to construct a TrailList */
	private Park p;
	
	/**
	 * Initializes objects to be used throughout testing
	 */
	@Before
	public void setUp() {
		p = new Park("park-0", "Pullen Park", "Located in Raleigh");
		t = new TrailList(p);
	}

	/**
	 * Tests TrailList constructor
	 */
	@Test
	public void testTrailList() {
		assertEquals("Pullen Park", t.getParkName());
		assertEquals(0, t.get2DArray().length);
	}
	
	/**
	 * Tests addTrail()
	 */
	@Test
	public void testAddTrail() {
		//Try adding an invalid trail
		assertFalse(t.addTrail(null, new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(0, t.get2DArray().length);
		
		//Add a valid trail, check that its ID is correct
		assertTrue(t.addTrail("Valid Trail 1", new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(1, t.get2DArray().length);
		assertEquals("park-0-0", t.get2DArray()[0][0]);
		
		//Try to add another invalid trail
		assertFalse(t.addTrail(null, new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(1, t.get2DArray().length);
		
		//Add another valid trail, check that its ID is correct
		assertTrue(t.addTrail("Valid Trail 2", new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(2, t.get2DArray().length);
		assertEquals("park-0-1", t.get2DArray()[1][0]);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetTrailAtIndex() {
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testRemoveTrailInt() {
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testRemoveTrailString() {
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testGet2DArray() {
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testGet2DArrayNoMaintenance() {
		
	}
	
	/**
	 * Tests get2DArray(Activity)
	 */
	public void testGet2DArrayActivity() {
		Activity a0 = new Activity("act-0", "bike", "mountain biking", false, 1);
		Activity a1 = new Activity("act-1", "ski", "skiing", true, 4);
		Activity a2 = new Activity("act-2", "run", "running", false, 2);
		Activity a3 = new Activity("act-3", "ultimate frisbee", "frisbee", false, 5);
		Activity a4 = new Activity("act-4", "snowshoe", "showshoeing", true, 8);
	}
	
	/**
	 * 
	 */
	public void testEquals() {
		
	}
	
	/**
	 * 
	 */
	public void testHashCode() {
		
	}

}
