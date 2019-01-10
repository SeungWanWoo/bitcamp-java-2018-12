// # JVM 아규먼트 응용 I
package ch06;

public class Test17 {
  public static void main(String[] args) {
    // JVM 아규먼트로 학생의 이름과 국영수 점수를 입력 받아 총점과 평균을 출력하라
    // 실행할 때는 
    //$ java -cp ./bin/main -Dname=홍길동 -Dkor=100 -Deng=100 -Dmath=90 ch06.Test17
    // 이름 : 홍길
    // 총점 : 290
    // 평균 : 96.9
    /* // args를 이용하는 방법이 프로그램 아규먼트와 다르기 때문에 아래 조건문이 동작할 수 없다.
     *     동작하더라도 원하는 값을 얻을 수 없다. 
     * if (args.length > 4) {
      System.out.println("실행 형식 : java -cp ./bin/main "
          + "-Dname=이름 -Dkor=국어점수 -Dmath=수학점수 -Deng=영어점수 ch06.Test17\n");
      return;
    }*/
    String name = System.getProperty("name");
    String s1 = System.getProperty("kor");
    String s2 = System.getProperty("eng");
    String s3 = System.getProperty("math");
    
    if (name == null || s1 == null || s2 == null || s3 == null) {
      System.out.println("실행 형식 : java -cp ./bin/main "
          + "-Dname=이름 -Dkor=국어점수 -Dmath=수학점수 -Deng=영어점수 ch06.Test17\n");
      return;
    }
    
    int kor = Integer.parseInt(s1);
    int eng = Integer.parseInt(s2);
    int math = Integer.parseInt(s3);
    int sum = kor + eng + math;
    double avr = sum / 3f;
        
    System.out.printf("이름 : %s\n", name);
    System.out.printf("합계 : %d\n", sum);
    System.out.printf("평균 : %.2f\n", avr);
  }
}