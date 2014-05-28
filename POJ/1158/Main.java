import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class Main {
    private static class MComparator implements Comparator<Junction> {
        @Override public int compare(Junction a, Junction b) {
            return a.time - b.time;
        }
    }

    private static class Junction {
        public int idx;
        public int c;
        public int r;
        public int b;
        public int p;
        public int time = Integer.MAX_VALUE;

        public Map<Junction, Integer> adjs = new HashMap<Junction, Integer>();
    }

    private static int getCurrentLight(int idx, int time, Map<Integer, Junction> junctions) {
        // 0 is B, 1 is purple
        Junction j = junctions.get(idx);
        if (time < j.r) {
            // less than remain
            return j.c;
        } else {
            int startColor = 1 - j.c;
            time -= j.r;
            int loop = j.b + j.p;
            int mod = time % loop;
            int firstDura = startColor == 0 ? j.b : j.p;
            if (mod < firstDura) {
                return startColor;
            } else {
                return 1 - startColor;
            }
        }
    }

    private static int getNextOppositeLightTime(int idx, int time, Map<Integer, Junction> junctions) {
        Junction j = junctions.get(idx);
        if (time < j.r) return j.r;
        int startColor = 1 - j.c;
        int nTime = time - j.r;
        int loop = j.b + j.p;
        int mod = nTime % loop;
        int firstDura = startColor == 0 ? j.b : j.p;

        //System.out.println(idx + ",, " + time + ",, " + mod + ",, " + junctions[idx][3-startColor]);
        if (mod < firstDura) {
            return time + firstDura - mod;
        } else {
            return time + loop - mod;
        }
    }

    private static int getNextSameLightTime(int idx1, int idx2, int time, Map<Integer, Junction> junctions) {
        int color1 = getCurrentLight(idx1, time, junctions);
        int color2 = getCurrentLight(idx2, time, junctions);
        if (color1 == color2) return time;
        else {
            int time1 = getNextOppositeLightTime(idx1, time, junctions);
            int time2 = getNextOppositeLightTime(idx2, time, junctions);
            //System.out.println("time1: " + time1 + " time2: " + time2);
            int nextTime = Math.min(time1, time2);
            return getNextSameLightTime(idx1, idx2, nextTime, junctions);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int source = scan.nextInt();
        int sink = scan.nextInt();

        int n = scan.nextInt();
        int m = scan.nextInt();

        Map<Integer, Junction> junctions = new HashMap<Integer, Junction>();
        scan.nextLine();
        for (int i = 1; i <= n; i++) {
            String[] tmp = scan.nextLine().split(" ");
            Junction j = new Junction();
            j.idx = i;
            j.c = "B".equals(tmp[0]) ? 0 : 1;
            j.r = Integer.valueOf(tmp[1]);
            j.b = Integer.valueOf(tmp[2]);
            j.p = Integer.valueOf(tmp[3]);
            junctions.put(i, j);
        }

        for (int i = 1; i <= m; i++) {
            Junction s = junctions.get(scan.nextInt());
            Junction e = junctions.get(scan.nextInt());
            int len = scan.nextInt();
            s.adjs.put(e, len);
            e.adjs.put(s, len);
        }

        PriorityQueue<Junction> pq = new PriorityQueue<Junction>(10, new MComparator());
        Junction jSource = junctions.get(source);
        jSource.time = 0;
        pq.offer(jSource);

        while (!pq.isEmpty()) {
            Junction current = pq.poll();
            int currentJun = current.idx;
            int startTime = current.time;

            for (Entry<Junction, Integer> entry: junctions.get(currentJun).adjs.entrySet()) {
                Junction j = entry.getKey();
                int len = entry.getValue();
                if (startTime + len >= j.time) continue;
                int goTime = getNextSameLightTime(currentJun, j.idx, startTime, junctions);
                if (goTime == Integer.MAX_VALUE) continue;
                int expectTime = goTime + len;
                //System.out.println("("+currentJun+","+j+") go time:"+goTime+", expectTime:"+expectTime);
                if (expectTime < j.time) {
                    j.time = expectTime;
                    pq.offer(j);
                }
            }
        }
        //System.out.println(Arrays.toString(times));
        Junction jSink = junctions.get(sink);
        if (jSink.time == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(jSink.time);
    }
}
