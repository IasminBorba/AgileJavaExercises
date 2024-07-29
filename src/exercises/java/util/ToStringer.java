package util;

import java.lang.reflect.*;

public class ToStringer {
    public static <T> String toString(T object) {
        StringBuilder builder = new StringBuilder(object.getClass().getSimpleName() + " fields annotation @Dump:\n");

        for (Field field : object.getClass().getFields()) {
            if (field.isAnnotationPresent(Dump.class))
                builder.append("\t").append(field.getName()).append("\n");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}
