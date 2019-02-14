// 11단계 : AbstractService 상속받기
package com.eomcs.lms.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 클라이언트의 요청을 처리하는 클래스라는 의미로
// 클래스명을 *Service로 변경한다.
public abstract class AbstractService<E> {

  ObjectInputStream in;
  ObjectOutputStream out;

  public void init(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }
  
  public abstract void execute(String request) throws Exception;
}
