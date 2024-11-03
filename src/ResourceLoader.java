package org.berandev.byterover;

import java.util.ResourceBundle;

public class ResourceLoader {
    private static ResourceBundle strings = ResourceBundle.getBundle("values.strings");

    public static String getString(String key){
        return strings.getString(key);
    }

    public static String getException(String key){
        return getString("exception." + key);
    }

    public static String getFilename(String key){
        return getString("filename." + key);
    }
}

