package lesson2.homework;

public class Main {
    public static void main(String[] args) {
        String[][] arr1 = {{"1", "2", "3", "4"}, {"-1", "-2", "-3", "-4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr2 = {{"1", "s", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        String[][] arr3 = new String[4][];
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = new String[i + 2];
        }

        String[][] arr4 = new String[2][5];

        System.out.println("Массив arr1:");
        sumArray(arr1);
        System.out.println("Массив arr2:");
        sumArray(arr2);
        //sumArray(arr3);

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
                            "Данные в ячейке (%s; %s) невозможно преобразовать в числовой формат", i, j));
                }
            }
        }
        System.out.printf("\nСумма всех членов массива = %s\n", sum);
    }

}
