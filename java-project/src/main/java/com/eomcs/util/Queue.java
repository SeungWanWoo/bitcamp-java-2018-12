package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

  public void offer(E value) {
    add(value);
  }
  
  public E poll() {
    return remove(0);
  }
  
  public boolean empty() {
    return tail == head;
  }  
  
  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++)
      temp.offer((E) get(i));
    return temp;
  }
}
