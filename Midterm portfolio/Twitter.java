import java.util.*;

public class Twitter {
    private Map<String, Set<String>> followList;
    private Map<String, Set<String>> posts;

    // post: constructs a new Twitter object
    public Twitter() {
        followList = new HashMap<>();
        posts = new HashMap<>();
    }

    // post: composes a new tweet with the message written by the user.
    public void post(String user, String message) {
        if(!posts.containsKey(user)) {
            posts.put(user, new HashSet<>());  
        }
        posts.get(user).add(message);
        
    }

    // pre : the follower is not the same as the followee
    // post: the follower begins following the followee
    public void follow(String follower, String followee) {
        if(!follower.equals(followee)) {
            if(! followList.containsKey(follower)) {
                followList.put(follower, new HashSet<>());
            }
            followList.get(follower).add(followee);
        }
    }

    // pre : the follower is following the followee
    //       the follower is not the same as the followee
    // post: the follower no longer follows the followee
    public void unfollow(String follower, String followee) {
        if(followList.get(follower).contains(followee) && !follower.equals(followee)) {
            followList.get(follower).remove(followee);
        }
    }

    // post: returns all messages by the user and their followees in any order
    public List<String> getFeed(String user) {
        List<String> temp = new ArrayList<>();
        if(posts.containsKey(user)) {
            //user's message
            //temp.add(posts.get(user));
            printMessage(user, temp);
            //followee's message
            //Kevin's followees(Taylor) 
            if(!followList.values().isEmpty()) {
                for(String person : followList.get(user)) {
                    printMessage(person, temp);
                }
            }
            
        }
        return temp;
    }

    private void printMessage(String user, List<String> temp) {
        for(String userMessage : posts.get(user)) {
            temp.add(userMessage);
        }
    }
}
