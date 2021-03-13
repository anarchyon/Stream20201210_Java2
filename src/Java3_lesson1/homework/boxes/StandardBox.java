package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

public class StandardBox<E extends Fruit> extends Box<E> {

    public StandardBox(E fruit, int amountOfStoredFruit) {
        super(fruit, amountOfStoredFruit);
        setCapacity(Box.CAPACITY_STANDARD_BOX);
    }
}
