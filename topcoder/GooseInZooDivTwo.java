import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GooseInZooDivTwo {
    public int count(String[] field, int dist) {
        int height = field.length;
        int width = field[0].length();
        int[][] count = new int[width][height];
        for (int i = 0; i < width; i++) {
            Arrays.fill(count[i], 0);
        }
        List<Set<Integer>> sets = new ArrayList<Set<Integer>>();
        for (int i = 0; i < field.length; i++) {
            String row = field[i];
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == 'v') {
                    count[j][i] = 1;
                }
            }
        }
        Set<Integer> checked = new HashSet<Integer>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (count[i][j] == 1) {
                    Set<Integer> current = null;
                    int num = j * width + i;
                    if (!checked.contains(num)) {
                        current = new HashSet<Integer>();
                        sets.add(current);
                        checked.add(num);
                        current.add(num);
                    } else {
                        for (int cur = 0; cur < sets.size(); cur++) {
                            current = sets.get(cur);
                            if (current.contains(num)) {
                                break;
                            }
                        }
                    }
                    for (int ii = i + 1; ii < width; ii++) {
                        for (int jj = j; jj < height; jj++) {
                            if (count[ii][jj] == 1) {
                                int newNum = jj * width + ii;
                                if (!checked.contains(newNum) && (Math.abs(i - ii) + Math.abs(j - jj)) <= dist) {
                                    current.add(newNum);
                                    checked.add(newNum);
                                }
                            }
                        }
                    }
                }
            }
        }
        int ret = 1;
        for (int i = 0; i < sets.size(); i++) {
            ret *= 2;
            if (i % 10 == 0) {
                ret %= 1000000007;
            }
        }
        return ret - 1;
    }
    
    public static void main(String[] args) {
        GooseInZooDivTwo test = new GooseInZooDivTwo();
        String[] s = new String[1];
        s[0] = "vvv";
        System.out.println(test.count(s, 1));
    }
}
