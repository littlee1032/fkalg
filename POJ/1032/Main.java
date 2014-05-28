import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int sum = 0;
        int prev = 0;
        int ii = 0;
        Set<Integer> delegates = new TreeSet<Integer>();
        for (int i = 2; i < 1000; i++) {
            prev = sum;
            sum += i;
            if (sum <= number) {
                delegates.add(i);
                ii = i;
            } else {
                break;
            }
        }
        if (prev != number) {
            int k = ii;
            if (number  - prev <= k - 1) {
                delegates.add(k+1);
                delegates.remove(k + 1 + prev - number);
            } else {
                delegates.remove(2);
                delegates.add(k+2);
            }
        }
        Integer[] arr = delegates.toArray(new Integer[]{});
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length) {
                System.out.print(" ");
            }
        }
    }
}
