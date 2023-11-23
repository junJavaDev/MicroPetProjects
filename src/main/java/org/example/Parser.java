package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Parser {

    private static final String DESCRIPTION_SELECTOR = "div[data-track-load=description_content]";
    private static final String LINK_SELECTOR = "a.text-lg.font-medium";
    private static final String DRIVER = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "chrome/chromedriver";
    private static final String CHROME_OPTIONS = "--headless";


    public static String[] parse(String fullUrl) {
        String clazz = "";
        String commit = "";


        // ЗАПУСК
        System.setProperty(DRIVER, DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(CHROME_OPTIONS);
        WebDriver driver = new ChromeDriver(options);
        String url = trimUrl(fullUrl);
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(DESCRIPTION_SELECTOR)));

        // ССЫЛКА
        WebElement linkElement = driver.findElement(By.cssSelector(LINK_SELECTOR));
        String linkText = linkElement.getText();

        // ИМЯ КЛАССА
        String className = convertToValidClassName(linkText);

        //КЛАСС
        String description = String.format("<a href=\"%s\">%s</a>\n", url, linkText);
        description += driver
                .findElement(By.cssSelector(DESCRIPTION_SELECTOR))
                .getAttribute("outerHTML");
        clazz = String.format("package ru.jvst.leetcode;\n\n/**\n%s\n**/\n\nclass %s {\n    \n}",
                description, className);


        // КОММИТ
        commit = String.format("LeetCode \"%s\" done", linkText);

        driver.quit();

        return new String[] {clazz, commit};
    }

    private static String trimUrl(String inputUrl) {
        int idx = inputUrl.indexOf('?');
        if (idx < 0) return inputUrl;
        return inputUrl.substring(0, idx);
    }

    private static String convertToValidClassName(String text) {
        // Удаляем цифры в начале строки до первого спецсимвола
        text = text.replaceFirst("^\\d+", "");

        // Удаляем все спецсимволы, кроме цифр и букв
        text = text.replaceAll("[^\\p{L}\\p{N}\\s]", "");

        // Преобразуем в camel case
        String[] words = text.split("\\s+|(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        StringBuilder camelCaseText = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                camelCaseText.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());  // преобразовать в CamelCase
            }
        }
        text = camelCaseText.toString();

        // Переносим цифры с начала в конец
        String nonDigitPart = "";
        String digitPart = "";
        int indexFirstLetter = text.length();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                if (indexFirstLetter == text.length()) {
                    digitPart += text.charAt(i);
                } else {
                    nonDigitPart += text.charAt(i);
                }
            } else {
                indexFirstLetter = i;
                nonDigitPart += text.charAt(i);
            }
        }
        text = nonDigitPart + digitPart;

        return text;
    }
}