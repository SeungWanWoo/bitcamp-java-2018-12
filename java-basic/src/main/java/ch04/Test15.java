// # 비트 이동 연산자 II : >>, >>>, <<
package ch04;
public class Test15 {
  public static void main(String[] args) {
    // >>> 연산자
    // =>오른쪽으로 비트를 이동시킨다. 경계를 넘어 가는 값은 버린다.
    //      그리고 왼쪽 빈자리는 무조건 0으로 채운다.
    int a = 0xca;   //0000 0000 0000 0000 0000 0000 1100 1010 = 202
    System.out.println(Integer.toHexString(a>>>4));  // 0x0c
    System.out.println(a>>>4);                       //12
    // 0000 0000 0000 0000 0000 0000 1100 1010
    // 0000 0000 0000 0000 0000 0000 0000 1100|1010
    // 0000 0000 0000 0000 0000 0000 0000 1100
    System.out.println(Integer.toHexString(a>>>3));  //0x19
    System.out.println(a>>>3);                       //25
    // 00000000 00000000 00000000 11001010
    //    00000 00000000 00000000 00011001|010
    // 00000000 00000000 00000000 00011001
    System.out.println(Integer.toHexString(a>>>2));  //0x32
    System.out.println(a>>>2);                       //50
    // 00000000 00000000 00000000 11001010
    //   000000 00000000 00000000 00110010|10
    // 00000000 00000000 00000000 00110010
    System.out.println(Integer.toHexString(a>>>1));  //0x65
    System.out.println(a>>>1);                       //101
    // 00000000 00000000 00000000 11001010
    //  0000000 00000000 00000000 01100101|0
    // 00000000 00000000 00000000 01100101
    
    // 음수 값을 오른쪽으로 비트 이동할 때 
    // 왼쪽 빈자리가 0으로 채워지므로 거대한 큰 양수 값으로 바뀐다.
    // => 2**n으로 나눈 것과 같다.
    // => 소수점 이하를 반올림 한 결과가 나온다.
    // => 왼쪽 빈자리가 부호비트로 채워진다.
    a = -202; // 11111111 11111111 11111111 00110110 = -202 = 0xffffff36
    System.out.println(a>>>1);
                // 11111111 11111111 11111111 00110110      <=넘어 가는 값은 버린다.
                // 01111111 11111111 11111111 10011011|0    <=빈자리는 부호비트로 채운다.
              // 0x7fffff9b =>Integer의 최대값에서 해당 값을 뺀 값
    System.out.println(a>>>2);
    // 11111111 11111111 11111111 00110110      
    // 00111111 11111111 11111111 11001101|10   
    // 0x3fffffcd
    System.out.println(a>>>3);
    // 11111111 11111111 11111111 00110110      
    // 00011111 11111111 11111111 11100110|110   
    // 0x1fffffe6
    System.out.println(a>>>4);
    // 00001111 11111111 11111111 00110110      
    // 11111111 11111111 11111111 11110011|0110 
    // 0x0ffffff3
    System.out.println(Integer.MAX_VALUE);
  }
}