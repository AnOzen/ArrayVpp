package io.github.arrayv.sorts.esoteric;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

public class ProbeSort extends BogoSorting {

    public ProbeSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Probe");
        this.setRunAllSortsName("Probe Sort");
        this.setRunSortName("Probe Sort");
        this.setCategory("Esoteric Sorts");
        this.setAuthors("AnOzen");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(128);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        while (!isArraySorted(array, sortLength)) {
            int i = randInt(0, sortLength), j = randInt(0, sortLength);

            if (i > j) {
                int temp = i;
                i = j;
                j = temp;
            }

            Highlights.markArray(1, i);
            Highlights.markArray(2, j);

            Delays.sleep(0.025);

            if (array[i] > array[j]) {
                Writes.swap(array, i, j, 0.075, true, false);
            }

        }
    }

}
