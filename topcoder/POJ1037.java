import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class POJ1037 {
    public static void main(String[] args) {
        int N = 20;
        long[][] matrix = new long[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(matrix[i], 0);
        }

        matrix[1][1] = 1;
        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < i; j++) {
                matrix[i][j + 1] = matrix[i][j] + matrix[i - 1][i - j];
            }
        }
        /*
         * for (int i = 0; i < N + 1; i++) { for (int j = 0; j < N + 1; j++) {
         * System.out.print(matrix[i][j] + "  "); } System.out.println(); }
         */

        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        for (int i = 0; i < k; i++) {
            int NN = scan.nextInt();
            long C = scan.nextLong();
            int n = NN;
            if (NN == 1 && C == 1) {
                System.out.println("1");
                continue;
            }
            List<Integer> woods = new LinkedList<Integer>();
            for (int j = 0; j < n + 1; j++) {
                woods.add(j);
            }
            int fence = 1;
            for (fence = 1; C > matrix[n][fence] + matrix[n][n - fence + 1]; fence++) {
                C -= matrix[n][fence] + matrix[n][n - fence + 1];
            }
            System.out.print(fence);
            woods.remove(fence);
            if (C == 0) {
                printLast(fence, woods, false);
                System.out.println();
                continue;
            } else {
                System.out.print(" ");
            }
            n--;
            // first complete, now we try to find second
            int fenceTry = 1;
            long tmp = C;
            for (; tmp >= matrix[n][n-fenceTry+1] && fenceTry < fence; fenceTry++) {
                tmp -= matrix[n][n-fenceTry+1];
                if (tmp == 0) break;
            }
            boolean isDownwards = true;
            C = tmp;
            if (fenceTry < fence) {
                // we fond the second
                int wood = woods.remove(fenceTry);
                System.out.print(fenceTry);
                if (C == 0) {
                    printLast(wood, woods, true);
                    System.out.println();
                    continue;
                } else {
                    System.out.print(" ");
                    isDownwards = true;
                }
                n--;
                fence = wood;
            }
           
            int prefence = fence;
            while (C > 0 && n > 0) {
//                 System.out.println("C: " + C);
                long cTry = C;
                boolean found = false;
                if (!isDownwards) {
                    for (fenceTry = 1; cTry >= matrix[n][n - fenceTry + 1] && fenceTry < prefence; fenceTry++) {                       
//                        System.out.println("Try: " + fenceTry);
//                        System.out.println(matrix[n][n - fenceTry + 1]);
                        cTry -= matrix[n][n - fenceTry + 1];
                        if (cTry == 0) break;
                    }
                    if (fenceTry < prefence) {
                        // found
                        C = cTry;
                        isDownwards = true;
                        found = true;
                    }
                } else {
                    for (fenceTry = prefence; cTry >= matrix[n][fenceTry] && fenceTry <= n; fenceTry++) {
                        cTry -= matrix[n][fenceTry];
                        if (cTry == 0) break;
                    }
                    if (fenceTry <= n) {
                        C = cTry;
                        isDownwards = false;
                        found = true;
                    }
                }
                /*
                 * System.out.println("woods: " + woods);
                 * System.out.println("isDownwards: " + isDownwards);
                 * System.out.println("fenceTry: " + fenceTry);
                 */
                if (!found) {
                    System.out.println("WRONG");
                    break;
                } else {
                    int wood = woods.remove(fenceTry);
                    System.out.print(wood);
                    if (C == 0) {
                        printLast(wood, woods, isDownwards);
                    } else {
                        System.out.print(" ");
                    }
                    n--;
                    prefence = fenceTry;
                }
            }
            System.out.println();
        }
    }

    private static void printLast(int wood, List<Integer> woods, boolean isDownwards) {
        if (woods.size() == 1) {
            return;
        } else {
            System.out.print(" ");
        }
        if (woods.size() == 2) {
            System.out.print(woods.get(1));
            return;
        }
        if (!isDownwards) {
            int cur = 1;
            for (; cur < woods.size() - 1 && woods.get(cur) < wood; cur++);
            int curWood = woods.remove(cur - 1);
            System.out.print(curWood);
            if (woods.size() >= 2) {
                System.out.print(" ");
                curWood = woods.remove(woods.size() - 1);
                System.out.print(curWood);
            }
            
            printLast(curWood, woods, isDownwards);
        } else {
            int curWoods = woods.remove(woods.size() - 1);
            System.out.print(curWoods);
            printLast(curWoods, woods, false);
        }
    }
}
