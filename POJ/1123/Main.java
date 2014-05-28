import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Stack;
import java.text.DecimalFormat;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 21;
    public static final int[] costs = new int[NMAX];
    public static final int[] sales = new int[NMAX];
    public static final boolean[][] featureNeeds = new boolean[NMAX][NMAX];
    public static final DecimalFormat df = new DecimalFormat("0.000");


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int counter = 0;
        while (counter++ < n) {
            int minCost = scan.nextInt();
            int maxCost = scan.nextInt();
            int featureNum = scan.nextInt();
            int cusNum = scan.nextInt();

            for (int i = 1; i <= featureNum; i++) {
                costs[i] = scan.nextInt();
            }

            for (int i = 1; i <= cusNum; i++) {
                int mFeatureNum = scan.nextInt();
                for (int j = 0; j < mFeatureNum; j++) {
                    featureNeeds[i][scan.nextInt()] = true;
                }
                sales[i] = scan.nextInt();
            }

            System.out.println("Feature Set " + counter);
            double maxPi = 0.0d;
            double maxPiCmp = 0.0d;
            int maxSale = 0;
            int minFeatureNum = Integer.MAX_VALUE;
            int maxCustomer = 0;
            int mCost = 0;
            List<Integer> chosen = new LinkedList<Integer>();
            Stack<Integer> stack = new Stack<Integer>();
            stack.push(1);
            int cost = costs[1];
            while (!stack.empty()) {
                if (DEBUG) {
                    System.out.println("stack: " + stack);
                    System.out.println("costs: " + cost);
                }
                if (cost >= minCost && cost <= maxCost) {
                    int curSale = 0;
                    int curCustNum = 0;
                    for (int i = 1; i <= cusNum; i++) {
                        boolean meet = true;
                        for (int j = 1; j <= featureNum; j++) {
                            if (featureNeeds[i][j] && !stack.contains(j)) {
                                meet = false;
                                break;
                            }
                        }
                        if (meet) {
                            curSale += sales[i];
                            curCustNum++;
                        }
                    }
                    double curPi = curSale * 1.0d / cost;
                    double cmpPi = Math.round(curPi * 1000.0d) / 1000.0d;
                    if (cmpPi > maxPiCmp ||
                        (cmpPi == maxPiCmp &&
                         (curSale > maxSale ||
                          (curSale == maxSale &&
                           (stack.size() < minFeatureNum ||
                            (stack.size() == minFeatureNum && curCustNum > maxCustomer))))
                         )
                        ) {
                        maxPi = curPi;
                        maxPiCmp = cmpPi;
                        maxSale = curSale;
                        mCost = cost;
                        minFeatureNum = stack.size();
                        maxCustomer = curCustNum;
                        chosen.clear();
                        chosen.addAll(stack);
                    }
                }
                if (cost > maxCost) {
                    int top = stack.peek();
                    if (top != featureNum) {
                        stack.push(featureNum);
                        cost += costs[featureNum];
                    }
                }
                if (!stack.isEmpty()) {
                    int top = stack.peek();
                    if (top == featureNum) {
                        stack.pop();
                        cost -= costs[top];
                        if (!stack.isEmpty()) {
                            top = stack.pop();
                            cost -= costs[top];
                        }
                    }
                    if (top != featureNum) {
                        stack.push(top + 1);
                        cost += costs[top + 1];
                    }
                }
            }
            System.out.println(df.format(maxPiCmp));
            System.out.println(maxSale);
            System.out.println(mCost);
            Collections.sort(chosen);
            for (int i = 0; i < chosen.size(); i++) {
                System.out.print(chosen.get(i));
                if (i != chosen.size() - 1) {
                    System.out.print(" ");
                } else {
                    System.out.println();
                }
            }

            boolean isFirst = true;
            for (int i = 1; i <= cusNum; i++) {
                boolean meet = true;
                for (int j = 1; j <= featureNum; j++) {
                    if (featureNeeds[i][j] && !chosen.contains(j)) {
                        meet = false;
                        break;
                    }
                }
                if (meet) {
                    if (!isFirst) {
                        System.out.print(" ");
                    } else {
                        isFirst = false;
                    }
                    System.out.print(i);
                }
            }
            System.out.println();
        }
    }
}
