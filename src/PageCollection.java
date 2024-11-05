package org.berandev.byterover;

import java.util.Map;

public interface PageCollection {
    default void addPage(String pageName, byte[] pageContent, String pageType){
        getPageEntries().put(pageName, new PageEntry(pageContent, pageType));
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
        return isPage(pageName) ? getPageEntries().get(pageName).getType() : "";
    }

    default String getPageContent(){
        return getPageContent(getSelectedPageName());
    }

    default String getPageType(){
        return getPageType(getSelectedPageName());
    }

    void selectPage(String pageName);
    String getSelectedPageName();
    Map<String, PageEntry> getPageEntries();
}

