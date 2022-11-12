package ru.otus.erinary.algo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Утилитный класс, позволяющий формировать кейсы для тестирования из файлов заданного формата.
 */
public class FileResourcesUtils {

    private static final String PREFIX = "test.";
    private static final String IN_SUFFIX = ".in";
    private static final String OUT_SUFFIX = ".out";

    /**
     * Формирует набор кейсов для тестирования.
     *
     * @param directoryPath путь к директории с файлами для тестов
     * @return набор {@link TestCase}
     */
    @SuppressWarnings("StringBufferReplaceableByString")
    public static List<TestCase> createTestCases(final String directoryPath) {
        int filesCount = Optional.ofNullable(new File(directoryPath).listFiles())
                .map(files -> files.length).orElse(0);
        var testCases = new ArrayList<TestCase>();

        for (int i = 0; i < filesCount / 2; i++) {
            var inputFileName = new StringBuilder(directoryPath)
                    .append(PREFIX).append(i).append(IN_SUFFIX).toString();
            var inputData = readFile(inputFileName).entrySet().stream().findFirst()
                    .map(Map.Entry::getValue).orElse(null);

            var outputFileName = new StringBuilder(directoryPath)
                    .append(PREFIX).append(i).append(OUT_SUFFIX).toString();
            var outputData = readFile(outputFileName);
            testCases.add(new TestCase(i, inputData, outputData));
        }

        return testCases;
    }

    private static Map<Integer, String> readFile(final String path) {
        try (var reader = new BufferedReader(new FileReader(path))) {
            String line;
            int count = 1;
            var map = new HashMap<Integer, String>();
            while ((line = reader.readLine()) != null) {
                map.put(count, line);
                count++;
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Модель кейса для тестирования.
     */
    public static class TestCase {
        private final int testNumber;
        private final String input;
        private final Map<Integer, String> output;

        /**
         * Конструктор {@link TestCase}.
         *
         * @param testNumber порядковый номер кейса
         * @param input      исходные данные
         * @param output     ожидаемый результат
         */
        public TestCase(final int testNumber, final String input, final Map<Integer, String> output) {
            this.testNumber = testNumber;
            this.input = input;
            this.output = output;
        }

        public String getInput() {
            return input;
        }

        public Map<Integer, String> getOutput() {
            return output;
        }

        public String getTestNumber() {
            return "Test number: " + testNumber;
        }

    }
}
