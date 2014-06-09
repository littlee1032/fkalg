import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        int n = scan.nextInt();
        while (n-- > 0) {
            pq.clear();
            int m = scan.nextInt();
            while (m-- > 0) pq.offer(scan.nextInt());
            int ans = 0;
            if (pq.size() == 1) {
                System.out.println(pq.poll());
            } else {
                while (!pq.isEmpty()) {
                    int sum = pq.poll();
                    sum += pq.poll();
                    ans += sum;
                    if (!pq.isEmpty()) pq.offer(sum);
                }
                System.out.println(ans);
            }
        }
    }
}
