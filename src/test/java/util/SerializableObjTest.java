package util;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

public class SerializableObjTest extends TestCase {
    public void testCreate() throws IOException, ClassNotFoundException{
        SerializableObj obj1 = new SerializableObj("name", 10.5, 123);
        File file = new File("SerializableObjTest.txt");
        obj1.serializableObj(file.getPath());

        SerializableObj obj2 = obj1.copyObj(file.getPath());

        assertEquals("name", obj2.getName());
        assertEquals(10.5, obj2.getValor());
        assertEquals(123, obj2.getSeq());

        file.delete();
    }

}
