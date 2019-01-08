/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitcamp.lms;

import java.sql.Date;

public class App {
  public static void main(String[] args) {
    java.util.Scanner scn = new java.util.Scanner(System.in);
    int index = 0;
    int[] num = new int[10];
    String[] cName = new String[10];
    String[] explan = new String[10];
    Date[] sDate = new Date[10];
    Date[] eDate = new Date[10];
    int[] totalLectureTime = new int[10];
    int[] dayLectureTime = new int[10];
    while (true) {
      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      System.out.print("번호? ");
      num[index] = Integer.parseInt(scn.nextLine());

      System.out.print("수업명? ");
      cName[index] = scn.nextLine();

      System.out.print("설명? ");
      explan[index] = scn.nextLine();

      System.out.print("시작일? ");
      sDate[index] = Date.valueOf(scn.nextLine());

      System.out.print("종료일? ");
      eDate[index] = Date.valueOf(scn.nextLine());  

      System.out.print("총수업시간? ");
      totalLectureTime[index] = Integer.parseInt(scn.nextLine());

      System.out.print("일수업시간? ");
      dayLectureTime[index] = Integer.parseInt(scn.nextLine());

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
    while (true)
    {
      System.out.println(num[i] + ", " + cName[i] + ", "
          + sDate[i] + " ~ " + eDate[i] + ", " 
          + totalLectureTime[i]);
        if (i == index)
          break;
        else
          i++;
    }
  }
}

