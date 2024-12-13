package org.berandev.byterover;

public class PageModel {
    private ByteArray contentEntry;
    private String contentType;

    public ByteArray getContentEntry(){
        return contentEntry;
    }

    public String readContent(){
        return hasContent() ? contentEntry.toString() : "";
    }

    public String getContentType(){
        return hasContent() ? contentType : "";
    }

    public boolean hasContent(){
        return contentEntry != null;
    }

    public void setContent(ByteArray entry, String type){
        contentEntry = entry;
        contentType = type;
    }
}

