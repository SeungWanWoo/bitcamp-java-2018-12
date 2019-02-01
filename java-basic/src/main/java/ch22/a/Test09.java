// java.io.File 클래스 : 폴더 안에 있는 파일이나 하위 폴더의 상세정보
package ch22.a;

import java.io.File;

public class Test09 {

  public static void main(String[] args) throws Exception {
    
    File file = new File(".");

    // 하위 파일이나 디렉토리의 상세 정보 알아내기
    File[] names = file.listFiles();
    for (File f : names) {
      System.out.printf("%s %12d %s\n",
          f.isDirectory() ? "d" : "-",
          f.length(), f.getName());
    }
  }
}
