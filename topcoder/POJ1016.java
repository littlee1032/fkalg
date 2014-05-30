import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class POJ1016 {
    private static String invent(String number) {
        int[] holder = new int[10];
        for (int i = 0; i < number.length(); i++) {
            holder[number.charAt(i) - '0'] ++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (holder[i] != 0) {
                sb.append(holder[i]);
                sb.append(i);
            }
        }
        
        return sb.toString();
    }
    
    private static void judge(String number) {
        List<String> breadscrum = new ArrayList<String>();
        String old = number;
        for (int i = 0; i < 15; i++) {
            String newInvent = invent(old);
//            System.out.println("newInvent: " + newInvent);
            if (newInvent.equals(old)) {
                if (i == 0) {
                    System.out.println(number + " is self-inventorying");
                } else {
                    System.out.println(number + " is self-inventorying after " + i + " steps");
                }
                return;
            } else if (breadscrum.contains(newInvent)) {
                for (int j = 0; j < breadscrum.size(); j++) {
//                    System.out.println("breadscrum: " + breadscrum.get(j));
                    if (breadscrum.get(j).equals(newInvent)) {
                        System.out.println(number + " enters an inventory loop of length " + (breadscrum.size() - j));
                        break;
                    }
                }
                return;
            }
            old = newInvent;
            breadscrum.add(old);
        }
        System.out.println(number + " can not be classified after 15 iterations");
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();
        
        while (!"-1".equals(number)) {
            judge(number);
            number = scan.nextLine();
        }
    }

}
