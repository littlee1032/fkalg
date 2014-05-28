import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final boolean DEBUG = false;

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    private static int calculate(int[] students, boolean[][] knew) {
        int max = 0;
        for (int i = 0; i < students.length; i++) {
            int thisMax = 0;
            for (int j = 0; j < students.length; j++) {
                if (!knew[students[i]][students[j]]) {
                    thisMax++;
                }
            }
            max = Math.max(max, thisMax);
        }
        return max;
    }

    private static void findMin(int[] first, int[] second, int total, boolean[][] knew, int start, int left, int[] min) {
        if (left == 0) {
            int firstMin = calculate(first, knew);
            int secIdx = 0;
            for (int i = 1; i <= total; i++) {
                boolean found = false;
                for (int j = 0; j < first.length; j++) {
                    if (first[j] == i) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    second[secIdx++] = i;
                }
            }
            int secMin = calculate(second, knew);
            int myMin = Math.max(firstMin, secMin);
            if (min[0] > myMin) {
                if (DEBUG) {
                    System.out.println("Find Min:" + myMin);
                    print(first);
                    print(second);
                }
                min[0] = myMin;
            }
        } else {
            for (int i = start; i <= total - left + 1; i++) {
                int len = first.length;
                first[len - left] = i;
                findMin(first, second, total, knew, i + 1, left - 1, min);
            }
        }
    }

    public static void main(String[] args) {
        int nMax = 31;
        boolean[][] knew = new boolean[nMax][nMax];
        for (int i = 0; i < nMax; i++) {
            Arrays.fill(knew[i], false);
            knew[i][0] = true;
            knew[i][i] = true;
        }
        Scanner scan = new Scanner(System.in);
        int num = 0;
        while (scan.hasNext()) {
            num++;
            int student = scan.nextInt();
            int knewNum = scan.nextInt();
            for (int i = 0; i < knewNum; i++) {
                int aStudent = scan.nextInt();
                knew[student][aStudent] = true;
                knew[aStudent][student] = true;
            }
        }
        int[] first = new int[num / 2];
        int[] second = new int[num - num / 2];
        int[] min = new int[]{Integer.MAX_VALUE};
        findMin(first, second, num, knew, 1, num / 2, min);
        System.out.println(min[0]);
    }
}
