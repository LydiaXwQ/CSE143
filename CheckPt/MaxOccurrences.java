import java.util.*;

public class MaxOccurrences {
    public static int maxOccurrences(List<Integer> list) {
        // TODO: Your code here
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int biggest = 0;
        for(int n : list) {
            if(map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
            if(map.get(n) > biggest){
                biggest = map.get(n);
            }
        }
        return biggest;

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(maxOccurrences(list));
    }
}