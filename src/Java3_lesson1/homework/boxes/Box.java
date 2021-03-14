package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Box<E extends Fruit> {
    public static final float CAPACITY_SMALL_BOX = 50f;
    public static final float CAPACITY_STANDARD_BOX = 80f;
    public static final float CAPACITY_LARGE_BOX = 130f;
    public static int counter;

    private final Stack<E> storedFruit;
    private float capacity;
    private final int number;

    public Box(float capacity) {
        storedFruit = new Stack<>();
        this.capacity = capacity;
        number = ++counter;
    }

    public float getWeight() {
        float weight = 0;
        for (Fruit f : storedFruit) {
            weight += storedFruit.peek().getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> comparedBox) {
        return Math.abs(this.getWeight() - comparedBox.getWeight()) < 0.001;
    }

    public boolean addOneFruit(E fruit) {
        if (getWeight() + fruit.getWeight() <= capacity) {
            storedFruit.push(fruit);
            return true;
        }
        return false;
    }

    public void removeOneFruit() {
        if (!storedFruit.empty()) {
            storedFruit.pop();
        }
    }

    //Box<? extends E> - если у яблока появятся наследники, например, разные сорта
    public void loadFruitFrom(Box<? extends E> box) {
        int limit = box.storedFruit.size();
        for (int i = 0; i < limit; i++) {
            if (addOneFruit(box.storedFruit.peek())) {
                box.removeOneFruit();
            } else {
                return;
            }
        }
    }

    protected void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public float getCapacity() {
        return capacity;
    }

    public String getLabel() {
        if (storedFruit.empty()) {
            return toString() + " пуста";
        }
        return String.format("%s, вместимость - %s весовых единиц, содержимое: %s - %s шт., %s весовых единиц",
                toString(),
                getCapacity(),
                storedFruit.peek().toString(),
                storedFruit.size(),
                getWeight());
    }

    @Override
    public String toString() {
        return String.format("Коробка %d", number);
    }
}
