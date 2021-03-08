package com.javarush.tasks.task1020;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Задача по алгоритмам Ӏ Java Syntax: 10 уровень, 11 лекция
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[5];
        for (int i = 0; i < 5; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        for (int i = 0; i<5; i++) {
            System.out.println(array[i]);
        }
    }

    public static void sort(int[] array) {
        //напишите тут ваш код
        boolean swap;
        int curDigit;
        do {
            swap=false;
            for (int i=0;i<array.length; i++){
                if (i<array.length-1) {
                    if (array[i]>array[i+1]) {
                        curDigit=array[i];
                        array[i]=array[i+1];
                        array[i+1]=curDigit;
                        swap=true;
                    }
                }
            }
        } while (swap);
    }
}
