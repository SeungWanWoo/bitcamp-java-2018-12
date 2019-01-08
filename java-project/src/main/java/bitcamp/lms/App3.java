package bitcamp.lms;

public class App3 {
  public static void main(String[] args) {
    java.util.Scanner scn = new java.util.Scanner(System.in);
    
    int index = 0;
    int viewCount = 0;
    int[] num = new int[10];
    String[] content = new String[10];
    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
    
    while (true) {
      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      System.out.print("번호? ");
      num[index] = Integer.parseInt(scn.nextLine());
      
      System.out.print("내용? ");
      content[index] = scn.nextLine();

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String answ = scn.nextLine();
      
      System.out.println();
      
      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase("")) {
        scn.close();
        break;
      } else {
        index++;
      }
    }
    
    int i = 0;
    while (true) {
      System.out.println(num[i] + ", " + content[i] + "    , "
          + date + ", " + viewCount);
        if (i == index)
          break;
        else
          i++;
    }
  }
}
