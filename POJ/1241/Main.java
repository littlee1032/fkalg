import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static class Node {
        public int highest = 0;
        public int childrenNum = 0;
        public int lowest = 0;
        public List<Node> children = new LinkedList<Node>();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            int num = 1;
            while (n-- > 0) num <<= 1;
            Node[] nodes = new Node[num+1];
            for (int i = 1; i <= num; i++) nodes[i] = new Node();
            for (int i = 1; i <= num; i++) nodes[i].lowest = num;
            int[] round = new int[num + 1];
            for (int i = 1; i <= num; i++) round[i] = i;
            for (int i = num / 2; i > 0; i /= 2) {
                for (int j = 1; j <= i; j++) {
                    int winner = scan.nextInt();
                    int left = j * 2 - 1;
                    int right = j * 2;
                    int looser = winner == round[left] ? round[right] : round[left];
                    round[j] = winner;
                    Node lNode = nodes[looser];
                    Node wNode = nodes[winner];
                    wNode.children.add(lNode);
                    wNode.childrenNum++;
                }
            }
            Node root = nodes[round[1]];
            root.highest = 1;
            bfs(root);
            dfs(root);

            int m = scan.nextInt();
            for (int i = 0; i < m; i++) {
                int idx = scan.nextInt();
                printNode(nodes[idx], idx);
            }
            System.out.println();
            n = scan.nextInt();
        }
    }

    private static void printNode(Node n, int idx) {
        System.out.println(String.format("Player %d can be ranked as high as %d or as low as %d.", idx, n.highest, n.lowest));
    }

    private static void bfs(Node n) {
        for (Node child : n.children) {
            child.highest = n.highest + 1;
            bfs(child);
        }
    }

    private static void dfs(Node n) {
        for (Node child : n.children) {
            dfs(child);
            n.childrenNum += child.childrenNum;
        }
        n.lowest -= n.childrenNum;
    }
}
