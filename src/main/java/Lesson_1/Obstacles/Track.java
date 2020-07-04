package Lesson_1.Obstacles;

public class Track implements Obstacle{
    double size;
    String name;

    public double getSize() { return size; }

    public String getName() { return name; }

    public Track(String name, double size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Беговая дорожка \"" + getName() + "\", длина: " + String.format("%.2f", getSize()) + " м";
    }
}
