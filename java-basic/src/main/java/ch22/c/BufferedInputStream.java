// 버퍼를 씀으로 써 얻을 수 있는 이득
package ch22.c;

import java.io.IOException;
import java.io.InputStream;

// InputStream에 기능을 덧붙이는 플러그인 역할을 수행하는 클래스이다.
// => 이런 클래스를 Decorator라 한다.
// => Decorator는 기능을 덧붙이는 대상 클래스와 같은 조상을 가져야 한다(InputStream)
//    그리고 생성자에게 대상 객체 주소를 받아야 한다.
//    작업을 수행할 때 대상 객체를 사용한다.
//    그리고 자신만의 기능을 덧붙인다.
public class BufferedInputStream extends InputStream {

  InputStream in;
  byte[] buf = new byte[1024];
  int cursor = 0;
  int size = 0;
  
  public BufferedInputStream(InputStream in) {
    this.in = in;
  }
  
  public int read() throws IOException {
    if (cursor >= size) {
      size = in.read(buf);
      if (size == -1)
        return -1;
      cursor = 0;
    }
    
    // 바이트의 값을 온전히 4바이트 int 값으로 변환하기 위해
    // 0x000000ff 값을 & 비트 연산한다.
    // => 0xff 상수 값은 0x000000ff 를 의미한다.
    return buf[cursor++] & 0xff;
  }
}
