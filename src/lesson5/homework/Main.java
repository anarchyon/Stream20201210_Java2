package lesson5.homework;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr1 = new float[size];
        float[] arr2 = new float[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = 1f;
            arr2[i] = 1f;
        }

        long a = System.currentTimeMillis();
        commonMethod(arr1);
        System.out.println("One thread: " + (System.currentTimeMillis() - a));

        a = System.currentTimeMillis();
        twoThreadMethod(arr2);
        System.out.println("Two thread: " + (System.currentTimeMillis() - a));
    }

    public static void commonMethod(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void twoThreadMethod(float[] arr) {
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        new Thread(() -> commonMethod(a1));
        new Thread(() -> commonMethod(a2));
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
    }
}
