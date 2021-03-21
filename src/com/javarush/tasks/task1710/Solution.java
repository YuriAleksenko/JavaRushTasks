package com.javarush.tasks.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        if (args.length==0) {
            System.out.println("Неправильный набор параметров!");
        } else {
            switch (args[0]) {
                case "-c":
                    try {
                        addHuman(args[1], sdf.parse(args[3]), args[2] == "м" ? Sex.MALE : Sex.FEMALE);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "-u":
                    try {
                        updatePerson(Integer.parseInt(args[1]), args[2], sdf.parse(args[4]), args[3]== "м" ? Sex.MALE : Sex.FEMALE);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "-i":
                    displayPerson(Integer.parseInt(args[1]));
                    break;
                case "-d":
                    deletePerson(Integer.parseInt(args[1]));
            }
        }
    }

    public static void addHuman(String name, Date birthdate, Sex sex) {
        Person newPers=null;
        if (sex.equals(Sex.MALE)) {
            newPers=Person.createMale(name, birthdate);
        } else if (sex.equals(Sex.FEMALE)) {
            newPers=Person.createFemale(name, birthdate);
        }
        allPeople.add(newPers);
        System.out.println(allPeople.size()-1);
    }

    public static void updatePerson(int index, String name, Date birthdate, Sex sex) {
        Person curPers=allPeople.get(index);
        curPers.setName(name);
        curPers.setBirthDate(birthdate);
        curPers.setSex(sex);
    }

    public static void displayPerson(int index) {
        Person curPers=allPeople.get(index);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
        String result=curPers.getName()+" "+(curPers.getSex().equals(Sex.MALE)?"м":"ж")+" "+sdf.format(curPers.getBirthDate());
        System.out.println(result);
    }

    public static void deletePerson(int index){
        Person curPers=allPeople.get(index);
        curPers.setName(null);
        curPers.setBirthDate(null);
        curPers.setSex(null);
    }
}
