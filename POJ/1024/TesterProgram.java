import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class TesterProgram {
    public static void printChart(int height, int width, Set<String> walls) {
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                printUp(x, y, height, width, walls);
            }
            System.out.println();
            for (int x = 0; x < width; x++) {
                printMiddle(x, y, height, width, walls);
            }
            System.out.println();
        }
    }

    private static void printMiddle(int x, int y, int height, int width, Set<String> walls) {
        if (x == 0) {
            System.out.print("  ");
        } else {
            String leftWall = (x - 1) + " " + y + " " + x + " " + y;
            if (walls.contains(leftWall)) {
                System.out.print("X  ");
            } else {
                System.out.print("   ");
            }
        }
        System.out.print("*  ");
    }

    private static void printUp(int x, int y, int height, int width, Set<String> walls) {
        if (y == height - 1) {
            return;
        }
        String upWall = x + " " + y + " " + x + " " + (y+ 1);
        if (walls.contains(upWall)) {
            System.out.print(" XXX  ");
        } else {
            System.out.print("      ");
        }
    }

    private static Set<String> createWall(int x, int y, char path, char prevPath) {
        Set<String> walls = new HashSet<String>();
        String leftWall = createWall(x - 1, y, x, y);
        String upperWall = createWall(x, y, x, y + 1);
        String rightWall = createWall(x, y, x + 1, y);
        String downWall = createWall(x, y - 1, x, y);
        if (path != 'R' && prevPath != 'L') {
            walls.add(rightWall);
        }
        if (path != 'L' && prevPath != 'R') {
            walls.add(leftWall);
        }
        if (path != 'U' && prevPath != 'D') {
            walls.add(upperWall);
        }
        if (path != 'D' && prevPath != 'U') {
            walls.add(downWall);
        }
        return walls;
    }

    private static boolean isNeccessary(int endX, int endY, int pathLen, int height, int width, String wall, Set<String> wallSet) {
        String[] pos = wall.split(" ");
        int x1 = Integer.valueOf(pos[0]);
        int y1 = Integer.valueOf(pos[1]);
        int x2 = Integer.valueOf(pos[2]);
        int y2 = Integer.valueOf(pos[3]);
        if (x1 < 0 || x2 >= width || y1 < 0 || y2 >= height) {
            return false;
        }
        int[][] startLen = bfsLen(0, 0, height, width, wall, wallSet);
        int[][] endLen = bfsLen(endX, endY, height, width, wall, wallSet);
        if (startLen[x1][y1] + endLen[x2][y2] + 1 <= pathLen ||
            startLen[x2][y2] + endLen[x1][y1] + 1 <= pathLen) {
            return true;
        } else {
            return false;
        }
    }

    private static int[][] bfsLen(int startX, int startY, int height, int width, String wall, Set<String> wallSet) {
        int[][] lens = new int[width][height];
        for (int i = 0; i < width; i++) {
            Arrays.fill(lens[i], Integer.MAX_VALUE);
        }
        lens[startX][startY] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(startX);
        queue.offer(startY);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int val = lens[x][y] + 1;
            if (x != 0) {
                String leftWall = createWall(x-1, y, x, y);
                if (leftWall.equals(wall) || !wallSet.contains(leftWall)) {
                    if (val < lens[x-1][y]) {
                        lens[x-1][y] = val;
                        queue.offer(x - 1);
                        queue.offer(y);
                    }
                }
            }
            if (x != width - 1) {
                String rightWall = createWall(x, y, x + 1, y);
                if (rightWall.equals(wall) || !wallSet.contains(rightWall)) {
                    if (val < lens[x+1][y]) {
                        lens[x+1][y] = val;
                        queue.offer(x + 1);
                        queue.offer(y);
                    }
                }
            }
            if (y != 0) {
                String downWall = createWall(x, y - 1, x, y);
                if (downWall.equals(wall) || !wallSet.contains(downWall)) {
                    if (val < lens[x][y-1]) {
                        lens[x][y-1] = val;
                        queue.offer(x);
                        queue.offer(y - 1);
                    }
                }
            }
            if (y != height - 1) {
                String upperWall = createWall(x, y, x , y + 1);
                if (upperWall.equals(wall) || !wallSet.contains(upperWall)) {
                    if (val < lens[x][y+1]) {
                        lens[x][y+1] = val;
                        queue.offer(x);
                        queue.offer(y + 1);
                    }
                }
            }
        }
        return lens;
    }

    private static String createWall(int x1, int y1, int x2, int y2) {
        return x1 + " " + y1 + " " + x2 + " " + y2;
    }

    private static Set<String> walls(int height, int width, String path) {
        int x = 0;
        int y = 0;
        char prevPath = '?';
        int pathLen = path.length();
        Set<String> wallSet = new HashSet<String>();
        for (int i = 0; i < pathLen; i++) {
            char onePath = path.charAt(i);
            wallSet.addAll(createWall(x, y, onePath, prevPath));
            prevPath = onePath;
            switch (onePath) {
            case 'R':
                x++;
                break;
            case 'L':
                x--;
                break;
            case 'U':
                y++;
                break;
            case 'D':
                y--;
                break;
            default:
                break;
            }
        }
        Set<String> walls = new HashSet<String>();
        Iterator<String> itor = wallSet.iterator();
        while (itor.hasNext()) {
            String candidate = itor.next();
            if (isNeccessary(x, y, pathLen, height, width, candidate, wallSet)) {
                walls.add(candidate);
            } else {
                itor.remove();
            }
        }
        return walls;
    }


    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testNum = scan.nextInt();
        for (int i = 0; i < testNum; i++) {
            int weight = scan.nextInt();
            int height = scan.nextInt();
            scan.nextLine();
            String path = scan.nextLine();
            //System.out.println(weight + " " + height + " " + path);
            Set<String> wallSet = walls(height, weight, path);
            System.out.println(wallSet.size());
            for (String wall : wallSet) {
                System.out.println(wall);
            }
            printChart(height, weight, wallSet);
        }
    }
}
