package bitcamp.lms;

import java.util.Date;

public class App2 {
  public static void main(String[] args) {
    java.util.Scanner scn = new java.util.Scanner(System.in);
    
    System.out.print("번호? ");
    int num = scn.nextInt();
    
    scn.nextLine();
    
    System.out.print("이름? ");
    String name = scn.nextLine();
    
    System.out.print("이메일? ");
    String email = scn.nextLine();
    
    System.out.print("암호? ");
    int passwd = scn.nextInt();
    
    scn.nextLine();
    
    System.out.print("사진? ");
    String pic = scn.nextLine();
    
    System.out.print("전화? ");
    String tel = scn.nextLine();
    
    scn.close();
    
    java.util.Date date = new java.util.Date();    
    System.out.println();
    System.out.printf("번호 : %d \n", num);
    System.out.printf("이름 : %s \n", name);
    System.out.printf("이메일 : %s \n", email);
    System.out.printf("암호 : %d \n", passwd);
    System.out.printf("사진 : %s \n", pic);
    System.out.printf("전화 : %s \n", tel);
    System.out.printf("가입일 : %1$tY-%1$tm-%1$td \n", date);
  }
}
