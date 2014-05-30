import java.util.Scanner;

public class POJ1019 {
    private static int digitNumber(int n) {
        int ret = 0;

        while (n != 0) {
            ret++;
            n /= 10;
        }

        return ret;
    }

    private static int findNum(int sequenceNumber, long position) {
        int pos = 0;
        int num = 0;
        for (int i = 1; i <= sequenceNumber; i++) {
            int numDigits = digitNumber(i);
            if (pos + numDigits >= position) {
                num = i;
                break;
            } else {
                pos += numDigits;
            }
        }

        return String.valueOf(num).charAt((int) (position - pos - 1)) - '0';
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testNum = scan.nextInt();
        for (int i = 0; i < testNum; i++) {
            int position = scan.nextInt();

            int numberDigits = 0;
            long sequenceNumberDigits = 0;
            long pos = 0;
            int sequenceNum = -1;

            int begin = 1;
            for (int j = begin; j <= Integer.MAX_VALUE; j++) {
                numberDigits = digitNumber(j);
                sequenceNumberDigits += numberDigits;
                if (pos + sequenceNumberDigits >= position) {
                    sequenceNum = j;
                    break;
                } else {
                    pos += sequenceNumberDigits;
                }
            }
            int p = findNum(sequenceNum, position - pos);
            System.out.println(p);
        }
    }

}
