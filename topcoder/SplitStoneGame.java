
public class SplitStoneGame {
    private static final String WIN = "WIN";
    private static final String LOSE = "LOSE";
    public String winOrLose(int[] number) {
        int count = 0;
        for (int i = 0; i < number.length; i++) {
            if (number[i] > 1) count++;
        }
        if (count == 0) return LOSE;
        else {
            int round = number.length - 2;
            if (round % 2 == 1) return WIN;
            else return LOSE;
        }
    }
}
