public class GearsDiv2 {
    public int getmin(String Directions) {
        char[] arr = Directions.toCharArray();
        int len = arr.length;
        int lastNum = 1;
        int ret = 0;
        for (int i = 1; i <= len - 1; i ++) {
            if (arr[i] == arr[i - 1]) {
                lastNum++;
            } else {
                ret += lastNum / 2;
                lastNum = 1;
            }
        }
        ret += lastNum / 2;
        // deal with the rotate;
        if (arr[0] == arr[len - 1]) {
            int k = 0;
            for (int i = 0; i < len; i++) {
                if (arr[i] == arr[len - 1]) k++;
                else break;
            }
            if (k % 2 == 1 && lastNum % 2 == 1) ret++;
        }
        
        return ret;
    }
}
