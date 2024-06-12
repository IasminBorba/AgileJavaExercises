package util;

public class Loops {
    public static int testWhile(int n){
        int factorialN = 1;
        int index = 1;

        if (n == 0 || n == 1){
            return factorialN;
        } else if (n > 1) {
            while (index < n+1){
                factorialN *= index;
                index++;
            }
        }
        return factorialN;
    }

    public static int testFor(int n){
        int factorialN = 1;

        if (n == 0 || n == 1){
            return factorialN;
        } else if (n > 1) {
            for (int x = 1; x < n+1; x++){
                factorialN *= x;
            }
        }
        return factorialN;
    }

    public static int testDo(int n){
        int factorialN = 1;
        int i = 1;

        do {
            if (n == 0 || n == 1){
                i = n;
            } else if (n > 1) {
                factorialN *= i;
            }
        } while (++i <= n);
        return factorialN;
    }

    public static int testTrue(int n){
        int factorialN = 1;
        int i = 1;

        if (n == 0 || n == 1)
            return factorialN;

        while (true){
            factorialN *= i;
            if (i == n)
                break;
            else
                i++;
        }
        return factorialN;
    }
}
