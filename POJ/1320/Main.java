import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        long n = 2;
        while (count < 10) {
            long target = (n+1)*n/2;
            long sqrt = (long)Math.sqrt(target);
            if (sqrt * sqrt == target) {
                print(sqrt, n);
                count++;
            }
            n++;
        }
    }

    private static void print(long m , long n) {
        System.out.println(String.format("%10d %10d", m, n));
    }
}
