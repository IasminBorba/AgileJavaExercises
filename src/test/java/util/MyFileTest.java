package util;

import junit.framework.TestCase;
import java.io.*;
import static org.junit.Assert.assertNotEquals;

public class MyFileTest extends TestCase {
    public void testCreate() throws IOException {
        String filename = "testMyFile.txt";
        MyFIle file = new MyFIle(filename);
        assertEquals(filename, file.filename);

        MyFIle file2 = new MyFIle("testMyFIle2.txt");
        assertEquals("testMyFIle2.txt", file2.filename);

        String text = "Test abcd\n 123";
        file.write(text);
        file2.write(text);

        assertEquals(text, file.read());
        assertEquals(text, file2.read());
        assertNotSame(file, file2);

        file.delete();
        file2.delete();
    }

    public void testExclude() throws IOException {
        MyFIle fileExclude = new MyFIle("testExclude.txt");
        fileExclude.write("test");
        assertEquals("test", fileExclude.read());

        fileExclude.delete();
        try{
            fileExclude.read();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void testOverWrite() throws IOException{
        MyFIle fileExist = new MyFIle("testExist.txt");
        fileExist.write("test1");
        assertEquals("test1", fileExist.read());

        try {
            fileExist.write("test2");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        assertNotEquals("test2", fileExist.filename);
        fileExist.overwrite("test2");
        assertEquals("test2", fileExist.read());
        fileExist.delete();
    }
}


