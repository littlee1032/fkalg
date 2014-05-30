public class FoxAndWord {
    public int howManyPairs(String[] words) {
        int ret = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                String str1 = words[i];
                String str2 = words[j];
                if (str1.length() != str2.length()) continue;
                if ((str1+str1).contains(str2)) ret++;
            }
        }
        return ret;
    }
}
