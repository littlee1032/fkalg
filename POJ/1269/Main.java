import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("INTERSECTING LINES OUTPUT");
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int[] coords = new int[8];
            for (int j = 0; j < 8; j++) coords[j] = scan.nextInt();
            solve(coords);
        }
        System.out.println("END OF OUTPUT");
    }

    private static int crossProduct(int[] a, int[] b, int[]c) {
        return (c[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (c[1] - a[1]);
    }

    private static boolean isParalle(int[] a, int[] b, int[]c, int[]d) {
        return (a[0] - b[0]) * (c[1] - d[1]) == (a[1] - b[1]) * (c[0] - d[0]);
    }

    private static double[] solve(int[] a, int[] b, int[]c, int[] d) {
        double a1 = (a[1] - b[1]) * 1.0d;
        double b1 = (b[0] - a[0]) * 1.0d;
        double c1 = (a[0]*b[1] - b[0]*a[1]) * 1.0d;
        double a2 = (c[1] - d[1]) * 1.0d;
        double b2 = (d[0] - c[0]) * 1.0d;
        double c2 = (c[0]*d[1] - d[0]*c[1]) * 1.0d;
        double x = (b1*c2 - b2*c1)/(a1*b2 - a2*b1);
        double y = (a2*c1 - a1*c2)/(a1*b2 - a2*b1);
        return new double[]{x, y};
    }

    private static void solve(int[] coords) {
        int[] a = new int[]{coords[0], coords[1]};
        int[] b = new int[]{coords[2], coords[3]};
        int[] c = new int[]{coords[4], coords[5]};
        int[] d = new int[]{coords[6], coords[7]};

        if (isParalle(a, b, c, d)) {
            if (crossProduct(a, b, c) == 0) {
                System.out.println("LINE");
            } else {
                System.out.println("NONE");
            }
        } else {
            double[] point = solve(a, b, c, d);
            System.out.println(String.format("POINT %.2f %.2f", point[0], point[1]));
        }
    }
}
