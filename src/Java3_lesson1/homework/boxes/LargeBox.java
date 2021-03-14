package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

public class LargeBox<E extends Fruit> extends Box<E> {

    public LargeBox() {
        super(Box.CAPACITY_LARGE_BOX);
    }
}
