import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] r = new int[16];
        int[] g = new int[16];
        int[] b = new int[16];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 16; i++) {
            r[i] = scan.nextInt();
            g[i] = scan.nextInt();
            b[i] = scan.nextInt();
        }
        int rr = scan.nextInt();
        int gg = scan.nextInt();
        int bb = scan.nextInt();
        while (rr != -1 && gg != -1 && bb != -1) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int i = 0; i < 16; i++) {
                int rDiff = r[i] - rr;
                int gDiff = g[i] - gg;
                int bDiff = b[i] - bb;
                int current = rDiff * rDiff + gDiff * gDiff + bDiff * bDiff;
                if (current < min) {
                    minIdx = i;
                    min = current;
                }
            }
            System.out.println("("+rr+","+gg+","+bb+")" + " maps to " + "("+r[minIdx]+","+g[minIdx]+","+b[minIdx]+")");
            rr = scan.nextInt();
            gg = scan.nextInt();
            bb = scan.nextInt();
        }
    }
}
