// java.io.File 클래스
// 연습 과제 - bin/main 폴더를 뒤져서 모든 클래스 파일의 이름을 출력하라.
package ch22.a;

import java.io.File;
import java.io.IOException;

public class Test14 {

  public static void main(String[] args) throws Exception {

    // 클래스 이름을 출력할 때 출력 형식은 패키지 이름을 포함해야 한다.
    // 예) ch01.Test01
    File file = new File("bin/main");
    search(file, "");
    System.out.println("탐색 완료2");
  }

  static void search(File file, String packageName) throws IOException {
    File[] files = file.listFiles(pathname -> 
    pathname.isDirectory() ||
    (pathname.isFile() && pathname.getName().endsWith(".class")) ? 
        true : false);

    for (File f : files) {
      if (f.isFile()) {
        System.out.printf("%s%s\n",
          packageName,
          f.getName().replace(".class", ""));
      } else
        search(f, packageName + f.getName() + ".");
    }
  }
}
