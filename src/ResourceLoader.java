package org.berandev.byterover;

import java.util.ResourceBundle;

public class ResourceLoader {
    private static ResourceBundle strings = ResourceBundle.getBundle("values.strings");

    public static String getString(String key){
        return strings.getString(key);
    }
}

