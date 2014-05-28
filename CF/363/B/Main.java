import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        scan.nextLine();
        String[] tmp = scan.nextLine().trim().split(" ");
        int[] fences = new int[n];
        for (int i = 0; i < n; i++) {
            fences[i] = Integer.valueOf(tmp[i]);
        }

        int minIdx = 1;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += fences[i];
        }

        int mSum = sum;
        for (int i = k, j = 0; i < n; i++, j++) {
            mSum -= fences[j];
            mSum += fences[i];
            if (mSum < sum) {
                sum = mSum;
                minIdx = j + 2;
            }
        }
        System.out.println(minIdx);
    }
}
