// 콘솔로 출력하기 II - 형식으로 갖춰 출력하기
package ch02;

public class Test12 {
  public static void main(String[] args) {
    //printf()
    System.out.printf("Hello!\n");
    
    //printf("%s", 문자열); 
    // =>Format : %s : 오른쪽 지정한 자리에 문자열을 출력한다.
    System.out.printf("이름 : %s\n", "홍길동");
    System.out.printf("안녕하세요! %s입니다.\n", "임꺽정");
    
    //printf("%d", 10진수); printf("%x", 16진수);
    // =>Format : %d : 정수 값을 10진수 문자열로 만들어 삽입한다.
    // =>Format : %x : 정수 값을 16진수 문자열로 만들어 삽입한다.
    // =>Format : %c : 정수 값을 문자로 만들어 삽입한다.
    //                    [주어진 값을 문자열로 생각하고 출력한다]
    // =>Format : %b : true/false 값을 문자열로 만들어 삽입한다.
    System.out.printf("%d %x %c %b\n", 65, 65, 65, true);
    // console -> 65 41 A true
    //한 개의 값을 여러 곳에 삽입할 수 있다.
    // %n$s : n은 값의 순서를 지정한다. 순서는 1부터 증가한다.
    //        s는 type 형식을 의미한다.
    System.out.printf("%2$d %2$x %2$c\n", 65, 66, 67);
    // console -> 66 42 B
    // 값이 출력할 때 사용할 공간을 지정할 수 있다.
    System.out.printf("'%s' '%s'\n", "홍길동", "임꺽정");
    // %[사용할공간너비]s
    // %[-][사용할공간너비]s : -는 왼쪽 정렬, 안붙이면 오른쪽 정렬
    System.out.printf("'%-10s' '%10s'\n", "홍길동", "임꺽정");
    // console -> '홍길동       ' '       임꺽정'
    System.out.printf("'%-10d' '%10d'\n", 12345, 12345);
    // console -> '12345     ' '     12345'
    // %[0][사용할공간너비]d : 앞에 0을 붙이면 빈자리가 0으로 채워진다.
    System.out.printf("'%010d' '%07d'\n", 12345, 12345);
    // console -> '0000012345' '0012345'
    // %[+][0][사용할공간너비]d : +의 의미는 해당 숫자의 부호를 뜻한다.
    System.out.printf("'%+010d' '%+07d'\n", 12345, -12345);
    // console -> '0000012345' '-012345'
  }
}

/* 
 */     
