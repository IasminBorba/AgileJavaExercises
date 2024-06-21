package util;

import junit.framework.TestCase;

import java.math.BigDecimal;

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
}
