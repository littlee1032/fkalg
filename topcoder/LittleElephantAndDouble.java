
public class LittleElephantAndDouble {
    public long gcd(long a, long b) {
        if (b == 1) return 1;
        if (a < b) return gcd(b, a);
        if (a % b == 0) return b;
        return gcd(b, (a % b));
    }
    
    public boolean twoReach(long a, long b) {
        if (a < b) return twoReach(b, a);
        if (a % b != 0) return false;
        long num = a / b;
        while (num > 1) {
            if (num % 2 != 0) return false;
            num /= 2;
        }
        return true;
    }
    
    public String getAnswer(int[] a) {
        int len = a.length;
        
        if (len == 1) return "YES";
        
        long num = a[0];
        
        for (int i = 1; i < len; i++) {
            int n = a[i];
            long gcd = gcd(n, num);
            long aa = num / gcd;
            long bb = n / gcd;
            if (!twoReach(aa, bb)) return "NO";
            num = aa * bb * gcd;
        }
        return "YES";
    }
}
