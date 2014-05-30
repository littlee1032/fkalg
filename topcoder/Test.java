import java.util.Collections;
import java.util.List;


public class Test {
    public static void f(List<? extends Number> c) {}
    public static void ff(Number[] c){}
    
    public static void main(String[] args) {
        f(Collections.<Integer> emptyList());
        ff(new Integer[]{});
        
        System.out.println("".split(" ")[0]);
    }
}
