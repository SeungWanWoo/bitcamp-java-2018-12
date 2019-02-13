package com.eomcs.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand implements Command {

  Scanner keyboard;

  public LessonUpdateCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      out.writeUTF("/lesson/update");
      out.flush();
      
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
      
      Lesson temp = new Lesson();
      
      temp.setNo(no);
      
      System.out.print("수업명? ");
      String input = keyboard.nextLine();
      temp.setTitle(input);
      
      System.out.print("설명? ");
      input = keyboard.nextLine();
      temp.setContents(input);
      
      System.out.print("시작일? ");
      input = keyboard.nextLine();
      temp.setStartDate(Date.valueOf(input));
      
      System.out.print("종료일? ");
      input = keyboard.nextLine();
      temp.setEndDate(Date.valueOf(input));
      
      System.out.print("총수업시간? ");
      input = keyboard.nextLine();
      temp.setTotalHours(Integer.parseInt(input));
      
      System.out.print("일수업시간? ");
      input = keyboard.nextLine();
      temp.setDayHours(Integer.parseInt(input));
      
      out.writeObject(temp);
      out.flush();
      
      System.out.println("수업을 변경했습니다.");
      
      String status = in.readUTF();
      if (!status.equals("OK"))
        throw new Exception("수업 정보 변경 실패!");
    } catch (Exception e) {
      System.out.printf("수업 정보 변경 오류! : %s\n", e.getMessage());
    }
  }
}
