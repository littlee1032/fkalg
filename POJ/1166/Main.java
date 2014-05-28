import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Main {
    /*
    private static int[] steps = new int[] {
        0x110110000, 0x111000000, 0x011011000,
        0x100100100, 0x010111010, 0x001001001,
        0x000110110, 0x000000111, 0x000011011
    };
    */

    private static int[] steps = new int[] {
        0x1B0, 0x1C0, 0x0D8,
        0x124, 0x0BA, 0x049,
        0x036, 0x007, 0x01B
    };

    private static int[] scales = new int[] {
        1 << 16, 1 << 14, 1 << 12,
        1 << 10, 1 << 8,  1 << 6,
        1 << 4,  1 << 2,  1 << 0
    };

    private static int[] masks = new int[] {
        1 << 8, 1 << 7, 1 << 6,
        1 << 5, 1 << 4, 1 << 3,
        1 << 2, 1 << 1, 1 << 0
    };

    private static int next(int cur, int step) {
        return move(cur, step, true);
    }

    private static int move(int cur, int step, boolean isForward) {
        int bias = isForward ? 1 : 3;
        int ret = 0;
        for (int i = 0; i < 9; i++) {
            if ((step & masks[i]) != 0) {
                ret += ((cur / scales[i] + bias) % 4) * scales[i];
            } else {
                ret += ((cur / scales[i]) % 4) * scales[i];
            }
        }
        //System.out.println("Move from " + cur + " to " + ret + " using step " + step + " isForward? " + isForward);
        return ret;
    }

    private static int prev(int cur, int step) {
        return move(cur, step, false);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<Integer, Integer> dp = new HashMap<Integer, Integer>();

        int init = 0;
        for (int i = 0; i < 9; i++) {
            init += scan.nextInt() * scales[i];
        }
        dp.put(init, Integer.MIN_VALUE);
        dp.put(0, Integer.MAX_VALUE);

        int end = 0;
        int start = init;

        boolean found = false;
        Queue<Integer> fq = new LinkedList<Integer>();
        Queue<Integer> bq = new LinkedList<Integer>();
        fq.offer(init);
        bq.offer(0);
        List<Integer> answer = new LinkedList<Integer>();
        int iter = 0;
        while (!found) {
            //System.out.println("iter: " + (++iter));
            Integer[] elements = fq.toArray(new Integer[0]);
            fq.clear();
            for (Integer ele : elements) {
                for (int i = 0; i < 9 && !found; i++) {
                    int next = next(ele, steps[i]);
                    if (!dp.containsKey(next)) {
                        dp.put(next, -i);
                        fq.offer(next);
                    } else if (dp.get(next) > 0) {
                        // found
                        end = next;
                        start = ele;
                        found = true;
                        // add the transition step
                        answer.add(i);
                        //System.out.println("start: " + start + " end: " + end);
                        break;
                    }
                }
                if (found) break;
             }
             if (!found) {
                 elements = bq.toArray(new Integer[0]);
                 bq.clear();
                 for (Integer ele : elements) {
                     for (int i = 0; i < 9 && !found; i++) {
                         int prev = prev(ele, steps[i]);
                         if (!dp.containsKey(prev)) {
                             dp.put(prev, i);
                             bq.offer(prev);
                         } else if (dp.get(prev) < 0) {
                             // found
                             end = ele;
                             start = prev;
                             //System.out.println("START: " + start + " END: " + end);
                             found = true;
                             // add the transition step
                             answer.add(i);
                             break;
                         }
                     }
                     if (found) break;
                 }
             }
        }
        //System.out.println("init: " + init);
        while (start != init) {
            int step = -dp.get(start);
            //System.out.println("start: " + start + " step: " + step);
            answer.add(step);
            start = prev(start, steps[step]);
        }
        while (end != 0) {
            int step = dp.get(end);
            answer.add(step);
            end = next(end, steps[step]);
        }
        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i)+1);
            if (i == answer.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }
}
