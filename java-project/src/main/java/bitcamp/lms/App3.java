package bitcamp.lms;

public class App3 {
  public static void main(String[] args) {
    java.util.Scanner scn = new java.util.Scanner(System.in);
    
    System.out.print("번호? ");
    int num = scn.nextInt();
    
    scn.nextLine();
    
    System.out.print("내용? ");
    String content = scn.nextLine();
    
    java.util.Date date = new java.util.Date();
    
    int viewCount = 0;
    System.out.println();
    System.out.printf("번호 : %d \n", num);
    System.out.printf("내용 : %s \n", content);
    System.out.printf("작성일 : %1$tY-%1$tm-%1$td \n", date);
    System.out.printf("조회수 : %d \n", viewCount);
  }
}
