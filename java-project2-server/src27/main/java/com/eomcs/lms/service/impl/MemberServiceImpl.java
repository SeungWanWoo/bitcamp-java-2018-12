package com.eomcs.lms.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

// 스프링 IoC 컨테이너가 관리하는 객체 중에서 
// 비즈니스 로직을 담당하는 객체는 
// 특별히 그 역할을 표시하기 위해 @Component 대신에 @Service 애노테이션을 붙인다.
// 이렇게 애노테이션으로 구분해두면 나중에 애노테이션으로 객체를 찾을 수 있다.
@Service
public class MemberServiceImpl implements MemberService {

  MemberDao memberDao;

  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<Member> list(String keyword) {
    if (keyword == null) {
      return memberDao.findAll();
    } else {
      return memberDao.findByKeyword(keyword);
    }
  }

  @Override
  public int add(Member member) {
    // 이 메서드도 하는 일이 없다.
    // 그래도 일관된 프로그래밍을 위해 Command 객체는 항상 Service 객체를 경유하여 DAO를
    // 사용해야 한다.
    return memberDao.insert(member);
  }

  @Override
  public Member get(int no) {
    // Command 객체는 데이터를 조회한 후 조회수를 높이는 것에 대해 신경 쓸 필요가 없어졌다.
    return memberDao.findByNo(no);
  }

  @Override
  public int update(Member member) {
    if (member.getName() != null
        || member.getEmail() != null
        || member.getPassword() != null
        || member.getPhoto() !=null
        || member.getTel() != null) {
      return memberDao.update(member);
    } else
      return 0;
  }

    @Override
    public int delete(int no) {
      // 이 메서드도 그냥 DAO에 명령을 전달하는 역할을 한다.
      return memberDao.delete(no);
    }
}