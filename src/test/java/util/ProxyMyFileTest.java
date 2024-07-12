package util;

import junit.framework.TestCase;

import static util.ObjectDumper.dumper;

public class ProxyMyFileTest extends TestCase {
    public void testCreate() throws IllegalAccessException {
        String filename = "Test for class";
        MyFile myFile = new MyFile(filename);
        String toStringMyFile = dumper(myFile);

        ProxyMyFile proxyMyFile = new ProxyMyFile(filename);
        String toStringProxyMyFile = proxyMyFile.toString();

        assertEquals(toStringMyFile, toStringProxyMyFile);
    }
}
