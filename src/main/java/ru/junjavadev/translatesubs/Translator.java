package ru.junjavadev.translatesubs;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Translator {
    public static void translateFile(File file) {
        try {
            String fileToTranslate = String.valueOf(file);
            String outputFile = fileToTranslate.replace(".en.", ".ru.");
            List<String> inputLines = Files.readAllLines(Path.of(fileToTranslate));
            List<String> outputLines = new ArrayList<>();
            for (String line : inputLines) {
                if (containAlpha(line)) {
                    outputLines.add(YandexConnection.translate(line));
                } else {
                    outputLines.add(line);
                }
            }
            Files.write(Path.of(outputFile), outputLines);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean containAlpha(String string) {
        if (string == null || StringUtils.isBlank(string)) {
            return false;
        }
        for (char c : string.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return true;
            }
        }
        return false;
    }

}
