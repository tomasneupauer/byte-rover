package org.berandev.byterover;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArray {
    protected byte[] content;

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
        return new ByteArrayInputStream(content);
    }
}

class ContentEntry extends ByteArray {
    private String contentType;

    public ContentEntry(byte[] content, String contentType){
        super(content);
        this.contentType = contentType;
    }

    public String getType(){
        return contentType;
    }
}

