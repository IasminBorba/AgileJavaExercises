package util;

import chess.Board;
import junit.framework.TestCase;

import java.util.ArrayList;

public class ToStringerTest extends TestCase {
    public void testCreate() throws Exception {
        Board board = new Board();

        ToStringer toStr = new ToStringer(board);

        String boardStr = "Board fields annotation @Dump:\n" +
                "\tfilename: null\n" +
                "\tpiecesOnTheBoard: \n" +
                "\tpieces: []\n" +
                "\tboard: null\n" +
                "\tpiecesBlack: 0\n" +
                "\tfile: null\n" +
                "\tpiecesWhite: 0";

        assertEquals(boardStr, toStr.toString(board));
    }

    public void testOrder() throws Exception {
        DefaultDumpFieldTest dumpFieldTest = new DefaultDumpFieldTest();
        ToStringer tStr = new ToStringer(dumpFieldTest);

        String defaultDumpStr = "DefaultDumpFieldTest fields annotation @Dump:\n" +
                "\ttestArrayList: null\n" +
                "\ttestBoolean: null\n" +
                "\ttestString: null\n" +
                "\ttestDoubleDump: 0.0\n" +
                "\ttestFloatDump: 0.0\n" +
                "\ttestStringNotDump: null\n" +
                "\ttestIntegerNotDump: null";

        assertEquals(defaultDumpStr, tStr.toString(dumpFieldTest));
    }

    public void testQuote() throws Exception {
        DefaultDumpQuoteTest dumpQuoteTest = new DefaultDumpQuoteTest();
        ToStringer tStr = new ToStringer(dumpQuoteTest);

        String defaultDumpStr = "DefaultDumpQuoteTest fields annotation @Dump:\n" +
                "\ttestArrayList: null\n" +
                "\ttestString: null\n" +
                "\ttestDoubleDumpQuote: \"0.0\"\n" +
                "\ttestPrivateBoolean: null\n" +
                "\ttestPrivateFloatQuote: \"0.0\"\n" +
                "\ttestIntegerDumpQuote: \"null\"\n" +
                "\ttestStringNotDump: null";

        assertEquals(defaultDumpStr, tStr.toString(dumpQuoteTest));
    }

    public void testOutputMethod() throws Exception {
        DefaultDumpToStringTest dumpToStringTest = new DefaultDumpToStringTest(new FullName());
        ToStringer tStr = new ToStringer(dumpToStringTest);

        String defaultDumpStr = "DefaultDumpToStringTest fields annotation @Dump:\n" +
                "\tfullNameCustom: Borba, Iasmin\n" +
                "\tfullName: Iasmin Borba";

        assertEquals(defaultDumpStr, tStr.toString(dumpToStringTest));
    }

    public void testOutputMethodException() throws Exception {
        DefaultDumpExceptionTest dumpExceptionTest = new DefaultDumpExceptionTest(new FullName());
        ToStringer tStr = new ToStringer(dumpExceptionTest);

        String defaultDumpStr = tStr.toString(dumpExceptionTest);
    }
}

class DefaultDumpFieldTest {
    @Dump(order = 3) public String testString;
    public String testStringNotDump;
    public Integer testIntegerNotDump;
    @Dump(order = 1) public ArrayList<String> testArrayList;
    @Dump private float testFloatDump;
    @Dump(order = 2) private Boolean testBoolean;
    @Dump public double testDoubleDump;
}

class DefaultDumpQuoteTest {
    @Dump(order = 3) public String testString;
    public String testStringNotDump;
    @Dump(quote = true) private Integer testIntegerDumpQuote;
    @Dump(order = 1) public ArrayList<String> testArrayList;
    @Dump(quote = true) private float testPrivateFloatQuote;
    @Dump private Boolean testPrivateBoolean;
    @Dump(quote = true) public double testDoubleDumpQuote;
}

class DefaultDumpToStringTest {
    @Dump(outputMethod = "customOutPut") public FullName fullNameCustom;
    public FullName fullName;

    public DefaultDumpToStringTest(FullName fullName) {
        this.fullNameCustom = fullName;
        this.fullName = fullName;
    }
}

class FullName {
    public String firstName = "Iasmin";
    public String lastName = "Borba";

    public String customOutPut() {
        return lastName + ", " + firstName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

}

class DefaultDumpExceptionTest {
    @Dump(outputMethod = "customNotExists") public FullName fullNameException;
    public FullName fullName;

    public DefaultDumpExceptionTest(FullName fullName) {
        this.fullNameException = fullName;
        this.fullName = fullName;
    }
}