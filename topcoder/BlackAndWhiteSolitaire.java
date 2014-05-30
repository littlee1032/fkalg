
public class BlackAndWhiteSolitaire {
    public int minimumTurns(String cardFront) {
        int bBegin = 0;
        int wBegin = 0;
        char bBeginChar = 'B';
        char wBeginChar = 'W';
        
        for (int i = 0; i < cardFront.length(); i++) {
            char c = cardFront.charAt(i);
            if (c != bBeginChar) {
                bBegin++;
            }
            if (c != wBeginChar) {
                wBegin++;
            }
            char tmp = bBeginChar;
            bBeginChar = wBeginChar;
            wBeginChar = tmp;
        }
        return bBegin <= wBegin ? bBegin : wBegin;
    }
}
