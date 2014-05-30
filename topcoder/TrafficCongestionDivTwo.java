
public class TrafficCongestionDivTwo {
//    public long theMinCars(int treeHeight) {
//        long[] rets = new long[treeHeight + 2];
//        rets[0] = 1;
//        rets[1] = 1;
//        for (int i = 2; i <= treeHeight; i++) {
//            rets[i] = 1;
//            for (int j = 0; j < i - 1; j++) {
//                rets[i] += 2 * rets[j];
//            }
//        }
//        return rets[treeHeight];
//    }
    public long theMinCars(int treeHeight) {
        long ret = 0;
        ret = ((1l << treeHeight + 1) - 1) / 3;
        if (treeHeight % 2 == 0) ret++;
        return ret;
    }
    
    
    public static void main(String[] args) {
        TrafficCongestionDivTwo tcd = new TrafficCongestionDivTwo();
        for (int i = 0; i <= 60; i++) {
            System.out.println(tcd.theMinCars(i));
        }
    }
}
