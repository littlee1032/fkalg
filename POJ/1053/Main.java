import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> tableau = new ArrayList<String>();
        List<String> sets = new ArrayList<String>();

        while (true) {
            for (int counter = 0; counter < 12; counter++) {
                tableau.add(scan.nextLine());
            }
            System.out.print("CARDS:  ");
            for (int i = 0; i < tableau.size(); i++) {
                System.out.print(tableau.get(i));
                if (i != tableau.size()) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            System.out.print("SETS:   ");
            for (int i = 0; i < tableau.size(); i++) {
                for (int j = i + 1; j < tableau.size(); j++) {
                    for (int k = j + 1; k < tableau.size(); k++) {
                        String cmp1 = tableau.get(i);
                        String cmp2 = tableau.get(j);
                        String cmp3 = tableau.get(k);

                        boolean found = true;
                        for (int idx = 0; idx < 4; idx++) {
                            char tmp1 = cmp1.charAt(idx);
                            char tmp2 = cmp2.charAt(idx);
                            char tmp3 = cmp3.charAt(idx);

                            if ((tmp1 == tmp2 && tmp2 == tmp3) || (tmp1 != tmp2 && tmp2 != tmp3 && tmp3 != tmp1)) {
                                continue;
                            } else {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            sets.add(cmp1);
                            sets.add(cmp2);
                            sets.add(cmp3);
                        }
                    }
                }
            }
            if (sets.isEmpty()) {
                System.out.println("*** None Found ***");
            } else {
                int count = 1;
                for (int i = 0; i < sets.size();) {
                    if (count != 1) {
                        System.out.print("        ");
                    }
                    System.out.print(count + ".  ");
                    count++;
                    System.out.print(sets.get(i) + " ");
                    System.out.print(sets.get(i + 1) + " ");
                    System.out.println(sets.get(i + 2));
                    i += 3;
                }
            }
            System.out.println();
            tableau.clear();
            sets.clear();

            try {
                scan.nextLine();
            } catch (Exception e) {
                break;
            }
        }
    }
}
