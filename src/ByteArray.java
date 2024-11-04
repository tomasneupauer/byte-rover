package org.berandev.byterover;

import java.io.ByteArrayInputStream;

public class ByteArray {
    protected byte[] content;

    public ByteArray(byte[] content){
        this.content = content;
    }

    public getContent(){
        return content;
    }

    public setContent(byte[] content){
        this.content = content;
    }

    public toString(){
        return new String(content);
    }
}

class StreamEntry extends ByteArray {
    public ArchiveEntry(byte[] content){
        super(content);
    }

    public toInputStream(){
        return new ByteArrayInputStream(content);
    }
}

class ContentEntry extends ByteArray {
    private String contentType;

    public ContentEntry(byte[] content, String contentType){;
        this.contentType = contentType;
        super(content);
    }

    public getType(){
        return contentType;
    }
}

