import java.util.Arrays;
public class GoodCompanyDivOne {
    public int minimumCost(int[] superior, int[] training) {
        int n = superior.length;
        int k = training.length;
        int[] childrenNo = new int[n];
        for (int i = 0; i < n; i++) {
            if (superior[i] < 0) continue;
            childrenNo[superior[i]]++;
            if (childrenNo[superior[i]] >= k) return -1;
        }
        
        Arrays.sort(training);
        int[] sum = new int[k];
        sum[0] = training[0];
        for (int i = 1; i < k; i++) {
            sum[i] = sum[i - 1] + training[i];
        }
        
        if (n == 1) return sum[1];
        System.out.println(Arrays.toString(childrenNo));
        System.out.println(Arrays.toString(sum));
        
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (childrenNo[i] > 0) {
                int c = childrenNo[i] + 1;
                ret += sum[c - 1];
                ret += (c - 1) * training[0] + training[1];
            }
        }
        
        return ret;
    }
    
    public static void main(String[] args) {
        int[] superior = new int[]{-1, 0, 0, 2, 2, 2, 1, 1, 6, 0, 5, 4, 11, 10, 3, 6, 11, 7, 0, 2, 13, 14, 2, 10, 9, 11, 22, 10, 3};
        int[] training = new int[]{4, 2, 6, 6, 8, 3, 3, 1, 1, 5, 8, 6, 8, 2, 4};
        System.out.println(new GoodCompanyDivOne().minimumCost(superior, training));
    }
}
