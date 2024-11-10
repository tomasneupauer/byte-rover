package org.berandev.byterover;

import java.util.Map;

public interface PageCollection {
    default void insertPage(String name){
        getPageModels().put(name, new PageModel());
    }

    default void renamePage(String oldName, String newName){
        if (hasPage(oldName)){
            getPageModels().put(newName, getPageModels().remove(oldName));
        }
    }

    default void removePage(String name){
        getPageModels().remove(name);
    }

    default void setPageContent(String name, ByteArray entry, String type){
        getPageModel(name).setContent(entry, type);
    }

    default String readSelectedContent(){
        return getPageModel(getSelectedPageName()).readContent();
    }

    default String getSelectedContentType(){
        return getPageModel(getSelectedPageName()).getContentType();
    }

    default boolean hasPage(String name){
        return getPageModels().containsKey(name);
    }

    default boolean hasSelectedContent(){
        return getPageModel(getSelectedPageName()).hasContent();
    }

    void setSelectedPageName(String name);
    String getSelectedPageName();
    Map<String, PageModel> getPageModels();

    private PageModel getPageModel(String name){
        return getPageModels().getOrDefault(name, new PageModel());
    }
}

