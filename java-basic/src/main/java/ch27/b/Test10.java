// reflection API - 배열 항목의 타입 알아내기
package ch27.b;

import java.sql.Date;

public class Test10 {
  public static void main(String[] args) throws Exception {
    
    System.out.println(byte.class.getComponentType()); //=>null 배열이 아니기 때문에 값이 존재하지 않는다.
    System.out.println("------------------------------------------------------");
    int[] arr1 = {};
    Class<?> clazz = arr1.getClass();
    System.out.println(clazz.getName());
    System.out.println("------------------------------------------------------");
    System.out.println("byte ==> " + byte.class.getName());
    System.out.println("byte ==>" + new byte[] {}.getClass().getName());
    System.out.println("byte ==>" + new byte[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("int ==> " + int.class.getName());
    System.out.println("int ==> " + new int[] {}.getClass().getName());
    System.out.println("int ==> " + new int[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("float ==> " + float.class.getName());
    System.out.println("float ==> " + new float[] {}.getClass().getName());
    System.out.println("float ==> " + new float[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("long ==> " + long.class.getName());
    System.out.println("long ==> " + new long[] {}.getClass().getName());
    System.out.println("long ==> " + new long[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("char ==> " + char.class.getName());
    System.out.println("char ==> " + new char[] {}.getClass().getName());
    System.out.println("char ==> " + new char[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("double ==> " + double.class.getName());
    System.out.println("double ==> " + new double[] {}.getClass().getName());
    System.out.println("double ==> " + new double[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("short ==> " + short.class.getName());
    System.out.println("short ==> " + new short[] {}.getClass().getName());
    System.out.println("short ==> " + new short[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");    
    System.out.println("boolean ==> " + boolean.class.getName());
    System.out.println("boolean ==> " + new boolean[] {}.getClass().getName());
    System.out.println("boolean ==> " + new boolean[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("String ==> " + String.class.getName());
    System.out.println("String ==> " + new String[] {}.getClass().getName());
    System.out.println("String ==> " + new String[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    System.out.println("Date ==> " + Date.class.getName());
    System.out.println("Date ==> " + new Date[] {}.getClass().getName());
    System.out.println("Date ==> " + new Date[] {}.getClass().getComponentType().getName());
    System.out.println("------------------------------------------------------");
    
  }
}
