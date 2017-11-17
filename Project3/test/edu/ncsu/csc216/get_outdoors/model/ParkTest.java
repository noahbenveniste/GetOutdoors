package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Park POJO class
 *
 * @author Noah Benveniste
 */
public class ParkTest {
	
	/** A Park object reference to be used throughout testing */
	private Park p;
	
	/** Invalid null id */
	private static final String INVALID_ID_NULL = null;
	/** Invalid empty string id */
	private static final String INVALID_ID_EMPTY = "";
	/** Invalid whitespace id */
	private static final String INVALID_ID_WHITESPACE = "      ";
	/** Invalid null name */
	private static final String INVALID_NAME_NULL = null;
	/** Invalid empty string name */
	private static final String INVALID_NAME_EMPTY = "";
	/** Invalid whitespace name */
	private static final String INVALID_NAME_WHITESPACE = "    ";
	/** Invalid null description */
	private static final String INVALID_DESC_NULL = null;
	/** Invalid empty string description */
	private static final String INVALID_DESC_EMPTY = "";
	/** Invalid whitespace description */
	private static final String INVALID_DESC_WHITESPACE = "       ";
	/** Valid id */
	private static final String VALID_ID = "ID";
	/** Valid id with leading and trailing whitespace */
	private static final String VALID_ID_WHITESPACE = "   ID  ";
	/** Valid name */
	private static final String VALID_NAME = "Name";
	/** Valid name with leading and trailing whitespace */
	private static final String VALID_NAME_WHITESPACE = "   Name  ";
	/** Valid description */
	private static final String VALID_DESC = "Some description of a park.";
	/** Valid description with leading and trailing whitespace */
	private static final String VALID_DESC_WHITESPACE = "   Some description of a park.   ";
	/** Valid snowfall amount */
	private static final double VALID_SNOWFALL = 5.2;
	/** Default snowfall amount */
	private static final double DEFAULT_SNOWFALL = 0;
	/** Expected toString() return value */
	private static final String EXPECTED_STRING = VALID_NAME + "\t" + VALID_DESC + "\t" + VALID_SNOWFALL;
	
	/**
	 * Sets up reused objects for testing before each test executes
	 */
	@Before
	public void setUp() {
		p = null;
	}
	
	/**
	 * Tests Park(String, String, String)
	 */
	@Test
	public void testParkStringStringString() {
		/** INVALID ID TESTS */
		try {
			p = new Park(INVALID_ID_NULL, VALID_NAME, VALID_DESC);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		try {
			p = new Park(INVALID_ID_EMPTY, VALID_NAME, VALID_DESC);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		try {
			p = new Park(INVALID_ID_WHITESPACE, VALID_NAME, VALID_DESC);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		/** INVALID NAME TESTS */
		try {
			p = new Park(VALID_ID, INVALID_NAME_NULL, VALID_DESC);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		try {
			p = new Park(VALID_ID, INVALID_NAME_EMPTY, VALID_DESC);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		try {
			p = new Park(VALID_ID, INVALID_NAME_WHITESPACE, VALID_DESC);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		/** INVALID DESCRIPTION TESTS */
		try {
			p = new Park(VALID_ID, VALID_NAME, INVALID_DESC_NULL);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		try {
			p = new Park(VALID_ID, VALID_NAME, INVALID_DESC_EMPTY);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		try {
			p = new Park(VALID_ID, VALID_NAME, INVALID_DESC_WHITESPACE);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(p);
		}
		
		/** Test valid construction */
		p = new Park(VALID_ID, VALID_NAME, VALID_DESC);
		assertEquals(VALID_ID, p.getParkID());
		assertEquals(VALID_NAME, p.getName());
		assertEquals(VALID_DESC, p.getDescription());
		assertTrue(DEFAULT_SNOWFALL == p.getSnowChanged());
		p = null;
		p = new Park(VALID_ID_WHITESPACE, VALID_NAME_WHITESPACE, VALID_DESC_WHITESPACE);
		assertEquals(VALID_ID, p.getParkID());
		assertEquals(VALID_NAME, p.getName());
		assertEquals(VALID_DESC, p.getDescription());
		assertTrue(DEFAULT_SNOWFALL == p.getSnowChanged());
	}
	
	/**
	 * Tests Park(String, String, String, double)
	 */
	@Test
	public void testParkStringStringStringDouble() {
		/** Test valid construction */
		p = new Park(VALID_ID, VALID_NAME, VALID_DESC, VALID_SNOWFALL);
		assertEquals(VALID_ID, p.getParkID());
		assertEquals(VALID_NAME, p.getName());
		assertEquals(VALID_DESC, p.getDescription());
		assertTrue(VALID_SNOWFALL == p.getSnowChanged());
	}
	
	/**
	 * Tests getSnowChanged()
	 */
	@Test
	public void testSetSnowChange() {
		p = new Park(VALID_ID, VALID_NAME, VALID_DESC);
		assertTrue(DEFAULT_SNOWFALL == p.getSnowChanged());
		p.setSnowChange(-5.3);
		assertTrue(-5.3 == p.getSnowChanged());
		p.setSnowChange(5.3);
		assertTrue(5.3 == p.getSnowChanged());
		p.setSnowChange(0);
		assertTrue(0 == p.getSnowChanged());
	}

	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {
		p = new Park(VALID_ID, VALID_NAME, VALID_DESC, VALID_SNOWFALL);
		assertEquals(EXPECTED_STRING, p.toString());
	}
	
	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		p = new Park(VALID_ID, VALID_NAME, VALID_DESC);
		Park q = new Park(VALID_ID, "A", VALID_DESC);
		Park r = new Park(VALID_ID, "Z", VALID_DESC);
		Park s = new Park(VALID_ID, VALID_NAME, VALID_DESC);
		assertTrue(p.compareTo(q) > 0);
		assertTrue(q.compareTo(p) < 0);
		assertTrue(p.compareTo(r) < 0);
		assertTrue(r.compareTo(p) > 0);
		assertTrue(p.compareTo(s) == 0);
		assertTrue(s.compareTo(p) == 0);
	}
	
}
