package ru.junjavadev.filemanager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public enum Icon {
    I3GP(R.drawable.i3gp),
    I7Z(R.drawable.i7z),
    ICLASS(R.drawable.iclass),
    AVI(R.drawable.avi),
    BACK(R.drawable.back),
    CSV(R.drawable.csv),
    DAT(R.drawable.dat),
    DIRECTORY(R.drawable.directory),
    DOCR(R.drawable.doc),
    DOCX(R.drawable.docx),
    EXE(R.drawable.exe),
    FILE(R.drawable.file),
    GIF(R.drawable.gif),
    HTML(R.drawable.html),
    ICO(R.drawable.ico),
    JPG(R.drawable.jpg),
    M3U(R.drawable.m3u),
    MKV(R.drawable.mkv),
    MOV(R.drawable.mov),
    MP3(R.drawable.mp3),
    MP4(R.drawable.mp4),
    MPG(R.drawable.mpg),
    PDF(R.drawable.pdf),
    PNG(R.drawable.png),
    PPT(R.drawable.ppt),
    PPTX(R.drawable.pptx),
    PSD(R.drawable.psd),
    RAR(R.drawable.rar),
    RAW(R.drawable.raw),
    SQL(R.drawable.sql),
    TORRENT(R.drawable.torrent),
    TXT(R.drawable.txt),
    WMV(R.drawable.wmv),
    XLSX(R.drawable.xlsx),
    XML(R.drawable.xml),
    ZIP(R.drawable.zip);

    final int iconId;

    Icon(int iconId) {
        this.iconId = iconId;
    }

    static final Set<String> extensions = new HashSet<>(); //Сэт значений в String, чтобы не использовать перехват исключения в методе getIconId
    static {
        for (Icon icon : Icon.values()) {
            extensions.add(icon.toString());
        }
    }


//    public static int getIconId(Path path) {
//        int iconId = 0;
//        if (Files.isDirectory(path)) {
//            iconId = DIRECTORY.iconId;
//        } else {
//            String fileName = path.getFileName().toString();
//            int index = fileName.lastIndexOf(".");
//            if (index == -1) {
//                iconId = FILE.iconId;
//            } else {
//                String extension = fileName.substring(index + 1).toUpperCase();
//                if (extensions.contains(extension)) {
//                    iconId = valueOf(extension).iconId;
//                } else {
//                    iconId = FILE.iconId;
//                }
//            }
//        }
//        if (path == MainActivity.pathSource) {
//            iconId = BACK.iconId;
//        }
//        return iconId;
//    }
}
