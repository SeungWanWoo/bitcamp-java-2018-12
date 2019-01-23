package algorithm.data_structure.linkedlist;

public class LinkedList2 {
  Node head;
  Node tail;
  int size;
  
  public LinkedList2() {
    head = new Node();
    tail = head;
    size = 0;
  }
  
  public Object[] toArray() {
    Object[] arr = new Object[size()]; 
    Node cursor = head;
    
    int i = 0;
    while (cursor != tail) {
      arr[i++] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }
  
  public void add(Object value) {
    tail.value = value;
    Node node = new Node();
    tail.next = node;
    node.prev = tail;
    tail = node;
    size++;
  }
  
  public Object get(int index) {
    if (index < 0 || index >= size)
      return null;
    Node cursor = head;
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    return cursor.value;
  }
  
  public Object set(int index, Object value) {
    if (index < 0 || index >= size)
      return null;
    
    Node cursor = head;
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    
    Node temp = new Node();
    temp.value = cursor.value;
   
    cursor.value = value;
    return temp.value;
  }
  
  public int insert(int index, Object value) {
    if (index < 0 || index >= size)
      return -1;
    
    Node cursor = head;
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    
    Node node = new Node(value);
    
    node.next = cursor;
    node.prev = cursor.prev;
    cursor.prev = node;
    if (node.prev != null) {
      node.prev.next = cursor.prev;
    } else {
      head = node;
    }
    size++;
    return 0;
  }
  
  public Object remove(int index) {
    if (index < 0 || index >= size)
      return null;
       
    Node cursor = head;
    
    for (int i = 1; i <= index; i++) 
      cursor = cursor.next;
    
    
    if (cursor.prev != null) {
      cursor.prev.next = cursor.next;
    } else {
      head = cursor.next;
    }
    cursor.next.prev = cursor.prev;
    Object temp = cursor.value;
    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;
    size--;
    return temp;
  }
  
  public int size() {
    return size;
  }
}
