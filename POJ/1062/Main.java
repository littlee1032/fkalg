import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Arrays;

public class Main {
    private static final boolean DEBUG = false;

    static class Item {
        public int original;
        public int best;
        public int level;
        public int[] depends;
        public int index;
        public boolean isBanned;

        public boolean dependsOn(int idx) {
            for (int i = 0; i < depends.length; i += 2) {
                if (depends[i] == idx) {
                    return true;
                }
            }
            return false;
        }
    }

    static class ItemComparator implements Comparator<Item> {
        public int compare(Item t1, Item t2) {
            int idx1 = t1.index;
            int idx2 = t2.index;

            if (t1.dependsOn(idx2)) {
                return 1;
            }
            if (t2.dependsOn(idx1)) {
                return -1;
            }
            return t1.original - t2.original + idx2 - idx1;
        }
    }

    private static int getBestPrice(int levelRagion, Map<Integer, Item> items, Item[] arr) {
        int level = items.get(1).level;
        int bestPrice = items.get(1).original;
        for (int minLevel = level - levelRagion; minLevel <= level; minLevel++) {
            int maxLevel = minLevel + levelRagion;
            if (DEBUG) System.out.println("min: " + minLevel + " max: " + maxLevel);
            for (Item item : arr) {
                if (item.level >= minLevel && item.level <= maxLevel) {
                    item.isBanned = false;
                    int curBest = item.original;
                    for (int i = 0; i < item.depends.length; i += 2) {
                        int dIdx = item.depends[i];
                        int value = item.depends[i+1];
                        Item dItem = items.get(dIdx);
                        if (!dItem.isBanned) {
                            int myBest = dItem.best + value;
                            if (myBest < curBest) {
                                curBest = myBest;
                                if (DEBUG) System.out.println("Item " + item.index + " best price is from Item " + dIdx + " " + curBest);
                            }
                        }
                    }
                    item.best = curBest;
                } else {
                    item.isBanned = true;
                    if (DEBUG) System.out.println("Item " + item.index + " is banned");
                }
            }
            if (bestPrice > items.get(1).best) {
                bestPrice = items.get(1).best;
            }
        }
        return bestPrice;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int M = scan.nextInt();
        int N = scan.nextInt();
        Map<Integer, Item> items = new HashMap<Integer, Item>();
        Item[] arr = new Item[N];

        for (int i = 1; i <= N; i++) {
            Item item = new Item();
            item.original = scan.nextInt();
            item.best = item.original;
            item.level = scan.nextInt();
            item.index = i;
            int depends = scan.nextInt();
            item.depends = new int[depends * 2];
            for (int j = 0, index = 0; j < depends; j++) {
                item.depends[index++] = scan.nextInt();
                item.depends[index++] = scan.nextInt();
            }
            items.put(i, item);
            arr[i - 1] = item;
        }

        Arrays.sort(arr, new ItemComparator());
        System.out.println(getBestPrice(M, items, arr));
    }
}
