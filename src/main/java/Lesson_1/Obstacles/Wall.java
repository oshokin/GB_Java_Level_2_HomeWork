package Lesson_1.Obstacles;

public class Wall implements Obstacle{
    double size;
    String name;

    public double getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Wall(String name, double size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Стена \"" + getName() + "\", длина: " + String.format("%.2f", getSize()) + " м";
    }
}
