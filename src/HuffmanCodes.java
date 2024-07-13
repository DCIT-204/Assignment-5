package src;
import java.util.HashMap;

public class HuffmanCodes {
    //method obtains frequencies of each character
    public HashMap<Character,Integer> getFrequency(String inputText){
        HashMap<Character,Integer> frequency = new HashMap<>();

        for(char character: inputText.toCharArray()){
            frequency.put(character,frequency.getOrDefault(character,0)+1);
        }
        return frequency;
    }

    //Node class with two constructors
    static class Node implements Comparable<Node>{
        //attributes of Node class
        int frequency;
        char character;
        Node left;
        Node right;

        //1ST constructor initializes frequency and character attributes
        public Node(char character,int frequency){
            this.character=character;
            this.frequency=frequency;
        }

        //2ND constructor initializes frequency,left and right Nodes
        public Node(int frequency,Node left,Node right){
            this.frequency=frequency;
            this.left=left;
            this.right=right;
        }

        //compareTo method that aids priority queue to sort/order Nodes
        @Override
        public int compareTo(Node otherNode) {
            return this.frequency-otherNode.frequency;
        }

    }
}
