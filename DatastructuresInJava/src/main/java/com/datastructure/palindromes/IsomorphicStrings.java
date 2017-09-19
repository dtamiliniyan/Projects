package com.datastructure.palindromes;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class IsomorphicStrings {
    //the words "abca" and "zbxz" are isomorphic

    public static boolean areIsomorphic(String s1, String s2) {
        Map<Character, ArrayList<Integer>> charPos1 = new LinkedHashMap<>();
        Map<Character, ArrayList<Integer>> charPos2 = new LinkedHashMap<>();

        for (int i=0; i<s1.length(); i++) {
            if (charPos1.containsKey(s1.charAt(i))) {
                charPos1.get(s1.charAt(i)).add(i);
            } else {
                charPos1.put(s1.charAt(i), new ArrayList<>(Arrays.asList(i)));
            }

        }
        System.out.println(charPos1);

        for (int i=0; i<s2.length(); i++) {
            if (charPos2.containsKey(s2.charAt(i))) {
                charPos2.get(s2.charAt(i)).add(i);
            } else {
                charPos2.put(s2.charAt(i), new ArrayList<>(Arrays.asList(i)));
            }
        }
        
        System.out.println(charPos2);
        System.out.println(new ArrayList<>(charPos1.values()));
        
        return new ArrayList<>(charPos1.values()).equals(new ArrayList<>(charPos2.values()));
        //return freqMap1.values().equals(freqMap2.values()); won't work
    }

    public static void main(String[] args) {
        String s1="abbcdce";
        String s2="xyyzwzu";
        System.out.println(areIsomorphic(s1, s2));
    }
}