import java.util.Arrays;

public class PackingBallsDiv2 {
    public int minPacks(int r, int g, int b) {
        int[] nums = new int[]{r, g, b};
        Arrays.sort(nums);
        int ret = nums[0];
        for (int i = 0; i < 3; i++) nums[i] -= ret;
        ret += nums[1] / 3;
        nums[1] = nums[1] % 3;
        ret += nums[2] / 3;
        nums[2] = nums[2] % 3;
        if (nums[1] == nums[2]) return ret + nums[1];
        else {
            if (nums[1] * nums[2] == 0) return ret + 1;
            else return ret + 2;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new PackingBallsDiv2().minPacks(4, 2, 4));
    }
}
