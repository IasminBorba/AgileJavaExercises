package util;

import java.io.*;

public class BaseSize implements Serializable {
    public static byte[] baseChar(File file, char aChar){
        DataOutputStream output;
        DataInputStream input;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeChar(aChar);

            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public byte[] baseInt(File file,int anInt){
        DataOutputStream output;
        DataInputStream input;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeInt(anInt);

            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public byte[] baseDouble(File file, double aDouble){
        DataOutputStream output;
        DataInputStream input;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeDouble(aDouble);

            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public byte[] baseLong(File file, long aLong){
        DataOutputStream output;
        DataInputStream input;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeLong(aLong);

            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public byte[] baseFloat(File file, float aFloat){
        DataOutputStream output;
        DataInputStream input;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeFloat(aFloat);

            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public byte[] baseShort(File file, short aShort){
        DataOutputStream output;
        DataInputStream input;
        byte[] bytes;
        try {
            output = new DataOutputStream(new FileOutputStream(file.getPath()));
            output.writeShort(aShort);

            input = new DataInputStream(new FileInputStream(file.getPath()));
            bytes = input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

}
