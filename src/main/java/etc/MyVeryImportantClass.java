package etc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyVeryImportantClass {

    //Поддержите
    // массивы объектов,
    String[] stringArray;
    // примитивные типы,
    char myChar;
    int  myInt;
    long myLong;
    // коллекции из стандартный библиотерки.
    List<String> myArrayList;

    public MyVeryImportantClass(String[] stringArray, char myChar,
                                int myInt, long myLong,
                                List<String> myArrayList) {
        this.stringArray = stringArray;
        this.myChar = myChar;
        this.myInt = myInt;
        this.myLong = myLong;
        this.myArrayList = myArrayList;
    }
}
