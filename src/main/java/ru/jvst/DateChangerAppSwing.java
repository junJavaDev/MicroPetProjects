package ru.jvst;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        Integer[] years = {2020, 2021, 2022, 2023, 2024};
        JComboBox<Integer> yearDropdown = new JComboBox<>(years);
        Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        JComboBox<Integer> monthDropdown = new JComboBox<>(months);
        JComboBox<Integer> dayDropdown = new JComboBox<>();

        updateDayDropdown(yearDropdown, monthDropdown, dayDropdown);

        datePanel.add(yearDropdown);
        datePanel.add(monthDropdown);
        datePanel.add(dayDropdown);

        JPanel buttonPanel = new JPanel();
        JButton previousDayButton = new JButton("Предыдущий день");
        JButton nextDayButton = new JButton("Следующий день");
        JButton executeButton = new JButton("Выполнить");

        buttonPanel.add(previousDayButton);
        buttonPanel.add(nextDayButton);
        buttonPanel.add(executeButton);

        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int year = (int) yearDropdown.getSelectedItem();
                int month = (int) monthDropdown.getSelectedItem();
                int day = (int) dayDropdown.getSelectedItem();
                String date = String.format("%d.%02d.%02d", year, month, day);

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String time = timeFormatter.format(LocalDateTime.now());

                String command = String.format("git commit --amend --no-edit --date=\"%s %s\"", date, time);
                executeCommand(command);
            }
        });

        previousDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = getSelectedDate(yearDropdown, monthDropdown, dayDropdown);
                LocalDate previousDay = currentDate.minus(1, ChronoUnit.DAYS);

                updateDropdowns(yearDropdown, monthDropdown, dayDropdown, previousDay);
            }
        });

        nextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = getSelectedDate(yearDropdown, monthDropdown, dayDropdown);
                LocalDate nextDay = currentDate.plus(1, ChronoUnit.DAYS);

                updateDropdowns(yearDropdown, monthDropdown, dayDropdown, nextDay);
            }
        });

        frame.add(datePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
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