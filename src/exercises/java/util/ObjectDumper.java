package util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ObjectDumper {

    private static final Set<Object> visitedObjects = new HashSet<>();

    public static <T> String dumper(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        if (object == null) {
            return builder.append("null").toString();
        }

        if (visitedObjects.contains(object)) {
            return builder.append("Circular reference detected\n").toString();
        }

        visitedObjects.add(object);

        builder.append("Class ").append(object.getClass().getSimpleName()).append("\n\n");

        // Obtém todos os campos da classe e da superclasse
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
            // Ignora campos estáticos ou campos inacessíveis
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            try {
                field.setAccessible(true);
            } catch (Exception e) {
                // Ignora campos que não podem ser tornados acessíveis
                continue;
            }
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

//            if (field.getType().getSuperclass() != null && field.getType().getSuperclass().getName().equals("java.lang.Object")) {
//                String stringAux = dumper(field.get(object));
//                if(stringAux.equals("null"))
//                    builder.append(stringAux).append("\n");
//                else
//                    builder.append("\n").append(stringAux).append("\n");
//            } else {
//                if(typeName.equals("StringBuilder")){
//                    StringBuilder aux = new StringBuilder();
//                    builder.append("\n");
//                    for(String str: field.get(object).toString().split("\n"))
//                        aux.append("\t").append(str).append("\n");
//                    builder.append(aux);
//                } else {
//                    Object value = field.get(object);
//                    builder.append(value).append("\n");
//                }
//            }


            Object value = field.get(object);
            if (value instanceof Collection) {
                builder.append("\n").append(processCollection((Collection<?>) value)).append("\n");
            } else if (value != null && !klass.isPrimitive() && !klass.getName().startsWith("java.lang")) {
                String stringAux = dumper(value);
                builder.append("\n").append(stringAux).append("\n");
            } else {
//            if (value != null && !klass.isPrimitive() && !klass.getName().startsWith("java.lang")) {
//                String stringAux = dumper(value);
//                builder.append("\n").append(stringAux).append("\n");
//            } else {
                if (typeName.equals("StringBuilder")) {
                    StringBuilder aux = new StringBuilder();
                    builder.append("\n");
                    for (String str : value.toString().split("\n")) {
                        aux.append("\t").append(str).append("\n");
                    }
                    builder.append(aux);
                } else {
                    builder.append(value).append("\n");
                }
            }

        }
        return builder.toString();
    }

    private static StringBuilder processCollection(Collection<?> collection) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        for (Object item : collection) {
            builder.append(dumper(item)).append("\n");
        }
        return builder;
    }
}
