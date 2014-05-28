import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(scan.nextInt());
        }
        Collections.sort(list);
        System.out.print(list.get(list.size() - 1));
        for (int i = 1; i < list.size() - 1; i++) {
            System.out.print(" " + list.get(i));
        }
        System.out.println(" " + list.get(0));
    }
}
