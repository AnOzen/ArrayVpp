package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class OptimizedReverseTwitterSort extends Sort {

    public OptimizedReverseTwitterSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Optimized Reverse Twitter");
        this.setRunAllSortsName("Optimized Reverse Twitter Sort");
        this.setRunSortName("Optimized Reverse Twittersort");
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
        for (int j = sortLength - 1; j >= 0; j--) {
            if (!ratioed)
                break;
            ratioed = false;
            for (int i = 0; i < j; i++) {
                if (Reads.compareIndices(array, i, i + 1, 0.025, true) > 0) {
                    // W + Ratio
                    ratioed = true;
                    int ratio;
                    if (array[i + 1] == 0) {
                        ratio = j - i;
                    } else {
                        ratio = (int) Math.ceil(array[i] / array[i + 1]);
                    }
                    Writes.insert(array, i, Math.min(i + ratio, j), 0.075, true, false);
                }
            }
        }

    }

}
