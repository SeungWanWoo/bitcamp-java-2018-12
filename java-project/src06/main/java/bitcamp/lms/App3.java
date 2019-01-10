package bitcamp.lms;

public class App3 {
  public static void main(String[] args) {
    java.util.Scanner scn = new java.util.Scanner(System.in);

    int index = 0;
    int viewCount = 0;
    int[] num = new int[10];
    String[] content = new String[10];
    java.sql.Date date[] = new java.sql.Date[10];

    // do ~ while 문을 활용하여 App3 해결
    do {
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

      date[index] = new java.sql.Date(System.currentTimeMillis());

      System.out.println();

      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase(""))
        break;
    } while (index++ < 10);
    
    scn.close();
    
    int i = 0;
    do {
      if (i == 10)
        break;
      System.out.printf("%02d, %-20s, %s, %d\n", num[i], content[i], date[i], viewCount);
    } while (i++ < index);
    // for 문을 활용하여 App3 해결
    /*
    for (; index < 10; index++) {
      System.out.print("번호? ");
      num[index] = Integer.parseInt(scn.nextLine());

      System.out.print("내용? ");
      content[index] = scn.nextLine();

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String answ = scn.nextLine();

      date[index] = new java.sql.Date(System.currentTimeMillis());

      System.out.println();

      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase(""))
        break;
    }
    
    scn.close();
    
    for (int i = 0; i <= index; i++) {
      System.out.printf("%02d, %-20s, %s, %d\n", num[i], content[i], date[i], viewCount);
    }
    
    // while 문을 활용하여 App3 해결
    /*
    while (index < 10) {
      System.out.print("번호? ");
      num[index] = Integer.parseInt(scn.nextLine());

      System.out.print("내용? ");
      content[index] = scn.nextLine();

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String answ = scn.nextLine();

      date[index] = new java.sql.Date(System.currentTimeMillis());

      System.out.println();

      index++;
      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase(""))
        break;
    }

    scn.close();
    
    int i = 0;
    while (i < index) {
      System.out.printf("%02d, %-20s, %s, %d\n", num[i], content[i], date[i], viewCount);
      i++;
    }*/
  }
}
