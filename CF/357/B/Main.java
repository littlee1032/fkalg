import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] dancers = new int[n+1];
        int[][] dances = new int[m][3];
        ArrayList[] reverseLookup = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            reverseLookup[i] = new ArrayList();
        }
        for (int i = 0; i < m; i++) {
            dances[i][0] = scan.nextInt();
            dances[i][1] = scan.nextInt();
            dances[i][2] = scan.nextInt();
            for (int j = 0; j < 3; j++) {
                reverseLookup[dances[i][j]].add(i);
            }
        }

        letTry(dancers, dances, reverseLookup, 0, 0);
        for (int i = 1; i <= n; i++) {
            System.out.print(dancers[i]);
            if (i != n) System.out.print(" ");
        }
    }

    private static boolean letTry(int[] dancers, int[][] dances, ArrayList[] reverseLookup, int dance, int dancer) {
        if (dance == dances.length) return true;
        if (dancer == dances[0].length) return letTry(dancers, dances, reverseLookup, dance+1, 0);
        if (dancers[dances[dance][dancer]] != 0) return letTry(dancers, dances, reverseLookup, dance, dancer+1);
        for (int color = 1; color <= 3; color++) {
            boolean conflict = false;
            for (Object d : reverseLookup[dances[dance][dancer]]) {
                int current = (Integer)d;
                if (current < dance) continue;
                for (int pre = 0; pre < 3; pre++) {
                    if (current == dance && pre == dancer) continue;
                    if (dancers[dances[current][pre]] == color) {
                        conflict = true;
                        break;
                    }
                }
            }
            if (conflict) continue;
            else {
                dancers[dances[dance][dancer]] = color;
                if (letTry(dancers, dances, reverseLookup, dance, dancer+1)) {
                    return true;
                } else {
                    dancers[dances[dance][dancer]] = 0;
                }
            }
        }
        return false;
    }
}
