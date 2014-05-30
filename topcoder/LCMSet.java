import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LCMSet {
    private final static String EQU = "Equal";
    private final static String NEQU = "Not equal";
    
    private long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public String equal(int[] A, int[] B) {
        PriorityQueue<Long> q1 = new PriorityQueue<Long>();
        PriorityQueue<Long> q2 = new PriorityQueue<Long>();
        for (int a : A)
            q1.offer((long) a);
        for (int b : B)
            q2.offer((long) b);
        
        List<Long> remove = new LinkedList<Long>();
        List<Long> addback = new LinkedList<Long>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            long a = q1.poll();
            long b = q2.poll();
            if (a != b)
                return NEQU;
            remove.clear();
            addback.clear();
            for (Long aa : q1) {
                long gcd = gcd(aa, a);
                remove.add(aa);
                if (aa / gcd != 1)
                    addback.add(aa / gcd);
            }
            q1.removeAll(remove);
            q1.addAll(addback);
            remove.clear();
            addback.clear();
            for (Long bb : q2) {
                long gcd = gcd(bb, b);
                remove.add(bb);
                if (bb / gcd != 1)
                    addback.add(bb / gcd);
            }
            q2.removeAll(remove);
            q2.addAll(addback);
        }

        if (q1.isEmpty() && q2.isEmpty())
            return EQU;
        else
            return NEQU;
    }
}
