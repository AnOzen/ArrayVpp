package io.github.arrayv.sorts.distribute;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.utils.ArrayVList;
public class RecursiveOzoneSort extends Sort {

  public RecursiveOzoneSort(ArrayVisualizer arrayVisualizer) {
    super(arrayVisualizer);
    this.setSortListName("Ozone (Recursive)");
    this.setRunAllSortsName("Ozone Sort (Recursive)");
    this.setRunSortName("Ozonesort (Recursive)");
    this.setCategory("Distribution Sorts");
    this.setAuthors("AnOzen");
    this.setConstant("n log n");
    this.setBucketSort(false);
    this.setRadixSort(false);
    this.setUnreasonablySlow(false);
    this.setUnreasonableLimit(0);
    this.setBogoSort(false);
  }

  private void Ozone(int[] array, int start, int end, int depth, ArrayVList[] lists){

    Writes.recordDepth(depth++);

    int library = Math.max(Math.min((end - start) / 16, 20), 4);

    for (ArrayVList list : lists) {
      Writes.arrayListClear(list);
    }

    int pos;
    int current;
    for(int i = start; i < start + library; i++) {
      if(i >= end) return;
      current = array[i];
      pos = i - 1;

      while(pos >= start && Reads.compareValues(array[pos], current) > 0){
        Writes.write(array, pos + 1, array[pos], 0.5, true, false);
        pos--;
      }
      Writes.write(array, pos + 1, current, 0.5, true, false);
    }

    for(int i = start; i < start + library; i++){
      Highlights.markArray(1, i);
      lists[i + 1 - start].add(array[i], 0.5, false);
    }
    Highlights.clearAllMarksPossible();

    for(int i = start + library; i < end; i++){
      boolean up = true;
      for(int j = start; j < start + library; j++){
        if(Reads.compareIndices(array, i, j, 0.25, true) < 0){
          lists[j - start].add(array[i]);
          up = false;
          break;
        }
      }
      if(up){
        lists[library].add(array[i]);
      }
    }

    Highlights.clearAllMarksPossible();

    int k = start;
    for(ArrayVList list : lists){
      for(int i : list){
        Writes.write(array, k, i, 0.5, true, false);
        k++;
      }
    }

    int[] lengths = new int[lists.length];
    k = 0;
    for(ArrayVList list : lists){
      lengths[k] = list.size();
      k++;
    }

    k = start;
    for(int len : lengths){
      Writes.recursion();
      this.Ozone(array, k, k + len, depth, lists);
      k += len;
    }

  }

  @Override
  public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
    ArrayVList[] lists = new ArrayVList[21];
    for (int i = 0; i < lists.length; i++) {
      lists[i] = Writes.createArrayList();
    }
    this.Ozone(array, 0, sortLength, 0, lists);
  }

}
