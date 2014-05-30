
public class OneDimensionalRobotEasy {
    public int finalPosition(String commands, int a, int b) {
        int pos = 0;
        for (int i = 0; i < commands.length(); i++) {
            char cmm = commands.charAt(i);
            if ('R' == cmm && pos + 1 <= b) pos++;
            else if ('L' == cmm && pos - 1 >= -a) pos--;
        }
        return pos;
    }
}
