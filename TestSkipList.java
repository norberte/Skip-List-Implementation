package SkipList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/* Coverage is 90.9% if you only look at the SkipList package.
 * Algorithms were written with the help and guidance of the COSC 222 (Data Structures and Algorithms in Java, 6th Edition) textbook
 * Both 2 tests pass even if you run them 10-20 times, since it does not depend on any random situation that you get.
 */

public class TestSkipList {
	SkipList mySL = new SkipList();

	@Before
	public void setUp(){
		mySL = new SkipList(); // reset skip list 
	}
	
	@Test
	public void testAdditionOfOne() {
		mySL.insert(3.0,2);	// insert
		mySL.insert(4.0,4);	// insert
		Node insertedNode = mySL.insert(5.0,5);	// insert
		Node testingNode = new Node(5.0,5);
		
		int size = mySL.getSize();
		
		assertEquals(size, 3);
		assertTrue(insertedNode.compareNodes(testingNode));
	}
	
	@Test
	public void testDeletionOfOne() {
		mySL.insert(14.0, 5);
		Node inserted = mySL.insert(16.0, 10);
		mySL.insert(18.0, 15);
		
		mySL.remove(inserted.getKey());
		
		Node inserted_prev = mySL.skipSearch(14.0);
		Node inserted_next = mySL.skipSearch(18.0);
		assertTrue(inserted_next.getPrev().equals(inserted_prev));
		assertTrue(inserted_prev.getNext().equals(inserted_next));
	}
	
	
}
