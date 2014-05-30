import java.util.Scanner;


public class Fail1015 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Scanner s = new Scanner(System.in);
	    int candidates = s.nextInt();
	    int jury = s.nextInt();
	    s.nextLine();
	    int round = 0;
	    int[][] recs = new int[201][5];
	    
	    while (jury != 0 && candidates != 0) {
	        round++;
	        System.out.println("Jury #" + round);
	        
	        for (int i = 1; i <= candidates; i++) {
	            int pi = s.nextInt();
	            int di = s.nextInt();
	            s.nextLine();
	            recs[i][0] = pi;
	            recs[i][1] = pi - di + 20; // make it positive
	            recs[i][2] = 0; // whether picked by first try
	            recs[i][3] = 0; // whether picked by second try
	            recs[i][4] = i; // original index
	        }
	        
	        int bagPack = jury * 20;
	        int firstTry = bagTry(recs, candidates, jury, bagPack, 1);
	        System.out.println("first Try: " + firstTry);
	        if (firstTry == bagPack) {
	            printResults(recs, candidates, jury, 1);
	        } else {
	            int secondTry = bagTry(recs, candidates, jury, 2 * bagPack - firstTry, 2);
	            System.out.println("secondTry: " + secondTry);
	            if (secondTry > firstTry && secondTry + firstTry < 2 * bagPack)
	                printResults(recs, candidates, jury, 2);
	            else
	                printResults(recs, candidates, jury, 1);
	        }

	        candidates = s.nextInt();
	        jury = s.nextInt();
	        s.nextLine();
	        s.nextLine();
	    }
	}
	
	private static int bagTry(int[][] recs, int candidates, int jury, int bagPack, int whichTry) {
	    int[][][] f = new int[candidates + 1][bagPack + 1][jury + 1];
	    for (int i = 1; i <= candidates; i++) {
	        for (int j = bagPack; j >= recs[i][1]; j--) {
	            for (int k = jury; k > 0; k--) {
	                f[i][j][k] = Math.max(f[i - 1][j][k], f[i - 1][j - recs[i][1]][k - 1] + recs[i][1]);
	            }
	        }
	    }
	    
	    int col = whichTry == 1 ? 2 : 3;
	    int i = candidates;
	    int j = bagPack;
	    int k = jury;
	    while (i >= 0) {
	        if (f[i][j][k] != 0 && f[i][j][k] == f[i - 1][j - recs[i][1]][k - 1] + recs[i][1]) {
//	            System.out.println("i: " + i + " j: " + j + " k: " + k + " recs: " + recs[i][1]);
	            recs[i][col] = 1;
	            j -= recs[i][1];
	            k -= 1;
	        }
	        i--;
	    }
	    
	    return f[candidates][bagPack][jury];
	}
	
	private static void transfer(int[][] recs, int candidates, int jury, int col) {
	    for (int i = 0; i <= candidates; i++) {
	        for (int j = i; j <= candidates; j++) {
	            if (recs[i][1] * 100 + recs[i][0] < recs[j][1] * 100 + recs[j][0]) {
	                int[] tmp = recs[j];
	                recs[j] = recs[i];
	                recs[i] = tmp;
	            }
	        }
	    }
	    
	    for (int i = 1; i <= candidates; i++) {
	        for (int j = 0; j < 5; j++)
	            System.out.print(recs[i][j] + " ");
	        System.out.println();
	    }
	    
	    int num = 0;
	    int pos = -1;
	    for (int i = 0; i <= candidates; i++) {
	        if (num != recs[i][1]) {
	            num = recs[i][1];
	            pos = i;
	            continue;
	        }
	   	    if (recs[i][col] == 1) {
	            recs[pos][col] = 1;
	            pos++;
	            recs[i][col] = 0;
	        }
	    }
	    
	    for (int i = 0; i <= candidates; i++) {
	        for (int j = i; j <= candidates; j++) {
	            if (recs[i][4] > recs[j][4]) {
	                int[] tmp = recs[i];
	                recs[i] = recs[j];
	                recs[j] = tmp;
	            }
	        }
	    }
	}
	
	private static void printResults(int[][] recs, int candidates, int jury, int whichTry) {
	    int col = 2;
	    if (whichTry == 2)
	        col = 3;
	    transfer(recs, candidates, jury, col);
	    int p = 0;
	    int d = 0;
	    int found = 0;
	    for (int i = 1; i <= candidates && found < jury; i++) {
	        if (recs[i][col] == 1) {
	            p += recs[i][0];
	            d += recs[i][0] - recs[i][1] + 20;
	            found++;
	        }
	    }
	    
	    System.out.println("Best jury has value " + p + " for prosecution and value " + d + " for defence:");
	    
	    found = 0;
	    for (int i = 1; i <= 200 && found < jury; i++)
	        if (recs[i][col] == 1)
	            System.out.print(" " + i);
	    
	    System.out.println();
	    System.out.println();
	}

}
