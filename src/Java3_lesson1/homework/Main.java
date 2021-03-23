package Java3_lesson1.homework;

import Java3_lesson1.homework.boxes.Box;
import Java3_lesson1.homework.boxes.LargeBox;
import Java3_lesson1.homework.boxes.SmallBox;
import Java3_lesson1.homework.boxes.StandardBox;
import Java3_lesson1.homework.fruit.Apple;
import Java3_lesson1.homework.fruit.Fruit;
import Java3_lesson1.homework.fruit.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        //задача 3.
        Box<Apple> box1 = new SmallBox<>();
        System.out.println(box1.getLabel());

        Box<Apple> box2 = new StandardBox<>();
        Box<Apple> box3 = new LargeBox<>();
        Box<Orange> box4 = new SmallBox<>();
        Box<Orange> box5 = new StandardBox<>();
        Box<Orange> box6 = new LargeBox<>();

        List<Box<Apple>> appleBoxes = new ArrayList<>(Arrays.asList(box1, box2, box3));
        List<Box<Orange>> orangeBoxes = new ArrayList<>(Arrays.asList(box4,box5,box6));

        //общий список всех коробок
        List<Box<? extends Fruit>> boxes = new ArrayList<>(appleBoxes);
        boxes.addAll(orangeBoxes);

        System.out.println("------");
        //заполняю все коробки по 20 фруктов соответствующего типа
        for (int i = 0; i < 20; i++) {
            appleBoxes.forEach(b -> b.addOneFruit(new Apple()));
            orangeBoxes.forEach(b -> b.addOneFruit(new Orange()));
        }

        //попытка заполнить фруктами больше вместимости
        System.out.println(box5.getLabel());
        System.out.printf("Засыпаем 100 апельсинов в коробку %s\n", box5);
        for (int i = 0; i < 100; i++) {
            if (!box5.addOneFruit(new Orange())) {
                System.out.printf("Коробка полна, добавлено %s фруктов\n", i);
                System.out.println(box5.getLabel());
                break;
            }
        }

        System.out.println("------");
        //проверка метода compare(), сравнение веса всех коробок с весом самой первой коробки
        for (Box<?> box : boxes) {
            System.out.println(box.getLabel());
            if (box != box1) {
                if (box1.compare(box)) {
                    System.out.printf("%s и %s имеют одинаковый вес\n", box, box1);
                } else {
                    System.out.printf("%s и %s имеют разный вес\n", box, box1);

                }
            }
        }
        System.out.println("------");
        //проверка метода для пересыпания фруктов из коробки в коробку
        System.out.println("Пересыпаем яблоки из коробки2 в коробку1 с яблоками");
        box1.loadFruitFrom(box2);
        System.out.println(box1.getLabel());
        System.out.println(box2.getLabel());
        System.out.println("Пересыпаем яблоки из коробки3 в коробку1 с яблоками");
        box1.loadFruitFrom(box3);
        System.out.println(box1.getLabel());
        System.out.println(box3.getLabel());

        //попытка пересыпать апельсины в коробку с яблоками дает ошибку времени компиляции - java: incompatible types
        //box3.loadFruitFrom(box4);
        //box3.loadFruitFrom(new SmallBox<Orange>());
    }

    //1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)
    public static <E> String changeFirstLastElements(E[] array) {
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
        return new ArrayList<>(Arrays.asList(array));
    }
}
