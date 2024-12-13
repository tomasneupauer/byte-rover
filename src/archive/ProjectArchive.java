package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProjectArchive {
    private Map<String, ByteArray> archiveEntries;
    private int entryId;

    public ProjectArchive(){
        archiveEntries = new HashMap<String, ByteArray>();
        entryId = 0;
    }

    public Set<String> getEntryNames(){
        return archiveEntries.keySet();
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

    //deprecated
    public void putEntry(String name, ByteArray entry){
        archiveEntries.put(name, entry);
    }

    public void putUnique(ByteArray entry, String name){
        archiveEntries.put(name, entry);
    }
}

