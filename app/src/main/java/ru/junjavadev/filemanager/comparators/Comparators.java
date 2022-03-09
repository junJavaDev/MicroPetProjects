package ru.junjavadev.filemanager.comparators;

import java.util.Comparator;

import ru.junjavadev.filemanager.Entry;

public enum Comparators {
    SORT_BY_NAME(new SortByName()),
    SORT_BY_NAME_REVERSE(new SortByNameReverse());

    private final Comparator<Entry> comparator;
    Comparators(Comparator<Entry> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Entry> getComparator() {
        return comparator;
    }
}
