// java.io.File 클래스 : 폴더 생성 후 파일 생성하기
package ch22.a;

import java.io.File;

public class Test07 {

  public static void main(String[] args) throws Exception {
    
    // 폴더와 파일을 산 번에 생성하는 방법
    File file = new File("temp2/a/b/c/temp.txt");

    // 파일의 디렉토리 경로를 가지고 File 객체 생성
    //File dir = new File(file.getParent());
    //System.out.println(file.getParentFile());
    File dir = file.getParentFile();
    
    // 먼저 디렉토리를 생성한다.
    if (dir.mkdirs())
      System.out.println("디렉토리를 생성함.");
    else
      System.out.println("디렉토리를 생성하지 못함");
    
    if (file.createNewFile())
      System.out.println("파일이 생성함");
    else
      System.out.println("파일을 생성하지 못함");
  }
}
