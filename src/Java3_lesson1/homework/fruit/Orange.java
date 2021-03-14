package Java3_lesson1.homework.fruit;

public class Orange extends Fruit{
    public static final String TYPE = "Апельсин";

    public Orange() {
        super(1.5f, TYPE);
    }

    public Orange(int weight) {
        super(weight, TYPE);
    }
}
