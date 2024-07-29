package util;

import junit.framework.TestCase;

import java.util.ArrayList;

public class ToStringerTest extends TestCase {
    public void testCreate() throws IllegalAccessException {
        DefaultDumpFieldTest dumpFieldTest = new DefaultDumpFieldTest();
        ToStringer tStr = new ToStringer(dumpFieldTest);

        String boardStr = "DefaultDumpFieldTest fields annotation @Dump:\n" +
                            "\ttestArrayList\n" +
                            "\ttestBoolean\n" +
                            "\ttestString\n" +
                            "\ttestDoubleNotDump\n" +
                            "\ttestFloatNotDump\n" +
                            "\ttestStringNotDump\n" +
                            "\ttestIntegerNotDump";

        assertEquals(boardStr, tStr.toString(dumpFieldTest));
    }
}

class DefaultDumpFieldTest {
    @Dump(order = 3) public String testString;
    public String testStringNotDump;
    public Integer testIntegerNotDump;
    @Dump(order = 1) public ArrayList<String> testArrayList;
    @Dump private float testFloatNotDump;
    @Dump(order = 2) private Boolean testBoolean;
    @Dump public double testDoubleNotDump;
}