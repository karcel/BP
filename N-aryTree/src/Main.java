

public class Main implements Patterns {


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node tree = new Node();
		
		Node ip1 = new Node(IP);
		Node user1 = new Node(USER);
		Node word1 = new Node(WORD);
		tree.add(ip1);
		tree.add(user1);
		tree.add(word1);

        Leaf leaf1 = new Leaf("ipHello");
        Leaf leaf2 = new Leaf("userHello");
        Leaf leaf3 = new Leaf("wordHello");
        Leaf leaf4 = new Leaf("wordHello");
        tree.add(ip1, leaf1);
        tree.add(user1, leaf2);
        tree.add(word1, leaf3);
        tree.add(word1, leaf4);
		System.out.println(tree.toString());
	}

}
