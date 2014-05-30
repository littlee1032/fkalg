import java.util.Arrays;

public class SurveillanceSystem {
    private static final boolean DEBUG = true;
    
    private static void print(boolean[] c) {
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] ? 1 : 0);
            System.out.print(" ");
        }
        System.out.println();
    }

    private static void print(int[] c) {
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
    }
    
    private void mark(boolean[] c, int count, int start, int N, int L, int picks, int[] holder, boolean[] isPicked, int[] total) {
        if (DEBUG) {
            System.out.println(count + " " + picks);
            System.out.println("isPicked:");
            print(isPicked);
        }
        if (picks == 0) {
            for (int i = 0; i < N; i++) {
                if (isPicked[i]) {
                    holder[i]++;
                }
            }
            if (DEBUG) {
                System.out.println("Holder: ");
                print(holder);
                System.out.println();
            }
            total[0]++;
            return;
        }
        boolean[] myPicked = new boolean[N];
        
        for (int j = start; j <= N - L; j++) {
            int thisCount = 0;
            for (int jj = j; jj < j + L; jj++) {
                if (c[jj]) thisCount++;
            }
            if (thisCount == count) {
                System.arraycopy(isPicked, 0, myPicked, 0, N);
                for (int jj = j; jj < j + L; jj++) {
                    myPicked[jj] = true;
                }
                mark(c, count, j + 1, N, L, picks - 1, holder, myPicked, total);
            }
        }
    }
    
    public String getContainerInfo(String containers, int[] reports, int L) {
        int N = containers.length();
        boolean[] c = new boolean[N];
        Arrays.fill(c, false);
        for (int i = 0; i < N; i++) {
            if (containers.charAt(i) == 'X') {
                c[i] = true;
            }
        }
        if (DEBUG) print(c);
        int[] arr = new int[N];
        Arrays.fill(arr, -1);
        Arrays.sort(reports);
        
        if (DEBUG) {
            System.out.println("reports: ");
            print(reports);
        }
        int prev = reports[0];
        int sameCount = 1;
        int[] holder = new int[N];
        boolean[] isPicked = new boolean[N];
        int[] total = new int[1];
        for (int i = 1; i < reports.length; i++) {
            if (reports[i] == prev) {
                sameCount++;
            } else {
                Arrays.fill(isPicked, false);
                Arrays.fill(holder, 0);
                total[0] = 0;
                mark(c, prev, 0, N, L, sameCount, holder, isPicked, total);
                int count = total[0];
//                int thisTotal = total[0];
//                int count = 1;
//                for (int j = 1; j <= sameCount; j++) {
//                    count *= (thisTotal - j + 1);
//                }
//                for (int j = 1; j <= sameCount; j++) {
//                    count /= j;
//                }
                for (int j = 0; j < N; j++) {
                    if (holder[j] == count) {
                        arr[j] = 2;
                    } else if (holder[j] != 0 && arr[j] == -1) {
                        arr[j] = 1;
                    }
                }

                prev = reports[i];
                sameCount = 1;
                if (DEBUG) {
                    System.out.println("total: " + total[0]);
                    System.out.println("arr: ");
                    print(arr);                  
                }
            }
        }
        Arrays.fill(isPicked, false);
        Arrays.fill(holder, 0);
        total[0] = 0;
        mark(c, prev, 0, N, L, sameCount, holder, isPicked, total);
        int count = total[0];
//      int thisTotal = total[0];
//      int count = 1;
//      for (int j = 1; j <= sameCount; j++) {
//          count *= (thisTotal - j + 1);
//      }
//      for (int j = 1; j <= sameCount; j++) {
//          count /= j;
//      }
        for (int j = 0; j < N; j++) {
            if (holder[j] == count) {
                arr[j] = 2;
            } else if (holder[j] != 0 && arr[j] == -1) {
                arr[j] = 1;
            }
        }
        if (DEBUG) {
            System.out.println("total: " + total[0]);
            System.out.println("arr: ");
            print(arr);            
        }
        String ret = "";
        for (int i=0; i < N; i++) {
            if (arr[i] == -1) {
                ret += '-';
            } else if (arr[i] == 1) {
                ret += '?';
            } else {
                ret += '+';
            }
        }
        return ret;
    }
    
    public static void main(String[] args) {
        String tst = "------X-XX-";
        int L = 5;
        int[] reports = new int[]{3, 0, 2, 0};
        System.out.println(new SurveillanceSystem().getContainerInfo(tst, reports, L));
    }
}
