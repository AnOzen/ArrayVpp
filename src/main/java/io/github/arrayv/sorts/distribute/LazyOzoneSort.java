package io.github.arrayv.sorts.distribute;

import java.util.ArrayList;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.utils.ArrayVList;
public class LazyOzoneSort extends Sort{

  public LazyOzoneSort(ArrayVisualizer arrayVisualizer) {
    super(arrayVisualizer);

    this.setSortListName("Lazy Ozone");
    this.setRunAllSortsName("Lazy Ozone Sort");
    this.setRunSortName("Lazy Ozonesort");
    this.setCategory("Distribution Sorts");
    this.setConstant("n^2");
    this.setAuthors("AnOzen");
    this.setBucketSort(false);
    this.setRadixSort(false);
    this.setUnreasonablySlow(false);
    this.setUnreasonableLimit(0);
    this.setBogoSort(false);
  }

  @Override
  public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {

    int library = Math.min(sortLength / 16, 20);

    int pos;
    int current;
    for(int i = 0; i < library; i++) {
      current = array[i];
      pos = i - 1;

      while(pos >= 0 && Reads.compareValues(array[pos], current) > 0){
        Writes.write(array, pos + 1, array[pos], 0.5, true, false);
        pos--;
      }
      Writes.write(array, pos + 1, current, 0.5, true, false);
    }

    ArrayList<ArrayVList> lists = new ArrayList<>();
    lists.add(Writes.createArrayList());
    for(int i = 0; i < library; i++){
      Highlights.markArray(1, i);
      lists.add(Writes.createArrayList());
      lists.get(i + 1).add(array[i], 0.5, false);
    }
    Highlights.clearAllMarksPossible();

    for(int i = library; i < sortLength; i++){
      boolean up = true;
      for(int j = 0; j < library; j++){
        if(Reads.compareIndices(array, i, j, 0.25, true) < 0){
          lists.get(j).add(array[i]);
          up = false;
          break;
        }
      }
      if(up){
        lists.get(library).add(array[i]);
      }
    }

    Highlights.clearAllMarksPossible();

    int k = 0;
    for(ArrayVList list : lists){
      for(int i : list){
        Writes.write(array, k, i, 0.5, true, false);
        k++;
      }
    }

    for(int i = 0; i < sortLength; i++) {
      current = array[i];
      pos = i - 1;

      while(pos >= 0 && Reads.compareValues(array[pos], current) > 0){
        Writes.write(array, pos + 1, array[pos], 0.5, true, false);
        pos--;
      }
      Writes.write(array, pos + 1, current, 0.5, true, false);
    }

    Writes.deleteAllExternalArrays();
  }

}
