import java.util.*;

public class BrowserHistory {
    private String popUp;
    private Stack<String> backward; //store very recent at the top
    private Stack<String> forward; // latest at the top
    

    // post: constructs a new BrowserHistory object with the given url
    public BrowserHistory(String url) {
        backward = new Stack<String>();
        forward = new Stack<String>();
        backward.push(url);
    }

    // post: visits the given url, clearing any forward history
    public void visit(String url) {
        backward.push(url);

        while(! forward.isEmpty()) {
            forward.pop();
        }
    }

    // post: moves the history back 1 page and returns the current url
    public String back() {
        String view = "";
        if(!backward.isEmpty()) {
            popUp = backward.pop();
            forward.push(popUp);
        }
        return current();
    }

    // post: moves the history forward 1 page and returns the current url
    public String forward() {
        String view = "";
        if(!forward.isEmpty()) {
            popUp = forward.pop();
            backward.push(popUp);
        }
        return current();
    }

    // post: returns the current page url
    public String current() {
        String view = "";
        if(!backward.isEmpty()) {
            view = backward.peek();
        } else {
            view = forward.peek();
        }
        return view;
    }

    // post: returns all back and forward urls in the order they were visited
    public List<String> pages() {
        List<String> historyPages = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        int backCounts = 0;
        int forwardCounts = 0;
        Stack<String> backwardCopy = (Stack<String>) backward.clone();
        Stack<String> forwardCopy = (Stack<String>) forward.clone();

        while(!backwardCopy.isEmpty()) {
            q.add(backwardCopy.pop());
        }

        while(!q.isEmpty()) {
            backwardCopy.push(q.remove());
        }
        
        while(!backwardCopy.isEmpty()) {
            historyPages.add(backwardCopy.pop());
            backCounts++;
        }

        while(!forwardCopy.isEmpty()) {
            historyPages.add(forwardCopy.pop());
            forwardCounts++;
        }

        return historyPages;
    }


    // Visited: [A,B,C,D]
    // backward: [A,B]
    // back -> Queue -> back, 现在back: [B, A]
    // Queue:空
    // forward: [D,C]
    // List -> back.pop(), add queue.add(back.pop), back.pop, queue.add(pop),
    // List: [A, B, C, D]
    // 

    // Q: [A,B,C,D]
    // back: backward.push(queue.remove),backward.push(queue.remove)
    // back stack: [A,B]
    // forward stack: [C,D]
    // pop2次到queue[D,C]
    
}