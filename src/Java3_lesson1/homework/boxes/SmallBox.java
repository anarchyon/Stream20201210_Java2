package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

public class SmallBox<E extends Fruit> extends Box<E>{

    public SmallBox(E fruit, int amountOfStoredFruit) {
        super(fruit, amountOfStoredFruit);
        setCapacity(Box.CAPACITY_SMALL_BOX);
    }
}
