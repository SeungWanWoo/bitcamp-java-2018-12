package ch29.g;

import java.beans.PropertyEditorSupport;

public class MyCustomBlackBoxEditor extends PropertyEditorSupport {
  
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    System.out.printf("MyCustomBlackBoxEditor.setAsText(%s)\n", text);
    // XML 파일에 입력한 문자열 값을 분석하여
    BlackBox blackBox = new BlackBox();
    
    // 바꾸고자 하는 객체로 만든다.
    String[] input = text.split(",");
    blackBox.setMaker(input[0]);
    blackBox.setModel(input[1]);
    
    // 내부에 저장한다.
    this.setValue(blackBox);
  }
}
