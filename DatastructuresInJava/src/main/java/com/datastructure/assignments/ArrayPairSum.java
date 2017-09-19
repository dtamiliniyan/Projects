package com.datastructure.assignments;
import java.util.HashMap;
import java.util.Map;

public class ArrayPairSum {


public static void main(String[] args) {        

    int[]a = {0,-4,2,5,3,8,9,1,6,3,11,5,2,7,3};
    printSumPairs(a,5);        

}


public static void printSumPairs(int []input, int k){
    Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();

    for(int i=0;i<input.length;i++){

        if(pairs.containsKey(input[i]))
            System.out.println(input[i] +", "+ pairs.get(input[i]));
        else
            pairs.put(k-input[i], input[i]);
    }
    System.out.println("=========");
    for(int i=0;i<input.length;i++){
    	for(int j=0;j<input.length;j++){
    		if(i == j) {
    			continue;
    		}
    		if(input[i]+input[j] == k) {
    			System.out.println(input[i] +", "+ input[j]);
    		}
    	}
    }

}
}