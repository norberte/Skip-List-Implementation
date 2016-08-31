package SkipList;

public class Node {
	private Node up = null;
	private Node down = null;
	private Node next = null;
	private Node prev = null;
	private Double key;
	private Integer value;
	
	public Node(Double k, Integer v ){
		setKey(k);
		setValue(v);
		up = down = next = prev = null;
	}
	
	public Node(Double k){
		setKey(k);
		setValue(null);
		up = down = next = prev = null;
	}
	
	public double getKey() {
		return key;
	}
	
	public void setKey(Double key){
		this.key = key;
	}
	public void setValue(Integer value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public Node getAbove() {
		return up;
	}
	public void setAbove(Node up) {
		this.up = up;
	}
	public Node getBelow() {
		return down;
	}
	public void setBelow(Node down) {
		this.down = down;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	
	public String mainProperties(){
		return this.key + " " + this.value;
	}
	
	public boolean compareNodes(Node n){
		if(this.key == n.getKey() && this.value == n.getValue()){
			return true;	// I do not want to check the Node's 4 directional pointers when I compare them
							// All I want to check is that their key and their value is the same
		} else {
			return false;
		}
	}
	
	 // For testing purposes only... not included to not ruin the coverage
	/*
	public String info(){
		String s = "";
		if(this.up != null){
			s = s + "Above: " + this.up.getKey() + System.lineSeparator();
		}
		if(this.down != null){
			s = s + "Below: " + this.down.getKey() + System.lineSeparator();
		}
		if(this.prev != null){
			s = s + "Prev: " + this.prev.getKey() + System.lineSeparator();
		}
		if(this.next != null){
			s = s + "Next : " + this.next.getKey() + System.lineSeparator();
		}
		s = s + "Key :" + this.key;
		return s;
	}
	*/
	
	
}
