public class Browser {
    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory("uw.edu");
        // You are on "uw.edu". Visit "my.uw.edu"...
        history.visit("my.uw.edu");
        // You are on "my.uw.edu". Visit "cs.uw.edu"...
        history.visit("cs.uw.edu");
        // You are on "cs.uw.edu". Visit "canvas.uw.edu"...
        history.visit("canvas.uw.edu");
        printStatus(history);

        // You are on "canvas.uw.edu". Move back to "cs.uw.edu"...
        history.back();
        // You are on "cs.uw.edu". Move back to "my.uw.edu"...
        history.back();
        // You are on "my.uw.edu". Move forward to "cs.uw.edu"...
        history.forward();
        // You are on "cs.uw.edu". Visit "notify.uw.edu", clearing the forward history...
        history.visit("notify.uw.edu");
        // You are on "notify.uw.edu". We just cleared the forward history, so moving forward has no effect...
        history.forward();
        printStatus(history);

        // You are on "notify.uw.edu". Move back 2 steps to "cs.uw.edu" then to "my.uw.edu"...
        for (int i = 0; i < 2; i += 1) {
            history.back();
        }
        printStatus(history);

        // You are on "my.uw.edu". Attempt to move back 7 steps, but there's only "uw.edu" in the history...
        for (int i = 0; i < 7; i += 1) {
            history.back();
        }
        printStatus(history);
    }

    public static void printStatus(BrowserHistory history) {
        System.out.println("Current page: " + history.current());
        System.out.println("Full history: " + history.pages());
        System.out.println();
    }
}
