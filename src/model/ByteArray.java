package org.berandev.byterover;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArray {
    private byte[] contentBytes;

    public ByteArray(byte[] contentByteArray){
        setContent(contentByteArray);
    }

    public ByteArray(String contentString){
        setContent(contentString);
    }

    public ByteArray(ByteArrayOutputStream contentStream){
        setContent(contentStream);
    }

    public void setContent(byte[] contentByteArray){
        contentBytes = contentByteArray;
    }

    public void setContent(String contentString){
        contentBytes = contentString.getBytes();
    }

    public void setContent(ByteArrayOutputStream contentStream){
        contentBytes = contentStream.toByteArray();
    }

    public byte[] getBytes(){
        return contentBytes;
    }

    public String toString(){
        return new String(contentBytes);
    }

    public ByteArrayInputStream toInputStream(){
        return new ByteArrayInputStream(contentBytes);
    }
}

