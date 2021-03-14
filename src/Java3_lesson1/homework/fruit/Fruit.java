package Java3_lesson1.homework.fruit;

public abstract class Fruit {
    private float weight;
    private String type;

    public Fruit(float weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return type;
    }
}
