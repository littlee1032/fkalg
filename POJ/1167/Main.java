import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;

public class Main {
    private static int[] times = new int[60];
    private static int[] uses = new int[60];
    private static int min = 17;
    private static int route = 0;
    private static List<Bus> bus = new ArrayList<Bus>();
    //private static int counter = 0;
    /*
    private static Queue<Bus> mq = new LinkedList<Bus>();
    private static Queue<Bus> q = new LinkedList<Bus>();
    */

    private static class Bus {
        public Bus(int start, int interval, int times) {
            this.start = start;
            this.interval = interval;
            this.times = times;
        }

        int start;
        int interval;
        int times;
    }

    private static class BusCmp implements Comparator<Bus> {
        @Override public int compare(Bus a, Bus b) {
            // revert sort
            return b.times - a.times;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) times[scan.nextInt()]++;

        for (int i = 0; i < 30; i++) {
            for (int j = 1; j < 60 - i; j++) {
                if (check(i, j)) {
                    if (!(i-j >= 0 && check(i-j, j))) {
                        bus.add(new Bus(i, j, (60-i)/j + 1));
                    }
                }
            }
        }

        Collections.sort(bus, new BusCmp());
        //System.out.println(bus.size());
        dfs(0, n, 0);
        System.out.println(min);
        //System.out.println(counter);
    }

    private static void dfs(int busIdx, int left, int routeNum) {
        //counter++;
        if (routeNum >= min) return;
        for (int i = busIdx; i < bus.size(); i++) {
            Bus b = bus.get(i);
            if (routeNum + left / b.times >= min) return;
            if (check(b.start, b.interval)) {
                boolean needBreak = false;
                int use = use(b.start, b.interval, 1);
                if (left - use == 0) {
                    min = Math.min(min, routeNum+1);
                    needBreak = true;
                } else {
                    dfs(i, left-use, routeNum+1);
                }
                use(b.start, b.interval, -1);
                if (needBreak) return;
            }
        }
    }
    private static int use(int start, int interval, int diff) {
        int ret = 0;
        for (int i = start; i < 60; i += interval) {
            uses[i] += diff;
            ret += diff;
        }
        return ret;
    }

    private static boolean check(int start, int interval) {
        for (int i = start; i < 60; i += interval)
            if (uses[i] >= times[i]) return false;
        return true;
    }
}
