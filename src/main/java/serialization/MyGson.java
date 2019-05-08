package serialization;

import javax.json.*;
import java.lang.reflect.Field;
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
            return JsonFromArray((Object[]) object);
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
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                jsonObjectBuilder.add(field.getName(),
                        objectToJson(field.get(object)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build();

    }


    private static JsonValue JsonFromBasic (final Object object) {
        return new MyGsonString(object.toString());
    }

    private static JsonValue JsonFromArray(final Object[] objects) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Object object : objects) {
            if (String.class.equals(object.getClass())) {
                jsonArrayBuilder.add((String) object);
            } else if (BigDecimal.class.equals(object.getClass())) {
                jsonArrayBuilder.add((BigDecimal) object);
            } else if (BigInteger.class.equals(object.getClass())) {
                jsonArrayBuilder.add((BigInteger) object);
            } else if (Integer.class.equals(object.getClass())) {
                jsonArrayBuilder.add((Integer) object);
            } else if (Long.class.equals(object.getClass())) {
                jsonArrayBuilder.add((Long) object);
            } else if (Double.class.equals(object.getClass())) {
                jsonArrayBuilder.add((Double) object);
            } else if (Boolean.class.equals(object.getClass())) {
                jsonArrayBuilder.add((Boolean) object);
            }
        }
        return jsonArrayBuilder.build();
    }

}
