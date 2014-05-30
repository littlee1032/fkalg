
public class LittleElephantAndString {
    public int getNumber(String a, String b) {
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        
        int[] aHash = new int[26];
        int[] bHash = new int[26];
        
        int len = aa.length;
        
        for (int i = 0; i < len; i++) {
            aHash[aa[i] - 'A']++;
            bHash[bb[i] - 'A']++;
        }
        
        for (int i = 0; i < 26; i++) {
            if (aHash[i] != bHash[i]) return -1;
        }
        
        for (int i = len; i > 0; i--) {
            for (int start = len - i; start + i - 1 < len; start++) {
                int aIdx = 0;
                int bIdx = start;
                for (; bIdx < len && aIdx < len;) {
                    if (aa[aIdx] != bb[bIdx]) aIdx++;
                    else {
                        bIdx++;
                        aIdx++;
                    }
                }
                if (bIdx == len) {
                    return len - i;
                }
            }
        }
        
        return len - 1;
    }
}
