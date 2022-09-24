import java.util.*;
public class MusicClient {
    public static void main(String[] args) {
        List<String> songs = new ArrayList<>();
        // songs.add("A");
        // songs.add("B");
        songs.add("C");
        songs.add("D");
        MusicManager manager = new MusicManager(songs);
        //manager.printCurrent();
        //System.out.println(manager.currentContains("d"));
        //  manager.remove("a");
         manager.remove("c");
        // manager.remove("c");
         manager.remove("d");
         manager.remove("a");

        
        manager.printRemoved();
        System.out.println(manager.nextSong());
       
        manager.printCurrent();
         System.out.println(manager.removedContains("a"));
         System.out.println(manager.removedContains("c"));
        
    }
}
