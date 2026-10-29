package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class ReverseTwitterSort extends Sort {

    public ReverseTwitterSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Reverse Twitter");
        this.setRunAllSortsName("Reverse Twitter Sort");
        this.setRunSortName("Reverse Twittersort");
        this.setCategory("Miscellaneous Sorts");
        this.setAuthors("AnOzen, wingedcatgirl");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        boolean ratioed = true;
        while (ratioed) {
            ratioed = false;
            for (int i = 0; i < sortLength - 1; i++) {
                if (Reads.compareIndices(array, i, i + 1, 0.025, true) > 0) {
                    // W + Ratio
                    ratioed = true;
                    int ratio;
                    if (array[i + 1] == 0) {
                        ratio = sortLength - 1 - i;
                    } else {
                        ratio = (int) Math.ceil(array[i] / array[i + 1]);
                    }
                    Writes.insert(array, i, Math.min(i + ratio, sortLength - 1), 0.075, true, false);
                }
            }
        }

    }

}
