package algorithm.data_structure.array;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ArrayListTest2 {
  
  @Test
  public void testAdd() {
    ArrayList list = new ArrayList();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    
    assertArrayEquals(new Object[] {100, 200,300,400,500}, list.toArray());
  }
  
  @Test
  public void testInsert() {
    ArrayList list = new ArrayList();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    
    list.insert(2, 55);
    
    assertArrayEquals(new Object[] {100, 200,55,300,400,500}, list.toArray());
  }
  
  @Test
  public void testGet() {
    ArrayList list = new ArrayList();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    
    
    assertEquals(200, list.get(1));
  }
  
  @Test
  public void testSet() {
    ArrayList list = new ArrayList();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    
    assertEquals(300, list.set(2, 100));
    assertEquals(100, list.get(2));
  }
  
  @Test
  public void testRemove() {
    ArrayList list = new ArrayList();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    
    assertEquals(300, list.remove(2));
    assertEquals(400, list.get(2));
  }
}
