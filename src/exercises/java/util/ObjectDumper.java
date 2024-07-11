package util;

import java.lang.reflect.*;
import java.util.*;

public class ObjectDumper {
    public int[] testArray1 = {1,2,3};
    public String[] testArrayString = {"test1","test2","test3"};
    float[][] testArray2 = new float[][] {{1f, 1f}, {2f, 2f}};
    Float[][] testArrayFloat = new Float[][] {{11F}, {22F}};
    String[][][] testArray3 = new String[][][] {{{"Test1.1", "Test1.2"}, {"Test2.1"}}};
    String[][][][] testArray4 = new String[][][][] {{{{"Test1", "test1.2"}, {"test2.2",}, {"teste"}}, {{"test123", "teste"}, {"test5432"}}}};
    Map <String, Integer> map = new HashMap<>();
    StringBuffer buffer = new StringBuffer();

protected ObjectDumper(){
    this.map.put("map1", 1);
    this.map.put("map2", 2);
    this.map.put("map3", 3);

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

        typeName = "Array " + dimensions + "D of " + typeName.substring(0, typeName.length() - (dimensions * 2));
        builder.append(typeName).append(" - ").append(nameArray + ":");

        extractValues(field, field.get(object), dimensions, builder);
        return builder.toString();
    }

    private static void extractValues(Field field, Object arrayObject, int dimensoes, StringBuilder builder) throws IllegalAccessException {
        if (field.getType().getComponentType().isPrimitive()) {
            builder.append(isPrimitive(field, arrayObject)); return;
        }
        builder.append("\n");
        for (int i = 0; i < Array.getLength(arrayObject); i++) {
            Object element = Array.get(arrayObject, i);
            if(element != null) {
                if (element.getClass().isArray() && dimensoes > 1) {
                    builder.delete(builder.length() - 1, builder.length());
                    extractValues(field, element, dimensoes - 1, builder);
                } else {
                    if (element.getClass().getName().startsWith("java.") || element.getClass().getName().startsWith("javax."))
                        builder.append("\t\t").append((element + ";"));
                    else
                        builder.append(hierarchyString(dumper(element) + ";\n"));
                }
            } else {
                builder.append("\t\t").append((element + ";\n"));
            }
        }
    }

    private static String isPrimitive(Field field, Object arrayObject) {
        StringBuilder builder = new StringBuilder();
        builder.append(" {");
        for (int i = 0; i < Array.getLength(arrayObject); i++) {
            Object element = Array.get(arrayObject, i);
            builder.append(element + ", ");
        }

        builder.delete(builder.length()-2, builder.length()).append("} \n");
        return builder.toString();
    }
}
