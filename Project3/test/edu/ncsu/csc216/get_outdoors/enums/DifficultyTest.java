package edu.ncsu.csc216.get_outdoors.enums;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests that the values of the Difficulty enumeration are correct.
 * 
 * @author demills
 */
public class DifficultyTest {

	/**
	 * Compares the values of the enumeration to their expected values.
	 */
	@Test
	public void test() {
		try {
			assertEquals(Difficulty.EASY, Difficulty.valueOf("EASY"));
			assertEquals(Difficulty.MODERATE, Difficulty.valueOf("MODERATE"));
			assertEquals(Difficulty.CHALLENGING, Difficulty.valueOf("CHALLENGING"));
			assertEquals(Difficulty.DIFFICULT, Difficulty.valueOf("DIFFICULT"));
			assertEquals(Difficulty.VERY_DIFFICULT, Difficulty.valueOf("VERY_DIFFICULT"));
			assertEquals(Difficulty.EXTREME, Difficulty.valueOf("EXTREME"));
		} catch (Exception e) {
			fail("At least one Difficulty constant is incorrect.");
		}
	}
}