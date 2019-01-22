package algorithm.data_structure;

public class ArrayList4 {
  static final int DEFAULT_LENGTH = 5;
  Object[] list;
  int size = 0;
  
  public ArrayList4() {
    list = new Object[DEFAULT_LENGTH];
  }
  
  public ArrayList4(int capacity) {
    if (capacity > DEFAULT_LENGTH)
      list = new Object[capacity];
    else
      list = new Object[DEFAULT_LENGTH];
  }
  
  public int size() {
    return this.size;
  }
  public void add(Object obj) {
    if (list.length == size)
      increase();
    list[size++] = obj;
  }
  
  public Object[] toArray() {
    if (size < 0 || size >= list.length)
      return null;
    Object[] temp = new Object[size];
    for (int i = 0; i < size; i++) 
      temp[i] = list[i];
    return temp;
  }
  
  public void insert(int index, Object obj) {
    if (list.length == size)
      increase();
    Object temp = list[index];
    
    for (int i = list.length -1; i >= index; i--) {
      list[i + 1] = list[i];
    }
    list[index] = obj;
    size--;
  }
  
  private void increase() {
    int oldSize = list.length;
    int newSize = oldSize + (oldSize >> 1);
    Object[] temp = new Object[newSize];
    for (int i = 0; i < list.length; i++) {
      temp[i] = list[i];
    }
    list = temp;
  }
}
