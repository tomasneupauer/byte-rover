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
        archiveEntries = new HashMap<String, ArchiveEntry>();
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveNotFound"));
        }
        try {
            while (zipInputStream.available()){
                loadNextZipEntry();
            }
            zipInputStream.close();
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveLoadFailed"));
        }
        return archiveEntries;
    }

    private static void loadNextZipEntry() throws Exception {
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; int length;
        while ((length = zipStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        ArchiveEntry archiveEntry = new ArchiveEntry(outStream.toByteArray());
        archiveEntries.put(zipEntry.getName(), archiveEntry);
    }
}
