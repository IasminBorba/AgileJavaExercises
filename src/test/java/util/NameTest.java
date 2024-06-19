package util;

import junit.framework.TestCase;

public class NameTest extends TestCase {
    public void testCreate(){
        Name nameA = new Name("abc");
        Name nameB = new Name("abc");
        Name nameC = new Name("abc");
        Name nameD = new Name("123");

        assertTrue(nameA.equals(nameA));

        assertTrue(nameA.equals(nameB));
        assertTrue(nameB.equals(nameA));

        assertTrue(nameA.equals(nameB));
        assertTrue(nameB.equals(nameC));
        assertTrue(nameA.equals(nameC));

        assertEquals(nameA, nameB);

        assertTrue(!nameA.equals(null));
    }
}
