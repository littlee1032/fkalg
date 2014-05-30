import java.util.Arrays;


public class FlippingBitsDiv2 {
    public int getmin(String[] s, int m) {
        String total = "";
        for (String as : s) {
            total += as;
        }
        char[] array = total.toCharArray();
        int n = array.length;
        int zeroNum = 0;
        for (int i = 0; i < n; i++)
            if (array[i] == '0') zeroNum++;
        if (zeroNum == 0) return 0;
        if (zeroNum == 1) return 1;
        
        // dp
        int size = n / m;
        int[] beZero = new int[size];
        int[] beOne = new int[size];
        int[] forwardZero = new int[size];
        int[] forwardOne = new int[size];
        int[] backwardZero = new int[size];
        int[] backwardOne = new int[size];
        Arrays.fill(forwardZero, n);
        Arrays.fill(forwardOne, n);
        Arrays.fill(backwardZero, n);
        Arrays.fill(backwardOne, n);
        
        for (int i = 0; i < size; i++) {
            int idx = m * i;
            int zero = 0;
            for (int j = idx; j < idx + m; j++) {
                if (array[j] == '0') zero++;
            }
            beZero[i] = m - zero;
            beOne[i] = zero;
        }

        int[] spIdx = new int[] { 0, size - 1 };
        for (int i = 0; i < spIdx.length; i++) {
            int idx = spIdx[i];
            if (beZero[idx] > m / 2) {
                beZero[idx] = Math.min(beZero[idx], beOne[idx] + 1);
            } else {
                beOne[idx] = Math.min(beOne[idx], beZero[idx] + 1);
            }
        }

        forwardZero[0] = beZero[0];
        forwardOne[0] = beOne[0];
        backwardZero[size - 1] = beZero[size - 1];
        backwardOne[size - 1] = beOne[size - 1];
        
        for (int i = 1; i < size; i++) {
            forwardZero[i] = Math.min(forwardZero[i - 1] + beZero[i], forwardOne[i - 1] + beOne[i] + 1);
            forwardOne[i] = Math.min(forwardOne[i - 1] + beOne[i], forwardZero[i - 1] + beZero[i] + 1);
        }
        for (int i = size - 2; i >= 0; i--) {
            backwardZero[i] = Math.min(backwardZero[i + 1] + beZero[i], backwardOne[i + 1] + beOne[i] + 1);
            backwardOne[i] = Math.min(backwardOne[i + 1] + beOne[i], backwardZero[i + 1] + beZero[i] + 1);
        }
        
        int ret = Math.min(forwardOne[size - 1], backwardOne[0]);
        for (int i = 0; i < size - 1; i++) {
            ret = Math.min(ret, forwardOne[i] + backwardOne[i + 1]);
            ret = Math.min(ret, forwardZero[i] + backwardZero[i + 1] + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        FlippingBitsDiv2 f = new FlippingBitsDiv2();
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
