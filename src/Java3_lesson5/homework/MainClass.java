package Java3_lesson5.homework;

import java.util.List;
import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    private static CountDownLatch cdl;
    private static CyclicBarrier b;
    private static ExecutorService executorService;

    public static void main(String[] args) {
        //синхронизатор потоку main для ожидания готовности всех участников и объявления начала гонки
//        cdl = new CountDownLatch(CARS_COUNT);
        //синхронизатор для машин, чтоб все ждали объявления начала гонки
        b = new CyclicBarrier(CARS_COUNT + 1);
        //синхронизатор для тоннеля с открытием доступа к тоннелю по fifo
        Semaphore semaphoreForTunnel = new Semaphore(CARS_COUNT / 2, true);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(semaphoreForTunnel), new Road(40));


        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), b);
        }

        executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for (Car car : cars) {
            executorService.submit(car);
        }

        executorService.shutdown();
        waitCarsReady();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        waitCarsReady();

        //ожидание завершения всех потоков, для объявления конца гонок
        waitCarsFinish();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    private static void waitCarsReady() {
        try {
            b.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private static void waitCarsFinish() {
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
