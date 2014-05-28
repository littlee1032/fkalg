import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Comparator;

public class Main {
    private static Comparator<int[]> cmp = new MyComparator();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }

        Map<Integer, List<int[]>> hMap = new TreeMap<Integer, List<int[]>>();
        Map<Integer, List<int[]>> vMap = new TreeMap<Integer, List<int[]>>();
        for (int i = 0; i < n; i++) {
            int lx = scan.nextInt();
            int ly = scan.nextInt();
            int rx = scan.nextInt();
            int ry = scan.nextInt();

            if (!hMap.containsKey(ly)) hMap.put(ly, new LinkedList<int[]>());
            if (!hMap.containsKey(ry)) hMap.put(ry, new LinkedList<int[]>());

            hMap.get(ly).add(new int[]{lx, rx, i, 1});
            hMap.get(ry).add(new int[]{lx, rx, i, -1});

            if (!vMap.containsKey(lx)) vMap.put(lx, new LinkedList<int[]>());
            if (!vMap.containsKey(rx)) vMap.put(rx, new LinkedList<int[]>());

            vMap.get(lx).add(new int[]{ly, ry, i, 1});
            vMap.get(rx).add(new int[]{ly, ry, i, -1});
        }

        int ret = 0;
        ret += calc(hMap);
        ret += calc(vMap);

        System.out.println(ret);
    }

    private static int calc(Map<Integer, List<int[]>> map) {
        int ret = 0;
        List<int[]> current = new LinkedList<int[]>();

        for (List<int[]> list: map.values()) {
            System.out.println("pre: " + myToString(current));
            System.out.println("merge: " + myToString(list));
            int pre = calc(current);
            merge(current, list, -1);
            int now = calc(current);
            System.out.println("now: " + myToString(current));
            System.out.println("pre: " + pre + " now: " + now);
            ret += Math.abs(now - pre);
            pre = now;
            merge(current, list, 1);
            now = calc(current);
        }

        return ret;
    }

    private static String myToString(List<int[]> list) {
        String ret = "{";
        for (int[] l : list) {
            ret += Arrays.toString(l) + " ";
        }
        ret += "}";
        return ret;
    }

    private static void merge(List<int[]> current, List<int[]> list) {
        Collections.sort(list, cmp);
        ListIterator<int[]> cItr = current.listIterator();
        ListIterator<int[]> lItr = list.listIterator();
        while (lItr.hasNext()) {
            int[] l = lItr.next();
            if (l[3] == -1) {
                // delete
                while(cItr.hasNext() && cItr.next()[2] != l[2]);
                cItr.remove();
            }
        }
        lItr = list.listIterator();
        while (lItr.hasNext()) {
            int[] l = lItr.next();
            if (l[3] == 1) current.add(l);
        }
        Collections.sort(current, cmp);
    }

    private static int calc(List<int[]> list) {
        if (list.size() == 0) return 0;
        int[] first = list.get(0);
        int min = first[0];
        int max = first[1];

        int ret = 0;
        for (int[] ele : list) {
            if (ele[0] >= max) {
                ret += (max - min);
                min = ele[0];
                max = ele[1];
            } else {
                max = Math.max(max, ele[1]);
            }
        }
        ret += max - min;
        return ret;
    }

    private static class MyComparator implements Comparator<int[]> {
        @Override public int compare(int[] a, int[] b) {
            // 0 small
            // 1 large
            if (a[0] == b[0]) {
                if (a[1] == b[1]) return a[2] - b[2];
                return a[1] - b[1];
            }
            return a[0] - b[0];
        }
    }
}
