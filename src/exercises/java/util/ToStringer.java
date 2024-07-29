package util;

import java.lang.reflect.*;
import java.util.*;

public class ToStringer {
    private final Object obj;
    Map<Integer, Field> dumpFields = new HashMap<>();
    Set<Field> notDumpFields = new HashSet<>();

    public <T> ToStringer(T object) {
        this.obj = object;
    }

    protected <T> String toString(T object) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder(object.getClass().getSimpleName()).append(" fields annotation @Dump:\n");
        for (Field field : getFields()) {
            builder.append("\t").append(field.getName()).append(": ");

            Dump dump = field.getAnnotation(Dump.class);
            Object value = field.get(object);
            if (dump != null && dump.quote())
                builder.append("\"").append(value).append("\"");
            else
                builder.append(value);

            builder.append("\n");
        }

        if (!builder.isEmpty() && builder.charAt(builder.length() - 1) == '\n')
            builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }


    private ArrayList<Field> getFields() {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Dump.class)) {
                Dump dump = field.getAnnotation(Dump.class);
                dumpFields.put(checkOrder(dump.order()), field);
            } else
                notDumpFields.add(field);
        }

        dumpFields = new TreeMap<>(dumpFields);
        ArrayList<Field> listFields = new ArrayList<>(dumpFields.values());
        listFields.addAll(notDumpFields);
        return listFields;
    }

    private int checkOrder(int order) {
        for (int orderExists : dumpFields.keySet()) {
            if (orderExists == order)
                return checkOrder(order - 1);
        }
        return order;
    }
}