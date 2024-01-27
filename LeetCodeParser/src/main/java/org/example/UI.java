package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class UI {
    private static String[] result;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Парсер текста из LeetCode");
        ClassLoader classLoader = UI.class.getClassLoader();
        java.net.URL iconURL = classLoader.getResource("leetcode.png");
        assert iconURL != null;
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());

        frame.setSize(400, 100); // Установка размера окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton copyClazzButton = new JButton("Класс");
        styleButton(copyClazzButton);

        JButton copyCommitButton = new JButton("Коммит");
        styleButton(copyCommitButton);

        copyClazzButton.addActionListener(e -> {
            try {
                String clipboardText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                result = Parser.parse(clipboardText);
                copyToClipboard(result[0]);
            } catch (UnsupportedFlavorException | IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        copyCommitButton.addActionListener(e -> {
            if (result != null) {
                String text = result[1];
                copyToClipboard(text);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Добавляем панель для кнопок "Вставить" и "Отправить"
        frame.add(buttonPanel);

        JPanel buttonPanel2 = new JPanel(new GridLayout(1, 3, 10, 10)); // Добавляем панель для остальных трех кнопок
        buttonPanel2.add(copyClazzButton);
        buttonPanel2.add(copyCommitButton);

        frame.add(buttonPanel2);
        frame.setVisible(true);
    }

    private static void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 50)); // Фиксированный размер кнопок
        button.setFont(button.getFont().deriveFont(20.0f)); // Увеличение текста внутри кнопок
    }

    private static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
}
