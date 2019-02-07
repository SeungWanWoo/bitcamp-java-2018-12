package ch22.g;

import java.io.Serializable;

public class Score1 implements Serializable {
  private static final long serialVersionUID = 1L;
  protected String name;
  protected int kor;
  protected int eng;
  protected int math;
  protected int sum;
  protected float aver;
  
  public Score1 () {
    System.out.println("Score1()");
  }
  
  public Score1(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

  @Override
  public String toString() {
    return "Score1 [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
        + sum + ", aver=" + aver + "]";
  }

}
