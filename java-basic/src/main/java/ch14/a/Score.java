package ch14.a;

public class Score {
  protected String name;
  protected int kor;
  protected int eng;
  protected int math;
  protected int sum;
  protected float aver;
  
  public void compute() {
    this.sum = this.eng + this.kor + this.math;
    this.aver = this.sum / 3f;
  }

  public int getSum() {
    return this.sum;
  }
  
  public float getAver() {
    return this.aver;
  }
}