package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class RecursiveStalinSort extends Sort {
    private int blok = -1;

    public RecursiveStalinSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Recursive In-Place Stalin");
        this.setRunAllSortsName("Recursive In-Place Stalin Sort");
        this.setRunSortName("Recursive In-Place Stalinsort");
        this.setCategory("Impractical Sorts");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    private void RSS(int[] array, int start, int end, boolean top) {
        if (start > end)
            return;
        int tail = end;
        int i = start;
        while (i < tail - 1) {
            Highlights.markArray(3, tail);
            Highlights.markArray(4, i);
            if (Reads.compareIndices(array, i, i + 1, 0.025, true) <= 0) {
                i++;
            } else {
                for (int j = i + 1; j < end - 1; j++) {
                    Writes.swap(array, j, j + 1, 0.075, true, false);
                }
                tail--;
            }
        }
        if (top)
            blok = tail;
        Writes.recursion();
        RSS(array, i + 1, end, false);

    }

    // private boolean isSorted(int[] array, int sortLength) {
    // for (int i = 0; i < sortLength; i++) {
    // if (Reads.compareIndices(array, i, i + 1, 0.025, true) == 1)
    // return false;
    // }
    // return true;
    // }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        while (blok < sortLength) {
            Writes.recursion();
            RSS(array, 0, sortLength, true);
            // IndexedRotations.threeReversal(array, 0, blok + 1, sortLength, 0.075, true,
            // false);

            for (int i = 0; i < blok; i++) {
                for (int j = 0; j < sortLength - 1; j++) {
                    Writes.swap(array, j, j + 1, 0.075, true, false);
                }
            }
        }
    }

}
