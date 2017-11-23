package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Tests the Trail class.
 * 
 * @author demills
 */
public class TrailTest {
	/** "trailID" constant used for testing. */
	private static final String ID = "park-0-1";
	/** "trailName" constant used for testing. */
	private static final String NAME = "TrailName";
	/** "snow" constant used for testing. */
	private static final double SNOW = 10;
	/** "distance" constant used for testing. */
	private static final double DISTANCE = 22.6;
	/** "activities" variable used for testing. Initialized in setUp(). */
	private static SortedArrayList<Activity> activities; 
	/** "Difficulty" constant used for testing. */
	private static final Difficulty DIFF = Difficulty.EXTREME;
	/** Generic Trail for use in testing. Initialized in setUp(). */
	private static Trail trail;
	/** An Activity that requires snow. Used for testing.  */
	private static Activity SNOW_ACTIVITY; 
	/** An constant Activity that doesn't require snow. Used for testing.  */
	private static Activity CLEAR_ACTIVITY;

	/**
	 * Sets up the class for testing.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		activities = new SortedArrayList<Activity>();
		SNOW_ACTIVITY = new Activity("act-1", "Snow Activity", "Needs Snow", true, 7);
		CLEAR_ACTIVITY = new Activity("act-2", "Non-Snow Activity", "Needs Clear Day", false, 3);
		activities.add(SNOW_ACTIVITY);
		activities.add(CLEAR_ACTIVITY);
		trail = new Trail(ID, NAME, activities, false, SNOW, DISTANCE, DIFF);
	}
	/**
	 * Tests the Trail constructor and getter methods.
	 */
	@Test
	public void testConstructorAndGetters() {
		// Tests normal construction.
		try {
			setUp();
			assertEquals(ID, trail.getTrailID());
			assertEquals(NAME, trail.getTrailName());
			assertEquals(CLEAR_ACTIVITY, trail.getActivities().get(0));
			assertEquals(SNOW_ACTIVITY, trail.getActivities().get(1));
			assertTrue(SNOW == trail.getSnow());
			assertTrue(DISTANCE == trail.getDistance());
			assertEquals(DIFF, trail.getDifficulty());
			assertTrue(trail.allow(CLEAR_ACTIVITY));
			assertTrue(trail.allow(SNOW_ACTIVITY));
			assertFalse(trail.closedForMaintenance());
			assertFalse(trail.trailOpen(CLEAR_ACTIVITY));
			assertTrue(trail.trailOpen(SNOW_ACTIVITY));
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
		
		////////////////////////////////////////////
		//////////// TRAIL ID TESTS   //////////////
		////////////////////////////////////////////
		// Tests passing null.
		try {
			trail = null;
			trail = new Trail(null, NAME, activities, false, SNOW, DISTANCE, DIFF);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}

		// Tests passing empty.
		try {
			trail = null;
			trail = new Trail("", NAME, activities, false, SNOW, DISTANCE, DIFF);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}

		// Tests passing all whitespace.
		try {
			trail = null;
			trail = new Trail("  \t\t \t", NAME, activities, false, SNOW, DISTANCE, DIFF);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}
		
		// Tests that leading whitespace is trimmed.
		try {
			trail = null;
			trail = new Trail(" \t " + ID, NAME, activities, false, SNOW, DISTANCE, DIFF);

			assertNotNull(trail);
			assertEquals(ID, trail.getTrailID());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
		
		// Tests that trailing whitespace is trimmed.
		try {
			trail = null;
			trail = new Trail(ID + " \t ", NAME, activities, false, SNOW, DISTANCE, DIFF);

			assertNotNull(trail);
			assertEquals(ID, trail.getTrailID());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		////////////////////////////////////////////
		//////////// TRAIL NAME TESTS   //////////////
		////////////////////////////////////////////
		// Tests passing null.
		try {
			trail = null;
			trail = new Trail(ID, null, activities, false, SNOW, DISTANCE, DIFF);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}

		// Tests passing empty.
		try {
			trail = null;
			trail = new Trail(ID, "", activities, false, SNOW, DISTANCE, DIFF);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}

		// Tests passing all whitespace.
		try {
			trail = null;
			trail = new Trail(ID, "  \t\t \t", activities, false, SNOW, DISTANCE, DIFF);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}
		
		// Tests that leading whitespace is trimmed.
		try {
			trail = null;
			trail = new Trail(ID, " \t " + NAME, activities, false, SNOW, DISTANCE, DIFF);

			assertNotNull(trail);
			assertEquals(NAME, trail.getTrailName());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
		
		// Tests that trailing whitespace is trimmed.
		try {
			trail = null;
			trail = new Trail(ID, NAME + " \t ", activities, false, SNOW, DISTANCE, DIFF);

			assertNotNull(trail);
			assertEquals(NAME, trail.getTrailName());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
		
		
		// Passing a null Difficulty value should throw an IAE.
		try {
			trail = null;
			trail = new Trail(ID, NAME, activities, false, SNOW, DISTANCE, null);
			fail("Expected IAE.");
		} catch (IllegalArgumentException e) {
			assertNull(trail);
		}
	}
	
	/**
	 * Tests Trail.setActivities().
	 */
	@Test
	public void testSetActivities() {
		// Tests setActivities through constructor.
		try {
			setUp();
			assertEquals(SNOW_ACTIVITY, trail.getActivities().get(1));
			assertEquals(CLEAR_ACTIVITY, trail.getActivities().get(0));
		} catch (Exception e) {
			fail("Unexpected Exception thrown from setUp().");
		}
		
		// Tests that IAE is thrown for a null parameter.
		try {
			trail.setActivities(null);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertEquals(SNOW_ACTIVITY, trail.getActivities().get(1));
			assertEquals(CLEAR_ACTIVITY, trail.getActivities().get(0));
		}
	}

	/**
	 * Tests Trail.setSnow() and Trail.addSnow().
	 */
	@Test
	public void testSetAndAddSnow() {
		// Tests snow value is set to 0 for a negative parameter.
		try {
			setUp();
			assertTrue(SNOW == trail.getSnow());
			trail.setSnow(-1);
			assertTrue(0 == trail.getSnow());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		} 
		
		// Tests that addSnow() increments properly.
		try {
			setUp();
			assertTrue(SNOW == trail.getSnow());
			trail.addSnow(10);
			assertTrue(SNOW + 10 == trail.getSnow());
			trail.addSnow(-10);
			assertTrue(SNOW == trail.getSnow());
			trail.addSnow(-15);
			assertTrue(0 == trail.getSnow());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		} 
	}
	
	/**
	 * Tests Trail.setDistance().
	 */
	@Test
	public void testSetDistance() {
		// Tests that setSnow() throws an IAE for a negative parameter.
		try {
			setUp();
			trail.setDistance(-1);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(DISTANCE == trail.getDistance());
		} catch (Exception e) {
			fail("Unexpected Exception thrown from setUp().");
		}
		
		// Tests that setDistance() does not throw an IAE on lower bound of 0.
		try {
			trail.setDistance(0);
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
	}

	/**
	 * Tests Trail.trailOpen() and Trail.setMaintenance().
	 */
	@Test
	public void testTrailOpenAndSetMaintenance() {
		try {
			setUp();

			// Trail.snow = 10, 
			// SNOW_ACTIVITY.snowBoundary = 7
			// CLEAR_ACTIVITY.snowBoundary = 3
			assertTrue(trail.trailOpen(SNOW_ACTIVITY));
			assertFalse(trail.trailOpen(CLEAR_ACTIVITY));
			
			// trailOpen() should return false for all trails under maintenance.
			trail.setTrailMaintenance(true);
			assertFalse(trail.trailOpen(SNOW_ACTIVITY));
			assertFalse(trail.trailOpen(CLEAR_ACTIVITY));
			
			trail.setTrailMaintenance(false);
			assertTrue(trail.trailOpen(SNOW_ACTIVITY));
			assertFalse(trail.trailOpen(CLEAR_ACTIVITY));

			// Negating each Activity's needSnow boolen value.
			// trailOpen() should now return the opposite of before.
			CLEAR_ACTIVITY.setNeedSnow(true);
			SNOW_ACTIVITY.setNeedSnow(false);
			assertFalse(trail.trailOpen(SNOW_ACTIVITY));
			assertTrue(trail.trailOpen(CLEAR_ACTIVITY));
			
			// Setting back to default for next test. Values are:
			// Trail.snow = 10, 
			// SNOW_ACTIVITY.snowBoundary = 7
			// CLEAR_ACTIVITY.snowBoundary = 3
			setUp();
			
			// Setting values on other side of each Activity's snowBoundary.
			// trailOpen() should return the opposite of before.
			SNOW_ACTIVITY.setSnowBoundary(15);
			CLEAR_ACTIVITY.setSnowBoundary(20);

			// Trail.snow = 10, 
			// SNOW_ACTIVITY.snowBoundary = 15
			// CLEAR_ACTIVITY.snowBoundary = 20
			assertFalse(trail.trailOpen(SNOW_ACTIVITY));
			assertTrue(trail.trailOpen(CLEAR_ACTIVITY));
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
	}

	/**
	 * Tests Trail.compareTo().
	 */
	@Test
	public void testCompareTo() {
		Trail trailA = new Trail(ID, "AAA", activities, false, SNOW, DISTANCE, DIFF);
		Trail trailB = new Trail(ID, "BBB", activities, false, SNOW, DISTANCE, DIFF);
		
		assertTrue(trailA.compareTo(trailB) < 0);
		assertTrue(trailB.compareTo(trailA) > 0);
		assertTrue(trailA.compareTo(trailA) == 0);
	}

	/**
	 * Tests Trail.toString().
	 */
	@Test
	public void testToString() {
		try {
			setUp();
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
		String expectedTrailString = NAME + "\t" + "false" + "\t" + SNOW + "\t" + DISTANCE + "\t" +
				                     DIFF + "\t" + trail.getActivities().get(0).toString() + "\t" +
				                     trail.getActivities().get(1).getName();

		assertEquals(expectedTrailString, trail.toString());
	}
	/**
	 * Tests Trail.toString().
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsAndHashcode() {
		// These two should equal each other, but not hash to the same value.
		Trail trailA1 = new Trail(ID + "abc", "AAA", activities, false, SNOW, DISTANCE, DIFF);
		Trail trailA2 = new Trail(ID, "AAA", activities, true, SNOW, DISTANCE, DIFF);
		assertEquals(trailA1, trailA2);
		assertFalse(trailA1.hashCode() == trailA2.hashCode());

		// This should not be equal to trailA1 or trailA2, due to case-sensitivity.
		Trail trailA3 = new Trail(ID, "aaa", activities, false, SNOW, DISTANCE, DIFF);
		assertNotEquals(trailA1, trailA3);
		assertNotEquals(trailA2, trailA3);
		assertFalse(trailA1.hashCode() == trailA3.hashCode());
		assertFalse(trailA2.hashCode() == trailA3.hashCode());

		// This should not be equal to trailA1 or trailA2, due to different characters.
		Trail trailB1 = new Trail(ID, "BBB", activities, false, SNOW, DISTANCE, DIFF);
		assertNotEquals(trailA1, trailB1);
		assertFalse(trailA1.hashCode() == trailB1.hashCode());
		
		// Non-Trails should always return false.
		String notATrail = "I am not a Trail type.";
		assertFalse(trailA1.equals(notATrail));
	}
}