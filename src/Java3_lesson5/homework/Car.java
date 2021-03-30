package Java3_lesson5.homework;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable{
    private static AtomicInteger CARS_COUNT;
    private static AtomicBoolean IS_WIN;
    private static Lock lock;
    private final CyclicBarrier barrier;
//    private final CountDownLatch cdlWaitStart;

    static {
        CARS_COUNT = new AtomicInteger(0);
        IS_WIN = new AtomicBoolean(false);
        lock = new ReentrantLock();
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT.incrementAndGet();
        this.name = "Участник #" + CARS_COUNT;
        this.barrier = barrier;
//        this.cdlWaitStart = cdlWaitStart;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
//            cdlWaitStart.countDown();
            barrier.await();
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        isWinner();
    }

    private void isWinner() {
        try {
            lock.lock();
            if (!IS_WIN.get()) System.out.println(this.name + " WIN");
            IS_WIN.compareAndSet(false, true);
        } finally {
            lock.unlock();
        }

    }
}

