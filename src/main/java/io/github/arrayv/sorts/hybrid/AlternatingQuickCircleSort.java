package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class AlternatingQuickCircleSort extends Sort {

    public AlternatingQuickCircleSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Alternating Quick Circle");
        this.setRunAllSortsName("Alternating Quick Circle Sort");
        this.setRunSortName("Alternating Quickcirclesort");
        this.setCategory("Hybrid Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
        this.setAuthors("AnOzen");
    }

    public int end;

    private int AQC(int[] array, int start, int end, int level, int swapCount) {
        if (start >= end)
            return swapCount;
        if (level % 2 == 0) { // Circlesort
            int high = end;
            int low = start;
            int mid = (end - start) / 2;

            while (start < end) {
                if (end < this.end && Reads.compareIndices(array, start, end, 0.25, true) > 0) {
                    Writes.swap(array, start, end, 0.5, true, false);
                    swapCount++;
                }

                start++;
                end--;
            }

            if (start == end && start + 1 < this.end
                    && Reads.compareIndices(array, start, start + 1, 0.5, true) > 0) {
                Writes.swap(array, start, start + 1, 1, true, false);
                swapCount++;
            }

            swapCount = this.AQC(array, low, low + mid, level + 1, swapCount);
            if (low + mid + 1 < this.end)
                swapCount = this.AQC(array, low + mid + 1, high, level + 1, swapCount);

        } else { // Quicksort

            int pivot = start + (end - start + 1) / 2;
            int x = array[pivot];

            int i = start;
            int j = end;

            Highlights.markArray(3, pivot);

            while (i <= j) {
                while (Reads.compareValues(array[i], x) == -1) {
                    i++;
                    Highlights.markArray(1, i);
                    Delays.sleep(0.5);
                }
                while (Reads.compareValues(array[j], x) == 1) {
                    j--;
                    Highlights.markArray(2, j);
                    Delays.sleep(0.5);
                }

                if (i <= j) {
                    // Follow the pivot and highlight it.
                    if (i == pivot) {
                        Highlights.markArray(3, j);
                    }
                    if (j == pivot) {
                        Highlights.markArray(3, i);
                    }

                    if (i != j && Reads.compareIndices(array, i, j, 0.5, true) != 0) {
                        Writes.swap(array, i, j, 1, true, false);
                        swapCount++;
                    }

                    i++;
                    j--;
                }
            }

            swapCount = this.AQC(array, start, j, level + 1, swapCount);
            swapCount = this.AQC(array, i, end, level + 1, swapCount);

        }

        return swapCount;
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        this.end = sortLength;

        int numberOfSwaps = 0;
        do {
            numberOfSwaps = this.AQC(array, 0, sortLength - 1, 0, 0);
        } while (numberOfSwaps != 0);
    }

}
