import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static int getSameCharNumForward(String cmp, String target) {
        int ret = 0;
        for (int i = 0; i < cmp.length() && i < target.length(); i++) {
            if (cmp.charAt(i) == target.charAt(i)) {
                ret++;
            } else {
                break;
            }
        }
        return ret;
    }

    private static int getSameCharNumBackward(String cmp, String target) {
        int ret = 0;
        for (int i = cmp.length() - 1, j = target.length() - 1; i >= 0 && j >= 0; i--, j--) {
            if (cmp.charAt(i) == target.charAt(j)) {
                ret++;
            } else {
                break;
            }
        }
        return ret;
    }

    public static boolean checkPass(String cmp, String target) {
        int len1 = cmp.length();
        int len2 = target.length();

        if (len1 == len2) {
            // replace
            int forward = getSameCharNumForward(cmp, target);
            int backward = getSameCharNumBackward(cmp.substring(forward), target.substring(forward));

            if (forward + backward + 1 == len1) {
                return true;
            } else {
                return false;
            }
        } else if (len1 == len2 + 1) {
            // insert
            int forward = getSameCharNumForward(cmp, target);
            int backward = getSameCharNumBackward(cmp.substring(forward), target.substring(forward));

            if (forward + backward == len2) {
                return true;
            } else {
                return false;
            }
        } else if (len1 == len2 - 1) {
            // delete
            int forward = getSameCharNumForward(cmp, target);
            int backward = getSameCharNumBackward(cmp.substring(forward), target.substring(forward));

            if (forward + backward == len1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Set<String> dictSet = new HashSet<String>();
        List<String> dictList = new ArrayList<String>();
        String line = scan.nextLine();
        while (!"#".equals(line)) {
            dictSet.add(line);
            dictList.add(line);
            line = scan.nextLine();
        }
        line = scan.nextLine();
        while (!"#".equals(line)) {
            if (dictSet.contains(line)) {
                System.out.println(line + " is correct");
            } else {
                System.out.print(line + ":");
                for (String cmp : dictList) {
                    if (checkPass(cmp, line)) {
                        System.out.print(" " + cmp);
                    }
                }
                System.out.println();
            }
            line = scan.nextLine();
        }
    }
}
