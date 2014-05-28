import java.util.Scanner;

public class Main {
    private static final boolean DEBUG = false;

    private static void print(int gain, int loss) {
        double exact = gain * 1.0d / loss;
        int deno = 1;
        int lastNumer = (int)Math.round(gain * 1.0d / loss);
        int numerator = lastNumer;
        double diff = 5001;
        double prevDiff = 5001;
        while (deno <= loss) {
            double curDiff = Math.abs((numerator * 1.0d) / deno - exact);
            if (DEBUG) {
                System.out.println(numerator + " " + deno + " " + diff + " " + prevDiff + " " + curDiff);
            }
            if (curDiff < diff) {
                System.out.println(numerator + "/" + deno);
                lastNumer = numerator;
                if (curDiff == 0.0d) {
                    return;
                }
                prevDiff = diff;
                diff = curDiff;
                numerator++;
            } else if (prevDiff < curDiff) {
                prevDiff = 5001;
                numerator = lastNumer;
                deno++;
            } else {
                prevDiff = curDiff;
                numerator++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int gain = scan.nextInt();
            int loss = scan.nextInt();
            print(gain, loss);
            System.out.println();
        }
    }
}
