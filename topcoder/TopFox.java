import java.util.HashSet;
import java.util.Set;


public class TopFox {
    public int possibleHandles(String familyName, String givenName) {
        Set<String> handles = new HashSet<String>();
        int n = familyName.length();
        int m = givenName.length();
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                String handle = familyName.substring(0, i) + givenName.substring(0, j);
                handles.add(handle);
            }
        }
        return handles.size();
    }
    
    public static void main(String[] args) {
        System.out.println(new TopFox().possibleHandles("ab", "cd"));
    }
}
