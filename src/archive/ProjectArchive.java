package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ProjectArchive {
    private Map<String, ByteArray> archiveEntries;
    private int entryId;

    public ProjectArchive(){
        archiveEntries = new HashMap<String, ByteArray>();
        entryId = 0;
    }

    public void putEntry(String name, ByteArray entry){
        archiveEntries.put(name, entry);
    }

    public ByteArray getEntry(String name){
        return archiveEntries.get(name);
    }

    public String putEntry(ByteArray entry){
        String name = String.valueOf(entryId++);
        archiveEntries.put(name, entry);
        return name;
    }

    public boolean containsEntry(String name){
        return archiveEntries.containsKey(name);
    }
}

