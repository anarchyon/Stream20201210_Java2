package Java3_lesson1.homework;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //массивы для проверки заданий 1 и 2
        Integer[] integerArray = {1, 2, 3, 4, 5, 6};
        Float[] floatArray = {1f};
        String[] stringArray = {"first", "last"};

        //проверка выполнения метода по заданию 1
        System.out.println(changeFirstLastElements(integerArray));
        System.out.println(changeFirstLastElements(floatArray));
        System.out.println(changeFirstLastElements(stringArray));

        System.out.println("------");

        //проверка выполнения метода по заданию 2
        System.out.println(convertArrayToList(integerArray));
        System.out.println(convertArrayToList(floatArray));
        System.out.println(convertArrayToList(stringArray));
    }

    //1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)
    public static  <E> String changeFirstLastElements(E[] array) {
        if (array.length > 1) {
            E var = array[0];
            array[0] = array[array.length - 1];
            array[array.length - 1] = var;
            return Arrays.asList(array).toString();
        } else {
            return String.format("В массиве %s только один элемент", Arrays.toString(array));
        }
    }

    //2. Написать метод, который преобразует массив в ArrayList
    public static <E> ArrayList<E> convertArrayToList(E[] array) {
        return new ArrayList<E>(Arrays.asList(array));
    }
}
