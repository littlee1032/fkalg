import java.util.Arrays;

public class LittleElephantAndBooks {
    public int getNumber(int[] pages, int number) {
        Arrays.sort(pages);
        int ret = 0;
        for (int i = 0; i < number; i++) {
            ret += pages[i];
        }
        return ret - pages[number - 1] + pages[number];
    }
}
