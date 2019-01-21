package ch17.c;

public class BubbleSort extends Sorter {
  
  @Override
  public void sort(int[] values) {
    int size = values.length;
    for (int i = 0; i < size - 1; i++) {
      boolean swapped = false;
      
      for(int j = 0; j < (size - i) - 1; j++) {
        if (values[j] > values[j + 1]) {
          //System.out.printf("%d < == > %d\n", values[j], values[j + 1]);
          int tmp = values[j];
          values[j] = values[j + 1];
          values[j + 1] = tmp;
        }
      }
    }
  }
}
