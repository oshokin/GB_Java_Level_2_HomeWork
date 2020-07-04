package Lesson_2;

public class ExceptionsDemo {
    private static String testString = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    private static String[][] uncastedMatrix;
    private static int[][] integerMatrix;
    private static int calcResult;
    private static final int MATRIX_ALLOWED_SIZE = 4;

    public static void main(String[] args) {
        //4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.
        try {
            uncastedMatrix = getStringMatrix(testString, MATRIX_ALLOWED_SIZE);
        } catch (MyMatrixSizeException e) {
            System.out.printf("We've got young hacker here! Your exception is: %n%s%n", e.getMessage());
            System.exit(1);
        }
        try {
            integerMatrix = castMatrixToInteger(uncastedMatrix);
        } catch (MyMatrixCastingException e) {
            System.out.printf("Tu matriz no es la que esperaba yo. Perdoname, tu excepcion es la siguente: %n%s%n", e.getMessage());
            System.exit(1);
        }
        //2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
        calcResult = sumMatrixDivideByTwo(integerMatrix);
        System.out.printf("Let's write it down in SQL way:%n%nSELECT%nSUMM(Matrix) / 2 AS result%n%n%nresult = %d%n", calcResult);
    }

    //1. Написать метод, на вход которого подаётся строка, метод должен преобразовать строку в двумерный массив типа String[][];
    //3. Ваши методы должны бросить исключения в случаях:
    //   Если размер матрицы, полученной из строки, не равен 4x4;
    private static String[][] getStringMatrix(String matrixSource, int allowedSize) throws MyMatrixSizeException {
        String[] matrixSplit = matrixSource.split("\n");
        String[][] funcResult = new String[allowedSize][allowedSize];
        if (matrixSplit.length != allowedSize) throw new MyMatrixSizeException("Tu matriz es demasiado grande o chica");
        for (int i = 0; i < matrixSplit.length; i++) {
            String row = matrixSplit[i];
            String[] rowSplit = row.split(" ");
            if (rowSplit.length != allowedSize) throw new MyMatrixSizeException("Tu matriz es INVALIDA, jajaja!");
            for (int j = 0; j < rowSplit.length; j++) {
                funcResult[i][j] = rowSplit[j];
            }
        }
        return funcResult;
    }

    //3. Ваши методы должны бросить исключения в случаях:
    //   Если в одной из ячеек полученной матрицы не число; (например символ или слово)
    private static int[][] castMatrixToInteger(String[][] stringMatrix) throws MyMatrixCastingException {
        int matrixSize = stringMatrix.length;
        int[][] funcResult = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            String[] curMatrix = stringMatrix[i];
            for (int j = 0; j < curMatrix.length; j++) {
                String castString = curMatrix[j];
                try {
                    funcResult[i][j] = Integer.parseInt(castString);
                } catch (NumberFormatException e) {
                    throw new MyMatrixCastingException(String.format("En la fila \"%d\" y la columna \"%d\" se encuentra algo raro: %s", i + 1, j + 1, castString));
                }
            }
        }
        return funcResult;
    }

    private static int sumMatrixDivideByTwo(int[][] integerMatrix) throws ArrayIndexOutOfBoundsException {
        int funcResult = 0;
        for (int[] row: integerMatrix) {
            for (int column:row) funcResult += column;
        }
        return funcResult / 2;
    }
}
