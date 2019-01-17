// 기능 확장 - 방법1) 기존 코드를 가져와서 기능 추가하기
package ch13.a;

public class Test01 {
  public static void main(String[] args) {
    // Calculator에 곱하기, 나누기, 기능을 추가하라.
    // 단, 원본을 손대지 말라.
    Calculator2 c1 = new Calculator2();
    c1.plus(5);
    c1.multiple(2);
    c1.minus(2);
    c1.divide(4);
    System.out.println(c1.getResult());
  }
}

/*
 * # 기능 추가하기
 * 1) 기존 클래스 파일에 추가하는 방법
 *  - 기존 코드를 변경하게 되면 원래 되던 기능도 오류가 발생할 수 있는 위험이 있다.
 *  - 그래서 원래 코드를 손대는 것은 매우 위험한 일이다.
 *  - 기존에 잘 되던 기능까지 동작이 안되는 문제가 발생할 수 있기 때문이다.
 *  
 * 2) 기존 코드를 복사하여 새 클래스를 만드는 방법
 *  - 장점
 *      - 기존 코드를 손대지 않기 때문에 문제가 발생할 가능성은 줄인다.
 *  - 단점
 *      - 기존 코드의 크기가 큰 경우에는 복사 붙여넣기가 어렵다.
 *      - 기존 클래스의 소스가 없는 경우에는 이 방법이 불가능하다.
 *        엥? 다른 개발자가 배포한 라이브러리만 있는 경우를 말한다. 
 *        소스가 없는 다른 개발자가 만든 클래스에 기능을 덧 붙일 때는 이 방법이 불가능하다.
 *      - 기존 코드에 버그가 있을 때 복사 붙여넣기 해서 만든 클래스도 영향을 받는다.
 *      - 기존 코드를 변경했을 때 복사 붙여넣기 한 모든 클래스를 찾아 변경해야 한다.
 * 3) 기존 코드를 상속 받아 기능을 추가하는 방법
 *  - 장점
 *      - 기존 클래스의 소스 코드가 필요 없다.
 *      - 간단한 선언으로 상속 받겠다고 표시한 후 새 기능만 추가하면 된다.
 *  - 단점
 *      - 일부 기능만 상속 받을 수 없다.
 *      - 쓰든 안쓰든 모든 기능을 상속 받는다.
 * 
 */
