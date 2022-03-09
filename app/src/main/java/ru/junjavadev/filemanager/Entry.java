package ru.junjavadev.filemanager;

import java.nio.file.Files;
import java.nio.file.Path;

public class Entry implements Comparable<Entry> {

    private final Path entryPath;
    private String entryName;
    private String entryDescription;


    private int entryIconResource;

//    public Entry(String entryName, String entryDescription, int fileIcon) {
//        this.entryName = entryName;
//        this.entryDescription = entryDescription;
//        this.entryIconResource = fileIcon;

    public Entry(Path entryPath) {
        this.entryPath = entryPath;
        setEntryName(entryPath);
        setEntryDescription(entryPath);
        setEntryIconResource(entryPath);
        this.entryDescription = getEntryDescription();
        this.entryIconResource = getEntryIconResource();
    }

    private void setEntryName(Path entryPath) {
        entryName = entryPath.getFileName().toString();
    }

    private void setEntryDescription(Path entryPath) {
        if (entryPath == MainActivity.pathSource) {
            entryDescription = entryPath.getParent().toString();
        } else {
            entryDescription = Files.isDirectory(entryPath) ? "Папка" : "Файл";
        }
    }

    private void setEntryIconResource(Path entryPath) {
        if (Files.isDirectory(entryPath)) {
            entryIconResource = Icon.DIRECTORY.iconId;
        } else {
            int index = entryName.lastIndexOf(".");
            if (index == -1) {
                entryIconResource = Icon.FILE.iconId;
            } else {
                String extension = entryName.substring(index + 1).toUpperCase();
                if (Icon.extensions.contains(extension)) {
                    entryIconResource = Icon.valueOf(extension).iconId;
                } else {
                    entryIconResource = Icon.FILE.iconId;
                }
            }
        }
        if (entryPath == MainActivity.pathSource) {
            entryIconResource = Icon.BACK.iconId;
        }
    }

    public Path getEntryPath() {
        return entryPath;
    }

    public String getEntryName() {
        return entryName;

    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public int getEntryIconResource() {
        return entryIconResource;
    }

    @Override
    public int compareTo(Entry o) {
        return this.entryPath.compareTo(o.entryPath);
    }

    public Boolean isHidden() {
        return entryName.startsWith(".");
    }

    public String getEntryExtention() {
        String entryExtention = "";
        String fileName = this.getEntryName();
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            entryExtention = fileName.substring(index + 1).toLowerCase();
        }
        return entryExtention;
    }
}
