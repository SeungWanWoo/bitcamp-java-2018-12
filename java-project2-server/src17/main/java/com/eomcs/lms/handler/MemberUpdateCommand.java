package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand extends AbstractCommand {
  SqlSessionFactory sqlSessionFactory;

  public MemberUpdateCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {

    // 1) SqlSession 객체를 준비한다.
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      // 2) SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
      int no = response.requestInt("번호? ");
      Member member = memberDao.findByNo(no);

      if (member == null) {
        response.println("해당 회원이 존재하지 않습니다.");
        return;
      }

      Member temp = new Member();
      temp.setNo(member.getNo());

      String input = response.requestString(
          String.format("이름(%s)? ", member.getName()));
      if (input.length() > 0) 
        temp.setName(input);

      input = response.requestString(
          String.format("이메일(%s)? ", member.getEmail()));
      if (input.length() > 0)
        temp.setEmail(input);

      input = response.requestString("암호(새 암호를 입력하세요)? ");
      if (input.length() > 0)
        temp.setPassword(input);

      input = response.requestString(
          String.format("사진(%s)? ", member.getPhoto()));
      if (input.length() > 0)
        temp.setPhoto(input);

      input = response.requestString(
          String.format("전화(%s)? ", member.getTel()));
      if (input.length() > 0)
        temp.setTel(input);

      if (member.getName() != null
          || member.getEmail() != null
          || member.getPassword() != null
          || member.getPhoto() !=null
          || member.getTel() != null) {
        memberDao.update(temp);
        sqlSession.commit();
        response.println("회원을 변경했습니다.");
      } else {
        sqlSession.rollback();
        response.println("변경을 취소하였습니다.");
      }
    } finally {
      sqlSession.close();
    }
  }
}
