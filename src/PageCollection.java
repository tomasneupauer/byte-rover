package org.berandev.byterover;

public interface PageCollection {
    //Mutators
    void insertPage(String pageName, ByteArray pageContent);
    void updatePage(String pageName, ByteArray pageContent);
    void renamePage(String pageName, String newName);
    void removePage(String pageName);
    void selectPage(String pageName);
    void setPageType(String pageName, String pageType);
    //Accessors
    String readPage(String pageName);
    String readSelectedPage();
    String getPageType(String pageName);
    String getSelectedPageType();
    String getSelectedPageName();
    //Predicates
    boolean isPage(String pageName);
    boolean hasSelectedPage();
}

