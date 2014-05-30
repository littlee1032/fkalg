package org.lee.algorithm.tools;

public class BagPack {

    public static void zeroOnePack(int cost, int value, int bag, int[] f) {
        for (int i = bag; i >= cost; i--)
            f[i] = Math.max(f[i], f[i - cost] + value);
    }
    
    public static void completePack(int cost, int value, int bag, int[] f) {
        for (int i = cost; i <= bag; i++)
            f[i] = Math.max(f[i], f[i - cost] + value);
    }
    
    public static void multiplePack(int cost, int value, int amount, int bag, int[] f) {
        if (cost * amount >= bag)
            completePack(cost, value, bag, f);
        else {
            int k = 1;
            while (amount >= k) {
                amount -= k;
                zeroOnePack(cost * k, value * k, bag, f);
                k *= 2;
            }
            zeroOnePack(cost * amount, value * amount, bag, f);
        }
    }
    
    public static void twoDimensionZeroOnePack(int cost1, int cost2, int value, int bag1, int bag2, int[][] f) {
        for (int i = bag1; i >= cost1; i--) {
            for (int j = bag2; j >= cost2; j--) {
                f[i][j] = Math.max(f[i][j], f[i - cost1][j - cost2] + value);
            }
        }
    }
    
    public static void twoDimensionCompletePack(int cost1, int cost2, int value, int bag1, int bag2, int[][] f) {
        for (int i = 0; i <= bag1; i++) {
            for (int j = 0; j <= bag2; j++) {
                f[i][j] = Math.max(f[i][j], f[i - cost1][j - cost2] + value);
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
