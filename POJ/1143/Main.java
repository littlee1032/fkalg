import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 21;
    public static final boolean[] candidates = new boolean[NMAX];
    public static final boolean[] snapshot = new boolean[NMAX];
    public static final boolean[] winner = new boolean[NMAX];
    public static final Set<Integer> winPositions = new HashSet<Integer>();
    public static final Set<Integer> losePositions = new HashSet<Integer>();

    private static int fromArray(boolean[] snapshot) {
        int ret = 0;
        for (int i = 2; i < NMAX; i++) {
            if (snapshot[i]) {
                ret |= 1 << (i - 2);
            }
        }
        return ret;
    }

    private static void restore(int s, boolean[] snapshot) {
        for (int i = 2; i < NMAX; i++) {
            if ((s & 1 << (i - 2)) != 0) snapshot[i] = true;
        }
    }

    public static void solve() {
        Arrays.fill(winner, false);
        boolean hasWinner = false;
        for (int i = 2; i < NMAX; i++) {
            if (candidates[i]) {
                System.arraycopy(candidates, 0, snapshot, 0, NMAX);
                if (amIWinner(snapshot, i, true)) {
                    winner[i] = true;
                    hasWinner = true;
                }
            }
        }

        if (hasWinner) {
            System.out.print("The winning moves are:");
            for (int i = 2; i < NMAX; i++) {
                if (winner[i]) System.out.print(" " + i);
            }
            System.out.println();
        } else {
            System.out.println("There's no winning move.");
        }
    }

    public static void regular(boolean[] snapshot) {
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int i = 2; i < NMAX; i++) {
                for (int j = i + 1; j < NMAX; j++) {
                    if (j % i == 0 && !snapshot[i] && snapshot[j]) {
                        snapshot[j] = false;
                        hasChanged = true;
                    }
                }
            }
            for (int i = 2; i < NMAX; i++) {
                for (int j = i + 1; j < NMAX - i; j++) {
                    if (!snapshot[i] && !snapshot[j] && snapshot[i + j]) {
                        snapshot[i + j] = false;
                        hasChanged = true;
                    }
                }
            }
        }
    }

    public static boolean amIWinner(boolean[] snapshot, int cand, boolean myTurn) {
        int current = fromArray(snapshot);
        if (DEBUG) {
            System.out.println("Trying candidate " + cand + " at pos: " + Integer.toString(current, 2) + "00" + " my turn? " + myTurn);
        }
        snapshot[cand] = false;
        int nextPos = fromArray(snapshot);
        boolean ret = false;
        if (winPositions.contains(nextPos)) {
            ret = !myTurn;
        } else if (losePositions.contains(nextPos)) {
            ret = myTurn;
        } else {
            regular(snapshot);
            nextPos = fromArray(snapshot);
            if (DEBUG) System.out.println("Next Pos is " + Integer.toString(nextPos, 2) + "00");
            if (winPositions.contains(nextPos)) {
                ret = !myTurn;
            } else if (losePositions.contains(nextPos)) {
                ret = myTurn;
            } else {
                boolean quick = false;
                ret = myTurn;
                for (int i = 2; i < NMAX; i++) {
                    if (snapshot[i]) {
                        if (myTurn !=  amIWinner(snapshot, i, !myTurn)) {
                            ret = !myTurn;
                            quick = true;
                            break;
                        }
                    }
                }
                if (quick) {
                    winPositions.add(nextPos);
                    if (DEBUG) System.out.println("adding " + Integer.toString(nextPos, 2) + "00 into win part.");
                } else {
                    if (DEBUG) System.out.println("adding " + Integer.toString(nextPos, 2)  + "00 into lose part.");
                    losePositions.add(nextPos);
                }
            }
        }
        restore(current, snapshot);
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int caseIdx = 0;
        losePositions.add(0);
        while (n > 0) {
            caseIdx++;
            System.out.println("Test Case #" + caseIdx);
            Arrays.fill(candidates, false);
            for (int i = 0; i < n; i++) {
                candidates[scan.nextInt()] = true;
            }
            regular(candidates);
            solve();
            System.out.println();
            n = scan.nextInt();
        }
    }
}
