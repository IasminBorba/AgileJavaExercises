package util;

import junit.framework.TestCase;

public class LoopsTest  extends TestCase {

    public void testFactorial(){
        assertEquals(1, Loops.testWhile(0));
        assertEquals(1, Loops.testWhile(1));
        assertEquals(2, Loops.testWhile(2));
        assertEquals(6, Loops.testWhile(3));
        assertEquals(24, Loops.testWhile(4));
        assertEquals(120, Loops.testWhile(5));

        assertEquals(1, Loops.testFor(0));
        assertEquals(1, Loops.testFor(1));
        assertEquals(2, Loops.testFor(2));
        assertEquals(6, Loops.testFor(3));
        assertEquals(24, Loops.testFor(4));
        assertEquals(120, Loops.testFor(5));

        assertEquals(1, Loops.testDo(0));
        assertEquals(1, Loops.testDo(1));
        assertEquals(2, Loops.testDo(2));
        assertEquals(6, Loops.testDo(3));
        assertEquals(24, Loops.testDo(4));
        assertEquals(120, Loops.testDo(5));

        assertEquals(1, Loops.testTrue(0));
        assertEquals(1, Loops.testTrue(1));
        assertEquals(2, Loops.testTrue(2));
        assertEquals(6, Loops.testTrue(3));
        assertEquals(24, Loops.testTrue(4));
        assertEquals(120, Loops.testTrue(5));
    }

}
