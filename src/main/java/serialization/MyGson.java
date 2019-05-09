package serialization;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class MyGson {

    public static String getJson(Object object) {
        return Optional.ofNullable(object).map(o -> objectToJson(o).toString()).orElse(null);
    }

   private static JsonValue objectToJson(Object object) {
        //Примитивы //Стринг
        if (Number.class.isAssignableFrom(object.getClass()) ||
                String.class.equals(object.getClass()) ||
                Character.class.equals(object.getClass()) ||
                Boolean.class.equals(object.getClass())) {
            return JsonFromBasic(object);
        }
        //Массивы
        if (object.getClass().isArray()) {
            return JsonFromArray(object);
        }
        //Коллекции
        if (Collection.class.isAssignableFrom(object.getClass())) {
            return JsonFromArray(((Collection) object).toArray());
        }
        //Прочие классы
        return JsonFromFields(object);
    }

    private static JsonValue JsonFromFields(final Object object) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> !Modifier.isStatic(f.getModifiers()))
                .filter(f -> !Modifier.isTransient(f.getModifiers()))
                .forEach(f -> {
                            f.setAccessible(true);
                            try {
                                Optional.ofNullable(f.get(object))
                                        .map(o -> jsonObjectBuilder.add(f.getName(),
                                                objectToJson(o))
                                        )
                                        .orElse(null);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                );
        return jsonObjectBuilder.build();

    }


    private static JsonValue JsonFromBasic (final Object object) {
        return new MyGsonString(object.toString());
    }

    private static JsonValue JsonFromArray(final Object object) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (int i = 0; i < Array.getLength(object); i++) {
            Object value = Array.get(object, i);
            if (value != null) {
                if (String.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((String) value);
                } else if (BigDecimal.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((BigDecimal) value);
                } else if (BigInteger.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((BigInteger) value);
                } else if (Integer.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Integer) value);
                } else if (Long.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Long) value);
                } else if (Double.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Double) value);
                } else if (Boolean.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Boolean) value);
                } else if (Float.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Float) value);
                } else if (Short.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Short) value);
                } else if (Character.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Character) value);
                } else if (Byte.class.equals(value.getClass())) {
                    jsonArrayBuilder.add((Byte) value);
                } else {
                    jsonArrayBuilder.add(objectToJson(value));
                }

            }
        }
        return jsonArrayBuilder.build();
    }

}
