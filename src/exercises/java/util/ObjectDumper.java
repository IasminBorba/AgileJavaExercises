package util;

import java.lang.reflect.Field;

public class ObjectDumper {
    static  <T> String dumper(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("Class ").append(object.getClass().getSimpleName()).append("\n\n");

        Field[] fields = object.getClass().getSuperclass().getDeclaredFields();
        for (Field field: fields){
            field.setAccessible(true);
            String flag = field.accessFlags().toString();
            String typeName = field.getType().getSimpleName();
            String name = field.getName();
            Object value = field.get(object);

            builder.append(flag).append(" ").append(typeName).append(" - ");
            builder.append(name).append(": ").append(value).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1).append(";");

        return builder.toString();
    }
}
