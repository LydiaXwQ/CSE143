
/**
 * Lydia Xue
 * 21/4/2022
 * CSE 143 Section AB
 * TA: Duncan Du
 * Take-home Assessment #2
 * The MusicManager class represents a user's music playlist
 * it allows user to preview, remove and see their current and removed songs
 * in the order of most recently removed.
 */

import java.util.*;

public class MusicManager {
    
    //field
    private MusicNode currFront;
    private MusicNode removFront;

    /**
     * constructs a music playlist according to the given list of songs(can't be empty) 
     * The playlist will have the same order as the songs show up in the list.
     * @param songs - pass the list of songs to the Music Manager class
     * pre: songs passed in must be non-empty and non-duplicated
     *      (throws IllegalArgumentException if the songs list is empty)
     */
    public MusicManager(List<String> songs) {

        if(songs.isEmpty()) {
            throw new IllegalArgumentException();
        }

        currFront = new MusicNode(songs.get(0));
        MusicNode temp = currFront;

        for(int i = 1; i < songs.size(); i++) {
            temp.next = new MusicNode(songs.get(i));
            temp = temp.next;
        }
    }

    /**
     * print the songs in the current playlist with every line indented
     * by 4 spaces and in an order according to the original list.
     */
    public void printCurrent() {
        print(currFront);
    }

    /**
     * check if the current playlist has the given song(case-insensitive)
     * @param song pass a song into the currentContains method
     * @return true if current playlist has the given song
     * Otherwise, return false
     */
    public boolean currentContains(String song) {
        return contains(currFront, song);
    }

    /**
     * remove the given song (case-insensitive) from the current playlist, and store it in
     * the removed list in an order of recently removed.
     * @param song pass a song into the remove method
     * pre: the current playlist can't be empty and must have at least one
     *      song to remove.
     *      (throws IllegalStateException if not)
     * 
     *      the given song must be a song already exist in the current playlist
     *      (throws IllegalArgumentException if not)
     * 
     *      (throws IllegalStateException if both cases appear)
     * 
     * post: remove the song from the current list and add it into the removed list
     */
    public void remove(String song) {

        if(! hasSongs()) {
            throw new IllegalStateException();
        }

        if(! currentContains(song)) {
            throw new IllegalArgumentException();
        }

        MusicNode temp = currFront;
        MusicNode temp2 = currFront;

        if(currFront.song.equalsIgnoreCase(song)) {
            currFront = temp.next;
        } else {
            while(!temp.next.song.equalsIgnoreCase(song)) {
                temp = temp.next;
            }
            temp2 = temp.next;
            temp.next = temp.next.next;
            
        }
        temp2.next = removFront;
        removFront = temp2;
    }
    
    /**
     * check if there are songs left in the current playlist
     * @return true if there's at least one song left in the current playlist
     * Otherwise, return false
     */
    public boolean hasSongs() {
        return currFront != null;
    }

    /**
     * gives the next song in the current playlist
     * @return the next song in the current playlist if the current playlist
     * is not empty. Otherwise, return null.
     */
    public String nextSong() {
        if(hasSongs()) {
            return currFront.song;
        } 
        return null;
    }

    /**
     * print out the removed songs in an order of recently removed with
     * every line indented by 4 spaces.
     */
    public void printRemoved() {
        print(removFront);
    }

    /**
     * check if the given song (case-insensitive) is in the removed list
     * @param song - pass a song into the removedContains method
     * @return true if the given song is in the removed list
     * Otherwise, return false
     */
    public boolean removedContains(String song) {
        return contains(removFront, song);
        
    }

    /**
     * a helper method for reducing the redundancy in currentContains and 
     * removedContains method by generalizing the "contain procedure" 
     * with only difference in which front node needs to be evaluated 
     * (currFront/removFront)
     * @param front pass in a front indicates which list needs to be evaluated
     * @param song pass in the song client gives to the method
     * @return true if the given song (case-insensitive) is in the (current/removed) list
     * Otherwise, return false
     */
    private boolean contains(MusicNode front, String song) {
        MusicNode temp = front;
        while(temp != null) {

            if(temp.song.equalsIgnoreCase(song)) {
                return true;
            } 
            temp = temp.next;
        }
        return false;
    }

    /**
     * a helper method for reducing redundancy in printCurrent and printRemoved
     * method by generalizing the " printing procedure" with only difference in 
     * which front node needs to be evaluated (currFront/removFront)
     * @param front pass in a front indicates which list needs to be evaluated
     */
    private void print(MusicNode front) {
        MusicNode temp = front;

        while(temp != null) {
            System.out.println("    " + temp.song);
            temp = temp.next; 
        }
    }
}