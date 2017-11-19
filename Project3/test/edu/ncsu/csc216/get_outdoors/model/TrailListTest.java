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
		assertEquals(0, t.size());
		
		//Add a valid trail, check that its ID is correct
		assertTrue(t.addTrail("Valid Trail 1", new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(1, t.size());
		assertEquals("park-0-0", t.get2DArray()[0][0]);
		
		//Try to add another invalid trail
		assertFalse(t.addTrail(null, new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(1, t.size());
		
		//Add another valid trail, check that its ID is correct
		assertTrue(t.addTrail("Valid Trail 2", new SortedArrayList<Activity>(), false, 0.0, 1.0, Difficulty.EASY));
		assertEquals(2, t.size());
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
		Activity a0 = new Activity("act-0", "bike", "mountain biking", false, 1);
		Activity a1 = new Activity("act-1", "ski", "skiing", true, 4);
		Activity a2 = new Activity("act-2", "run", "running", false, 2);
		Activity a3 = new Activity("act-3", "ultimate frisbee", "frisbee", false, 5);
		Activity a4 = new Activity("act-4", "snowshoe", "showshoeing", true, 8);
		
		SortedArrayList<Activity> a = new SortedArrayList<Activity>();
		a.add(a0);
		a.add(a1);
		a.add(a2);
		a.add(a3);
		a.add(a4);
		
		t.addTrail("Trail 1", a, true, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 2", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 3", a, true, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 4", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 5", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 6", a, true, 0.0, 1.0, Difficulty.EASY);
		
		Object[][] o = t.get2DArrayNoMaintenance();
		assertEquals(3, o.length);
		
		assertEquals("park-0-1", o[0][0]);
		assertEquals("Trail 2", o[0][1]);
		assertEquals(false, o[0][2]);
		assertEquals(0.0, o[0][3]);
		assertEquals(1.0, o[0][4]);
		assertEquals(Difficulty.EASY, o[0][5]);
		assertEquals(a, o[0][6]);
		
		assertEquals("park-0-3", o[1][0]);
		assertEquals("Trail 4", o[1][1]);
		assertEquals(false, o[1][2]);
		assertEquals(0.0, o[1][3]);
		assertEquals(1.0, o[1][4]);
		assertEquals(Difficulty.EASY, o[1][5]);
		assertEquals(a, o[1][6]);
		
		assertEquals("park-0-4", o[2][0]);
		assertEquals("Trail 5", o[2][1]);
		assertEquals(false, o[2][2]);
		assertEquals(0.0, o[2][3]);
		assertEquals(1.0, o[2][4]);
		assertEquals(Difficulty.EASY, o[2][5]);
		assertEquals(a, o[2][6]);
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
