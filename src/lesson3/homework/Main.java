package lesson3.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        String[] words = {"альфа",
                "бета",
                "гамма",
                "дельта",
                "эпсилон",
                "альфа",
                "лямбда",
                "лямбда",
                "сигма",
                "тау",
                "ипсилон",
                "омега",
                "бета",
                "альфа",
                "альфа"
        };
        System.out.println(Arrays.asList(words));
        /*
        рефакторинг после подсказок преподавателя после проверки домашки
        оставляю свой старый код закомментированным, чтобы видеть насколько новый код лаконичнее
        TreeSet<String> set = new TreeSet<>(Arrays.asList(words));
        System.out.println(set);
         */
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (String stringInArray : words) {
            /*
            int sum = 0;
            for (String stringInArray : words) {
                if (stringInSet.equals(stringInArray)) sum++;
            }
             */
            treeMap.put(stringInArray, treeMap.getOrDefault(stringInArray, 0) + 1);
        }
        //treeMap.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println(treeMap);
        System.out.println(treeMap.keySet());

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("aaa", "+7-999-999-99-99");
        phoneBook.add("bbb", "+7-999-999-99-88");
        phoneBook.add("aaa", "+7-999-999-99-77");
        phoneBook.add("ccc", "+7-999-999-99-66");
        phoneBook.add("ddd", "+7-999-999-99-55");
        phoneBook.add("aaa", "+7-999-999-99-44");
        phoneBook.add("bbb", "+7-999-999-99-33");

        String toFind = "aaa";
        System.out.printf("Поиск телефонных номеров по фамилии %s:\n", toFind);
        System.out.println(phoneBook.get(toFind));

        toFind = "bbb";
        System.out.printf("Поиск телефонных номеров по фамилии %s:\n", toFind);
        System.out.println(phoneBook.get(toFind));

        toFind = "eee";
        System.out.printf("Поиск телефонных номеров по фамилии %s:\n", toFind);
        System.out.println(phoneBook.get(toFind));
    }
}
