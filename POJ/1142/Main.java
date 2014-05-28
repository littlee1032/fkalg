import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = true;

    public static int calValue(int num) {
        int ret = 0;
        while (num > 0) {
            ret += num % 10;
            num /= 10;
        }
        return ret;
    }

    public static boolean isSmithNum(int num) {
        if (num == 2) return false;
        int target = calValue(num);
        int m = 0;
        int nn = num;
        for (int i = 2; i <= Math.round(Math.sqrt(num) + 0.5);) {
            if (nn % i == 0) {
                m += calValue(i);
                nn /= i;
            } else if (nn > i * i) {
                i++;
            } else {
                if (nn != num && nn != 1) m += calValue(nn);
                break;
            }
        }
        return m == target;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            int num = n + 1;
            while(true) {
                if (isSmithNum(num)) {
                    System.out.println(num);
                    break;
                } else {
                    num++;
                }
            }
            n = scan.nextInt();
        }
    }
}
