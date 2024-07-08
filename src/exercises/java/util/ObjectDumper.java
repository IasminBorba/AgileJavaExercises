package util;

import java.lang.reflect.Field;

public class ObjectDumper {
    public static <T> String dumper(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        if (object == null){
            return builder.append("null").toString();
        }

        builder.append("Class ").append(object.getClass().getSimpleName()).append("\n\n");
        Field[] fields = object.getClass().getSuperclass().getDeclaredFields();
        if(fields.length == 0){
            fields = object.getClass().getDeclaredFields();
        }

        for (Field field: fields) {
            field.setAccessible(true);
            String flag = field.accessFlags().toString();
            Class<?> klass = field.getType();
            String typeName = klass.getSimpleName();
            String name = field.getName();

            if (klass.isArray()) {
                if (typeName.contains("[][]")) {
                    typeName = "Two-dimensional array of " + typeName.substring(0, typeName.length() - 4);
                }
            }

            builder.append(flag).append(" ").append(typeName).append(" - ");
            builder.append(name).append(": ");

            if (field.getType().getSuperclass() != null && field.getType().getSuperclass().getName().equals("java.lang.Object")) {
                    String stringAux = dumper(field.get(object));
                    if(stringAux.equals("null"))
                        builder.append(stringAux).append("\n");
                    else
                        builder.append("\n").append(stringAux).append("\n");
            } else {
                if(typeName.equals("StringBuilder")){
                    StringBuilder aux = new StringBuilder();
                    builder.append("\n");
                    for(String str: field.get(object).toString().split("\n"))
                        aux.append("\t").append(str).append("\n");
                    builder.append(aux);
                } else {
                    Object value = field.get(object);
                    builder.append(value).append("\n");
                }
            }
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
