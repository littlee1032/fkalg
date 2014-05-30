public class PowerOfThreeEasy {
    private static String OK = "Possible";
    private static String NOK = "Impossible";

    public String ableToGet(int x, int y) {
        if (x == 0 && y == 0) return OK;
        while (x > 0 && y > 0) {
            int xm = x % 3;
            int ym = y % 3;
            if (xm == 2 || ym == 2 || xm == ym) return NOK;
            x /= 3;
            y /= 3;
        }
        int remain = x > 0 ? x : y;
        while (remain > 0) {
            int rm = remain % 3;
            if (rm != 1) return NOK;
            remain /= 3;
        }
        return OK;
    }
}
