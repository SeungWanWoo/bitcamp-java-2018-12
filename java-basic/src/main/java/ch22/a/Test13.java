// java.io.File 클래스 : 연습 과제 - bin 폴더를 삭제하라!
package ch22.a;

import java.io.File;

public class Test13 {

  public static void main(String[] args) throws Exception {

    // bin의 하위 폴더와 파일을 모두 삭제해야만 bin 폴더를 삭제할 수 있다.
    //
    File file = new File("bin");
    delete(file);
    System.out.println("삭제 완료");
  }

  static void delete(File file) {
    // 파일의 하위 디렉토리와 파일 목록을 얻는다. pseudo code
    File[] fileList = file.listFiles();
    // 파일 목록에서 파일을 하나 꺼낸다.
    for (File files : fileList) {
      // 만약 파일이면 삭제한다.
      if (files.isFile()) {
        files.delete();
        System.out.println("파일을 삭제하였습니다.");
      // 디렉토리면 delete()를 호출하여 삭제한다.
      } else if(files.isDirectory()) {
        files.delete();
      } else {
        delete(files);
        System.out.println("디렉토리를 삭제하였습니다.");
      }
      file.delete();
    }
  }
}
