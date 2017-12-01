package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ActivityList.
 * 
 * @author demills
 */
public class ActivityListTest {
	/** Constant name of an Activity used in setUp(). */
	private static final String ACTIVITY_NAME = "Yellowstone National Activity";
	/** Constant description of an Activity used in setUp(). */
	private static final String DESC = "Mountainous Activity in the mid-West US.";
	/** Constant for a Activitys's "snowBoundary", used in setUp(). */
	private static final int SNOW_BOUNDARY = 5;

	/** Constant for the expected value of the ID assigned to the Activity. */
	private static final String EXPECTED_ID = "act-0";
	/** Constant for expected value of a ActivityList. */
	private static final String ACTIVITYLIST_NAME = "Activities";
	
	/** ActivityList reference used for testing.*/
	private static ActivityList activityList;
	/** Activity reference used for testing. */
	private static Activity activity;

	/**
	 * Sets up a ActivityList for testing.
	 */
	@Before
	public void setUp() {
		activityList = new ActivityList();
		activity = new Activity(EXPECTED_ID, ACTIVITY_NAME, DESC, true, SNOW_BOUNDARY);
		assertNotNull(activity);
		assertTrue(activityList.addActivity(ACTIVITY_NAME, DESC, true, SNOW_BOUNDARY));	
	}

	/**
	 * Tests addActivity() and getActivityAt(). Additionally, simple methods
	 * like size() and isEmpty() are covered through these tests.
	 */
	@Test
	public void testAddGetIndexOfAndConstructor() {
		try {
							/* TESTS ON EMPTY LIST. */
			// Tests construction of an empty Activity.
			activityList = new ActivityList();
			assertEquals(ACTIVITYLIST_NAME, activityList.getName());
			assertEquals(0, activityList.size());
			assertTrue(activityList.isEmpty());
			
			// Tests that getActivityAt() throws an IOoBE on for index 0 on an empty list.
			Activity activity0 = null;
			try {
				activity0 = null;
				activity0 = activityList.getActivityAt(0);
				fail("Should have thrown IndexOutOfBoundsException.");
			} catch (IndexOutOfBoundsException e) {
				assertNull(activity0);
				
			}
			// Tests that indexOf() returns negative -1 on empty list.
			try {
				assertEquals(-1, activityList.indexOfID(EXPECTED_ID));
			} catch (IndexOutOfBoundsException e) {
				fail("Should have thrown IndexOutOfBoundsException.");
			}

							/* TESTS ON LIST OF SIZE 1. */

			// In setUp(), "activity" and "activityList" are constructed with the testing 
			// constants. The ActivityList.addActivity() is called passing fields matching
			// those set for "activity".
			setUp();

			// Testing constructor and addition of a activity.
			assertNotNull(activityList);
			assertEquals(ACTIVITYLIST_NAME, activityList.getName());
			assertEquals(1, activityList.size());
			assertFalse(activityList.isEmpty());
			assertEquals(0, activityList.indexOfID(EXPECTED_ID));

			// Testing the that the added Activity was constructed correctly.
			assertNotNull(activityList.getActivityAt(0));

			assertEquals(activity.getName(), activityList.getActivityAt(0).getName());
			assertEquals(activity.getDescription(), activityList.getActivityAt(0).getDescription());
			assertEquals(activity.getActivityID(), activityList.getActivityAt(0).getActivityID());
			assertTrue(activity.getSnowBoundary() == activityList.getActivityAt(0).getSnowBoundary());
			
			// Tests that getActivityAt() throws an IOoBE on for index 1 on an singleton list.
			try {
				activity0 = null;
				activity0 = activityList.getActivityAt(0);
				assertNotNull(activity0);
			} catch (IndexOutOfBoundsException e) {
				fail("IndexOutOfBoundsException should not have been thrown.");
			}
			
							/* TESTS ON LIST OF SIZE 2. */
			// Testing addition of non-duplicate Activity.
			assertTrue(activityList.addActivity("Zion National Activity", "A Activity somewhere named Zion.", true, SNOW_BOUNDARY + 1));
			
			// Checking that the first activity is unchanged and unmoved.
			assertEquals(ACTIVITY_NAME, activityList.getActivityAt(0).getName());
			assertEquals(DESC, activityList.getActivityAt(0).getDescription());
			assertEquals(EXPECTED_ID, activityList.getActivityAt(0).getActivityID());
			assertTrue(SNOW_BOUNDARY == activityList.getActivityAt(0).getSnowBoundary());
			assertEquals(0, activityList.indexOfID(EXPECTED_ID));
			
			// Checking the value of the second activity.
			assertNotNull(activityList.getActivityAt(1));
			assertEquals("Zion National Activity", activityList.getActivityAt(1).getName());
			assertEquals("A Activity somewhere named Zion.", activityList.getActivityAt(1).getDescription());
			assertEquals("act-1", activityList.getActivityAt(1).getActivityID());
			assertEquals(1, activityList.indexOfID("act-1"));
			assertTrue(SNOW_BOUNDARY + 1 == activityList.getActivityAt(1).getSnowBoundary());
			
			// Tests that getActivityAt() throws an IOoBE on for index 2 on an empty list.
			Activity activity2 = null;
			try {
				activity = activityList.getActivityAt(2);
				fail("Should have thrown IIOoBE");
			} catch (IndexOutOfBoundsException e) {
				assertNull(activity2);
			}
			
			// Tests passing getActivityAt() a negative value throws an IOoBE.
			Activity nullActivity = null;
			try {
				nullActivity = activityList.getActivityAt(-1);
				fail("Should have thrown IOoBE for negative index.");
			} catch (IndexOutOfBoundsException e) {
				assertNull(nullActivity);
			}
			
			// Tests adding a duplicate Activity.
			try {
				activityList.addActivity(ACTIVITY_NAME, DESC, true, SNOW_BOUNDARY);
				fail("Should have thrown IlleglArgumentException.");
			} catch (IllegalArgumentException e) {
				assertEquals(2, activityList.size());
			}
		} catch (Exception e) {
			fail("Unexpected Exception in setUp().");
		}
	}

	/**
	 * Tests ActivityList.get2DArray().
	 */
	@Test
	public void testGet2DArray() {
		try {
			setUp();
		} catch (Exception e) {
			fail("Unexpected Exception thrown from setUp().");
		}
		
		// Initializes several rows of expeceted values.
		Object[] firstRowExpected = {EXPECTED_ID, ACTIVITY_NAME, DESC, true, SNOW_BOUNDARY};
		Object[] secondRowExpected = {"act-1", "AAA" + ACTIVITY_NAME, "Second " + DESC, true, 1 + SNOW_BOUNDARY};
		Object[] thirdRowExpected = {"act-2", "CCC" + ACTIVITY_NAME, "Third " + DESC, true, 2 + SNOW_BOUNDARY};
		
		// The SortedLinkedList they are added to should sorted them {2nd, 3rd, 1st},
		//   due to their names.
		Object[][] expectedRows = {secondRowExpected, thirdRowExpected, firstRowExpected};

		activityList.addActivity("AAA" + ACTIVITY_NAME, "Second " + DESC, true, 1 + SNOW_BOUNDARY);
		activityList.addActivity("CCC" + ACTIVITY_NAME, "Third " + DESC, true, 2 + SNOW_BOUNDARY);

		for (int i = 0; i < expectedRows.length; i++) {
			for (int j = 0; j < expectedRows[i].length; j++) {
				assertEquals(expectedRows[i][j], activityList.get2DArray()[i][j]);
			}
		}
	}
}