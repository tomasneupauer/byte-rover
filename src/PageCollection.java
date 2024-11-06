package org.berandev.byterover;

import java.util.Map;

public interface PageCollection {
    default void addPage(String pageName, ByteArray pageContent, String pageType){
        getPageEntries().put(pageName, pageContent);
        getPageTypes().put(pageName, pageType);
    }

    default boolean isPage(String pageName){
        return getPageEntries().containsKey(pageName);
    }

    default boolean hasSelectedPage(){
        return isPage(getSelectedPageName());
    }

    default String getPageContent(String pageName){
        return isPage(pageName) ? getPageEntries().get(pageName).toString() : "";
    }

    default String getPageType(String pageName){
        return isPage(pageName) ? getPageTypes().get(pageName) : "";
    }

    default String getSelectedPageContent(){
        return getPageContent(getSelectedPageName());
    }

    default String getSelectedPageType(){
        return getPageType(getSelectedPageName());
    }

    void selectPage(String pageName);
    String getSelectedPageName();

    //helper
    Map<String, ByteArray> getPageEntries();
    Map<String, String> getPageTypes();
}

