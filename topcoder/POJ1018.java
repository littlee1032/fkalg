import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class POJ1018 {
    public static class Holder implements Comparable<Holder> {
        public int bandwidth;
        public int price;
        public int group;
        
        public Holder(int bandwidth, int price, int group) {
            this.bandwidth = bandwidth;
            this.price = price;
            this.group = group;
        }
        
        public String toString() {
            return ("(" + bandwidth + "," + price + "," + group + ")");
        }
        
        public int compareTo(Holder obj) {
            if (obj == this)
                return 0;
            
            if (bandwidth < obj.bandwidth)
                return -1;
            if (bandwidth > obj.bandwidth)
                return 1;
            
            if (price < obj.price)
                return -1;
            if (price > obj.price)
                return 1;
            
            if (group < obj.group)
                return -1;
            if (group > obj.group)
                return 1;
            
            return 0;
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        for (int i = 0; i < number; i++) {
            List<Holder> holders = new ArrayList<Holder>();
            Map<Integer, List<Holder>> map = new HashMap<Integer, List<Holder>>();
            int parts = scan.nextInt();
            
            for (int j = 0; j < parts; j++) {
                List<Holder> sortedList = new LinkedList<Holder>();
                int devices = scan.nextInt();
                for (int k = 0; k < devices; k++) {
                    int b = scan.nextInt();
                    int p = scan.nextInt();
                    Holder aHolder = new Holder(b, p, j+1);
                    holders.add(aHolder);
                    sortedList.add(aHolder);
                }
                Collections.sort(sortedList, new Comparator<Holder>() {
                    public int compare(Holder o1, Holder o2) {
                        return o1.price - o2.price;
                    }
                    
                    public boolean equals(Object obj) {
                        return false;
                    }
                });
                map.put(j+1, sortedList);
            }
            Collections.sort(holders);
            
            double bp = 0.0;
            int checkedBandWidth = holders.get(0).bandwidth;
            Set<Integer> checked = new HashSet<Integer>();
            for (int ii = 0 ; ii < holders.size(); ii++) {
                Holder holder = holders.get(ii);
                int group = holder.group;
                int bandwidth = holder.bandwidth;
                double price = 0.0;
                
                if (bandwidth != checkedBandWidth) {
                    checkedBandWidth = bandwidth;
                    checked = new HashSet<Integer>();
                }
                
                if (checked.contains(group))
                    continue;
                
                checked.add(group);
                
                price += holder.price;
                
                boolean wrong = false;
                for (int j = 1; j <= parts; j++) {
                    if (j != group) {
                        boolean found = false;
                        for (Holder oHolder : map.get(j)) {
                            if (oHolder.bandwidth >= bandwidth) {
                                found = true;
                                price += oHolder.price;
                                break;
                            }
                        }
                        if (!found) {
                            wrong = true;
                            break;
                        }
                    }
                }
                
                if (!wrong) {
                    if (bandwidth / price > bp)
                        bp = bandwidth / price;
                }
            }
            
            System.out.printf("%.3f\n", bp);
        }
    }

}
