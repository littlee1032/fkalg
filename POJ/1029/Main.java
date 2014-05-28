import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int coinNum = scan.nextInt();
        int[] array = new int[coinNum + 1];
        Arrays.fill(array, Integer.MAX_VALUE);
        int round = scan.nextInt();
        int expect = 0;
        for (int i = 0; i < round; i++) {
            int numPerSide = scan.nextInt();
            Set<Integer> leftSide = new HashSet<Integer>();
            for (int j = 0; j < numPerSide; j++) {
                leftSide.add(scan.nextInt());
            }
            Set<Integer> rightSide = new HashSet<Integer>();
            for (int j = 0; j < numPerSide; j++) {
                rightSide.add(scan.nextInt());
            }
            String result = scan.next();
            Set<Integer> merge = new HashSet<Integer>(leftSide);
            merge.addAll(rightSide);
            if ("=".equals(result)) {
                for (int j = 1; j <= coinNum; j++) {
                    if (merge.contains(j)) {
                        array[j] = 0;
                    }
                }
            } else {
                for (int j = 1; j <= coinNum; j++) {
                    if (merge.contains(j) && array[j] == Integer.MAX_VALUE) {
                        array[j] = 0;
                    }
                }
                expect++;
                int diff = 0;
                if ("<".equals(result)) {
                    diff = 1;
                } else {
                    diff = -1;
                }
                for (int j = 1; j <= coinNum; j++) {
                    if (leftSide.contains(j)) {
                        array[j] -= diff;
                    }
                    if (rightSide.contains(j)) {
                        array[j] += diff;
                    }
                }
            }
        }
        List<Integer> found = new ArrayList<Integer>();
        if (expect > 0) {
            for (int i = 1; i <= coinNum; i++) {
                if (array[i] == expect) {
                    found.add(i);
                } else if (array[i] == -1 * expect) {
                    found.add(i);
                }
            }
        }
        if (found.size() == 1) {
            System.out.println(found.get(0));
            return;
        } else if (found.size() != 0) {
            System.out.println("0");
            return;
        }
        found.clear();
        for (int i = 1; i <= coinNum; i++) {
            if (array[i] == Integer.MAX_VALUE) {
                found.add(i);
            }
        }
        if (found.size() == 1) {
            System.out.println(found.get(0));
        } else {
            System.out.println("0");
        }
    }
}
