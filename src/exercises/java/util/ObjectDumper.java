package util;

import java.lang.reflect.*;
import java.util.*;

public class ObjectDumper {
    Map <String, Integer> map = new HashMap<>();
    String[][][] str3D = new String[2][2][2];
    String[] str1D = new String[2];
    StringBuffer buffer = new StringBuffer();

protected ObjectDumper(){
    this.map.put("map1", 1);
    this.map.put("map2", 2);
    this.map.put("map3", 3);

    str3D[0][0][0] = "initial";
    str3D[0][0][1] = "right";
    str3D[0][1][0] = "center";
    str3D[1][0][0] = "left";


    this.str1D[0] = "zero";
    this.str1D[1] = "one";

    this.buffer.append("testBuffer");
}

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
        int dimensions = (int) typeName.chars().filter(ch -> ch == '[').count();

        typeName = "Array " + dimensions + "D of " + typeName.substring(0, typeName.length() - (dimensions + 2));
        builder.append(typeName).append(" - ").append(nameArray).append(":\n");

        Object[] rankArray = (Object[]) field.get(object);
        ArrayList<Object> valueArray = new ArrayList<>();

        if (dimensions == 1) {
            valueArray.add(field.get(object));
        } else {
            extractValues(field.get(object), valueArray, dimensions);
        }

        StringBuilder auxBuilder = new StringBuilder();
        for (Object obj : valueArray) {
            String auxString = hierarchyString(dumper(obj) + ";");
            auxBuilder.append(auxString);
        }
        builder.append(auxBuilder);

        return builder.toString();
    }

    private static void extractValues(Object array, List<Object> valueArray, int dimensions) {
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object subArray = Array.get(array, i);
            extractValues(subArray, valueArray, dimensions - 1);
        }
    }
}
