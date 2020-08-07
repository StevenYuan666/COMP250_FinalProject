import java.util.ArrayList;

public class Twitter {
	
	//ADD YOUR CODE BELOW HERE
	
	private MyHashTable<String, ArrayList<Tweet>> authorTable;
	private MyHashTable<String, ArrayList<Tweet>> dateTable;
	private ArrayList<Tweet> allTweets;
	private ArrayList<String> allStopWords;
	
	//ADD CODE ABOVE HERE 
	
	// O(n+m) where n is the number of tweets, and m the number of stopWords
	public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {
		//ADD YOUR CODE BELOW HERE
		this.allStopWords = new ArrayList<String>();
		
		int size = tweets.size();
		for(int i = 0; i < stopWords.size(); i++) {
			this.allStopWords.add(stopWords.get(i).toLowerCase());
		}
		MyHashTable<String, ArrayList<Tweet>> author = new MyHashTable<String, ArrayList<Tweet>>(size);
		MyHashTable<String, ArrayList<Tweet>> date = new MyHashTable<String, ArrayList<Tweet>>(size);
		ArrayList<Tweet> all = new ArrayList<Tweet>();
		this.allTweets = all;
		for(int i = 0; i < size; i++) {
			if(author.get(tweets.get(i).getAuthor()) == null) {
				ArrayList<Tweet> toAdd = new ArrayList<Tweet>();
				toAdd.add(tweets.get(i));
				author.put(tweets.get(i).getAuthor(), toAdd);
			}
			else {
				author.get(tweets.get(i).getAuthor()).add(tweets.get(i));
			}
			if(date.get(tweets.get(i).getDateAndTime().substring(0, 10)) == null) {
				ArrayList<Tweet> toAdd = new ArrayList<Tweet>();
				toAdd.add(tweets.get(i));
				date.put(tweets.get(i).getDateAndTime().substring(0, 10), toAdd);
			}
			else {
				date.get(tweets.get(i).getDateAndTime().substring(0, 10)).add(tweets.get(i));
			}
			this.allTweets.add(new Tweet(tweets.get(i).getAuthor(),tweets.get(i).getDateAndTime(),tweets.get(i).getMessage()));
		}
		this.authorTable = author;
		this.dateTable = date;
		
		//ADD CODE ABOVE HERE 
	}
	
	
    /**
     * Add Tweet t to this Twitter
     * O(1)
     */
	public void addTweet(Tweet t) {
		//ADD CODE BELOW HERE
		this.allTweets.add(t);
		
		if(this.authorTable.get(t.getAuthor()) == null) {
			ArrayList<Tweet> toAdd = new ArrayList<Tweet>();
			toAdd.add(t);
			this.authorTable.put(t.getAuthor(), toAdd);
		}
		else {
			this.authorTable.get(t.getAuthor()).add(t);
		}
		
		if(this.dateTable.get(t.getDateAndTime()) == null) {
			ArrayList<Tweet> toAdd = new ArrayList<Tweet>();
			toAdd.add(t);
			this.dateTable.put(t.getDateAndTime(), toAdd);
		}
		else {
			this.dateTable.get(t.getDateAndTime()).add(t);
		}
		
		//ADD CODE ABOVE HERE 
	}
	

    /**
     * Search this Twitter for the latest Tweet of a given author.
     * If there are no tweets from the given author, then the 
     * method returns null. 
     * O(1)  
     */
    public Tweet latestTweetByAuthor(String author) {
        //ADD CODE BELOW HERE
    	
    	if(this.authorTable.get(author) != null) {
    		ArrayList<Tweet> targetList = this.authorTable.get(author);
    		Tweet latest = targetList.get(0);
    		for(int i = 1; i < targetList.size(); i ++) {
    			if(targetList.get(i).compareTo(latest) > 0) {
    				latest = targetList.get(i);
    			}
    		}
    		return latest;
    	}
    	else {
    		return null;
    	}
        //ADD CODE ABOVE HERE 
    }


    /**
     * Search this Twitter for Tweets by `date' and return an 
     * ArrayList of all such Tweets. If there are no tweets on 
     * the given date, then the method returns null.
     * O(1)
     */
    public ArrayList<Tweet> tweetsByDate(String date) {
        //ADD CODE BELOW HERE
    	
    	if(this.dateTable.get(date) != null) {
    		ArrayList<Tweet> result = this.dateTable.get(date);
    		return result;
    	}
    	else {
    		return null;
    	}
    	
        //ADD CODE ABOVE HERE
    }
    
	/**
	 * Returns an ArrayList of words (that are not stop words!) that
	 * appear in the tweets. The words should be ordered from most 
	 * frequent to least frequent by counting in how many tweet messages
	 * the words appear. Note that if a word appears more than once
	 * in the same tweet, it should be counted only once. 
	 */
    public ArrayList<String> trendingTopics() {
        //ADD CODE BELOW HERE
    	
    	//get all words in all of tweets
    	ArrayList<String> allWords = new ArrayList<String>();
    	for(int i = 0; i < this.allTweets.size(); i++) {
    		ArrayList<String> temp = getWords(this.allTweets.get(i).getMessage());
    		ArrayList<String> toAdd = new ArrayList<String>();
    		for(int j = 0; j < temp.size(); j++) {
    			String s = temp.get(j).toLowerCase();
    			if(!toAdd.contains(s) && !this.allStopWords.contains(s)) {
    				toAdd.add(s);
    			}
    		}
    		allWords.addAll(toAdd);
    	}
    	
    	//create a new MyHashTable use each unique words as key
    	MyHashTable<String, Integer> result = new MyHashTable<String, Integer>(10);
    	for(int j = 0; j < allWords.size(); j++) {
    		if(result.get(allWords.get(j)) == null) {
    			result.put(allWords.get(j), 1);
    		}
    		else {
    			Integer newCount = result.get(allWords.get(j)) + 1;
    			result.put(allWords.get(j), newCount);
    		}
    	}
    	ArrayList<String> sorted = MyHashTable.fastSort(result);
    	
    	return sorted;
        //ADD CODE ABOVE HERE    	
    }
    
    
    
    /**
     * An helper method you can use to obtain an ArrayList of words from a 
     * String, separating them based on apostrophes and space characters. 
     * All character that are not letters from the English alphabet are ignored. 
     */
    private static ArrayList<String> getWords(String msg) {
    	msg = msg.replace('\'', ' ');
    	String[] words = msg.split(" ");
    	ArrayList<String> wordsList = new ArrayList<String>(words.length);
    	for (int i=0; i<words.length; i++) {
    		String w = "";
    		for (int j=0; j< words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
    				w += c;
    			
    		}
    		wordsList.add(w);
    	}
    	return wordsList;
    }

    

}
