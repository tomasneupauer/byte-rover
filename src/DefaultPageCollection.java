package org.berandev.byterover;

import java.util.Map;

public interface DefaultPageCollection extends PageCollection {
    @Override
    default void insertPage(String pageName, ByteArray pageContent){
        getPageEntries().put(pageName, pageContent);
        getPageTypes().put(pageName, ResourceLoader.getString("model.defaultPageType"));
    }

    @Override
    default void updatePage(String pageName, ByteArray pageContent){
        getPageEntries().replace(pageName, pageContent);
    }

    @Override
    default void renamePage(String pageName, String newName){
        if (isPage(pageName)){
            getPageEntries().put(newName, getPageEntries().remove(pageName));
            getPageTypes().put(newName, getPageTypes().remove(pageName));
        }
    }

    @Override
    default void removePage(String pageName){
        getPageEntries().remove(pageName);
        getPageTypes().remove(pageName);
    }

    @Override
    default void setPageType(String pageName, String pageType){
        if (isPage(pageName)){
            getPageTypes().replace(pageName, pageType);
        }
    }

    @Override
    default String readPage(String pageName){
        return isPage(pageName) ? getPageEntries().get(pageName).toString() : "";
    }

    @Override
    default String readSelectedPage(){
        return readPage(getSelectedPageName());
    }

    @Override
    default String getPageType(String pageName){
        return isPage(pageName) ? getPageTypes().get(pageName) : "";
    }

    @Override
    default String getSelectedPageType(){
        return getPageType(getSelectedPageName());
    }

    @Override
    default boolean isPage(String pageName){
        return getPageEntries().containsKey(pageName);
    }

    @Override
    default boolean hasSelectedPage(){
        return isPage(getSelectedPageName());
    }

    Map<String, ByteArray> getPageEntries();
    Map<String, String> getPageTypes();
}

