package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class RedditSort extends Sort {

    public RedditSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Reddit");
        this.setRunAllSortsName("Reddit Sort");
        this.setRunSortName("Redditsort");
        this.setCategory("Miscellaneous Sorts");
        this.setAuthors("AnOzen");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        boolean ackshually = true;
        while (ackshually) {
            ackshually = false;
            for (int i = 0; i < sortLength - 1; i++) {
                if (Reads.compareIndices(array, i, i + 1, 0.025, true) > 0) {
                    // Far-Right Pipeline
                    ackshually = true;
                    int wagegap = array[i] - array[i + 1];
                    Writes.insert(array, i, Math.min(i + wagegap, sortLength - 1), 0.075, true, false);
                }
            }
        }

    }

}
