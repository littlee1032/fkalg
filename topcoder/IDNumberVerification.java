
public class IDNumberVerification {
    private final int[] doms = new int[]{-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int[] remains = new int[] {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
    private static final String WRONG = "Invalid";
    private static final String MALE = "Male";
    private static final String FEMALE = "Female";

    private boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }

    public String verify(String id, String[] regionCodes) {
        int len = id.length();
        if (len != 18) return WRONG;
        
        String regionCode = id.substring(0, 6);
        boolean found = false;
        for (int i = 0; i < regionCodes.length; i++) {
            if (regionCodes[i].equals(regionCode)) {
                found = true;
                break;
            }
        }
        if (!found) return WRONG;
        int year = Integer.valueOf(id.substring(6, 10));
        int month = Integer.valueOf(id.substring(10, 12));
        int day = Integer.valueOf(id.substring(12, 14));
        if (year < 1900 || year > 2011) return WRONG;
        if (month > 12 || month < 1) return WRONG;
        boolean isLeap = isLeapYear(year);
        int dom = doms[month];
        if (isLeap && month == 2) dom++;
        if (day < 1 || day > dom) return WRONG;
        
        int seq = Integer.valueOf(id.substring(14, 17));
        if (seq == 0) return WRONG;
        boolean isMale = (seq % 2 == 1);
        
        int total = 0;
        for (int i = 0; i < 17; i++) {
            int a = id.charAt(i) - '0';
            int remain = remains[(17 - i) % 10];
            total += a * remain;
        }
        char checksum = '0';
        for (int i = 0; i <= 10; i++) {
            if ((total + i) % 11 == 1) {
                if (i == 10) checksum = 'X';
                else checksum = (char)('0' + i);
                break;
            }
        }
        if (id.charAt(17) == checksum) {
            if (isMale) return MALE;
            else return FEMALE;
        } else {
            return WRONG;
        }
    }
}
