import com.google.gson.Gson;
import etc.MyVeryImportantClass;
import serialization.MyGson;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        BigDecimal bigDecimal = BigDecimal.valueOf(123.334);
        String string = "test nomer raz";
        System.out.println("basic bigDecimal: " + MyGson.getJson(bigDecimal));
        System.out.println("basic string: " + MyGson.getJson(string));

        String[] array = {"nomer1", "nomer2", "я здесь не для того, чтобы оправдовать твои ожидания"};
        System.out.println("array: " + MyGson.getJson(array));

        List<String> collection = Arrays.asList("json","collect");
        System.out.println("collection: " + MyGson.getJson(collection));


        String [] s =  {"t1","t2","t3"};
        MyVeryImportantClass myVeryImportantClass =
                new MyVeryImportantClass(
                        s ,
                        'd', 2, 3L,
                        Arrays.asList("test1", "test2")
                );
        String google = new Gson().toJson(myVeryImportantClass);
        System.out.println("google: " + google);
        System.out.println("myVeryImportantClass: " + MyGson.getJson(myVeryImportantClass));
    }
}
