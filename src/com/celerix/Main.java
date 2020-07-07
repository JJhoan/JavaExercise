package com.celerix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        final String FILE_NAME = "/home/jjhoan/listNumbers.txt";

        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            List<String> listNumbers = new ArrayList<>();
            stream.takeWhile(line -> !line.equals("0"))
                    .forEach(line -> saveLine(listNumbers, line));
            writeFile(listNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLine(List<String> listNumbers, String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            stack.push(c);
        }
        listNumbers.add(getNumber(stack));
    }

    public static void writeFile(List<String> numbers) throws IOException {
        final String FILE_REVERSE_NUMBERS = "/home/jjhoan/reverseNumbers.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_REVERSE_NUMBERS));
        numbers.forEach(s -> {
            try {
                writer.write(s + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }


    private static String getNumber(Stack<Character> stack) {
        StringBuilder builder = new StringBuilder();

        while (!stack.isEmpty()) {
            final char num = stack.pop();
            builder.append(num);
        }

        return String.valueOf(builder);

    }
}
