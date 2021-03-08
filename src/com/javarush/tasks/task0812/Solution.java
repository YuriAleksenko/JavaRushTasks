package com.javarush.tasks.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = new ArrayList<>();
        for (int i=0; i<10; i++) {
            numbers.add(Integer.parseInt(reader.readLine()));
        }
        int count = 1;
        int maxCount = 1;
        Integer previousNumber=null;
        for (Integer number : numbers) {
            if (previousNumber!=null) {
                if (previousNumber.intValue()==number.intValue()){
                    count+=1;
                    previousNumber=number.intValue();
                } else {
                    if (count>maxCount) {
                        maxCount = count;
                        count=1;
                    }
                    previousNumber=number.intValue();
                    count=1;
                }
            } else {
                previousNumber=number.intValue();
            }
        }
        if (count>maxCount) {
            maxCount=count;
        }
        System.out.println(maxCount);
    }
}
