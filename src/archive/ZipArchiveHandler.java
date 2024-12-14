package org.berandev.byterover;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

public class ZipArchiveHandler {
    public static void loadArchive(ProjectArchive archive, String path) throws Exception {
        try (
            FileInputStream fileInputStream = new FileInputStream(path);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
        ){
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null){
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                copyStream(zipInputStream, outputStream);
                archive.putUnique(new ByteArray(outputStream), zipEntry.getName());
                zipInputStream.closeEntry();
            }
        }
        catch (Exception exception){
            throw new Exception(ResourceLoader.getException("archive.loadFailed"));
        }
    }

    public static void saveArchive(ProjectArchive archive, String path) throws Exception {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        ){
            for (String entryName : archive.getEntryNames()){
                ZipEntry zipEntry = new ZipEntry(entryName);
                zipOutputStream.putNextEntry(zipEntry);
                ByteArray archiveEntry = archive.getEntry(entryName);
                copyStream(archiveEntry.toInputStream(), zipOutputStream);
                zipOutputStream.closeEntry();
            }
        }
        catch (Exception exception){
            throw new Exception(ResourceLoader.getException("archive.saveFailed"));
        }
    }

    private static void copyStream(InputStream source, OutputStream target) throws Exception {
        byte[] buffer = new byte[1024]; int length;
        while ((length = source.read(buffer)) > 0){
            target.write(buffer, 0, length);
        }
    }
}

