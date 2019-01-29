package design_pattern.command.after;

public class MemberDetailCommand implements Command {
  
  @Override
  public void execute() {
    System.out.println("회원 상세 조회 처리!");
  }
}
