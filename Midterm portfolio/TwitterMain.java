import java.util.*;

public class TwitterMain {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        printFeed(twitter, "Kevin");

        twitter.post("Kevin", "CSE 143 woo!");
        printFeed(twitter, "Kevin");

        twitter.follow("Kevin", "Taylor");
        twitter.post("Taylor", "Hi Kevin");
        printFeed(twitter, "Kevin");

        twitter.unfollow("Kevin", "Taylor");
        printFeed(twitter, "Kevin");
    }

    public static void printFeed(Twitter twitter, String user) {
        System.out.println(user + "'s feed:");
        List<String> feed = new ArrayList<String>(twitter.getFeed(user));
        Collections.sort(feed);
        for (String message : feed) {
            System.out.println(message);
        }
        System.out.println();
    }
}