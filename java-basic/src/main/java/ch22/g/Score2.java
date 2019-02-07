package ch22.g;

import java.io.Serializable;

public class Score2 implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float aver;
  
  public Score2 () {
    System.out.println("Score2()");
  }
  
  public Score2(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

  @Override
  public String toString() {
    return "Score2 [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
        + sum + ", aver=" + aver + "]";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKor() {
    return kor;
  }

  public void setKor(int kor) {
    this.kor = kor;
  }

  public int getEng() {
    return eng;
  }

  public void setEng(int eng) {
    this.eng = eng;
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  public float getAver() {
    return aver;
  }

  public void setAver(float aver) {
    this.aver = aver;
  }
  
}
