import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class FlippingBitsDiv2Naive {
    public void flip(char[] arr, int idx) {
        if (arr[idx] == '1') arr[idx] = '0';
        else arr[idx] = '1';
    }

    public List<String> generate(String s, int m) {
        List<String> ret = new LinkedList<String>();
        char[] arr = s.toCharArray();
        char[] template = new char[arr.length];
        System.arraycopy(arr, 0, template, 0, arr.length);
        // flip one
        for (int i = 0; i < arr.length; i++) {
            flip(arr, i);
            ret.add(new String(arr));
            flip(arr, i);
        }
        // flip first k*m
        int idx = 0;
        for (int k = 1; k * m <= s.length(); k++) {
            for (int i = 0; i < m; i++) {
                flip(arr, idx++);
            }
            ret.add(new String(arr));
        }
        System.arraycopy(template, 0, arr, 0, arr.length);
        // flip last k*m
        idx = s.length() - 1;
        for (int k = 1; k * m <= s.length(); k++) {
            for (int i = 0; i < m; i++) {
                flip(arr, idx--);
            }
            ret.add(new String(arr));
        }
        
        return ret;
    }

    public int getmin(String[] s, int m) {
        String total = "";
        for (String as : s) {
            total += as;
        }
        int zeroNum = 0;
        for (int i = 0; i < total.length(); i++) {
            if (total.charAt(i) == '0') zeroNum++;
        }
        if (zeroNum == 0) return 0;
        if (zeroNum == 1) return 1;
        Set<String> vis = new HashSet<String>();
        Set<String> nextRound = new HashSet<String>();
        String ones = "";
        for (int i = 0; i < total.length(); i++) {
            ones += "1";
        }
        nextRound.add(ones);
        int ret = 0;
        List<String> cands = new LinkedList<String>();
        while (!nextRound.isEmpty() && ret <= zeroNum) {
            System.out.println("NR: " + nextRound.size());
            ret++;
            for (String nr : nextRound) {
                if (!vis.contains(nr)) {
                    vis.add(nr);
                    cands.addAll(generate(nr, m));
                }
            }
            nextRound.clear();
            nextRound.addAll(cands);
            if (nextRound.contains(total)) return ret;
            cands.clear();
        }
        return ret;
    }
    
    public static void main(String[] args) {
        FlippingBitsDiv2Naive f = new FlippingBitsDiv2Naive();
        String[][] strs = new String[5][];
        int[] ms = new int[5];
        strs[0] = new String[] {"00111000"};
        ms[0] = 1;
        
        strs[1] = new String[] {"00111000"};
        ms[1] = 2;
        
        strs[2] = new String[] {"111111"};
        ms[2] = 3;
        
        strs[3] = new String[] {"00100"};
        ms[3] = 5;
        
        strs[4] = new String[] {"00010","11010110","1010111","110001010","0110001100"
                ,"000110110","011010101","00","111","100"};
        ms[4] = 13;
        
        for (int i = 0; i < 5; i++) {
            System.out.print("case #" + i + ":");
            System.out.println(f.getmin(strs[i], ms[i]));
        }
    }
}
