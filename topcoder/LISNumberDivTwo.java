
public class LISNumberDivTwo {
    public int calculate(int[] seq) {
        int ret = 0;
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] <= prev) {
                ret++;
            }
            prev = seq[i];
        }
        ret++;
        return ret;
    }
    
    public static void main(String[] args) {
//        int[] test = new int[]{1, 4, 4, 2, 6, 3};
        int[] test = new int[]{1, 1, 9, 9, 2, 2, 3, 3};
        System.out.println(new LISNumberDivTwo().calculate(test));
    }
}
