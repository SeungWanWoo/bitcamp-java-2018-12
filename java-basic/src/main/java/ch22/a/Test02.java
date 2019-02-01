// java.io.File 클래스 : 파일 다루기
package ch22.a;

import java.io.File;

public class Test02 {
  
  public static void main(String[] args) throws Exception {
    // File 클래스
    // => 디렉토리나 파일을 다룰 때 사용하는 클래스이다.
    // => 즉, 디렉토리나 파일을 생성-삭제-변경 할 수 있다.
    //
    // 존재하는 파일
    File file = new File("build.gradle");
    System.out.println(file.getName()); // 폴더명
    System.out.println(file.getPath()); // 경로
    System.out.println(file.getAbsolutePath()); // 절대 경로
    System.out.println(file.getCanonicalPath()); //계산된 경로
    
    // 파일이 존재하지 않는 경우 크기는 0이 나온다.
    System.out.println(file.getTotalSpace()); // HDD총 용량
    System.out.println(file.getFreeSpace()); // 빈 용량
    System.out.println(file.getUsableSpace()); // 빈 용량 중 사용가능한 용량
    System.out.println(file.length()); // 파일 크기
    
    // 파일이 존재하지 않는 경우 리턴 값은 false이다.
    System.out.println(file.isDirectory()); // 디렉토리 여부 확인
    System.out.println(file.isFile()); // 파일 여부 확인
    System.out.println(file.isHidden()); // 파일숨김 여부 확인
    System.out.println(file.exists()); // 존재 여부 확인
    System.out.println(file.canExecute()); // 실행 여부 확인
    
    System.out.println("-------------------------------");
    
    
    // 존재하지 않는 파일
    file = new File("build.gradle2");
    System.out.println(file.getName()); // 폴더명
    System.out.println(file.getPath()); // 경로
    System.out.println(file.getAbsolutePath()); // 절대 경로
    System.out.println(file.getCanonicalPath()); //계산된 경로
    
    // 파일이 존재하지 않는 경우 크기는 0이 나온다.
    System.out.println(file.getTotalSpace()); // HDD총 용량
    System.out.println(file.getFreeSpace()); // 빈 용량
    System.out.println(file.getUsableSpace()); // 빈 용량 중 사용가능한 용량
    System.out.println(file.length()); // 파일 크기
    
    // 파일이 존재하지 않는 경우 리턴 값은 false이다.
    System.out.println(file.isDirectory()); // 디렉토리 여부 확인
    System.out.println(file.isFile()); // 파일 여부 확인
    System.out.println(file.isHidden()); // 파일숨김 여부 확인
    System.out.println(file.exists()); // 존재 여부 확인
    System.out.println(file.canExecute()); // 실행 여부 확인
  }
}
