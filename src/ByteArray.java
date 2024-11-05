package org.berandev.byterover;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArray {
    private byte[] content;

    public ByteArray(byte[] content){
        this.content = content;
    }

    public byte[] getContent(){
        return content;
    }

    public void setContent(byte[] content){
        this.content = content;
    }

    public String toString(){
        return new String(content);
    }
}

class StreamEntry extends ByteArray {
    public StreamEntry(ByteArrayOutputStream contentStream){
        super(contentStream.toByteArray());
    }

    public ByteArrayInputStream toInputStream(){
        return new ByteArrayInputStream(getContent());
    }
}

class PageEntry extends ByteArray {
    private String contentType;

    public PageEntry(byte[] content, String contentType){
        super(content);
        this.contentType = contentType;
    }

    public String getType(){
        return contentType;
    }

    public void setContent(String content){
        setContent(content.getBytes());
    }
}

