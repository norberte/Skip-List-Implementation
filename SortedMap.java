package SortedMap;

import java.util.ArrayList;
import java.util.Iterator;

import SkipList.*;
public class SortedMap implements sortedMapInterface{
	
	private SkipList sl;
	private Node find;
	
	public SortedMap(SkipList sl){
		this.sl = sl;
	}
	
	public SortedMap(){
		sl = new SkipList();
	}
	
	public Node firstEntry() {
		if(isEmpty()){
			return null;
		} else {
			Node p = sl.getHeadNode(); // start by getting the head of the skip list (neg. infinity)
			
			// go to the bottom list
			if(p.getBelow() != null){
				while(p.getBelow() != null){
					p = p.getBelow();
				}
			}
			// get the first entry, the one right after the head node
			p = p.getNext();
			return p;
		}
	}

	public Node lastEntry() {
		if(isEmpty()){
			return null;
		} else {
			Node p = sl.getTailNode(); // start by getting the head of the skip list (neg. infinity)
			
			// go to the bottom list
			if(p.getBelow() != null){
				while(p.getBelow() != null){
					p = p.getBelow();
				}
			}
			// get the last entry, the one right before the tail node
			p = p.getPrev();
			return p;
		}
	}

	public Node ceilingEntry(double k) {
		find = sl.skipSearch(k);
		if(find.getKey() < k){
			return find.getNext();
		} else if (find.getKey() == k){
			return find;
		} else {
			return null;
		}
	}

	public Node floorEntry(double k) {
		find = sl.skipSearch(k);
		if (find.getKey() == k || find.getKey() < k){ // key equal to k case and
			// if the skipSearch returned a key that is smaller than k, then is as close as it gets to k
			return find;
		} else {
			return null; // no such entry case
		}
	}

	public Node lowerEntry(double k) {
		find = sl.skipSearch(k);
		if (find.getKey() < k){ // if the skipSearch returned a key that is smaller than k, then is as close as it gets to k
			return find;
		} else if (find.getKey() == k){
			return find.getPrev();
		} else {
			return null; // no such entry case
		}
	}

	public Node higherEntry(double k) {
		find = sl.skipSearch(k);
		if (find.getKey() == k || find.getKey() < k){ // key equal to k case and
			// if the skipSearch returned a key that is smaller than k, then is as close as it gets to k
			if (find.getNext().getKey() > k){
				return find.getNext();  // return the entry that is
			} else {return null;}
		} else {
			return null; // no such entry case
		}
	}

	private ArrayList<Node> subMapHelper(double k1, double k2){
		ArrayList<Node> allEntries = new ArrayList<>(size());
		if(isEmpty()){
			return allEntries;
		}
		
		Node start = ceilingEntry(k1);
		Node end = lowerEntry(k2);
		
		Node p = start;
		allEntries.add(p);
		
		// go through all the nodes from start to end and add each node to the array list
		while(!p.equals(end)){
			p = p.getNext();
			allEntries.add(p);
		}
		return allEntries;
	}
	
	public SortedMap subMap(double k1, double k2) {
		ArrayList<Node> nodes = subMapHelper(k1,k2);
		SkipList mySL = new SkipList();
		
		Iterator<Node> it = nodes.iterator();
		while(it.hasNext()){
			Node current = it.next();
			mySL.insert(current.getKey(), current.getValue());
		}
		
		return new SortedMap(mySL);
	}

	public int size() {
		return sl.getSize();
	}

	public boolean isEmpty() {
		if(size() == 0){
			return true;
		} else {
			return false;
		}
	}

	public Integer get(double k) {
		find = sl.skipSearch(k);
		
		if(find.getKey() == k){
			return find.getValue();
		} else {
			return null;
		}
	}

	public Integer put(double k, Integer v) {
		find = sl.skipSearch(k);
		
		if(find.getKey() != k){
			sl.insert(k, v);
			return null;
		} else {
			int temp = find.getValue();
			find.setValue(v);
			return temp;
		}
	}

	public Integer remove(double k) {
		find = sl.skipSearch(k);
		
		if(find.getKey() == k){
			int temp = find.getValue();
			sl.remove(k);
			return temp;
		} else {
			return null;
		}
	}

	public ArrayList<Double> keySet() {
		ArrayList<Double> keys = new ArrayList<>(size());
		if(isEmpty()){
			return keys;
		}
		Node p = sl.getHeadNode(); // start by getting the head of the skip list (neg. infinity)
		
		// go to the bottom list
		if(p.getBelow() != null){
			while(p.getBelow() != null){
				p = p.getBelow();
			}
		}
		
		// go through all the nodes and add each node's key to the array list
		if(p.getNext() != null){
			while(p.getNext() != null && p.getNext().getKey() != Double.POSITIVE_INFINITY){
				p = p.getNext();
				keys.add(p.getKey());
			}
		}
		
		return keys;
	}

	public ArrayList<Integer> values() {
		ArrayList<Integer> values = new ArrayList<>(size());
		if(isEmpty()){
			return values;
		}
		Node p = sl.getHeadNode(); // start by getting the head of the skip list (neg. infinity)
		
		// go to the bottom list
		if(p.getBelow() != null){
			while(p.getBelow() != null){
				p = p.getBelow();
			}
		}
		
		// go through all the nodes and add each node's value to the array list
		if(p.getNext() != null){
			while(p.getNext() != null && p.getNext().getKey() != Double.POSITIVE_INFINITY){
				p = p.getNext();
				values.add(p.getValue());
			}
		}
		
		return values;
	}

	public ArrayList<Node> entrySet() {
		ArrayList<Node> allEntries = new ArrayList<>(size());
		if(isEmpty()){
			return allEntries;
		}
		Node p = sl.getHeadNode(); // start by getting the head of the skip list (neg. infinity)
		
		// go to the bottom list
		if(p.getBelow() != null){
			while(p.getBelow() != null){
				p = p.getBelow();
			}
		}
		
		// go through all the nodes and add each node to the array list
		if(p.getNext() != null){
			while(p.getNext() != null && p.getNext().getKey() != Double.POSITIVE_INFINITY){
				p = p.getNext();
				allEntries.add(p);
			}
		}
		return allEntries;
	}

	public void resetSkipList(){
		sl = new SkipList();
	}
	
	public SkipList useSkipList(){
		return sl;
	}
}