package algorithm.data_structure.array;

import static org.junit.Assert.assertArrayEquals;
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
  public void testInsert() {
    ArrayList4 list = new ArrayList4();
    
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);
    list.add(600);
    
    list.insert(2, 222);
    assertArrayEquals(new Object[] {100,200,222,300,400,500,600}, list.toArray());
  }
}
