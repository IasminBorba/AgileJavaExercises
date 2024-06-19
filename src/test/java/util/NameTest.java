package util;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

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

    public void testHashCodePerformance() {
        Set<Name> nameSet = new HashSet<>();
        Name name1 = new Name("Foo");
        nameSet.add(name1);
        Name name2 = new Name("Foo1");
        nameSet.add(name2);
        Name name3 = new Name("Foo2");
        nameSet.add(name3);

        assertFalse(nameSet.contains(new Name("Foo")));

        Name foo = new Name("Foo");
        nameSet.add(foo);
        assertTrue(nameSet.contains(foo));
    }
}
