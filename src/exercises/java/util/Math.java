package util;

import static java.lang.Math.*;

public class Math {
    public static int hexDecimal(String hex) {
        int size = hex.length()-2;
        int result = 0;

        for (int i = 2; i < hex.length(); i++) {
            switch (hex.charAt(i)) {
                case '1':
                    result += (1 * pow(16, --size));
                    break;
                case '2':
                    result += (2 * pow(16, --size));
                    break;
                case '3':
                    result += (3 * pow(16, --size));
                    break;
                case '4':
                    result += (4 * pow(16, --size));
                    break;
                case '5':
                    result += (5 * pow(16, --size));
                    break;
                case '6':
                    result += (6 * pow(16, --size));
                    break;
                case '7':
                    result += (7 * pow(16, --size));
                    break;
                case '8':
                    result += (8 * pow(16, --size));
                    break;
                case '9':
                    result += (9 * pow(16, --size));
                    break;
                case 'A':
                    result += (10 * pow(16, --size));
                    break;
                case 'B':
                    result += (11 * pow(16, --size));
                    break;
                case 'C':
                    result += (12 * pow(16, --size));
                    break;
                case 'D':
                    result += (13 * pow(16, --size));
                    break;
                case 'E':
                    result += (14 * pow(16, --size));
                    break;
                case 'F':
                    result += (15 * pow(16, --size));
            }
        }

        return result;
    }
}
