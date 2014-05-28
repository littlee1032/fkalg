import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int c = scan.nextInt();

        char[] lamps = new char[n];
        Arrays.fill(lamps, '1');
        List<Integer> ons = new ArrayList<Integer>();
        List<Integer> offs = new ArrayList<Integer>();

        int on = scan.nextInt();
        while (on != -1) {
            ons.add(on);
            on = scan.nextInt();
        }
        int off = scan.nextInt();
        while (off != -1) {
            offs.add(off);
            off = scan.nextInt();
        }

        List<String> results = new ArrayList<String>();
        int press = 0;
        for (int i = 0; i < 2; i++) {
            if (i != 0) {for (int j = 0; j < n; j++) switchLamps(lamps, j); press++;}

            for (int j = 0; j < 2; j++) {
                if (j != 0) {for (int k = 0; k < n; k+=2) switchLamps(lamps, k); press++;}

                for (int k = 0; k < 2; k++) {
                    if (k != 0) {
                        for (int l = 1; l < n; l+=2) switchLamps(lamps, l);
                        press++;
                    }

                    for (int m = 0; m < 2; m++) {
                        if (m != 0) {
                            for (int mm = 0; mm < n; mm+=3) switchLamps(lamps, mm);
                            press++;
                        }
                        boolean correct = true;
                        if (press > c || ((c - press) % 2 != 0)) correct = false;
                        if (correct) {
                            for (Integer on_ : ons)
                                if (lamps[on_-1] != '1') {
                                    correct = false;
                                    break;
                                }
                        }
                        if (correct) {
                            for (Integer off_ : offs)
                                if (lamps[off_-1] != '0') {
                                    correct = false;
                                    break;
                                }
                        }

                        if (correct) {
                            String candidate = new String(lamps);
                            if (!results.contains(candidate)) results.add(candidate);
                        }

                        if (m != 0) {for (int mm = 0; mm < n; mm+=3) switchLamps(lamps, mm); press--;}
                    }

                    if (k != 0) {for (int l = 1; l < n; l+=2) switchLamps(lamps, l); press--;}
                }

                if (j != 0) {for (int k = 0; k < n; k+=2) switchLamps(lamps, k); press--;}
            }


            if (i != 0) {for (int j = 0; j < n; j++) switchLamps(lamps, j); press--;}
        }

        Collections.sort(results);
        for (String r : results) {
            System.out.println(r);
        }
    }

    private static void switchLamps(char[] lamps, int idx) {
        if (lamps[idx] == '1') lamps[idx] = '0';
        else lamps[idx] = '1';
    }
}
