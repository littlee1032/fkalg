import java.util.HashSet;
import java.util.Set;

public class EelAndRabbit {
    public int getmax(int[] l, int[] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = i + 1; j < t.length; j++) {
                if (t[i] > t[j] || (t[i] == t[j] && l[i] > l[j])) {
                    int tmp = t[i];
                    t[i] = t[j];
                    t[j] = tmp;
                    tmp = l[i];
                    l[i] = l[j];
                    l[j] = tmp;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        Set<Integer> eel = new HashSet<Integer>();
        Set<Integer> maxEel = new HashSet<Integer>();
        for (int i = 0; i < t.length; i++) {
            int count = 1;
            eel.add(i);
            for (int j = 0; j < i; j++) {
                if (t[j] + l[j] >= t[i]) {
                    count++;
                    eel.add(j);
                }
            }
            if (count > max) {
                max = count;
                maxEel.clear();
                maxEel.addAll(eel);
            }
            eel.clear();
        }
        int ret = max;
        max = 0;
        for (int i = 0; i < t.length; i++) {
            if (maxEel.contains(i))
                continue;
            int count = 1;
            for (int j = 0; j < i; j++) {
                if (t[j] + l[j] >= t[i] && !maxEel.contains(j)) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        }
        ret += max;
        return ret;
    }
    
    public static void main(String[] args) {
        EelAndRabbit r = new EelAndRabbit();
        int[] l = {8, 2, 1, 10, 8, 6, 3, 1, 2, 5};
        int[] t = {17, 27, 26, 11, 1, 27, 23, 12, 11, 13};
        System.out.println(r.getmax(l,  t));
    }
}
