import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main{
    private static final Node trie = new Node(-1, 0);
    private static final List<Node> nodes = new LinkedList<Node>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int n = scan.nextInt();
        scan.nextLine();
        String tmp = scan.nextLine();
        char[] codes = tmp.substring(0, tmp.length()-1).toCharArray();
        for (int i = 0; i + b < codes.length; i++) {
            insert(codes, i, i+b);
        }
        for (int j = b; j > 0; j--) {
            insert(codes, codes.length-j, codes.length);
        }
        Collections.sort(nodes, new MyComparator());
        int preFreq = 0;
        int counter = 0;
        for (Node node : nodes) {
            if (node.len < a) continue;
            String present = new String(codes, node.start, node.len);
            if (node.freq == preFreq) {
                System.out.print(" " + present);
            } else {
                if (preFreq != 0) System.out.println();
                counter++;
                if (counter > n) break;
                preFreq = node.freq;
                System.out.print(node.freq + " " + present);
            }
        }
    }

    private static class Node {
        final int start;
        final int len;
        int freq;
        int id = 1;
        Node left;
        Node right;

        @Override public String toString() {
            return freq + " " + start + " " + len + " " + id;
        }

        public Node(int start, int len) {
            this.start = start;
            this.len = len;
        }
    }

    private static class MyComparator implements Comparator<Node> {
        @Override public int compare(Node a, Node b) {
            if (a.freq != b.freq) return b.freq - a.freq;
            if (a.len != b.len) return b.len - a.len;
            return b.id - a.id;
        }
    }

    private static void insert(char[] arr, int start, int end) {
        Node node = trie;
        Node p;
        for (int i = start;i < end; i++) {
            p = node;
            boolean left = '0' == arr[i];
            node = left ? node.left : node.right;
            if (node == null) {
                node = new Node(start, i-start+1);
                nodes.add(node);
                if (left) {p.left = node; node.id = p.id * 2;}
                else {p.right = node; node.id = p.id * 2 + 1;}
            }
            node.freq++;
        }
    }
}
