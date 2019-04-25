// Proxy 패턴 적용 : BoardDAO에서 인터페이스를 추출한다.
package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Board;

public interface BoardDao {
   int insert(Board board);
   List<Board> findAll(Map<String,Object> params);
   Board findByNo(int num);
   int increaseCount(int no);
   int update(Board board);
   int delete(int no);
   int countAll();
}
