package Lesson_2;

//5. Написать собственные классы исключений для каждого из случаев

public class MyMatrixSizeException extends Exception {
    public MyMatrixSizeException(String message) {
        super(message);
    }
}
