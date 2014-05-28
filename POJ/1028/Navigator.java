import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Navigator {
    private String currentUrl;
    private final Deque<String> backStack = new LinkedList<String>();
    private final Deque<String> forwardStack = new LinkedList<String>();

    public Navigator(String initUrl) {
        currentUrl = initUrl;
    }

    public String exec(String cmd, String arg) {
        if ("BACK".equals(cmd)) {
            if (backStack.isEmpty()) {
                return "Ignored";
            } else {
                forwardStack.push(currentUrl);
                currentUrl = backStack.pop();
            }
        } else if ("FORWARD".equals(cmd)) {
            if (forwardStack.isEmpty()) {
                return "Ignored";
            } else {
                backStack.push(currentUrl);
                currentUrl = forwardStack.pop();
            }
        } else if ("VISIT".equals(cmd)) {
            backStack.push(currentUrl);
            forwardStack.clear();
            currentUrl = arg;
        } else {
            // QUIT
        }
        return currentUrl;
    }

    public static void main(String[] args) {
        Navigator nav = new Navigator("http://www.acm.org/");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        while (!"QUIT".equals(line)) {
            String[] tmp = line.split(" ");
            if ("VISIT".equals(tmp[0])) {
                System.out.println(nav.exec(tmp[0], tmp[1]));
            } else {
                System.out.println(nav.exec(tmp[0], null));
            }
            line = scan.nextLine();
        }
    }
}
