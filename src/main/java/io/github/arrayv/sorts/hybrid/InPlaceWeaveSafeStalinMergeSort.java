
package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class InPlaceWeaveSafeStalinMergeSort extends Sort {

    public InPlaceWeaveSafeStalinMergeSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("In-Place Weave Safe Stalin Merge");
        this.setRunAllSortsName("In-Place Weave Safe Stalin Merge Sort");
        this.setRunSortName("In-Place Weave Safe Stalin Mergesort");
        this.setCategory("Hybrid Sorts");
        this.setAuthors("AnOzen");
        // this.setConstant("n^2");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);

    }

    private void weaveSafeStalin(int[] array, int start, int end) {
        int moved = 0;
        for (int i = start; i < end; i++) {
            if (Reads.compareIndices(array, i, i + 1, 0.25, true) > 0) {
                Writes.insert(array, i + 1, start + moved++, 0.5, true, false);
            }
        }
        if (moved == 0)
            return;
        this.weaveMerge(array, start, end - 1, start + moved - 1);
    }

    private void weaveMerge(int[] arr, int min, int max, int mid) {
        if (min == max)
            return;

        int i = 0;
        int j = 0;
        while (true) {
            if (!(i + min < mid + 1 && j + mid + 1 < max))
                break;
            Writes.insert(arr, mid + 1 + j, min + (i * 2 + 1), 0.5, true, false);
            i++;
            j++;
        }
        this.weaveSafeStalin(arr, min, max);
    }

    private void weaveMergeSort(int[] array, int min, int max) {
        if (max - min == 0) { // only one element.
            Delays.sleep(1); // no swap
        } else if (max - min == 1) { // only two elements and swaps them
            if (Reads.compareValues(array[min], array[max]) == 1) {
                Writes.swap(array, min, max, 0.01, true, false);
            }
        } else {
            int mid = (int) Math.floor((min + max) / 2); // The midpoint

            this.weaveMergeSort(array, min, mid); // sort the left side
            this.weaveMergeSort(array, mid + 1, max); // sort the right side
            this.weaveMerge(array, min, max, mid); // combines them
        }
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        this.weaveMergeSort(array, 0, sortLength - 1);
    }

}
