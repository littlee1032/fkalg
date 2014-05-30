import java.util.Scanner;


public class POJ1017 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] holder = new int[6];
        int flag = 0;
        for (int i = 0; i < 6; i++) {
            holder[i] = scan.nextInt();
            flag += holder[i];
        }
        
        while (flag != 0) {
            int result = 0;
            
            // 6 * 6
            result += holder[5];
            holder[5] = 0;
            
            // 5 * 5
            while (holder[4] != 0) {
                result++;
                holder[4]--;
                // use 1*1 to fill
                if (holder[0] >= 11)
                    holder[0] -= 11;
                else
                    holder[0] = 0;
            }
            
            // 4 * 4
            while (holder[3] != 0) {
                result++;
                holder[3]--;
                // use 2*2 to fill
                if (holder[1] >= 5)
                    holder[1] -= 5;
                else {
                    int rest = 4 * (5 - holder[1]);
                    holder[1] = 0;
                    // use 1*1 to fill
                    if (holder[0] >= rest)
                        holder[0] -= rest;
                    else
                        holder[0] = 0;
                }
            }
            
            // 3*3
            // fill with 3*3
            while (holder[2] >= 4) {
                result++;
                holder[2] -= 4;
            }
            if (holder[2] != 0) {
                result++;
                int rest = 6 * 6 - holder[2] * 3 * 3;
                int toFilled = 0;
                switch (holder[2]) {
                case 1:
                    toFilled = 5;
                    break;
                case 2:
                    toFilled = 3;
                    break;
                case 3:
                    toFilled = 1;
                    break;
                default:
                    // can not be here
                }
                
                holder[2] = 0;
                
                // filled with 2*2
                if (holder[1] >= toFilled) {
                    holder[1] -= toFilled;
                    rest -= 2*2*toFilled;
                } else {
                    rest -= 2*2*holder[1];
                    holder[1] = 0;
                }
                
                // filled with 1*1
                if (rest != 0) {
                    if (holder[0] >= rest) {
                        holder[0] -= rest;
                    } else {
                        holder[0] = 0;
                    }
                }
            }
            
            // 2*2
            while (holder[1] >= 9) {
                result++;
                holder[1] -= 9;
            }
            if (holder[1] != 0) {
                result++;
                int rest = 6 * 6 - 2 * 2 * holder[1];
                holder[1] = 0;
                if (holder[0] >= rest)
                    holder[0] -= rest;
                else
                    holder[0] = 0;
            }
            
            // 1*1
            if (holder[0] != 0) {
                result += (holder[0] - 1) / 36 + 1; // ceiling....
            }
            holder[0] = 0;
            
            System.out.println(result);
            
            flag = 0;
            for (int i = 0; i < 6; i++) {
                holder[i] = scan.nextInt();
                flag += holder[i];
            }
        }
    }

}
