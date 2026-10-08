package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;
import io.github.arrayv.utils.ArrayVList;

public class WeaveSafeStalinMergeSortSlow extends Sort {

    public WeaveSafeStalinMergeSortSlow(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Weave Safe Stalin Merge (Slow)");
        this.setRunAllSortsName("Weave Safe Stalin Merge Sort (Slow)");
        this.setRunSortName("Weave Safe Stalin Mergesort (Slow)");
        this.setCategory("Hybrid Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
        this.setAuthors("AnOzen");
    }

    private void merge(int[] array, int start, int mid, int end, int d, ArrayVList tmp, ArrayVList tmp2) {
        if (start == mid)
            return;

        Writes.recordDepth(d++);
        Writes.recursion();
        merge(array, start, (mid + start) / 2, mid, d, tmp, tmp2);
        Writes.recursion();
        merge(array, mid, (mid + end) / 2, end, d, tmp, tmp2);

        tmp.clear();
        tmp2.clear();

        int left = start;
        int right = mid;

        int correctsize = 0;
        do {

            while (left < mid) {
                Highlights.markArray(1, left);
                Highlights.markArray(2, right);
                tmp.add(array[left], 0.75, true);
                left++;
                if (right < end)
                    tmp.add(array[right], 0.75, true);
                right++;
            }
            while (right < end) {
                Highlights.markArray(2, right);
                tmp.add(array[right], 0.75, true);
                right++;
            }
            Highlights.clearAllMarks();

            for (int j = 0; j < tmp.size(); j++) {
                Writes.write(array, start + j, tmp.get(j), 0.75, true, false);
            }

            tmp.clear();

            int correct = start;
            int incorrect = start + 1;

            tmp.add(array[correct], 0.75, true);

            while (incorrect < end) {
                if (Reads.compareIndices(array, correct, incorrect, 0.25, true) > 0) {
                    tmp2.add(array[incorrect], 0.75, true);
                } else {
                    correct = incorrect;
                    tmp.add(array[correct], 0.75, true);
                }
                incorrect++;
            }

            int i = start;
            for (int j : tmp2) {
                Writes.write(array, i, j, 0.75, true, false);
                i++;
            }
            for (int j : tmp) {
                Writes.write(array, i, j, 0.75, true, false);
                i++;
            }

            left = start;
            mid = start + tmp2.size();
            right = start + tmp2.size();
            correctsize = tmp.size();

            tmp.clear();
            tmp2.clear();
        } while (correctsize < end - start);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {

        int start = 0;
        int end = sortLength;
        int mid = start + ((end - start) / 2);

        ArrayVList tmp = Writes.createArrayList();
        ArrayVList tmp2 = Writes.createArrayList();

        merge(array, start, mid, end, 0, tmp, tmp2);
        Writes.deleteAllExternalArrays();

    }

}
