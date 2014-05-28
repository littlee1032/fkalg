import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final boolean DEBUG = false;
    private static final char[] alpha = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'};

    private static void judge(boolean[][] r, int N, List<String> prev) {
        List<String> sol = new ArrayList<String>();
        boolean[] tried = new boolean[N];
        Arrays.fill(tried, false);
        for (int i = 0; i < N; i++) {
            if (!tried[i]) {
                for (int j = i + 1; j < N; j++) {
                    if (!r[i][j] && !tried[j]) {
                        StringBuilder table = new StringBuilder();
                        table.append(alpha[i]);
                        table.append(alpha[j]);
                        tried[j] = true;
                        for (int k = j + 1; k < N; k++) {
                            if (!r[i][k] && !r[j][k]) {
                                table.append(alpha[k]);
                                tried[k] = true;
                            }
                        }
                        if (table.length() != 4) {
                            System.out.println("It is not possible to complete this schedule.");
                            return;
                        } else {
                            String tableStr = table.toString();
                            for (int k = 0; k < 4; k++) {
                                for (int l = k + 1; l < 4; l++) {
                                    char c = tableStr.charAt(k);
                                    char d = tableStr.charAt(l);
                                    r[c - 'A'][d - 'A'] = true;
                                    r[d - 'A'][c - 'A'] = true;
                                }
                            }
                            if (DEBUG) {
                                System.out.println("Found one table: " + tableStr);
                                print(r, N);
                            }
                            sol.add(tableStr);
                            table.setLength(0);
                            break;
                        }
                    }
                }
            }
        }

        // last round
        StringBuilder table = new StringBuilder();
        for (int i = 0; i < N; i++) {
            boolean self = false;
            for (int j = i + 1; j < N; j++) {
                if (!r[i][j]) {
                    if (!self) {
                        table.append(alpha[i]);
                        self = true;
                    }
                    table.append(alpha[j]);
                    if (table.length() == 4) {
                        String tableStr = table.toString();
                        for (int k = 0; k < 4; k++) {
                            for (int l = k + 1; l < 4; l++) {
                                char c = tableStr.charAt(k);
                                char d = tableStr.charAt(l);
                                r[c - 'A'][d - 'A'] = true;
                                r[d - 'A'][c - 'A'] = true;
                            }
                        }
                        sol.add(tableStr);
                        table.setLength(0);
                    }
                }
            }
        }

        for (String p : prev) {
            System.out.println(p);
        }

        for (int i = 0; i < sol.size(); i++) {
            System.out.print(sol.get(i));
            if ((i % 4) !=  3) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }

    private static void print(boolean[][] r, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(r[i][j] ? 1 : 0);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 16;
        boolean[][] r = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(r[i], false);
            r[i][i] = true;
        }
        String line = null;
        Scanner scan = new Scanner(System.in);
        List<String> prev = new ArrayList<String>();
        while (true) {
            prev.clear();
            for (int i = 0; i < 3; i++) {
                line = scan.nextLine();
                prev.add(line);
                String[] blocks = line.split(" ");
                for (String block : blocks) {
                    for (int j = 0; j < block.length(); j++) {
                        for (int k = j + 1; k < block.length(); k++) {
                            char c1 = block.charAt(j);
                            char c2 = block.charAt(k);
                            r[c1 - 'A'][c2 - 'A'] = true;
                            r[c2 - 'A'][c1 - 'A'] = true;
                        }
                    }
                }
            }
            if (DEBUG) {
                print(r, N);
            }
            judge(r, N, prev);
            System.out.println();

            try {
                line = scan.nextLine();
                for (int i = 0; i < N; i++) {
                    Arrays.fill(r[i], false);
                    r[i][i] = true;
                }
            } catch (Exception e) {
                break;
            }
        }
    }
}
