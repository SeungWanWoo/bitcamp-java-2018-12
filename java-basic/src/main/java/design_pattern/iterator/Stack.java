package design_pattern.iterator;

public class Stack<E> implements Cloneable{

  public static final int DEFAULT_SIZE = 5;
  Object[] list;
  int size;

  public Stack() {
    list = new Object[DEFAULT_SIZE];
  }

  public void push(E value) {
    // 맨 마지막에 추가한다.
    // 배열의 크기가 작다면 확장해야 한다.
    if (list.length == size) {
      int oldLength = list.length;
      int newLength = oldLength + (oldLength >> 1);
      Object[] temp = new Object[newLength];
      for (int i = 0; i < oldLength; i++) {
        temp[i] = list[i];
      }
      list = temp;
    }
    list[size++] = value; 
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    // 맨 마지막 값을 꺼내 리턴한다.
    // 꺼낸 값을 배열에서 제거되어야 한다.
    if (size == 0)
      return null;
    
    return (E) list[--size];
  }

  public boolean empty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      // 이 클래스는 Stack에서 값을 꺼내주는 일을 전문적으로 한다.
      // => 이런 일을 하는 객체를 "Iterator"라 부른다.
      // 
      int index = 0;
      
      @Override
      public boolean hasNext() {
        return index < size();
      }
      
      @SuppressWarnings("unchecked")
      @Override
      public E next() {
        int lastIndex = size - 1;
        return (E) list[lastIndex - (index++)];
      }
    };
  }
  
  
  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() throws CloneNotSupportedException {
    return (Stack<E>) super.clone();
  }
}
