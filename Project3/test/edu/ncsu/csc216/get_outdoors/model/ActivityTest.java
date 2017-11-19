package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Activity class.
 * 
 * @author demills
 */
public class ActivityTest {
	/** Private "activityID" constant for testing. */
	private static final String ID = "act-2";
	/** Private "name" constant for testing. */
	private static final String NAME = "Skiing";
	/** Private "description" constant for testing. */
	private static final String DESC = "An Activity description.";
	/** Private "snowBoundary" constant for testing. */
	private static final int S_BOUND = 5;

	/**
	 * Tests the Activity constructor.
	 */
	@Test
	public void testConstructor() {
		Activity activity = null;

		// Tests a simple valid construction.
		try {
			activity = null;
			activity = new Activity(ID, NAME, DESC, false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
			
			// Setting description after construction.
			activity.setDescription("New Description");
			assertEquals("New Description", activity.getDescription());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		///////////////////////////////////////
		///////// Testing activityID //////////
		///////////////////////////////////////

		// Tests that null activityID throws IAE.
		// Tests a simple valid construction.
		try {
			activity = null;
			activity = new Activity(null, NAME, DESC, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that empty activityID throws IAE.
		try {
			activity = null;
			activity = new Activity("", NAME, DESC, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that all whitespace activityID throws IAE.
		try {
			activity = null;
			activity = new Activity(" \t\t \t", NAME, DESC, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that leading whitespace is trimmed.
		try {
			activity = null;
			activity = new Activity(" \t " + ID, NAME, DESC, false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		// Tests that trailing whitespace is trimmed.
		try {
			activity = null;
			activity = new Activity(ID + " \t ", NAME, DESC, false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		///////////////////////////////////////
		//////////// Testing name /////////////
		///////////////////////////////////////
		// Tests that null name throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, null, DESC, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that empty name throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, "", DESC, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that all whitespace name throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, " \t\t \t", DESC, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that leading whitespace is trimmed.
		try {
			activity = null;
			activity = new Activity(ID, " \t " + NAME, DESC, false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		// Tests that trailing whitespace is trimmed.
		try {
			activity = null;
			activity = new Activity(ID, NAME + " \t ", DESC, false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		///////////////////////////////////////
		///////// Testing description /////////
		///////////////////////////////////////
		// Tests that null description throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, NAME, null, false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests tha empty description throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, NAME, "", false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that all whitespace description throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, NAME, " \t\t \t", false, S_BOUND);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}

		// Tests that leading whitespace is trimmed.
		try {
			activity = null;
			activity = new Activity(ID, NAME, " \t " + DESC, false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
		// Tests that trailing whitespace is trimmed.
		try {
			activity = null;
			activity = new Activity(ID, NAME, DESC + " \t ", false, S_BOUND);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(S_BOUND, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}

		///////////////////////////////////////
		//////// Testing snowBoundary /////////
		///////////////////////////////////////
		
		// Tests that negative snowBoundary throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, NAME, NAME, false, -1);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}
		
		// Tests that passing snowBoundary value on lower bound (0) is allowed. 
		try {
			activity = null;
			activity = new Activity(ID, NAME, DESC, false, 0);

			assertNotNull(activity);
			assertEquals(ID, activity.getActivityID());
			assertEquals(NAME, activity.getName());
			assertEquals(DESC, activity.getDescription());
			assertFalse(activity.snowNeeded());
			assertEquals(0, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
	}
	
	/**
	 * Tests the setNeedSnow() method.
	 */
	@Test
	public void testSetNeedSnowSetBoundary() {
		Activity activity = new Activity(ID, NAME, DESC, false, S_BOUND);
		assertFalse(activity.snowNeeded());

		// Tests setting from false to false.
		activity.setNeedSnow(false);
		assertFalse(activity.snowNeeded());

		// Tests toggling from false to true.
		activity.setNeedSnow(true);
		assertTrue(activity.snowNeeded());

		// Tests setting from true to true.
		activity.setNeedSnow(true);
		assertTrue(activity.snowNeeded());

		// Tests setting from true to false.
		activity.setNeedSnow(false);
		assertFalse(activity.snowNeeded());
		
		// Tests that negative snowBoundary throws IAE.
		try {
			activity = null;
			activity = new Activity(ID, NAME, NAME, false, -1);
			fail("Expected IAE to be thrown.");
		} catch (IllegalArgumentException e) {
			assertNull(activity);
		}
		
		// Tests setting snowBoundary to new, valid value.
		try {
			activity = new Activity(ID, NAME, DESC, false, S_BOUND);
			assertEquals(S_BOUND, activity.getSnowBoundary());
			activity.setSnowBoundary(10);
			assertEquals(10, activity.getSnowBoundary());
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
	}

	/**
	 * Tests the toString() method.
	 */
	@Test
	public void testToString() {
		Activity activity = new Activity(ID, NAME, DESC, false, S_BOUND);
		assertEquals(NAME + "\t" + DESC + "\t" + "false" + "\t" + S_BOUND, activity.toString()); 
	}

	/**
	 * Tests the equals() method.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsAndHashCode() {
		Activity activity1 = new Activity(ID, NAME, DESC, false, S_BOUND);
		Activity activity2 = new Activity(ID, NAME + "A", DESC, false, S_BOUND + 1);
		Activity activity3 = new Activity(ID, NAME, DESC, false, S_BOUND);

		// Tests that two distinct Activity Objects are 
		// considered equal if their names are identical.
		assertTrue(activity1.equals(activity3));
		assertTrue(activity3.equals(activity1));

		// Tests that identical Activities have identical hash codes.
		assertEquals(activity1.hashCode(), activity3.hashCode());
		

		// Tests that two distinct Activity Objects are 
		// considered not equal if their names are different.
		assertFalse(activity1.equals(activity2));
		assertFalse(activity2.equals(activity1));

		// Tests that distinct Activities have different hash codes.
		assertNotEquals(activity1.hashCode(), activity2.hashCode());
		
		// Tests that passing a non-Activity Object returns false.
		assertFalse(activity1.equals("Not an Activity."));
	}

	/**
	 * Tests the compareTo() method.
	 */
	@Test
	public void testCompareTo() {
		// "activityA" should precede "activityB".
		Activity activityA = new Activity(ID, "AAA", DESC, false, S_BOUND);
		Activity activityB = new Activity(ID, "BBB", DESC, false, S_BOUND);

		// Tests that an Activity with a name preceding another's returns 
		// negative when the other is passed as a parameter to compareTo().
		assertTrue(activityA.compareTo(activityB) < 0);

		// Tests that an Activity with a name preceding another's returns 
		// negative when the other is passed as a parameter to compareTo().
		assertTrue(activityB.compareTo(activityA) > 0);
		
		// Tests that compareTo returns 0 for an Activity with the identical name.
		assertEquals(0, activityA.compareTo(activityA));
	}
}