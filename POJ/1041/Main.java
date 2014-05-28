import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    private static void print(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print("]");
        System.out.println();
    }

    private static int nextStart(boolean[] visited, int[][] zz, int maxZ) {
        for (int i = 1; i <= maxZ; i++) {
            if (visited[i] != true) {
                return zz[i][0];
            }
        }
        return -1;
    }

    private static void dfs(int start, int[][][] map, boolean[] visited, int[] degrees, List<Integer> steps, List<Integer> junctions) {
        //System.out.println("dfs " + start);
        //System.out.println("degrees: " + degrees[start]);
        //System.out.println("steps: " + steps);
        //System.out.println("junctions: " + junctions);
        //for (int i = 0; i < 50; i++) {
        //    System.out.println(map[start][i][0] + ", " + map[start][i][1]);
        //}
        degrees[start]--;
        if (degrees[start] > 0) {
            junctions.add(start);
            for (int i = 0; i < 50; i++) {
                int z = map[start][i][0];
                int end = map[start][i][1];
                if (end > 0 && !visited[z]) {
                    visited[z] = true;
                    steps.add(z);
                    dfs(end, map, visited, degrees, steps, junctions);
                    break;
                }
            }
        }
    }

    private static void printResults(List<List<Integer>> results, List<List<Integer>> juncResults) {
        List<Integer> base = results.remove(0);
        List<Integer> juncBase = juncResults.remove(0);
        List<Integer> s = new LinkedList<Integer>();
        if (results.size() == 0) {
            s = base;
        } else {
            Collections.reverse(base);
            Collections.reverse(juncBase);

            while (!results.isEmpty()) {
                //System.out.println("Base: " + base);
                //System.out.println("Base Juncs: " + juncBase);
                List<Integer> zs = results.remove(0);
                List<Integer> juncs = juncResults.remove(0);
                int baseIdx = -1;
                for (int i = 0; i < base.size(); i++) {
                    int junc = base.get(i);
                    if (juncs.contains(junc)) {
                        baseIdx = i;
                        break;
                    }
                }
                if (baseIdx == -1) {
                    results.add(zs);
                    juncResults.add(juncs);
                } else {
                    Collections.reverse(zs);
                    Collections.reverse(juncs);

                    //System.out.println("zs: " + zs);
                    //System.out.println("juncs: " + juncs);

                    int sameJunc = base.get(baseIdx);
                    int cmpIdx = juncs.indexOf(sameJunc);
                    for (int i = 0; i < zs.size(); i++) {
                        base.add(baseIdx + 1, zs.get(cmpIdx + i % zs.size()));
                    }
                }
            }

            Collections.reverse(base);
            s = base;
        }

        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i));
            if (i != s.size() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        boolean[] visited = new boolean[2000];
        int[][][] map = new int[50][50][2];
        int[] degrees = new int[50];
        int[][] zz = new int[2000][2];
        Scanner scan = new Scanner(System.in);
        int start = scan.nextInt();
        int end = scan.nextInt();
        Arrays.fill(visited, false);
        Arrays.fill(degrees, 0);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Arrays.fill(map[i][j], 0);
            }
        }
        for (int i = 0; i < 2000; i++) {
            Arrays.fill(zz[i], 0);
        }
        int myStart = start;
        int maxZ = 0;
        while (start != 0 && end != 0) {
            degrees[start]++;
            degrees[end]++;
            int z = scan.nextInt();
            if (z > maxZ) {
                maxZ = z;
            }
            for (int i = 0; i < 50; i++) {
                if (map[start][i][0] == 0  || map[start][i][0] > z) {
                    for (int j = 49; j - 1 >= i; j--) {
                        map[start][j][0] = map[start][j - 1][0];
                        map[start][j][1] = map[start][j - 1][1];
                    }
                    map[start][i][0] = z;
                    map[start][i][1] = end;
                    break;
                }
            }
            for (int i = 0; i < 50; i++) {
                if (map[end][i][0] == 0 || map[end][i][0] > z) {
                    for (int j = 49; j - 1 >= i; j--) {
                        map[end][j][0] = map[end][j - 1][0];
                        map[end][j][1] = map[end][j - 1][1];
                    }
                    map[end][i][0] = z;
                    map[end][i][1] = start;
                    break;
                }
            }

            zz[z][0] = start;
            zz[z][1] = end;

            start = scan.nextInt();
            end = scan.nextInt();
            if (start == 0 && end == 0) {
                // in the block
                boolean available = true;
                for (int i = 0; i < 50; i++) {
                    if (degrees[i] % 2 != 0) {
                        available = false;
                        break;
                    }
                }
                if (!available) {
                    System.out.println("Round trip does not exist.");
                } else {
                    List<Integer> steps = new LinkedList<Integer>();
                    List<Integer> junctions = new LinkedList<Integer>();
                    dfs(myStart, map, visited, degrees, steps, junctions);
                    List<List<Integer>> results = new LinkedList<List<Integer>>();
                    List<List<Integer>> juncResults = new LinkedList<List<Integer>>();
                    results.add(steps);
                    juncResults.add(junctions);
                    int newStart = nextStart(visited, zz, maxZ);
                    while (newStart != -1) {
                        steps = new LinkedList<Integer>();
                        junctions = new LinkedList<Integer>();
                        dfs(newStart, map, visited, degrees, steps, junctions);
                        results.add(steps);
                        juncResults.add(junctions);
                        newStart = nextStart(visited, zz, maxZ);
                    }
                    printResults(results, juncResults);
                }
                Arrays.fill(visited, false);
                Arrays.fill(degrees, 0);
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        Arrays.fill(map[i][j], 0);
                    }
                }
                for (int i = 0; i < 50; i++) {
                    Arrays.fill(zz[i], 0);
                }
                start = scan.nextInt();
                end = scan.nextInt();
                myStart = start;
                if (start == 0 && end == 0) {
                    break;
                }
            }
        }
    }
}
