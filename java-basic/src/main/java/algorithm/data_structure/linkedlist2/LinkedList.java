// static nested class 문법 적용 - Node 클래스를 스태틱 중첩 클래스로 정의한다.

package algorithm.data_structure.linkedlist2;

public class LinkedList {
  protected Node head;
  protected Node tail;
  protected int size;
  
  public LinkedList() {
    head = new Node();
    tail = head;
    size = 0;
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
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
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
  
  public Object set(int index, Object value) {
    if (index < 0 || index >= size)
      return null;
    Node cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    Object old = cursor.value;
    cursor.value = value;
    return old;
  }
  public int insert(int index, Object value) {
    if (index < 0 || index >= size)
      return -1;
    Node node = new Node(value);
    Node cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    node.next = cursor;
    node.prev = cursor.prev;
    cursor.prev = node;
    if (node.prev != null) {
      node.prev.next = node;
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
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    if (cursor.prev != null) {
      cursor.prev.next = cursor.next;
    } else {
      head = cursor.next;
    }
    cursor.next.prev = cursor.prev;
    Object old = cursor.value;
    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;
    size--;
    return old;
  }
  
  public int size() {
    return size;
  }
  
  // LinkedList에서만 쓰이는 Node 클래스를 중첩 클래스로 만든다.
  // => Node 클래스는 LinkedList의 특정 인스턴스를 사용하지 않는다.
  // => 그래서 static 중첩 클래스로 정의하는 것이다.
  // => 외부에 공개할 일이 없기 때문에 private으로 접근을 제한한다.
  private static class Node {
    // LinkedList 내부에서만 사용하기 때문에 Node의 멤버를 public으로 공개할
     // 필요는 없다. 그냥 default로 두어라!
    Object value;
    Node next;
    Node prev;
    
    Node() {}
    
    Node(Object value) {
      this.value = value;
    }
  }
}
