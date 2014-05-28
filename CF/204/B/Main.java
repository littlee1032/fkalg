import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int NMAX = 100001;
        int[][] counter = new int[NMAX][3];

        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();

            int lastSaw = counter[a][1];
            counter[a][1] = i;
            if (counter[a][0] == -1) continue;
            else if (counter[a][0] == 0) counter[a][0]++;
            else if (counter[a][0] == 1) {
                counter[a][0]++;
                counter[a][2] = i - lastSaw;
            } else {
                if (i - lastSaw != counter[a][2]) counter[a][0] = -1;
                else {
                    counter[a][0]++;
                    counter[a][1] = i;
                }
            }
        }

        int total = 0;
        for (int i = 1; i < NMAX; i++) {
            if (counter[i][0] > 0) total++;
        }

        System.out.println(total);
        for (int i = 1; i < NMAX; i++) {
            if (counter[i][0] == 1) {
                System.out.println(i + " " + 0);
            } else if (counter[i][0] > 1) {
                System.out.println(i + " " + counter[i][2]);
            }
        }
    }
}
