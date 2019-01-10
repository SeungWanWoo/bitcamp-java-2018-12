// # JVM 아규먼트 응용 II
package ch06;

public class Test18 {
  public static void main(String[] args) {
    // JVM에 기본으로 설정되어 있는 Property를 모두 출력하라!
    
    // JVM의 전체 프로퍼티 목록을 가져오기
    java.util.Properties props = System.getProperties();
    
    // key에 들어있는 값을 리턴해준다.
    java.util.Set keySet = props.keySet();
    
    for (Object key : keySet) {
      String value = System.getProperty((String)key); 
      //key 값이 Object 이기 때문에 (String)으로 형변환 해준다.
      // 그리고 해당 값의 주소를 value 변수에 지정해준다.
      System.out.printf("%s = %s\n", key, value);
    }
  }
}

/*
 * Properties는 내부적으로 엑셀처럼 key 값과 value 값을 두고 정렬시킨다.  
 */
