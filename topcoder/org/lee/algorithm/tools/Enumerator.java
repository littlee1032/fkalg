package org.lee.algorithm.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enumerator {
    public static List<String> enumerate(String elements) {
        List<String> ret = new ArrayList<String>();
        
        String sortedElements = sort(elements);
        ret.add(sortedElements);
        char[] array = sortedElements.toCharArray();
        int length = array.length;
        
        while (true) {
            int i = length - 2;
            while (i >= 0 && array[i + 1] <= array[i]) i--;
            if (i == -1) break;
            int j = length - 1;
            while (j > 0 && array[j] <= array[i]) j--;
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            
            for (int k = i + 1, l = length - 1; k < l; k++, l--) {
                tmp = array[k];
                array[k] = array[l];
                array[l] = tmp;
            }
            ret.add(new String(array));
        }
        return ret;
    }
    
    public static String sort(String elements) {
        char[] array = elements.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String test = "1223";
        System.out.println(enumerate(test));
    }

}
