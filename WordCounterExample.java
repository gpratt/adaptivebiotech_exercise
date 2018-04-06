/**
 * Created by gpratt on 4/3/18.
 * Testing code
 */
import WORDCOUNTER.WORDCOUNTER;

import java.util.*;

public class WordCounterExample {
    public static void main(String[] args) {
        // Prints "Hello, World" in the terminal window.
        WORDCOUNTER foo = new WORDCOUNTER();
        List<String> myList = Arrays.asList("hub","032018_atxn2_mm9");
        System.out.println(foo.getKeywordCount(myList, "https://s3-us-west-1.amazonaws.com/sauron-yeo/032018_atxn2_mm9/hub.txt")); //should print 5
        try {
            Thread.sleep(1000);
            System.out.println("Waiting");
        } catch (InterruptedException e) {
            System.out.println("Exception (shouldn't happen)");
        }
        System.out.println(foo.getKeywordCount(myList, "https://s3-us-west-1.amazonaws.com/sauron-yeo/032018_atxn2_mm9/hub.txt")); // should print 5

	myList = Arrays.asList("hub","032018_atxn2_mm9", "email");
	System.out.println(foo.getKeywordCount(myList, "https://s3-us-west-1.amazonaws.com/sauron-yeo/032018_atxn2_mm9/hub.txt"));  // should print 6
	System.out.println(foo.getKeywordCount(myList, "")); //should print 0 
	System.out.println(foo.getKeywordCount(myList, null)); //should print 0
    }
}
