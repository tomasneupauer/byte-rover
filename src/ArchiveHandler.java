package org.berandev.byterover;

import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.HashMap;

public class ArchiveHandler {
    private HashMap<String, ByteArrayInputStream> inputStreamMap;

    public ArchiveHandler(String projectArchivePath) throws Exception {
        FileInputStream inputFile = new FileInputStream(projectArchivePath);
        ZipInputStream inputZip = new ZipInputStream(inputFile);

        inputStreamMap = new HashMap<String, ByteArrayInputStream>();
        ZipEntry zipEntry;
        while ((zipEntry = inputZip.getNextEntry()) != null){
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputZip.read(buffer)) > 0){
                outStream.write(buffer, 0, len);
            }
            byte[] byteArray = outStream.toByteArray();
            inputStreamMap.put(zipEntry.getName(), new ByteArrayInputStream(byteArray));
        }
    }

    public String[] getEntryNames(){
        String[] keys = new String[inputStreamMap.keySet().size()];
        int i=0;
        for (String key : inputStreamMap.keySet()){
            keys[i++] = key;
        }
        return keys;
    }
}

