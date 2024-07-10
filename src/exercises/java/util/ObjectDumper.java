package util;

import java.lang.reflect.*;
import java.util.*;

public class ObjectDumper {
    public static <T> String dumper(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        if (object == null)
            return builder.append("null").toString();

        builder.append("Class ").append(object.getClass().getSimpleName()).append(":\n");

        Field[] fields = object.getClass().getDeclaredFields();
        Class<?> superClass = object.getClass().getSuperclass();
        if (superClass != null) {
            Field[] superFields = superClass.getDeclaredFields();
            builder.append(processFields(fields, object));
            builder.append(processFields(superFields, object));
        } else
            builder.append(processFields(fields, object));
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static <T> String processFields(Field[] fields, T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();

        for (Field field: fields) {
            if (Modifier.isStatic(field.getModifiers()))
                continue;

            field.setAccessible(true);
            String flag = field.accessFlags().toString();
            builder.append("\t").append(flag).append(" ");
            Class<?> klass = field.getType();
            String typeName = klass.getSimpleName();
            String name = field.getName();

            if (klass.isArray()) {
                builder.append(dumperIsArray(typeName, name, object, field));
            } else {
                builder.append(typeName).append(" - ").append(name).append(": ");
                if (typeName.equals("StringBuilder")) {
                    builder.append("\n").append(hierarchyString(hierarchyString(field.get(object).toString())));
                } else {
                    Object value = field.get(object);
                    if (value instanceof Collection)
                        builder.append("\n").append(processCollection((Collection<?>) value));
                    else
                        builder.append(value).append("\n");
                }
            }
        }
        return builder.toString();
    }

    private static String processCollection(Collection<?> collection) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        for (Object item : collection)
            builder.append(hierarchyString(dumper(item)));
        return hierarchyString(builder.toString());
    }

    private static String hierarchyString(String builder){
        StringBuilder string = new StringBuilder();

        for(String str: builder.split("\n"))
            string.append("\t").append(str).append("\n");

        return string.toString();
    }

    private static String dumperIsArray(String typeName, String nameArray, Object object, Field field) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        int count = (int) typeName.chars().filter(ch -> ch == '[').count();

        typeName = "Array " + count + "D of " + typeName.substring(0, typeName.length() - (count+2));
        builder.append(typeName).append(" - ").append(nameArray).append(":\n");

        Object[] rankArray = (Object[]) field.get(object);
        ArrayList<Object> valueArray = new ArrayList<>();
        for(Object obj: rankArray)
            valueArray.addAll(Arrays.asList((Object[]) obj));

        StringBuilder auxBuilder = new StringBuilder();
        for (Object obj: valueArray) {
            String auxString = hierarchyString(dumper(obj) + ";");
            auxBuilder.append(hierarchyString(auxString));
        }
        builder.append(auxBuilder);

        return builder.toString();
    }
}
