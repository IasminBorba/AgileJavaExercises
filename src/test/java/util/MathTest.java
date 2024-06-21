package util;

import junit.framework.TestCase;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertNotEquals;

public class MathTest extends TestCase {
    public void testBigDecimal(){
        BigDecimal testBigDecimal = new BigDecimal("5.00");

        testBigDecimal.add(new BigDecimal("1.00"));
        assertEquals(new BigDecimal("5.00"), testBigDecimal);
    }

    public void testBigDecimal2(){
        BigDecimal testBigDecimal = new BigDecimal("10.00");
        BigDecimal testBigDecimal2 = new BigDecimal("1");
        assertNotEquals(testBigDecimal2, testBigDecimal);

        BigDecimal bigDecimalScale = (testBigDecimal2).multiply(new BigDecimal("10.00"));
        assertEquals(testBigDecimal, bigDecimalScale);

        BigDecimal bigDecimalNotPrime = new BigDecimal("10").divide(bigDecimalScale);
        assertEquals(testBigDecimal2, bigDecimalNotPrime);
    }

    public void testFloat(){
        float number1 = 0.9f;
        float number2 = 0.005f*2.0f;
        assertNotEquals(number1, number2);

        float precisionFloat = 0.9f;
        double precisionDouble = 0.9;
        assertEquals(number1, number2, precisionFloat);
        assertEquals(number1, number2, precisionDouble);

        double number12 = 0.9;
        double number22 = 0.005*2.0;
        assertEquals(number1, number2, precisionFloat);
        assertEquals(number1, number2, precisionDouble);
    }

    public static class CompilerError{
//        float x = 0.01;  ERROR
        float x = 0.01f;
        float y = (float)0.01;
    }

    public void testSpikeDecimalValue(){
        String strValue = "0xDEAD";

        int valueDecimal = Integer.decode("0xDEAD");
        assertEquals(57005, valueDecimal);

        int valueDecimal2 = Math.hexDecimal("0xDEAD");
        assertEquals(57005, valueDecimal2);

        
        String valueOctal = new BigInteger(String.valueOf(valueDecimal)).toString(8);
        assertEquals("157255", valueOctal);
    }
}
