package org.berandev.byterover;

import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

public class ZipArchiveHandler {
    private static ProjectArchive projectArchive;
    private static ZipInputStream zipInputStream;

    public static ProjectArchive loadZipArchive(String zipPath) throws Exception {
        projectArchive = new ProjectArchive();
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveNotFound"));
        }
        try {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null){
                readZipEntry(zipEntry);
            }
            zipInputStream.close();
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveLoadFailed"));
        }
        return projectArchive;
    }

    public static void saveZipArchive(String zipPath, ProjectArchive projectArchive) throws Exception {
        
    }

    private static void readZipEntry(ZipEntry zipEntry) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; int length;
        while ((length = zipInputStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        projectArchive.putEntry(zipEntry.getName(), new ByteArray(outStream));
    }
}

