import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] map = new int[N];
        int zeroNum = 0;
        int oneNum = 0;
        int idx = 0;
        int rIdx = N - 1;
        for (int i = 0; i < N; i++) {
            int read = scan.nextInt();
            if (read == 1) {
                oneNum++;
                map[rIdx--] = i;
            }
            else {
                zeroNum++;
                map[idx++] = i;
            }
        }
        Arrays.sort(map, rIdx + 1, N);
        System.out.print(zeroNum >= 1 ? 0 : 1);
        int mapIdx = 0;
        for (int i = 1; i < N; i++) {
            idx = map[mapIdx];
            System.out.print(" ");
            if (idx >= zeroNum) System.out.print("1");
            else System.out.print("0");
            mapIdx = idx;
        }
        System.out.println();
    }
}
