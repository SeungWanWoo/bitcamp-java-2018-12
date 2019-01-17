package ch12.b;

public class Score {
  String name;
  int kor;
  int eng;
  int math;
  
  // 계산 결과는 조작하지 못하도록 접근을 제한하자!
  private int sum;
  private float aver;
  
  // 대신 결과 값을 꺼낼 수 있는 메서드를 제공한다.
  public int getSum() {
    return this.sum;
  }
  
  public float getAver() {
    return this.aver;
  }
  
  void compute() {
    this.sum = this.eng + this.kor + this.math;
    this.aver = this.sum / 3f;
  }
}
