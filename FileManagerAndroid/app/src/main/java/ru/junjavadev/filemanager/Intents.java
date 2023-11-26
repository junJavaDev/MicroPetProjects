package ru.junjavadev.filemanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

public class Intents {

    public static Intent openFile(Entry entry, Context context) {

        File file = entry.getEntryPath().toFile();
        if (!file.exists()) return null;
        //Получить расширение
        String entryExtention = entry.getEntryExtention();
        //Определить MimeType в соответствии с типом расширения
        if (entryExtention.equals("m4a") || entryExtention.equals("mp3") || entryExtention.equals("mid") ||
                entryExtention.equals("xmf") || entryExtention.equals("ogg") || entryExtention.equals("wav")) {
            return getAudioFileIntent(file, context);
        } else if (entryExtention.equals("3gp") || entryExtention.equals("mp4") || entryExtention.equals("avi")|| entryExtention.equals("mkv")) {
            return getVideoFileIntent(file, context);
        } else if (entryExtention.equals("jpg") || entryExtention.equals("gif") || entryExtention.equals("png") ||
                entryExtention.equals("jpeg") || entryExtention.equals("bmp")) {
            return getImageFileIntent(file, context);
        } else if (entryExtention.equals("apk")) {
            return getApkFileIntent(file, context);
        } else if (entryExtention.equals("ppt")) {
            return getPptFileIntent(file, context);
        } else if (entryExtention.equals("xls")) {
            return getExcelFileIntent(file, context);
        } else if (entryExtention.equals("doc")) {
            return getWordFileIntent(file, context);
        } else if (entryExtention.equals("pdf")) {
            return getPdfFileIntent(file, context);
        } else if (entryExtention.equals("chm")) {
            return getChmFileIntent(file, context);
        } else if (entryExtention.equals("txt")) {
            return getTextFileIntent(file, context);
        } else {
            return getAllIntent(file, context);
        }
    }

    // Android получает намерение открыть файл APK
    public static Intent getAllIntent(File file, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    // Android получает намерение открыть файл APK
    public static Intent getApkFileIntent(File file, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context,  BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    // Android получает намерение открыть файл VIDEO
    public static Intent getVideoFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    // Android получает намерение открыть файл AUDIO
    public static Intent getAudioFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    // Android получает намерение открыть HTML-файлы
    public static Intent getHtmlFileIntent(File file, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.getPath()).build();
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    // Android получает намерение открыть файл изображения
    public static Intent getImageFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    // Android получает намерение открыть файл PPT
    public static Intent getPptFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    // Android получает намерение открыть файл Excel
    public static Intent getExcelFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    // Android получает намерение открыть файл Word
    public static Intent getWordFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    // Android получает намерение открыть файл CHM
    public static Intent getChmFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    // Android получает намерение открыть текстовый файл
    public static Intent getTextFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "text/plain");
        return intent;
    }

    // Android получает намерение открыть файл PDF
    public static Intent getPdfFileIntent(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file);
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }
}
