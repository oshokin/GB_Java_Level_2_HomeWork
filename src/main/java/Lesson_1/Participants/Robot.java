package Lesson_1.Participants;

import Lesson_1.Obstacles.Obstacle;

public class Robot implements Participant {
    private String name;
    private double maxRunDistance;
    private double maxJumpDistance;

    public String getName() { return name; }

    public double getMaxRunDistance() {
        return maxRunDistance;
    }

    public double getMaxJumpDistance() {
        return maxJumpDistance;
    }

    public Robot(String name, double maxRunDistance, double maxJumpDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpDistance = maxJumpDistance;
    }

    public boolean run(Obstacle obs, boolean consoleOut) {
        double obsSize = obs.getSize();
        boolean result = (obsSize <= maxRunDistance);
        if (consoleOut) {
            System.out.printf("Робот \"%s\" %sсмог пробежать препятствие%n", getName(), result ? "" : "не ");
            if (!result) System.out.printf("Максимальная дистанция: %.2f м.%n", maxRunDistance);
        }
        return result;
    }

    public boolean jump(Obstacle obs, boolean consoleOut) {
        double obsSize = obs.getSize();
        boolean result = (obsSize <= maxJumpDistance);
        if (consoleOut) {
            System.out.printf("Робот \"%s\" %sсмог перепрыгнуть препятствие%n", getName(), result ? "" : "не ");
            if (!result) System.out.printf("Максимальная высота прыжка: %.2f м.%n", maxJumpDistance);
        }
        return result;
    }
}