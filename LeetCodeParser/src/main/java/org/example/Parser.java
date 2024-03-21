package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Parser {

    private static final String DESCRIPTION_SELECTOR = "div[data-track-load=description_content]";
    private static final String LINK_SELECTOR = "a.no-underline.truncate.cursor-text.whitespace-normal";
    private static final String HEADLESS_UI_SELECTOR = "//button[text()='C++']";
    private static final String JAVA_PUBLIC_SELECTOR = "//span[@class='mtk4' and text()='public']";
    private static final String JAVA_DIV_SELECTOR = "//div[text()='Java']";
    private static final String CODE_SELECTOR = "//*[@id=\"editor\"]/div[2]/div[1]/div/div/div[1]/div[2]/div[1]/div[5]";
//    private static final String[] CHROME_OPTIONS = new String[]{"headless"};

    public static String[] parse(String fullUrl) throws InterruptedException {
        String clazz;
        String commit;

        // ЗАПУСК
        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(CHROME_OPTIONS));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String url = trimUrl(fullUrl);
        driver.get(url);

        System.out.println(driver.getPageSource());
        // ССЫЛКА
        WebElement linkElement = waitUntilAndGetByCssSelector(driver, wait, LINK_SELECTOR);
        WebElement headlessUi = waitUntilAndGetByXpath(driver, wait, HEADLESS_UI_SELECTOR);
        if (headlessUi != null) {
            headlessUi.click();
        }
        WebElement javaDiv = waitUntilAndGetByXpath(driver, wait, JAVA_DIV_SELECTOR);
        if (javaDiv != null) {
            javaDiv.click();
        }

        waitUntilAndGetByXpath(driver, wait, JAVA_PUBLIC_SELECTOR);
        String fullCode = Objects.requireNonNull(waitUntilAndGetByXpath(driver, wait, CODE_SELECTOR)).getText();


        String code = fullCode.substring(17, fullCode.length()-16);

        assert linkElement != null;
        String linkText = linkElement.getText();
        // ИМЯ КЛАССА
        String className = convertToValidClassName(linkText);

        //КЛАСС
        String description = String.format("<a href=\"%s\">%s</a>\n", url, linkText);
        description += driver
                .findElement(By.cssSelector(DESCRIPTION_SELECTOR))
                .getAttribute("outerHTML");
        clazz = String.format("package ru.jvst.leetcode;\n\n/**\n%s\n**/\n\nclass %s {\n%s\n        \n    }\n}",
                description, className,code);


        // КОММИТ
        commit = String.format("LeetCode \"%s\" done", linkText);
        driver.quit();

        return new String[]{clazz, commit};
    }

    private static String trimUrl(String inputUrl) {
        int idx = inputUrl.indexOf('?');
        if (idx < 0) return inputUrl;
        return inputUrl.substring(0, idx);
    }

    private static WebElement waitUntilAndGetByXpath(WebDriver driver, WebDriverWait wait, String xpath) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return driver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("Элемент " + xpath + " не найден на странице");
        }
        return null;
    }

    private static WebElement waitUntilAndGetByCssSelector(WebDriver driver, WebDriverWait wait, String cssSelector) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            return driver.findElement(By.cssSelector(cssSelector));
        } catch (Exception e) {
            System.out.println("Элемент " + cssSelector + " не найден на странице");
        }
        return null;
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
        StringBuilder nonDigitPart = new StringBuilder();
        StringBuilder digitPart = new StringBuilder();
        int indexFirstLetter = text.length();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                if (indexFirstLetter == text.length()) {
                    digitPart.append(text.charAt(i));
                } else {
                    nonDigitPart.append(text.charAt(i));
                }
            } else {
                indexFirstLetter = i;
                nonDigitPart.append(text.charAt(i));
            }
        }
        text = nonDigitPart.toString() + digitPart;

        return text;
    }
}