import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        int count = 0;
        boolean inc2 = false;
        for (int i = 0; i < n; i++) {
            if (arr[i] == i) count++;
            else if (i == arr[arr[i]]) inc2 = true;
        }

        if (count != n) {
            if (inc2) count += 2;
            else count += 1;
        }
        System.out.println(count);
    }
}
