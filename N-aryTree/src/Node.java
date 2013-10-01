import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

// DOKONCIT NEDOROBENE METODY... UPRAVIT METODY KTORE NEFUNGUJU... PODLA MOZNOSTI PREKOPAT
// TOSTRING - PRIPOJIT LIBRARY JSON-U A SPRAVIT TO S JEHO POMOCOU
// DOPLNIT ADD(STRING SENTENCE), KTORE BUDE ROZOBERAT LOG, VYTVARAT
// UZLY A LISTY A ZAROVEN ICH VKLADAT DO AKTUALNEHO STROMU


public class Node implements Patterns{
	

	private HashMap<Pattern, Node> childs;
	private ArrayList<Leaf> leaves;
	private Pattern reg;
	private boolean isRoot;
	
	public Pattern getReg()
	{
		return reg;
	}
	
	public ArrayList<Leaf> getLeaves()
	{
		return leaves;
	}
	
	public HashMap<Pattern, Node> getChilds()
	{
		return childs;
	}
	
	public Node()
	{
		isRoot= true;
		reg = null;
		childs = new HashMap<Pattern,Node>();
		leaves = new ArrayList<Leaf>();
	}

	public boolean getIsRoot()
	{
		return isRoot;
	}
	
	public Node(Pattern regex)
	{
	
		isRoot = false;
		reg= regex;
		childs = new HashMap<Pattern,Node>();
		leaves = new ArrayList<Leaf>();
	
	}
	
	
	///METHODS
	public boolean add(Node child)
	{
		if (contains(child)) return false;
		if (child.getReg() == null) return false;
		childs.put(child.getReg(), child);
		return true;
	}
	
	public boolean add(Leaf leaf)
	{
		if (contains(leaf)) return false;
		if (leaf.getMessagge().isEmpty()) return false;
		leaves.add(leaf);
		return true;
	}
	
	public boolean add(Node parent, Node child)
	{
		if(!(contains(parent))) return false;
		return find(parent).add(child);
	}
	
	public boolean add(Node parent, Leaf leaf)
	{
		if(!(contains(parent))) return false;
		return find(parent).add(leaf);
	}
		
	//Vyriesit problem s hlaskou:
	//Method addAll(Collection<Node> has the same erasure addAll(Collection<E>) as another method
	/*public boolean addAll(Collection<Node> coll)
	{
		Iterator<Node> it = coll.iterator();
		
		while(it.hasNext())
		{
			boolean addedAll = add(it.next());
			if(addedAll == false) return false;
		}
		return true;
	}
	
	public boolean addAll(Node parent, Collection<Node> coll)
	{
		if (contains(parent) == null) return false;
		
		Iterator<Node> it = coll.iterator();
		
		while(it.hasNext())
		{
			Node next = it.next();
			if (contains(next) != null) return false;
			boolean addedAll = contains(parent).add(next);
			if(addedAll == false) return false;
		}
		return true;
	}
	
	public boolean addAll(Collection<Leaf> coll)
	{
		return false;
	}
	
	public boolean addAll(Node parent, Collection<Leaf> coll)
	{
		return false;
	}
	*/

	public void clear()
	{
		childs.clear();
		leaves.clear();
	}
	
	public Node find(Node node)
	{
		if (equals(node)) return this;
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			if (node.equals(x)) return x;
			Node y = x.find(node);
			if (y != null) return y; 
		}
		return null;
	}
	
	public Leaf find(Leaf leaf)
	{
		
		for(Leaf item : leaves)
		{
			if (item.equals(leaf)) return item;
		}
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			Leaf found = x.find(leaf);
			if (found != null) return found;
		}
		return null;
		
	}
	
	public boolean contains(Node node)
	{
		if (find(node) != null) return true;
		return false;
	}
	
	public boolean contains(Leaf leaf)
	{
		if (find(leaf) != null) return true;
		return false;
	}
	
	//VYRIESIT PROBLEM S GENERIKAMI
	/*
	public boolean containsAll(Collection<Node> nodes)
	{
		return false;
	}
	
	public boolean containsAll(Collection<Leaf> leaves)
	{
		return false;
	}*/

	public Node commonAncestor(Node node1, Node node2)
	{
		return null;
	}
	
	public Node commonAncestor(Node node, Leaf leaf)
	{
		return null;
	}
	
	public Node commonAncestor(Leaf leaf1, Leaf leaf2)
	{
		return null;
	}
	
	public boolean isAncestor(Node node, Node child)
	{
		if (child.isRoot == true) return false;
		
		Node x = parent(child);
		while(x.isRoot == false)
		{
			if(x.equals(node)) return true;
			x = parent(x);
		}
		if (x.equals(node)) return true;
		return false;
		
	}
	
	public boolean isAncestor(Node node, Leaf leaf)
	{
		
		Node x = parent(leaf);
		while(x.isRoot == false)
		{
			if(x.equals(node)) return true;
			x = parent(x);
		}
		if (x.equals(node)) return true;
		return false;
	}
	
	public boolean isDescendant(Node parent, Node node)
	{
		return parent.contains(node);
	}
	
	public boolean isDescendant(Node parent, Leaf leaf)
	{
		return parent.contains(leaf);
	}
	
	public boolean isEmpty()
	{
		if(leaves.size() == 0 && getChilds().size() == 0) return true;
		return false;
	}
	
	public Node parent(Node node)
	{
		if (node == null) return null;
	
		if (node.isRoot == true) return null;
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			if (node.equals(x)) return this;
			Node y = x.find(node);
			if (y != null) return x; 
		}
		return null;
	}
	
	public Node parent(Leaf leaf)
	{
		if (leaf == null) return null;
		
		if (leaves.contains(leaf)) return this;
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			Node y = x.parent(leaf);
			if (y != null) return y;
		}
		return null;
	}
	
	public boolean remove(Node node)
	{
		if (node == null) return false;
		
		if (!(contains(node))) return false;
		
		parent(node).getChilds().remove(node.getReg());
		return true;
	}
	
	public boolean remove(Leaf leaf)
	{
		if (leaf == null) return false;
		
		if (!(contains(leaf))) return false;
		
		parent(leaf).getLeaves().remove(leaf);
		return true;
	}
	
	public int size()
	{
		return sizeOfNodes() + sizeOfLeaves();
	}
	
	public int sizeOfNodes()
	{
		int counter = 1;
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			counter += x.sizeOfNodes();
		}
		
		return counter;
	}
	
	public int sizeOfLeaves()
	{
		int counter = leaves.size();
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			counter += x.sizeOfLeaves();
		}
		
		return counter;
	}
	
	public boolean equals(Node node)
	{
		if (reg != node.getReg()) return false;
		if (isRoot != node.getIsRoot()) return false;
		
		for(Leaf item : leaves)
		{
			if (!(node.getLeaves().contains(item))) return false;
		}
		
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		if(childs.size() != node.getChilds().size()) return false;
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			if (!(node.getChilds().containsValue(x))) return false;
			if (!(x.equals(node.find(x)))) return false;
		}
		
		return true;
	}
	
	
	public String toString()
	{
		String thisIs = "";
		
		if(isRoot) 
		{
			thisIs = "Root";
		}
		else 
		{
			if(this.getReg().equals(IP)) thisIs = "\"IP\"";
			if(this.getReg().equals(USER)) thisIs = "\"USER\"";
			if(this.getReg().equals(TMSP)) thisIs = "\"TMSP\"";
			if(this.getReg().equals(WORD)) thisIs = "\"WORD\"";
			
		}
		
		if (childs.size() == 0 && leaves.size() == 0) 
		{
			return "{ \"" +thisIs+"\"" + System.getProperty("line.separator") + "}";
		}
		
		
		String children ="";
		Iterator<Node> keySetIterator = childs.values().iterator();
		
		while(keySetIterator.hasNext())
		{
			Node x = keySetIterator.next();
			children = children + x.helpToString();
		}
		return "{ \"" +thisIs+"\" : {"+children + "}"+System.getProperty("line.separator") + "}";
	}
	
	private String helpToString()
	{
		String output = "";
		
		if(isRoot) 
		{
			output = "\"Root\"";
		}
		else 
		{
			if(this.getReg().equals(IP)) output = "\"IP\"";
			if(this.getReg().equals(USER)) output = "\"USER\"";
			if(this.getReg().equals(TMSP)) output = "\"TMSP\"";
			if(this.getReg().equals(WORD)) output = "\"WORD\"";
			
		}
		
		output = output + " : {" + System.getProperty("line.separator");
		
		if (leaves.size() > 0) 
		{
			for(Leaf item : leaves)
			{
				output = output + item.toString() + System.getProperty("line.separator");;
			}
		}
		
		if (childs.size() > 0)
		{
			Iterator<Node> keySetIterator = childs.values().iterator();
			
			while(keySetIterator.hasNext())
			{
				Node x = keySetIterator.next();
				output = output + x.helpToString();
			}
		}
		
		return output + "}";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
