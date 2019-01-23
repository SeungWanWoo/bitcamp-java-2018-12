package algorithm.data_structure.array;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import algorithm.data_structure.ArrayList4;

public class ArrayListTest3 {
  @Test
  public void testAdd() {
    ArrayList4 list = new ArrayList4();
    
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
    ArrayList4 list = new ArrayList4();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(200, list.get(1));
  }
  
  @Test
  public void testSet() {
    ArrayList4 list = new ArrayList4();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    list.set(2, 350);
    assertEquals(350, list.get(2));
  }
  
  @Test
  public void testRemove() {
    ArrayList4 list = new ArrayList4();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    assertEquals(300, list.remove(2));
    assertEquals(400, list.get(2));
    assertArrayEquals(new Object[] {100, 200, 400, 500, 600}, list.toArray());
  }
  
  @Test
  public void testInsert() {
    ArrayList4 list = new ArrayList4();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
  }
}
