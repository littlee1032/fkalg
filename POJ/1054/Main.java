import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class Main {
    private static int getMaxLen(int[] arr, int row, int column) {
        Set<Integer> flatten = new HashSet<Integer>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            flatten.add(arr[i]);
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x1 = arr[i] / 10000;
                int y1 = arr[i] % 10000;
                int x2 = arr[j] / 10000;
                int y2 = arr[j] % 10000;
                //System.out.println("("+x1+","+y1+")" + " ("+x2+","+y2+")");

                int m = 1;
                int dx = x2 - x1;
                int dy = y2 - y1;

                if (x1 - dx >= 1 && x1 - dx <= row && y1 - dy >= 1 && y1 - dy <= column) {
                    continue;
                }

                if (x1 + dx * max > row) {
                    continue;
                }
                if (dy >= 0) {
                    if (y1 + dy * max > column) {
                        continue;
                    } else {
                        for (int x = x1 + dx, y = y1 + dy; x <= row && y <= column; x += dx, y += dy) {
                            //System.out.println("try ("+x+","+y+")");
                            int code = x * 10000 + y;
                            if (flatten.contains(code)) {
                                m++;
                            } else {
                                m = 0;
                                break;
                            }
                        }
                    }
                } else {
                    if (y1 + dy * max < 1) {
                        continue;
                    } else {
                        for (int x = x1 + dx, y = y1 + dy; x <= row && y >= 1; x += dx, y += dy) {
                            //System.out.println("try ("+x+","+y+")");
                            int code = x * 10000 + y;
                            if (flatten.contains(code)) {
                                m++;
                            } else {
                                m = 0;
                                break;
                            }
                        }
                    }
                }
                if (m > max) {
                    max = m;
                }
            }
        }
        if (max > 2) {
            return max;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int R = scan.nextInt();
        int C = scan.nextInt();
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int code = x * 10000 + y;
            arr[i] =  code;
        }
        Arrays.sort(arr);
        System.out.println(getMaxLen(arr, R, C));
    }
}
