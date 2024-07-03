package util;

import junit.framework.TestCase;

import java.io.*;

public class BaseSizeTest extends TestCase {
    public void testBaseChar() throws IOException{
        File file = new File("testChar.txt");
        BaseSize baseSize = new BaseSize();
        char aChar = 'a';
        byte [] bytes = BaseSize.baseChar(file, aChar);
        assertEquals(2, bytes.length);
        file.delete();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        char value = dis.readChar();

        assertEquals(aChar, value);
    }

    public void testBaseInt() throws IOException{
        File file = new File("testInt.txt");
        BaseSize baseSize = new BaseSize();
        int anInt = 5;
        byte [] bytes = baseSize.baseInt(file, anInt);

        assertEquals(4, bytes.length);
        file.delete();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        int value = dis.readInt();

        assertEquals(anInt, value);
    }

    public void testBaseDouble() throws IOException{
        File file = new File("testDouble.txt");
        BaseSize baseSize = new BaseSize();
        double aDouble = 1.5;

        byte [] bytes = baseSize.baseDouble(file, aDouble);
        assertEquals(8, bytes.length);
        file.delete();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        double value = dis.readDouble();

        assertEquals(aDouble, value);
    }

    public void testBaseLong() throws IOException{
        File file = new File("testLong.txt");
        BaseSize baseSize = new BaseSize();
        long aLong = 1000L;

        byte [] bytes = baseSize.baseLong(file, aLong);
        assertEquals(8, bytes.length);
        file.delete();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        long value = dis.readLong();

        assertEquals(aLong, value);
    }

    public void testBaseFloat() throws IOException{
        File file = new File("testFloat.txt");
        BaseSize baseSize = new BaseSize();
        float aFloat = 1.4f;

        byte [] bytes = baseSize.baseFloat(file, aFloat);
        assertEquals(4, bytes.length);
        file.delete();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        float value = dis.readFloat();

        assertEquals(aFloat, value);
    }

    public void testBaseShort() throws IOException{
        File file = new File("testShort.txt");
        BaseSize baseSize = new BaseSize();
        short aShort = 1000;

        byte [] bytes = baseSize.baseShort(file, aShort);
        assertEquals(2, bytes.length);
        file.delete();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        short value = dis.readShort();

        assertEquals(aShort, value);
    }
}
