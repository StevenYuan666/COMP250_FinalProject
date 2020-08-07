
import java.util.*;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:46","@USER_6921e61d I can be made into one twitter superstar."));
        tweets.add(new Tweet("USER_a75657c2","2010-03-03 00:02:54","@USER_13e8a102 They reached a compromise just on time"));
        tweets.add(new Tweet("USER_989b85bb","2010-03-04 15:34:47","I can be MADE into a need."));
        tweets.add(new Tweet("USER_a75657c2","2010-03-07 21:45:48","So SunChips made a bag that is 100% biodegradeable. It is about damn time somebody did."));
        tweets.add(new Tweet("USER_ee551c6c","2010-03-07 15:40:27","drthema: Do something today that feeds your spirit and empowers you to start the week from a higher place."));
        tweets.add(new Tweet("USER_6c78461b","2010-03-03 05:13:34","@USER_a3d59856 yes, i watched that foolery done disturbed my spirit. @USER_b1d28f26"));
        tweets.add(new Tweet("USER_92b2293c","2010-03-04 14:00:11","@USER_5aac9e88: Let no one push u around today! Be at Peace! If u dont have restful spirit, u'll definitely have a stressful spirit"));
        tweets.add(new Tweet("USER_75c62ed9","2010-03-07 03:35:38","@USER_cb237f7f Congrats on everything I am there in spirit my brother."));
        tweets.add(new Tweet("USER_7f72a368","2010-03-07 07:18:22","Actions speak louder than words but feelings and spirits speak louder than anything #FACT"));
        tweets.add(new Tweet("USER_b6cc1831","2010-03-07 04:04:37","@USER_be777094 urban spirit cafe. On Long st"));
        tweets.add(new Tweet("USER_65006b55","2010-03-05 00:58:28","RT @USER_86e8d97f: @USER_65006b55's spirit just took a turn for the worst. Lol please."));
        tweets.add(new Tweet("USER_60b9991b","2010-03-04 22:33:23","Who on my time ever flew on spirit airlines let me kno if there decent"));
        tweets.add(new Tweet("USER_36607a99","2010-03-03 02:06:01","@USER_561fe280: Nourish your spirit with your own achievement."));
        tweets.add(new Tweet("USER_9506fb5f","2010-03-04 01:16:34","Great spirits have often encountered violent opposition from weak minds"));
        tweets.add(new Tweet("USER_d3ca457f","2010-03-03 04:53:06","RT @USER_6d6bfb4d: The things that make a woman beautiful are her character, intellect, and spirituality."));
        tweets.add(new Tweet("USER_14f78255","2010-03-03 17:07:45","@USER_9afbc367 Oh in spirit. That's all that matters lol"));
        tweets.add(new Tweet("USER_3dfae4fe","2010-03-05 00:44:33","time for a spiritual cleansing of my facebook friend list"));
        tweets.add(new Tweet("USER_bd852fb7","2010-03-03 14:19:51","RT @USER_24bd1961:God's spirit is like a Radio station, broadcasting all the time. You just have to learn how to tune in and receive his signal"));
        tweets.add(new Tweet("USER_136c16da","2010-03-07 19:56:54","RT @USER_11d35e61: @USER_136c16da finally a kindred spirit. *daps* lol thanks"));
        tweets.add(new Tweet("USER_47063e51","2010-03-04 12:47:54","cathartic - noun - a purification or purgation that brings about spiritual renewal or release from tension"));
        tweets.add(new Tweet("USER_1e4eb302","2010-03-03 20:13:18","Anything worth having you have to contribute yourself heart, mind, soul and spirit to. It is so rewarding. Have u contributed lately?"));
        tweets.add(new Tweet("USER_5d246e83","2010-03-04 14:57:01","@USER_8e090edb That's always good to hear. Starting off to a good morning, always puts your spirit in a great place."));
        tweets.add(new Tweet("USER_b7117680","2010-03-03 06:55:17","I got a hustlas spirit, period!"));
        tweets.add(new Tweet("USER_25ecff25","2010-03-05 17:33:20","RT @USER_3a117437: The woman at the rental car spot tried 2 give us a Toyota! No ma'am lk the old spiritual says \"aint got time 2 die!\""));   
        tweets.add(new Tweet("USER_f91d8165","2010-03-03 22:33:24","#RandomThought why do people grab guns or knives when they think theres a ghost? DUMBASS! You can't shoot a spirit, grab some holy water! duh"));
        tweets.add(new Tweet("USER_86c542b8","2010-03-04 02:52:06","@USER_8cd1512d haha, maybe your right. I use to watch gymnastics all the time. I love the olympics. That's why I have so much spirit lol"));
        ArrayList<String> stopWords = new ArrayList<String>();
        
        stopWords.add("i");
        stopWords.add("YOU");
        stopWords.add("mE");
        stopWords.add("am");
        stopWords.add("this");
        stopWords.add("That");
        stopWords.add("is");
        stopWords.add("an");
        stopWords.add("a");
        stopWords.add("are");
       
        
        Twitter test = new Twitter(tweets, stopWords);
        
        ArrayList<String> trending = test.trendingTopics();
        //System.out.println(trending);
		/*
		 
		Integer a = 1;
		Integer b = 1111;
		System.out.println(a.compareTo(b));
		
		Tweet t1 = new Tweet("YY","2020-12-05 12:12:12","666");
		Tweet t2 = new Tweet("DZX","2020-02-07 11:11:11","777");
		System.out.println(t1.compareTo(t2));
		

		MyHashTable<String, String> t = new MyHashTable<>(3);
		t.put("20", "DZX");
		System.out.println(t.getBuckets().toString());
		System.out.println(t.size());
		t.put("19", "YY");
		
		System.out.println(t.getBuckets().toString());
		System.out.println(t.size());
		
		
		
		
		System.out.println(t.values());
		* 
		/
		
		
		/*
		 * test for put and rehash
		 
		t.put("18","Freddy" );
		t.put("17","A" );
		t.put("16","B" );
		t.put("15","C" );
		t.put("14","D" );
		t.put("13","E" );
		t.put("12","F" );
		System.out.println(t.getBuckets().toString());
		System.out.println(t.size());
		t.rehash();
		System.out.println(t.getBuckets().toString());
		System.out.println(t.size());
		
		Hashtable<String, String> a = new Hashtable<>(8);
		a.put("20", "DZX");
		a.put("19", "YY");
		a.put("18","Freddy" );
		a.put("17","A" );
		a.put("16","B" );
		a.put("15","C" );
		a.put("14","D" );
		a.put("13","E" );
		a.put("12","F" );
		System.out.println(a.toString());
		System.out.println(a.size());
		*/
	}

}
