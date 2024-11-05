package org.berandev.byterover;

import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.HashMap;
import java.util.Map;

public class ZipHandler {
    private static Map<String, ByteArray> archiveEntries;
    private static ZipInputStream zipInputStream;

    public static Map<String, ByteArray> loadZip(String zipPath) throws Exception {
        archiveEntries = new HashMap<String, ByteArray>();
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
        return archiveEntries;
    }

    private static void readZipEntry(ZipEntry zipEntry) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; int length;
        while ((length = zipInputStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        StreamEntry archiveEntry = new StreamEntry(outStream);
        archiveEntries.put(zipEntry.getName(), archiveEntry);
    }
}
