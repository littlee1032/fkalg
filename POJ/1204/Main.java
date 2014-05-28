import java.util.Scanner;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

public class Main {
    static class Node {
        public Node fail;
        public Node[] children = new Node[26];
        public int id = -1;

        public Node() {}
    }

    static class MyComparator implements Comparator<int[]> {
        @Override public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            if (a[2] != b[2]) return a[2] - b[2];
            return a[3] - b[3];
        }
    }

    public static final Node root = new Node();
    public static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    public static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    public static List<int[]> ans;
    public static int[] lens;

    public static void insert(String s, int id) {
        char[] cs = s.toCharArray();
        Node p = root;
        for (int i = 0; i < cs.length; i++) {
            int idx = cs[i] - 'A';
            if (p.children[idx] == null) {
                p.children[idx] = new Node();
            }
            p = p.children[idx];
        }
        p.id = id;
    }

    public static void buildAcMachine() {
        Node p = root;
        Queue<Node> q = new LinkedList<Node>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            Node myFail = null;
            for (int i = 0; i < 26; i++) {
                if (tmp.children[i] != null) {
                    if (tmp == root) {
                        tmp.children[i].fail = root;
                    } else {
                        myFail = tmp.fail;
                        while (myFail != null) {
                            if (myFail.children[i] != null) {
                                tmp.children[i].fail = myFail.children[i];
                                break;
                            }
                            myFail = myFail.fail;
                        }
                        if (myFail == null) {
                            tmp.children[i].fail = root;
                        }
                    }
                    q.offer(tmp.children[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int l = scan.nextInt();
        int c = scan.nextInt();
        int w = scan.nextInt();
        scan.nextLine();
        lens = new int[w];

        char[][] map = new char[l][c];
        for (int i = 0; i < l; i++) {
            map[i] = scan.nextLine().toCharArray();
        }
        for (int i = 0; i < w; i++) {
            String str = scan.nextLine();
            lens[i] = str.length();
            insert(str, i);
        }
        buildAcMachine();
        ans = new LinkedList<int[]>();

        for (int col = 0; col < c; col++) {
            acSearch(0, col, 4, map);
            acSearch(0, col, 3, map);
            acSearch(0, col, 5, map);
            acSearch(l - 1, col, 0, map);
            acSearch(l - 1, col, 7, map);
            acSearch(l - 1, col, 1, map);
        }
        for (int row = 0; row < l; row++) {
            acSearch(row, 0, 2, map);
            acSearch(row, 0, 1, map);
            acSearch(row, 0, 3, map);
            acSearch(row, c - 1, 6, map);
            acSearch(row, c - 1, 7, map);
            acSearch(row, c - 1, 5, map);
        }

        Collections.sort(ans, new MyComparator());
        for (int[] one : ans) {
            System.out.println(one[2] + " " + one[3] + " " + (char)('A' + one[1]));
        }
    }

    private static void acSearch(int r, int c, int direct, char[][] map) {
        Node p = root;
        for (int row = r, col = c; row < map.length && col < map[0].length && row >= 0 && col >= 0;) {
            int idx = map[row][col] - 'A';
            while (p.children[idx] == null && p != root) p = p.fail;
            p = p.children[idx];
            if (p == null) p = root;
            for (Node tmp = p; tmp != root && tmp.id >= 0; tmp = tmp.fail) {
                int len = lens[tmp.id];
                int x = row - (len-1) * dx[direct];
                int y = col - (len-1) * dy[direct];
                ans.add(new int[]{tmp.id, direct, x, y});
                tmp.id = -1;
            }
            row += dx[direct];
            col += dy[direct];
        }
    }
}
