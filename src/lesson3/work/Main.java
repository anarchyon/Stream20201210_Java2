package lesson3.work;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("A");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("A");
        arrayList.add("E");
        System.out.println(arrayList);
        arrayList.removeIf(el -> el.equals("A"));
        System.out.println(arrayList);
    }
}
