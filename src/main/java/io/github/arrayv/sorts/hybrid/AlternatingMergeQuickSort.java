package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class AlternatingMergeQuickSort extends Sort {

    public AlternatingMergeQuickSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Alternating Merge Quick");
        this.setRunAllSortsName("Alternating Merge Quick Sort");
        this.setRunSortName("Alternating Mergequicksort");
        this.setCategory("Hybrid Sorts");
        this.setAuthors("AnOzen");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        int[] tmp = Writes.createExternalArray(sortLength);
        AMQ(array, 0, sortLength - 1, 0, tmp);
    }

    private void AMQ(int[] array, int left, int right, int level, int[] tmp) {
        if (left == right)
            return;
        if (level % 2 == 0) { // Quicksort
            int p = left, r = right;
            int[] a = array;
            int pivot = p + (r - p + 1) / 2;
            int x = a[pivot];

            int i = p;
            int j = r;

            Highlights.markArray(3, pivot);

            while (i <= j) {
                while (Reads.compareValues(a[i], x) == -1) {
                    i++;
                    Highlights.markArray(1, i);
                    Delays.sleep(0.5);
                }
                while (Reads.compareValues(a[j], x) == 1) {
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

                    Writes.swap(a, i, j, 1, true, false);

                    i++;
                    j--;
                }
            }

            if (p < j) {
                Writes.recursion();
                AMQ(a, p, j, level + 1, tmp);
            }
            if (i < r) {
                Writes.recursion();
                AMQ(a, i, r, level + 1, tmp);
            }
        } else { // Mergesort
            int start = left;
            int end = right;
            int mid = (start + end) / 2;
            Writes.recursion();
            // merge(array, tmp, start, (mid + start) / 2, mid, d, binary);
            AMQ(array, left, mid, level + 1, tmp);
            Writes.recursion();
            // merge(array, tmp, mid, (mid + end) / 2, end, d, binary);
            AMQ(array, mid, end, level + 1, tmp);

            int low = start;
            int high = mid;

            for (int nxt = 0; nxt < end - start; nxt++) {
                if (low >= mid && high >= end)
                    break;

                Highlights.markArray(1, low);
                Highlights.markArray(2, high);

                if (low < mid && high >= end) {
                    Highlights.clearMark(2);
                    Writes.write(tmp, nxt, array[low++], 1, false, true);
                } else if (low >= mid && high < end) {
                    Highlights.clearMark(1);
                    Writes.write(tmp, nxt, array[high++], 1, false, true);
                } else if (Reads.compareValues(array[low], array[high]) <= 0) {
                    Writes.write(tmp, nxt, array[low++], 1, false, true);
                } else {
                    Writes.write(tmp, nxt, array[high++], 1, false, true);
                }
            }
            Highlights.clearMark(2);

            for (int i = 0; i < end - start; i++) {
                Highlights.markArray(tmp, 2, i);
                Writes.write(array, start + i, tmp[i], 1, true, false);
            }
        }
    }

}
