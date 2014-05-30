package org.lee.algorithm.tools;

public class Printers {
    public static void printMap(boolean[][] map) {
        System.out.print("\t");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i+"\t");
        }
        System.out.println();
        
        for (int i = 0; i < map.length; i++) {
            System.out.print(i+"\t");
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]?"1":"0");
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
