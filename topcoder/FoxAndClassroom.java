
public class FoxAndClassroom {
    private int gcd(int a, int b) {
        if (b == 1) return 1;
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public String ableTo(int n, int m) {
        if (gcd(n, m) == 1) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }
}
