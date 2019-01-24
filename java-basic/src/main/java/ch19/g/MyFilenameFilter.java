package ch19.g;

import java.io.File;
import java.io.FilenameFilter;

// File.list() 메서드에서 사용할 FilenameFilter를 만든다.
// = File 클래스의 인스턴스 메서드인 list() ...

public class MyFilenameFilter implements FilenameFilter{
  @Override
  public boolean accept(File dir, String name) {
    if (name.endsWith(".txt"))
      return true;
    return false;
  }
}
