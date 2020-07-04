package Lesson_1;

import Lesson_1.Obstacles.Track;
import Lesson_1.Obstacles.Wall;
import Lesson_1.Participants.Participant;
import Lesson_1.Participants.Cat;
import Lesson_1.Participants.Robot;
import Lesson_1.Participants.Human;
import Lesson_1.Obstacles.Obstacle;

//Здание 1: Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
//Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//Решение: Создал 3 класса в пакете Participants
//
//2. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
//участники должны выполнять соответствующие действия (бежать или прыгать),
// результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
//Решение: Создал 2 класса в пакете Obstacles
//
//3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
//
//4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
//Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

public class Arena {
    private static Participant[] victims;
    private static Obstacle[] obstacles;

    public static void main(String[] args) {
        fillVictims();
        fillObstacles();
        makeSomeFun();
    }

    private static void fillVictims() {
        int numVictims = (int) getRandomWithin(3, 100);
        victims = new Participant[numVictims];
        for (int i = 0; i < numVictims; i++) {
            int whoseTurn = (int) getRandomWithin(1, 3);
            int maxRunDistance = (int) getRandomWithin(0, 1000);
            int maxJumpDistance = (int) getRandomWithin(0, 1000);
            //можно и Class.getClassByName по int ключу предопределенного хешмапа получать, но свитч безопаснее
            switch (whoseTurn) {
                case 1:
                    victims[i] = new Cat("Полосатый " + (i + 1), maxRunDistance, maxJumpDistance);
                    break;
                case 2:
                    victims[i] = new Robot("Механический " + (i + 1), maxRunDistance, maxJumpDistance);
                    break;
                case 3:
                    victims[i] = new Human("Живой " + (i + 1), maxRunDistance, maxJumpDistance);
                    break;
            }
        }
    }

    private static void fillObstacles() {
        int numObstacles = (int) getRandomWithin(1, 30);
        obstacles = new Obstacle[numObstacles];
        for (int i = 0; i < numObstacles; i++) {
            int maxSize = (int) getRandomWithin(0, 1000);
            int whoseTurn = (int) getRandomWithin(1, 2);
            switch (whoseTurn) {
                case 1:
                    obstacles[i] = new Track("длиная " + (i + 1), maxSize);
                    break;
                case 2:
                    obstacles[i] = new Wall("высокая " + (i + 1), maxSize);
                    break;
            }
        }
    }

    private static void makeSomeFun() {
        for (Obstacle obs: obstacles) {
            System.out.printf("%s%n", obs);
            for (int i = 0; i < victims.length; i++) {
                Participant victim = victims[i];
                if (victim == null) continue;
                if (obs instanceof Track) {
                    if (!victim.run(obs, true)) victims[i] = null;
                } else if (obs instanceof Wall) {
                    if (!victim.jump(obs, true)) victims[i] = null;
                }
            }
        }
    }

    private static double getRandomWithin(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }
}