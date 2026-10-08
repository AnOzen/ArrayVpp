package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.utils.ArrayVList;

public class StableAlternatingMergeQuickSort extends Sort {
    private int length;

    public StableAlternatingMergeQuickSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Stable Alternating Merge Quick");
        this.setRunAllSortsName("Stable Alternating Merge Quick Sort");
        this.setRunSortName("Stable Alternating Merge Quicksort");
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
        this.length = sortLength;
        SAMQ(array, 0, sortLength - 1, 0, tmp);
    }

    /* Partition/Quicksort "Stable Sort" version using O(n) space */
    private int stablePartition(int[] array, int start, int end) {
        int pivotValue = array[start]; // poor pivot choice
        Highlights.markArray(3, start);

        ArrayVList leftList = Writes.createArrayList(this.length);
        ArrayVList rightList = Writes.createArrayList(this.length);

        for (int i = start + 1; i <= end; i++) {
            Highlights.markArray(1, i);

            if (Reads.compareValues(array[i], pivotValue) == -1) {
                // Writes.mockWrite(end - start, leftList.size(), array[i], 0.25);
                // Writes.arrayListAdd(leftList, array[i]);
                leftList.add(array[i], 0.25, false);
            } else {
                // Writes.mockWrite(end - start, rightList.size(), array[i], 0.25);
                // Writes.arrayListAdd(rightList, array[i]);
                rightList.add(array[i], 0.25, false);
            }
        }
        int startIndex = start;

        /* Recreate array */
        for (int num : leftList) {
            this.Writes.write(array, startIndex++, num, 0.25, false, false);
            this.Highlights.markArray(1, startIndex);
        }

        int newPivotIndex = start + leftList.size();

        Writes.write(array, newPivotIndex, pivotValue, 0.25, false, false);
        Highlights.markArray(1, newPivotIndex);
        int startIndex1 = newPivotIndex + 1;

        for (int num1 : rightList) {
            this.Writes.write(array, startIndex1++, num1, 0.25, false, false);
            this.Highlights.markArray(1, startIndex1);
        }

        Writes.deleteArrayList(leftList);
        Writes.deleteArrayList(rightList);

        return newPivotIndex;
    }

    private void SAMQ(int[] array, int left, int right, int level, int[] tmp) {
        if (left >= right)
            return;
        if (level % 2 == 0) { // Stable Quicksort

            int pivotIndex = this.stablePartition(array, left, right);
            this.SAMQ(array, left, pivotIndex - 1, level + 1, tmp);
            this.SAMQ(array, pivotIndex + 1, right, level + 1, tmp);

        } else {
            int start = left;
            int end = right;
            int mid = (start + end) / 2;
            Writes.recursion();
            // merge(array, tmp, start, (mid + start) / 2, mid, d, binary);
            SAMQ(array, left, mid, level + 1, tmp);
            Writes.recursion();
            // merge(array, tmp, mid, (mid + end) / 2, end, d, binary);
            SAMQ(array, mid, end, level + 1, tmp);

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
