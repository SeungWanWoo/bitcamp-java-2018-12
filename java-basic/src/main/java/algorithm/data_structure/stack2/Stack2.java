package algorithm.data_structure.stack2;

public class Stack2<E> {
  static final int DEFAULT_SIZE = 5;
  Object[] list;
  int size;
  
  public Stack2() {
    list = new Object[DEFAULT_SIZE];
  }
  
  public void push(E value) {
    if (size == list.length) {
      Object[] temp = new Object[list.length + (list.length >> 1)];
      for (int i = 0; i < list.length; i++) {
        temp[i] = list[i];
      }
      list = temp;
    }
    list[size++] = value;
  }
  
  @SuppressWarnings("unchecked")
  public E pop() {
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
  
  
}
