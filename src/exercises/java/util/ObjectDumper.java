package util;

import java.lang.reflect.*;
import java.util.*;

public class ObjectDumper {

    private static final Set<Object> visitedObjects = new HashSet<>();

    public static <T> String dumper(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        if (object == null) {
            return builder.append("null").toString();
        }

        if (visitedObjects.contains(object)) {
            return builder.append("Circular reference detected").toString();
        }

        visitedObjects.add(object);
        builder.append("Class ").append(object.getClass().getSimpleName()).append("\n\n");

        Field[] fields = object.getClass().getDeclaredFields();
        Class<?> superClass = object.getClass().getSuperclass();
        if (superClass != null) {
            Field[] superFields = superClass.getDeclaredFields();
            builder.append(processFields(fields, object));
            builder.append(processFields(superFields, object));
        } else {
            builder.append(processFields(fields, object));
        }

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
            builder.append(flag).append(" ");

            Class<?> klass = field.getType();
            String typeName = klass.getSimpleName();
            String name = field.getName();

            if (klass.isArray()) {
                int count = 0;
                for (Character aChar: typeName.toCharArray()) {
                    if (aChar.equals('['))
                        count++;
                }
                typeName = "Array " + count + "D of " + typeName.substring(0, typeName.length() - (count+2));
                builder.append(typeName).append(" - ").append(name).append(": \n");

                Object[] rankArray = (Object[]) field.get(object);
                ArrayList<Object> valueArray = new ArrayList<>();
                for(Object obj: rankArray){
                    valueArray.addAll(Arrays.asList((Object[]) obj));
                }

                StringBuilder auxBuilder = new StringBuilder();
                for (Object obj: valueArray) {
                    if (obj == null) {
                        auxBuilder.append("\tnull;\n");
                    } else {
                        String dumperString = dumper(obj) + ";";
                        auxBuilder.append("\n").append(hierarchyString(dumperString));
                    }
                }
                builder.append(auxBuilder).append("\n");
            } else {
                builder.append(typeName).append(" - ").append(name).append(": ");

                if (typeName.equals("StringBuilder")) {
                    StringBuilder aux = new StringBuilder();
                    builder.append("\n");
                    for (String str : field.get(object).toString().split("\n"))
                        aux.append("\t").append(str).append("\n");
                    builder.append(aux);
                } else {
                    Object value = field.get(object);
                    if (value instanceof Collection) {
                        builder.append("\n").append(processCollection((Collection<?>) value));
                    } else {
                        value = field.get(object);
                        builder.append(value).append("\n");
                    }
                }
            }
        }
        return builder.toString();
    }

    private static String processCollection(Collection<?> collection) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        for (Object item : collection) {
            builder.append(dumper(item)).append("\n\n");
        }
        return hierarchyString(builder.toString());
    }

    private static String hierarchyString(String builder){
        StringBuilder string = new StringBuilder();

        for(String str: builder.split("\n"))
            string.append("\t").append(str).append("\n");

        return string.toString();
    }
}
