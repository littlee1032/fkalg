
public class GUMIAndSongsDiv2 {
    public static final int[] masks = new int[15];
    
    static {
        for (int i = 0; i < 15; i++) {
            masks[i] = 1 << i;
        }
    }
    
    public int maxSongs(int[] duration, int[] tone, int T) {
        int len = tone.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (tone[i] > tone[j]) {
                    int tmp = tone[i];
                    tone[i] = tone[j];
                    tone[j] = tmp;
                    tmp = duration[i];
                    duration[i] = duration[j];
                    duration[j] = tmp;
                }
            }
        }
        int max = 0;
        for (int i = 1; i < (1 << len); i++) {
            int t = 0;
            int lastTone = -1;
            int count = 0;
            for (int j = 0; j < len; j++) {
                if ((i & masks[j]) != 0) {
                    count++;
                    t += duration[j];
                    if (lastTone != -1) {
                        t += tone[j] - lastTone;
                    }
                    lastTone = tone[j];
                    if (t > T) break;
                }
            }
            if (t <= T && count > max) max = count;
        }
        return max;
    }
}
