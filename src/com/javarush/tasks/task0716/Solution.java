package com.javarush.tasks.task0716;

import java.util.ArrayList;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("роза");
        strings.add("лоза");
        strings.add("лира");
        strings.add("мера");
        strings.add("вода");
        strings.add("упор");
        strings = fix(strings);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> strings) {
        ArrayList<String> result = new ArrayList<>();
        for (String word : strings) {
            if (word.contains("р")&&word.contains("л")){
                result.add(word);
            } else if (word.contains("л")) {
                result.add(word);
                result.add(word);
            } else if (!word.contains("р")){
                result.add(word);
            }
        }
        return result;
    }
}