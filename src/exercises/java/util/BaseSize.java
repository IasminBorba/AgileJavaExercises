package util;

import java.io.*;

public class BaseSize implements Serializable {
    public static byte[] bytesSeq(File file) throws IOException{
        DataInputStream input = null;
        byte[] bytes;
        try{
            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } finally {
            input.close();
        }
        return bytes;
    }

    public byte[] baseChar(File file, char aChar, int i) throws IOException{
        DataOutputStream output = null;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeChar(aChar);
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }

    public byte[] baseInt(File file,int anInt) throws IOException{
        DataOutputStream output = null;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeInt(anInt);
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }

    public byte[] baseDouble(File file, double aDouble) throws IOException{
        DataOutputStream output = null;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeDouble(aDouble);
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }

    public byte[] baseLong(File file, long aLong) throws IOException{
        DataOutputStream output = null;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeLong(aLong);
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }

    public byte[] baseFloat(File file, float aFloat) throws IOException{
        DataOutputStream output = null;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeFloat(aFloat);
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }

    public byte[] baseShort(File file, short aShort) throws IOException{
        DataOutputStream output = null;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeShort(aShort);
            bytes = bytesSeq(file);
        } finally {
            output.close();
        }
        return bytes;
    }
}