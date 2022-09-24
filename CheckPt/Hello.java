import java.util.*;;

public class Hello {

    // public inOrder() {

    // }
    // 0 1 2 3
    //[A,B,C,D]
    public static void main(String[] args) {
        List<String> terms = new ArrayList<>();
        terms.add("B");
        terms.add("A");
        terms.add("D");
        terms.add("E");
        System.out.println(inOrder(terms));
    }
    
    public static boolean inOrder(List<String> terms) {
        for (int i = 0 ; i <= terms.size() - 1 ; i++) {
            String prev = terms.get(i);
            String curr = terms.get(i + 1);
            int cmp = prev.compareTo(curr);
            if(cmp > 0) {
                return false;
            }

        }
        return true;
    }
}
