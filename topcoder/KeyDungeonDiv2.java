
public class KeyDungeonDiv2 {
    public int countDoors(int[] doorR, int[] doorG, int[] keys) {
        int len = doorR.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int gl = doorR[i] - keys[0] > 0 ? doorR[i] - keys[0] : 0;
            int rl = doorG[i] - keys[1] > 0 ? doorG[i] - keys[1] : 0;
            if (gl + rl <= keys[2]) {
                count++;
            }
        }
        return count;
    }
}
