import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class Main {
    public static class Block {
        public int label;
        public int stack;
        public Block up;
        public Block down;
    }

    private static Map<Integer, Block> map = new HashMap<Integer, Block>();

    private static void flatten(Block b) {
        while (b != null) {
            b.down.up = null;
            b.stack = b.label;
            b.down = null;
            b = b.up;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            Block b = new Block();
            b.label = i;
            b.stack = i;
            map.put(i, b);
        }

        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] ops = line.split(" ");
            if ("quit".equals(ops[0])) break;
            else {
                Block s = map.get(Integer.valueOf(ops[1]));
                Block t = map.get(Integer.valueOf(ops[3]));
                if (s.stack == t.stack) continue;
                if ("move".equals(ops[0])) {
                    flatten(s.up);
                }
                if ("onto".equals(ops[2])) {
                    flatten(t.up);
                }
                Block top = t;
                while (top.up != null) top = top.up;
                int stack = t.stack;
                if (s.down != null) s.down.up = null;
                s.down = top;
                top.up = s;
                while (s != null) {
                    s.stack = stack;
                    s = s.up;
                }
            }
        }

        Map<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            Block b = map.get(i);
            int stack = b.stack;
            if (result.containsKey(stack)) continue;
            List<Integer> stacks = new LinkedList<Integer>();
            result.put(stack, stacks);
            while (b.down != null) b = b.down;
            while (b != null) {
                stacks.add(b.label);
                b = b.up;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(i + ":");
            if (result.containsKey(i)) {
                for (int j : result.get(i)) {
                    System.out.print(" " + j);
                }
            }
            System.out.println();
        }
    }
}
