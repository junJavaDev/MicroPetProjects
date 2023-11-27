package ru.jvst;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateChangerAppSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Git Commit Date");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel datePanel = new JPanel();
        datePanel.setPreferredSize(new Dimension(420, 40));

        Integer[] years = {2020, 2021, 2022, 2023, 2024};
        JComboBox<Integer> yearDropdown = new JComboBox<>(years);
        yearDropdown.setPreferredSize(new Dimension(80, 30));

        Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        JComboBox<Integer> monthDropdown = new JComboBox<>(months);
        monthDropdown.setPreferredSize(new Dimension(80, 30));

        JComboBox<Integer> dayDropdown = new JComboBox<>();
        dayDropdown.setPreferredSize(new Dimension(80, 30));

        updateDayDropdown(yearDropdown, monthDropdown, dayDropdown);

        datePanel.add(dayDropdown);
        datePanel.add(monthDropdown);
        datePanel.add(yearDropdown);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(420, 40));
        JButton executeButton = new JButton("Обновить");
        executeButton.setPreferredSize(new Dimension(130, 33));
        JButton executeNextButton = new JButton("Обновить++");
        executeNextButton.setPreferredSize(new Dimension(130, 33));

        buttonPanel.add(executeButton);
        buttonPanel.add(executeNextButton);

        // Чтение даты из файла "LastCommit.txt" при запуске приложения
        try (BufferedReader br = new BufferedReader(new FileReader("LastCommit.txt"))) {
            String lastCommitDate = br.readLine();
            LocalDate lastDate = LocalDate.parse(lastCommitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            yearDropdown.setSelectedItem(lastDate.getYear());
            monthDropdown.setSelectedItem(lastDate.getMonthValue());
            dayDropdown.setSelectedItem(lastDate.getDayOfMonth());
        } catch (IOException | DateTimeParseException e) {
            // Обработка ошибок чтения файла или парсинга даты
            e.printStackTrace();
        }

        executeButton.addActionListener(e -> {
            int year = (int) yearDropdown.getSelectedItem();
            int month = (int) monthDropdown.getSelectedItem();
            int day = (int) dayDropdown.getSelectedItem();
            String date = String.format("%d-%02d-%02d", year, month, day);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = timeFormatter.format(LocalDateTime.now());

            String command = String.format("git commit --amend --no-edit --date=\"%s %s\"", date, time);
            executeCommand(command);

            // Запись новой даты в файл "LastCommit.txt"
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("LastCommit.txt"))) {
                bw.write(date);
            } catch (IOException ex) {
                // Обработка ошибок записи в файл
                ex.printStackTrace();
            }
        });

        executeNextButton.addActionListener(e -> {
            int year = (int) yearDropdown.getSelectedItem();
            int month = (int) monthDropdown.getSelectedItem();
            int day = (int) dayDropdown.getSelectedItem();
            String date = String.format("%d-%02d-%02d", year, month, day);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = timeFormatter.format(LocalDateTime.now());

            String command = String.format("git commit --amend --no-edit --date=\"%s %s\"", date, time);
            executeCommand(command);

            LocalDate currentDate = getSelectedDate(yearDropdown, monthDropdown, dayDropdown);
            LocalDate nextDay = currentDate.plus(1, ChronoUnit.DAYS);

            updateDropdowns(yearDropdown, monthDropdown, dayDropdown, nextDay);

            // Запись новой даты в файл "LastCommit.txt"
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("LastCommit.txt"))) {
                bw.write(date);
            } catch (IOException ex) {
                // Обработка ошибок записи в файл
                ex.printStackTrace();
            }
        });

        frame.add(datePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(420, 120);
        frame.setVisible(true);
    }


    private static void updateDropdowns(JComboBox<Integer> yearDropdown, JComboBox<Integer> monthDropdown, JComboBox<Integer> dayDropdown, LocalDate date) {
        yearDropdown.setSelectedItem(date.getYear());
        monthDropdown.setSelectedItem(date.getMonthValue());
        dayDropdown.setSelectedItem(date.getDayOfMonth());
    }

    private static LocalDate getSelectedDate(JComboBox<Integer> yearDropdown, JComboBox<Integer> monthDropdown, JComboBox<Integer> dayDropdown) {
        int year = (int) yearDropdown.getSelectedItem();
        int month = (int) monthDropdown.getSelectedItem();
        int day = (int) dayDropdown.getSelectedItem();
        return LocalDate.of(year, month, day);
    }

    private static void updateDayDropdown(JComboBox<Integer> yearDropdown, JComboBox<Integer> monthDropdown, JComboBox<Integer> dayDropdown) {
        int selectedMonth = (int) monthDropdown.getSelectedItem();
        YearMonth yearMonthObject = YearMonth.of((int) yearDropdown.getSelectedItem(), selectedMonth);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        dayDropdown.removeAllItems(); // Clear day dropdown

        // Populate day dropdown with days
        for (int i = 1; i <= daysInMonth; i++) {
            dayDropdown.addItem(i);
        }
    }

    private static void executeCommand(String command) {
        try {
            Process process;

            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                process = new ProcessBuilder("cmd", "/c", command).start();
            } else {
                process = new ProcessBuilder("bash", "-c", command).start();
            }
            System.out.println(command);

            process.waitFor();
        } catch (Exception ex) {
            System.out.println("Error");
            JOptionPane.showMessageDialog(null, "Error executing command: " + ex.getMessage());
        }
    }
}