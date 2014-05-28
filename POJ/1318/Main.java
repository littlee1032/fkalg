import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class Main {
    private static final Map<String, List<String>> dict = new HashMap<String, List<String>>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        while (!"XXXXXX".equals(line)) {
            String key = transform(line);
            if (!dict.containsKey(key)) dict.put(key, new LinkedList<String>());
            dict.get(key).add(line);
            line = scan.nextLine();
        }
        for (List<String> val : dict.values()) {
            Collections.sort(val);
        }

        line = scan.nextLine();
        while (!"XXXXXX".equals(line)) {
            String key = transform(line);
            if (!dict.containsKey(key)) {
                System.out.println("NOT A VALID WORD");
            } else {
                for (String s : dict.get(key)) {
                    System.out.println(s);
                }
            }
            System.out.println("******");
            line = scan.nextLine();
        }
    }

    private static String transform(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        return String.valueOf(cs);
    }
}
