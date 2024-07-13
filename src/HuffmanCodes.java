package src;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodes {
    //method obtains frequencies of each character
    public HashMap<Character, Integer> getFrequency(String inputText) {
        HashMap<Character, Integer> frequency = new HashMap<>();

        for (char character : inputText.toCharArray()) {
            frequency.put(character, frequency.getOrDefault(character, 0) + 1);
        }
        return frequency;
    }

    //Node class with two constructors
    static class Node implements Comparable<Node> {
        //attributes of Node class
        int frequency;
        char character;
        Node left;
        Node right;

        //1ST constructor initializes frequency and character attributes
        public Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        //2ND constructor initializes frequency,left and right Nodes
        public Node(int frequency, Node left, Node right) {
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        //compareTo method that aids PriorityQueue to sort/order Nodes
        @Override
        public int compareTo(Node otherNode) {
            return this.frequency - otherNode.frequency;
        }

    }


    //building the huffman tree to obtain root node

    public static Node huffmanTree(HashMap<Character, Integer> frequencyMap) {
        PriorityQueue<Node> huffmanQueue = new PriorityQueue<>();

        //adding nodes to the PriorityQueue
        for (char character : frequencyMap.keySet()) {
            huffmanQueue.add(new Node(character,frequencyMap.get(character)));
        }

        //compressing each node into one root node
        while(huffmanQueue.size()>1){
            Node left= huffmanQueue.poll();
            Node right=huffmanQueue.poll();
            Node parent = new Node(left.frequency+right.frequency,left,right);
            huffmanQueue.add(parent);
        }
        //returning the root node
        return huffmanQueue.poll();
    }

}

