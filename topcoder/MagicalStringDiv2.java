public class MagicalStringDiv2 {
    public int minimalMoves(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int ret = 0;
        for (int i = 0; i < len / 2; i++)
            if (arr[i] != '>') ret++;
        for (int i = len / 2; i < len; i++)
            if (arr[i] != '<') ret++;
        return ret;
    }
}
