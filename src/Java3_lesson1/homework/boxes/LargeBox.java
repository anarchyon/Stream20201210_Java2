package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

public class LargeBox<E extends Fruit> extends Box<E> {

    public LargeBox(E fruit, int amountOfStoredFruit) {
        super(fruit, amountOfStoredFruit);
        setCapacity(Box.CAPACITY_LARGE_BOX);
    }
}
