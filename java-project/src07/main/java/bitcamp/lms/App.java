/*
 * This Java source file was generated by the Gradle 'init' task.
 * 1 단계 : 입력에 반복문 적용
 */
package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int index = 0;
    
    // Lesson 인스턴스의 주소를 담을 레퍼런스를 10개 만든다.
    Lesson[] ls = new Lesson[10];

    while(index < 10) {
      Lesson lss = new Lesson();
      System.out.print("번호? ");
      lss.num = Integer.parseInt(scn.nextLine());

      System.out.print("수업명? ");
      lss.cName = scn.nextLine();

      System.out.print("설명? ");
      lss.explan = scn.nextLine();

      System.out.print("시작일? ");
      lss.sDate = Date.valueOf(scn.nextLine());

      System.out.print("종료일? ");
      lss.eDate = Date.valueOf(scn.nextLine());  

      System.out.print("총수업시간? ");
      lss.totalLectureTime = Integer.parseInt(scn.nextLine());

      System.out.print("일수업시간? ");
      lss.dayLectureTime = Integer.parseInt(scn.nextLine());

      System.out.println();

      ls[index++] = lss;

      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String answ = scn.nextLine();

      System.out.println();

      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase("")) {
        break;
      }
    }

    scn.close();

    for (int i = 0; i < index; i++) {
      System.out.printf("%02d, %-20s, %s ~ %s, %04d\n", 
          ls[i].num, ls[i].cName, ls[i].sDate, ls[i].eDate, 
          ls[i].totalLectureTime);
    }
  }
}