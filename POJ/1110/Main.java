import java.util.Scanner;

public class Main {
    private static final boolean DEBUG = false;

    private static final int MAXROW = 10;
    private static final int MAXCOL = 79;

    private static final char[][] picture = new char[MAXROW][MAXCOL];

    private static char pixel(int symbol, int w, int r, int c) {
        return picture[r][w * symbol + c];
    }

    private static boolean solve(int r, int w, int symbol, int symbolNum) {
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < w; col++) {
                if ('.' != pixel(symbol, w, row, col)) {
                    boolean unique = true;
                    for (int s = 0; s < symbolNum; s++) {
                        if (s == symbol) continue;
                        if ('.' != pixel(s, w, row, col)) {
                            unique = false;
                            break;
                        }
                    }
                    if (unique) {
                        picture[row][w * symbol + col] = '#';
                        return true;
                    }
                }
            }
        }

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < w; col++) {
                if ('.' != pixel(symbol, w, row, col)) {
                    for (int row2 = row; row2 < r; row2++) {
                        for (int col2 = 0; col2 < w; col2++) {
                            if (row == row2 && col2 < col + 1) continue;
                            if ('.' != pixel(symbol, w, row2, col2)) {
                                boolean unique = true;
                                for (int s = 0; s < symbolNum; s++) {
                                    if (s == symbol) continue;
                                    if ('.' != pixel(s, w, row, col) && '.' != pixel(s, w, row2, col2)) {
                                        unique = false;
                                        break;
                                    }
                                }
                                if (unique) {
                                    picture[row][w * symbol + col] = '#';
                                    picture[row2][w * symbol + col2] = '#';
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int r = scan.nextInt();
        int c = scan.nextInt();
        int counter = 0;
        while (n + r + c > 0) {
            counter++;
            scan.nextLine();
            for (int i = 0; i < r; i++) {
                String line = scan.nextLine().trim();
                int idx = 0;
                for (int j = 0; j < line.length(); j++) {
                    char cc = line.charAt(j);
                    if (cc != ' ') {
                        picture[i][idx++] = cc;
                    }
                }
            }
            System.out.println("Test " + counter);
            boolean found = true;
            for (int i = 0; i < n; i++) {
                found = solve(r, c, i, n);
                if (!found) break;
            }
            if (!found) {
                System.out.println("impossible");
            } else {
                for (int i = 0; i < r; i++) {
                    int idx = 0;
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < c; k++) {
                            System.out.print(picture[i][idx++]);
                        }
                        if (j != n - 1) {
                            System.out.print(' ');
                        }
                    }
                    System.out.println();
                }
            }

            n = scan.nextInt();
            r = scan.nextInt();
            c = scan.nextInt();
        }
    }
}
