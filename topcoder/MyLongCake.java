public class MyLongCake {
    private int gcd(int a, int b) {
        if (b == 1) return 1;
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public int cut(int n) {
        int ret = 0;
        for (int i = 1; i < n; i++) {
            if (gcd(n, i) != 1) ret++;
        }
        return ret + 1;
    }
    
    public static void main(String[] args) {
        System.out.println(new MyLongCake().cut(100000));
    }
}
