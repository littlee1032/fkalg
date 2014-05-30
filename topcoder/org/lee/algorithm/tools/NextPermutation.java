package org.lee.algorithm.tools;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NextPermutation {
    public boolean nextPermutation(List<Integer> cs) {
        int size = cs.size();
        int k = size - 1;
        for (; k >= 1; k--) {
            if (cs.get(k - 1).compareTo(cs.get(k)) < 0) break;
        }
        if (k == 0) return false;
        int l = size - 1;
        Integer cmpValue = cs.get(k - 1);
        for (; l >= k; l--) {
            if (cs.get(l).compareTo(cmpValue) > 0) break;
        }
        cs.set(k - 1, cs.get(l));
        cs.set(l,  cmpValue);
        Collections.reverse(cs.subList(k, size));
        return true;
    }
    
    public boolean nextPermutation(int[] cs) {
        int size = cs.length;
        int k = size - 1;
        for (; k >= 1; k--) {
            if (cs[k - 1] < cs[k]) break;
        }
        if (k == 0) return false;
        int l = size - 1;
        int cmpValue = cs[k - 1];
        for (; l > k; l--) {
            if (cs[l] > cmpValue) break;
        }
        cs[k - 1] = cs[l];
        cs[l] = cmpValue;
        for (int i = k, j = size - 1; i < j; i++, j--) {
            int tmp = cs[i];
            cs[i] = cs[j];
            cs[j] = tmp;
        }
        return true;
    }
    
    public static void main(String[] args) {
        List<Integer> lists = new LinkedList<Integer>();
        int[] iarr = new int[5];
        for (int i = 0; i < 5; i++) {
            lists.add(i);
            iarr[i] = i;
        }
        
        NextPermutation np = new NextPermutation();
        
        do {
            System.out.println(lists);
        } while (np.nextPermutation(lists));
        
        do {
            for (int i = 0; i < 5; i++) {
                System.out.print(iarr[i] + ",");
            }
            System.out.println();
        } while (np.nextPermutation(iarr));
    }
}
