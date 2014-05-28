import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class Main {
    private static final boolean DEBUG = true;

    static class Table {
        public Set<Character> hold = new HashSet<Character>();
        public Set<Character> neu = new HashSet<Character>();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Table[] tables = new Table[n];
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            tables[i] = new Table();
            String line = scan.nextLine();
            String[] tmp = line.split(" ");
            for (int j = 0; j < tmp[0].length(); j++) {
                char c = tmp[0].charAt(j);
                if (c != '.') {
                    tables[i].hold.add(c);
                }
            }
            for (int j = 0; j < tmp[1].length(); j++) {
                char c = tmp[1].charAt(j);
                if (c != '.') {
                    tables[i].neu.add(c);
                }
            }
            tables[i].hold.removeAll(tables[i].neu);
        }
        boolean[][] map = new boolean[n][n];
        int up = scan.nextInt();
        int down = scan.nextInt();
        while (up + down > 0) {
            map[up - 1][down - 1] = true;
            up = scan.nextInt();
            down = scan.nextInt();
        }

        boolean tryNext = true;
        while (tryNext) {
            tryNext = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j]) {
                        Set<Character> newHold = new HashSet<Character>(tables[j].hold);
                        newHold.addAll(tables[i].hold);
                        newHold.removeAll(tables[j].neu);
                        if (!newHold.equals(tables[j].hold)) {
                            tryNext = true;
                            tables[j].hold = newHold;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            Character[] arr = tables[i].hold.toArray(new Character[0]);
            for (int j = 0; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[j].charValue()  > arr[k].charValue()) {
                        Character tmp = arr[j];
                        arr[j] = arr[k];
                        arr[k] = tmp;
                    }
                }
            }
            System.out.print(":");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j]);
            }
            System.out.println(":");
        }
    }
}
