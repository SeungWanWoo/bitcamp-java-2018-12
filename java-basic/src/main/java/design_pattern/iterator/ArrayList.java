package design_pattern.iterator;

import java.lang.reflect.Array;

public class ArrayList<E> {
  static final int DEFAULT_SIZE = 5;
  Object[] arr;
  int size;
  
  public ArrayList() {
    this(0);
  }
  
  public ArrayList(int capacity) {
    if (capacity > DEFAULT_SIZE)
      arr = new Object[capacity];
    else
      arr = new Object[DEFAULT_SIZE];
  }
  
  public int size() {
    return this.size;
  }
  
  public void add(E value) {
    // 현재 배열이 꽉 찼다면 현재 배열 크기의 50% 만큼 증가시켜라.
    if (arr.length == size) 
      increase();
    arr[size++] = value;
  }
  
  public Object[] toArray() {
    // 데이터의 개수만큼 배열을 만들고 리턴
    Object[] obj = new Object[size];
    for (int i = 0; i < size; i++) {
      obj[i] = arr[i];
    }
    return obj;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    // 데이터의 개수만큼 배열을 만들고 리턴
    T[] obj = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
    for (int i = 0; i < size; i++) {
      obj[i] = (T) arr[i];
    }
    return obj;
  }
  
  public int insert(int index, E value) {
    // 현재 배열이 꽉 찼다면 현재 배열 크기의 50% 만큼 증가시켜라.
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 저장하지 말라.
    // 삽입할 위치의 항목부터 이후의 항목들을 뒤로 밀어라.
    if (index < 0 || index >= size)
      return -1;
    if (arr.length == size) 
      increase();
    
    for (int i = size - 1; i >= index; i--) {
      arr[i + 1] = arr[i];
    }
    arr[index] = value;
    size++;
    return 0;
  }
  
  @SuppressWarnings("unchecked")
  public E get(int index) {
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 null을 리턴하라.
    if (index < 0 || index >= size)
      return null;
    return (E) arr[index];
  }
  
  @SuppressWarnings("unchecked")
  public E set(int index, E value) {
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 변경하지 말라. 리턴 값은 null이다.
    if (index < 0 || index >= size)
      return null;
    E obj = (E) this.arr[index];
    arr[index] = value;
    return obj;
  }
  
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    // 유효 인덱스(현재 배열에 데이터가 저장된 방 번호)가 아니면 삭제하지 말라.
    // 삭제한 후 다음 항목을 앞으로 당긴다.
    if (index < 0 || index >= size)
      return null;
    
    E obj = (E) arr[index];
    
    for (int i = index; i < size - 1; i++) {
      arr[i] = arr[i + 1];
    }
    size--;
    return obj;
  }
  private void increase() {
      int oldLength = arr.length;
      int newLength = oldLength + (oldLength >> 1);
      Object[] obj = new Object[newLength];
      for (int i = 0; i < arr.length; i++) {
        obj[i] = arr[i];
      }
      arr = obj;
  }
  
  // 자신이 보유한 데이터를 꺼내주는 일을 하는 객체를 알려주는 메서드
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      // 이 클래스는 ArrayList에서 값을 꺼내주는 일을 전문적으로 한다.
      // => 이런 일을 하는 객체를 "Iterator"라 부른다.
      // 
      int index = 0;
      
      @Override
      public boolean hasNext() {
        return index < size();
      }

      @Override
      public E next() {
        return (E) get(index++);
      }
    };
  }
}
