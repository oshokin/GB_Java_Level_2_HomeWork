package Lesson_5;

import java.util.Arrays;

public class ThreadsDemo {
    static final int size = 10_000_000;
    static final int maxThreads = 20;

    public static void main(String[] args) {
        System.out.printf("Array size (el tamanho de conjunto) is %d items%n", size);
        System.out.println("First, we gonna try the sequential calculation (Primero, vamos a ver como funcciona la calculacion sequencial)");
        System.out.println("Then, we'll see how threads can speed up our task (Despues, veremos como va con los threads)");
        sequentialCalculation();
        parallelCalculation(maxThreads);
    }

    private static void sequentialCalculation() {
        //1) Создают одномерный длинный массив, например:
        float[] testArray = new float[size];
        //2) Заполняют этот массив единицами;
        Arrays.fill(testArray, 1);
        //3) Засекают время выполнения: long a = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        //4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = (float) (testArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        //5) Проверяется время окончания метода System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        //6) В консоль выводится время работы:
        System.out.printf("Who's the Speedy Racer over here? Ok, duration is: %d ms%n", endTime - startTime);
    }

    private static void parallelCalculation(int numThreads) {
        //1) Создают одномерный длинный массив, например:
        float[] testArray = new float[size];
        //2) Заполняют этот массив единицами;
        Arrays.fill(testArray, 1);
        int blockSize = size / numThreads;
        if (blockSize < 100) {
            System.out.println("C'mon, who you're trying to fool? Minimum block size is 100 items!");
            return;
        }
        //3) Засекают время выполнения: long a = System.currentTimeMillis();
        Thread[] threadPool = new Thread[numThreads];
        long[] threadDuration = new long[numThreads];
        ItemsInterval[] blockSizes = new ItemsInterval[numThreads];
        int upperBound = testArray.length - 1;
        for (int i = 0; i < numThreads; i++) {
            final int firstIndex = i * blockSize;
            if (firstIndex > upperBound) break;
            final int lastIndex = Integer.min(upperBound, firstIndex + blockSize - 1);
            if (lastIndex > upperBound || firstIndex - lastIndex == 0) break;
            blockSizes[i] = new ItemsInterval(firstIndex, lastIndex);
            threadPool[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    calculateBlock(testArray, firstIndex, lastIndex);
                }
            });
        }
        for (int i = 0; i < numThreads; i++) {
            System.out.printf("Запускаем поток №%d%n", i + 1);
            long startTime = System.currentTimeMillis();
            threadPool[i].start();
            try {
                threadPool[i].join();
            } catch (InterruptedException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            //5) Проверяется время окончания метода System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            threadDuration[i] = endTime - startTime;
            System.out.printf("Поток №%d отработал%n", i + 1);
        }
        long totalDuration = Arrays.stream(threadDuration).sum();
        for (int i = 0; i < numThreads; i++) {
            //6) В консоль выводится время работы:
            System.out.printf("Поток №%d выполнялся %d мс. Блок (%d <-> %d), размер: %d элементов%n", i + 1, threadDuration[i],
                    blockSizes[i].firstIndex, blockSizes[i].lastIndex, blockSizes[i].getSize());
        }
        System.out.printf("Параллельное выполнение заняло %d мс. Кто тут самый крутой параллелизатор?", totalDuration);
    }

    //4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    private static void calculateBlock(float[] testArray, final int firstIndex, final int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            testArray[i] = (float) (testArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
    private static class ItemsInterval {
        int firstIndex;
        int lastIndex;

        public ItemsInterval(int firstIndex, int lastIndex) {
            this.firstIndex = firstIndex;
            this.lastIndex = lastIndex;
        }

        public int getSize() {
            return lastIndex - firstIndex;
        }

    }
}