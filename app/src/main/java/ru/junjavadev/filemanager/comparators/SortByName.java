package ru.junjavadev.filemanager.comparators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import ru.junjavadev.filemanager.Entry;

public class SortByName implements Comparator<Entry> {
    @Override
    public int compare(Entry firstPath, Entry secondPath) {
        int result = 0;
        if (Files.isDirectory(firstPath.getEntryPath()) && Files.isRegularFile(secondPath.getEntryPath())) {
            result = -1;
        }
        else if (Files.isRegularFile(firstPath.getEntryPath()) && Files.isDirectory(secondPath.getEntryPath())) {
            result = 1;
        }
        else result = firstPath.compareTo(secondPath);
        return result;
    }

}
