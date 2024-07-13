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
}
