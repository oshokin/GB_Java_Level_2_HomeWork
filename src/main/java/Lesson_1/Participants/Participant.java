package Lesson_1.Participants;

import Lesson_1.Obstacles.Obstacle;

public interface Participant {
    boolean run(Obstacle obs, boolean consoleOut);
    boolean jump(Obstacle obs, boolean consoleOut);
}