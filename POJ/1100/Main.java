import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static final boolean DEBUG = false;
    private static final String[] ops = new String[]{"+", "-", "*"};
    private static final int NMAX = 18;
    private static final List<OpNode> opList = new LinkedList<OpNode>();
    private static final long[] threes = new long[NMAX];


    static abstract class Node {
        public abstract String toString();
        public abstract long eval();
    }

    static class ValueNode extends Node {
        private final long value;

        public ValueNode(long value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(value);
        }

        public long eval() {
            return value;
        }
    }

    static class OpNode extends Node {
        private int op;
        private final Node left;
        private final Node right;

        public OpNode(int op, Node left, Node right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }

        public void setOp(int op) {
            this.op = op;
        }

        public int getOp() {
            return op;
        }

        public String toString() {
            return left.toString() + ops[op] + right.toString();
        }

        public long eval() {
            if (op == 0) return left.eval() + right.eval();
            if (op == 1) return left.eval() - right.eval();
            else return left.eval() * right.eval();
        }
    }

    static class ParaNode extends Node {
        private final Node child;

        public ParaNode(Node child) {
            this.child = child;
        }

        public String toString() {
            return "(" + child.toString() + ")";
        }

        public long eval() {
            return child.eval();
        }
    }

    private static Node parseTree(String cmd) {
        String[] tmp = new String[NMAX];
        StringBuilder sb = new StringBuilder();
        int cmdsIdx = 0;
        int lPara = 0;
        for (int i = 0; i < cmd.length(); i++) {
            char c = cmd.charAt(i);
            if ('(' == c) {
                if (sb.length() != 0 && lPara == 0) {
                    tmp[cmdsIdx++] = sb.toString();
                    sb.setLength(0);
                }
                sb.append(c);
                lPara++;
            } else if (')' == c) {
                lPara--;
                sb.append(c);
                if (lPara == 0) {
                    tmp[cmdsIdx++] = sb.toString();
                    sb.setLength(0);
                }
            } else if (' ' == c && lPara == 0) {
                if (sb.length() != 0) {
                    tmp[cmdsIdx++] = sb.toString();
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) {
            tmp[cmdsIdx++] = sb.toString();
        }

        if (DEBUG) {
            for (int i = 0; i < cmdsIdx; i++) {
                System.out.print(tmp[i] + " ");
            }
            System.out.println();
        }

        int num = cmdsIdx;
        Node left = createNode(tmp[0]);
        Node ret = left;
        for (int i = 1; i < num; i++) {
            Node right = createNode(tmp[i]);
            Node opNode = new OpNode(-1, left, right);
            opList.add((OpNode)opNode);
            ret = opNode;
            left = opNode;
        }
        return ret;
    }

    private static Node createNode(String aCmd) {
        if ('(' == aCmd.charAt(0)) {
            return new ParaNode(parseTree(aCmd.substring(1, aCmd.length() - 1)));
        } else {
            return new ValueNode(Long.valueOf(aCmd));
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine().trim();
        int counter = 0;
        threes[0] = 1;
        for (int i = 1; i < NMAX; i++) threes[i] = 3 * threes[i - 1];
        while (!"0".equals(line)) {
            counter++;
            System.out.println("Equation #" + counter + ":");
            String[] tmp = line.split("=");
            long target = Long.valueOf(tmp[0].trim());
            Node tree = parseTree(tmp[1].trim());
            int n = opList.size();
            boolean found = false;
            for (int i = 0; i < threes[n]; i++) {
                for (int j = 0; j < n; j++) {
                    int op = (int)((i / threes[n - j - 1]) % 3);
                    opList.get(j).setOp(op);
                }
                if (DEBUG) System.out.println("Tring: " + tree);
                if (target == tree.eval()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Impossible");
            } else {
                System.out.println(target + "=" + tree);
            }
            System.out.println();
            line = scan.nextLine().trim();
            opList.clear();
        }
    }
}
