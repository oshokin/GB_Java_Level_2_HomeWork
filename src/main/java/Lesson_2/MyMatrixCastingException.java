package Lesson_2;

//5. Написать собственные классы исключений для каждого из случаев

public class MyMatrixCastingException extends Exception {
    public MyMatrixCastingException(String message) {
        super(message);
    }
}
