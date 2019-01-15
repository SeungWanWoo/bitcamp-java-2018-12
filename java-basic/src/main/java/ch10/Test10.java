// # 생성자 - 기본 생성자 II
package ch10;

class Monitor8 {

  int bright;   // 밝기 (0% ~ 100%)
  int contrast = 50; // 명암 (0% ~ 100%)
  int widthRes; // 해상도 너비
  int heightRes = 1080; // 해상도 높이
  
  //만약 생성자가 한 개라도 있으면 컴파일러는 기본 생성자를 추가하지 않는다.
 // 
  Monitor8(int bright, int contrast) {
    this.widthRes = 2560;
    this.heightRes = 1200;
  }
  // 단순 값은 위에 = 을 이용해서 사용해도 되나, 조건이나 반복이 들어간 경우는 보통 생성자
  // 를 이용해서 사용한다.
  
  public void on() {
    // 모니터를 켜면 bright, contrast, widthRes, heightRes 값에 맞춰서
    // LCD의 불을 밝힌다.
    System.out.println("화면을 출력한다.");
  }
}
public class Test10 {
  public static void main(String[] args) {
    // 인스턴스 생성
    // 
    // 기본 생성자가 없는 경우 다음과 같이 기본 생성자를 지정할 수 없다.
    //new Monitor8(); // 컴파일 오류!
    // 존재하는 생성자를 지정해야 하고, 그 생성자의 파라미터에 맞춰 값을 넘겨야 한다.
    // => Monitor8 설계도에 따라 인스턴스를 생성한 후, int 값 두 개를 받는 생성자를 호출하라는 의미다.
    new Monitor8(50, 50); // OK!
  }
}