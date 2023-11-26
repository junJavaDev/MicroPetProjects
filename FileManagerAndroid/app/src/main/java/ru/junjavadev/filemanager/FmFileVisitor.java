package ru.junjavadev.filemanager;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import ru.junjavadev.filemanager.comparators.Comparators;
import ru.junjavadev.filemanager.comparators.SortByName;

public class FmFileVisitor extends SimpleFileVisitor<Path> {
    private final TreeSet<Entry> entries;

    public FmFileVisitor(Comparator<Entry> comparator) {
        this.entries = new TreeSet<>(comparator);
    }

    public TreeSet<Entry> getEntries() {
        return entries;
    }



    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        entries.add(new Entry(dir));
        return dir.equals(MainActivity.pathSource) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        entries.add(new Entry(file));

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    public TreeSet<Entry> visitDirectory(Path directory, FmFileVisitor fileVisitor) {
        fileVisitor.entries.clear();
        try {
            Files.walkFileTree(directory, fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Predicate<Entry> isHidden = Entry::isHidden;
        entries.removeIf(isHidden);
        entries.remove(MainActivity.HOME_ENTRY);
        return fileVisitor.getEntries();

    }


}
