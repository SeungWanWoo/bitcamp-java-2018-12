package algorithm.data_structure.linkedlist;

public class Node2 {
  Object value;
  Node2 next;
  Node2 prev;
  
  Node2() {}
  Node2(Object value) {
    this.value = value;
  }
  
  Node2(Object value, Node2 next, Node2 prev) {
    this(value);
    this.next = next;
    this.prev = prev;
  }
}
