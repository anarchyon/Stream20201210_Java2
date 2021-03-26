package Java3_lesson2.work;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        new Thread(m::method1).start();
        new Thread(m::method2).start();
        new Thread(m::method3).start();
    }

    private synchronized void method1() {
        System.out.println("Method 1 start");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Method 1 stop");
    }

    private synchronized void method2() {
        System.out.println("Method 2 start");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Method 2 stop");
    }

    private void method3() {
        System.out.println("Method 3 start");
        for (int i = 0; i < 5; i++) {
            System.out.println("m3_" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Method 3 stop");
    }
}
