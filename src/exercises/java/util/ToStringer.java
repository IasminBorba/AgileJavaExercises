package util;

import java.lang.reflect.*;

//Crie uma anotação @Dump. x
// Esta anotação pode ser aplicada a qualquer campo em uma classe.x
// Em seguida, crie uma classe ToStringer x
// que, quando passada um objeto, irá gerar um dump de cada campo que foi marcado para uso no método toString.
public class ToStringer {
    public static <T> String toString(T object) {
        StringBuilder builder = new StringBuilder(object + ":\n");

        for (Field field : object.getClass().getFields()) {
            if (field.isAnnotationPresent(Dump.class))
                builder.append(field).append("\n");
        }
        return builder.toString();
    }
}
