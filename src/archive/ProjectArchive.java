package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ProjectArchive {
    private Map<String, ByteArray> archiveEntries;

    public ProjectArchive(){
        archiveEntries = new HashMap<String, ByteArray>();
    }

    public void putEntry(String name, ByteArray entry){
        archiveEntries.put(name, entry);
    }

    public ByteArray getEntry(String name){
        return archiveEntries.get(name);
    }

    public boolean containsEntry(String name){
        return archiveEntries.containsKey(name);
    }
}

