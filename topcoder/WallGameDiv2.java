
public class WallGameDiv2 {
    public int play(String[] costs) {
        String firstLine = costs[0];
        int colNum = firstLine.length();
        int rowNum = costs.length;
        int[] lastLine = new int[colNum];
        lastLine[0] = 0;
        for (int i = 1; i < firstLine.length(); i++) {
            lastLine[i] = lastLine[i - 1] + (firstLine.charAt(i) == 'x' ? Integer.MIN_VALUE : firstLine.charAt(i) - '0');
        }
        int max = 0;
        for (int i = 1; i < costs.length - 1; i++) {
            
        }
        return max;
    }
}
