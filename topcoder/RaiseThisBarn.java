
public class RaiseThisBarn {
    public int calc(String str) {
        int cowNum = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == 'c') cowNum++;
        }
        if (cowNum % 2 == 1) return 0;

        int ret = 0;
        for (int i = 0; i < len - 1; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j <=i; j++) {
                if (str.charAt(j) == 'c') left++;
            }
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(j) == 'c') right++;
            }
            if (left == right) ret++;
        }
        return ret;
    }
}
