package SortedMap;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import SkipList.*;

/* Coverage is 97.8 %, if you only look at the Sorted Map package and all 19 tests pass.
 */

public class UnitTesting {
	SortedMap MAP = new SortedMap();
	SkipList sl = new SkipList();

	@Before
	public void setUp(){
		MAP.resetSkipList();
		sl = new SkipList();
	}
	
	@Test
	public void removeNotExistingKey() {
		Integer value = MAP.remove(5.0);
		Integer expected = null;
		assertEquals(value, expected);
	}
	
	@Test
	public void removeAnExistingKey() {
		Node inserted = MAP.useSkipList().insert(14.0, 2); // using the skip list's method, because it was already tested and I know it works 100 %
		Integer value = MAP.remove(inserted.getKey());
		Integer expected = 2;
		
		assertEquals(value, expected);
	}
	
	@Test
	public void putInAWholeEntry() {
		Integer getNull = MAP.put(10.0, 5);
		assertEquals(getNull, null);
	}
	
	@Test
	public void usePutToReplaceAValue() {
		Node inserted = MAP.useSkipList().insert(14.0, 2);
		Integer changeValue = MAP.put(inserted.getKey(), 5);
		Integer expected = 2; // return the old value
		assertEquals(changeValue, expected);
	}
	
	@Test
	public void checkSize() {
		MAP.put(12, 6);
		int size = MAP.size();
		assertEquals(size, 1);
	}
	
	@Test
	public void checkIsEmpty() {
		boolean empty = MAP.isEmpty();
		
		assertTrue(empty);
	}
	 
	@Test
	public void checkIsNotEmpty() {
		MAP.put(12, 6);
		boolean empty = MAP.isEmpty();
		
		assertTrue(empty == false);
	}
	
	@Test
	public void getExistingValue() {
		MAP.put(12.0, 6);
		Integer value = MAP.get(12.0);
		Integer expected = 6;
		
		assertEquals(value, expected);
	}
	
	@Test
	public void getNotExistingValue() {
		Integer shouldBeNull = MAP.get(20.0);
		Integer expected = null;
		
		assertEquals(shouldBeNull, expected);
	}
	
	@Test
	public void testCollectionOfKeys() {
		ArrayList<Double> expectedKeys = new ArrayList<>(5);
		MAP.put(20.0, 5);
		MAP.put(15.0, 4);
		MAP.put(10.0, 3);
		MAP.put(5.0, 2);
		MAP.put(1.0, 1);
		expectedKeys.add(1.0);
		expectedKeys.add(5.0);
		expectedKeys.add(10.0);
		expectedKeys.add(15.0);
		expectedKeys.add(20.0);
		
		ArrayList<Double> keySet = MAP.keySet();
		assertTrue(expectedKeys.toString().equals( keySet.toString() ));
	}
	
	@Test
	public void testCollectionOfValues() {
		ArrayList<Integer> expectedValues = new ArrayList<>(5);
		MAP.put(20.0, 5);
		MAP.put(15.0, 4);
		MAP.put(10.0, 3);
		MAP.put(5.0, 2);
		MAP.put(1.0, 1);
		expectedValues.add(MAP.get(1.0));
		expectedValues.add(MAP.get(5.0));
		expectedValues.add(MAP.get(10.0));
		expectedValues.add(MAP.get(15.0));
		expectedValues.add(MAP.get(20.0));
		
		ArrayList<Integer> valueSet = MAP.values();
		assertTrue(expectedValues.toString().equals( valueSet.toString() ));
	}
	
	@Test
	public void testCollectionOfEntries() {
		ArrayList<Node> expectedNodes = new ArrayList<>(5);
		MAP.put(20.0, 5);
		MAP.put(15.0, 4);
		MAP.put(10.0, 3);
		MAP.put(5.0, 2);
		MAP.put(1.0, 1);
		
		expectedNodes.add(MAP.useSkipList().skipSearch(1.0));
		expectedNodes.add(MAP.useSkipList().skipSearch(5.0));
		expectedNodes.add(MAP.useSkipList().skipSearch(10.0));
		expectedNodes.add(MAP.useSkipList().skipSearch(15.0));
		expectedNodes.add(MAP.useSkipList().skipSearch(20.0));
		
		ArrayList<Node> nodes = MAP.entrySet();
		
		assertTrue(expectedNodes.get(0).mainProperties().equals(nodes.get(0).mainProperties()));
		assertTrue(expectedNodes.get(1).mainProperties().equals(nodes.get(1).mainProperties()));
		assertTrue(expectedNodes.get(2).mainProperties().equals(nodes.get(2).mainProperties()));
		assertTrue(expectedNodes.get(3).mainProperties().equals(nodes.get(3).mainProperties()));
		assertTrue(expectedNodes.get(4).mainProperties().equals(nodes.get(4).mainProperties()));
	}
	
	@Test
	public void getFirstEntry() {
		Node firstExpected = MAP.useSkipList().insert(1.0, 1);
		MAP.useSkipList().insert(5.0, 2);
		MAP.useSkipList().insert(10.0, 3);
		
		Node first = MAP.firstEntry();
		
		assertTrue(firstExpected.compareNodes(first));
	}
	
	@Test
	public void getLastEntry() {
		MAP.useSkipList().insert(1.0, 1);
		MAP.useSkipList().insert(5.0, 2);
		Node lastExpected =  MAP.useSkipList().insert(10.0, 3);
		
		Node last = MAP.lastEntry();
		
		assertTrue(lastExpected.compareNodes(last));
	}
	
	@Test
	public void getCeilingEntry() {
		MAP.useSkipList().insert(1.0, 1);
		MAP.useSkipList().insert(5.0, 2);
		Node expected = MAP.useSkipList().insert(10.0, 3);
		MAP.useSkipList().insert(12.0, 9);
		MAP.useSkipList().insert(15.0, 8);
		
		
		Node ceilingEntry = MAP.ceilingEntry(9.0);
		assertTrue(ceilingEntry.compareNodes(expected));
	}
	
	@Test
	public void getFloorEntry() {
		MAP.useSkipList().insert(1.0, 1);
		Node expected = MAP.useSkipList().insert(5.0, 2);
		MAP.useSkipList().insert(10.0, 3);
		MAP.useSkipList().insert(12.0, 9);
		MAP.useSkipList().insert(15.0, 8);
		
		
		Node ceilingEntry = MAP.floorEntry(9.0);
		assertTrue(ceilingEntry.compareNodes(expected));
	}
	
	@Test
	public void getLowerEntry() {
		MAP.useSkipList().insert(1.0, 1);
		Node expected = MAP.useSkipList().insert(5.0, 2);
		MAP.useSkipList().insert(10.0, 3);
		MAP.useSkipList().insert(12.0, 9);
		MAP.useSkipList().insert(15.0, 8);
		
		
		Node ceilingEntry = MAP.lowerEntry(7.0);
		assertTrue(ceilingEntry.compareNodes(expected));
	}
	
	@Test
	public void getHigherEntry() {
		MAP.useSkipList().insert(1.0, 1);
		Node expected = MAP.useSkipList().insert(5.0, 2);
		MAP.useSkipList().insert(10.0, 3);
		MAP.useSkipList().insert(12.0, 9);
		MAP.useSkipList().insert(15.0, 8);
		
		
		Node ceilingEntry = MAP.higherEntry(2.0);
		assertTrue(ceilingEntry.compareNodes(expected));
	}
	
	@Test
	public void testSubMap() {
		MAP.useSkipList().insert(1.0, 1);
		MAP.useSkipList().insert(5.0, 2);
		MAP.useSkipList().insert(10.0, 3);
		MAP.useSkipList().insert(12.0, 9);
		MAP.useSkipList().insert(14.0, 10);
		MAP.useSkipList().insert(15.0, 8);
		
		SortedMap subMap = MAP.subMap(5.0, 14.0); // nodes with key 5.0, 10.0 and 12.0
		
		SkipList testSkipList = new SkipList();
		testSkipList.insert(5.0, 2);
		testSkipList.insert(10.0, 3);
		testSkipList.insert(12.0, 9);
		
		SortedMap expected = new SortedMap(testSkipList);
		
		System.out.println("Expected: " + expected.entrySet().toString());
		System.out.println("Submap: " + subMap.entrySet().toString());
		
		// 3 nodes expected in the subMap: nodes with key 5.0, 10.0 and 12.0
		assertTrue(expected.entrySet().get(0).mainProperties().equals(subMap.entrySet().get(0).mainProperties()));
		assertTrue(expected.entrySet().get(1).mainProperties().equals(subMap.entrySet().get(1).mainProperties()));
		assertTrue(expected.entrySet().get(2).mainProperties().equals(subMap.entrySet().get(2).mainProperties()));
	}
	
}

