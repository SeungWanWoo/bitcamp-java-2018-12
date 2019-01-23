package algorithm.data_structure.linkedlist;

import static org.junit.Assert.*;
import org.junit.Test;

public class LinkedList2Test {

  @Test
  public void testAdd() {
    LinkedList2 list = new LinkedList2();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertArrayEquals(new Object[] {100,200,300,400,500,600}, list.toArray());
    
  }

  @Test
  public void testGet() {
    LinkedList2 list = new LinkedList2();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(200, list.get(1));
    assertEquals(400, list.get(3));
    assertNull(list.get(-1));
    assertNull(list.get(6));
  }

  @Test
  public void testSet() {
    LinkedList2 list = new LinkedList2();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(200, list.set(1, 350));
    assertEquals(350, list.get(1));
    assertEquals(400, list.set(3, 450));
    assertEquals(450, list.get(3));
    assertNull(list.set(6, 1000));
    assertNull(list.set(-1, 1000));
  }

  @Test
  public void testInsert() {
    LinkedList2 list = new LinkedList2();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(-1, list.insert(-1, 55));
    assertEquals(-1, list.insert(6, 55));
    
    assertEquals(0, list.insert(2,  55));
    assertArrayEquals(
        new Object[] {100, 200, 55, 300, 400, 500, 600}, list.toArray());
    assertEquals(0, list.insert(0,  55));
    assertArrayEquals(
        new Object[] {55, 100, 200, 55, 300, 400, 500, 600}, list.toArray());
    assertEquals(0, list.insert(7,  55));
    assertArrayEquals(
        new Object[] {55, 100, 200, 55, 300, 400, 500, 55, 600}, list.toArray());
    
  }

  @Test
  public void testRemove() {
    LinkedList2 list = new LinkedList2();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(300, list.remove(2));
    assertEquals(400, list.remove(2));
    assertArrayEquals(
        new Object[] {100, 200, 500, 600}, list.toArray());
    assertEquals(600, list.remove(3));
    assertArrayEquals(
        new Object[] {100, 200, 500}, list.toArray());
    
    assertEquals(100, list.remove(0));
    assertArrayEquals(
        new Object[] {200, 500}, list.toArray());
    
    assertEquals(200, list.remove(0));
    assertEquals(500, list.remove(0));
    assertArrayEquals(
        new Object[] {}, list.toArray());
  }

}
