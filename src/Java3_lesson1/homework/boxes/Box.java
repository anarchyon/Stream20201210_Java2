package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

import java.util.Stack;

public abstract class Box<E extends Fruit> {
    public static final float CAPACITY_SMALL_BOX = 50f;
    public static final float CAPACITY_STANDARD_BOX = 75f;
    public static final float CAPACITY_LARGE_BOX = 130f;

    private final Stack<E> storedFruit;
    private float weight;
    private float capacity;

    public Box(E fruit, int amountOfStoredFruit) {
        storedFruit = new Stack<>();
        for (int i = 0; i < amountOfStoredFruit; i++) {
            if (!addOneFruit(fruit)) {
                break;
            }
        }
    }

    public float getWeight() {
        return weight;
    }

    public boolean compare(Box<?> comparedBox) {
        return Math.abs(this.getWeight() - comparedBox.getWeight()) < 0.001;
    }

    public boolean addOneFruit(E fruit) {
        if (weight + fruit.getWeight() <= capacity) {
            storedFruit.push(fruit);
            weight += fruit.getWeight();
            return true;
        }
        return false;
    }

    public void removeOneFruit() {
        if (!storedFruit.empty()) {
            storedFruit.pop();
        }
    }

    public void loadFruit(Box<E> box) {
        for (int i = 0; i < box.storedFruit.size(); i++) {
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
}
