package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class ICantBelieveItCanSort extends Sort {

    public ICantBelieveItCanSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("I Can't Believe It Can");
        this.setRunAllSortsName("I Can't Believe It Can Sort");
        this.setRunSortName("I Can't Believe It Can Sort");
        this.setCategory("Exchange Sorts");
        this.setAuthors("Stanley P. Y. Fung");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        for (int i = 0; i < sortLength; i++) {
            for (int j = 0; j < sortLength; j++) {
                Highlights.markArray(1, i);
                Highlights.markArray(2, j);
                Delays.sleep(0.025);
                if (Reads.compareValues(array[i], array[j]) == -1) {
                    Writes.swap(array, i, j, 0.075, true, false);
                }

            }
        }
    }

}
