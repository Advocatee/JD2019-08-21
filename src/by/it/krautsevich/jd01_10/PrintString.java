package by.it.krautsevich.jd01_10;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PrintString {
    public static void main(String[] args) {

        Class <String> strClass = String.class ;
        Method[] methods = strClass.getDeclaredMethods() ;

    for (Method method : methods) {
            int modifiers = method.getModifiers();
            if (!Modifier.isStatic(modifiers))

            System.out.println(method.getName());


    /*    for (Method method : methods) {
            System.out.println(method); */
        }


    }
}
