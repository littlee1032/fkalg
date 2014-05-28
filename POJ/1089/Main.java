import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static class MyComparator implements Comparator<int[]> {
        public int compare(int[] t1, int[] t2) {
            if (t1[0] == t2[0]) {
                return t1[1] - t2[1];
            } else {
                return t1[0] - t2[0];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<int[]> intervals = new ArrayList<int[]>();
        while (n-- > 0) {
            int[] interval = new int[]{scan.nextInt(), scan.nextInt()};
            intervals.add(interval);
        }

        Collections.sort(intervals, new MyComparator());
        int[] first = intervals.get(0);
        int start = first[0];
        int end = first[1];
        for (int i = 1; i < intervals.size(); i++) {
            int[] interval = intervals.get(i);
            int newStart = interval[0];
            int newEnd = interval[1];

            if (newStart <= end) {
                if (end < newEnd) {
                    end = newEnd;
                }
            } else {
                System.out.println(start + " " + end);
                start = newStart;
                end = newEnd;
            }
        }
        System.out.println(start + " " + end);
    }
}
