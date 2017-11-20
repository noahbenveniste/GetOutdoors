package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ParkList.
 * 
 * @author demills
 */
public class ParkListTest {
	/** Constant name of a Park used in setUp(). */
	private static final String PARK_NAME = "Yellowstone National Park";
	/** Constant description of a Park used in setUp(). */
	private static final String DESC = "Mountainous Park in the mid-West US.";
	/** Constant for a Parks's "snowChange", used in setUp(). */
	private static final double SNOW_CHANGE = 2.5;

	/** Constant for the expected value of the ID assigned to the Park. */
	private static final String EXPECTED_ID = "park-0";
	/** Constant for expected value of a ParkList. */
	private static final String PARKLIST_NAME = "Parks";
	
	/** ParkList reference used for testing.*/
	private static ParkList parkList;
	/** Park reference used for testing. */
	private static Park park;

	/**
	 * Sets up a ParkList for testing.
	 */
	@Before
	public void setUp() throws Exception {
		parkList = new ParkList();
		park = new Park(EXPECTED_ID, PARK_NAME, DESC, SNOW_CHANGE);
		assertNotNull(park);
		assertTrue(parkList.addPark(PARK_NAME, DESC, SNOW_CHANGE));	
	}

	/**
	 * Tests the addPark() and getParkAt() mathods. Additionally, simple
	 * methods like size() and isEmpty() are covered through these tests.
	 */
	@Test
	public void testAddGetIndexOfAndConstructor() {
		try {
							/* TESTS ON EMPTY LIST. */
			// Tests construction of an empty Park.
			parkList = new ParkList();
			assertEquals(PARKLIST_NAME, parkList.getName());
			assertEquals(0, parkList.size());
			assertTrue(parkList.isEmpty());
			
			// Tests that getParkAt() throws an IOoBE on for index 0 on an empty list.
			Park park0 = null;
			try {
				park0 = null;
				park0 = parkList.getParkAt(0);
				fail("Should have thrown IndexOutOfBoundsException.");
			} catch (IndexOutOfBoundsException e) {
				assertNull(park0);
				
			}
			// Tests that indexOf() returns negative -1 on empty list.
			try {
				assertEquals(-1, parkList.indexOfID(EXPECTED_ID));
			} catch (IndexOutOfBoundsException e) {
				fail("Should have thrown IndexOutOfBoundsException.");
			}

							/* TESTS ON LIST OF SIZE 1. */

			// In setUp(), "park" and "parkList" are constructed with the testing 
			// constants. The ParkList.addPark() is called passing fields matching
			// those set for "park".
			setUp();

			// Testing constructor and addition of a park.
			assertNotNull(parkList);
			assertEquals(PARKLIST_NAME, parkList.getName());
			assertEquals(1, parkList.size());
			assertFalse(parkList.isEmpty());
			assertEquals(0, parkList.indexOfID(EXPECTED_ID));

			// Testing the that the added Park was constructed correctly.
			assertNotNull(parkList.getParkAt(0));

			assertEquals(park.getName(), parkList.getParkAt(0).getName());
			assertEquals(park.getDescription(), parkList.getParkAt(0).getDescription());
			assertEquals(park.getParkID(), parkList.getParkAt(0).getParkID());
			assertTrue(park.getSnowChange() == parkList.getParkAt(0).getSnowChange());
			
			// Tests that getParkAt() throws an IOoBE on for index 1 on an singleton list.
			try {
				park0 = null;
				park0 = parkList.getParkAt(0);
				assertNotNull(park0);
			} catch (IndexOutOfBoundsException e) {
				fail("IndexOutOfBoundsException should not have been thrown.");
			}
			
							/* TESTS ON LIST OF SIZE 2. */
			// Testing addition of non-duplicate Park.
			assertTrue(parkList.addPark("Zion National Park", "A Park somewhere named Zion.", SNOW_CHANGE + 1.0));
			
			// Checking that the first park is unchanged and unmoved.
			assertEquals(PARK_NAME, parkList.getParkAt(0).getName());
			assertEquals(DESC, parkList.getParkAt(0).getDescription());
			assertEquals(EXPECTED_ID, parkList.getParkAt(0).getParkID());
			assertTrue(SNOW_CHANGE == parkList.getParkAt(0).getSnowChange());
			assertEquals(0, parkList.indexOfID(EXPECTED_ID));
			
			// Checking the value of the second park.
			assertNotNull(parkList.getParkAt(1));
			assertEquals("Zion National Park", parkList.getParkAt(1).getName());
			assertEquals("A Park somewhere named Zion.", parkList.getParkAt(1).getDescription());
			assertEquals("park-1", parkList.getParkAt(1).getParkID());
			assertEquals(1, parkList.indexOfID("park-1"));
			assertTrue(SNOW_CHANGE + 1 == parkList.getParkAt(1).getSnowChange());
			
			// Tests that getParkAt() throws an IOoBE on for index 2 on an empty list.
			Park park2 = null;
			try {
				park = parkList.getParkAt(2);
				fail("Should have thrown IIOoBE");
			} catch (IndexOutOfBoundsException e) {
				assertNull(park2);
			}
			
			// Tests passing getParkAt() a negative value throws an IOoBE.
			Park nullPark = null;
			try {
				nullPark = parkList.getParkAt(-1);
				fail("Should have thrown IOoBE for negative index.");
			} catch (IndexOutOfBoundsException e) {
				assertNull(nullPark);
			}
			
			// Tests adding a duplicate Park.
			Park duplicatePark = null;
			try {
				parkList.addPark(PARK_NAME, DESC, SNOW_CHANGE);
				fail("Should have thrown IllegalArgumentException.");
			} catch (IllegalArgumentException e) {
				assertNull(duplicatePark);
			}
		} catch (Exception e) {
			fail("Unexpected Exception in setUp().");
		}
	}

	/**
	 * Tests ParkList.get2DArray().
	 */
	@Test
	public void testGet2DArray() {
		try {
			setUp();
		} catch (Exception e) {
			fail("Unexpected Exception thrown from setUp().");
		}
		
		// Initializes several rows of expecetd values.
		Object[] firstRowExpected = {EXPECTED_ID, PARK_NAME, DESC, SNOW_CHANGE};
		Object[] secondRowExpected = {"park-1", "AAA" + PARK_NAME, "Second " + DESC, 1 + SNOW_CHANGE};
		Object[] thirdRowExpected = {"park-2", "CCC" + PARK_NAME, "Third " + DESC, 2 + SNOW_CHANGE};
		
		// The SortedLinkedList they are added to should sorted them {2nd, 3rd, 1st},
		//   due to their names.
		Object[][] expectedRows = {secondRowExpected, thirdRowExpected, firstRowExpected};

		parkList.addPark("AAA" + PARK_NAME, "Second " + DESC, 1 + SNOW_CHANGE);
		parkList.addPark("CCC" + PARK_NAME, "Third " + DESC, 2 + SNOW_CHANGE);

		for (int i = 0; i < expectedRows.length; i++) {
			for (int j = 0; j < expectedRows[i].length; j++) {
				assertEquals(expectedRows[i][j], parkList.get2DArray()[i][j]);
			}
		}
	}
}