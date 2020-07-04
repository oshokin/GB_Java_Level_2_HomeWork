package Lesson_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class CollectionsDemo {

    //1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
    private static String[] wordsContainer;
    private static HashMap<String, Integer> repeatStats;
    private static final int MAX_WORDS_COUNT = 30;
    private static final int MAX_REPEATS_COUNT = 3;

    public static void main(String[] args) {
        wordsContainer = RandValuesGenerator.getStringArray(MAX_WORDS_COUNT, MAX_REPEATS_COUNT);
        System.out.printf("Initial array is: %s%n", Arrays.deepToString(wordsContainer));
        System.out.printf("Amount of words (without repeats): %d%n", getUniqueItemsCount());
        repeatStats = getRepeatStats();
        System.out.println("Repeats stats:");
        repeatStats.forEach((k, v) -> System.out.printf("La palabra \"%s\" se repetio \"%d\" veces%n", k, v));
    }

    //1a. Найти список слов, из которых состоит текст (дубликаты не считать);
    private static int getUniqueItemsCount() {
        HashSet<String> hash = new HashSet<>(wordsContainer.length);
        for (String word: wordsContainer) {
            hash.add(word);
        }
        return hash.size();
    }

    //1b. Посчитать сколько раз встречается каждое слово (использовать HashMap);
    private static HashMap<String, Integer> getRepeatStats() {
        HashMap<String, Integer> funcResult = new HashMap<>(wordsContainer.length);
        for (String word: wordsContainer) {
            Integer numRepeats = funcResult.get(word);
            if (!(numRepeats instanceof Integer)) numRepeats = 0;
            funcResult.put(word, numRepeats + 1);
        }
        return funcResult;
    }
}
