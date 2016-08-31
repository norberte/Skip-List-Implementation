package SkipList;

import java.util.Random;

public class SkipList {
	private final double NEG_INF = Double.NEGATIVE_INFINITY;
	private final double POS_INF = Double.POSITIVE_INFINITY;
	private Node head;
	private Node tail;
	private Random rand;	
	private int height; 
	private int size;
	
	
	public SkipList(){
		height = 0;
		size = 0;
		rand = new Random();
		head = new Node(NEG_INF);
		tail = new Node(POS_INF);
		head.setNext(tail);
	}
	
	public Node skipSearch(Double k){
		Node pos = head;
		if(size == 0){ // if there is no Node in the skip List, do not search for nothing 
			return pos;
		} else if(height == 0){	// special skipSearch algorithm, of there is only a single list, without a tower in the skip list
			while(k >= pos.getNext().getKey()){
				pos = pos.getNext();
			}
		} else {	// regular skipSearch algorithm
			if(pos.getBelow() != null ){
				while(pos.getBelow() != null) {
					pos = pos.getBelow();
					if(pos.getNext() != null){
						while(k >= pos.getNext().getKey()){
							pos = pos.getNext();
						}
					} else { // if there is no node on that level
						continue;	// just move one level below
					}
				}
			} else { // pos.below() == null, so you are at the bottom of the skip list
				while(k >= pos.getNext().getKey()){
					pos = pos.getNext();
				}
			}
		}
		
		return pos;
	}
	
	private Node insertAfterAbove(Node p, Node q, Double k, Integer v){
		Node newNode = new Node(k,v);
		newNode.setPrev(p);
		newNode.setBelow(q);
		newNode.setAbove(null);
		
		if(p != null){
			newNode.setNext(p.getNext());
			if(p.getNext() != null){
				p.getNext().setPrev(newNode);
			}
			p.setNext(newNode);
		}
		
		if(q != null){
			q.setAbove(newNode);
		}
		return newNode;
	}
	
	public Node insert(Double k, Integer v){
		Node pos = skipSearch(k);
		Node q = null;
		int i = -1;
		
		int r;
		do{ // while the coin flip is not a head 
			i++;
			if(i >= height){
				height++;
				head = insertAfterAbove(null, head, NEG_INF, 0);
				insertAfterAbove(head, tail, POS_INF, 0);
			}
			q = insertAfterAbove(pos,q,k,v);
			
			if(height != 0){ // do not check for above, if the height is 0...avoid null pointers
			//	if(!pos.compareNodes(head)){ // if the position is the starting position, then do not scam backwards 
				while(pos.getAbove() == null && pos.getPrev() != null){
					pos = pos.getPrev();
				}
				pos = pos.getAbove();
			}
			r = rand.nextInt(2);
		}while(r != 0);
		size++;
		return q;
	}
	
	public void remove(Double k){
		Node pos = skipSearch(k);
		if(pos.getKey() != k){
			return;
		}
		int counter = 0;
		do{
			if(counter !=0){ // do not do this when it is the first time looping through
				pos = pos.getAbove();
			}
			counter++;
			Node prev = pos.getPrev();
			Node next = pos.getNext();
			prev.setNext(next);
			next.setPrev(prev);
		}while(pos.getAbove() != null);
		size--;
	}

	public int getSize(){
		return size;
	}
	
	public Node getHeadNode(){
		return head;
	}
	
	public Node getTailNode(){
		return tail;
	}
}
