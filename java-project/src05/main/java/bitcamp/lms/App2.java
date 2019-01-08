package bitcamp.lms;

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
    /*
     * package = 도구함
     * method = 기능
     * System.currentTimeMillis()=>1970년대부터 현재 까지의 시간을 밀리초 단위로 리턴
     * 날짜에 대한 데이터를 출력할 땐 %s로 출력
     * System = 도구 / currentTimeMillis() => 도구함
     * 
     */
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
