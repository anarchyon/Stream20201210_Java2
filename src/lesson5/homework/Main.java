package lesson5.homework;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];


    public static void main(String[] args) {

        //вычисления в главном потоке
        fillArray();
        long a = System.currentTimeMillis();
        calculateArray(arr, 0);
        System.out.println("One thread: " + (System.currentTimeMillis() - a));

        //вычисления в двух потоках: главном потоке и дополнительно созданном потоке t1
        fillArray();
        a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread t1 = new Thread(() -> calculateArray(a1, 0), "t1");
        t1.start();
        calculateArray(a2, h);
        if (t1.isAlive()) {
            try {
                t1.join();
            } catch (InterruptedException e) {
                System.out.println("Thread " + t1.getName() + " was interrupted");
            }
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Two thread: " + (System.currentTimeMillis() - a));

        //вычисления в двух потоках, независимых от главного
        fillArray();
        a = System.currentTimeMillis();
        twoThreadMethod();
        System.out.println("Two thread (second variant): " + (System.currentTimeMillis() - a));
    }

    //заполнение массива единичками
    public static void fillArray() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }
    }

    public static void calculateArray(float[] array, int shift) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i + shift) / 5) *
                    Math.cos(0.2f + (i + shift) / 5) * Math.cos(0.4f + (i + shift) / 2));
        }
    }

    private static void twoThreadMethod() {
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread threadOne = new Thread(() -> calculateArray(a1, 0));
        Thread threadTwo = new Thread(() -> calculateArray(a2, h));
        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            System.out.println("Some thread was interrupted");
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
    }
}
