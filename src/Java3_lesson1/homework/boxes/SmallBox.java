package Java3_lesson1.homework.boxes;

import Java3_lesson1.homework.fruit.Fruit;

public class SmallBox<E extends Fruit> extends Box<E>{

    public SmallBox() {
        super(Box.CAPACITY_SMALL_BOX);
    }
}
