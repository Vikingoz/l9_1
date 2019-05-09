import com.google.gson.Gson;
import etc.MyVeryImportantClass;
import serialization.MyGson;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
        String google = new Gson().toJson(new MyVeryImportantClass(new String []{"as1"}, 'c', 1, 2, null));
        System.out.println("google: " + google);
        System.out.println("myVeryImportantClass: " + MyGson.getJson(myVeryImportantClass));
    /**/
        // Эти вызовы приведут к исключениям
        //System.out.println("res: " + MyGson.getJson(new byte[] {1, 2, 3, 4}));
        System.out.println("res: " + MyGson.getJson(new int[] {1, 2, 3, 4}));
        System.out.println("res: " + MyGson.getJson(new float[] {1f, 2f, 3f, 4f}));
        System.out.println("res: " + MyGson.getJson(new double[] {1d, 2d, 3d, 4d}));
        System.out.println("res: " + MyGson.getJson(new short[] {1, 2, 3, 4}));
        System.out.println("res: " + MyGson.getJson(new char[] {1, 2, 3, 4}));
        System.out.println("res: " + MyGson.getJson(new boolean[] {true, false, false, true}));
        System.out.println("res: " + MyGson.getJson(new MyVeryImportantClass(new String []{"as1"}, 'c', 1, 2, null)));

        // Эти вызовы вернуть пустой массив
        System.out.println("res2: " + MyGson.getJson(new Character[] {1, 2, 3, 4}));
        System.out.println("res2: " + MyGson.getJson(new Byte[] {1, 2, 3, 4}));
        System.out.println("res2: " + MyGson.getJson(new Short[] {1, 2, 3, 4}));
        System.out.println("res2: " + MyGson.getJson(new Float[] {1f, 2f, 3f, 4f}));
        System.out.println("res2: " + MyGson.getJson(Collections.singletonList(1f)));
        System.out.println("res2: " + MyGson.getJson(Collections.singletonList('c')));

        MyVeryImportantClass[] classes = new MyVeryImportantClass[2];
        IntStream.range(0, 2).forEachOrdered(i->classes[i] = new MyVeryImportantClass(new String []{"as1"}, 'c', i, i, Collections.singletonList("ls1")));
        System.out.println("res: " + MyGson.getJson(classes));

    }
}
