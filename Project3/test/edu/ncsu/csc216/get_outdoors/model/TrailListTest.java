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
		//Construction with a null park
		TrailList invalid = null;
		try {
			invalid = new TrailList(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Park cannot be null", e.getMessage());
			assertNull(invalid);
		}
		assertEquals("Pullen Park", t.getParkName());
		assertEquals(0, t.get2DArray().length);
		assertTrue(t.isEmpty());
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
	 * Tests getTrailAt()
	 */
	@Test
	public void testGetTrailAt() {
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
		
		t.addTrail("Trail 1", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 2", a, false, 1.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 3", a, false, 2.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 4", a, false, 3.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 5", a, false, 4.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 6", a, false, 5.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 7", a, false, 6.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 8", a, false, 7.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 9", a, false, 8.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 10", a, false, 9.0, 1.0, Difficulty.EASY);
		
		Trail exp1 = new Trail("park-0-9", "Trail 10", a, false, 9.0, 1.0, Difficulty.EASY);
		Trail exp2 = new Trail("park-0-0", "Trail 1", a, false, 0.0, 1.0, Difficulty.EASY);
		Trail exp3 = new Trail("park-0-7", "Trail 8", a, false, 7.0, 1.0, Difficulty.EASY);
		Trail exp4 = new Trail("park-0-8", "Trail 9", a, false, 8.0, 1.0, Difficulty.EASY);
		
		try {
			t.getTrailAt(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside of the acceptable range", e.getMessage());
		}
		
		assertEquals(exp2, t.getTrailAt(0));
		assertEquals(exp1, t.getTrailAt(1));
		assertEquals(exp3, t.getTrailAt(8));
		assertEquals(exp4, t.getTrailAt(9));
	}
	
	/**
	 * Tests removeTrail(int)
	 */
	@Test
	public void testRemoveTrailInt() {
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
		
		t.addTrail("Trail 1", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 2", a, false, 1.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 3", a, false, 2.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 4", a, false, 3.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 5", a, false, 4.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 6", a, false, 5.0, 1.0, Difficulty.EASY);
		
		Object[][] exp = {
				{"park-0-0", "Trail 1", false, 0.0, 1.0, Difficulty.EASY, a}, 
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a},
				{"park-0-5", "Trail 6", false, 5.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o = t.get2DArray();
		for (int i = 0; i < o.length; i++) {
			for (int j = 0; j < o[i].length; j++) {
				assertEquals(exp[i][j], o[i][j]);
			}
		}
		
		//Try removing at an invalid index
		try {
			t.removeTrail(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside of the acceptable range", e.getMessage());
		}
		
		//Remove from the front
		t.removeTrail(0);
		Object[][] exp1 = {
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a},
				{"park-0-5", "Trail 6", false, 5.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o1 = t.get2DArray();
		for (int i = 0; i < o1.length; i++) {
			for (int j = 0; j < o1[i].length; j++) {
				assertEquals(exp1[i][j], o1[i][j]);
			}
		}
		
		//Remove from the back
		t.removeTrail(4);
		Object[][] exp2 = {
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o2 = t.get2DArray();
		for (int i = 0; i < o2.length; i++) {
			for (int j = 0; j < o2[i].length; j++) {
				assertEquals(exp2[i][j], o2[i][j]);
			}
		}
		
		//Remove from the middle
		t.removeTrail(2);
		Object[][] exp3 = {
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o3 = t.get2DArray();
		for (int i = 0; i < o3.length; i++) {
			for (int j = 0; j < o3[i].length; j++) {
				assertEquals(exp3[i][j], o3[i][j]);
			}
		}
	}
	
	/**
	 * Tests removeTrail(String)
	 */
	@Test
	public void testRemoveTrailString() {
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
		
		t.addTrail("Trail 1", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 2", a, false, 1.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 3", a, false, 2.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 4", a, false, 3.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 5", a, false, 4.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 6", a, false, 5.0, 1.0, Difficulty.EASY);
		
		Object[][] exp = {
				{"park-0-0", "Trail 1", false, 0.0, 1.0, Difficulty.EASY, a}, 
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a},
				{"park-0-5", "Trail 6", false, 5.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o = t.get2DArray();
		for (int i = 0; i < o.length; i++) {
			for (int j = 0; j < o[i].length; j++) {
				assertEquals(exp[i][j], o[i][j]);
			}
		}
		
		//Remove from the front
		t.removeTrail("park-0-0");
		Object[][] exp1 = {
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a},
				{"park-0-5", "Trail 6", false, 5.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o1 = t.get2DArray();
		for (int i = 0; i < o1.length; i++) {
			for (int j = 0; j < o1[i].length; j++) {
				assertEquals(exp1[i][j], o1[i][j]);
			}
		}
		
		//Remove from the back
		t.removeTrail("park-0-5");
		Object[][] exp2 = {
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o2 = t.get2DArray();
		for (int i = 0; i < o2.length; i++) {
			for (int j = 0; j < o2[i].length; j++) {
				assertEquals(exp2[i][j], o2[i][j]);
			}
		}
		
		//Remove from the middle
		t.removeTrail("park-0-3");
		Object[][] exp3 = {
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o3 = t.get2DArray();
		for (int i = 0; i < o3.length; i++) {
			for (int j = 0; j < o3[i].length; j++) {
				assertEquals(exp3[i][j], o3[i][j]);
			}
		}
	}
	
	/**
	 * Tests get2DArray()
	 */
	@Test
	public void testGet2DArray() {
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
		
		Object[][] exp = {
				{"park-0-0", "Trail 1", true, 0.0, 1.0, Difficulty.EASY, a},
				{"park-0-1", "Trail 2", false, 0.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", true, 0.0, 1.0, Difficulty.EASY, a}
		};
		Object[][] o = t.get2DArray();
		for (int i = 0; i < o.length; i++) {
			for (int j = 0; j < o[i].length; j++) {
				assertEquals(exp[i][j], o[i][j]);
			}
		}
	}
	
	/**
	 * Tests get2DArrayNoMaintenance()
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
	@Test
	public void testGet2DArrayActivity() {
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
		
		t.addTrail("Trail 1", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 2", a, false, 1.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 3", a, false, 2.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 4", a, false, 3.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 5", a, false, 4.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 6", a, false, 5.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 7", a, false, 6.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 8", a, false, 7.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 9", a, false, 8.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 10", a, false, 9.0, 1.0, Difficulty.EASY);
		
		try {
			t.get2DArray(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Activity cannot be null", e.getMessage());
		}
		
		Object[][] o1 = t.get2DArray(a0);
		Object[][] exp1 = {
				{"park-0-0", "Trail 1", false, 0.0, 1.0, Difficulty.EASY, a}, 
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a}
		};
		for (int i = 0; i < o1.length; i++) {
			for (int j = 0; j < o1[i].length; j++) {
				assertEquals(exp1[i][j], o1[i][j]);
			}
		}
		
		Object[][] o2 = t.get2DArray(a1);
		Object[][] exp2 = {
				{"park-0-9", "Trail 10", false, 9.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a},
				{"park-0-5", "Trail 6", false, 5.0, 1.0, Difficulty.EASY, a},
				{"park-0-6", "Trail 7", false, 6.0, 1.0, Difficulty.EASY, a},
				{"park-0-7", "Trail 8", false, 7.0, 1.0, Difficulty.EASY, a},
				{"park-0-8", "Trail 9", false, 8.0, 1.0, Difficulty.EASY, a}
		};
		for (int i = 0; i < o2.length; i++) {
			for (int j = 0; j < o2[i].length; j++) {
				assertEquals(exp2[i][j], o2[i][j]);
			}
		}
		
		Object[][] o3 = t.get2DArray(a2);
		Object[][] exp3 = {
				{"park-0-0", "Trail 1", false, 0.0, 1.0, Difficulty.EASY, a}, 
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a}
		};
		for (int i = 0; i < o3.length; i++) {
			for (int j = 0; j < o3[i].length; j++) {
				assertEquals(exp3[i][j], o3[i][j]);
			}
		}
		
		Object[][] o4 = t.get2DArray(a3);
		Object[][] exp4 = {
				{"park-0-0", "Trail 1", false, 0.0, 1.0, Difficulty.EASY, a}, 
				{"park-0-1", "Trail 2", false, 1.0, 1.0, Difficulty.EASY, a},
				{"park-0-2", "Trail 3", false, 2.0, 1.0, Difficulty.EASY, a},
				{"park-0-3", "Trail 4", false, 3.0, 1.0, Difficulty.EASY, a},
				{"park-0-4", "Trail 5", false, 4.0, 1.0, Difficulty.EASY, a},
				{"park-0-5", "Trail 6", false, 5.0, 1.0, Difficulty.EASY, a}
		};
		for (int i = 0; i < o4.length; i++) {
			for (int j = 0; j < o4[i].length; j++) {
				assertEquals(exp4[i][j], o4[i][j]);
			}
		}
		
		Object[][] o5 = t.get2DArray(a4);
		Object[][] exp5 = {
				{"park-0-9", "Trail 10", false, 9.0, 1.0, Difficulty.EASY, a},
				{"park-0-8", "Trail 9", false, 8.0, 1.0, Difficulty.EASY, a}
		};
		for (int i = 0; i < o5.length; i++) {
			for (int j = 0; j < o5[i].length; j++) {
				assertEquals(exp5[i][j], o5[i][j]);
			}
		}
	}
	
	/**
	 * Tests indexOfID()
	 */
	@Test
	public void testIndexOfID() {
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
		
		t.addTrail("Trail 1", a, false, 0.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 2", a, false, 1.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 3", a, false, 2.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 4", a, false, 3.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 5", a, false, 4.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 6", a, false, 5.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 7", a, false, 6.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 8", a, false, 7.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 9", a, false, 8.0, 1.0, Difficulty.EASY);
		t.addTrail("Trail 10", a, false, 9.0, 1.0, Difficulty.EASY);
		
		assertEquals(-1, t.indexOfID("invalid"));
		assertEquals(0, t.indexOfID("park-0-0"));
		assertEquals(1, t.indexOfID("park-0-9"));
		assertEquals(9, t.indexOfID("park-0-8"));
	}
	
	/**
	 * Tests equals()
	 */
	@Test
	public void testEqualsHashcode() {
		setUp();
		TrailList t1 = new TrailList(new Park("park-1", "Central Park", "Located in NYC"));
		TrailList t2 = new TrailList(p);
		
		// Tests that TrailLists with identical Park names are considered equal.
		assertTrue(t.equals(t2));
		assertTrue(t2.equals(t));
		
		
		// Tests that TrailLists with different Park names are considered not equal.
		assertFalse(t.equals(t1));
		assertFalse(t1.equals(t));
		
		// Tests that passing null argument returns false.
		assertFalse(t == null);
		
		// Tests that identical TrailLists return identical hashcodes.
		TrailList duplicate = new TrailList(p);
		assertTrue(duplicate.hashCode() == t2.hashCode());
		
		// Tests that different Parks return different hashcodes.
		assertFalse(t1.hashCode() == t2.hashCode());
	}
	
}
