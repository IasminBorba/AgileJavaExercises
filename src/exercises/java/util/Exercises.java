package util;

public class Exercises {


    public void blowsUp() {
        throw new RuntimeException("Somebody should catch this!");
    }

    public void rethrows(){
        try {
            blowsUp();
        } catch (Exception e){
            throw new RuntimeException("Exception Rethrows", e);
        }
    }
}

