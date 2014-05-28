import java.util.Scanner;

public class Main {
    private static int dfs(int pLeft, int step, int earn, int curMax, int[][] orders, int mark) {
        int orderNum = orders.length;
        if (step >= orders.length) {
            if (earn > curMax)
                return earn;
            else
                return curMax;
        }
        int earnLeft = 0;
        for (int i = step; i < orderNum; i++) {
            earnLeft += orders[i][3];
        }
        if (earn + earnLeft <= curMax) {
            return curMax;
        }
        // passenger off
        for (int i = 0; i < step; i++) {
            if ((mark & (1 << i)) != 0 && orders[i][1] <= orders[step][0]) {
                pLeft += orders[i][2];
                mark ^= (1 << i);
            }
        }
        // use step
        if (pLeft >= orders[step][2]) {
            int newMark = mark | (1 << step);
            int thisMax = dfs(pLeft - orders[step][2], step + 1, earn + orders[step][3], curMax, orders, newMark);
            if (thisMax > curMax)
                curMax = thisMax;
        }
        // not use this step
        int thisMax = dfs(pLeft, step + 1, earn, curMax, orders, mark);
        if (thisMax > curMax)
            curMax = thisMax;

        return curMax;
    }

    private static void sortOrder(int[][] orders) {
        for (int i = 0; i < orders.length; i++) {
            for (int j = i + 1; j < orders.length; j++) {
                if (orders[i][0] > orders[j][0] || (orders[i][0] == orders[j][0] && orders[i][1] > orders[j][1])) {
                    int[] tmp = orders[i];
                    orders[i] = orders[j];
                    orders[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int pLeft = scan.nextInt();
        int stops = scan.nextInt();
        int orderNum = scan.nextInt();
        while (pLeft + stops + orderNum != 0) {
            int[][] orders = new int[orderNum][4];
            for (int i = 0; i < orderNum; i++) {
                orders[i][0] = scan.nextInt();
                orders[i][1] = scan.nextInt();
                orders[i][2] = scan.nextInt();
                orders[i][3] = (orders[i][1] - orders[i][0]) * orders[i][2];
            }
            sortOrder(orders);
            System.out.println(dfs(pLeft, 0, 0, 0, orders, 0));

            pLeft = scan.nextInt();
            stops = scan.nextInt();
            orderNum = scan.nextInt();
        }
    }

}
