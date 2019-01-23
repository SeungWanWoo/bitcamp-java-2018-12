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
    if (size < 0 || size > list.length)
      return;
    
    Object temp = list[index];
    
    for (int i = list.length - 1; i >= index; i--) {
      list[i + 1] = list[i];
    }
    list[index] = obj;
    size++;
  }
  
  public Object get(int index) {
    if (size < 0 || size >= list.length)
      return null;
    
    return list[index];
  }
  
  public Object set(int index, Object obj) {
    if (size < 0 || size >= list.length)
      return null;
    
    Object temp = list[index];
    list[index] = obj;
    return temp;
  }
  
  public Object remove(int index) {
    if (size < 0 || size >= list.length)
      return null;
    
    Object temp = list[index];
    for (int i = index; i < list.length - 1; i++) {
      list[i] = list[i + 1];
    }
    size--;
    return temp;
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
