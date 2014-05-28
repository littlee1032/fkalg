import java.util.Scanner;

public class Main {
    private static int[] degrees;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        while (!"ENDOFINPUT".equals(line)) {
            String[] argus = line.split(" ");
            int start = Integer.valueOf(argus[1]);
            int roomNo = Integer.valueOf(argus[2]);
            int doorNo = 0;
            degrees = new int[roomNo];
            for (int i = 0; i < roomNo; i++) {
                line = scan.nextLine();
                if (!"".equals(line)) {
                    for (String adjStr : line.split(" ")) {
                        int adj = Integer.valueOf(adjStr);
                        degrees[i]++;
                        degrees[adj]++;
                        doorNo++;
                    }
                }
            }
            boolean ok = true;
            for (int i = 0; i < roomNo; i++) {
                if ((i == 0 || i == start) && (start != 0)) {
                    if (degrees[i] % 2 != 1) {
                        ok = false;
                        break;
                    }
                } else {
                    if (degrees[i] % 2 != 0) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) System.out.println("YES " + doorNo);
            else System.out.println("NO");
            scan.nextLine();
            line = scan.nextLine();
        }
    }
}
