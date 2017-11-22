package edu.ncsu.csc216.get_outdoors.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for SortedLinkedList
 * @author Noah Benveniste
 */
public class SortedLinkedListTest {

	private SortedLinkedList<String> list;
	
	/**
	 * Sets up a SortedLinkedList object to be used throughout testing
	 */
	@Before
	public void setUp() {
		list = new SortedLinkedList<String>();
	}

	/**
	 * Tests the default constructor
	 */
	@Test
	public void testSortedLinkedList() {
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests add(e)
	 */
	@Test
	public void testAdd() {
		assertTrue(list.isEmpty());
		assertTrue(list.add("d"));
		assertEquals(1, list.size());
		assertEquals("[d]", list.toString());

		assertTrue(list.add("b"));
		assertEquals(2, list.size());
		assertEquals("[b, d]", list.toString());
		
		assertTrue(list.add("g"));
		assertEquals(3, list.size());
		assertEquals("[b, d, g]", list.toString());
		
		assertTrue(list.add("a"));
		assertEquals(4, list.size());
		assertEquals("[a, b, d, g]", list.toString());
		
		assertTrue(list.add("c"));
		assertEquals(5, list.size());
		assertEquals("[a, b, c, d, g]", list.toString());
		
		assertTrue(list.add("e"));
		assertEquals(6, list.size());
		assertEquals("[a, b, c, d, e, g]", list.toString());
		
		assertTrue(list.add("f"));
		assertEquals(7, list.size());
		assertEquals("[a, b, c, d, e, f, g]", list.toString());
		
		assertTrue(list.add("h"));
		assertEquals(8, list.size());
		assertEquals("[a, b, c, d, e, f, g, h]", list.toString());
		
		assertTrue(list.add("x"));
		assertEquals(9, list.size());
		assertEquals("[a, b, c, d, e, f, g, h, x]", list.toString());
		
		assertTrue(list.add("q"));
		assertEquals(10, list.size());
		assertEquals("[a, b, c, d, e, f, g, h, q, x]", list.toString());
		
		assertTrue(list.add("z"));
		assertEquals(11, list.size());
		assertEquals("[a, b, c, d, e, f, g, h, q, x, z]", list.toString());
		
		assertTrue(list.add("y"));
		assertEquals(12, list.size());
		assertEquals("[a, b, c, d, e, f, g, h, q, x, y, z]", list.toString());
		
		//Try adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("[a, b, c, d, e, f, g, h, q, x, y, z]", list.toString());
		}
		
		//Try adding a repeat element
		try {
			list.add("c");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("[a, b, c, d, e, f, g, h, q, x, y, z]", list.toString());
		}
	}
	
	/**
	 * Tests remove(int)
	 */
	@Test
	public void testRemove() {
		assertTrue(list.isEmpty());
		list.add("c");
		list.add("b");
		list.add("g");
		list.add("e");
		list.add("d");
		list.add("f");
		list.add("a");
		assertEquals(7, list.size());
		assertEquals("[a, b, c, d, e, f, g]", list.toString());
		
		/** Test removing from the front with different combinations of adding */
		
		//Remove from the front
		assertEquals("a", list.remove(0));
		assertEquals(6, list.size());
		assertEquals("[b, c, d, e, f, g]", list.toString());
		
		//Add an element to the front
		list.add("a");
		
		//Remove from the front
		assertEquals("a", list.remove(0));
		assertEquals(6, list.size());
		assertEquals("[b, c, d, e, f, g]", list.toString());
		
		//Add an element to the back
		list.add("z");
		
		//Remove from the front
		assertEquals("b", list.remove(0));
		assertEquals(6, list.size());
		assertEquals("[c, d, e, f, g, z]", list.toString());
		
		//Add an element to the middle
		list.add("q");
		
		//Remove from the front
		assertEquals("c", list.remove(0));
		assertEquals(6, list.size());
		assertEquals("[d, e, f, g, q, z]", list.toString());
		
		/** Test removing from the back */
		
		//Remove from the back
		assertEquals("z", list.remove(list.size() - 1));
		assertEquals(5, list.size());
		assertEquals("[d, e, f, g, q]", list.toString());
		
		//Add to the front
		list.add("a");
		
		//Remove from the back
		assertEquals("q", list.remove(list.size() - 1));
		assertEquals(5, list.size());
		assertEquals("[a, d, e, f, g]", list.toString());
		
		//Add to the middle
		list.add("c");
		
		//Remove from the back
		assertEquals("g", list.remove(list.size() - 1));
		assertEquals(5, list.size());
		assertEquals("[a, c, d, e, f]", list.toString());
		
		//Add to the back
		list.add("r");
		
		//Remove from the back
		assertEquals("r", list.remove(list.size() - 1));
		assertEquals(5, list.size());
		assertEquals("[a, c, d, e, f]", list.toString());
		
		/** Test removing from the middle */
		
		//Remove from the middle
		assertEquals("d", list.remove(2));
		assertEquals(4, list.size());
		assertEquals("[a, c, e, f]", list.toString());
		
		//Add to the front
		list.remove(0);
		list.add("b");
		list.add("a");
		
		//Remove from the middle
		assertEquals("c", list.remove(2));
		assertEquals(4, list.size());
		assertEquals("[a, b, e, f]", list.toString());
		
		//Add to the middle
		list.add("d");
		
		//Remove from the middle
		assertEquals("e", list.remove(3));
		assertEquals(4, list.size());
		assertEquals("[a, b, d, f]", list.toString());
		
		//Add to the back
		list.add("m");
		
		//Remove from the middle
		assertEquals("d", list.remove(2));
		assertEquals(4, list.size());
		assertEquals("[a, b, f, m]", list.toString());
		
		//Try removing at an out of bounds index
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("[a, b, f, m]", list.toString());
		}
		
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("[a, b, f, m]", list.toString());
		}
	}
	
	/**
	 * Tests contains(e)
	 */
	@Test
	public void testContains() {
		assertTrue(list.isEmpty());
		//Try to find an element in an empty list
		assertFalse(list.contains("a"));
		list.add("c");
		list.add("b");
		list.add("g");
		list.add("e");
		list.add("d");
		list.add("f");
		list.add("a");
		assertTrue(list.contains("a"));
		assertTrue(list.contains("d"));
		list.contains("g");
		assertTrue(list.contains("g"));
		assertFalse(list.contains("z"));
		
		//Check if the list contains null
		try {
			list.contains(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("List cannot have null elements", e.getMessage());
		}
	}

	/**
	 * Tests indexOf(e)
	 */
	@Test
	public void testIndexOf() {
		assertTrue(list.isEmpty());
		//Try to find an element in an empty list
		assertEquals(-1, list.indexOf("a"));
		list.add("c");
		list.add("b");
		list.add("g");
		list.add("e");
		list.add("d");
		list.add("f");
		list.add("a");
		assertEquals(0, list.indexOf("a"));
		assertEquals(3, list.indexOf("d"));
		assertEquals(6, list.indexOf("g"));
		assertEquals(-1, list.indexOf("z"));
	}
	
	/**
	 * Tests get(int)
	 */
	@Test
	public void testGet() {
		list.add("a");
		list.add("b");
		list.add("c");
		//Try getting at an out of bounds index
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("[a, b, c]", list.toString());
		}
		
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("[a, b, c]", list.toString());
		}
	}
	
	/**
	 * Tests equals()
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		try {
			setUp();
			
			// Tests the passing null element returns false.
			assertFalse(list.equals(null));

			// Tests that passing non-SortedLinkedList type returns false.
			assertFalse(list.equals("Not a SortedLinkedList."));

			// Tests that passing list of unequal size returns false.
			SortedLinkedList<String> otherList = new SortedLinkedList<String>();
			list.add("apple");
			list.add("banana");
			otherList.add("apple");
			assertFalse(otherList.equals(list));

			// Tests that passing equal size list with distinct elements returns false.
			otherList.add("carrot");
			assertFalse(otherList.equals(list));

			// Tests that lists of equal size and matching elements return true.
			otherList.remove(1);
			otherList.add("banana");
			assertTrue(otherList.equals(list));
		} catch (Exception e) {
			fail("Unexpected Exception thrown.");
		}
	}

}