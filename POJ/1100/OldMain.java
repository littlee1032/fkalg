import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    private static final boolean DEBUG = false;

    private static final Deque<String> queue = new LinkedList<String>();
    private static final int NMAX = 18;
    private static final int[] operators = new int[NMAX];
    private static final String[] ops = new String[]{"+", "-", "*"};

    private static boolean hasLPara(String value) {
        return value.charAt(0) == '(';
    }

    private static boolean hasRPara(String value) {
        return value.charAt(value.length() - 1) == ')';
    }

    private static long eval(Deque<String> queue) {
        if (DEBUG) System.out.println(queue);
        String lValue = queue.remove();
        long l = 0;
        if ("(".equals(lValue)) {
            queue.addFirst("(");
            queue.addFirst("+");
        } else {
            l = Long.valueOf(lValue);
        }
        while (!queue.isEmpty()) {
            String operator = queue.remove();
            String rValue = queue.remove();
            if (DEBUG) System.out.println("op: " + operator + " rValue: " + rValue);
            long r = 0;
            if ("(".equals(rValue)) {
                int lPara = 1;
                Deque<String> tmp = new LinkedList<String>();
                while (lPara > 0) {
                    String v = queue.remove();
                    if ("(".equals(v)) {
                        lPara++;
                    } else if (")".equals(v)) {
                        lPara--;
                    }
                    if (lPara != 0) {
                        tmp.add(v);
                    }
                }
                r = eval(tmp);
            } else {
                r = Long.valueOf(rValue);
            }
            if ("-".equals(operator)) {
                l -= r;
            } else if ("+".equals(operator)) {
                l += r;
            } else {
                l *= r;
            }
            if (DEBUG) System.out.println("l: " + l);
        }
        return l;
    }

    private static boolean interpret(String[] args, int idx, long target) {
        String arg = args[idx];
        if (hasLPara(arg)) {
            queue.add("(");
            queue.add(arg.substring(1));
        } else if (hasRPara(arg)) {
            queue.add(arg.substring(0, arg.length() - 1));
            queue.add(")");
        } else {
            queue.add(arg);
        }
        boolean ret = false;
        if (idx == args.length - 1) {
            long test = eval(new LinkedList<String>(queue));
            if (test == target) {
                ret = true;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                operators[idx] = i;
                queue.add(ops[i]);
                if (interpret(args, idx + 1, target)) {
                    ret = true;
                }
                queue.removeLast();
                if (ret) break;
            }
        }
        queue.removeLast();
        if (hasLPara(arg) || hasRPara(arg)) {
            queue.removeLast();
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine().trim();
        int counter = 0;
        while (!"0".equals(line)) {
            counter++;
            String[] tmp = line.split(" ");
            long target = Long.valueOf(tmp[0]);
            int n = tmp.length - 2;
            String[] margs = new String[n];
            System.arraycopy(tmp, 2, margs, 0, n);
            System.out.println("Equation #" + counter + ":");
            if (interpret(margs, 0, target)) {
                System.out.print(target + "=");
                for (int i = 0; i < n; i++) {
                    System.out.print(margs[i]);
                    if (i != n - 1) System.out.print(ops[operators[i]]);
                }
                System.out.println();
            } else {
                System.out.println("Impossible");
            }
            System.out.println();
            line = scan.nextLine().trim();
        }
    }

}
