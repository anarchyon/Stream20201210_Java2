package Java3_lesson1.homework.fruit;

public class Apple extends Fruit{
    public static final String TYPE = "Яблоко";

    public Apple() {
        super(1.0f, TYPE);
    }

    //конструктор для создания яблока с определенным весом, например вес по рандому
    public Apple(int weight){
        super(weight, TYPE);
    }
}
