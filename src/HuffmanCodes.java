package src;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCodes {
    //method obtains frequencies of each character
    public static HashMap<Character, Integer> getFrequency(String inputText) {
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

    //Huffman code generation for each character
    public static HashMap<Character,String> huffmanCodes(Node root){
        HashMap<Character,String> characterCodeMap = new HashMap<>();
        codeGenerator(root," ",characterCodeMap);
        return characterCodeMap;
    }

    //codeGenerator method
    public static void codeGenerator(Node node,String code,HashMap<Character,String> characterCodeMap){
        if(node==null){
            characterCodeMap.put(' ',"");
        }
        if(node.left== null && node.right==null){
            characterCodeMap.put(node.character,code);
        }
        else{
            codeGenerator(node.left,code+"0",characterCodeMap);
            codeGenerator(node.right,code+"1",characterCodeMap);
        }
    }

    //encoding the input text
    public static String encode(String inputString,HashMap<Character,String> characterCodeMap){
        StringBuilder encodedString= new StringBuilder();

        for(char character: inputString.toCharArray()){
            encodedString.append(characterCodeMap.get(character));
        }
        return encodedString.toString();
    }


    //Decoding encoded text
    public static String decode(String encodedString,Node rootNode){
        StringBuilder decodedString = new StringBuilder();
        Node currentNode =rootNode;

        for(char nodeBit:encodedString.toCharArray()){
            if(nodeBit=='0'){
                currentNode=currentNode.left;
            }else if(nodeBit=='1'){
                currentNode=currentNode.right;
            }
            if(currentNode.left== null && currentNode.right ==null){
                decodedString.append(currentNode.character);

                //resetting currentNode to decode next sequence of characters in encoded string
                currentNode=rootNode;
            }
        }
        return decodedString.toString();
    }

    //method to get encoded text
    public static String getEncodedInput(String inputText){
        Node root =huffmanTree(getFrequency(inputText));
        HashMap<Character,String> map=huffmanCodes(root);
        return encode(inputText,map);
    }

    //INTERFACE FOR HUFFMAN CODE

    public static void HuffmanExecution() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean flagToContinue = true;


        //do while loop here
        do {
            System.out.print("Enter text you want encoded: ");
            String textInput = scanner.nextLine();
            String encodedText=HuffmanCodes.getEncodedInput(textInput);
            System.out.println("ENCODED TEXT IS: \n" + encodedText);

            Thread.sleep(1000);

            //ask user if they would like text to be decoded
            System.out.println("Would you like to decode the encoded string? \n Enter yes or no to continue: ");
            String response = scanner.nextLine();
            String responseToContinue;
            if (!response.toLowerCase().equals("yes") && !response.toLowerCase().equals("no")) {


                do {
                    Thread.sleep(1000);
                    System.out.println("INVALID RESPONSE! \n Enter yes or no to continue...");
                    responseToContinue = scanner.nextLine();

                } while (!responseToContinue.toLowerCase().equals("yes") && !responseToContinue.toLowerCase().equals("no"));
                response = responseToContinue;

            }
            if (response.toLowerCase().equals("yes")) {

                //call decoding method
                System.out.println("DECODED TEXT IS: \n" + HuffmanCodes.decode(encodedText,HuffmanCodes.huffmanTree(HuffmanCodes.getFrequency(textInput))));


            }

            Thread.sleep(1000);
            System.out.println("Would you like to ENCODE another string?: ");
            String continueResponse = scanner.nextLine();
            String endResponse;
            if (!continueResponse.toLowerCase().equals("yes") && !continueResponse.toLowerCase().equals("no")) {

                do {
                    Thread.sleep(1000);
                    System.out.println("INVALID RESPONSE! \nEnter yes or no to continue...");
                    endResponse = scanner.nextLine();

                } while (!endResponse.toLowerCase().equals("yes") && !endResponse.toLowerCase().equals("no"));
                continueResponse = endResponse;

            }

            if (continueResponse.toLowerCase().equals("yes")) {
                flagToContinue = true;
            } else {
                flagToContinue = false;
                System.out.println("Thank you for ENCODING!!");
            }


        }while (flagToContinue) ;

    }





}

