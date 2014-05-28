import java.util.Scanner;

public class Main {
    private static final int NMAX = 110;
    private static final int[] index = new int[NMAX];
    private static final int[] lowIndex = new int[NMAX];
    private static final int[] sccNo = new int[NMAX];
    private static final int[] stack = new int[NMAX];
    private static int head = -1;
    private static boolean[][] map = new boolean[NMAX][NMAX];
    private static int scc = 1;
    private static int idx = 1;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            int school = scan.nextInt();
            while (school != 0) {
                map[i][school] = true;
                school = scan.nextInt();
            }
        }

        for (int i = 1; i <= n; i++) {
            if (index[i] == 0) {
                tarjan(i);
            }
        }

        if (scc == 2) {
            // only one strong connect component
            System.out.println(1);
            System.out.println(0);
        } else {
            int zeroIn = 0;
            int zeroOut = 0;

            int[] ins = new int[scc];
            int[] outs = new int[scc];

            boolean[][] sccMap = new boolean[scc][scc];
            for (int i = 1; i <= n; i++) {
                int myNo = sccNo[i];
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] && myNo != sccNo[j]) {
                        sccMap[myNo][sccNo[j]] = true;
                    }
                }
            }

            for (int i = 1; i < scc; i++) {
                for (int j = 1; j < scc; j++) {
                    if (sccMap[i][j]) {
                        ins[j]++;
                        outs[i]++;
                    }
                }
            }

            for (int i = 1; i < scc; i++) {
                if (ins[i] == 0) zeroIn++;
                if (outs[i] == 0) zeroOut++;
            }

            System.out.println(zeroIn);
            System.out.println(Math.max(zeroIn, zeroOut));
        }
    }

    private static void tarjan(int n) {
        stack[++head] = n;
        index[n] = idx++;
        lowIndex[n] = index[n];
        for (int i = 1; i < NMAX; i++) {
            if (map[n][i]) {
                if (index[i] == 0) {
                    tarjan(i);
                    lowIndex[n] = Math.min(lowIndex[n], lowIndex[i]);
                } else if (sccNo[i] == 0) {
                    lowIndex[n] = Math.min(lowIndex[n], index[i]);
                }
            }
        }
        if (lowIndex[n] == index[n]) {
            int nn = -1;
            while (nn != n) {
                nn = stack[head--];
                sccNo[nn] = scc;
            }
            scc++;
        }
    }
}
