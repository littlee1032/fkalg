import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static int[] bias = new int[]{100000, 10000, 1000, 100, 10, 1};
    private static Map<Integer, Node> cache = new HashMap<Integer, Node>();
    private static int MAX_DIGIT = 0;
    private static int MIN_DIGIT = 10;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int s = scan.nextInt();
        int e = scan.nextInt();

        int[] eNumbers = getNums(e * 10);
        Set<Integer> ends = new HashSet<Integer>();
        for (int i = 1; i <= 6; i++) ends.add(e * 10 + i);
        for (int i = 1; i <= 6; i++) {
            MAX_DIGIT = Math.max(MAX_DIGIT, eNumbers[i]);
            MIN_DIGIT = Math.min(MIN_DIGIT, eNumbers[i]);
        }

        PriorityQueue<Node> pq = new PriorityQueue<Node>(10, new MyComparator());
        Set<Integer> vis = new HashSet<Integer>();
        Node startNode = new Node();
        startNode.numbers = getNums(s*10 + 1);
        cache.put(s*10+1, startNode);
        pq.offer(startNode);
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int mNumber = genNumber(node.numbers);
            if (ends.contains(mNumber)) {
                System.out.println(node.cost);
                //print(node, startNode);
                break;
            }
            if (vis.contains(mNumber)) continue;
            List<Node> neighbors = getNeighbors(node);
            for (Node nei : neighbors) {
                int mh = h(nei.numbers, eNumbers, nei.numbers[0]);
                int mn = genNumber(nei.numbers);
                if (vis.contains(mn)) continue;

                if (!cache.containsKey(mn)) {
                    cache.put(mn, nei);
                } else
                if (pq.contains(nei)) {
                    if (node.cost + mh + 1 < nei.cost + nei.h) {
                        pq.remove(nei);
                    } else
                        continue;
                }
                nei.cost = node.cost + 1;
                nei.h = mh;
                nei.parent = node;
                pq.offer(nei);
            }
            vis.add(mNumber);
        }
    }

    private static void print(Node node, Node start) {
        //System.out.println("Printing " + Arrays.toString(node.numbers));
        //System.out.println("Parenting " + Arrays.toString(node.parent.numbers));
        if (node != start) print(node.parent, start);
        System.out.println(Arrays.toString(node.numbers));
    }

    private static class MyComparator implements Comparator<Node> {
        @Override public int compare(Node a, Node b) {
            return a.cost + a.h - b.cost - b.h;
        }
    }

    private static int genNumber(int[] numbers) {
        int ret = 0;
        for (int i = 1; i <= 6; i++) {
            ret += numbers[i];
            ret *= 10;
        }
        ret += numbers[0];
        return ret;
    }

    private static class Node {
        public int cost;
        public int h;
        public int[] numbers;
        public Node parent;

        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node n = (Node)o;
            for (int i = 0; i <= 6; i++)
                if (numbers[i] != n.numbers[i]) return false;
            return true;
        }
    }

    private static int h(int[] n, int[] e, int pos) {
        int ret = 0;

        for (int i = 1; i <= 6; i++) {
            if (i != pos && n[i] != e[i]) ret++;
        }

        int[] nn = new int[7];
        int[] ne = new int[7];
        System.arraycopy(n, 0, nn, 0, 7);
        System.arraycopy(e, 0, ne, 0, 7);
        Arrays.sort(nn, 1, 7);
        Arrays.sort(ne, 1, 7);
        for (int i = 1; i <= 6; i++) {
            ret += Math.abs(nn[i] - ne[i]);
        }
        return ret;
    }

    private static int[] getNums(int n) {
        int[] ret = new int[7];
        ret[0] = n % 10;
        n /= 10;
        for (int i = 1; i <= 6; i++) {
            ret[7-i] = n % 10;
            n /= 10;
        }

        return ret;
    }

    private static List<Node> getNeighbors(Node n) {
        List<Node> ret = new LinkedList<Node>();
        List<Integer> ints = new LinkedList<Integer>();
        int[] numbers = n.numbers;
        int pos = numbers[0];
        if (pos < 6) {
            numbers[0]++;
            ints.add(genNumber(numbers));
            numbers[0]--;
        }
        if (pos > 1) {
            numbers[0]--;
            ints.add(genNumber(numbers));
            numbers[0]++;
        }
        if (numbers[pos] < MAX_DIGIT) {
            numbers[pos]++;
            ints.add(genNumber(numbers));
            numbers[pos]--;
        }
        if (numbers[pos] > MIN_DIGIT) {
            numbers[pos]--;
            ints.add(genNumber(numbers));
            numbers[pos]++;
        }
        int first = numbers[1];
        int change = numbers[pos];
        int last = numbers[6];
        if (pos != 1 && change != first) {
            numbers[1] = change;
            numbers[pos] = first;
            ints.add(genNumber(numbers));
            numbers[1] = first;
            numbers[pos] = change;
        }
        if (pos != 6 && change != last) {
            numbers[6] = change;
            numbers[pos] = last;
            ints.add(genNumber(numbers));
            numbers[6] = last;
            numbers[pos] = change;
        }

        for (Integer cand : ints) {
            if (!cache.containsKey(cand)) {
                Node newNode = new Node();
                newNode.numbers = getNums(cand);
                ret.add(newNode);
            } else {
                ret.add(cache.get(cand));
            }
        }
        return ret;
    }
}
