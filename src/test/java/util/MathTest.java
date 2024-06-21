package util;

import junit.framework.TestCase;
import java.math.*;
import java.util.*;

import static java.lang.Math.*;
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

    public void testNaNInfinity(){
        //Usamdp float, mas tudo se aplica pra o tipo Double;

        assertEquals(Float.NaN, (0.0f/0));
        assertEquals(Float.NaN, (float) sqrt(-1));
        assertEquals(Float.NaN, (10.0f % 0.0f));
        assertEquals(Float.NaN, Float.NaN * 1f);
        assertEquals(Float.NaN, Float.NaN / 1f);
        assertEquals(Float.NaN, Float.NaN + 1f);
        assertEquals(Float.NaN, Float.NaN - 1f);
        assertEquals(Float.NaN, Float.NaN * Float.NaN);
        assertEquals(Float.NaN, Float.NaN / Float.NaN);
        assertEquals(Float.NaN, Float.NaN + Float.NaN);
        assertEquals(Float.NaN, Float.NaN - Float.NaN);


        assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY);
        assertEquals(Float.POSITIVE_INFINITY, (1.0f/0));

        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY - Float.POSITIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY + Float.NEGATIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY * Float.NEGATIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY * Float.POSITIVE_INFINITY);
        assertEquals(Float.NEGATIVE_INFINITY, (-1.0f/0));


        assertEquals(Float.NaN, Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY - Float.NEGATIVE_INFINITY);
        assertEquals(Float.NaN, Float.POSITIVE_INFINITY - Float.POSITIVE_INFINITY);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY + Float.POSITIVE_INFINITY);
        assertEquals(Float.NaN, Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY);
        assertEquals(Float.NaN, Float.POSITIVE_INFINITY * 0);
        assertEquals(Float.NaN, Float.NEGATIVE_INFINITY * 0);
    }

    public void testIntegerDivision(){
        Set<Integer> listNumber = new HashSet<>(Arrays.asList(7,15,20,249,75,3,10));
        List<Integer> number3 = new ArrayList<>(Arrays.asList(3,249, 75,15));

        List<Integer> result1 = Math.divisibleBy3(listNumber);
        assertEquals(number3, result1);

        List<Integer> result2 = Math.divisibleBy3Plus(listNumber);
        assertEquals(number3, result2);
    }
}
