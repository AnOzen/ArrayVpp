package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class TwitterSort extends Sort {

    public TwitterSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Twitter");
        this.setRunAllSortsName("Twitter Sort");
        this.setRunSortName("Twittersort");
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
            for (int i = sortLength - 1; i > 0; i--) {
                if (Reads.compareIndices(array, i - 1, i, 0.025, true) > 0) {
                    // L + Ratio
                    ratioed = true;
                    int ratio;
                    if (array[i] == 0) {
                        ratio = i;
                    } else {
                        ratio = (int) Math.ceil(array[i - 1] / array[i]);
                    }
                    Writes.insert(array, i, Math.max(i - ratio, 0), 0.075, true, false);
                }
            }
        }

    }

}
