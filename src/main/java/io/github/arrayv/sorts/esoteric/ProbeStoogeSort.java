package io.github.arrayv.sorts.esoteric;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.BogoSorting;

public class ProbeStoogeSort extends BogoSorting {

    public ProbeStoogeSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Probe Stooge");
        this.setRunAllSortsName("Probe Stooge Sort");
        this.setRunSortName("Probe Stooge Sort");
        this.setCategory("Esoteric Sorts");
        this.setAuthors("AnOzen");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(true);
        this.setUnreasonableLimit(256);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        while (!isArraySorted(array, sortLength)) {
            int[] triple = new int[] {
                    randInt(0, sortLength),
                    randInt(0, sortLength),
                    randInt(0, sortLength) };

            if (triple[0] > triple[1]) {
                int temp = triple[0];
                triple[0] = triple[1];
                triple[1] = temp;
            }
            if (triple[1] > triple[2]) {
                int temp = triple[1];
                triple[1] = triple[2];
                triple[2] = temp;
            }
            if (triple[0] > triple[1]) {
                int temp = triple[0];
                triple[0] = triple[1];
                triple[1] = temp;
            }

            Highlights.markArray(1, triple[0]);
            Highlights.markArray(2, triple[1]);
            Highlights.markArray(3, triple[2]);

            Delays.sleep(0.025);

            if (array[triple[0]] > array[triple[1]]) {
                Writes.swap(array, triple[0], triple[1], 0.075, true, false);
            }
            if (array[triple[1]] > array[triple[2]]) {
                Writes.swap(array, triple[1], triple[2], 0.075, true, false);
            }
            if (array[triple[0]] > array[triple[1]]) {
                Writes.swap(array, triple[0], triple[1], 0.075, true, false);
            }

        }
    }

}
