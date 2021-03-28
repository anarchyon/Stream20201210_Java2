package Java3_lesson5.homework;

import java.util.concurrent.Semaphore;

public abstract class Stage {
    protected int length;
    protected String description;
    protected Semaphore semaphoreForStage;

    public Stage(){
        semaphoreForStage = null;
    }

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);

    public Semaphore getSemaphoreForStage() {
        return semaphoreForStage;
    }

    public void setSemaphoreForStage(Semaphore semaphoreForStage) {
        this.semaphoreForStage = semaphoreForStage;
    }
}
