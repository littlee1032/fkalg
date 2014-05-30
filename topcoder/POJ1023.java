import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


public class POJ1023 {
    public static String kFun(String input, int[] bits, int k) {
        Deque<String> stack = new LinkedList<String>();
        if (k < 64) {
            long number = Long.valueOf(input);
            
            int flag = 1;
            if (number < 0) {
                flag = -1;
                number *= -1;
            }
            
            int counter = k;
            while (counter > 0) {
                if (number % 2 == 1) {
                    stack.push("1");
                    number -= flag * bits[counter - 1];
                } else {
                    stack.push("0");
                }
                number /= 2;
                counter--;
            }
            if (number != 0)
                return "Impossible";
        } else {
            BigInteger number = new BigInteger(input);
            BigInteger zero = BigInteger.ZERO;
            BigInteger one = BigInteger.ONE;
            BigInteger two = new BigInteger("2");
            
            int counter = k;
            while (counter > 0) {
                if (number.mod(two).equals(one)) {
                    stack.push("1");
                    number = number.subtract(new BigInteger(String.valueOf(bits[counter - 1])));
                } else {
                    stack.push("0");
                }
                number = number.divide(two);
                counter--;
            }
            if (number.compareTo(zero) != 0)
                return "Impossible";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testNum = scan.nextInt();
        int[] bits = new int[64];
        
        for (int i = 0; i < testNum; i++) {
            int k = scan.nextInt();
            scan.nextLine();
            String bitStr = scan.nextLine();
            for (int j = 0; j < k; j++) {
                if (bitStr.charAt(j) == 'p') {
                    bits[j] = 1;
                } else {
                    bits[j] = -1;
                }
            }
            
            String ask = scan.nextLine();
            System.out.println(kFun(ask, bits, k));
        }
    }

}
