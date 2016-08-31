package SortedMap;

import java.util.ArrayList;

import SkipList.Node;

public interface sortedMapInterface {
	public abstract Node firstEntry(); 
	public abstract Node lastEntry();
	public abstract Node ceilingEntry(double k);
	public abstract Node floorEntry(double k);
	public abstract Node lowerEntry(double k);
	public abstract Node higherEntry(double k);
	public abstract SortedMap subMap(double k1, double k2);
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract Integer get(double k);
	public abstract Integer put(double k, Integer v);
	public abstract Integer remove(double k);
	public abstract ArrayList<Double> keySet();
	public abstract ArrayList<Integer> values();
	public abstract ArrayList<Node> entrySet();
}
