import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.LinkedQueue;

public class TweetHist {
    public static void main(String[] args) {
    	    String tweetfile = args[0];
    	    LinkedQueue<String> all_words = new LinkedQueue<String>();
    	    SeparateChainingHashST<String, Integer> unique_words = new SeparateChainingHashST<String, Integer>();
    	    SeparateChainingHashST<String, Integer> unique_address = new SeparateChainingHashST<String, Integer>();
    	    String c;
    	    String http = "http";
    	    /*
    	     * find a way to separate the words from symbols (like # or @)
    	     * could use other st's for them, but would be easier to just sort them.    	    
    	    */
    	    In tweets = new In(tweetfile);
    	    
    	    //this while loop reads in all text and puts them into a linked queue for easier digestion
    	    while (tweets.hasNextLine()) {
    	    	    String string = tweets.readLine();
    	    	    String[] tokens = string.split(",", 16);
    	    	    //Try catch to catch errors caused by bad formatting.
    	    	    try {
    	    	        c = tokens[7]; // need a catch statement in case there is no text data.
    	    	    }
    	    	    //just skips the tweet if it is formatted poorly. Small enough % that it doesn't ruin data set.
    	    	    catch (ArrayIndexOutOfBoundsException e) { 
    	    	    	    continue;
    	    	    }
    	    	    String[] words = c.split(" ");
    	    	    for (String x: words) {
    	    	    	    all_words.enqueue(x);
    	    	    	}
    	    	}
    	    //puts unique words into a hash table if not found. If found, it replaces it and increments the count
    	    for (String x : all_words) {
    	    	    if (x.length() > 4) {
    	    	        String test = x.substring(0, 3);
	    	        if (test.equals(http)) {
	    	        	    System.out.println(test);
	    	    	        if (unique_address.contains(x)) { 
	    	    	        	    System.out.println(x);
	    	    	        	    unique_address.put(x, unique_address.get(x) + 1);}
	    	    	        else { unique_address.put(x, 1);}
	    	    	        //continue; -- I don't think I need this line of code to have it  not put the address also in the words st
	    	        }
    	    	    }
    	    	    //put in here the other ST's -- a check to see what the first few things are -- look  up the sql like command in java
    	    	    if (unique_words.contains(x)) { unique_words.put(x, unique_words.get(x) + 1);}
    	    	    else { unique_words.put(x, 1); }
    	    }
    	    String header = "Unique:     Count";
    	    
    	    //sort(unique_words.keys());
    	    //prints out the words and their values
    	    System.out.printf("%50s", header);
    	    for (String x: unique_words.keys()) {
    	    	    if (unique_words.get(x) < 100) {continue;}
    	    	    //if (unique.get(x) < 100) {continue;}
    	    	    System.out.printf("%50s     ", x);
    	    	    System.out.printf("%d\n", unique_words.get(x));
    	    }
	    /*
	    editing out the header ling to make the data output compatible for R.
    	    String header2 = "Unique Address:     Count";
    	    System.out.printf("%50s", header2);
	    */
    	    for (String y: unique_address.keys()) {
    	    	    //if (unique.get(x) < 100) {continue;}
    	    	    //System.out.println("I am going through the loop"); // not going through the loop
    	    	    System.out.printf("%20s     ", y);
    	    	    System.out.printf("10%d\n", unique_address.get(y));
    	    }   
    }
}
