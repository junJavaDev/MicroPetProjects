package ru.junjavadev.filemanager.comparators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import ru.junjavadev.filemanager.Entry;

public class SortByNameReverse implements Comparator<Entry> {
    @Override
    public int compare(Entry firstEntry, Entry secondEntry) {
        int result = 0;
        if (Files.isDirectory(firstEntry.getEntryPath()) && Files.isRegularFile(secondEntry.getEntryPath())) {
            result = 1;
        }
        else if (Files.isRegularFile(firstEntry.getEntryPath()) && Files.isDirectory(secondEntry.getEntryPath())) {
            result = -1;
        }
        else result = firstEntry.compareTo(secondEntry);
        return result;
    }
}
