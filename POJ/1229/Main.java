import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    enum Type {
        ONE,
        ZERO_ONE,
        ANY,
        STR,
        END
    };

    static class Node {
        public final Type type;
        public final String val;

        public Node(Type type, String val) {
            this.type = type;
            this.val = val;
        }

        public String toString() {
            return this.type + " " + val;
        }
    }

    private static List<Node> genList(String s) {
        String[] domains = s.split("\\.");
        //System.out.println(Arrays.toString(domains));
        List<Node> ret = new ArrayList<Node>();
        for (String domain : domains) {
            if ("*".equals(domain)) {
                ret.add(new Node(Type.ONE, "ONE"));
                ret.add(new Node(Type.ANY, "ANY"));
            } else if ("?".equals(domain)) {
                ret.add(new Node(Type.ONE, "ONE"));
                ret.add(new Node(Type.ZERO_ONE, "ZERO_ONE"));
                ret.add(new Node(Type.ZERO_ONE, "ZERO_ONE"));
            } else if ("!".equals(domain)) {
                ret.add(new Node(Type.ONE, "ONE"));
                ret.add(new Node(Type.ONE, "ONE"));
                ret.add(new Node(Type.ONE, "ONE"));
                ret.add(new Node(Type.ANY, "ANY"));
            } else {
                ret.add(new Node(Type.STR, domain));
            }
        }
        ret.add(new Node(Type.END, "END"));
        return ret;
    }

    private static boolean[][] dp;
    private static boolean[][] vis;
    private static List<Node> list1;
    private static List<Node> list2;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String str1 = scan.nextLine();
            String str2 = scan.nextLine();
            //System.out.println(str1);
            //System.out.println(str2);
            solve(str1, str2);
        }
    }

    private static void solve(String str1, String str2) {
        list1 = genList(str1);
        list2 = genList(str2);
        int s1 = list1.size() + 1;
        int s2 = list2.size() + 1;


        for (Node n : list1) {System.out.print(n.val + " ");}
        System.out.println();
        for (Node n : list2) {System.out.print(n.val + " ");}
        System.out.println();

        dp = new boolean[s1][s2];
        vis = new boolean[s1][s2];

        if (dfs(0, 0)) System.out.println("YES");
        else System.out.println("NO");
    }

    private static boolean dfs(int idx1, int idx2) {
        if (vis[idx1][idx2]) return dp[idx1][idx2];
        int size1 = list1.size();
        int size2 = list2.size();
        if (idx1 >= size1 && idx2 < size2) return false;
        if (idx1 < size1 && idx2 >= size2) return false;

        Node n1 = list1.get(idx1);
        Node n2 = list2.get(idx2);

        boolean result = false;

        if (n1.type == Type.END && n2.type == Type.END) {
            result = true;
        } else if (n1.type == Type.STR && n2.type == Type.STR) {
            result = n1.val.equals(n2.val) && dfs(idx1+1, idx2+1);
        } else if (n1.type == Type.STR && n2.type != Type.STR) {
            result = false;
        } else if ((n1.type == Type.END && n2.type != Type.END) ||
                   (n1.type != Type.END && n2.type == Type.END)) {
            result = false;
        } else {
            result = dfs(idx1+1, idx2+1);
            if (n1.type == Type.ZERO_ONE || n1.type == Type.ANY) {
                result |= dfs(idx1, idx2 + 1);
            }
            if (n2.type == Type.ZERO_ONE || n2.type == Type.ANY) {
                result |= dfs(idx1+1, idx2);
            }
        }
        dp[idx1][idx2] = result;
        vis[idx1][idx2] = true;
        //System.out.println(idx1 + " " + idx2 + ": " + dp[idx1][idx2]);
        return result;
    }
}
