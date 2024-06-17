package util;

import junit.framework.TestCase;

public class ExercisesTest extends TestCase {
    Exercises test = new Exercises();
    public void testBlowsUp1() {
        Exercises test = new Exercises();
        test.blowsUp();
    }

    public void testBlowsUp2() {
        Exercises test = new Exercises();
        try{
            test.blowsUp();
            fail("expected exception");
        } catch (RuntimeException e){
            assertEquals("Somebody should catch this!", e.getMessage());
        }
    }

    public void testBlowsUp3() {
        Exercises test = new Exercises();
        try{
            test.blowsUp();
            fail("expected exception");
        } catch (RuntimeException e){
            assertEquals("Somebody should", e.getMessage());
        }
    }

    public void testRethrows4() {
        Exercises test = new Exercises();
        try{
            test.rethrows();
            fail("expected exception");
        } catch (RuntimeException e){
            assertEquals("Somebody should catch this!", e.getCause().getMessage());
        }
    }

    public void testRethrows5() throws SimpleException{
        Exercises test = new Exercises();
        try{
            test.blowsUp();
            fail("expected exception");
        } catch (SimpleException e){
            assertEquals("Somebody should catch this!", e.getMessage());
        }
    }

    public void testExceptionOrder1() {
        try {
            test.blowsUp();
            test.rethrows();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }
    public void testExceptionOrder2() {
        try {
            test.rethrows();
            test.blowsUp();
            fail("no exception");
        }
        catch (SimpleException success) {
        }catch (RuntimeException failure) {
            fail("caught wrong exception");
        }
    }
//    public void testExceptionOrder3() {
//        try {
//            test.blowsUp();
//            test.rethrows();
//            fail("no exception");
//        }
//        catch (RuntimeException success) {
//        }
//        catch (SimpleException yours) {
//            fail("caught wrong exception");
//        }
//    }
//    public void testExceptionOrder4() {
//        try {
//            test.blowsUp();
//            test.rethrows();
//            fail("no exception");
//        }
//        catch (RuntimeException fail) {
//            fail("exception unacceptable");
//        }
//        catch (SimpleException yours) {
//            fail("caught wrong exception");
//        }
//        finally {
//            return;
//        }
//    }
    public void testExceptionOrder5() {
        try {
            test.blowsUp();
            test.rethrows();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }
    public void testExceptionOrder6() {
        try {
            test.rethrows();
            test.blowsUp();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }
    public void testExceptionOrder7() {
        try {
            test.rethrows();
            test.blowsUp();
            fail("no exception");
        }
        catch (SimpleException success) {
        }
        catch (RuntimeException fail) {
            fail("caught wrong exception");
        }
    }
    public void testErrorException1() {
        try {
            throw new RuntimeException("fail");
        }
        catch (Exception success) {
        }
    }
    public void testErrorException2() {
        try {
            new Dyer();
        }
        catch (Exception success) {
        }
    }
    public void testErrorException3() {
        try {
            new Dyer();
        }
        catch (Error success) {
        }
    }
//    public void testErrorException4() {
//        try {
//            new Dyer();
//        }
//        catch (Throwable success) {
//        }
//    }
//    public void testErrorException5() {
//        try {
//            new Dyer();
//        }
//        catch (Throwable fail) {
//            fail("caught exception in wrong place");
//        }
//        catch (Error success) {
//        }
//    }
    public void testErrorException6() {
        try {
            new Dyer();
        }
        catch (Error fail) {
            fail("caught exception in wrong place");
        }
        catch (Throwable success) {
        }
    }
    public void testErrorException7() {
        try {
            new Dyer();
        }
        catch (Error fail) {
            fail("caught exception in wrong place");
        }
        catch (Throwable success) {
        }finally {
            return;
        }
    }
    static class Dyer {
        Dyer() {
            throw new RuntimeException("oops.");
        }
    }

    public void testWithProblems() {
        try {
            doSomething();
            fail("no exception");
        } catch (Exception success) {}
    }

    void doSomething() throws Exception {
        throw new Exception("blah");
    }

}
