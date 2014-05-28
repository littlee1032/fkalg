import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static boolean check(boolean[] occ, String code) {
        int left = 0;
        int right = 0;
        int index = 0;
        boolean myParent = false;
        for (int i = 0; i < code.length(); i++) {
            left = index * 2 + 1;
            right = left + 1;
            int parent = index;
            if (!myParent && occ[parent] && !occ[left] && !occ[right]) {
                return false;
            }
            if (code.charAt(i) == '0') {
                if (!occ[left]) {
                    myParent = true;
                    occ[left] = true;
                }
                index = left;
            } else {
                if (!occ[right]) {
                    myParent = true;
                    occ[right] = true;
                }
                index = right;
            }
        }
        if (!myParent) {
            return false;
        } else {
            left = index * 2 + 1;
            right = left + 1;
            if (occ[left] || occ[right]) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nMax = 5000;
        boolean[] occ = new boolean[nMax];
        boolean check = true;
        int caseNum = 1;
        Arrays.fill(occ, false);
        while (scan.hasNext()) {
            String code = scan.nextLine();
            if ("9".equals(code)) {
                if (check) {
                    System.out.println("Set " + caseNum + " is immediately decodable");
                }
                Arrays.fill(occ, false);
                caseNum++;
                check = true;
            } else if (check) {
                //System.out.println("Check " + code);
                check = check(occ, code);
                //for (int i = 0; i < 20; i++) {
                //    System.out.print((occ[i] ? 1 : 0) + ", ");
                //}
                //System.out.println();
                if (!check) {
                    //System.out.println("Check " + code + " fail");
                    System.out.println("Set " + caseNum + " is not immediately decodable");
                }
            }
        }
    }
}
