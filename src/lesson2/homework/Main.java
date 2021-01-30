package lesson2.homework;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[][] arr1 = {{"1", "2", "3", "4"}, {"-1", "-2", "-3", "-4"},
                {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr2 = {{"1", "2", "3", "4"}, {"1", "2", "3", "sd"},
                {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        String[][] arr3 = new String[4][];
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = new String[i + 2];
        }

        String[][] arr4 = new String[2][5];

        fillArray(arr3);
        fillArray(arr4);

        System.out.println("Массив arr1:");
        showArray(arr1);
        sumArray(arr1);
        System.out.println("Массив arr2:");
        showArray(arr2);
        sumArray(arr2);
        System.out.println("Массив arr3:");
        showArray(arr3);
        sumArray(arr3);
        System.out.println("Массив arr4:");
        showArray(arr4);
        sumArray(arr4);

    }

    public static void sumArray(String[][] array) {
        try {
            sum(array);
        } catch (MyArraySizeException | MyArrayDataException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void sum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        MyArraySizeException e = new MyArraySizeException("Метод может обработать только массив размером 4х4");
        if (array.length != 4) {
            throw e;
        }
        for (String[] arrString : array) {
            if (arrString.length != 4) {
                throw e;
            }
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]);
                    sum += number;
                } catch (NumberFormatException exception) {
                    throw new MyArrayDataException(String.format(
                            "Данные в ячейке (строка %s; столбец %s) невозможно преобразовать в числовой формат",
                            i + 1, j + 1));
                }
            }
        }
        System.out.printf("Сумма всех членов массива = %s\n", sum);
    }

    public static void fillArray (String[][] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = String.valueOf(random.nextInt(10));
            }
        }
    }

    public static void showArray (String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
